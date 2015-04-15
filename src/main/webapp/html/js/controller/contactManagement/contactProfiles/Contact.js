Ext.define('CRM.controller.contactManagement.contactProfiles.Contact', {
    extend: 'Ext.app.Controller',
    views: [ 'contactManagement.contactProfiles.ContactCard',
             'contactManagement.contactProfiles.ContactList',
            'contactManagement.contactProfiles.ContactForm',
            'contactManagement.contactProfiles.FamilyForm',
            'contactManagement.contactProfiles.SocialForm',
            'contactManagement.contactProfiles.ContactDetail' ],
    stores: [ 'contactManagement.contactProfiles.Contacts',
              'contactManagement.contactProfiles.ContactFamilys',
            'contactManagement.contactProfiles.ContactSocials' ],

    models: [],
    init: function() {
        this.control({
            'contactlist': {
//              select: this.changeDetail,
                itemdblclick: this.editContact,
                selectionchange: this.changeBtn
            },
            'contactlist textfield': {
                blur: utils.trimSpaceSearch
            },
            'contactForm textfield': {
                blur: utils.trimSpace
            },
            'familyForm textfield': {
                blur: utils.trimSpace
            },
            'socialForm textfield': {
                blur: utils.trimSpace
            },
            'contactForm grid[id=Social]': {
                itemdblclick: this.editSocial
            },
            'contactForm grid[id=Family]': {
                itemdblclick: this.editFamily
            },
            'contactlist toolbar button[id=ContactAddBtn]': {
                click: this.addContact
            },
            'contactlist toolbar button[id=ContactEditBtn]': {
                click: this.editContact
            },
            'contactlist toolbar button[id=ContactDelBtn]': {
                click: this.delContact
            },
            'contactlist toolbar button[action=superQueryBtn]': {
                click: this.openOrCloseSuperQueryBtn
            },
            'contactlist button[action=queryBtn]': {
                click: this.contactQuery
            },
            'contactForm button[action=addContactSubmitBtn]': {
                click: this.addContactSubmit
            },
            'contactForm button[action=addContactResetBtn]': {
                click: this.addContactReset
            },
            'contactForm button[action=addContactCancelBtn]': {
                click: this.cancel
            },
            'contactForm button[action=addFamily]': {
                click: this.addFamily
            },
            'contactForm button[action=editFamily]': {
                click: this.editFamily
            },
            'contactForm button[action=addSocial]': {
                click: this.addSocial
            },
            'contactForm button[action=editSocial]': {
                click: this.editSocial
            },
            'contactForm button[action=deleteFamilyOrSocial]': {
                click: this.deleteFamilyOrSocial
            },
            'familyForm button[action=save]': {
                click: this.saveFamilyForm
            },
            'familyForm button[action=reset]': {
                click: this.resetForm
            },
            'familyForm button[action=cancel]': {
                click: this.cancel
            },
            'socialForm button[action=save]': {
                click: this.saveSocialForm
            },
            'socialForm button[action=reset]': {
                click: this.resetForm
            },
            'socialForm button[action=cancel]': {
                click: this.cancel
            }
        });
    },
    viewInit: function(treeId, panel, record) {
        var contactCard = Ext.getCmp('contactCard');
        if (typeof (contactCard) === 'undefined') {
            contactCard = Ext.widget('contactCard');
        }
        if (typeof panel !== 'undefined') {
            panel.insert(0, contactCard);
        }
        var contactList = Ext.getCmp('contactListCard');
        utils.authorizationControl(treeId, contactList);
//        Ext.getCmp('contactDetail').hide();
        if (contactList.sexStore.getCount() == 0) {
            contactList.sexStore.load({
                params: {
                    code: '00030004'
                }
            });
        }
        if (contactList.educationStore.getCount() == 0) {
            contactList.educationStore.load({
                params: {
                    code: '00030002'
                }
            });
        }
        if (contactList.contactTypeStore.getCount() == 0) {
            contactList.contactTypeStore.load({
                params: {
                    code: '00010007'
                }
            });
        }
        if (typeof record !== 'undefined') {
            this.record = record;
            var customerID = record.get('customerID');
            contactList.down('#customerID').setValue(customerID);
            var store = contactList.getStore();
            store.on('beforeload', function(store, options) {
                var new_params = {
                        customerID: customerID
                };
                Ext.apply(store.proxy.extraParams, new_params);
            });
        }
        utils.hideSuperQuery(contactList);
        return contactCard;
    },
    changeDetail: function(grid, record) {
//        if (selections.length == 1) {
            var detail = Ext.getCmp('contactDetail');
            var record = Ext.getCmp('contactListCard').getSelectionModel().getSelection()[0];
            Ext.getCmp('contactDetail').getForm().loadRecord(record);
            var contactId = record.get('contactID');
            var familyForm = Ext.getCmp('familyformdetail');
            var familyStore = Ext.data.StoreManager.lookup('family_store');
            if (new String(familyStore) == 'undefined') {
                familyStore = Ext.create('CRM.store.contactManagement.contactProfiles.ContactFamilys');
            }
            familyStore.load({
                params: {
                    contactID: contactId
                },
                callback: function() {
                    familyForm.removeAll();
                    if (familyStore.count() > 0) {
                        familyStore.each(function(item) {
                            familyForm.add({
                                columnWidth: 0.5,
                                margin: '0 0 0 10',
                                xtype: 'fieldset',
                                title: Ext.String.htmlEncode(item.get('familyRelation')),
                                collapsible: true,
                                defaults: {
                                    htmlEncode: true,
                                    width: 240,
                                    labelWidth: 90
                                },
                                defaultType: 'displayfield',
                                items: [ {
                                    fieldLabel: '姓名',
                                    value: item.get('familyName')
                                }, {
                                    fieldLabel: '出生日期',
                                    value: Ext.util.Format.date(item.get('birthday'), 'Y-m-d')
                                }, {
                                    fieldLabel: '政治面貌',
                                    value: item.get('political')
                                }, {
                                    fieldLabel: '工作单位',
                                    value: item.get('company')
                                }, {
                                    fieldLabel: '职务',
                                    value: item.get('job')
                                }, {
                                    fieldLabel: '备注',
                                    value: item.get('descriptions')
                                } ]
                            });
                        });
                    } else {
                        familyForm.add({
                            xtype: 'panel',
                            layout: 'fit',
                            html: '没有数据'
                        });
                    }
                }
            });
            familyForm.doLayout();
            var socialForm = Ext.getCmp('socialformdetail');
            var socialStore = Ext.data.StoreManager.lookup('social_store');
            if (new String(socialStore) == 'undefined') {
                socialStore = Ext.create('CRM.store.contactManagement.contactProfiles.ContactSocials');
            }
            socialStore.load({
                params: {
                    contactID: contactId
                },
                callback: function() {
                    socialForm.removeAll();
                    if (socialStore.count() > 0) {
                        socialStore.each(function(item) {
                            socialForm.add({
                                columnWidth: 0.5,
                                margin: '0 0 0 10',
                                xtype: 'fieldset',
                                title: Ext.String.htmlEncode(item.get('socialRelation')),
                                collapsible: true,
                                defaults: {
                                    htmlEncode: true,
                                    width: 240,
                                    labelWidth: 90
                                },
                                defaultType: 'displayfield',
                                items: [ {
                                    fieldLabel: '姓名',
                                    value: item.get('socialName')
                                }, {
                                    fieldLabel: '出生日期',
                                    value: Ext.util.Format.date(item.get('birthday'), 'Y-m-d')
                                }, {
                                    fieldLabel: '政治面貌',
                                    value: item.get('political')
                                }, {
                                    fieldLabel: '工作单位',
                                    value: item.get('company')
                                }, {
                                    fieldLabel: '职务',
                                    value: item.get('job')
                                }, {
                                    fieldLabel: '备注',
                                    value: item.get('descriptions')
                                } ]
                            });
                        });
                    } else {
                        socialForm.add({
                            xtype: 'panel',
                            layout: 'fit',
                            html: '没有数据'
                        });
                    }
                }
            });
            detail.on('beforeshow', function() {
                detail.expand();
            });
//            detail.show();
//        } else {
//            Ext.getCmp('contactDetail').hide();
//        }
    },
    changeBtn: function(sm, selections) {
        Ext.getCmp('ContactDelBtn').setDisabled(selections.length == 0);
        Ext.getCmp('ContactEditBtn').setDisabled(selections.length != 1);
//        if (selections.length != 1) {
//            Ext.getCmp('contactDetail').hide();
//        } else {
//            Ext.getCmp('contactDetail').show();
//        }
    },

    addContact: function() {
        if (!Ext.getCmp('contactForm')) {
            Ext.create('CRM.view.contactManagement.contactProfiles.ContactForm');
        }
//        Ext.getCmp('add_tabpan').collapse(Ext.Component.DIRECTION_TOP, false);
        var view = Ext.getCmp('contactForm');
        view.setTitle('添加联系人');
        view.down('form').getForm().reset();
//        Ext.getCmp('Family').store.load({
//            params: {
//                contactID: 0
//            }
//        });
//        Ext.getCmp('Social').store.load({
//            params: {
//                contactID: 0
//            }
//        });
        view.down('#resetform').setText('清空');
        view.showAt(300, 50);
        if (typeof this.record !== 'undefined') {
            view.down('#customerID').setValue(this.record.get('customerID'));
        }
    },
    editContact: function() {
        var button = Ext.getCmp('ContactEditBtn');
        if (button.isHidden) {
            var grid = button.up('grid');
            var checkRecord = grid.getSelectionModel().getSelection();
            var contactID = checkRecord[0].get('contactID');
            if (!Ext.getCmp('contactForm')) {
                Ext.create('CRM.view.contactManagement.contactProfiles.ContactForm');
            }
            Ext.getCmp('add_tabpan').collapse(Ext.Component.DIRECTION_TOP, false);
            var editWin = Ext.getCmp('contactForm');
            editWin.showAt(300, 50);
            editWin.down('#resetform').setText('重置');
            editWin.setTitle('编辑联系人');
            var form = editWin.down('form');
            var family_store = Ext.getCmp('Family').getStore();
            family_store.removed = [];
            family_store.load({
                params: {
                    contactID: contactID
                }
            });
            var social_store = Ext.getCmp('Social').getStore();
            social_store.removed = [];
            social_store.load({
                params: {
                    contactID: contactID
                }
            });
            form.loadRecord(checkRecord[0]);
        }
    },

    delContact: function(button) {
        var grid = button.up('grid');
        utils.delRecordsCheck(grid, 'deleteContact.action', 'contactID');
    },

    openOrCloseSuperQueryBtn: function(button) {
        utils.openOrCloseSuperQueryBtn(button);
    },

    contactQuery: function(button) {
        utils.query(button);
    },

    addContactSubmit: function(button) {
        function setVal(fieldId, val) {
            if (fieldId != 'validate') {
                form.down('[name=' + fieldId + ']').markInvalid(val);
            }
        }
        var win = button.up('window');
        var form = win.down('form');
        var contactID = form.down('#contactID').getValue();
//        var customerID = form.down('#customerID').getValue();
        if (form.getForm().isValid()) {
//            var form = button.up('window').down('form');
//            var createTimeField = form.down('hiddenfield[name=createTime]');
//            var updateTimeField = form.down('hiddenfield[name=updateTime]');
//
//            var now = Ext.util.Format.date(new Date(), 'Y-m-d H:i:s');
//            // create contact : set updateTime to blank
//            if (form.down('hiddenfield[name=contactID]').getValue() === '') {
//                createTimeField.setValue(now);
//                updateTimeField.setValue('');
//            } else {
//                // update contact
//                updateTimeField.setValue(now);
//            }

            if (!form.down('#jobResume').getValue()) {
                form.down('#jobResume').setValue('');
            }
            if (!form.down('#eduBackground').getValue()) {
                form.down('#eduBackground').setValue('');
            }
            var familyjson = new Array();
            var i = 0;
            familyStore = Ext.getCmp('Family').getStore();
            Ext.each(familyStore.getUpdatedRecords(), function(record) {
                familyjson[i] = Ext.encode(record.data);
                i++;
            });
            Ext.each(familyStore.getNewRecords(), function(record) {
                familyjson[i] = Ext.encode(record.data);
                i++;
            });
            var socialjson = new Array();
            var j = 0;
            socialStore = Ext.getCmp('Social').getStore();
            Ext.each(socialStore.getUpdatedRecords(), function(record) {
                socialjson[j] = Ext.encode(record.data);
                j++;
            });
            Ext.each(socialStore.getNewRecords(), function(record) {
                socialjson[j] = Ext.encode(record.data);
                j++;
            });
            var familyIDs = new Array();
            var i = 0;
            Ext.each(familyStore.getRemovedRecords(), function(record) {
                familyIDs[i] = record.get('familyID');
                i++;
            });
            var socialIDs = new Array();
            var i = 0;
            Ext.each(socialStore.getRemovedRecords(), function(record) {
                socialIDs[i] = record.get('socialID');
                i++;
            });
            form.getForm().submit({
                url: 'updateContact.action',
                method: 'POST',
                params: {
                    jsonString: Ext.encode(form.getValues()),
//                    customerID: customerID,
                    familyInfo: familyjson,
                    socialInfo: socialjson,
                    familyID: familyIDs,
                    socialID: socialIDs
                },
                failure: function(response, opts) {
                    if (contactID == '') {
                        Ext.crm.msg("添加失败!", " ");
                    } else {
                        Ext.crm.msg("编辑失败!", " ");
                    }
                },
                success: function(response, opts) {
                    if (CRM.checkResponse(response)) {
                        return;
                    }
                    var responseText = Ext.decode(response.responseText);
                    if (responseText.validate == false) {
                        Ext.iterate(responseText, setVal);
                        messageBox.alert('提示', '输入信息有误，请检查输入信息！');
                    } else {
                        Ext.getCmp('contactListCard').store.load();
                        if (contactID == '') {
                            Ext.crm.msg("添加成功!", " ");
                        } else {
                            Ext.crm.msg("编辑成功!", " ");
                        }
                        win.close();
                    }
                }
            });
        } else {
            messageBox.alert("提示", "输入信息有误，请检查输入信息！");
        }
    },

    addContactReset: function(button) {
        var contactID = 0;
        var form = button.up('window').down('form');
        var checkRecord = Ext.getCmp('contactListCard');
        if (button.getText() == '清空') {
            form.getForm().reset();
        } else {
            checkRecord = Ext.getCmp('contactListCard').getSelectionModel().getSelection();
            contactID = checkRecord[0].get('contactID');
            form.loadRecord(checkRecord[0]);
        }
        Ext.getCmp('Social').getStore().load({
            params: {
                contactID: contactID
            }
        });
        Ext.getCmp('Family').getStore().load({
            params: {
                contactID: contactID
            }
        });
        Ext.getCmp('Social').getStore().removed = [];
        Ext.getCmp('Family').getStore().removed = [];
    },

    cancel: function(button) {
        button.up('window').down('form').getForm().reset();
        button.up('window').hide();
    },
    addFamily: function(button) {

        if (!Ext.getCmp('familyForm')) {
            Ext.create('CRM.view.contactManagement.contactProfiles.FamilyForm');
        }
        var view = Ext.getCmp('familyForm');
        view.setTitle('添加家庭信息');
        var form = view.down('form');
        form.getForm().reset();
        form.down('#contactID').setValue(button.up('grid').up('window').down('form').down('#contactID').getValue());
        view.down('#resetform').setText('清空');
        view.showAt(400, 100);

    },

    editFamily: function(button) {
        if (!Ext.getCmp('familyForm')) {
            Ext.create('CRM.view.contactManagement.contactProfiles.FamilyForm');
        }
        var editWin = Ext.getCmp('familyForm');
        editWin.setTitle('编辑家庭信息');
        editWin.down('#resetform').setText('重置');
        var checkRecord = button.up('grid').getSelectionModel().getSelection();
        var form = editWin.down('form');
        form.loadRecord(checkRecord[0]);
        editWin.showAt(400, 100);
    },

    addSocial: function(button) {
        if (!Ext.getCmp('socialForm')) {
            Ext.create('CRM.view.contactManagement.contactProfiles.SocialForm');
        }
        var view = Ext.getCmp('socialForm');
        view.setTitle('添加社会信息');
        var form = view.down('form');
        form.getForm().reset();
        form.down('#contactID').setValue(button.up('grid').up('window').down('form').down('#contactID').getValue());
        view.down('#resetform').setText('清空');
        view.showAt(400, 100);
    },

    editSocial: function(button) {
        if (!Ext.getCmp('socialForm')) {
            Ext.create('CRM.view.contactManagement.contactProfiles.SocialForm');
        }
        var editWin = Ext.getCmp('socialForm');
        editWin.setTitle('编辑社会信息');
        editWin.down('#resetform').setText('重置');
        var checkRecord = button.up('grid').getSelectionModel().getSelection();
        var form = editWin.down('form');
        form.loadRecord(checkRecord[0]);
        editWin.showAt(400, 100);
    },

    deleteFamilyOrSocial: function(button) {
        var checkRecord = button.up('grid').getSelectionModel().getSelection();
        var store = button.up('grid').getStore();
        store.remove(checkRecord);
        button.up('grid').getView().refresh();
    },

    saveFamilyForm: function(button) {
        box.confirm('提示', '确定保存信息？', function showResult(but) {
            if (but == 'yes') {
                var win = button.up('window');
                var form = win.down('form');
                if (form.getForm().isValid()) {
                    if (form.down('#ID').getValue() == '') {
                        Ext.getCmp('Family').getStore().add(form.getValues());
                    } else {
                        var checkRecord = Ext.getCmp('Family').getSelectionModel().getSelection();
                        checkRecord[0].set(form.getValues());
                    }
                    form.getForm().reset();
                    win.hide();
                } else {
                    messageBox.alert("提示", "输入信息有误，请检查输入信息！");
                }
            }
        });

    },

    saveSocialForm: function(button) {
        box.confirm('提示', '确定保存信息？', function showResult(but) {
            if (but == 'yes') {
                var win = button.up('window');
                var form = win.down('form');
                if (form.getForm().isValid()) {
                    if (form.down('#ID').getValue() == '') {
                        Ext.getCmp('Social').getStore().add(form.getValues());
                    } else {
                        var checkRecord = Ext.getCmp('Social').getSelectionModel().getSelection();
                        checkRecord[0].set(form.getValues());
                    }
                    form.getForm().reset();
                    win.hide();
                } else {
                    messageBox.alert("提示", "输入信息有误，请检查输入信息！");
                }
            }
        });
    },
    resetForm: function(button) {
        box.confirm('提示', '确定重置信息？', function showResult(but) {
            if (but == 'yes') {
                var form = button.up('window').down('form');
                if (form.down('#ID').getValue() == '') {
                    form.getForm().reset();
                } else {
                    var record = form.getRecord();
                    form.loadRecord(record);
                }
            }
        });
    }
});
