Ext.define('CRM.controller.systemManagement.codeManagement.Code', {
    extend: 'Ext.app.Controller',
    views: [ 'systemManagement.codeManagement.CodeList', 'systemManagement.codeManagement.CodeUpdate' ],
    models: [ 'systemManagement.codeManagement.CodeItems' ],
    init: function() {
        this.control({
            'CodeList': {
                selectionchange: this.changeBtn
            },
            'CodeList button[action=codeItemsAddBtn]': {
                click: this.addCodeItems
            },
            'CodeList button[action=codeItemsEditBtn]': {
                click: this.editCodeItems
            },
            'CodeUpdate button[action=save]': {
                click: this.saveCode
            },
            'CodeUpdate button[action=cancel]': {
                click: this.closeWindow
            },
            'CodeList combobox[name=module]': {
                change: this.loadCategory
            },
            'CodeUpdate combobox[name=module]': {
                change: this.loadCategory
            },
            'CodeUpdate combobox[name=addMode]': {
                change: this.changeMode
            },
            'CodeList combobox[name=mode]': {
                change: this.changeMode
            },
            'CodeList button[action=queryBtn]': {
                click: this.loadData
            },
            'CodeList button[action=codeItemsDeleteBtn]': {
                click: this.deleteCodeItems
            }
        });
    },

    viewInit: function(flag) {
        var view = Ext.getCmp('CodeList');
        if (typeof (view) == 'undefined') {
            view = Ext.widget('CodeList');
        }
        Ext.getCmp('centercard').insert(1, view);
        Ext.getCmp('centercard').getLayout().setActiveItem('CodeList');
        Ext.getCmp('moduleComboBox').setValue('');
        Ext.getCmp('categoryComboBox').setValue('');
        view.getStore().setProxy(new Ext.data.proxy.Ajax({
            url: 'getAllData.action',
            reader: {
                type: 'json',
                root: 'items'
            }
        }));
        view.getStore().loadPage(1);
        var mode = Ext.getCmp('viewModeComboBox');
        mode.getStore().removeAll();
        mode.getStore().insert(0, {
            code: '3',
            value: '数据项'
        });
        mode.getStore().insert(0, {
            code: '2',
            value: '类别'
        });
        mode.getStore().insert(0, {
            code: '1',
            value: '模块'
        });
        mode.setValue('3');
        var moduleStore = view.moduleStore;
        this.comboBoxStoreLoad(moduleStore);
        Ext.getCmp('codeItemsEdit').setDisabled(true);
        Ext.getCmp('codeItemsDelete').setDisabled(true);
        this.deleteFlag = 1;
        this.editFlag = 1;
        this.addMode = '3';
        return view;
    },
    comboBoxStoreLoad: function(store) {
        store.load({
            callback: function(records, operation, success) {
                store.insert(0, {
                    code: '0000',
                    value: '- 不限 -'
                });
                Ext.getCmp('moduleComboBox').setValue('0000');
            }
        });

    },
    changeBtn: function(sm, selections) {
        Ext.getCmp('codeItemsDelete').setDisabled(selections.length == 0 || this.deleteFlag == 0);
        Ext.getCmp('codeItemsEdit').setDisabled(selections.length != 1 || this.editFlag == 0);
    },
    loadCategory: function(combobox) {
        var category;
        var panel;
        var me = this;
        if (combobox.up('window') == null) {
            panel = combobox.up('grid');
            category = panel.down('combobox[name="category"]');
        } else {
            panel = combobox.up('window');
            category = panel.down('combobox[name="category"]');
        }
        category.setValue('');
        var value = panel.down('combobox[name="module"]').getValue();
        if (value != '') {
            category.getStore().setProxy(new Ext.data.proxy.Ajax({
                type: 'ajax',
                url: 'getCategory.action?code=' + value,
                reader: {
                    type: 'json',
                    root: 'items'
                }
            }));
            category.getStore().load({
                callback: function(records, operation, success) {
                    if (combobox.up('window') == null) {
                        category.getStore().insert(0, {
                            code: '0000',
                            value: '- 不限 -'
                        });
                        category.setValue('0000');
                    }
                    if (combobox.up('window') != null) {
                        category.setValue(me.addCategory);
                        category.clearInvalid();
                    }
                }
            });
        } else {
            category.getStore().removeAll();
        }
        category.clearInvalid();
    },

    loadData: function(button) {
        var view = Ext.getCmp('CodeList');
        var mode = view.down('combobox[name="mode"]').getValue();
        var category = view.down('combobox[name="category"]').getValue();
        var module = view.down('combobox[name="module"]').getValue();
        var url;
        var me = this;
        if (mode == '1') {
            me.addMode = '1';
            me.addModule = '';
            me.addCategory = '';
            url = 'getData.action?mode=' + mode;
        } else if (mode == '2') {
            me.addMode = '2';
            if (module != '0000' && module != '') {
                me.addModule = module;
            } else {
                me.addModule = '';
            }
            me.addCategory = '';
            url = 'getData.action?module=' + module + '&mode=' + mode;
        } else {
            this.addMode = '3';
            if (module != '0000' && module != '') {
                me.addModule = module;
            } else {
                me.addModule = '';
            }
            if (category != '0000' && category != '') {
                me.addCategory = category;
            } else {
                me.addCategory = '';
            }
            url = 'getData.action?module=' + module + '&category=' + category + '&mode=' + mode;
        }
        if (mode == '2' && module == '0004') {
            me.deleteFlag = 0;
        } else {
            me.editFlag = 1;
            me.deleteFlag = 1;
        }
        view.getStore().setProxy(new Ext.data.proxy.Ajax({
            url: url,
            reader: {
                type: 'json',
                root: 'items'
            }
        }));
        view.getStore().loadPage(1);
    },
    addCodeItems: function() {
        var me = this;
        var win = Ext.widget('CodeUpdate');
        var mode = Ext.getCmp('addModeComboBox');
        var module = win.down('combobox[name="module"]');
        module.getStore().load();
        if (me.addModule != '') {
            module.setValue(me.addModule);
        }
        if (me.addMode != '0') {
            mode.setValue(me.addMode);
        }
    },

    closeWindow: function() {
        var win = Ext.getCmp('CodeUpdate');
        win.close();
    },
    saveCode: function() {
        var addFlag = 1;
        var me = this;
        var view = Ext.getCmp('CodeList');
        var win = Ext.getCmp('CodeUpdate');
        var form = win.down('form');
        var value = win.down('textfield[name="value"]').getValue();
        var mode = win.down('combobox[name="addMode"]').getValue();
        var category = win.down('combobox[name="category"]').getValue();
        var module = win.down('combobox[name="module"]').getValue();
        var code = '';
        if (win.down('combobox[name="category"]').isVisible()) {
            if (mode == '1') {
                code = '';
                addFlag = 1;
            } else if (mode == '2') {
                if (module != '') {
                    code = module;
                    addFlag = 1;
                } else {
                    addFlag = 0;
                }
            } else if (mode == '3') {
                if (category != '') {
                    code = category;
                    addFlag = 1;
                } else {
                    addFlag = 0;
                }
            } else {
                addFlag = 0;
            }
            if (form.getForm().isValid() && value != '' && addFlag != 0) {
                Ext.Ajax.request({
                    url: 'addCode.action',
                    params: {
                        code: code,
                        value: value
                    },
                    method: 'POST',
                    success: function(response, opts) {
                        if (CRM.checkResponse(response)) {
                            return;
                        }
                        if (Ext.JSON.decode(response.responseText).msg == 'overflow') {
                            messageBox.alert('提示', '超过可添加数量！');
                        } else if (Ext.JSON.decode(response.responseText).msg == 'valueExisted') {
                            form.down('[name=value]').markInvalid('该属性已存在！');
                        } else {
                            if (me.addMode == '1') {
                                var moduleBox = Ext.getCmp('moduleComboBox');
                                moduleBox.getStore().load({
                                    callback: function(records, operation, success) {
                                        moduleBox.getStore().insert(0, {
                                            code: '0000',
                                            value: '- 不限 -'
                                        });
                                        moduleBox.setValue('0000');
                                    }
                                });
                            }
                            if (me.addMode == '2') {
                                var categoryBox = Ext.getCmp('categoryComboBox');
                                categoryBox.getStore().load({
                                    callback: function(records, operation, success) {
                                        categoryBox.getStore().insert(0, {
                                            code: '0000',
                                            value: '- 不限 -'
                                        });
                                        categoryBox.setValue('0000');
                                    }
                                });
                            }
                            view.getStore().load();
                            win.close();
                        }
                    },
                    failure: function(response, opts) {
                        win.close();
                        messageBox.alert('提示', '添加失败！');
                    }
                });
            } else {
                messageBox.alert('提示', '输入信息有误，请检查输入信息！');
            }
        } else {
            code = win.down('textfield[name="code"]').getValue();
            if (value != '') {
                Ext.Ajax.request({
                    url: 'editCode.action',
                    params: {
                        module: '',
                        category: '',
                        code: code,
                        value: value
                    },
                    method: 'POST',
                    success: function(response, opts) {
                        if (CRM.checkResponse(response)) {
                            return;
                        }
                        if (me.addMode == '1') {
                            var moduleBox = Ext.getCmp('moduleComboBox');
                            moduleBox.getStore().load({
                                callback: function(records, operation, success) {
                                    moduleBox.getStore().insert(0, {
                                        code: '0000',
                                        value: '- 不限 -'
                                    });
                                    moduleBox.setValue('0000');
                                }
                            });
                        }
                        if (me.addMode == '2') {
                            var categoryBox = Ext.getCmp('categoryComboBox');
                            categoryBox.getStore().load({
                                callback: function(records, operation, success) {
                                    categoryBox.getStore().insert(0, {
                                        code: '0000',
                                        value: '- 不限 -'
                                    });
                                    categoryBox.setValue('0000');
                                }
                            });
                        }
                        view.getStore().load();
                    },
                    failure: function(response, opts) {
                        Ext.Msg.alert('提示', '编辑失败！');
                    }
                });
                win.close();
            }
        }
    },
    editCodeItems: function() {
        var view = Ext.getCmp('CodeList');
        var data = view.getSelectionModel().getSelection();
        var win = Ext.widget('CodeUpdate');
        win.setTitle('编辑Code');
        var modeText = win.down('textfield[name="modeText"]');
        var moduleText = win.down('textfield[name="moduleText"]');
        var categoryText = win.down('textfield[name="categoryText"]');
        win.down('combobox[name="addMode"]').setVisible(false);
        win.down('combobox[name="module"]').setVisible(false);
        win.down('combobox[name="category"]').setVisible(false);
        modeText.setVisible(true);
        moduleText.setVisible(true);
        categoryText.setVisible(true);
        if (this.addMode == '1') {
            modeText.setValue('模块');
            moduleText.setValue('');
            moduleText.setDisabled(true);
            categoryText.setValue('');
            categoryText.setDisabled(true);
        } else if (this.addMode == '2') {
            modeText.setValue('类别');
            moduleText.setDisabled(false);
            moduleText.setValue(data[0].get('category'));
            categoryText.setValue('');
            categoryText.setDisabled(true);
        } else if (this.addMode == '3') {
            modeText.setValue('数据项');
            var code = data[0].get('code').toString().substr(0, 4);
            moduleText.setDisabled(false);
            categoryText.setDisabled(false);
            categoryText.setValue(data[0].get('category'));
            Ext.Ajax.request({
                url: 'getACode.action',
                params: {
                    code: code
                },
                method: 'POST',
                success: function(response, opts) {
                    if (CRM.checkResponse(response)) {
                        return;
                    }
                    moduleText.setValue(Ext.JSON.decode(response.responseText).value);
                }
            });
        }
        win.down('form').loadRecord(data[0]);
    },

    changeMode: function(combobox) {
        var panel;
        var mode;
        if (combobox.up('window') == null) {
            panel = combobox.up('grid');
            mode = panel.down('combobox[name="mode"]');
        } else {
            panel = combobox.up('window');
            mode = panel.down('combobox[name="addMode"]');
        }
        var module = panel.down('combobox[name="module"]');
        var category = panel.down('combobox[name="category"]');
        if (mode.getValue() == '1') {
            module.setValue('');
            category.setValue('');
            category.clearInvalid();
            module.setDisabled(true);
            category.setDisabled(true);
            module.clearInvalid();
        } else if (mode.getValue() == '2') {
            category.setValue('');
            module.setDisabled(false);
            module.clearInvalid();
            category.clearInvalid();
            category.setDisabled(true);
        } else {
            module.setDisabled(false);
            category.clearInvalid();
            module.clearInvalid();
            category.setDisabled(false);
        }
    },
    deleteCodeItems: function() {
        var view = Ext.getCmp('CodeList');
        var data = view.getSelectionModel().getSelection();
        var code = '';
        if (data.length == 0) {
            Ext.Msg.alert('提示', '请选择想要删除的数据！');
        } else {
            box.confirm('提示', '你确定要删除这些数据吗？', function(button, text) {
                if (button == 'yes') {
                    for ( var i = 0; i < data.length; i++) {
                        code = code + data[i].get('code') + ',';
                    }
                    Ext.Ajax.request({
                        url: 'deleteCode.action',
                        params: {
                            code: code
                        },
                        method: 'POST',
                        success: function(response, opts) {
                            if (CRM.checkResponse(response)) {
                                return;
                            }
                            if (Ext.JSON.decode(response.responseText).msg == 'used') {
                                Ext.Msg.alert('提示', '正在使用，不可删除！');
                            } else if (Ext.JSON.decode(response.responseText).msg == 'nodelete') {
                                Ext.Msg.alert('提示', '该Code不可删除！');
                            } else {
                                view.getStore().load();
                            }
                        },
                        failure: function(response, opts) {
                            Ext.Msg.alert('提示', '删除失败！');
                        }
                    });
                }
            });
        }
    },
    deleteFlag: 1,
    editFlag: 1,
    addMode: '0',
    addModule: '',
    addCategory: ''
});
