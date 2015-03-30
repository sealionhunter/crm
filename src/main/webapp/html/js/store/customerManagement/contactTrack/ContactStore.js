Ext.define('CRM.store.customerManagement.contactTrack.ContactStore', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.customerManagement.contactTrack.ContactModel',
    pageSize: 25,
    clearOnPageLoad: true,
    proxy: {
        // 异步获取数据，这里的URL可以改为任何动态页面，只要返回JSON数据即可
        type: 'ajax',
        url: 'ContactTrackAction.action',
        actionMethods: {
            read: 'POST'
        },
        reader: {
            type: 'json',
            root: 'items',
            totalProperty: 'total'
        }
    }
});