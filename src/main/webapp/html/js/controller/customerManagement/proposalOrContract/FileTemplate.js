Ext.define('CRM.controller.customerManagement.proposalOrContract.FileTemplate', {
    extend: 'Ext.app.Controller',
    stores: [ 'customerManagement.proposalOrContract.FileTemplateStore', 'customerManagement.proposalOrContract.ProposalTemplateStore' ],
    models: [],
    views: [ 'customerManagement.proposalOrContract.FileTemplateList', 'customerManagement.proposalOrContract.ProposalTemplateList',
            'customerManagement.proposalOrContract.FileExport', 'customerManagement.proposalOrContract.FileTemplateUpdate',
            'customerManagement.proposalOrContract.FileTemplateInfo', 'customerManagement.proposalOrContract.ProposalTemplateInfo',
            'customerManagement.proposalOrContract.ProposalTemplateBody', 'customerManagement.proposalOrContract.FileTemplateBody' ],
    init: function() {
        this.control({
            'filetemplatelist button[action=queryBtn]': {
                click: this.searchContractTemplate
            },
            'textfield': {
                blur: utils.trimSpace
            },
            'textarea': {
                blur: utils.trimSpace
            },
            'proposaltemplatelist button[action=queryBtn]': {
                click: this.searchProposalTemplate
            },
            'proposaltemplatelist button[action=proposalSuperQueryBtn]': {
                click: this.searchProposalTemplate
            },
            'filetemplatelist button[action=contractSuperQueryBtn]': {
                click: this.searchContractTemplate
            },
            'filetemplatelist textfield': {
                blur: utils.trimSpace
            },
            'proposaltemplatelist textfield': {
                blur: utils.trimSpace
            },
            'filetemplatelist': {
                itemdblclick: this.contractTemplateItemdbclick,
                selectionchange: this.contractTemplateSelectChange
            },
            'proposaltemplatelist': {
                itemdblclick: this.proposalTemplateItemdbclick,
                selectionchange: this.proposalTemplateSelectChange
            },
            'fileexport button[action=fileExport]': {
                click: this.fileExport
            },
            'fileexport button[action=closeFileExport]': {
                click: this.closeFileExport
            },
            'filetemplatelist button[action=contractTemplateCreateDoc]': {
                click: this.contractTemplateCreateDoc
            },
            'proposaltemplatelist button[action=proposalTemplateCreateDoc]': {
                click: this.proposalTemplateCreateDoc
            },
            'filetemplatelist button[action=add]': {
                click: this.addContractTemplate
            },
            'proposaltemplatelist button[action=add]': {
                click: this.addProposalTemplate
            },
            'filetemplatelist button[action=edit]': {
                click: this.editContractTemplate
            },
            'proposaltemplatelist button[action=edit]': {
                click: this.editProposalTemplate
            },
            'filetemplatelist button[action=delete]': {
                click: this.deleteContractTemplate
            },
            'proposaltemplatelist button[action=delete]': {
                click: this.deleteContractTemplate
            },
            'filetemplatelist button[action=superQueryBtn]': {
                click: this.advancedSearchContractTemplate
            },
            'proposaltemplatelist button[action=superQueryBtn]': {
                click: this.advancedSearchContractTemplate
            },
            'filetemplateupdate button[action=save]': {
                click: this.saveContractTemplate
            },
            'filetemplateupdate button[action=reset]': {
                click: utils.resetRecord
            },
            'filetemplateupdate button[action=cancel]': {
                click: this.cancelContractTemplateInfo
            },
            'filetemplateupdate textarea': {
                blur: utils.trimSpace
            },
            'filetemplateupdate textfield': {
                blur: utils.trimSpace
            }
        });
    },
    contractTemplateSelectChange: function(sm, selections) {
        Ext.getCmp('contractTemplateListDel').setDisabled(selections.length == 0);
        Ext.getCmp('contractTemplateListEdit').setDisabled(selections.length != 1);
        Ext.getCmp('contractTemplateCreateDoc').setDisabled(selections.length != 1);
        var detail = Ext.getCmp('filetemplateinfo');
        if (selections.length != 1) {
            if (!detail.isHidden()) {
                detail.hide();
            }
        } else {
            var record = Ext.getCmp('filetemplatelist').getSelectionModel().getSelection()[0];
            if (detail.isHidden()) {
                detail.show();
            }
            detail.loadRecord(record);
        }
    },
    proposalTemplateSelectChange: function(sm, selections) {
        Ext.getCmp('proposalTemplateListDel').setDisabled(selections.length == 0);
        Ext.getCmp('proposalTemplateListEdit').setDisabled(selections.length != 1);
        Ext.getCmp('proposalTemplateCreateDoc').setDisabled(selections.length != 1);
        var detail = Ext.getCmp('proposaltemplateinfo');
        if (selections.length != 1) {
            if (!detail.isHidden()) {
                detail.hide();
            }
        } else {
            var record = Ext.getCmp('proposaltemplatelist').getSelectionModel().getSelection()[0];
            if (detail.isHidden()) {
                detail.show();
            }
            detail.loadRecord(record);
        }
    },
    contractTemplateItemdbclick: function(grid, record) {
        var editButton = Ext.getCmp('contractTemplateListEdit');
        if (!editButton.isHidden()) {
            var contractTemplateEdit = Ext.widget('filetemplateupdate');
            contractTemplateEdit.down('button[action=reset]').setText('重置');
            contractTemplateEdit.setTitle('编辑合同模板');
            contractTemplateEdit.down('form').loadRecord(record);
        }
    },
    proposalTemplateItemdbclick: function(grid, record) {
        var editButton = Ext.getCmp('proposalTemplateListEdit');
        if (!editButton.isHidden()) {
            var proposalTemplateEdit = Ext.widget('filetemplateupdate');
            proposalTemplateEdit.down('button[action=reset]').setText('重置');
            proposalTemplateEdit.setTitle('编辑建议书模板');
            proposalTemplateEdit.down('form').loadRecord(record);
        }
    },
    fileExport: function(button) {
        var form = button.up('window').down('form');
        if (typeof (Ext.getCmp('proposaltemplatelist')) != 'undefined' && button.up('window').title == '建议书模板导出') {
            var proposalrows = Ext.getCmp('proposaltemplatelist').getSelectionModel().getSelection();
            var name = "";
            if (proposalrows.length > 0) {
                name = proposalrows[0].get('fileTemplateName');
            }
            // 日语系统中文路径乱码
            name = 'JYSMB';
            utils.createDoc(form, 'createFileTemplateDoc.action', name);
        } else if (typeof (Ext.getCmp('filetemplatelist')) != 'undefined') {
            var rows = Ext.getCmp('filetemplatelist').getSelectionModel().getSelection();
            var sname = "";
            if (rows.length > 0) {
                sname = rows[0].get('fileTemplateName');
            }
            // 日语系统中文路径乱码
            sname = 'HTMB';
            utils.createDoc(form, 'createFileTemplateDoc.action', sname);
        }
    },
    closeFileExport: function(button) {
        Ext.getCmp('fileexport').destroy();
    },
    contractTemplateCreateDoc: function() {
        var rows = Ext.getCmp('filetemplatelist').getSelectionModel().getSelection();
        var fileExport = Ext.widget('fileexport');
        fileExport.down('form').loadRecord(rows[0]);
        fileExport.setTitle('合同模板导出');
    },
    proposalTemplateCreateDoc: function() {
        var rows = Ext.getCmp('proposaltemplatelist').getSelectionModel().getSelection();
        var fileExport = Ext.widget('fileexport');
        fileExport.down('form').loadRecord(rows[0]);
        fileExport.setTitle('建议书模板导出');
    },
    addContractTemplate: function() {
        var contractTemplateAdd = Ext.widget('filetemplateupdate');
        contractTemplateAdd.setTitle('添加合同模板');
        Ext.getCmp("filetemplateupdate").down("hidden[name=type]").setValue(1);
    },
    addProposalTemplate: function() {
        var contractTemplateAdd = Ext.widget('filetemplateupdate');
        contractTemplateAdd.setTitle('添加建议书模板');
        Ext.getCmp("filetemplateupdate").down("hidden[name=type]").setValue(2);
    },
    editContractTemplate: function() {
        var rows = Ext.getCmp('filetemplatelist').getSelectionModel().getSelection();
        var contractTemplateEdit = Ext.widget('filetemplateupdate');
        contractTemplateEdit.setTitle('编辑合同模板');
        contractTemplateEdit.down('button[action=reset]').setText('重置');
        contractTemplateEdit.down('form').loadRecord(rows[0]);
    },
    editProposalTemplate: function() {
        var rows = Ext.getCmp('proposaltemplatelist').getSelectionModel().getSelection();
        var contractTemplateEdit = Ext.widget('filetemplateupdate');
        contractTemplateEdit.setTitle('编辑建议书模板');
        contractTemplateEdit.down('button[action=reset]').setText('重置');
        contractTemplateEdit.down('form').loadRecord(rows[0]);
    },
    deleteContractTemplate: function(button) {
        var grid = button.up('gridpanel');
        utils.delRecordsCheck(grid, 'deleteFileTemplate.action', 'fileTemplateID');
    },
    saveContractTemplate: function(button) {
        var flag = button.up('form').down('hidden[name=fileTemplateID]').getValue();
        var type = button.up('form').down('hidden[name=type]').getValue();
        var fileTemplateValue = button.up('form').down('htmleditor').getValue();
        if (!button.up('form').getForm().isValid()) {
            messageBox.alert('提示', '输入信息有误，请检查输入信息！');
        } else if (fileTemplateValue == "\u200b" || fileTemplateValue == "<br>" || fileTemplateValue == "") {
            messageBox.alert('提示', '模板内容不能为空！');
        } else {
            if (flag == 0) {
                Ext.getCmp('contractTemplateAddDate').setValue(Ext.Date.format(new Date(), 'Y-m-d'));
            } else {
                Ext.getCmp('contractTemplateEditDate').setValue(Ext.Date.format(new Date(), 'Y-m-d'));
            }
            if (type == 1) {
                utils.updateRecord(button, 'updateFileTemplate.action', 'filetemplatelist');
            } else {
                utils.updateRecord(button, 'updateFileTemplate.action', 'proposaltemplatelist');
            }
        }
    },
    cancelContractTemplateInfo: function() {
        var msg = '您确定取消' + Ext.getCmp('filetemplateupdate').title + '吗？';
        box.confirm('提示', msg, function(btn) {
            if (btn == 'yes') {
                Ext.getCmp('filetemplateupdate').destroy();
            }
        });
    },
    advancedSearchContractTemplate: function(button) {
        utils.openOrCloseSuperQueryBtn(button);
    },
    searchContractTemplate: function(button) {
        if ((Ext.getCmp('contractTemplateAdvanced').isHidden() && Ext.getCmp("contractTemplateQueryText").isValid())
                || (!Ext.getCmp('contractTemplateAdvanced').isHidden() && Ext.getCmp("contractTemplateAdvanced").getForm().isValid())) {
            var store = button.up('grid').getStore();
            var fileTemplateName = 'fileTemplateName:Ext.getCmp("contractTemplateNameS").getValue(),';
            var fileTemplateDescriptions = 'fileTemplateDescriptions:Ext.getCmp("contractTemplateDescriptionsS").getValue(),';
            var fileTemplateAddDateStart = 'fileTemplateAddDateStart:Ext.getCmp("contractTemplateAddDateStart").getSubmitValue(),';
            var fileTemplateAddDateEnd = 'fileTemplateAddDateEnd:Ext.getCmp("contractTemplateAddDateEnd").getSubmitValue(),';
            var fileTemplateEditDateStart = 'fileTemplateEditDateStart:Ext.getCmp("contractTemplateEditDateStart").getSubmitValue(),';
            var fileTemplateEditDateEnd = 'fileTemplateEditDateEnd:Ext.getCmp("contractTemplateEditDateEnd").getSubmitValue(),';
            var searchText = 'searchText : Ext.getCmp("contractTemplateQueryText").getValue(),';
            var type = 'type : 1';
            var searchStr = '{' + fileTemplateName + fileTemplateDescriptions + fileTemplateAddDateStart + fileTemplateAddDateEnd
                    + fileTemplateEditDateStart + fileTemplateEditDateEnd + searchText + type + '}';
            var searchBean = Ext.decode(searchStr);
            var searchFlag = 1;
            if (!Ext.getCmp('contractTemplateAdvanced').isHidden()) {
                searchFlag = 2;
            }
            store.on('beforeload', function(store, options) {
                var new_params = {
                    searchFlag: searchFlag,
                    jsonString: Ext.encode(searchBean)
                };
                Ext.apply(store.proxy.extraParams, new_params);
            });
            store.load({
                params: {
                    page: 1
                },
                callback: function(records, operation, success) {
                    if (success) {
                        var text = store.getProxy().getReader().rawData;
                        var contractTemplateNameError = text.fileTemplateNameError;
                        var descriptionsError = text.descriptionsError;
                        var searchTextError = text.searchTextError;
                        var addTimeError = text.addTimeError;
                        var addTimeStartError = text.addTimeStartError;
                        var addTimeEndError = text.addTimeEndError;
                        var editTimeError = text.editTimeError;
                        var editTimeStartError = text.editTimeStartError;
                        var editTimeEndError = text.editTimeEndError;
                        if (contractTemplateNameError != null) {
                            var contractTemplateNameTextfield = Ext.getCmp('contractTemplateNameS');
                            contractTemplateNameTextfield.markInvalid(contractTemplateNameError);
                        }
                        if (descriptionsError != null) {
                            var descriptionsTextfield = Ext.getCmp('contractTemplateDescriptionsS');
                            descriptionsTextfield.markInvalid(descriptionsError);
                        }
                        if (searchTextError != null) {
                            var searchTextfield = Ext.getCmp('contractTemplateQueryText');
                            searchTextfield.markInvalid(searchTextError);
                        }
                        if (addTimeError != null) {
                            var addTimefield = Ext.getCmp('contractTemplateAddDateStart');
                            addTimefield.markInvalid(addTimeError);
                        }
                        if (addTimeStartError != null) {
                            var addTimeStartfield = Ext.getCmp('contractTemplateAddDateStart');
                            addTimeStartfield.markInvalid(addTimeStartError);
                        }
                        if (addTimeEndError != null) {
                            var addTimeEndfield = Ext.getCmp('contractTemplateAddDateEnd');
                            addTimeEndfield.markInvalid(addTimeEndError);
                        }
                        if (editTimeError != null) {
                            var editTimefield = Ext.getCmp('contractTemplateEditDateStart');
                            editTimefield.markInvalid(editTimeError);
                        }
                        if (editTimeStartError != null) {
                            var editTimeStartfield = Ext.getCmp('contractTemplateEditDateStart');
                            editTimeStartfield.markInvalid(editTimeStartError);
                        }
                        if (editTimeEndError != null) {
                            var editTimeEndfield = Ext.getCmp('contractTemplateEditDateStart');
                            editTimeEndfield.markInvalid(editTimeEndError);
                        }
                    } else {
                        messageBox.alert('提示', '操作失败，请联系管理员');
                    }
                    if (store.getCount() == 0) {
                        messageBox.alert("提示", "未检索到符合条件的数据！");
                    }
                }
            });
            store.currentPage = 1;
        } else {
            messageBox.alert("检索错误", "您搜索的值不符合规范，请重新输入！");
        }
    },
    searchProposalTemplate: function(button) {
        if ((Ext.getCmp('proposalTemplateAdvanced').isHidden() && Ext.getCmp("proposalTemplateQueryText").isValid())
                || (!Ext.getCmp('proposalTemplateAdvanced').isHidden() && Ext.getCmp("proposalTemplateAdvanced").getForm().isValid())) {
            var store = button.up('grid').getStore();
            var fileTemplateName = 'fileTemplateName:Ext.getCmp("proposalTemplateNameS").getValue(),';
            var fileTemplateDescriptions = 'fileTemplateDescriptions:Ext.getCmp("proposalTemplateDescriptionsS").getValue(),';
            var fileTemplateAddDateStart = 'fileTemplateAddDateStart:Ext.getCmp("proposalTemplateAddDateStart").getSubmitValue(),';
            var fileTemplateAddDateEnd = 'fileTemplateAddDateEnd:Ext.getCmp("proposalTemplateAddDateEnd").getSubmitValue(),';
            var fileTemplateEditDateStart = 'fileTemplateEditDateStart:Ext.getCmp("proposalTemplateEditDateStart").getSubmitValue(),';
            var fileTemplateEditDateEnd = 'fileTemplateEditDateEnd:Ext.getCmp("proposalTemplateEditDateEnd").getSubmitValue(),';
            var searchText = 'searchText : Ext.getCmp("proposalTemplateQueryText").getValue(),';
            var type = 'type : 2';
            var searchStr = '{' + fileTemplateName + fileTemplateDescriptions + fileTemplateAddDateStart + fileTemplateAddDateEnd
                    + fileTemplateEditDateStart + fileTemplateEditDateEnd + searchText + type + '}';
            var searchBean = Ext.decode(searchStr);
            var searchFlag = 1;
            if (!Ext.getCmp('proposalTemplateAdvanced').isHidden()) {
                searchFlag = 2;
            }
            store.on('beforeload', function(store, options) {
                var new_params = {
                    searchFlag: searchFlag,
                    jsonString: Ext.encode(searchBean)
                };
                Ext.apply(store.proxy.extraParams, new_params);
            });
            store.load({
                params: {
                    page: 1
                },
                callback: function(records, operation, success) {
                    if (success) {
                        var text = store.getProxy().getReader().rawData;
                        var contractTemplateNameError = text.fileTemplateNameError;
                        var descriptionsError = text.descriptionsError;
                        var searchTextError = text.searchTextError;
                        var addTimeError = text.addTimeError;
                        var addTimeStartError = text.addTimeStartError;
                        var addTimeEndError = text.addTimeEndError;
                        var editTimeError = text.editTimeError;
                        var editTimeStartError = text.editTimeStartError;
                        var editTimeEndError = text.editTimeEndError;
                        if (contractTemplateNameError != null) {
                            var contractTemplateNameTextfield = Ext.getCmp('proposalTemplateNameS');
                            contractTemplateNameTextfield.markInvalid(contractTemplateNameError);
                        }
                        if (descriptionsError != null) {
                            var descriptionsTextfield = Ext.getCmp('proposalTemplateDescriptionsS');
                            descriptionsTextfield.markInvalid(descriptionsError);
                        }
                        if (searchTextError != null) {
                            var searchTextfield = Ext.getCmp('proposalTemplateQueryText');
                            searchTextfield.markInvalid(searchTextError);
                        }
                        if (addTimeError != null) {
                            var addTimefield = Ext.getCmp('proposalTemplateAddDateStart');
                            addTimefield.markInvalid(addTimeError);
                        }
                        if (addTimeStartError != null) {
                            var addTimeStartfield = Ext.getCmp('proposalTemplateAddDateStart');
                            addTimeStartfield.markInvalid(addTimeStartError);
                        }
                        if (addTimeEndError != null) {
                            var addTimeEndfield = Ext.getCmp('proposalTemplateAddDateEnd');
                            addTimeEndfield.markInvalid(addTimeEndError);
                        }
                        if (editTimeError != null) {
                            var editTimefield = Ext.getCmp('proposalTemplateEditDateStart');
                            editTimefield.markInvalid(editTimeError);
                        }
                        if (editTimeStartError != null) {
                            var editTimeStartfield = Ext.getCmp('proposalTemplateEditDateStart');
                            editTimeStartfield.markInvalid(editTimeStartError);
                        }
                        if (editTimeEndError != null) {
                            var editTimeEndfield = Ext.getCmp('proposalTemplateEditDateStart');
                            editTimeEndfield.markInvalid(editTimeEndError);
                        }
                    } else {
                        messageBox.alert('提示', '操作失败，请联系管理员');
                    }
                    if (store.getCount() == 0) {
                        messageBox.alert("提示", "未检索到符合条件的数据！");
                    }
                }
            });
            store.currentPage = 1;
        } else {
            messageBox.alert("检索错误", "您搜索的值不符合规范，请重新输入！");
        }
    },
    fileViewInit: function(treeId) {
        var filetemplatebody = Ext.getCmp('filetemplatebody');
        if (typeof (filetemplatebody) == 'undefined') {
            filetemplatebody = Ext.widget('filetemplatebody');
        }
        Ext.getCmp('centercard').insert(1, filetemplatebody);
        Ext.getCmp('centercard').getLayout().setActiveItem('filetemplatebody');
        var list = Ext.getCmp('filetemplatelist');
        utils.authorizationControl(treeId, list);
        var store = Ext.getCmp('filetemplatelist').getStore();
        store.on('beforeload', function(store, options) {
            var searchStr = '{type : 1}';
            var searchBean = Ext.decode(searchStr);
            var new_params = {
                searchFlag: 0,
                jsonString: Ext.encode(searchBean)
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        utils.hideSuperQuery(list);
        Ext.getCmp('filetemplateinfo').hide();
    },
    proposalViewInit: function(treeId) {
        var proposaltemplatebody = Ext.getCmp('proposaltemplatebody');
        if (typeof (proposaltemplatebody) == 'undefined') {
            proposaltemplatebody = Ext.widget('proposaltemplatebody');
        }
        Ext.getCmp('centercard').insert(1, proposaltemplatebody);
        Ext.getCmp('centercard').getLayout().setActiveItem('proposaltemplatebody');
        var list = Ext.getCmp('proposaltemplatelist');
        utils.authorizationControl(treeId, list);
        var store = Ext.getCmp('proposaltemplatelist').getStore();
        store.on('beforeload', function(store, options) {
            var searchStr = '{type : 2}';
            var searchBean = Ext.decode(searchStr);
            var new_params = {
                searchFlag: 0,
                jsonString: Ext.encode(searchBean)
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        utils.hideSuperQuery(list);
        Ext.getCmp('proposaltemplateinfo').hide();
    }
});