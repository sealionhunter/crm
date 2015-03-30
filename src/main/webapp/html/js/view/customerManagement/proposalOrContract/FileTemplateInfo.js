Ext.define('CRM.view.customerManagement.proposalOrContract.FileTemplateInfo', {
    extend: 'Ext.form.Panel',
    defaultType: 'displayfield',
    alias: 'widget.filetemplateinfo',
    itemId: 'filetemplateinfo',
    id: 'filetemplateinfo',
    collapsible: true,
    autoScroll: true,
    title: '合同模板详细信息',
    width: 300,
    height: 202,
    margins: '0 0 0 5',
    defaults: {
        x: 10,
        y: 10,
        labelWidth: 60,
        labelAlign: "left",
        width: 230
    },
    items: [ {
        fieldLabel: '模板编号 ',
        name: 'fileTemplateID'
    }, {
        fieldLabel: '模板名称',
        name: 'fileTemplateName',
        htmlEncode: true
    }, {
        fieldLabel: '添加时间',
        name: 'fileTemplateAddDate'
    }, {
        fieldLabel: '修改时间',
        name: 'fileTemplateEditDate'
    }, {
        fieldLabel: '模板描述',
        name: 'descriptions',
        htmlEncode: true
    } ]
});