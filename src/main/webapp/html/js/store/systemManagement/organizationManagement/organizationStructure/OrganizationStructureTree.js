Ext.define('CRM.store.systemManagement.organizationManagement.organizationStructure.OrganizationStructureTree', {
    extend: 'Ext.data.TreeStore',
    root: {
        text: '科大国创股份有限公司',
        expanded: true,
        children: [ {
            text: '电网系统部',
            expanded: true,
            children: [ {
                text: '电网一部',
                leaf: true
            }, {
                text: '电网二部',
                leaf: true
            } ]
        }, {
            text: '金融系统部',
            leaf: true
        }, {
            text: '通用系统部',
            expanded: true,
            children: [ {
                text: '通用一部',
                leaf: true
            }, {
                text: '通用二部',
                leaf: true
            }, {
                text: '通用三部',
                leaf: true
            } ]
        }, {
            text: '电信部',
            leaf: true

        }, {
            text: '保险系统部',
            leaf: true

        }

        ]

    }

});
