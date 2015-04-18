Ext.define('CRM.model.customerManagement.customerProfiles.Business', {
    extend: 'Ext.data.Model',
    fields: [ {
        name: 'businessId',
        type: 'int'
    }, {
        name: 'customerID',
        type: 'string'
    }, {
        name: 'customerName',
        type: 'string'
    }, {
        name: 'businessType',
        type: 'string'
    }, {
        name: 'businessTypeName',
        type: 'string'
    }, {
        name: 'contractYear',
        type: 'string'
    }, {
        name: 'contractNumber',
        type: 'string'
    }, {
        name: 'contractMoney',
        type: 'string'
    }, {
        name: 'startDate',
        type: 'string'
    }, {
        name: 'descriptions',
        type: 'string'
    }, {
        name: 'createTime',
        type: 'string'
    }, {
        name: 'updateTime',
        type: 'string'
    } ]
});