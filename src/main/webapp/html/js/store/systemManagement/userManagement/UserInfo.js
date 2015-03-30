Ext.define('CRM.store.systemManagement.userManagement.UserInfo', {
    extend: 'CRM.store.commonStore',
    model: 'CRM.model.systemManagement.userManagement.UserInfo',
    pageSize: 25,
    id: 'UserInfoStore',
    proxy: {
        type: 'ajax',
        url: 'getAllUserInfo.action',
        actionMethods: {
            read: 'POST'
        },
        reader: {
            type: 'json',
            root: 'items'
//            totalProperty: 'total' // 总数。 默认值='total'
        }
    }
});