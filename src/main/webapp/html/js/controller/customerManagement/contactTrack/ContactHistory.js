Ext.override(Ext.form.CheckboxGroup, {
    // 在inputValue中找到定义的内容后，设置到items里的各个checkbox中
    setValue: function(value) {
        value = value == undefined
                ? "" : value;
        this.items.each(function(f) {
            if (value.indexOf(f.inputValue) != -1) {
                f.setValue(true);
            } else {
                f.setValue(false);
            }
        });
    },
    // 在Field类中定义的getName方法不符合CheckBoxGroup中默认的定义，因此需要重写该方法使其可以被BasicForm找到
    getName: function() {
        return this.name;
    }
});
Ext.define('CRM.controller.customerManagement.contactTrack.ContactHistory', {
    extend: 'Ext.app.Controller',
    views: [ 'customerManagement.contactTrack.ContactHistoryList', 'customerManagement.contactTrack.ContactHistoryDetail',
            'customerManagement.contactTrack.ReFeedbackList', 'customerManagement.contactTrack.ContactHistoryBody' ],
    stores: [ 'customerManagement.contactTrack.ContactHistoryStore', 'salesManagement.salesEvent.SalesEventStore' ],
    init: function() {
        this.control({
            // 监听点击grid每一行触发的详细信息事件
            'contacthistorylist': {
                select: this.detailContactHistory,
                selectionchange: this.selectionchange,
                scrollershow: utils.scrollershow
            },
            // 监听list的textfield失去焦点时去除前后空格
            'contacthistorylist textfield': {
                blur: utils.trimSpaceSearch
            },
            // 监听列表页面的高级搜索打开时的联系类型combo中“不限”和其他选项的关系
            'contacthistorylist combobox[id=contactHistoryTypeSearch]': {
                select: utils.comboChangeSelect
            },
            // 监听列表页面的高级搜索打开时的联系方式combo中“不限”和其他选项的关系
            'contacthistorylist combobox[id=contactHistoryWaySearch]': {
                select: utils.comboChangeSelect
            },
            // 监听列表页面的高级搜索按钮按下时发生的事件
            'contacthistorylist button[id=openOrCloseContactHistorySuperSearch]': {
                click: this.openOrCloseSuperSearch
            },
            // 监听列表页面的模糊查询和高级查询
            'contacthistorylist button[action=queryBtn]': {
                click: this.search
            },
            // 监听列表页面的重新反馈按钮
            'contacthistorylist button[action=contactHistoryResponseEdit]': {
                click: this.responseEdit
            },
            // 监听重新反馈页面的‘是否反馈’单选按钮
            'refeedbacklist radiogroup[id=ifContact2]': {
                change: this.isOrNotContact
            },
            // 监听保存用户反馈信息的按钮
            'refeedbacklist button[id=feedBackSave1]': {
                click: this.feedBackSave
            },
            // 监听反馈信息中的‘重置’按钮
            'refeedbacklist button[action=doClear1]': {
                click: this.resetRecord
            },
            // 监听反馈信息中的‘取消’按钮
            'refeedbacklist button[action=doCancel1]': {
                click: this.doCancel
            },
            'refeedbacklist datetimefield': {
                blur: utils.trimSpace
            },
//            'refeedbacklist checkboxgroup[id=chancetype1]': {
//                change: this.chanceTypeSelect
//            },
//            // 监听机会事件选择下拉框
//            'refeedbacklist combobox[id=chanceevent1]': {
//                select: this.comboAllStatus
//            },
//            // 监听机会事件选择下拉框选择
//            'refeedbacklist combobox[id=chanceevent1]': {
//                focus: this.comboStatusSelect
//            },
            // 监听列表页面的删除按钮
            'contacthistorylist button[action=deleteContactHistory]': {
                click: this.deleteContactHistory
            }
        });
    },
    viewInit: function(treeId, panel, record) {
        var contactHistoryBody = Ext.getCmp('contacthistorybody');
        if (typeof (contactHistoryBody) === 'undefined') {
            contactHistoryBody = Ext.widget('contacthistorybody');
        }
        if (typeof panel !== 'undefined') {
            panel.insert(0, contactHistoryBody);
        } else {
            Ext.getCmp('centercard').insert(1, contactHistoryBody);
            Ext.getCmp('centercard').getLayout().setActiveItem(contactHistoryBody);
        }
        var contacthistorylist = Ext.getCmp('contacthistorylist');
        var contactHistorySuperSearchForm = Ext.getCmp('contactHistorySuperSearchForm');
        var contactHistorySearchText = Ext.getCmp('contactHistorySearchText');
        var contactHistorySearchBtn = Ext.getCmp('contactHistorySearchBtn');
        var contactHistoryOpenOrCloseBtn = Ext.getCmp('openOrCloseContactHistorySuperSearch');
        var contactHistoryGridStore = contacthistorylist.getStore();

        this.record = record;
        var customerID = record.get('customerID');
        contactHistoryGridStore.on('beforeload', function(store, options) {
            var new_params = {
                    customerID: customerID
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        utils.loadPageOne(contactHistoryGridStore);
        if (contacthistorylist.contactTypeMemoryStore.getCount() == 0) {
            contacthistorylist.contactTypeMemoryStore.load({
                params: {
                    code: '00060001'
                }
            });
        }
        if (contacthistorylist.contactWayMemoryStore.getCount() == 0) {
            contacthistorylist.contactWayMemoryStore.load({
                params: {
                    code: '00060002'
                }
            });
        }
        if (contacthistorylist.ifContactMemoryStore.getCount() == 0) {
            contacthistorylist.ifContactMemoryStore.load({
                params: {
                    code: '00060003'
                }
            });
        }
        if (contactHistorySuperSearchForm.isHidden()) {
            contactHistorySearchText.reset();
        } else {
            Ext.getCmp('contactHistoryCustomerSearch').reset();
            Ext.getCmp('contactHistoryWeContactSearch').reset();
            Ext.getCmp('contactHistoryOppositeContactSearch').reset();
            Ext.getCmp('contactHistoryTypeSearch').reset();
            Ext.getCmp('contactHistoryWaySearch').reset();
            Ext.getCmp('contactHistoryIfcontactSearch').reset();
            Ext.getCmp("contactHistoryDateStartSearch").reset();
            Ext.getCmp("contactHistoryDateEndSearch").reset();
            contactHistorySearchBtn.setDisabled(false);
            contactHistorySearchText.setDisabled(false);
            contactHistorySuperSearchForm.hide();
            contactHistoryOpenOrCloseBtn.setText('高级检索');
            contactHistoryOpenOrCloseBtn.up('grid').update();
        }
        utils.authorizationControl(treeId, contacthistorylist);
        return contactHistoryBody;
    },
    // 客户反馈页面中的‘取消’按钮方法
    doCancel: function(button) {
        button.up('window').close();
    },
    detailContactHistory: function(view, record, el, index, e) {
        // 点击grid的每一行右边的form出现不同的详细信息
        Ext.getCmp('contacthistorydetail').getForm().loadRecord(record);
//        var status = record.get('chanceType');
//        var state = record.get('state');
        Ext.getCmp('contacthistorydetail').down('[name=notContantReason]').setVisible(!record.get('ifContact'));
//        if (state != 0) {
//            if (record.get('flowStatus')) {
//                Ext.getCmp('contacthistorydetail').down('#flowStatusName').setValue('已终止');
//            } else {
//                Ext.getCmp('contacthistorydetail').down('#flowStatusName').setValue('进行中');
//            }
//            if (status == 1) {
//                Ext.getCmp('contacthistorydetail').down('#chanceTypeName').setValue('发现机会');
//                Ext.getCmp('contacthistorydetail').down('#checkResult').setValue('未验证');
//            } else if (status == 2) {
//                Ext.getCmp('contacthistorydetail').down('#chanceTypeName').setValue('验证机会');
//                if (record.get('checkResult') || state == 1) {
//                    Ext.getCmp('contacthistorydetail').down('#checkResult').setValue('成功');
//                } else {
//                    Ext.getCmp('contacthistorydetail').down('#checkResult').setValue('失败');
//                }
//            } else {
//                Ext.getCmp('contacthistorydetail').down('#chanceTypeName').setValue('其他');
//                Ext.getCmp('contacthistorydetail').down('#checkResult').setValue('');
//            }
//        } else {
//            Ext.getCmp('contacthistorydetail').down('#chanceTypeName').setValue('无');
//            Ext.getCmp('contacthistorydetail').down('#checkResult').setValue('');
//        }
    },

    selectionchange: function(sm, selections) {
        // 当点击grid不止一行的时候，右边的form不会显示详细信息
        if (selections.length != 1) {
            Ext.getCmp('contacthistorydetail').hide();
        } else {
            Ext.getCmp('contacthistorydetail').show();
        }
        Ext.getCmp('contactHistoryResponseEdit').setDisabled(selections.length != 1);
        Ext.getCmp('deleteContactHistory').setDisabled(selections.length == 0);
    },

    openOrCloseSuperSearch: function(button) {
        // 点击高级搜索按钮，触发高级搜索的Toolbar展开和收缩事件
        var contactHistoryGrid = button.up('grid');
        var superSearchForm = Ext.getCmp('contactHistorySuperSearchForm');
        if (superSearchForm.isHidden()) {
            if (contactHistoryGrid.contactTypeStore.getCount() == 0) {
                utils.searchStoreInset(contactHistoryGrid.contactTypeStore, contactHistoryGrid.contactTypeMemoryStore, Ext
                        .getCmp('contactHistoryTypeSearch'));
            }
            if (contactHistoryGrid.contactWayStore.getCount() == 0) {
                utils.searchStoreInset(contactHistoryGrid.contactWayStore, contactHistoryGrid.contactWayMemoryStore, Ext
                        .getCmp('contactHistoryWaySearch'));
            }
            if (contactHistoryGrid.ifContactStore.getCount() == 0) {
                utils.searchStoreInset(contactHistoryGrid.ifContactStore, contactHistoryGrid.ifContactMemoryStore, Ext
                        .getCmp('contactHistoryIfcontactSearch'));
            }
        }
        utils.openOrCloseSuperQueryBtn(button);
    },

    search: function(button) {
        // 实现简单搜索和高级搜索功能
        utils.query(button);
    },
    resetRecord: function(button) {
        var form = button.up('window').down('form');
        var list = Ext.getCmp('contacthistorylist');
        var record = list.getView().getSelectionModel().getSelection()[0];
        if (record == '') {
            form.getForm().reset();
        } else {
            var ifContact = record.get('ifContact');
            form.down('radiogroup[id=ifContact2]').setValue(ifContact);
            form.down('#noContact').setValue(!ifContact);
            form.loadRecord(record);
//            form.down('checkbox[id=findchance1]').setValue(record.get('state') == 1 || record.get('state') == 3);
//            form.down('checkbox[id=checkchance1]').setValue(record.get('state') == 2 || record.get('state') == 3);
        }
    },
    responseEdit: function(button) {
        var me = this;
        var feedBack = Ext.widget('refeedbacklist');
        var list = Ext.getCmp('contacthistorylist');
        var record = list.getView().getSelectionModel().getSelection()[0];
        feedBack.setTitle("编辑" + Ext.String.htmlEncode(record.get('customerName')) + "反馈信息");
        var ifContact = record.get('ifContact');
        feedBack.down('form').down('radiogroup[id=ifContact2]').setValue(ifContact);
        feedBack.down('form').down('#noContact').setValue(!ifContact);
        if (ifContact) {
            Ext.getCmp('notContantReason1').setVisible(false);
            feedBack.down('form').down('#noContact').setDisabled(true);
//            var status = parseInt(record.get('chanceType'));
//            var state = record.get('state');
//            var end = record.get('flowStatus');
//            var success = record.get('checkResult');
//            if (state != 0) {
////                me.eventID1 = record.get('eventID');
//                if (state == status) {
//                    feedBack.down('form').down('[id=findchance1]').setReadOnly((state != 1 && state != 3) || end);
//                    feedBack.down('form').down('[id=checkchance1]').setReadOnly(end);
//                } else {
//                    if (state != 3) {
//                        feedBack.down('form').down('[id=findchance1]').setReadOnly(true);
//                        feedBack.down('form').down('[id=checkchance1]').setReadOnly(true);
//                    } else {
//                        feedBack.down('form').down('[id=findchance1]').setReadOnly(status != 2);
//                        feedBack.down('form').down('[id=checkchance1]').setReadOnly(status != 2);
//                    }
//                }
//                if (!status && state != 0) {
//                    status = 3;
//                }
//                Ext.getCmp('findchancecontent1').setVisible(state == 1 || state == 3);
//                Ext.getCmp('findchancecontent1').setReadOnly(status != 1 && !(state == status || (state == 3 && state - 1 == status)) || end);
//                Ext.getCmp('checkchancecontent1').setVisible(state == 2 || state == 3);
//                Ext.getCmp('checkchancecontent1').setReadOnly(
//                        status != 2 && (state == status || (state == 3 && state - 1 == status)) || end || status > 2);
//                Ext.getCmp('chancename1').setVisible(state == 1 || state == 3);
//                Ext.getCmp('chanceevent1').setVisible(state == 2);
//                Ext.getCmp('checkresult1').setVisible(state == 2 || state == 3);
//                Ext.getCmp('chancename1').setReadOnly(true);
//
//                Ext.getCmp('chanceevent1').setReadOnly(true);
//                Ext.getCmp('chanceevent1').clearListeners();
//                Ext.getCmp('checkresult1').setReadOnly(state != 3 && !status == 1);
//                feedBack.down('form').down('#failure').setReadOnly((state == 3 || state == 2) && success);
//                if (status == 2) {
//                    feedBack.down('form').down('combobox[id=chanceevent1]').setValue(record.get('eventName'));
//                }
//                feedBack.down('form').down('radiogroup[id = checkresult1]').setValue(success);
//            }
//            feedBack.down('form').down('#failure').setValue(!success);
//            feedBack.down('form').down('checkbox[id=findchance1]').setValue(state == 1 || state == 3);
//            feedBack.down('form').down('checkbox[id=checkchance1]').setValue(state == 2 || state == 3);
//            Ext.getCmp('findchancecontent1').setDisabled(state != 1 && state != 3);
//            Ext.getCmp('checkchancecontent1').setDisabled(state != 2 && state != 3);
//            Ext.getCmp('chancename1').setDisabled(state != 1 && state != 3);
//            Ext.getCmp('chanceevent1').setDisabled(state != 2);
//            Ext.getCmp('checkresult1').setDisabled(state != 2 && state != 3);
//            feedBack.down('form').down('#failure').setDisabled(state != 2 && state != 3);
        }
        feedBack.down('form').getForm().loadRecord(record);
    },
//    // 机会类型复选框
//    chanceTypeSelect: function() {
//        var findChance = Ext.getCmp('findchance1');
//        var checkChance = Ext.getCmp('checkchance1');
//        Ext.getCmp('findchancecontent1').setVisible(findChance.checked);
//        Ext.getCmp('findchancecontent1').setDisabled(!findChance.checked);
//        Ext.getCmp('checkchancecontent1').setVisible(checkChance.checked);
//        Ext.getCmp('checkchancecontent1').setDisabled(!checkChance.checked);
//        Ext.getCmp('checkchancecontent1').setReadOnly(!checkChance.checked);
//        Ext.getCmp('chancename1').setVisible(findChance.checked);
//        Ext.getCmp('chancename1').setDisabled(!findChance.checked);
//        Ext.getCmp('chanceevent1').setVisible(checkChance.checked && !findChance.checked);
//        Ext.getCmp('chanceevent1').setDisabled(!checkChance.checked || findChance.checked);
//        Ext.getCmp('checkresult1').setVisible(checkChance.checked);
//        Ext.getCmp('checkresult1').setDisabled(!checkChance.checked);
//    },
    isOrNotContact: function(button) {
        var win = button.up('window');
        var ifContact = Ext.getCmp('ifContact2');
        var value = ifContact.items.items[0].getValue();
        var realityDateBegin = Ext.getCmp('realityDateBegin1');
        var realityDateEnd = Ext.getCmp('realityDateEnd1');
        var userFeedbackInformation = Ext.getCmp('userFeedbackInformation1');
        var strategy = Ext.getCmp('strategy1');
//        var chanceType = Ext.getCmp('chancetype1');
        var notContantReason = Ext.getCmp('notContantReason1');
        var list = Ext.getCmp('contacthistorylist');
        if (typeof (list) == 'undefined') {
            list = Ext.widget('contacthistorylist');
        }
        var selection = list.getView().getSelectionModel().getSelection()[0];
        win.down('form').loadRecord(selection);
        // 清除无效提示信息
        win.down('form').getForm().clearInvalid();
//        chanceType.setDisabled(!value);
//        chanceType.setVisible(value);
        realityDateBegin.setDisabled(!value);
        realityDateBegin.setVisible(value);
        realityDateEnd.setDisabled(!value);
        realityDateEnd.setVisible(value);
        userFeedbackInformation.setDisabled(!value);
        userFeedbackInformation.setVisible(value);
        strategy.setDisabled(!value);
        strategy.setVisible(value);
        notContantReason.setDisabled(value);
        notContantReason.setVisible(!value);
        if (value) {
            notContantReason.setValue('');
        }
    },
//    eventID1: 0,
//    // 机会事件
//    comboAllStatus: function(combo, index) {
//        combo.up('window').chanceEventStoreByStatus.removeAll();
//        Ext.getCmp('chanceevent1').reset();
//    },
//    // 机会事件筛选
//    comboStatusSelect: function(combo, eOpts) {
//        var me = this;
//        combo.store.removeAll();
//        var list = Ext.getCmp('contacthistorylist');
//        if (typeof (list) == 'undefined') {
//            list = Ext.widget('contacthistorylist');
//        }
//        var selection = list.getView().getSelectionModel().getSelection()[0];
//        combo.up('window').chanceEventStoreByStatus.removeAll();
//        combo.up('window').chanceEventStore.each(function(record) {
//            if (record.get('status') == 1 && selection.get('customerID') == record.get('customerID') && !record.get('isAbolished')) {
//                combo.up('window').chanceEventStoreByStatus.add(record);
//                me.eventID1 = record.get('eventID');
//            }
//        });
//        if (combo.up('window').chanceEventStoreByStatus.getCount() == 0) {
//            messageBox.alert("提示", "该客户没有已发现机会记录，请先添加机会！");
//            Ext.getCmp('chanceevent1').setReadOnly(true);
//        } else {
//            Ext.getCmp('chanceevent1').setReadOnly(false);
//        }
//    },
    feedBackSave: function(button) {
        var me = this;
        var submitFlag = 0;
//        var findChance = Ext.getCmp('findchance1');
//        var checkChance = Ext.getCmp('checkchance1');
        var win = button.up('window');
        var form = win.down('form');
        if (Ext.getCmp('notContantReason1').isDisabled()) {
            submitFlag = 2;
        } else {
            submitFlag = 3;
        }
        function setVal(fieldId, val) {
            if (fieldId != 'validate') {
//                if (fieldId == "chancename") {
//                    form.down('[id=' + fieldId + '1]').markInvalid(val);
//                } else {
                    form.down('[name=' + fieldId + ']').markInvalid(val);
//                }
            }
        }
        if (form.getForm().isValid()) {
            var values = form.getValues();
//            if (submitFlag == 2) {
//                if (findChance.checked && checkChance.checked) {
//                    values.chanceType = 3;
//                }
//            }
            var record = form.getRecord();
            var list = Ext.getCmp("contacthistorylist") || '';
            var store = null;
            if (list != '') {
                store = list.getStore();
            }
            Ext.Ajax.request({
                url: 'updateContactTrackAction.action',
                params: {
                    submitFlag: submitFlag,
                    userID: USER_ID,
                    jsonString: Ext.encode(values)
                },
                success: function(response) {
                    if (CRM.checkResponse(response)) {
                        return;
                    }
                    var responseText = Ext.decode(response.responseText) || '';

                    if (responseText.validate == false) {
                        Ext.iterate(responseText, setVal);
                        messageBox.alert('提示', '输入信息有误，请检查输入信息！');
                    } else {
                        if (record == '') {
                            Ext.crm.msg("添加成功！", "");
                        } else {
                            Ext.crm.msg("编辑成功！", "");
                        }
                        if (list != '') {
                            store.loadPage(store.currentPage);
                        }
                        win.close();
                    }
                },
                failure: function(response) {
                    if (record == '') {
                        messageBox.alert('提示', '添加失败，请联系管理员！');
                    } else {
                        messageBox.alert('提示', '编辑失败，请联系管理员！');
                    }
                }
            });
        } else {
            messageBox.alert('提示', '输入信息有误，请检查输入信息！');
        }
    },

    deleteContactHistory: function(button) {
        var grid = button.up('grid');
        utils.delRecords(grid, 'deleteContactTrackAction.action', 'contactID');
    }
});