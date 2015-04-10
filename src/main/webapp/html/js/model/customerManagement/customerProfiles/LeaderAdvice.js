Ext.define('CRM.model.customerManagement.customerProfiles.LeaderAdvice', {
    extend : 'Ext.data.Model',
    fields : [ {
        name : 'adviceID',
        type : 'int'
    }, {
        name : 'customerID',
        type : 'int'
    }, {
        name : 'customerName',
        type : 'string'
    }, {
        name : 'userID',
        type : 'int'
    }, {
        name : 'userName',
        type : 'string'
    }, {
        name : 'adviceContent',
        type : 'string'
    }, {
        name : 'hasRead',
        type : 'boolean' //TODO
    }, {
        name : 'createTime',
        type : 'string'
    }, {
        name : 'updateTime',
        type : 'string'
    }]
});