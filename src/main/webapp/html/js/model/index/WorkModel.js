Ext.define('CRM.model.index.WorkModel', {
    extend: 'Ext.data.Model',
    fields: [ {
        name: 'workID',
        type: 'int'
    }, {
        name: 'userID',
        type: 'int'
    }, {
        name: 'theme',
        type: 'string'
    }, {
        name: 'customerID',
        type: 'string'
    }, {
        name: 'customerName',
        type: 'string'
    }, {
        name: 'teamFlag',
        type: 'int'
    }, {
        name: 'assignee',
        type: 'string'
    }, {
        name: 'assignees',
        convert: function(v, record) {
            return record.get('assignee').split(',');
        }
    }, {
        name: 'priority',
        type: 'string'
    }, {
        name: 'priorityName',
        type: 'string'
    }, {
        name: 'workType',
        type: 'string'
    }, {
        name: 'workTypeName',
        type: 'string'
    }, {
        name: 'startTime',
        convert: function(v) {
            if (v == null) {
                return v;
            }
            return v.substr(0, 19);
        }
    }, {
        name: 'endTime',
        convert: function(v) {
            if (v == null) {
                return v;
            }
            return v.substr(0, 19);
        }
    }, {
        name: 'completion',
        type: 'string'
    }, {
        name: 'completionName',
        type: 'string'
    }, {
        name: 'isMailInformed',
        convert: function(v) {
            if (v) {
                return 'on';
            } else {
                return '';
            }
        }
    }, {
        name: 'workDetail',
        type: 'string'
    }, {
        name: 'descriptions',
        type: 'string'
    } ]
});