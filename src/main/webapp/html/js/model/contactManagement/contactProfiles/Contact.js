Ext.define('CRM.model.contactManagement.contactProfiles.Contact', {
    extend: 'Ext.data.Model',
    fields: [ {
        name: 'contactID',
        type: 'number'
    }, {
        name: 'contactName',
        type: 'string'
    }, {
        name: 'sex',
        type: 'string'
    }, {
        name: 'sexName',
        type: 'string'
    }, {
        name: 'birthday',
        type: 'string'
    }, {
        name: 'company',
        type: 'string'
    }, {
        name: 'department',
        type: 'string'
    }, {
        name: 'job',
        type: 'string'
    }, {
        name: 'phoneNumber',
        type: 'string'
    }, {
        name: 'nativePlace',
        type: 'string'
    }, {
        name: 'contactNational',
        type: 'string'
    }, {
        name: 'addr',
        type: 'string'
    }, {
        name: 'education',
        type: 'string'
    }, {
        name: 'educationName',
        type: 'string'
    }, {
        name: 'health',
        type: 'string'
    }, {
        name: 'email',
        type: 'string'
    }, {
        name: 'fax',
        type: 'string'
    }, {
        name: 'QQ',
        type: 'string'
    }, {
        name: 'homePhone',
        type: 'string'
    }, {
        name: 'political',
        type: 'string'
    }, {
        name: 'hobby',
        type: 'string'
    }, {
        name: 'jobResume',
        type: 'string'
    }, {
        name: 'eduBackground',
        type: 'string'
    }, {
        name: 'descriptions',
        type: 'string'
    }, {
        name: 'customerID',
        type: 'string'
    }, {
        name: 'contactType',
        type: 'string'
    }, {
        name: 'contactTypeName',
        type: 'string'
    }, {
        name : 'createTime',
        type : 'string',
        convert : function(v) {
            if (v == null) {
                return "";
            }
            return v.substr(0, 19);
        }
    }, {
        name : 'updateTime',
        type : 'string',
        convert : function(v) {
            if (v == null) {
                return "";
            }
            return v.substr(0, 19);
        }
    } ]
});