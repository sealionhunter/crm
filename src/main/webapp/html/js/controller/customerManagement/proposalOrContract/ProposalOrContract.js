Ext.define('CRM.controller.customerManagement.proposalOrContract.ProposalOrContract', {
    extend: 'Ext.app.Controller',
    stores: [ 'customerManagement.proposalOrContract.ContractStore', 'customerManagement.proposalOrContract.ContractTypeStore', 'customerManagement.proposalOrContract.ContractPayTypeStore',
            'customerManagement.proposalOrContract.ProposalStore', 'customerManagement.proposalOrContract.FileTemplateNameStore', 'customerManagement.proposalOrContract.ContractOrderStore' ],// 声明该控制层要用到的store
    views: [ 'customerManagement.proposalOrContract.ContractInfo', 'customerManagement.proposalOrContract.ContractList', 'customerManagement.proposalOrContract.ProposalInfo',
            'customerManagement.proposalOrContract.ProposalList', 'customerManagement.proposalOrContract.ContractUpdate', 'customerManagement.proposalOrContract.CreateContract',
            'customerManagement.proposalOrContract.ProposalUpdate', 'customerManagement.proposalOrContract.ProposalOrContractContent', 'customerManagement.proposalOrContract.ContractSelectType',
            'customerManagement.proposalOrContract.ContractDetail', 'customerManagement.proposalOrContract.ProposalDetail', 'customerManagement.proposalOrContract.ContractSelectOrder',
            'customerManagement.proposalOrContract.ContractReferenceInfo', 'customerManagement.proposalOrContract.ContractOrderList', 'customerManagement.proposalOrContract.ContractEdit' ],// 声明该控制层要用到的view
    init: function() {
        this.control({
            'proposallist': {
                itemdblclick: this.dbceditproposal,
                selectionchange: this.selectionchangeproposal
            },
            'textfield': {
                blur: utils.trimSpace
            },
            'textarea': {
                blur: utils.trimSpace
            },
            'proposallist button[action=queryBtn]': {
                click: this.searchProposal
            },
            'proposalorcontractcontent button[action= createcontentfile]': {
                click: this.saveContent
            },
            'proposalorcontractcontent button[action= closeContent]': {
                click: utils.winClose
            },
            'contractlist button[action=detail]': {
                click: this.showContent
            },
            'proposallist button[action=proposaldetail]': {
                click: this.showProposal
            },
            'proposallist button[action=proposaladd]': {
                click: this.addProposal
            },
            'proposallist button[action = deleteproposal]': {
                click: this.deleteProposal
            },
            'proposallist button[action=proposaledit]': {
                click: this.editProposal
            },
            'proposalupdate button[action = save]': {
                click: this.saveAddOrEditProposal
            },
            'proposalupdate button[action = resetProposal]': {
                click: utils.resetRecord
            },
            'proposalupdate button[action = cancelProposal]': {
                click: utils.winClose
            },
            'proposallist button[action = superQueryBtn]': {
                click: this.advancedSearchProposalOrContract
            },
            'proposallist textfield': {
                blur: utils.trimSpace
            },
            'proposalupdate form combo[name=fileTemplateName]': {
                select: this.selectProposalTemplate,
                focus: this.noneProposalTemplate,
                change: this.changeTemplate
            }
        });
    },

    bindingOrders: function(combo, newvalue, ordervalue, e) {
        if (combo.getValue() == 2) {
            if (typeof (Ext.getCmp('contractSelectOrder')) == 'undefined') {
                Ext.widget('contractSelectOrder');
            }
            Ext.getCmp('orderID').show();
        } else {
            Ext.getCmp('orderID').hide();
        }
    },

    addContractClose: function(button) {
        var selectType = Ext.getCmp('contractSelectType');
        if (typeof (selectType) != 'undefined') {
            selectType.close();
        }
        var createContract = Ext.getCmp('createContract');
        if (typeof (createContract) != 'undefined') {
            createContract.close();
        }
        Ext.getCmp('contractupdate').close();
    },

    createContractFirst: function(button) {
        Ext.getCmp('contractSelectType').show();
        button.up('window').hide();
    },

    contractSelectFirst: function(button) {
        Ext.getCmp('contractupdate').show();
        button.up('window').hide();
    },

    addContractSelectType: function(button) {
        var selectType = Ext.getCmp('contractSelectType');
        if (typeof (selectType) == 'undefined') {
            selectType = Ext.widget('contractSelectType');
            selectType.setTitle('选择模板');
        }
        selectType.show();
        button.up('window').hide();
    },

    addContractCreateContract: function(button) {
        var createContract = Ext.getCmp('createContract');
        if (typeof (createContract) == 'undefined') {
            createContract = Ext.widget('createContract');
            createContract.setTitle('生成合同');
        }
        createContract.show();
        button.up('window').hide();
    },

    createContract: function(button) {
        this.addContractClose(button);
    },

    viewInit: function(treeId, typeNum, str, typeId) {
        var proposalorcontractinfo = Ext.getCmp(str + 'info');
        if (typeof (proposalorcontractinfo) == 'undefined') {
            proposalorcontractinfo = Ext.widget(str + 'info');
        }
        Ext.getCmp('centercard').insert(1, proposalorcontractinfo);
        Ext.getCmp('centercard').getLayout().setActiveItem(str + 'info');
        var proposalorcontractlist = Ext.getCmp(str + 'list');
        utils.authorizationControl(treeId, proposalorcontractlist);
        Ext.getCmp(typeId).setValue(typeNum);
        var store = proposalorcontractlist.getStore();
        store.on('beforeload', function(store, options) {
            var type = 'type : ' + typeNum;
            var searchStr = '{' + type + '}';
            var searchBean = Ext.decode(searchStr);
            var new_params = {
                searchFlag: 0,
                jsonString: Ext.encode(searchBean)
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        utils.hideSuperQuery(proposalorcontractlist);
        return proposalorcontractinfo;
    },
    selectionchangecontract: function(sm, selections) {
        Ext.getCmp('deleteContract').setDisabled(selections.length == 0);
        Ext.getCmp('contractListEdit').setDisabled(selections.length != 1);
        Ext.getCmp('contentdetail').setDisabled(selections.length != 1);
        var detail = Ext.getCmp('contractdetail');
        if (selections.length == 1) {
            var record = Ext.getCmp('contractlist').getSelectionModel().getSelection()[0];
            detail.on('beforeshow', function() {
                detail.expand();
            });
            detail.show();
            detail.loadRecord(record);
        } else {
            detail.hide();
        }
    },
    selectionchangeproposal: function(sm, selections) {
        Ext.getCmp('deleteproposal').setDisabled(selections.length == 0);
        Ext.getCmp('proposalEdit').setDisabled(selections.length != 1);
        Ext.getCmp('proposal_detail').setDisabled(selections.length != 1);
        var detail = Ext.getCmp('proposaldetail');
        if (selections.length == 1) {
            var record = Ext.getCmp('proposallist').getSelectionModel().getSelection()[0];
            detail.on('beforeshow', function() {
                detail.expand();
            });
            detail.show();
            detail.loadRecord(record);
        } else {
            detail.hide();
        }
    },
    dbceditcontract: function(grid, record) {
        if (!Ext.getCmp('contractListEdit').isHidden()) {
            var contractEdit = Ext.widget('contractupdate');
            contractEdit.setTitle('编辑合同');
            Ext.getCmp('resetContract').setText('重置');
            contractEdit.down('form').loadRecord(record);
        }
    },
    dbceditproposal: function(grid, record) {
        if (!Ext.getCmp('proposalEdit').isHidden()) {
            var contractEdit = Ext.widget('proposalupdate');
            contractEdit.setTitle('编辑建议书');
            Ext.getCmp('resetProposal').setText('重置');
            contractEdit.down('form').loadRecord(record);
        }
    },
    editContract: function() {
        var rows = Ext.getCmp('contractlist').getSelectionModel().getSelection();
        var contractEdit = Ext.widget('contractupdate');
        contractEdit.setTitle('编辑合同');
        Ext.getCmp('resetContract').setText('重置');
        contractEdit.down('form').loadRecord(rows[0]);
    },
    editProposal: function() {
        var rows = Ext.getCmp('proposallist').getSelectionModel().getSelection();
        var proposalEdit = Ext.widget('proposalupdate');
        proposalEdit.setTitle('编辑建议书');
        Ext.getCmp('resetProposal').setText('重置');
        proposalEdit.down('form').loadRecord(rows[0]);
    },
    saveContent: function(button) {
        var form = button.up('window').down('form');
        var rows = Ext.getCmp('proposallist').getSelectionModel().getSelection();
        var name = "";
        if (rows.length > 0) {
            name = rows[0].get('proposalOrContractName');
        }
        // 日语系统中文路径乱码
        name = 'JYS';
        utils.createDoc(form, 'createDoc.action', name);
    },
    showContent: function() {
        var rows = Ext.getCmp('contractlist').getSelectionModel().getSelection();
        var contractContent = Ext.widget('proposalorcontractcontent');
        contractContent.down('form').loadRecord(rows[0]);
        contractContent.setTitle('合同导出');
    },
    showProposal: function() {
        var rows = Ext.getCmp('proposallist').getSelectionModel().getSelection();
        var proposalContent = Ext.widget('proposalorcontractcontent');
        proposalContent.down('form').loadRecord(rows[0]);
        proposalContent.setTitle('建议书导出');
    },
    addContract: function() {
        var contractAdd = Ext.widget('contractupdate');
        var typeValue = Ext.getCmp('contracttype').getValue();
        Ext.getCmp('contractupdate').down('hidden[name=type]').setValue(typeValue);
        contractAdd.setTitle('添加合同');
    },
    addProposal: function() {
        var proposalAdd = Ext.widget('proposalupdate');
        var typeValue = Ext.getCmp('proposaltype').getValue();
        Ext.getCmp('proposalupdate').down('hidden[name=type]').setValue(typeValue);
        proposalAdd.setTitle('添加建议书');
    },
    deleteContract: function() {
        var grid = Ext.getCmp('contractlist');
        utils.delRecords(grid, 'deleteProposalOrContract.action', 'proposalOrContractID');
    },
    deleteProposal: function() {
        var grid = Ext.getCmp('proposallist');
        utils.delRecordsCheck(grid, 'deleteProposalOrContract.action', 'proposalOrContractID');
    },
    saveAddOrEditProposal: function(button) {
        var flag = Ext.getCmp('proposalupdate').down('form').down('hidden[name=proposalOrContractID]').getValue();
        var proposalValue = button.up('form').down('htmleditor').getValue();
        if (!button.up('form').getForm().isValid()) {
            messageBox.alert('提示', '输入信息有误，请检查输入信息！');
        } else if (proposalValue == "\u200b" || proposalValue == "<br>" || proposalValue == "") {
            messageBox.alert('提示', '建议书内容不能为空！');
        } else {
            if (flag == 0) {
                Ext.getCmp('proposalAddDate').setValue(Ext.Date.format(new Date(), 'Y-m-d'));
            } else {
                Ext.getCmp('proposalEditDate').setValue(Ext.Date.format(new Date(), 'Y-m-d'));
            }
            if (Ext.getCmp('proposalcombox').getValue() == '-无-') {
                Ext.getCmp('proposalcombox').setValue('');
            }
            utils.updateRecord(button, 'addOrUpdateProposalOrContract.action', 'proposallist');
        }
    },
    advancedSearchProposalOrContract: function(button) {
        utils.openOrCloseSuperQueryBtn(button);
    },
    changeTemplate: function(combo, newValue, oldValue, eOpts) {
        if (combo.store.count() != 0) {
            combo.editable = false;
        } else {
            combo.editable = true;
        }
    },
    noneContractTemplate: function(combo, eOpts) {
        if (combo.store.count() == 0) {
            messageBox.alert("提示", "无模板存在，请跳过此项或返回添加模板！");
        } else {
            if (combo.getStore().getAt(0).get('fileTemplateName') != '-无-') {
                combo.store.insert(0, {
                    fileTemplateID: '0',
                    fileTemplateName: '-无-'
                });
            }
        }
    },
    noneProposalTemplate: function(combo, eOpts) {
        if (combo.store.count() == 0) {
            messageBox.alert("提示", "无模板存在，请跳过此项或返回添加模板！");
        } else {
            if (combo.getStore().getAt(0).get('fileTemplateName') != '-无-') {
                combo.store.insert(0, {
                    fileTemplateID: '0',
                    fileTemplateName: '-无-'
                });
            }
        }
    },
    selectContractTemplate: function(combo) {
        var index = combo.getStore().findBy(function(record, id) {
            return record.get('fileTemplateName') == combo.getValue();
        });
        if (combo.getValue() != '-无-') {
            Ext.getCmp('contractInfoForm').down('htmleditor').setValue(combo.getStore().getAt(index).get('fileTemplateValue'));
            Ext.getCmp('proposalOrContractType').setValue(combo.getValue());
        } else if (combo.getValue() == '-无-') {
            Ext.getCmp('contractInfoForm').down('htmleditor').setValue("");
            Ext.getCmp('proposalOrContractType').setValue("");
        }
    },
    selectProposalTemplate: function(combo) {
        var index = combo.getStore().findBy(function(record, id) {
            return record.get('fileTemplateName') == combo.getValue();
        });
        if (combo.getValue() != '-无-') {
            Ext.getCmp('proposalInfoForm').down('htmleditor').setValue(combo.getStore().getAt(index).get('fileTemplateValue'));
            Ext.getCmp('proposalType').setValue(combo.getValue());
        } else if (combo.getValue() == '-无-') {
            Ext.getCmp('proposalInfoForm').down('htmleditor').setValue("");
            Ext.getCmp('proposalType').setValue("");
        }
    },
    searchContract: function(button) {
        if ((Ext.getCmp('contractAdvanced').isHidden() && Ext.getCmp("search_text").isValid()) || (!Ext.getCmp('contractAdvanced').isHidden() && Ext.getCmp('contractAdvanced').getForm().isValid())) {
            var store = Ext.getCmp('contractlist').getStore();
            var textValue = 'textValue:Ext.getCmp("search_text").getValue()';
            var proposalOrContractName = 'proposalOrContractName:Ext.getCmp("proposalOrContractNameS").getValue(),';
            var proposalOrContractType = 'proposalOrContractType:Ext.getCmp("proposalOrContractTypeS").getValue(),';
            var proposalOrContractObject = 'proposalOrContractObject:Ext.getCmp("proposalOrContractObjectS").getValue(),';
            var proposalOrContractAddDateMin = 'proposalOrContractAddDateMin:Ext.getCmp("proposalOrContractAddDateMin").getSubmitValue(),';
            var proposalOrContractAddDateMax = 'proposalOrContractAddDateMax:Ext.getCmp("proposalOrContractAddDateMax").getSubmitValue(),';
            var proposalOrContractEditDateMin = 'proposalOrContractEditDateMin:Ext.getCmp("proposalOrContractEditDateMin").getSubmitValue(),';
            var proposalOrContractEditDateMax = 'proposalOrContractEditDateMax:Ext.getCmp("proposalOrContractEditDateMax").getSubmitValue(),';
            var type = 'type : Ext.getCmp("contracttype").getValue(),';
            var searchString = '{' + proposalOrContractName + proposalOrContractType + proposalOrContractObject + proposalOrContractAddDateMin + proposalOrContractEditDateMax
                    + proposalOrContractEditDateMin + proposalOrContractAddDateMax + type + textValue + '}';
            var proposalOrContractSearchBean = Ext.decode(searchString);
            var searchFlag = 1;
            if (!Ext.getCmp('contractAdvanced').isHidden()) {
                searchFlag = 2;
            }
            store.on('beforeload', function(store, options) {
                var new_params = {
                    searchFlag: searchFlag,
                    jsonString: Ext.encode(proposalOrContractSearchBean)
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
                        var nameError = text.nameError;
                        var typeError = text.typeError;
                        var searchTextError = text.searchTextError;
                        var objectError = text.objectError;
                        var addTimeError = text.addTimeError;
                        var editTimeError = text.editTimeError;
                        var editTimeMax = text.editTimeMax;
                        var editTimeMin = text.editTimeMin;
                        var addTimeMax = text.addTimeMax;
                        var addTimeMin = text.addTimeMin;
                        if (nameError != null) {
                            Ext.getCmp('proposalOrContractNameS').markInvalid(nameError);
                        }
                        if (typeError != null) {
                            Ext.getCmp('proposalOrContractTypeS').markInvalid(typeError);
                        }
                        if (searchTextError != null) {
                            Ext.getCmp('search_text').markInvalid(searchTextError);
                        }
                        if (objectError != null) {
                            Ext.getCmp('proposalOrContractObjectS').markInvalid(objectError);
                        }
                        if (editTimeMax != null) {
                            Ext.getCmp('proposalOrContractEditDateMax').markInvalid(editTimeMax);
                        }
                        if (editTimeMin != null) {
                            Ext.getCmp('proposalOrContractEditDateMin').markInvalid(editTimeMin);
                        }
                        if (addTimeMax != null) {
                            Ext.getCmp('proposalOrContractAddDateMax').markInvalid(addTimeMax);
                        }
                        if (addTimeMin != null) {
                            Ext.getCmp('proposalOrContractAddDateMin').markInvalid(addTimeMin);
                        }
                        if (editTimeMin != null) {
                            Ext.getCmp('proposalOrContractAddDateMin').markInvalid(editTimeMin);
                        }
                        if (editTimeError != null) {
                            Ext.getCmp('proposalOrContractEditDateMin').markInvalid(editTimeError);
                            Ext.getCmp('proposalOrContractEditDateMax').markInvalid(editTimeError);
                        }
                        if (addTimeError != null) {
                            Ext.getCmp('proposalOrContractAddDateMin').markInvalid(addTimeError);
                            Ext.getCmp('proposalOrContractAddDateMax').markInvalid(addTimeError);
                        }
                    } else {
                        messageBox.alert('提示', '检索出错！');
                    }
                    if (store.getCount() == 0) {
                        messageBox.alert("提示", "未检索到符合条件的数据！");
                    }
                }
            });
            store.currentPage = 1;
        } else {
            messageBox.alert("提示", "您搜索的值不符合规范，请重新输入！");
        }
    },
    searchProposal: function(button) {
        if ((Ext.getCmp('proposalAdvanced').isHidden() && Ext.getCmp("search_text_proposal").isValid())
                || (!Ext.getCmp('proposalAdvanced').isHidden() && Ext.getCmp('proposalAdvanced').getForm().isValid())) {
            var store = Ext.getCmp('proposallist').getStore();
            var textValue = 'textValue:Ext.getCmp("search_text_proposal").getValue()';
            var proposalOrContractName = 'proposalOrContractName:Ext.getCmp("proposalNameS").getValue(),';
            var proposalOrContractType = 'proposalOrContractType:Ext.getCmp("proposalTypeS").getValue(),';
            var proposalOrContractObject = 'proposalOrContractObject:Ext.getCmp("proposalObjectS").getValue(),';
            var proposalOrContractAddDateMin = 'proposalOrContractAddDateMin:Ext.getCmp("proposalAddDateMin").getSubmitValue(),';
            var proposalOrContractAddDateMax = 'proposalOrContractAddDateMax:Ext.getCmp("proposalAddDateMax").getSubmitValue(),';
            var proposalOrContractEditDateMin = 'proposalOrContractEditDateMin:Ext.getCmp("proposalEditDateMin").getSubmitValue(),';
            var proposalOrContractEditDateMax = 'proposalOrContractEditDateMax:Ext.getCmp("proposalEditDateMax").getSubmitValue(),';
            var type = 'type : Ext.getCmp("proposaltype").getValue(),';
            var searchString = '{' + proposalOrContractName + proposalOrContractType + proposalOrContractObject + proposalOrContractAddDateMin + proposalOrContractEditDateMax
                    + proposalOrContractEditDateMin + proposalOrContractAddDateMax + type + textValue + '}';
            var proposalOrContractSearchBean = Ext.decode(searchString);
            var searchFlag = 1;
            if (!Ext.getCmp('proposalAdvanced').isHidden()) {
                searchFlag = 2;
            }
            store.on('beforeload', function(store, options) {
                var new_params = {
                    searchFlag: searchFlag,
                    jsonString: Ext.encode(proposalOrContractSearchBean)
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
                        var nameError = text.nameError;
                        var typeError = text.typeError;
                        var searchTextError = text.searchTextError;
                        var objectError = text.objectError;
                        var addTimeError = text.addTimeError;
                        var editTimeError = text.editTimeError;
                        var editTimeMax = text.editTimeMax;
                        var editTimeMin = text.editTimeMin;
                        var addTimeMax = text.addTimeMax;
                        var addTimeMin = text.addTimeMin;
                        if (nameError != null) {
                            Ext.getCmp('proposalNameS').markInvalid(nameError);
                        }
                        if (typeError != null) {
                            Ext.getCmp('proposalTypeS').markInvalid(typeError);
                        }
                        if (searchTextError != null) {
                            Ext.getCmp('search_text_proposal').markInvalid(searchTextError);
                        }
                        if (objectError != null) {
                            Ext.getCmp('proposalObjectS').markInvalid(objectError);
                        }
                        if (editTimeMax != null) {
                            Ext.getCmp('proposalEditDateMax').markInvalid(editTimeMax);
                        }
                        if (editTimeMin != null) {
                            Ext.getCmp('proposalEditDateMin').markInvalid(editTimeMin);
                        }
                        if (addTimeMax != null) {
                            Ext.getCmp('proposalAddDateMax').markInvalid(addTimeMax);
                        }
                        if (addTimeMin != null) {
                            Ext.getCmp('proposalAddDateMin').markInvalid(addTimeMin);
                        }
                        if (editTimeMin != null) {
                            Ext.getCmp('proposalAddDateMin').markInvalid(editTimeMin);
                        }
                        if (editTimeError != null) {
                            Ext.getCmp('proposalEditDateMin').markInvalid(editTimeError);
                            Ext.getCmp('proposalEditDateMax').markInvalid(editTimeError);
                        }
                        if (addTimeError != null) {
                            Ext.getCmp('proposalAddDateMin').markInvalid(addTimeError);
                            Ext.getCmp('proposalAddDateMax').markInvalid(addTimeError);
                        }
                    } else {
                        messageBox.alert('提示', '检索出错！');
                    }
                    if (store.getCount() == 0) {
                        messageBox.alert("提示", "未检索到符合条件的数据！");
                    }
                }
            });
            store.currentPage = 1;
        } else {
            messageBox.alert("提示", "您搜索的值不符合规范，请重新输入！");
        }
    }
});