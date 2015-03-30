Ext.define('CRM.controller.customerManagement.customerProfiles.ContactSelect', {
    extend: 'Ext.app.Controller',
    views: [ 'customerManagement.customerProfiles.ContactSelect' ],
    stores: [ 'customerManagement.customerProfiles.ContactSelect',
            'customerManagement.customerProfiles.ContactCanSelect' ],
    init: function() {
        this.control({
            'contactselect > gridpanel[id=contactSListForm]': {
                itemdblclick: this.addContact,
                selectionchange: this.contactSListFormSelectionchange
            },
            'contactselect > gridpanel[id=contactSelectForm]': {
                itemdblclick: this.removeContact,
                selectionchange: this.contactSelectFormSelectionchange
            },
            'contactselect button[action=contactSelectAdd]': {
                click: this.addContact
            },
            'contactselect button[action=contactSelectRemove]': {
                click: this.removeContact
            },
            'contactselect gridpanel[id=contactSListForm] button[id=searchText_contactBtn]': {
                click: this.contactSearch
            }
        });
    },

    viewInit: function(flag, record) {
        var win = Ext.widget('contactselect');
        var objectID = '';
        var objectFlag = '';
        var objectIDValue = '';
        if (flag == 1) {
            var customerName = record.get('customerName');
            objectIDValue = record.get('customerID');
            win.setTitle('联系人选择-' + Ext.String.htmlEncode(customerName));
            objectID = 'objectID:' + objectIDValue + ',';
            objectFlag = 'objectFlag:1';
        } else {
            var cooperatorName = record.get('cooperatorName');
            objectIDValue = record.get('cooperatorID');
            win.setTitle('联系人选择-' + Ext.String.htmlEncode(cooperatorName));
            objectID = 'objectID:' + objectIDValue + ',';
            objectFlag = 'objectFlag:2';
        }
        Ext.getCmp('objectID').setValue(objectIDValue);
        Ext.getCmp('objectFlag').setValue(flag);
        var searchStr = '{' + objectID + objectFlag + '}';
        var searchBean = Ext.decode(searchStr);
        var ContactSelectStore = Ext.getCmp('contactSelectForm').getStore();
        ContactSelectStore.load({
            params: {
                contactSelectSearchBean: Ext.encode(searchBean)
            }
        });
        var store = Ext.getCmp('contactSListForm').getStore();
        store.on('beforeload', function(store, options) {
            var new_params = {
                contactSearchText: encodeURI(Ext.getCmp('searchText_contact').getValue())
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.loadPage(1);
    },

    contactSListFormSelectionchange: function(sm, selections) {
        Ext.getCmp('contactSelectAdd').setDisabled(selections.length == 0);
    },

    contactSelectFormSelectionchange: function(sm, selections) {
        Ext.getCmp('contactSelectRemove').setDisabled(selections.length == 0);
    },

    addContact: function() {
        var record = Ext.getCmp('contactSListForm').getView().getSelectionModel().getSelection();
        var canSelectStore = Ext.getCmp('contactSListForm').getStore();
        var contactSelectAddIDs = new Array();
        for ( var i = 0; i < record.length; i++) {
            contactSelectAddIDs[i] = record[i].get("contactID");
        }
        Ext.Ajax.request({
            url: 'addContactSelect.action',
            params: {
                contactSelectAddIDs: contactSelectAddIDs,
                objectID: Ext.getCmp('objectID').getValue(),
                objectFlag: Ext.getCmp('objectFlag').getValue()
            },
            success: function(response) {
                if (CRM.checkResponse(response)) {
                    return;
                }
                var objectID = 'objectID:' + Ext.getCmp('objectID').getValue() + ',';
                var objectFlag = 'objectFlag:' + Ext.getCmp('objectFlag').getValue();
                var searchStr = '{' + objectID + objectFlag + '}';
                var searchBean = Ext.decode(searchStr);
                canSelectStore.load();
                Ext.getCmp('contactSelectForm').getStore().load({
                    params: {
                        contactSelectSearchBean: Ext.encode(searchBean)
                    }
                });
            },
            failure: function(response) {
                messageBox.alert('提示', '添加失败，请联系管理员！');
            }
        });
    },

    removeContact: function() {
        var record = Ext.getCmp('contactSelectForm').getSelectionModel().getSelection();
        var canSelectStore = Ext.getCmp('contactSListForm').getStore();
        var contactSelectIDs = new Array();
        for ( var i = 0; i < record.length; i++) {
            contactSelectIDs[i] = record[i].get("contactSelectID");
        }
        box.confirm('提示', '确定移除所选联系人？', function(result) {
            if (result == 'yes') {
                if (record) {
                    Ext.Ajax.request({
                        url: 'deleteContactSelect.action',
                        params: {
                            contactSelectIDs: contactSelectIDs
                        },
                        success: function(response) {
                            if (CRM.checkResponse(response)) {
                                return;
                            }
                            var objectID = 'objectID:' + Ext.getCmp('objectID').getValue() + ',';
                            var objectFlag = 'objectFlag:' + Ext.getCmp('objectFlag').getValue();
                            var searchStr = '{' + objectID + objectFlag + '}';
                            var searchBean = Ext.decode(searchStr);
                            canSelectStore.load();
                            Ext.getCmp('contactSelectForm').getStore().load({
                                params: {
                                    contactSelectSearchBean: Ext.encode(searchBean)
                                }
                            });
                        },
                        failure: function(response) {
                            messageBox.alert('提示', '删除失败，请联系管理员！');
                        }
                    });
                }
            }
        });
    },

    contactSearch: function() {
        var contactSearchText = Ext.getCmp('searchText_contact');
        if (contactSearchText.isValid()) {
            var store = Ext.getCmp('contactSListForm').getStore();
            store.loadPage(1, {
                callback: function() {
                    if (store.getCount() == 0) {
                        messageBox.alert("提示", "未检索到符合条件的数据！");
                    }
                }
            });
        } else {
            messageBox.alert("提示", "您搜索的值不符合规范，请重新输入！");
        }
    }
});