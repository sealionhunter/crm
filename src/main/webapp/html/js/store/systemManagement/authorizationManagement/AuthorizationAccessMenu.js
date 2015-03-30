Ext.define('CRM.store.systemManagement.authorizationManagement.AuthorizationAccessMenu', {
    extend: 'Ext.data.TreeStore',
    root: {
        text: 'root',
        id: 'userRole_0',
        leaf: false,
        expanded: true,
        children: [ {
            text: '客户fd管理',
            id: 'userRole_00',
            checked: false,
            expanded: true,
            children: [ {
                text: '客户档案',
                id: 'userRole_000',
                checked: false,
                expanded: false,
                children: [ {
                    text: '客户基本信息',
                    id: 'userRole_0000',
                    checked: false,
                    leaf: true
                }, {
                    text: '客户来源',
                    id: 'userRole_0001',
                    checked: false,
                    leaf: true
                } ]
            }, {
                text: '客户联系跟踪',
                id: 'userRole_001',
                checked: false,
                children: [ {
                    text: '客户联系',
                    id: 'userRole_0010',
                    checked: false,
                    leaf: true
                }, {
                    text: '客户联系历史',
                    id: 'userRole_0011',
                    checked: false,
                    leaf: true
                } ]

            }, {
                id: 'userRole_002',
                text: '竞争对手',
                checked: false,
                children: [ {
                    text: '竞争对手基本信息',
                    checked: false,
                    id: 'userRole_0020',
                    leaf: true,
                    id: "131"
                }, {
                    text: '竞争对手分析',
                    id: 'userRole_0021',
                    checked: false,
                    leaf: true
                } ]

            }, {
                id: 'userRole_003',
                text: '合作伙伴',
                checked: false,
                children: [ {
                    text: '合作伙伴基本信息',
                    id: 'userRole_0030',
                    checked: false,
                    leaf: true
                }, {
                    text: '合作伙伴分析',
                    id: 'userRole_0031',
                    checked: false,
                    leaf: true
                } ]

            }, {
                id: 'userRole_004',
                text: '订单',
                checked: false,
                children: [ {
                    id: 'userRole_0040',
                    text: '订单基本信息',
                    checked: false,
                    leaf: true
                } ]

            }, {
                id: 'userRole_005',
                text: '建议书与合同',
                checked: false,
                children: [ {
                    id: 'userRole_0050',
                    text: '建议书管理',
                    checked: false,
                    children: [ {
                        text: '建议书模板',
                        id: 'userRole_00500',
                        checked: false,
                        leaf: true
                    }, {
                        text: '建议书一览',
                        id: 'userRole_00501',
                        checked: false,
                        leaf: true
                    } ]
                }, {
                    text: '合同管理',
                    id: 'userRole_0051',
                    checked: false,
                    children: [ {
                        text: '合同模板',
                        id: 'userRole_00510',
                        checked: false,
                        leaf: true
                    }, {
                        text: '合同一览',
                        id: 'userRole_00511',
                        checked: false,
                        leaf: true
                    } ]
                } ]

            } ]
        }, {
            text: '联系人管理',
            id: 'userRole_01',
            checked: false,
            expanded: true,
            children: [ {
                text: '联系人档案',
                id: 'userRole_010',
                checked: false,
                children: [ {
                    text: '联系人基本信息',
                    id: 'userRole_0100',
                    checked: false,
                    leaf: true
                } ]

            } ]
        }, {
            text: '营销活动管理',
            id: 'userRole_02',
            expanded: true,
            checked: false,
            children: [ {
                text: '营销活动',
                id: 'userRole_020',
                checked: false,
                children: [ {
                    text: '营销活动基本信息',
                    checked: false,
                    id: 'userRole_0200',
                    leaf: true
                } ]
            } ]
        }, {
            text: '系统34管理',
            id: 'userRole_03',
            checked: false,
            expanded: true,
            children: [ {
                text: '系统管理',
                id: 'userRole_030',
                checked: false,
                children: [ {
                    text: '用户管理',
                    id: 'userRole_0300',
                    checked: false,
                    leaf: true
                }, {
                    text: '权限管理',
                    id: 'userRole_0301',
                    checked: false,
                    leaf: true
                } ]
            } ]
        } ]
    }
});