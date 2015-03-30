Ext.define('CRM.model.customerManagement.customerProfiles.Customer', {
    extend : 'Ext.data.Model',
    fields : [ {
        name : 'customerID',
        type : 'int'
    }, {
        name : 'customerName',
        type : 'string'
    }, {
        name : 'holder',
        type : 'int'
    }, {
        name : 'holderName',
        type : 'string'
    }, {
        name : 'scale',
        type : 'string'
    }, {
        name : 'scaleName',
        type : 'string'
    }, {
        name : 'customerTypeName',
        type : 'string'
    }, {
        name : 'customerStatementName',
        type : 'string'
    }, {
        name : 'industryName',
        type : 'string'
    }, {
        name : 'valueEvaluateName',
        type : 'string'
    }, {
        name : 'feeName',
        type : 'string'
    }, {
        name : 'customerType',
        type : 'string'
    }, {
        name : 'industry',
        type : 'string'
    }, {
        name : 'customerStatement',
        type : 'string'
    }, {
        name : 'valueEvaluate',
        type : 'string'
    }, {
        name : 'fee',
        type : 'string'
    }, {
        name : 'customerAddr',
        type : 'string'
    }, {
        name : 'earning',
        type : 'string'
    }, {
        name : 'earningName',
        convert : function(v, record) {
            var value = record.get('earning');
            if (value == '' || value == 0) {
                return '';
            } else {
                return value;
            }
        }
    }, {
        name : 'descriptions',
        type : 'string'
    }, {
        name : 'business1',
        type : 'string'
    }, {
        name : 'business2',
        type : 'string'
    }, {
        name : 'business3',
        type : 'string'
    }, {
        name : 'business4',
        type : 'string'
    }, {
        name : 'businessUnit',
        type : 'string'
    }, {
        name : 'number',
        type : 'string'
    }, {
        name : 'createTime',
        type : 'string',
        convert : function(v) {
            if (v == null) {
                return v;
            }
            return v.substr(0, 19);
        }
    }, {
        name : 'updateTime',
        type : 'string',
        convert : function(v) {
            if (v == null) {
                return v;
            }
            return v.substr(0, 19);
        }
    }]
});