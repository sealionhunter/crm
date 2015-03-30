Ext.define('CRM.view.customerManagement.order.BuyProduct', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.buyProduct',
    store: 'customerManagement.order.BuyProductStore',
    title: '产品列表',
    id: 'buyProduct',
    multiSelect: true,
    columnLines: true,
    initComponent: function() {
        var me = this;
        this.selModel = Ext.create('Ext.selection.CheckboxModel');
        this.columns = [ {
            text: '序号',
            width: 40,
            renderer: function(value, metadata, record, rowIndex) {
                var page = me.store.currentPage, pageSize = me.store.pageSize;
                return (page - 1) * pageSize + rowIndex + 1;
            }
        }, {
            header: 'id',
            dataIndex: 'id',
            hidden: true
        }, {
            header: '产品编号',
            dataIndex: 'productID',
            minWidth: 145,
            flex: 1.2
        }, {
            header: '产品名称',
            dataIndex: 'name',
            flex: 1
        }, {
            header: '标准价格',
            dataIndex: 'price',
            flex: 1
        }, {
            header: '折扣价格',
            dataIndex: 'discount',
            renderer: this.rendererMinPrice,
            flex: 1
        }, {
            header: '数量',
            dataIndex: 'productNumber',
            name: 'productNumber',
            renderer: this.rendererValue,
            flex: 1
        } ];
        this.dockedItems = [ {
            dock: 'top',
            xtype: 'toolbar',
            items: [ {
                xtype: 'tbfill'
            }, {
                xtype: 'button',
                text: '选择产品',
                action: 'selectProduct'
            }, {
                xtype: 'button',
                text: '删除',
                action: 'deleteProduct',
                id: 'deleteProductBtn',
                disabled: true
            } ]
        }, {
            dock: 'bottom',
            xtype: 'toolbar',
            style: "background-color:White; background-image:url();",
            items: [ {
                xtype: 'tbfill'
            }, {
                xtype: 'displayfield',
                id: 'totalPrice',
                labelWidth: 34,
                readOnly: true,
                width: 87,
                fieldLabel: '总价'
            }, {
                xtype: 'displayfield',
                labelSeparator: '',
                fieldLabel: '万元',
                labelWidth: 28
            } ]
        } ];
        this.callParent(arguments);
    },
    rendererValue: function(value, metadata, record, rowIndex) {
        var productNumId = 'productNumber' + record.get('id');
        var func = "javascript:";
        func += "var newProductNumber = document.getElementById('" + productNumId + "').value;";
        // 值进行验证
        func += "var reg = new RegExp('^[1-9][0-9]*$');";
        func += "if(!reg.test(newProductNumber)) {";
        func += "   Ext.MessageBox.alert('提示','产品数量输入有误！');";
        func += "    return;";
        func += "}";

        // 重新计算总价
        func += "var productStore = Ext.getCmp('buyProduct').getStore();";
        func += "var totalPrice = 0;";
        func += "for(var i=0;i<productStore.getCount();i++){";
        func += "  var id= productStore.getAt(i).get('id');";
        func += "  var productNum= document.getElementById('productNumber'+id).value;";
        func += "  var discount= document.getElementById('discount'+id).value;";
        func += "	totalPrice += parseFloat(productNum)*parseFloat(discount);";
        func += "	if(id == " + record.get('id') + "){";
        func += "	    productStore.getAt(i).set('productNumber',productNum);";
        func += "	    productStore.getAt(i).commit();";
        func += "	}";
        func += "};";
        func += "Ext.getCmp('totalPrice').setValue((totalPrice*0.0001).toFixed(2));";
        return '<input type="text" onblur="' + func + '" id ="' + productNumId + '" name="' + productNumId + '" value="' + value + '" style="width:68px;"/>';
    },
    rendererMinPrice: function(value, metadata, record, rowIndex) {
        var dicountId = 'discount' + record.get('id');
        var func = "javascript:";
        func += "var newDiscount = document.getElementById('" + dicountId + "').value;";
        // 值进行验证
        func += "var reg = new RegExp('^[1-9][0-9]*(\.[0-9]{1,2})?$');";
        func += "if(!reg.test(newDiscount)){";
        func += "    Ext.MessageBox.alert('提示','折扣价格输入有误！');";
        func += "    return;";
        func += "}";
        // 最低价格判断
        // 重新计算总价
        func += "var productStore = Ext.getCmp('buyProduct').getStore();";
        func += "var totalPrice = 0;";
        // 重新将discount写入store中
        func += "for(var i=0;i<productStore.getCount();i++){";
        func += "  var id= productStore.getAt(i).get('id');";
        func += "  var productNum= document.getElementById('productNumber'+id).value;";
        func += "  var discount= document.getElementById('discount'+id).value;";
        func += "	totalPrice += parseFloat(productNum)*parseFloat(discount);";
        func += "	if(id == " + record.get('id') + "){";
        func += "	    productStore.getAt(i).set('discount',parseFloat(discount));";
        func += "	    productStore.getAt(i).commit();";
        func += "	}";
        func += "};";
        func += "Ext.getCmp('totalPrice').setValue((totalPrice*0.0001).toFixed(2));";
        return '<input type="text" onblur="' + func + '" id="' + dicountId + '" name="' + dicountId + '" value="' + value + '" style="width:68px;"/>';
    }
});