Ext.define('CRM.store.customerManagement.proposalOrContract.ContractPayTypeStore', {
    extend: 'CRM.store.commonStore',
    fields: [ 'value', 'key' ],
    data: [ {
        "value": "现金支付",
        "key": "1"
    }, {
        "value": "银行转账支付",
        "key": "2"
    }, {
        "value": "信用卡支付",
        "key": "3"
    } ]
});