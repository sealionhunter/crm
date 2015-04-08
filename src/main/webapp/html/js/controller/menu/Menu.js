Ext.define('CRM.controller.menu.Menu', {
    extend: 'Ext.app.Controller',
    views: [ 'menu.Menu', 'main.Main' ],
    stores: [ 'systemManagement.authorizationManagement.AccessRightsTree' ],
    models: [],
    init: function() {
        this.control({
            'panel[id=centercard]': {
                render: this.onPanelRendered
            },
            'crmmenu': {
                itemclick: this.changeCard,
                render: this.loadStore
            },
            'crmmenu button[action=expand]': {
                click: this.expandAll
            },
            'crmmenu button[action=collapse]': {
                click: this.collapseAll
            }
        });
    },
    onPanelRendered: function(panel) {
        if (GROUP_ID == 0) {
            initController('systemManagement.menuManagement.MenuManage').viewInit();
        } else {
            initController('index.IndexController').viewInit();
        }
    },
    expandAll: function(button) {
        button.up('treepanel').expandAll();
    },
    collapseAll: function(button) {
        button.up('treepanel').collapseAll();
    },
    loadStore: function(treePanel) {
        var userMode = null;
        if (GROUP_ID == 0) {
            userMode = 2;
        } else {
            userMode = 0;
        }
        var new_params = {
            userMode: userMode,
            id: GROUP_ID
        };
        var treeStore = treePanel.getStore();
        treeStore.on('beforeload', function(store, options) {
            Ext.apply(store.proxy.extraParams, new_params);
        });
        treeStore.load({
            callback: function(records, options, success) {
                if (success) {
                    // Modified for EXTJS 4.2 start 20150302
                    var rootNode = treePanel.getRootNode();
                    if (GROUP_ID != 0) {
                        rootNode.insertChild(0, {
                            id: 0,
                            text: '主页',
                            leaf: true
                        });
//                        if (GROUP_ID == 1) {
//                            var systemManageNode = treeStore.getNodeById(4);
//                            if (typeof (systemManageNode) == 'object') {
////                                rootNode.removeAll();
//                                utils.removeAllChild.apply(rootNode);
//                                rootNode.insertChild(1, systemManageNode);
//                            }
//                        }
                    } else {
                        Ext.getCmp('crmTreeMenu').down('toolbar[dock=top]').hide();
                        var systemManageNode = treeStore.getNodeById(4);
                        var menuManageNode = treeStore.getNodeById(45);
//                        systemManageNode.removeAll();
                        utils.removeAllChild.apply(systemManageNode);
                        systemManageNode.insertChild(1, menuManageNode);
//                        rootNode.removeAll();
                        utils.removeAllChild.apply(rootNode);
                        rootNode.insertChild(1, systemManageNode);
                    }
                    rootNode.removeChild(treeStore.getNodeById(2));
                    if (typeof treeStore.getNodeById(1) !== 'undefined') {
                        treeStore.getNodeById(1).removeChild(treeStore.getNodeById(12));
                    }
                    rootNode.cascadeBy(function(node) {
                        node.set('checked', null);
                    });
                    // Modified for EXTJS 4.2 start 20150302
                }
            }
        });
    },

    changeCard : function(view, record, item, index, e) {
        /*
         * if view is not null, the event fired by click tree node. then add a
         * link to index toobar. if it is null,the event fired by click index
         * navigation link, the execute the event.
         */
        // var treeId = record.raw.id;
        var treeId = record.data.id;
        if (treeId == 0) {
            initController('index.IndexController').viewInit(treeId);
        } else if (treeId == 111) {
            initController('customerManagement.customerProfiles.Customer').viewInit(treeId, false);
        } else if (treeId == 112) {
            initController('customerManagement.customerProfiles.Customer').viewInit(treeId, true);
        } else if (treeId == 113) {
            initController('customerManagement.customerProfiles.CustomerTransfer').viewInit(treeId, true);
//        } else if (treeId == 121) {
//            initController('customerManagement.contactTrack.ContactController').viewInit(treeId);
//        } else if (treeId == 122) {
//            initController('customerManagement.contactTrack.ContactHistory').viewInit(treeId);
//        } else if (treeId == 131) {
//            initController('customerManagement.competitor.CompetitorInfoController').viewInit(treeId);
//        } else if (treeId == 132) {
//            initController('customerManagement.competitor.CprAnalysis').viewInit(treeId);
//        } else if (treeId == 141) {
//            initController('customerManagement.cooperator.Cooperator').viewInit('cooperator', treeId);
//        } else if (treeId == 142) {
//            initController('customerManagement.cooperator.Cooperator').viewInit('copAnalysis', treeId);
//        } else if (treeId == 151) {
//            initController('customerManagement.intentOrder.IntentOrder').viewInit('show');
//        } else if (treeId == 152) {
//            initController('customerManagement.order.Order').viewInit('show');
//        } else if (treeId == 1611) {
//            initController('customerManagement.proposalOrContract.FileTemplate').proposalViewInit(treeId);
//        } else if (treeId == 1612) {
//            initController('customerManagement.proposalOrContract.ProposalOrContract').viewInit(treeId, 2, 'proposal', 'proposaltype');
//        } else if (treeId == 1621) {
//            initController('customerManagement.proposalOrContract.FileTemplate').fileViewInit(treeId);
//        } else if (treeId == 1622) {
//            initController('customerManagement.proposalOrContract.Contract').viewInit(treeId);
//        } else if (treeId == 211) {
//            initController('contactManagement.contactProfiles.Contact').viewInit(treeId);
//        } else if (treeId == 311) {
//            initController('activityManagement.activity.Activities').viewInit(treeId);
        } else if (treeId == 42) {
            initController('systemManagement.userManagement.User').viewInit('show');
        } else if (treeId == 431) {
            initController('systemManagement.organizationManagement.organizationStructure.OrganizationStructure').viewInit();
        } else if (treeId == 432) {
            initController('systemManagement.organizationManagement.departmentManagement.Department').viewInit('show');
        } else if (treeId == 433) {
            initController('systemManagement.organizationManagement.projectTeamManagement.ProjectTeam').viewInit();
        } else if (treeId == 41) {
            initController('systemManagement.groupManagement.Group').viewInit();
        } else if (treeId == 441) {
            initController('systemManagement.codeManagement.Code').viewInit('show');
//        } else if (treeId == 442) {
//            initController('salesManagement.eventFlow.EventFlow').viewInit(treeId);
        } else if (treeId == 45) {
            initController('systemManagement.menuManagement.MenuManage').viewInit();
//        } else if (treeId == 51) {
//            initController('statistics.salesStatistics.SalesInit').viewInit(treeId);
//        } else if (treeId == 61) {
//            initController('salesManagement.transactionManagement.TransactionManagement').viewInit(treeId);
//        } else if (treeId == 71) {
//            initController('productManagement.Product').viewInit();
//        } else if (treeId == 72) {
//            initController('productManagement.Price').viewInit();
        }
    }
});
