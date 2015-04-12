Ext.define('CRM.model.customerManagement.contactTrack.ContactModel', {
    extend : 'Ext.data.Model',
    fields : [
        // 第一个字段需要指定mapping， 其他字段， 可以省略掉。
        {
            name : 'contactID',
            mapping : 'contactID'
        }, 
        'customerID',
        'contactTheme',
        'customerName',
        'contactType',
        'contactTypeName',
//        'weContact',
//        'weContactName',
        'oppositeContact', 
        'oppositeContactName', 
        'contactWay',
        'contactWayName',
        'contactContent', 
        'remarks',
        'ifContact',
        'ifContactName',
        {
            name : 'planDateBegin',
            convert : function(v) {
                if (v == null) {
                    return v;
                }
                return v.substr(0, 19);
            }
        }, 
            {
            name : 'realityDateBegin',
            convert : function(v) {
                if (v == null) {
                    return v;
                }
                return v.substr(0, 19);
            }
           }, {
            name : 'realityDateEnd',
            convert : function(v) {
                if (v == null) {
                    return v;
                }
                return v.substr(0, 19);
            }
        },
        'userFeedbackInformation',
        'strategy',
//        'eventID',
//        'eventName',
//        'findChanceContent',
//        'checkResult',
//        'checkChanceContent',
        'notContantReason',
        {
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
        }
//        'flowStatus',
//        'state',
//        'chanceType'
]
});
