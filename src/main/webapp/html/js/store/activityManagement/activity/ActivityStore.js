Ext.define('CRM.store.activityManagement.activity.ActivityStore', {
    extend: 'CRM.store.commonStore',
    alias: 'widget.activityGstore',
    model: 'CRM.model.activityManagement.activity.Activity',
    pageSize: 25,
    proxy: {
        type: 'ajax',
        url: 'getAllActivity.action',
        actionMethods: {
            read: 'POST'
        },
        reader: {
            type: 'json',
            root: 'items',
            totalProperty: 'total' // 总数。 默认值='total'
        }
    }
});