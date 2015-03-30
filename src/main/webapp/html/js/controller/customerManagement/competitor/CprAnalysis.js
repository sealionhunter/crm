Ext.define('CRM.controller.customerManagement.competitor.CprAnalysis', {
    extend: 'Ext.app.Controller',
    stores: [ 'customerManagement.competitor.CprAnalysisStore',
            'customerManagement.competitor.CprChooseStore' ],
    views: [ 'customerManagement.competitor.CprAnalysisList',
            'customerManagement.competitor.AddOrEditCprAnalysis',
            'customerManagement.competitor.DetailedAnalysis',
            'customerManagement.competitor.CompetitorInfoList',
            'customerManagement.competitor.CprAnalysisBody' ],
    init: function() {
        this.control({
            'cpranalysislist': {
                selectionchange: this.selectionchange,
                select: this.showDetail,
                itemdblclick: this.editCprAnalysis
            },
            'cpranalysislist button[action=addCprAnalysis]': {
                click: this.addCprAnalysis
            },
            'cpranalysislist button[action=editCprAnalysis]': {
                click: this.editCprAnalysis
            },
            'cpranalysislist button[action=delCprAnalysis]': {
                click: this.delCprAnalysis
            },
            'addoreditcpranalysis button[action=addOrEditSave]': {
                click: this.addOrEditSave
            },
            'addoreditcpranalysis button[action=addOrEditReset]': {
                click: this.resetRecord
            },
            'addoreditcpranalysis button[action=addOrEditClose]': {
                click: utils.winClose
            },
            'addoreditcpranalysis textarea': {
                blur: utils.trimSpace
            },
            'addoreditcpranalysis textfield': {
                blur: utils.trimSpace
            },
            'competitorInfoList button[action=showCprAnalysisWin]': {
                click: this.showCprAnalysisWin
            },
            'showcpranalysiswin gridpanel': {
                select: this.showCprAnalysisWinDetail,
                selectionchange: this.selectionchangeWin
            }
        });
    },

    viewInit: function(treeId) {
        var cpranalysisbody = Ext.getCmp('cpranalysisbody');
        if (typeof (cpranalysisbody) == 'undefined') {
            cpranalysisbody = Ext.widget('cpranalysisbody');
            Ext.getCmp('centercard').insert(6, cpranalysisbody);
        }
        var cprAnalysisList = Ext.getCmp('cpranalysislist');
        utils.authorizationControl(treeId, cprAnalysisList);
        Ext.getCmp('centercard').getLayout().setActiveItem('cpranalysisbody');
        var store = cprAnalysisList.getStore();
        store.on('beforeload', function(store, options) {
            var new_params = {
                competitorInfoId: 0
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.loadPage(1);
    },

    showDetail: function(view, record, el, index, e) {
        Ext.getCmp('detailedanalysis').loadRecord(record);
    },

    selectionchange: function(sm, selections) {
        Ext.getCmp('CprAnalysisEditBtn').setDisabled(selections.length != 1);
        Ext.getCmp('CprAnalysisDelBtn').setDisabled(selections.length == 0);
        if (selections.length != 1) {
            Ext.getCmp('detailedanalysis').hide();
        } else {
            Ext.getCmp('detailedanalysis').show();
        }
    },

    addCprAnalysis: function(button) {
        Ext.widget('addoreditcpranalysis');
        Ext.getCmp('addOrEditReset').setText('清空');
    },

    editCprAnalysis: function(button) {
        var CprAnalysisEditBtn = Ext.getCmp('CprAnalysisEditBtn');
        if (!CprAnalysisEditBtn.hidden) {
            var list = Ext.getCmp('cpranalysislist');
            var record = list.getView().getSelectionModel().getSelection()[0];
            var view = Ext.widget('addoreditcpranalysis');
            view.down('form').loadRecord(record);
            view.setTitle('编辑竞争对手分析');
            Ext.getCmp('competitorInfoId').setReadOnly(true);
        }
    },

    delCprAnalysis: function(button) {
        var grid = button.up("panel");
        utils.delRecords(grid, 'deleteCprAnalysis.action', 'cprAnalysisID');
    },

    addOrEditSave: function(button) {
        utils.updateRecord(button, 'updateCprAnalysis.action', 'cpranalysislist');
    },

    showCprAnalysisWin: function(button) {
        var win = Ext.getCmp('showCpranAlysisWindow');
        if (typeof (win) == 'undefined') {
            win = Ext.widget('showcpranalysiswin');
        }
        var store = win.down('grid').getStore();
        var selection = button.up('gridpanel').getSelectionModel().getSelection()[0];
        var competitorInfoId = selection.get('competitorInfoId');
        store.on('beforeload', function(store, options) {
            var new_params = {
                competitorInfoId: competitorInfoId
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
                        box.confirm('提示', '该竞争对手目前还没有详细分析，是否添加？', function showResult(button) {
                            if (button == 'yes') {
                                var cprAnalysisList = Ext.getCmp('cpranalysislist');
                                var cprAnalysisShowFlag = 0;
                                if (typeof (cprAnalysisList) == 'undefined') {
                                    cprAnalysisList = Ext.widget('cpranalysislist');
                                    cprAnalysisShowFlag = 1;
                                }
                                cprAnalysisList.industryStore.load({
                                    params: {
                                        code: '00010008'
                                    }
                                });
                                Ext.widget('addoreditcpranalysis');
                                var competitorInfoId = selection.get('competitorInfoId');
                                Ext.getCmp('competitorInfoId').setValue(competitorInfoId);
                                Ext.getCmp('competitorInfoId').setReadOnly(true);
                                Ext.getCmp('addOrEditReset').setText("清空");
                                if (cprAnalysisShowFlag == 1) {
                                    cprAnalysisList.close();
                                }
                            }
                        });
                    }
                } else {
                    win.close();
                    messageBox.alert('提示', '操作失败，请联系管理员！');
                }
            }
        });
    },

    showCprAnalysisWinDetail: function(grid, record) {
        var win = Ext.getCmp('showCpranAlysisWindow');
        var form = win.down('#showDetailedAnalysis');
        form.loadRecord(record);
    },

    selectionchangeWin: function(sm, selections) {
        if (selections.length != 1) {
            Ext.getCmp('showDetailedAnalysis').hide();
        } else {
            Ext.getCmp('showDetailedAnalysis').show();
        }
    },
    resetRecord: function(button) {
        var competitorInfo = Ext.getCmp('competitorInfoId');
        var value = competitorInfo.getValue();
        utils.resetRecord(button);
        if (competitorInfo.readOnly == true) {
            competitorInfo.setValue(value);
        }
    }
});