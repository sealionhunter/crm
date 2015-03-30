Ext.define('CRM.view.statistics.salesStatistics.SalesCountShow', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.salesCountShow',
    id: 'salesCountShow',
    layout: 'fit',
    width: 800,
    height: 440,
    hidden: false,
    initComponent: function() {
        this.items = [ {
            id: 'chartPanel',
            xtype: 'panel',
            layout: 'column',
            items: [ {
                xtype: 'chart',
                id: 'chart',
                columnWidth: .7,
                height: 430,
                animate: true,
                store: 'statistics.SalesStatistics',
                axes: [ {
                    type: 'Numeric',
                    position: 'top',
                    fields: [ 'rightCount', 'leftCount' ],
                    hidden: true
                }, {
                    type: 'Category',
                    position: 'left',
                    fields: [ 'state' ],
                    title: '事件阶段',
                    hidden: true
                } ],
                series: [ {
                    type: 'bar',
                    axis: 'top',
                    stacked: true,
                    label: {
                        display: 'insideEnd',
                        field: 'rightCount',
                        renderer: function(v) {
                            return (v - 1) + '个';
                        },
                        orientation: 'horizontal',
                        color: '#333',
                        'text-anchor': 'middle'
                    },
                    xField: 'state',
                    yField: [ 'rightCount', 'leftCount' ],
                    renderer: this.rendererFun,
                    tips: {
                        trackMouse: true,
                        height: 25,
                        maxWidth: 700,
                        listeners: {
                            titlechange: function() {
                                this.setWidth((this.title.length) * 12);
                            }
                        },
                        renderer: function(storeItem, item) {
                            if (item.value[1] >= 0) {
                                this.setTitle(storeItem.get('state') + ": " + String(item.value[1] - 1) + '个');
                            } else {
                                this.setTitle(storeItem.get('state') + ": " + String((-item.value[1] - 1)) + '个');
                            }
                        }
                    }
                } ]
            }, {
                id: 'gridPanel',
                xtype: 'grid',
                margin: '30 10 0 0',
                columnWidth: .3,
                store: 'statistics.SalesStatisticsGrid',
                sortableColumns: false,
                disableSelection: true,
                columns: [ {
                    header: '事件阶段',
                    dataIndex: 'state',
                    minWidth: 80,
                    flex: 1,
                    menuDisabled: true
                }, {
                    header: '机会个数',
                    dataIndex: 'count',
                    minWidth: 80,
                    menuDisabled: true,
                    flex: 1,
                    renderer: function(v, meta, record, store) {
                        return record.get('count');
                    }
                }, {
                    header: '命中率',
                    menuDisabled: true,
                    dataIndex: '',
                    flex: 1,
                    renderer: function(v, meta, record, store) {
                        var value = (record.get('count')) / record.get('allCount') * 100;
                        Ext.util.Format.thousandSeparator = ',';
                        Ext.util.Format.decimalSeparator = '.';
                        return Ext.util.Format.number(value, '0.00') + '%';
                    }
                }, {
                    header: '',
                    width: 30,
                    menuDisabled: true,
                    renderer: function(value, meta, record, rowIndex, store) {
                        var count = Ext.getCmp('gridPanel').getStore().getCount();
                        switch (15 - count + rowIndex) {
                        case 0:
                            meta.tdCls = 'style1';
                            break;
                        case 1:
                            meta.tdCls = 'style2';
                            break;
                        case 2:
                            meta.tdCls = 'style3';
                            break;
                        case 3:
                            meta.tdCls = 'style4';
                            break;
                        case 4:
                            meta.tdCls = 'style5';
                            break;
                        case 5:
                            meta.tdCls = 'style6';
                            break;
                        case 6:
                            meta.tdCls = 'style7';
                            break;
                        case 7:
                            meta.tdCls = 'style8';
                            break;
                        case 8:
                            meta.tdCls = 'style9';
                            break;
                        case 9:
                            meta.tdCls = 'style10';
                            break;
                        case 10:
                            meta.tdCls = 'style11';
                            break;
                        case 11:
                            meta.tdCls = 'style12';
                            break;
                        case 12:
                            meta.tdCls = 'style13';
                            break;
                        case 13:
                            meta.tdCls = 'style14';
                            break;
                        case 14:
                            meta.tdCls = 'style15';
                            break;
                        }
                        return value;
                    }
                } ]
            } ]
        } ];
        this.callParent(arguments);
    },
    rendererFun: function(sprite, record, attr, index, store) {
        var level = store.getCount();
        var height = 400 / level;
        var value = Math.floor(index / 2);
        var color = [
                'rgb(218, 200, 120)',
                'rgb(213, 70, 121)',
                'rgb(44, 153, 201)',
                'rgb(146, 6, 157)',
                'rgb(49, 149, 0)',
                'rgb(249, 153, 0)',
                'rgb(0, 0, 255)',
                'rgb(220, 20, 60)',
                'rgb(100, 149, 237)',
                'rgb(165, 42, 42)',
                'rgb(147, 169, 208)',
                'rgb(114, 19, 15)',
                'rgb(92, 48, 71)',
                'rgb(81, 114, 179)',
                'rgb(200, 168, 37)' ][value];
        return Ext.apply(attr, {
            'fill': color,
            height: height
        });
    }
});
