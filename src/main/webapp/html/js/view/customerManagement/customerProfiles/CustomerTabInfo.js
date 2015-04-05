Ext.define('CRM.view.customerManagement.customerProfiles.CustomerTabInfo', {
    extend : 'Ext.panel.Panel',
    alias : 'widget.customertabinfo',
    id : 'customertabinfo',
    itemId : 'customertabinfo',
    layout : 'border',
    title : '<a href="#" onclick="utils.backToCustomer();">客户基本信息</a> >> 客户详细',
//    border : false,
    items : [ {
        region : "north",
        layout : 'vbox',
        xtype : 'form',
        defaults : {
            labelWidth : 60,
            htmlEncode : true,
            margin: '2 20 2 20',
            border: false
        },
        items : [ {
            layout : 'hbox',
            width : 1100,
            defaultType : 'displayfield',
            items : [ {
                name : 'customerName',
                fieldLabel : '客户名称',
                labelWidth : 60,
                //defaultType : 'displayfield',
                width : 350
            }, {
                name : 'holderName',
                fieldLabel : '客户经理',
                labelWidth : 60,
                //defaultType : 'displayfield',
                width : 350
            }, {
                name : 'scaleName',
                fieldLabel : '注册资金',
                labelWidth : 60,
                //defaultType : 'displayfield',
                width : 350
            } ]
        }, {
            layout : 'hbox',
            width : 1100,
            defaultType : 'displayfield',
            items : [ {
                name : 'customerTypeName',
                fieldLabel : '客户类型',
                labelWidth : 60,
                width : 350
            }, {
                name : 'industryName',
                fieldLabel : '市场名称',
                labelWidth : 60,
                width : 350
            }, {
                name : 'customerStatement',
                fieldLabel : '经营单元',
                labelWidth : 60,
                width : 350
            } ]
        }, {
            layout : 'hbox',
            width : 1100,
            defaultType : 'displayfield',
            items : [ {
                name : 'valueEvaluateName',
                fieldLabel : '价值评估',
                labelWidth : 60,
                width : 350
            }, {
                name : 'earningName',
                fieldLabel : '年产值',
                labelWidth : 60,
                width : 350
            }, {
                name : 'feeName',
                fieldLabel : '所有权',
                labelWidth : 60,
                width : 350
            } ]
        }, {
            layout : 'hbox',
            width : 1100,
            defaultType : 'displayfield',
            items : [ {
                name : 'customerAddr',
                fieldLabel : '标准地址',
                labelWidth : 60,
                width : 500
//            }, {
//                name : 'descriptions',
//                fieldLabel : '企业简介',
//                labelWidth : 60,
//                width : 750
            } ]
        } ]
    }, {
        region : 'center',
        layout : 'fit',
        xtype : 'customertab'
    } ]

});