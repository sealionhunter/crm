Ext.define('CRM.controller.customerManagement.proposalOrContract.Contract', {
    extend: 'Ext.app.Controller',
    stores: [ 'customerManagement.proposalOrContract.ContractStore', 'customerManagement.proposalOrContract.ContractTypeStore', 'customerManagement.proposalOrContract.ContractPayTypeStore',
            'customerManagement.proposalOrContract.FileTemplateNameStore', 'customerManagement.proposalOrContract.ContractOrderStore' ],
    views: [ 'customerManagement.proposalOrContract.ContractInfo', 'customerManagement.proposalOrContract.ContractList', 'customerManagement.proposalOrContract.ContractUpdate',
            'customerManagement.proposalOrContract.MouthField', 'customerManagement.proposalOrContract.CreateContract', 'customerManagement.proposalOrContract.ContractSelectType',
            'customerManagement.proposalOrContract.ContractDetail', 'customerManagement.proposalOrContract.ContractSelectOrder', 'customerManagement.proposalOrContract.ContractReferenceInfo',
            'customerManagement.proposalOrContract.ContractContent', 'customerManagement.proposalOrContract.ContractOrderList', 'customerManagement.proposalOrContract.ContractSelectOrder',
            'customerManagement.proposalOrContract.ContractEdit' ],
    model: [ 'customerManagement.proposalOrContract.ContractModel' ],
    init: function() {
        this.control({
            'contractlist': {
                itemdblclick: this.dbceditcontract,
                selectionchange: this.selectionchangecontract,
                scrollershow: utils.scrollershow
            },
            'textfield': {
                blur: utils.trimSpace
            },
            'textarea': {
                blur: utils.trimSpace
            },
            'contractlist button[action=queryBtn]': {
                click: this.searchContract
            },
            'contractcontent button[action= createcontentfile]': {
                click: this.saveContent
            },
            'contractcontent button[action= closeContent]': {
                click: utils.winClose
            },
            'contractlist button[action=daochu]': {
                click: this.showContent
            },
            'contractlist button[action=add]': {
                click: this.addContract
            },
            'contractlist button[action=delete]': {
                click: this.deleteContract
            },
            'contractlist mouthfield[id=payEndTimeMin]': {
                change: this.onOkClickMin
            },
            'contractlist mouthfield[id=payEndTimeMax]': {
                change: this.onOkClickMax
            },
            'contractlist button[action=edit]': {
                click: this.editContract
            },
            'contractlist button[action=superQueryBtn]': {
                click: this.advancedSearchContract
            },
            'contractlist textfield': {
                blur: utils.trimSpace
            },
            // 添加合同部分action,添加页面三个按钮
            'contractupdate': {
                beforeclose: this.beforeColseContractUpdate
            },
            'contractupdate button[action=bingOrder]': {
                click: this.bingOrder
            },
            'contractupdate button[action=cancleBingOrder]': {
                click: this.cancleBingOrder
            },
            'contractupdate button[action=createContract]': {
                click: this.addContractCreateContract
            },
            'contractupdate button[action=resetContract]': {
                click: utils.resetRecord
            },
            'contractupdate button[action=cancelContract]': {
                click: this.closeContractUpdate
            },
            'contractupdate textarea': {
                blur: utils.trimSpace
            },
            'contractupdate textfield': {
                blur: utils.trimSpace
            },
            // 选择订单编号
            'contractSelectOrder button[action=selectOrder]': {
                click: this.contractSelectOrderOk
            },
            'contractSelectOrder button[action=close]': {
                click: this.colseSelectOrder
            },
            'contractupdate combo[name=fileTemplateName]': {
                select: this.selectContractTemplate,
                focus: this.noneContractTemplate
            },
            // 生成合同页面四个按钮
            'createContract': {
                beforeclose: this.beforeCloseCreateContract
            },
            'createContract button[action=resetContract]': {
                click: utils.resetRecord
            },
            'createContract button[action=selectType]': {
                click: this.createContractFirst
            },
            'createContract button[action=cancelContract]': {
                click: this.closeCreateContract
            },
            'createContract button[action=createContract]': {
                click: this.createContract
            },
            'createContract checkbox[id=contractEvent]': {
                change: this.contractEventCheck
            },
            // 编辑合同窗口注册事件
            'contractedit button[action=bingOrder]': {
                click: this.bingOrder
            },
            'contractedit button[action=cancleBingOrder]': {
                click: this.cancleBingOrder
            },
            'contractedit button[action=save]': {
                click: this.saveContractEdit
            },
            'contractedit button[action=reset]': {
                click: this.resetContractEdit
            },
            'contractedit button[action=cancel]': {
                click: this.closeContractEdit
            },
            'contractedit combo[name=fileTemplateName]': {
                select: this.changeContractTemplate,
                focus: this.noneContractTemplate,
                change: this.selectContractTemplate
            }
        });
    },
    colseSelectOrder: function(buttun) {
        if (Ext.getCmp("orderID").getValue() == "") {
            Ext.getCmp('contractSelectOrder').close();
        } else {
            Ext.getCmp('contractSelectOrder').hide();
        }
    },
    beforeColseContractUpdate: function(buttun) {
        var contractOrderList = Ext.getCmp('contractSelectOrder');
        if (typeof (contractOrderList) != 'undefined') {
            contractOrderList.close();
        }
    },
    closeContractUpdate: function(buttun) {
        var contractOrderList = Ext.getCmp('contractSelectOrder');
        if (typeof (contractOrderList) != 'undefined') {
            contractOrderList.close();
        }
        var contractupdate = Ext.getCmp('contractupdate');
        if (typeof (contractupdate) != 'undefined') {
            contractupdate.close();
        }
    },

    beforeCloseCreateContract: function(buttun) {
        var contractOrderList = Ext.getCmp('contractSelectOrder');
        if (typeof (contractOrderList) != 'undefined') {
            contractOrderList.close();
        }
        var contractupdate = Ext.getCmp('contractupdate');
        if (typeof (contractupdate) != 'undefined') {
            contractupdate.close();
        }
        var selectType = Ext.getCmp('contractSelectType');
        if (typeof (selectType) != 'undefined') {
            selectType.close();
        }
    },

    closeCreateContract: function(buttun) {
        Ext.getCmp('createContract').close();
    },

    closeAllWindow: function() {
        var contractOrderList = Ext.getCmp('contractSelectOrder');
        if (typeof (contractOrderList) != 'undefined') {
            contractOrderList.close();
        }
        var contractupdate = Ext.getCmp('contractupdate');
        if (typeof (contractupdate) != 'undefined') {
            contractupdate.close();
        }
        var selectType = Ext.getCmp('contractSelectType');
        if (typeof (selectType) != 'undefined') {
            selectType.close();
        }
        var createContract = Ext.getCmp('createContract');
        if (typeof (createContract) != 'undefined') {
            createContract.close();
        }
    },

    resetContractEdit: function(button) {
        var rows = Ext.getCmp('contractlist').getSelectionModel().getSelection();
        var contractEdit = Ext.getCmp('contractEdit');
        contractEdit.down('form').loadRecord(rows[0]);
        if (Ext.getCmp('orderID').getValue() == "") {
            Ext.getCmp('zhongbiao').setDisabled(true);
        } else {
            var contractInfo = {
                orderID: Ext.getCmp('orderKey').getValue()
            };
            Ext.Ajax.request({
                url: 'getContractEvent.action',
                params: {
                    jsonString: Ext.encode(contractInfo)
                },
                success: function(resp, opts) {
                    if (CRM.checkResponse(resp)) {
                        return;
                    }
                    if (Ext.JSON.decode(resp.responseText).eventExit == 0) {
                        Ext.getCmp('zhongbiao').setDisabled(true);
                    } else {
                        Ext.getCmp('zhongbiao').setValue(Ext.JSON.decode(resp.responseText).eventValue);
                        Ext.getCmp('zhongbiao').setDisabled(false);
                    }
                }
            });
        }
    },

    closeContractEdit: function(button) {
        Ext.getCmp('contractEdit').close();
    },

    bingOrder: function(button) {
        var contractSelectOrder = Ext.getCmp('contractSelectOrder');
        var grid = Ext.getCmp('contractlist');
        var checkRecords = grid.getSelectionModel().getSelection();
        var orderKey = 0;
        if (button.up('window').id == 'contractEdit') {
            if (typeof (checkRecords) != 'undefined') {
                if (checkRecords.length > 0) {
                    orderKey = checkRecords[0].get('orderKey');
                }
            }
        }
        if (typeof (contractSelectOrder) == 'undefined') {
            contractSelectOrder = Ext.widget('contractSelectOrder');
            var store = Ext.getCmp('contractOrderList').getStore();
            store.on('beforeload', function(store, options) {
                var new_params = {
                    searchFlag: 0,
                    userID: USER_ID,
                    id: orderKey
                };
                Ext.apply(store.proxy.extraParams, new_params);
            });
            Ext.getCmp('contractOrderList').getStore().load({
                callback: function(records, operation, success) {
                    var coStore = Ext.getCmp('contractOrderList').getStore();
                    for ( var i = 0; i < coStore.getCount(); i++) {
                        if (coStore.getAt(i).get('id') == orderKey) {
                            Ext.getCmp('contractOrderList').getSelectionModel().select(i, true);
                            continue;
                        }
                    }
                }
            });
        }
        contractSelectOrder.show();
        Ext.getCmp('orderID').show();
    },

    cancleBingOrder: function(button) {
        Ext.getCmp('orderID').setValue("");
        Ext.getCmp('orderKey').setValue("");
        Ext.getCmp('contractSelectOrder').close();
        if (typeof (Ext.getCmp('zhongbiao')) != 'undefined') {
            Ext.getCmp('zhongbiao').setDisabled(true);
            Ext.getCmp('zhongbiao').setValue("");
        }
    },

    contractSelectOrderOk: function(button) {
        var grid = Ext.getCmp('contractOrderList');
        var checkRecords = grid.getSelectionModel().getSelection();
        if (checkRecords.length == 1) {
            Ext.getCmp('orderID').setValue(checkRecords[0].get("orderID"));
            Ext.getCmp('orderKey').setValue(checkRecords[0].get('id'));
            if (Ext.getCmp('orderID').getValue() == "") {
                Ext.getCmp('zhongbiao').setDisabled(true);
            } else {
                var contractInfo = {
                    orderID: Ext.getCmp('orderKey').getValue()
                };
                Ext.Ajax.request({
                    url: 'getContractEvent.action',
                    params: {
                        jsonString: Ext.encode(contractInfo)
                    },
                    success: function(resp, opts) {
                        if (CRM.checkResponse(resp)) {
                            return;
                        }
                        if (Ext.JSON.decode(resp.responseText).eventExit == 0) {
                            Ext.getCmp('zhongbiao').setDisabled(true);
                        } else {
                            Ext.getCmp('zhongbiao').setValue(Ext.JSON.decode(resp.responseText).eventValue);
                            Ext.getCmp('zhongbiao').setDisabled(false);
                        }
                    }
                });
            }
            Ext.getCmp('contractSelectOrder').hide();
        } else {
            Ext.MessageBox.alert("提示", "选择订单时，请选中一行");
        }
    },

    contractSelectOrderColse: function(button) {
    },

    createContractFirst: function(button) {
        Ext.getCmp('contractupdate').show();
        button.up('window').hide();
    },

    contractEventCheck: function(checkbox, newValue, oldValue, eOpts) {
        if (checkbox.getValue()) {
            Ext.getCmp('zhongbiao').show();
        } else {
            Ext.getCmp('zhongbiao').hide();
        }
    },
    addContractSelectType: function(button) {
        if (Ext.getCmp("contractInfoForm").getForm().isValid()) {
            var contractInfo = {
                contractName: Ext.getCmp('contractName').getValue()
            };
            Ext.Ajax.request({
                url: 'NameOrOrderIdIsExit.action',
                params: {
                    jsonString: Ext.encode(contractInfo)
                },
                success: function(resp, opts) {
                    if (CRM.checkResponse(resp)) {
                        return;
                    }
                    if (Ext.JSON.decode(resp.responseText).state != 'exit') {
                        button.up('window').hide();
                    } else {
                        messageBox.alert('提示', '合同名称已存在，请重新输入！');
                    }
                },
                failure: function(resp, opts) {
                    messageBox.alert('提示', '添加合同失败！');
                }
            });
        } else {
            messageBox.alert('提示', '输入信息有误，请确认输入信息！');
        }
    },

    addContractCreateContract: function(button) {
        if (Ext.getCmp("contractInfoForm").getForm().isValid()) {
            var contractInfo = {
                contractName: Ext.getCmp('contractName').getValue()
            };
            Ext.Ajax.request({
                url: 'NameOrOrderIdIsExit.action',
                params: {
                    jsonString: Ext.encode(contractInfo)
                },
                success: function(resp, opts) {
                    if (CRM.checkResponse(resp)) {
                        return;
                    }
                    if (Ext.JSON.decode(resp.responseText).state != 'exit') {
                        var createContract = Ext.getCmp('createContract');
                        if (typeof (createContract) == 'undefined') {
                            createContract = Ext.widget('createContract');
                            createContract.setTitle('生成合同');
                        }
                        if (Ext.getCmp('orderID').getValue() == "") {
                            Ext.getCmp('zhongbiao').setDisabled(true);
                        } else {
                            var contractInfo = {
                                orderID: Ext.getCmp('orderKey').getValue()
                            };
                            // 填充中标内容
                            Ext.Ajax.request({
                                url: 'getContractEvent.action',
                                params: {
                                    jsonString: Ext.encode(contractInfo)
                                },
                                success: function(resp, opts) {
                                    if (CRM.checkResponse(resp)) {
                                        return;
                                    }
                                    if (Ext.JSON.decode(resp.responseText).eventExit == 0) {
                                        Ext.getCmp('zhongbiao').setDisabled(true);
                                    } else {
                                        Ext.getCmp('zhongbiao').setValue(Ext.JSON.decode(resp.responseText).eventValue);
                                        Ext.getCmp('zhongbiao').setDisabled(false);
                                    }
                                }
                            });
                        }
                        createContract.show();
                        var combo = Ext.getCmp('contractcombo');
                        var index = combo.getStore().findBy(function(record, id) {
                            return record.get('fileTemplateName') == combo.getValue();
                        });
                        Ext.getCmp('refContractName').setValue(Ext.getCmp('contractName').getValue());
                        Ext.getCmp('refOrderID').setValue(Ext.getCmp('orderID').getValue());
                        Ext.getCmp('refPayDate').setValue(Ext.util.Format.date(Ext.getCmp('payDate').getValue(), 'Y-m'));
                        Ext.getCmp('refPayType').setValue(Ext.getCmp('payType').getRawValue());
                        // 合同提示信息
                        var grid = Ext.getCmp('contractOrderList');
                        if (typeof (grid) != 'undefined') {
                            var checkRecords = grid.getSelectionModel().getSelection();
                            if (checkRecords.length == 1) {
                                var ContractNoticeInfos = Ext.getCmp('contractReferenceInfo');
                                ContractNoticeInfos.loadRecord(checkRecords[0]);
                            }
                        }
                        if (combo.getValue() != '-无-' && typeof (combo.getValue()) != 'undefined') {
                            Ext.getCmp('contractValue').setValue(combo.getStore().getAt(index).get('fileTemplateValue'));
                        } else if (combo.getValue() == '-无-') {
                            Ext.getCmp('contractValue').setValue("");
                        }
                        button.up('window').hide();
                    } else {
                        messageBox.alert('提示', '合同名称已存在，请重新输入！');
                    }
                },
                failure: function(resp, opts) {
                    messageBox.alert('提示', '添加合同失败！');
                }
            });
        } else {
            messageBox.alert('提示', '输入信息有误，请确认输入信息！');
        }
    },

    createContract: function(button) {
        var contractValue = Ext.getCmp('contractValue').getValue();
        if (contractValue == "\u200b" || contractValue == "<br>" || contractValue == "") {
            messageBox.alert('提示', '合同内容不能为空！');
            return;
        }
        if (Ext.getCmp('contractValue').getValue().length > 10000) {
            messageBox.alert('提示', '合同内容长度不能超过10000字符！');
            return;
        }
        var templateName = Ext.getCmp('contractcombo').getRawValue();
        if (templateName == '-无-') {
            templateName = "";
        }
        var contractInfo = {
            contractName: Ext.getCmp('contractName').getValue(),
            contractValue: Ext.getCmp('contractValue').getValue(),
            orderID: Ext.getCmp('orderKey').getValue(),
            isAbolished: 0,
            payType: Ext.getCmp('payType').getValue(),
            payEndTime: Ext.util.Format.date(Ext.getCmp('payDate').getValue(), 'Y-m'),
            fileTemplateName: templateName
        };
        var url = "addOrUpdateContract.action";
        Ext.Ajax.request({
            url: url,
            params: {
                userID: USER_ID,
                jsonString: Ext.encode(contractInfo),
                demand: Ext.getCmp('zhongbiao').getValue()
            },
            success: function(response) {
                if (CRM.checkResponse(response)) {
                    return;
                }
                Ext.getCmp('createContract').close();
                Ext.getCmp('contractlist').getStore().load();
                Ext.crm.msg("生成合同成功！", "");
            },
            failure: function(response) {
                Ext.MessageBox.alert('提示', '生成失败');
            }
        });
    },

    saveContractEdit: function(button) {
        if (Ext.getCmp("proposalInfoForm").getForm().isValid()) {
            if (Ext.getCmp('contractValue').getValue() == "<br>") {
                Ext.MessageBox.alert('提示', '合同内容不能为空');
                return;
            }
            if (Ext.getCmp('contractValue').getValue().length > 10000) {
                Ext.MessageBox.alert('提示', '合同内容不能超过10000字符！');
                return;
            }
            var templateName = Ext.getCmp('fileTemplateName').getRawValue();
            if (templateName == '-无-') {
                templateName = "";
            }
            var contractInfo = {
                contractID: Ext.getCmp('contractID').getValue(),
                contractName: Ext.getCmp('contractName').getValue(),
                contractValue: Ext.getCmp('contractValue').getValue(),
                orderID: Ext.getCmp('orderKey').getValue(),
                isAbolished: 0,
                payType: Ext.getCmp('payType').getValue(),
                payEndTime: Ext.util.Format.date(Ext.getCmp('payEndTime').getValue(), 'Y-m'),
                fileTemplateName: templateName
            };
            var url = "addOrUpdateContract.action";
            Ext.Ajax.request({
                url: url,
                params: {
                    userID: USER_ID,
                    jsonString: Ext.encode(contractInfo),
                    demand: Ext.getCmp('zhongbiao').getValue()
                },
                success: function(response) {
                    if (CRM.checkResponse(response)) {
                        return;
                    }
                    Ext.getCmp('contractEdit').close();
                    Ext.getCmp('contractlist').getStore().load();
                    Ext.crm.msg('编辑合同成功！', '');
                },
                failure: function(response) {
                    Ext.MessageBox.alert('提示', '编辑失败！');
                }
            });
        } else {
            messageBox.alert('提示', '输入信息有误，请确认输入信息！');
        }
    },

    viewInit: function(treeId) {
        var contractinfo = Ext.getCmp('contractinfo');
        if (typeof (contractinfo) == 'undefined') {
            contractinfo = Ext.widget('contractinfo');
        }
        Ext.getCmp('centercard').insert(1, contractinfo);
        var contractlist = Ext.getCmp('contractlist');
        utils.hideSuperQuery(contractlist);
        Ext.getCmp('payTypeSearch').getStore().load({
            params: {
                code: '00080001'
            },
            callback: function(records, operation, success) {
                Ext.getCmp('payTypeSearch').getStore().insert(0, {
                    code: '0',
                    value: '- 不限 -'
                });
                Ext.getCmp('payTypeSearch').setValue('0');
                contractlist.getStore().load();
            }
        });
        Ext.getCmp('centercard').getLayout().setActiveItem('contractinfo');
        return contractinfo;
    },

    selectionchangecontract: function(sm, selections) {
        Ext.getCmp('deleteContract').setDisabled(selections.length == 0);
        Ext.getCmp('contractListEdit').setDisabled(selections.length != 1);
        Ext.getCmp('daochu').setDisabled(selections.length != 1);
        var detail = Ext.getCmp('contractdetail');
        if (selections.length == 1) {
            var record = Ext.getCmp('contractlist').getSelectionModel().getSelection()[0];
            detail.on('beforeshow', function() {
                detail.expand();
            });
            detail.show();
            detail.loadRecord(record);
            detail.down('[name=payType]').setValue(this.rendererPayType(detail.down('[name=payType]').getValue()));
        } else {
            detail.hide();
        }
    },

    rendererPayType: function(value) {
        var result = "";
        if (Ext.getCmp('payTypeSearch').getStore().getCount() > 0) {
            Ext.getCmp('payTypeSearch').getStore().each(function(r) {
                if (r.get('code') == value) {
                    result = r.get('value');
                    return;
                }
            });
        }
        return result;
    },

    dbceditcontract: function(sm, selections) {
        var contractEdit = Ext.widget('contractedit');
        contractEdit.down('form').loadRecord(selections);
        // 填充中标内容
        if (Ext.getCmp('orderID').getValue() == "") {
            Ext.getCmp('zhongbiao').setDisabled(true);
        } else {
            var contractInfo = {
                orderID: Ext.getCmp('orderKey').getValue()
            };
            Ext.Ajax.request({
                url: 'getContractEvent.action',
                params: {
                    jsonString: Ext.encode(contractInfo)
                },
                success: function(resp, opts) {
                    if (CRM.checkResponse(resp)) {
                        return;
                    }
                    if (Ext.JSON.decode(resp.responseText).eventExit == 0) {
                        Ext.getCmp('zhongbiao').setDisabled(true);
                    } else {
                        Ext.getCmp('zhongbiao').setValue(Ext.JSON.decode(resp.responseText).eventValue);
                        Ext.getCmp('zhongbiao').setDisabled(false);
                    }
                }
            });
        }
    },

    editContract: function() {
        var rows = Ext.getCmp('contractlist').getSelectionModel().getSelection();
        var contractEdit = Ext.widget('contractedit');
        contractEdit.down('form').loadRecord(rows[0]);
        // 填充中标内容
        if (Ext.getCmp('orderID').getValue() == "") {
            Ext.getCmp('zhongbiao').setDisabled(true);
        } else {
            var contractInfo = {
                orderID: Ext.getCmp('orderKey').getValue()
            };
            Ext.Ajax.request({
                url: 'getContractEvent.action',
                params: {
                    jsonString: Ext.encode(contractInfo)
                },
                success: function(resp, opts) {
                    if (CRM.checkResponse(resp)) {
                        return;
                    }
                    if (Ext.JSON.decode(resp.responseText).eventExit == 0) {
                        Ext.getCmp('zhongbiao').setDisabled(true);
                    } else {
                        Ext.getCmp('zhongbiao').setValue(Ext.JSON.decode(resp.responseText).eventValue);
                        Ext.getCmp('zhongbiao').setDisabled(false);
                    }
                }
            });
        }
    },

    saveContent: function(button) {
        var form = button.up('window').down('form');
        var rows = Ext.getCmp('contractlist').getSelectionModel().getSelection();
        var name = "";
        if (rows.length > 0) {
            name = rows[0].get('contractName');
        }
        // 日语系统中文路径乱码
        name = 'HT';
        utils.createDoc(form, 'createDoc.action', name);
    },

    showContent: function() {
        var rows = Ext.getCmp('contractlist').getSelectionModel().getSelection();
        var contractContent = Ext.widget('contractcontent');
        contractContent.down('form').loadRecord(rows[0]);
        contractContent.setTitle('合同导出');
    },

    addContract: function() {
        var contractAdd = Ext.widget('contractupdate');
        contractAdd.setTitle('添加合同');
    },

    deleteContract: function() {
        var grid = Ext.getCmp('contractlist');
        utils.delRecords(grid, 'deleteContract.action', 'contractID');
    },

    advancedSearchContract: function(button) {
        utils.openOrCloseSuperQueryBtn(button);
    },

    noneContractTemplate: function(combo, eOpts) {
        if (combo.store.count() == 0) {
            messageBox.alert("提示", "无模板存在，请跳过此项或返回添加模板！");
            return;
        } else {
            if (combo.getStore().getAt(0).get('fileTemplateName') != '-无-') {
                combo.store.insert(0, {
                    fileTemplateID: '0',
                    fileTemplateName: '-无-'
                });
            }
        }
    },

    changeContractTemplate: function(combo, eOpts) {
        var index = combo.getStore().findBy(function(record, id) {
            return record.get('fileTemplateName') == combo.getValue();
        });
        if (combo.getValue() != '-无-') {
            Ext.getCmp('contractValue').setValue(combo.getStore().getAt(index).get('fileTemplateValue'));
            Ext.getCmp('proposalOrContractType').setValue(combo.getValue());
        } else if (combo.getValue() == '-无-') {
            Ext.getCmp('contractValue').setValue("");
        }
    },

    selectContractTemplate: function(combo) {
        if (combo.store.count() != 0) {
            combo.editable = false;
        } else {
            combo.editable = true;
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
            var textValue = 'textValue:Ext.getCmp("search_text").getValue(),';
            var contractName = 'contractName:Ext.getCmp("contractNameSearch").getValue(),';
            var orderID = 'orderID:Ext.getCmp("orderIDSearch").getValue(),';
            var fileTemplateName = 'fileTemplateName:Ext.getCmp("fileTemplateNameSearch").getValue(),';
            var payEndTimeMin;
            if (Ext.getCmp("payEndTimeMin").getValue()) {
                payEndTimeMin = 'payEndTimeMin:Ext.getCmp("payEndTimeMin").getValue(),';
            } else {
                payEndTimeMin = 'payEndTimeMin:"",';
            }
            var payEndTimeMax;
            if (Ext.getCmp("payEndTimeMax").getValue()) {
                payEndTimeMax = 'payEndTimeMax:Ext.getCmp("payEndTimeMax").getValue(),';
            } else {
                payEndTimeMax = 'payEndTimeMax:"",';
            }
            var payType = 'payType:Ext.getCmp("payTypeSearch").getValue()';
            var searchString = '{' + contractName + orderID + fileTemplateName + payEndTimeMin + payEndTimeMax + textValue + payType + '}';
            var contractSearchBean = Ext.decode(searchString);
            var searchFlag = 1;
            if (!Ext.getCmp('contractAdvanced').isHidden()) {
                searchFlag = 2;
            }
            store.on('beforeload', function(store, options) {
                var new_params = {
                    searchFlag: searchFlag,
                    jsonString: Ext.encode(contractSearchBean)
                };
                Ext.apply(store.proxy.extraParams, new_params);
            });
            store.load({
                callback: function() {
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

    onOkClickMin: function(mouthMin, newValue, oldValue, eOpts) {
        var mouthMax = Ext.getCmp('payEndTimeMax').getValue();
        if (mouthMax != null && mouthMax < newValue) {
            messageBox.alert("提示", "付款截止日期输入错误，请重新输入！");
            mouthMin.setValue("");
        }
    },
    onOkClickMax: function(mouthMax, newValue, oldValue, eOpts) {
        var mouthMin = Ext.getCmp('payEndTimeMin').getValue();
        if (mouthMin != null && mouthMin > newValue) {
            messageBox.alert("提示", "付款截止日期输入错误，请重新输入！");
            mouthMax.setValue("");
        }
    }
});