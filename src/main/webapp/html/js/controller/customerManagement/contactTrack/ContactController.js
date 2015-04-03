Ext.define('CRM.controller.customerManagement.contactTrack.ContactController', {
    extend: 'Ext.app.Controller',
    views: [ 'customerManagement.contactTrack.ContactTrackList', 'customerManagement.contactTrack.ContactFeedbackList',
            'customerManagement.contactTrack.ContactTrackDetail', 'customerManagement.contactTrack.ContactTrackBody',
            'customerManagement.contactTrack.ContactInfoAdd' ],
    stores: [ 'customerManagement.contactTrack.ContactStore', 'contactManagement.contactProfiles.Contacts',
            'salesManagement.salesEvent.SalesEventStore' ],
    init: function() {
        this.control({
            // 监听客户联系跟踪列表页面
            'contacttrackcontactlist': {
                selectionchange: this.selectionchange,
                select: this.showContactDetail,
                scrollershow: utils.scrollershow,
                itemdblclick: this.editContact
            },
            // 监听列表页面添加按钮
            'contacttrackcontactlist button[action=contactAdd]': {
                click: this.addContactWin
            },
            // 监听添加页面的保存按钮
            'contactinfoadd button[action=addSave]': {
                click: this.saveAdd
            },
            // 监听添加页面的清空按钮
            'contactinfoadd button[action=clear]': {
                click: this.clear
            },
            // 监听添加页面的取消按钮
            'contactinfoadd button[action=doCancel]': {
                click: this.doCancel
            },
            // 监听‘编辑’按钮
            'contacttrackcontactlist button[action=contactEdit]': {
                click: this.editContact
            },
            'contacttrackcontactlist button[id=contactDelete]': {
                click: this.contactDelete
            },
            // 监听列表页面的‘客户反馈’按钮
            'contacttrackcontactlist button[id=contactSaveHistory]': {
                click: this.contactSaveHistory
            },
            // 监听客户反馈页面的‘是否反馈’单选按钮
            'contacttrackcontactfeedbacklist radiogroup[id=ifContact1]': {
                change: this.isOrNotContact
            },
//            'contacttrackcontactfeedbacklist checkboxgroup[id=chancetype]': {
//                change: this.chanceTypeSelect
//            },
            // 监听保存用户反馈信息的按钮
            'contacttrackcontactfeedbacklist button[id=feedBackSave]': {
                click: this.saveAdd
            },
            // 监听反馈信息中的‘清空’按钮
            'contacttrackcontactfeedbacklist button[id=feedBackClear]': {
                click: this.clear
            },
            // 监听反馈信息中的‘取消’按钮
            'contacttrackcontactfeedbacklist button[action=doCancel]': {
                click: this.doCancel
            },
            // 监听列表页面的‘模糊检索’或'高级查询'按钮
            'contacttrackcontactlist button[action=queryBtn]': {
                click: this.contactSearch
            },
            // 监听列表页面的‘高级查询’按钮
            'contacttrackcontactlist button[action=expertSearchButton]': {
                click: this.openOrCloseExpertSearch
            },
            // 监听列表页面的高级搜索的下拉框
            'contacttrackcontactlist combobox': {
                select: this.comboChangeSelect
            },
//            // 监听添加页面的客户选择下拉框
//            'contactinfoadd combobox[name=customerID]': {
//                select: this.customerchange
//            },
//            // 监听机会事件选择下拉框
//            'contacttrackcontactfeedbacklist combobox[id=chanceevent]': {
//                select: this.comboAllStatus
//            },
//            // 监听机会事件选择下拉框选择
//            'contacttrackcontactfeedbacklist combobox[id=chanceevent]': {
//                focus: this.comboStatusSelect
//            },
//            'contactinfoadd combobox[id=oppositeContact]': {
//                focus: this.addContact
//            },
//            'contactinfoadd combobox[id=customer]': {
//                focus: this.addCustomer
//            },
            'contactinfoadd textfield': {
                blur: utils.trimSpace
            },
            'contacttrackcontactlist textfield': {
                blur: utils.trimSpace
            },
            'contacttrackcontactfeedbacklist datetimefield': {
                blur: utils.trimSpace
            }
        });
    },

    viewInit: function(treeId, panel, record) {
        var contactTrackBody = Ext.getCmp('contacttrackbody');
        if (typeof (contactTrackBody) === 'undefined') {
            contactTrackBody = Ext.widget('contacttrackbody');
        }
        if (typeof panel !== 'undefined') {
            panel.insert(0, contactTrackBody);
        } else {
            Ext.getCmp('centercard').insert(3, contactTrackBody);
            Ext.getCmp('centercard').getLayout().setActiveItem(contactTrackBody);
        }
        var contactTrackList = Ext.getCmp('contacttrackcontactlist');
        utils.authorizationControl(treeId, contactTrackList);
        contactTrackList.down('#searchText').reset();
        Ext.getCmp('contactTrackPanel').getForm().reset();
        var btn = Ext.getCmp('expertSearchButton');
        btn.setText('高级检索');
        contactTrackList.down('#searchText').setDisabled(false);
        Ext.getCmp('searchButton').setDisabled(false);
        Ext.getCmp('contactTrackPanel').hide();
        btn.up('grid').update();
        if (contactTrackList.contactWayStore.getCount() == 0) {
            contactTrackList.contactWayStore.load({
                params: {
                    code: '00060002'
                }
            });
        }
        if (contactTrackList.contactTypeStore.getCount() == 0) {
            contactTrackList.contactTypeStore.load({
                params: {
                    code: '00060001'
                }
            });
        }
        this.record = record;
        var customerID = record.get('customerID');
        var store = contactTrackList.getStore();
        store.on('beforeload', function(store, options) {
            var new_params = {
                    customerID: customerID
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        if (contactTrackList.cutomerContactStore.getCount() == 0) {
            contactTrackList.cutomerContactStore.load({
                params: {
                    customerID: customerID
                }
            });
        }
        utils.loadPageOne(store);
        return contactTrackBody;
    },

    // 列表页面的客户反馈方法
    contactSaveHistory: function(button) {
        var feedBack = Ext.widget('contacttrackcontactfeedbacklist');
        Ext.getCmp('feedBackClear').setText('清空');
        var list = Ext.getCmp('contacttrackcontactlist');
        var records = list.getView().getSelectionModel().getSelection()[0];
        feedBack.setTitle(Ext.String.htmlEncode(records.get('customerName')) + "-反馈信息");
        feedBack.down('form').getForm().loadRecord(records);
    },

    // 机会类型复选框
    chanceTypeSelect: function() {
        var findChance = Ext.getCmp('findchance');
        var checkChance = Ext.getCmp('checkchance');
        Ext.getCmp('findchancecontent').setVisible(findChance.checked);
        Ext.getCmp('findchancecontent').setDisabled(!findChance.checked);
        Ext.getCmp('checkchancecontent').setVisible(checkChance.checked);
        Ext.getCmp('checkchancecontent').setDisabled(!checkChance.checked);
        Ext.getCmp('chancename').setVisible(findChance.checked);
        Ext.getCmp('chancename').setDisabled(!findChance.checked);
        Ext.getCmp('chanceevent').setVisible(checkChance.checked && !findChance.checked);
        Ext.getCmp('chanceevent').setDisabled(!checkChance.checked || findChance.checked);
        Ext.getCmp('checkresult').setVisible(checkChance.checked);
        Ext.getCmp('checkresult').setDisabled(!checkChance.checked);
    },
//    eventID: 0,
//    // 机会事件
//    comboAllStatus: function(combo, index) {
//        combo.up('window').chanceEventStoreByStatus.removeAll();
//        Ext.getCmp('chanceevent').reset();
//    },
//    // 机会事件筛选
//    comboStatusSelect: function(combo, eOpts) {
//        var me = this;
//        var list = Ext.getCmp('contacttrackcontactlist');
//        if (typeof (list) == 'undefined') {
//            list = Ext.widget('contacttrackcontactlist');
//        }
//        var selection = list.getView().getSelectionModel().getSelection()[0];
//        combo.up('window').chanceEventStoreByStatus.removeAll();
//        combo.up('window').chanceEventStore.each(function(record) {
//            if (record.get('status') == 1 && selection.get('customerID') == record.get('customerID')
//                    && !record.get('isAbolished')) {
//                combo.up('window').chanceEventStoreByStatus.add(record);
//                me.eventID = record.get('eventID');
//            }
//        });
//        if (combo.up('window').chanceEventStoreByStatus.getCount() == 0) {
//            messageBox.alert("提示", "该客户没有已发现机会记录，请先添加为机会！");
//            Ext.getCmp('chanceevent').setReadOnly(true);
//            Ext.getCmp('chanceevent').setHideTrigger(false);
//        } else {
//            Ext.getCmp('chanceevent').setReadOnly(false);
//        }
//    },

    // 添加编辑信息的保存方法以及客户反馈页面的保存按钮
    saveAdd: function(button) {
        var me = this;
        var submitFlag = 0;
//        var findChance = Ext.getCmp('findchance');
//        var checkChance = Ext.getCmp('checkchance');

        if (button.id == 'infoadd') {
            submitFlag = 1;
        } else if (Ext.getCmp('notContantReason').isDisabled()) {
            submitFlag = 2;
        } else {
            submitFlag = 3;
        }
        var win = button.up('window');
        var form = win.down('form');
        function setVal(fieldId, val) {
            if (fieldId != 'validate') {
//                if (fieldId == "chancename") {
//                    form.down('[id=' + fieldId + ']').markInvalid(val);
//                } else {
                    form.down('[name=' + fieldId + ']').markInvalid(val);
//                }
            }
        }

        if (form.getForm().isValid()) {
            var form = button.up('window').down('form');
//            var createTimeField = form.down('hiddenfield[name=createTime]');
//            var updateTimeField = form.down('hiddenfield[name=updateTime]');
//
//            var now = Ext.util.Format.date(new Date(), 'Y-m-d H:i:s');
//            // create contact : set updateTime to blank
//            if (form.down('hiddenfield[name=contactID]').getValue() === '') {
//                createTimeField.setValue(now);
//                updateTimeField.setValue('');
//            } else {
//                // update contact
//                updateTimeField.setValue(now);
//            }

            var values = form.getValues();

//            if (submitFlag == 2) {
//                if (findChance.checked && checkChance.checked) {
//                    values.chanceType = 3;
//                }
//            }
            var record = form.getRecord();
            var list = Ext.getCmp("contacttrackcontactlist") || '';
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

    // 列表页面添加按钮方法
    addContactWin: function() {
        var view = Ext.widget('contactinfoadd');
//        view.down('#oppositeContact').forceSelection = true;
//        view.down('#oppositeContact').setReadOnly(true);
        view.setTitle('添加客户联系信息');
        Ext.getCmp('clearInfo').setText('清空');

        var customerID = this.record.get('customerID');
        view.down('#customerID').setValue(customerID);
        var customerName = this.record.get('customerName');
        view.down('#customerName').setValue(customerName);
    },
//    addCustomer: function() {
//        if (Ext.getCmp('customer').store.count() == 0) {
//            messageBox.alert("提示", "无客户，请先添加客户！");
//            Ext.getCmp('customer').setReadOnly(true);
//        } else {
//            Ext.getCmp('customer').setReadOnly(false);
//            Ext.getCmp('oppositeContact').forceSelection = false;
//        }
//    },
//    // 添加页面的客户选择下拉框
//    customerchange: function(combo, record, index) {
//        combo.up('window').cutomerContactStoreByCustomer.removeAll();
//        combo.up('window').down('#oppositeContact').reset();
//        Ext.getCmp('oppositeContact').setReadOnly(false);
//    },
//    addContact: function(combo, eOpts) {
//        combo.store.removeAll();
//        if (combo.up('window').down('#customer_ID').value == null) {
//            messageBox.alert("提示", "请先选择客户！");
//            return;
//        } else {
//            combo.up('window').cutomerContactStore.each(function(record) {
//                if (record.get('customerID') == combo.up('window').down('#customer_ID').getValue()) {
//                    combo.up('window').cutomerContactStoreByCustomer.add(record);
//                }
//            });
//        }
//        if (combo.store.count() == 0) {
//            Ext.getCmp('oppositeContact').setReadOnly(true);
//            messageBox.alert("提示", "该客户没有联系人，请追加联系人！");
//        }
//    },

    // 添加、编辑以及客户反馈页面的清空或重置按钮方法
    clear: function(button) {
        var win = button.up('window');
        if (button.getText() == '清空') {
            setTimeout(function fn() {
                win.down('form').getForm().reset();
            }, 100);
        } else {
            var view = Ext.getCmp('contactinfoadd');
//            view.cutomerContactStoreByCustomer.load();
            var list = Ext.getCmp('contacttrackcontactlist');
            var selection = list.getView().getSelectionModel().getSelection()[0];
            win.down('form').getForm().loadRecord(selection);
        }
    },

    // 客户反馈页面中的‘取消’按钮方法
    doCancel: function(button) {
        button.up('window').close();
    },

    selectAddr: function(combobox) {
        var field = Ext.getCmp('contactAddr');
        field.setVisible(true);
    },

    // 列表页面‘编辑’按钮响应方法
    editContact: function(grid) {
        if (!Ext.getCmp('contactEdit').isHidden()) {
            var view = Ext.widget('contactinfoadd');
            view.setTitle('编辑客户联系信息');
            var list = Ext.getCmp('contacttrackcontactlist');
            var record = list.getView().getSelectionModel().getSelection()[0];
//            view.cutomerContactStoreByCustomer.load();
            view.down('form').getForm().loadRecord(record);
        }
    },

    // 客户联系跟踪列表页面的按钮是否可用的方法
    selectionchange: function(sm, selections) {
        if (selections.length != 1) {
            Ext.getCmp('contacttrackcontactdetail').hide();
        } else {
            Ext.getCmp('contacttrackcontactdetail').show();
        }
        Ext.getCmp('contactEdit').setDisabled(selections.length != 1);
        Ext.getCmp('contactDelete').setDisabled(selections.length == 0);
        Ext.getCmp('contactSaveHistory').setDisabled(selections.length != 1);
    },

    // 显示详细详细
    showContactDetail: function(grid, record) {
        Ext.getCmp('contacttrackcontactdetail').getForm().loadRecord(record);
    },

    // 列表页面的删除方法
    contactDelete: function(button) {
        var grid = button.up('gridpanel');
        utils.delRecords(grid, 'deleteContactTrackAction.action', 'contactID');
    },

    // 客户反馈中的是否联系单选按钮方法
    isOrNotContact: function(button) {
        var win = button.up('window');
        var ifContact = Ext.getCmp('ifContact1');
        var value = ifContact.items.items[0].getValue();
        var realityDateBegin = Ext.getCmp('realityDateBegin');
        var realityDateEnd = Ext.getCmp('realityDateEnd');
        var userFeedbackInformation = Ext.getCmp('userFeedbackInformation');
        var strategy = Ext.getCmp('strategy');
//        var chanceType = Ext.getCmp('chancetype');
        var notContantReason = Ext.getCmp('notContantReason');
        var list = Ext.getCmp('contacttrackcontactlist');
        if (typeof (list) == 'undefined') {
            list = Ext.widget('contacttrackcontactlist');
        }
        var record1 = list.getView().getSelectionModel().getSelection()[0];
        win.down('form').getForm().loadRecord(record1);
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
    },

    // 列表页面中的检索方法
    contactSearch: function(button) {
        utils.query(button);
    },
    comboChangeSelect: function(combo, records, eOpts) {
        utils.comboChangeSelect(combo, records, eOpts);
    },
    // 客户联系列表页面的高级检索按钮方法
    openOrCloseExpertSearch: function(button) {
        var gridPanel = button.up('gridpanel');
        var superQueryPanel = gridPanel.down('form');
        if (superQueryPanel.isHidden()) {
            if (gridPanel.contactWaySearchStore.getCount() == 0) {
                utils.searchStoreInset(gridPanel.contactWaySearchStore, gridPanel.contactWayStore, Ext.getCmp('contactWaySearch'));
            }
            if (gridPanel.contactTypeSearchStore.getCount() == 0) {
                utils.searchStoreInset(gridPanel.contactTypeSearchStore, gridPanel.contactTypeStore, Ext.getCmp('contactTypeSearch'));
            }
        }
        utils.openOrCloseSuperQueryBtn(button);
    }
});