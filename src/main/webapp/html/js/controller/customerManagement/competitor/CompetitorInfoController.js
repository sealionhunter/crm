Ext.define('CRM.controller.customerManagement.competitor.CompetitorInfoController', {
    extend: 'Ext.app.Controller',
    views: [ 'customerManagement.competitor.CompetitorInfoList', 'customerManagement.competitor.CompetitorInfoDetail',
            'customerManagement.competitor.ShowCprAnalysisWin', 'customerManagement.competitor.CompetitorUpdate',
            'customerManagement.competitor.CompetitorInfoBody' ],
    models: [ 'customerManagement.competitor.CompetitorInfoModel', 'customerManagement.competitor.CprAnalysis',
            'customerManagement.competitor.ComboBoxModel' ],
    stores: [ 'customerManagement.competitor.CompetitorInfoNow', 'customerManagement.competitor.CompetitorInfoNew',
            'customerManagement.competitor.CprAnalysisStore' ],
    init: function() {
        this.control({
            'competitorInfoList': {
                tabchange: this.loadStore
            },
            'competitorInfoList gridpanel[name=competitorInfoNow]': {
                select: this.showDetail,
                itemdblclick: this.updateCompetitorexisted,
                selectionchange: this.existedSelectionChange
            },
            'competitorInfoList textfield': {
                blur: utils.trimSpace
            },
            'competitorInfoList gridpanel[name=competitorInfoNew]': {
                select: this.showDetail,
                itemdblclick: this.updateCompetitorpotential,
                selectionchange: this.potentialSelectionChange
            },
            'competitorInfoList toolbar button[id=existedadd]': {
                click: this.addcompetitor
            },
            'competitorInfoList toolbar button[id=potentialadd]': {
                click: this.addcompetitor
            },
            'competitorInfoList combobox[name=competitorProperty]': {
                select: this.comboSelectListener
            },
            'competitorInfoList combobox[name=competitorMoney]': {
                select: this.comboSelectListener
            },
            'competitorInfoList combobox[name=competitorPeople]': {
                select: this.comboSelectListener
            },
            'competitorinfoedit toolbar button[action=add]': {
                click: this.add
            },
            'competitorinfoedit textfield': {
                blur: utils.trimSpace
            },
            'competitorinfoedit toolbar button[action=addreset]': {
                click: this.addreset
            },
            'competitorinfoedit toolbar button[action=cancel]': {
                click: this.cancel
            },
            'competitorInfoList button[id=existedmodify]': {
                click: this.updateCompetitorexisted
            },
            'competitorInfoList button[id=potentialmodify]': {
                click: this.updateCompetitorpotential
            },
            'competitorInfoList button[name=query]': {
                click: this.competitorQuery
            },
            'competitorInfoList gridpanel[name=competitorInfoNew] button[name=superqueryButton]': {
                click: this.openOrCloseSuperQueryBtn
            },
            'competitorInfoList gridpanel[name=competitorInfoNow] button[name=superqueryButton]': {
                click: this.openOrCloseSuperQueryBtn
            },
            'competitorInfoList button[name=superquery]': {
                click: this.competitorQuery
            },
            'competitorInfoList button[id=existeddelete]': {
                click: this.deleteCompetitor
            },
            'competitorInfoList button[id=potentialdelete]': {
                click: this.deleteCompetitor
            },
            'competitorInfoList button[action=showCprAnalysisWin]': {
                click: this.showCprAnalysisController
            }
        });
    },
    loadStore: function(tabPanel, newCard, oldCard) {
        if (typeof (Ext.getCmp('CompetitorInfoDetail')) != 'undefined') {
            Ext.getCmp('CompetitorInfoDetail').hide();
        }
        var grid = newCard.down('grid');
        var gridId = grid.getId();
        var gridIndex = 1311;
        var competitorType = '000700040001';
        if (gridId == 'competitorInfoNew') {
            competitorType = '000700040002';
            gridIndex = 1312;
        }
        var toolbar = grid.down('toolbar');
        utils.authorizationControl(gridIndex, grid);
        var panel = grid.down('form');
        if (panel.isHidden()) {
            toolbar.down('textfield').reset();
        } else {
            toolbar.down('button[name="query"]').setDisabled(false);
            toolbar.down('textfield[name="competitorSearchText"]').setDisabled(false);
            panel.getForm().reset();
            panel.hide();
            var button = toolbar.down('button[name="superqueryButton"]');
            button.setText('高级检索');
            button.up('gridpanel').update();
        }
        var store = grid.getStore();
        store.on('beforeload', function(store, options) {
            var new_params = {
                'competitorType': competitorType,
                searchFlag: 0
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.loadPage(1);
    },
    viewInit: function(treeID) {
        if (typeof (Ext.getCmp('CompetitorInfoDetail')) != 'undefined') {
            Ext.getCmp('CompetitorInfoDetail').hide();
        }
        var competitorinfobody = Ext.getCmp('competitorinfobody');
        if (typeof (competitorinfobody) == 'undefined') {
            competitorinfobody = Ext.widget('competitorinfobody');
            Ext.getCmp('centercard').insert(1, competitorinfobody);
        }
        var competitorInfoNow = Ext.getCmp('competitorInfoNow');
        Ext.getCmp('centercard').getLayout().setActiveItem('competitorinfobody');
        var formExist = Ext.getCmp('existedSuperForm');
        var toolbarExist = Ext.getCmp('existedToolbar');
        if (formExist.isHidden()) {
            toolbarExist.down('textfield[name="competitorSearchText"]').reset();
        } else {
            toolbarExist.down('button[name="query"]').setDisabled(false);
            toolbarExist.down('textfield[name="competitorSearchText"]').setDisabled(false);
            formExist.getForm().reset();
            formExist.hide();
            var button = toolbarExist.down('button[name="superqueryButton"]');
            button.setText('高级检索');
            button.up('gridpanel').update();
        }
        Ext.getCmp('competitorInfo').setActiveTab(0);
        var competitorNowStore = competitorInfoNow.getStore();
        competitorNowStore.on('beforeload', function(store, options) {
            var new_params = {
                'competitorType': '000700040001',
                searchFlag: 0
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        utils.authorizationControl(1311, competitorInfoNow);
        competitorNowStore.loadPage(1);
        return competitorinfobody;
    },
    addcompetitor: function() {
        var win = Ext.create('CRM.view.customerManagement.competitor.CompetitorUpdate');
        win.staffStore.load({
            params: {
                code: '00070002'
            },
            callback: function(records, operation, success) {
                win.staffStore.insert(0, {
                    value: '-无-',
                    name: '0'
                });
            }
        });
        win.setTitle('添加竞争对手基本信息');
    },
    updateCompetitorexisted: function(button) {
        if (!Ext.getCmp('existedmodify').isHidden()) {
            var win = Ext.create('CRM.view.customerManagement.competitor.CompetitorUpdate');
            win.staffStore.load({
                params: {
                    code: '00070002'
                },
                callback: function(records, operation, success) {
                    win.staffStore.insert(0, {
                        value: '-无-',
                        name: '0'
                    });
                }
            });
            win.setTitle('编辑竞争对手基本信息');
            var grid = button.up('grid');
            var record = grid.getSelectionModel().getSelection();
            win.down('form').loadRecord(record[0]);
            win.down('button[action=addreset]').setText('重置');
        }
    },
    updateCompetitorpotential: function(button) {
        if (!Ext.getCmp('potentialmodify').isHidden()) {
            var win = Ext.create('CRM.view.customerManagement.competitor.CompetitorUpdate');
            win.staffStore.load({
                params: {
                    code: '00070002'
                },
                callback: function(records, operation, success) {
                    win.staffStore.insert(0, {
                        value: '-无-',
                        name: '0'
                    });
                }
            });
            win.setTitle('编辑竞争对手基本信息');
            var grid = button.up('grid');
            var record = grid.getSelectionModel().getSelection();
            win.down('form').loadRecord(record[0]);
            win.down('button[action=addreset]').setText('重置');
        }
    },
    existedSelectionChange: function(sm, selections) {
        Ext.getCmp('existeddelete').setDisabled(selections.length == 0);
        Ext.getCmp('existedmodify').setDisabled(selections.length != 1);
        Ext.getCmp('showCprAnalysisWin').setDisabled(selections.length != 1);
        if (selections.length == 1) {
            Ext.getCmp('CompetitorInfoDetail').show();
        } else {
            Ext.getCmp('CompetitorInfoDetail').hide();
        }
    },
    potentialSelectionChange: function(sm, selections) {
        Ext.getCmp('potentialdelete').setDisabled(selections.length == 0);
        Ext.getCmp('potentialmodify').setDisabled(selections.length != 1);
        Ext.getCmp('showHiddenCprAnalysisWin').setDisabled(selections.length != 1);
        if (selections.length == 1) {
            Ext.getCmp('CompetitorInfoDetail').show();
        } else {
            Ext.getCmp('CompetitorInfoDetail').hide();
        }
    },
    comboSelectListener: function(combo, records, eOpts) {
        if (records[0].get('name') == '00' && records.length > 1) {
            combo.setValue(records[1].get('name'));
        } else {
            Ext.Array.each(records, function(record) {
                if (record.get('name') == '00') {
                    combo.setValue('00');
                    return false;
                }
            });
        }
    },
    add: function(button) {
        var addwin = Ext.getCmp('competitorinfoedit');
        var form = Ext.getCmp('addForm');
        var values = form.getValues();
        var record = form.getRecord() || '';
        function setVal(fieldId, val) {
            if (fieldId != null && fieldId != 'validate') {
                form.down('[name=' + fieldId + ']').markInvalid(val);
            }
        }
        if (form.getForm().isValid()) {
            Ext.Ajax.request({
                url: 'updateCompetitorinfo.action',
                params: {
                    record: Ext.encode(values)
                },
                success: function(response) {
                    if (CRM.checkResponse(response)) {
                        return;
                    }
                    var responseText = Ext.decode(response.responseText);
                    var validate = responseText.validate || '';
                    if (responseText.validate == false) {
                        Ext.iterate(responseText, setVal);
                        messageBox.alert('提示', '输入信息有误，请检查输入信息！');
                    } else {
                        if (record == '') {
                            Ext.crm.msg("添加成功", "");
                        } else {
                            Ext.crm.msg("编辑成功", "");
                        }
                        Ext.getCmp('competitorInfoNow').store.on('beforeload', function(store) {
                            var param = {
                                competitorType: '000700040001'
                            };
                            Ext.apply(store.proxy.extraParams, param);
                        });
                        Ext.getCmp('competitorInfoNow').store.load();

                        Ext.getCmp('competitorInfoNew').store.on('beforeload', function(store) {
                            var param = {
                                competitorType: '000700040002'
                            };
                            Ext.apply(store.proxy.extraParams, param);
                        });
                        Ext.getCmp('competitorInfoNew').store.load();
                        form.getForm().reset();
                        addwin.close();
                    }
                },
                failure: function(response) {
                    if (record == '') {
                        messageBox.alert("错误提示", "添加失败，请联系管理员");
                    } else {
                        messageBox.alert("错误提示", "编辑失败，请联系管理员");
                    }
                }
            });
        } else {
            messageBox.alert('提示', '输入信息有误，请检查输入信息！');
        }
    },
    cancel: function(button) {
        var win = button.up('window');
        win.close();
    },
    addreset: function(button) {
        var form = Ext.getCmp('addForm');
        if (form.down('#competitorInfoId').getValue() == '') {
            form.getForm().reset();
        } else {
            var record = form.getRecord();
            if (record.get('competitorType') == "000700040001") {
                var selection = Ext.getCmp('competitorInfoNow').getView().getSelectionModel().getSelection()[0];
                form.loadRecord(selection);
                Ext.getCmp('competitorType').setValue('000700040001');
            } else {
                var selection = Ext.getCmp('competitorInfoNew').getView().getSelectionModel().getSelection()[0];
                form.loadRecord(selection);
                Ext.getCmp('competitorType').setValue('000700040002');
            }
            if (record.get('competitorPeople') == '') {
                Ext.getCmp('competitorPeople').setValue(null);
            }
        }
    },
    competitorQuery: function(button) {
        if (button.up('grid').down('form').isHidden()
                && button.up('toolbar').down('textfield[name="competitorSearchText"]').isValid()
                || !button.up('grid').down('form').isHidden() && button.up('form').getForm().isValid()) {
            var jsonString = "";
            var store = button.up('grid').getStore();
            var searchFlag = 1;
            var gridPanel = button.up('gridpanel');
            var form = gridPanel.down('form');
            if (!form.isHidden()) {
                searchFlag = 2;
                var values = form.getValues();
                var competitorPeople = values.competitorPeople;
                var competitorPeoples = new Array();
                competitorPeoples.push(competitorPeople);
                values.competitorPeople = competitorPeoples;
                var competitorProperty = values.competitorProperty;
                var competitorPropertys = new Array();
                competitorPropertys.push(competitorProperty);
                values.competitorProperty = competitorPropertys;
                var competitorMoney = values.competitorMoney;
                var competitorMoneys = new Array();
                competitorMoneys.push(competitorMoney);
                values.competitorMoney = competitorMoneys;
                jsonString = Ext.encode(values);
            } else {
                searchFlag = 1;
                var competitorSearchText = button.up('grid').down('textfield[name=competitorSearchText]').getValue();
                jsonString = Ext.decode('{competitorSearchText : ' + Ext.encode(competitorSearchText) + '}');
                jsonString = Ext.encode(jsonString);
            }
            store.on('beforeload', function(store, options) {
                var new_params = {
                    searchFlag: searchFlag,
                    jsonString: jsonString
                };
                Ext.apply(store.proxy.extraParams, new_params);
            });
            store.loadPage(1, {
                callback: function(obj, records, success) {
                    if (success) {
                        var msg = Ext.decode(records.response.responseText);
                        if (msg.validate == "false") {
                            if (msg.trimSpaceError) {
                                messageBox.alert("检索错误", msg.trimSpaceError);
                            } else {
                                utils.checkErrorMsg(msg.competitorPeople, peopleId);
                                utils.checkErrorMsg(msg.competitorProperty, propertyId);
                                utils.checkErrorMsg(msg.competitorMoney, MoneyId);
                            }
                        } else {
                            if (store.getTotalCount() == 0) {
                                messageBox.alert("提示", "未检索到符合条件的数据！");
                            }
                        }
                    } else {
                        messageBox.alert("检索错误", "发生数据库异常！");
                    }
                },
                params: {
                    start: 0,
                    page: 1
                }
            });
            store.currentPage = 1;
        } else {
            messageBox.alert("提示", "您搜索的值不符合规范，请重新输入！");
        }
    },
    openOrCloseSuperQueryBtn: function(button) {
        var tabPanel = button.up('tabpanel');
        var toolBar = button.up('toolbar');
        var gridPanel = toolBar.up('gridpanel');
        var toolForm = gridPanel.down('form');
        if (tabPanel.querymoneyStore.getCount() == 0) {
            utils.searchStoreInset(tabPanel.querymoneyStore, tabPanel.moneyStore, toolForm
                    .down('combobox[name="competitorMoney"]'));
        } else {
            toolForm.down('combobox[name="competitorMoney"]').setValue('00');
            toolForm.down('combobox[name="competitorPeople"]').setValue('00');
            toolForm.down('combobox[name="competitorProperty"]').setValue('00');
        }
        if (tabPanel.querystaffStore.getCount() == 0) {
            utils.searchStoreInset(tabPanel.querystaffStore, tabPanel.staffStore, toolForm
                    .down('combobox[name="competitorPeople"]'));
        }
        if (tabPanel.querypropertyStore.getCount() == 0) {
            utils.searchStoreInset(tabPanel.querypropertyStore, tabPanel.propertyStore, toolForm
                    .down('combobox[name="competitorProperty"]'));
        }
        if (toolForm.isHidden()) {
            toolBar.down('button[name="query"]').setDisabled(true);
            toolBar.down('textfield[name="competitorSearchText"]').setDisabled(true);
            toolBar.down('textfield[name="competitorSearchText"]').reset();
            toolForm.show();
            button.setText('关闭检索');
            gridPanel.update();
        } else {
            toolBar.down('button[name="query"]').setDisabled(false);
            toolBar.down('textfield[name="competitorSearchText"]').setDisabled(false);
            toolForm.getForm().reset();
            toolForm.hide();
            button.setText('高级检索');
            gridPanel.update();
        }
        var competitorNewStore = Ext.getCmp('competitorInfoNew').getStore();
        var competitorNowStore = Ext.getCmp('competitorInfoNow').getStore();
        competitorNewStore.on('beforeload', function(store, options) {
            var new_params = {
                'competitorType': '000700040002',
                searchFlag: 0
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        competitorNowStore.on('beforeload', function(store, options) {
            var new_params = {
                'competitorType': '000700040001',
                searchFlag: 0
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        competitorNewStore.loadPage(1);
        competitorNowStore.loadPage(1);
        return competitorinfobody;
    },
    deleteCompetitor: function(button) {
        var store = button.up("panel").getStore();
        var checkRecord = button.up("panel").getView().getSelectionModel().getSelection();
        var checkRecordIDs = new Array();
        for ( var i = 0; i < checkRecord.length; i++) {
            checkRecordIDs[i] = checkRecord[i].get('competitorInfoId');
        }
        if (checkRecord.length != 0) {
            box.confirm('提示', '确定删除所选信息？', function showResult(button) {
                if (button == 'yes') {
                    Ext.Ajax.request({
                        url: 'deleteCompetitorinfo.action',
                        params: {
                            competitorInfoIds: Ext.encode(checkRecordIDs)
                        },
                        success: function(response) {
                            if (CRM.checkResponse(response)) {
                                return;
                            }
                            var currentPage = store.currentPage;
                            var pageSize = store.pageSize;
                            var total = store.totalCount - checkRecord.length;
                            if (total <= (currentPage - 1) * pageSize) {
                                currentPage = currentPage - 1;
                            }
                            if (total == 0) {
                                currentPage = 1;
                            }
                            Ext.crm.msg("删除成功！", "");
                            store.loadPage(currentPage);
                        },
                        failure: function(response) {
                            messageBox.alert('提示', '删除失败，请联系管理员！');
                        }
                    });
                }
            });
        }
    },
    showDetail: function(grid, record) {
        Ext.getCmp('CompetitorInfoDetail').loadRecord(record);
    },
    showCprAnalysisController: function(button) {
        initController('customerManagement.competitor.CprAnalysis').showCprAnalysisWin(button);
    }
});