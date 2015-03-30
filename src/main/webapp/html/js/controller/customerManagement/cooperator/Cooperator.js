Ext.define('CRM.controller.customerManagement.cooperator.Cooperator', {
    extend: 'Ext.app.Controller',
    views: [ 'customerManagement.cooperator.CooperatorDetail', 'customerManagement.cooperator.CooperatorList',
            'customerManagement.cooperator.CooperatorUpdate', 'customerManagement.cooperator.CooperatorBody',
            'customerManagement.cooperator.CooperationProjectList', 'customerManagement.cooperator.CooperatorAnalysisDetail',
            'customerManagement.cooperator.CooperatorAnalysisList', 'customerManagement.cooperator.CooperatorAnalysisUpdate',
            'customerManagement.cooperator.CooperationProjectUpdate', 'customerManagement.cooperator.CooperatorAnalysisShowWin',
            'customerManagement.cooperator.CooperatorAnalysisShowList', 'customerManagement.cooperator.CooperatorAnalysisCenter' ],
    stores: [ 'customerManagement.cooperator.Cooperator', 'customerManagement.cooperator.ProjectItem',
            'customerManagement.cooperator.CooperatorAnalysisStore', 'customerManagement.cooperator.CooperatorNameCombo',
            'customerManagement.cooperator.CopAnalysisShowStore' ],
    init: function() {
        this.control({
            /* cooperatorList listener */
            'cooperatorlist': {
                select: this.changeCooperatorDetail,
                itemdblclick: this.showCooperatorEditForm,
                selectionchange: this.activeCooperatorListButton,
                scrollershow: utils.scrollershow
            },
            'cooperatorlist button[action=queryBtn]': {
                click: this.searchCooperator
            },
            'cooperatorlist  button[action=showCooperatorAdvancedSearch]': {
                click: this.showCooperatorAdvancedSearchForm
            },
            'cooperatorlist button[action=advancedSearch]': {
                click: this.searchCooperator
            },
            'cooperatorlist button[action=showCopAnalysisBtn]': {
                click: this.showCooperatorAnalysis
            },
            'cooperatorlist button[action=contactSelect]': {
                click: this.contactSelect
            },
            'cooperatorlist  button[action=showCooperationProject]': {
                click: this.showCooperationProject
            },
            'cooperatorlist button[action=createCooperator]': {
                click: this.createCooperator
            },
            'cooperatorlist button[action=editCooperator]': {
                click: this.editCooperator
            },
            'cooperatorlist button[action=delCooperator]': {
                click: this.delCooperator
            },
            'cooperatorlist textfield': {
                blur: utils.trimSpace
            },
            'cooperatorlist combobox': {
                select: utils.comboChangeSelect
            },
            /* cooperatorUpdateForm listener */
            'cooperatorupdate  button[action=save]': {
                click: this.saveCooperator
            },
            'cooperatorupdate  button[action=reset]': {
                click: utils.resetRecord
            },
            'cooperatorupdate  button[action=cancel]': {
                click: utils.winClose
            },
            'cooperatorupdate  textfield': {
                blur: utils.trimSpace
            },
            'cooperatorupdate  textarea': {
                blur: utils.trimSpace
            },
            /* cooperationProjectList listener */
            'cooperationprojectlist > gridpanel': {
                selectionchange: this.activeCooperationProjectListButton,
                itemdblclick: this.showProjectEditForm,
                scrollershow: utils.scrollershow
            },
            'cooperationprojectlist button[action=queryBtn]': {
                click: this.searchProject
            },
            'cooperationprojectlist button[action=showAdvancedSearchForm]': {
                click: this.showProjectAdvancedSearchForm
            },
            'cooperationprojectlist button[action=advancedSearch]': {
                click: this.searchProject
            },
            'cooperationprojectlist button[action=createProject]': {
                click: this.createProject
            },
            'cooperationprojectlist button[action=editProject]': {
                click: this.editProject
            },
            'cooperationprojectlist button[action=delProject]': {
                click: this.delProject
            },
            'cooperationprojectlist textfield': {
                blur: utils.trimSpace
            },
            'cooperationprojectlist combobox': {
                select: utils.comboChangeSelect
            },
            /* cooperationProjectUpdateForm listener */
            'cooperationprojectupdate button[action=save]': {
                click: this.saveProject
            },
            'cooperationprojectupdate button[action=reset]': {
                click: this.resetProjectRecord
            },
            'cooperationprojectupdate button[action=cancel]': {
                click: utils.winClose
            },
            'cooperationprojectupdate textfield': {
                blur: utils.trimSpace
            },
            'cooperationprojectupdate textarea': {
                blur: utils.trimSpace
            },

            /* cooperatorAnalysis listener */
            'cooperatoranalysislist': {
                itemdblclick: this.dblClickShowEditWin,
                selectionchange: this.activeAnalysisButton,
                scrollershow: utils.scrollershow
            },
            'cooperatoranalysisupdate button[action=addOrEditSave]': {
                click: this.addOrUpdateCopAnalysis
            },
            'cooperatoranalysisupdate button[action=addOrEditReset]': {
                click: this.resetRecord
            },
            'cooperatoranalysisupdate button[action=addOrEditCancel]': {
                click: utils.winClose
            },
            'cooperatoranalysisupdate textarea': {
                blur: utils.trimSpace
            },
            'cooperatoranalysislist button[action=copAnalysisAddBtn]': {
                click: this.showAnalysisAddWin
            },
            'cooperatoranalysislist button[action=copAnalysisEditBtn]': {
                click: this.showAnalysisEditWin
            },
            'cooperatoranalysislist button[action=copAnalysisDelBtn]': {
                click: this.delCopAnalysis
            },
            'cooperatoranalysisshowlist': {
                itemclick: this.detailCopAnalysisById
            }
        });
    },

    /* cooperatorList listener executive methods */
    viewInit: function(flag, treeId) {
        var centerCard = Ext.getCmp('centercard');
        if (flag == 'cooperator') {
            var cooperatorbody = Ext.getCmp('cooperatorbody');
            if (typeof (cooperatorbody) == 'undefined') {
                cooperatorbody = Ext.widget('cooperatorbody');
                centerCard.insert(7, cooperatorbody);
            }
            var queryTbar = Ext.getCmp('cooperatorQueryTbar');
            var panel = Ext.getCmp('cooperatorAdvancedSearchForm');
            if (panel.isHidden()) {
                queryTbar.down('#searchText').reset();
            } else {
                queryTbar.down('#queryBtn').setDisabled(false);
                queryTbar.down('#searchText').setDisabled(false);
                panel.getForm().reset();
                panel.hide();
                var button = queryTbar.down('#showCooperatorAdvancedSearch');
                button.setText('高级检索');
                button.up('gridpanel').update();
            }
            var cooperatorList = Ext.getCmp('cooperatorList');
            utils.authorizationControl(treeId, cooperatorList);
            centerCard.getLayout().setActiveItem('cooperatorbody');
            var store = Ext.getCmp('cooperatorList').getStore();
            utils.loadPageOne(store);

        } else if (flag == 'copAnalysis') {
            var center = Ext.getCmp('cooperatoranalysiscenter');
            if (typeof (center) == 'undefined') {
                center = Ext.widget('cooperatoranalysiscenter');
                centerCard.insert(8, center);
            }
            var copAnalysisList = Ext.getCmp('cooperatorAnalysisList');
            utils.authorizationControl(treeId, copAnalysisList);
            centerCard.getLayout().setActiveItem('cooperatoranalysiscenter');
            var store = copAnalysisList.getStore();
            store.loadPage(1);
        }
    },
    changeCooperatorDetail: function(grid, record) {
        Ext.getCmp('cooperatorDetail').loadRecord(record);
    },
    showCooperatorEditForm: function(grid, record) {
        if (!Ext.getCmp('editCooperator').isHidden()) {
            var view = Ext.widget('cooperatorupdate');
            view.setTitle('编辑');
            view.down('form').loadRecord(record);
        }
    },
    activeCooperatorListButton: function(sm, selections) {
        Ext.getCmp('editCooperator').setDisabled(selections.length != 1);
        Ext.getCmp('delCooperator').setDisabled(selections.length == 0);
        Ext.getCmp('cooperatorContactShow').setDisabled(selections.length != 1);
        Ext.getCmp('showCooperationProject').setDisabled(selections.length != 1);
        Ext.getCmp('analysisCooperator').setDisabled(selections.length != 1);
        if (selections.length != 1) {
            Ext.getCmp('cooperatorDetail').hide();
        } else {
            Ext.getCmp('cooperatorDetail').show();
        }
    },
    searchCooperator: function(button) {
        utils.query(button);
    },
    showCooperatorAdvancedSearchForm: function(button) {
        var searchForm = Ext.getCmp('cooperatorAdvancedSearchForm');
        var gridPanel = button.up('cooperatorlist');
        if (searchForm.isHidden()) {
            if (gridPanel.industrySearchStore.getCount() == 0) {
                utils.searchStoreInset(gridPanel.industrySearchStore, gridPanel.industryStore, searchForm.down('#industrySearch'));
            }
            if (gridPanel.scaleSearchStore.getCount() == 0) {
                utils.searchStoreInset(gridPanel.scaleSearchStore, gridPanel.scaleStore, searchForm.down('#scaleSearch'));
            }
        }
        utils.openOrCloseSuperQueryBtn(button);
    },
    showCooperationProject: function(button) {
        var gridpanel = button.up('gridpanel');
        var rows = gridpanel.getSelectionModel().getSelection();
        var cooperatorName = rows[0].get('cooperatorName');
        var cooperatorID = rows[0].get('cooperatorID');
        var view = Ext.widget('cooperationprojectlist');
        view.setTitle('合作履历表-' + Ext.String.htmlEncode(cooperatorName));
        Ext.getCmp('cooperatorIDSearch').setValue(cooperatorID);
        var store = view.down('gridpanel').getStore();
        var cooperatorID = Ext.decode("{cooperatorIDSearch : Ext.getCmp('cooperatorIDSearch').getValue()}");
        store.on('beforeload', function(store, options) {
            var new_params = {
                searchFlag: 0,
                jsonString: Ext.encode(cooperatorID)
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.loadPage(1);
    },
    createCooperator: function(button) {
        var view = Ext.widget('cooperatorupdate');
        view.setTitle('添加合作伙伴');
        var resetButton = view.down('button[action=reset]');
        resetButton.setText('清空');
    },
    editCooperator: function(button) {
        var partnerGrid = button.up('cooperatorlist');
        var record = partnerGrid.getSelectionModel().getSelection();
        var view = Ext.widget('cooperatorupdate');
        view.setTitle('编辑合作伙伴');
        view.down('button[action=reset]').setText('重置');
        view.down('form').loadRecord(record[0]);
    },
    delCooperator: function(button) {
        var grid = button.up("panel");
        utils.delRecords(grid, 'delCooperator.action', 'cooperatorID');
    },
    saveCooperator: function(button) {
        utils.updateRecord(button, 'updateCooperator.action', "cooperatorList");
    },

    /* cooperationProjectList listener executive methods */
    activeCooperationProjectListButton: function(sm, selections) {
        Ext.getCmp('editProject').setDisabled(selections.length != 1);
        Ext.getCmp('delProject').setDisabled(selections.length == 0);
    },
    showProjectEditForm: function(grid, record) {
        var view = Ext.widget('cooperationprojectupdate');
        view.setTitle('编辑合作履历');
        view.down('form').loadRecord(record);
    },
    searchProject: function(button) {
        utils.superQuery(button, "cooperatorIDSearch", Ext.getCmp('cooperatorIDSearch').getValue());
    },
    showProjectAdvancedSearchForm: function(button) {
        var searchForm = Ext.getCmp('projectAdvancedSearchForm');
        // var advancedBtn =
        // Ext.getCmp('showProjectAdvancedSearchForm');
        var gridPanel = button.up('cooperationprojectlist');
        if (searchForm.isHidden()) {
            if (gridPanel.typeSearchStore.getCount() == 0) {
                utils.searchStoreInset(gridPanel.typeSearchStore, gridPanel.typeStore, searchForm.down('#typeSearch'));
            }
        }
        utils.changeAdvancedSearchShowBtnType(button, "cooperatorIDSearch", Ext.getCmp("cooperatorIDSearch").getValue());
    },
    createProject: function(button) {
        var win = Ext.widget('cooperationprojectupdate');
        var cooperatorID;
        win.setTitle('添加合作履历');
        win.down('button[action=reset]').setText('清空');
        cooperatorID = Ext.getCmp('cooperatorIDSearch').getValue();
        win.down("form > hidden[name='cooperatorID']").setValue(cooperatorID);
    },
    editProject: function(button) {
        var win = Ext.widget('cooperationprojectupdate');
        win.setTitle('编辑合作履历');
        var partnerGrid = Ext.ComponentQuery.query('cooperationprojectlist >gridpanel');
        var record = partnerGrid[0].getSelectionModel().getSelection();
        win.down('form').loadRecord(record[0]);
    },
    delProject: function(button) {
        var grid = button.up('gridpanel');
        var store = grid.getStore();
        var checkRecord = grid.getSelectionModel().getSelection();
        var checkRecordIDs = new Array();
        for ( var i = 0; i < checkRecord.length; i++) {
            checkRecordIDs[i] = checkRecord[i].get('cooperationProjectID');
        }
        if (checkRecord.length != 0) {
            box.confirm('提示', '确定删除所选信息？', function showResult(button) {
                if (button == 'yes') {
                    Ext.Ajax.request({
                        url: 'deleteCooperationProject.action',
                        params: {
                            jsonString: checkRecordIDs
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
                            utils.loadPageOne(Ext.getCmp('cooperatorList').getStore());
                        },
                        failure: function(response) {
                            messageBox.alert('提示', '删除失败，请联系管理员');
                        }
                    });
                }
            });
        }
    },
    saveProject: function(button) {
        utils.updateRecord(button, 'updateCooperationProject.action', "cooperationprojectlist");
        var store = Ext.getCmp('cooperatorList').getStore();
        utils.loadPageOne(store);
    },
    resetProjectRecord: function(button) {
        var form = button.up('window').down('form');
        var record = form.getRecord() || '';
        if (record == '') {
            var cooperatorID = form.down('#cooperatorID').getValue();
            form.getForm().reset();
            form.down('#cooperatorID').setValue(cooperatorID);
        } else {
            form.loadRecord(record);
        }
    },
    // show cooperator abnalysis in right panel of window from
    // query database by id.
    detailCopAnalysisById: function(grid, record) {
        var win = Ext.getCmp('copAnalysisShowId');
        var form = win.down('#showInfoAnalysis');
        var eastPanel = win.down('#eastPanel');
        form.loadRecord(record);
        eastPanel.getLayout().setActiveItem(form);
    },

    activeAnalysisButton: function(sm, selections) {
        var list = Ext.getCmp('cooperatorAnalysisList');
        var editButton = list.down('button[name="edit"]');
        var delButton = list.down('button[name="delete"]');
        var detail = Ext.getCmp('cooperatorAnalysisDetail');
        editButton.setDisabled(selections.length != 1);
        delButton.setDisabled(selections.length == 0);
        if (selections.length != 1) {
            if (!detail.isHidden()) {
                detail.hide();
            }
        } else {
            var record = list.getSelectionModel().getSelection()[0];
            if (detail.isHidden()) {
                detail.show();
            }
            detail.loadRecord(record);
        }
    },

    // show cooperator analysis add utils.
    showAnalysisAddWin: function() {
        var win = Ext.widget('cooperatoranalysisupdate');
        win.down('#reset').setText('清空');
    },

    // show cooperator analysis edit utils.
    showAnalysisEditWin: function(button) {
        var grid = button.up('grid');
        var record = grid.getSelectionModel().getSelection()[0];
        var editWin = Ext.widget('cooperatoranalysisupdate');
        var form = editWin.down('form');
        editWin.setTitle('编辑合作伙伴分析');
        form.down('button[action=addOrEditReset]').setText('重置');
        form.loadRecord(record);
        form.down('#cooperatorName').setReadOnly(true);
    },

    // delete cooperator analysis
    delCopAnalysis: function(button) {
        var grid = button.up('grid');
        utils.delRecords(grid, 'delCopAnalysisInfo.action', 'copAnalysisID');
    },

    // double click item show edit Window
    dblClickShowEditWin: function(grid, record) {
        var editBut = Ext.getCmp('cooperatorAnalysisList').down('button[name="edit"]');
        if (!editBut.isHidden()) {
            var editWin = Ext.widget('cooperatoranalysisupdate');
            editWin.setTitle('编辑合作伙伴分析');
            var form = editWin.down('form');
            form.loadRecord(record);
            form.down('#cooperatorName').setReadOnly(true);
        }
    },

    // add or update cooperator analysis.
    addOrUpdateCopAnalysis: function(button) {
        utils.updateRecord(button, 'saveOrUpdateCopAnalysis.action', 'cooperatorAnalysisList');
    },

    // cooperator analysis show.
    showCooperatorAnalysis: function(button) {
        var win = Ext.widget('cooperatoranalysisshowwin', {
            id: 'copAnalysisShowId'
        });
        var id = Ext.getCmp('cooperatorList').getSelectionModel().getSelection()[0].get('cooperatorID');
        var store = win.down('#showListAnalysis').getStore();
        // set currentPage = 1
        store.currentPage = 1;
        store.on('beforeload', function(store, options) {
            var new_params = {
                cooperatorID: id
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.loadPage(1, {
            callback: function(records, operation, success) {
                if (success) {
                    if (store.getTotalCount() > 0) {
                        win.show();
                    } else {
                        win.close();
                        box.confirm('提示', '该合作伙伴目前还没有详细分析，是否添加？', function showResult(button) {
                            if (button == 'yes') {
                                var cooperatorAnalysisList = Ext.getCmp('cooperatorAnalysisList');
                                var cprAnalysisShowFlag = 0;
                                if (typeof (cooperatorAnalysisList) == 'undefined') {
                                    cooperatorAnalysisList = Ext.widget('cooperatoranalysislist');
                                    cprAnalysisShowFlag = 1;
                                }
                                cooperatorAnalysisList.storeMagAbility.load({
                                    params: {
                                        code: '00070006'
                                    }
                                });
                                cooperatorAnalysisList.storeRespSpeed.load({
                                    params: {
                                        code: '00030007'
                                    }
                                });
                                cooperatorAnalysisList.storeTrustDegree.load({
                                    params: {
                                        code: '00030005'
                                    }
                                });
                                cooperatorAnalysisList.storeCopName.load();
                                Ext.widget('cooperatoranalysisupdate');
                                Ext.getCmp('cooperatorID').setValue(id);
                                Ext.getCmp('cooperatorID').setReadOnly(true);
                                if (cprAnalysisShowFlag == 1) {
                                    cooperatorAnalysisList.close();
                                }
                            }
                        });
                    }
                } else {
                    win.close();
                    messageBox.alert('提示', '检索合作伙伴分析出现错误啦！');
                }
            }
        });
    },
    resetRecord: function(button) {
        var cooperator = Ext.getCmp('cooperatorID');
        var id = cooperator.getValue();
        utils.resetRecord(button);
        if (cooperator.readOnly == true) {
            cooperator.select(id);
        }
    },
    contactSelect: function(button) {
        var record = button.up('toolbar').up('grid').getView().getSelectionModel().getSelection()[0];
        initController('customerManagement.customerProfiles.ContactSelect').viewInit(2, record);
    }
});
