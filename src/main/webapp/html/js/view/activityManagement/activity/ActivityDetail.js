Ext.define('CRM.view.activityManagement.activity.ActivityDetail', {
    extend: 'Ext.form.Panel',
    alias: 'widget.activitydetail',
    id: 'activitydetail',
    layout: 'accordion',
    region: "east",
    title: '营销活动详细信息',
    collapsible: true,
    width: 300,
    hidden: true,
    autoScroll: true,
    items: [ {
        title: '<div style="font-size:12px" align="center">基本信息</div>',
        defaultType: 'displayfield',
        autoScroll: true,
        height: 160,
        border: false,
        defaults: {
            x: 10,
            y: 10,
            labelWidth: 80,
            width: 248,
            htmlEncode: true
        },
        items: [ {
            fieldLabel: '活动名称',
            name: 'activityName'
        }, {
            fieldLabel: '活动类型',
            name: 'activityTypeName'
        }, {
            fieldLabel: '活动地点',
            name: 'activityPlace'
        }, {
            fieldLabel: '活动范围',
            name: 'activityRangeName'
        }, {
            fieldLabel: '重视程度',
            name: 'activityEmphasisName'
        }, {
            fieldLabel: '开始时间',
            name: 'activityStartTime'
        }, {
            fieldLabel: '结束时间',
            name: 'activityEndTime'
        }, {
            fieldLabel: '备注',
            name: 'activityComment'
        }, {
            fieldLabel: '参加成员',
            name: 'memberName'
        } ]
    }, {
        title: '<div style="font-size:12px" align="center">活动细节</div>',
        defaultType: 'displayfield',
        autoScroll: true,
        height: 160,
        border: false,
        defaults: {
            x: 10,
            y: 10,
            labelWidth: 70,
            width: 200,
            htmlEncode: true
        },
        items: [ {
            fieldLabel: '活动目的',
            name: 'activityAim'
        }, {
            fieldLabel: '我方人数',
            name: 'activityOurPersonNO'
        }, {
            fieldLabel: '我方负责人',
            name: 'activityLeader'
        }, {
            fieldLabel: '录入人',
            name: 'activityModifer'
        }, {
            fieldLabel: '活动经费',
            name: 'activityFunds'
        }, {
            fieldLabel: '活动周期',
            name: 'activityPeriodName'
        }, {
            fieldLabel: '活动状态',
            name: 'activityStateName'
        }, {
            fieldLabel: "活动计划",
            name: 'activityPlan',
            htmlEncode: false
        }, {
            fieldLabel: "详细内容",
            name: 'activityDetail',
            htmlEncode: false
        } ]
    }, {
        title: '<div style="font-size:12px" align="center">活动意义</div>',
        defaultType: 'displayfield',
        autoScroll: true,
        height: 160,
        border: false,
        defaults: {
            x: 10,
            y: 10,
            labelWidth: 60,
            width: 200
        },
        items: [ {
            name: 'activityMeans',
            htmlEncode: true
        } ]
    } ]
});