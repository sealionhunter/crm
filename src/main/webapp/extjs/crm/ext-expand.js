Ext.define('CRM.view.PagingToolbar', {
    extend: 'Ext.PagingToolbar',
    alias: 'widget.crmpagetoolbar',
    displayInfo : true
});
Ext.define('CRM.view.index.DateTimeField', {
    extend: 'Ext.form.field.Date',
    alias: 'widget.datetimefield',
    initComponent: function() {
        this.format = 'Y-m-d' + ' ' + 'H:i:s';
        this.callParent();
    },
    // overwrite
    createPicker: function() {
        var me = this, format = Ext.String.format;

        return Ext.create('CRM.view.index.DateTimePicker', {
            ownerCt: me.ownerCt,
            renderTo: document.body,
            floating: true,
            hidden: true,
            focusOnShow: true,
            // Added for EXTJS 4.2 start 20150302
            width: 189,
            // Added for EXTJS 4.2 end 20150302
            minDate: me.minValue,
            maxDate: me.maxValue,
            disabledDatesRE: me.disabledDatesRE,
            disabledDatesText: me.disabledDatesText,
            disabledDays: me.disabledDays,
            disabledDaysText: me.disabledDaysText,
            format: me.format,
            showToday: me.showToday,
            startDay: me.startDay,
            minText: format(me.minText, me.formatDate(me.minValue)),
            maxText: format(me.maxText, me.formatDate(me.maxValue)),
            listeners: {
                scope: me,
                select: me.onSelect
            },
            keyNavConfig: {
                esc: function() {
                    me.collapse();
                }
            }
        });
    }
});
/**
 * 时间输入框, 三个整数框分别输入时,分,秒.
 * 
 * @author wangzilong update Ext - 4.1 2012/04/27
 */
Ext.define('CRM.view.index.DateTimePicker', {
    extend: 'Ext.picker.Date',
    alias: 'widget.datetimepicker',
    todayText: '现在',
    timeLabel: '时间',

    initComponent: function() {
        // keep time part for value
        var value = this.value || new Date();
        this.callParent();
        this.value = value;
    },
    onRender: function(container, position) {
        if (!this.timefield) {
            this.timefield = Ext.create('CRM.view.index.TimePicker', {
                fieldLabel: this.timeLabel,
                labelWidth: 40,
                value: Ext.Date.format(this.value, 'H:i:s')
            });
        }
        this.timefield.ownerCt = this;
        this.timefield.on('change', this.timeChange, this);
        this.callParent(arguments);

        var table = Ext.get(Ext.DomQuery.selectNode('table', this.el.dom));
        var tfEl = Ext.core.DomHelper.insertAfter(table, {
            tag: 'div',
            style: 'border:0px;',
            children: [ {
                tag: 'div',
                cls: 'x-datepicker-footer ux-timefield'
            } ]
        }, true);
        this.timefield.render(this.el.child('div div.ux-timefield'));

        var p = this.getEl().parent('div.x-layer');
        if (p) {
            p.setStyle("height", p.getHeight() + 31);
        }
    },
    // listener 时间域修改, timefield change
    timeChange: function(tf, time, rawtime) {
        this.value = this.fillDateTime(this.value);
    },
    // @private
    fillDateTime: function(value) {
        if (this.timefield) {
            var rawtime = this.timefield.getRawValue();
            value.setHours(rawtime.h);
            value.setMinutes(rawtime.m);
            value.setSeconds(rawtime.s);
        }
        return value;
    },
    // @private
    changeTimeFiledValue: function(value) {
        this.timefield.un('change', this.timeChange, this);
        this.timefield.setValue(this.value);
        this.timefield.on('change', this.timeChange, this);
    },

    // overwrite
    setValue: function(value) {
        this.value = value;
        this.changeTimeFiledValue(value);
        return this.update(this.value);
    },
    // overwrite
    getValue: function() {
        return this.fillDateTime(this.value);
    },

    // overwrite : fill time before setValue
    handleDateClick: function(e, t) {
        var me = this, handler = me.handler;

        e.stopEvent();
        if (!me.disabled && t.dateValue && !Ext.fly(t.parentNode).hasCls(me.disabledCellCls)) {
            me.doCancelFocus = me.focusOnSelect === false;
            me.setValue(this.fillDateTime(new Date(t.dateValue))); // overwrite:
            // fill
            // time
            // before
            // setValue
            delete me.doCancelFocus;
            me.fireEvent('select', me, me.value);
            if (handler) {
                handler.call(me.scope || me, me, me.value);
            }
            me.onSelect();
        }
    },

    // overwrite : fill time before setValue
    selectToday: function() {
        var me = this, btn = me.todayBtn, handler = me.handler;

        if (btn && !btn.disabled) {
            // me.setValue(Ext.Date.clearTime(new Date())); //src
            me.setValue(new Date());// overwrite: fill time before
            // setValue
            me.fireEvent('select', me, me.value);
            if (handler) {
                handler.call(me.scope || me, me, me.value);
            }
            me.onSelect();
        }
        return me;
    }
});
Ext.define('CRM.view.index.TimePicker', {
    extend: 'Ext.form.field.Base',
    alias: 'widget.timepicker',

    // 隐藏BaseField的输入框 , hidden basefield's input
    inputType: 'hidden',

    style: 'padding:4px 0 0 0;margin-bottom:0px',

    /**
     * @cfg {String} value initValue, format: 'H:i:s'
     */
    value: null,

    /**
     * @cfg {Object} spinnerCfg 数字输入框参数, number input config
     */
    spinnerCfg: {
        width: 40
    },

    /** Override. */
    initComponent: function() {
        var me = this;

        me.value = me.value || Ext.Date.format(new Date(), 'H:i:s');

        me.callParent();// called setValue

        me.spinners = [];
        var cfg = Ext.apply({}, me.spinnerCfg, {
            readOnly: me.readOnly,
            disabled: me.disabled,
            style: 'float: left',
            listeners: {
                change: {
                    fn: me.onSpinnerChange,
                    scope: me
                }
            }
        });

        me.hoursSpinner = Ext.create('Ext.form.field.Number', Ext.apply({}, cfg, {
            minValue: 0,
            maxValue: 23
        }));
        me.minutesSpinner = Ext.create('Ext.form.field.Number', Ext.apply({}, cfg, {
            minValue: 0,
            maxValue: 59
        }));
        // TODO 使用timeformat 判断是否创建秒输入框, maybe second field is not always need.
        me.secondsSpinner = Ext.create('Ext.form.field.Number', Ext.apply({}, cfg, {
            minValue: 0,
            maxValue: 59
        }));

        me.spinners.push(me.hoursSpinner, me.minutesSpinner, me.secondsSpinner);

    },
    /**
     * @private Override.
     */
    onRender: function() {
        var me = this, spinnerWrapDom, spinnerWrap;
        me.callParent(arguments);

        // render to original BaseField input td
        // Modified for EXTJS 4.2 start 20150302
        // spinnerWrap = Ext.get(Ext.DomQuery.selectNode('div', this.el.dom)); // 4.0.2
        spinnerWrapDom = Ext.dom.Query.select('td', this.getEl().dom)[1]; // 4.0 ->4.1 div->td
        spinnerWrap = Ext.get(spinnerWrapDom);
        // Modified for EXTJS 4.2 end 20150302
        me.callSpinnersFunction('render', spinnerWrap);

        Ext.core.DomHelper.append(spinnerWrap, {
            tag: 'div',
            cls: 'x-form-clear-left'
        });

        this.setRawValue(this.value);
    },

    _valueSplit: function(v) {
        if (Ext.isDate(v)) {
            v = Ext.Date.format(v, 'H:i:s');
        }
        if (v) {
            var split = v.split(':');
            return {
                h: split.length > 0
                        ? split[0] : 0,
                m: split.length > 1
                        ? split[1] : 0,
                s: split.length > 2
                        ? split[2] : 0
            };
        } else {
            return {
                h: 0,
                m: 0,
                s: 0
            };
        }
    },
    onSpinnerChange: function() {
        if (!this.rendered) {
            return;
        }
        this.fireEvent('change', this, this.getValue(), this.getRawValue());
    },
    // 依次调用各输入框函数, call each spinner's function
    callSpinnersFunction: function(funName, args) {
        for ( var i = 0; i < this.spinners.length; i++) {
            this.spinners[i][funName](args);
        }
    },
    // @private get time as object,
    getRawValue: function() {
        if (!this.rendered) {
            var date = this.value || new Date();
            return this._valueSplit(date);
        } else {
            return {
                h: this.hoursSpinner.getValue(),
                m: this.minutesSpinner.getValue(),
                s: this.secondsSpinner.getValue()
            };
        }
    },

    // private
    setRawValue: function(value) {
        value = this._valueSplit(value);
        if (this.hoursSpinner) {
            this.hoursSpinner.setValue(value.h);
            this.minutesSpinner.setValue(value.m);
            this.secondsSpinner.setValue(value.s);
        }
    },
    // overwrite
    getValue: function() {
        var v = this.getRawValue();
        return Ext.String.leftPad(v.h, 2, '0') + ':' + Ext.String.leftPad(v.m, 2, '0') + ':'
                + Ext.String.leftPad(v.s, 2, '0');
    },
    // overwrite
    setValue: function(value) {
        this.value = Ext.isDate(value)
                ? Ext.Date.format(value, 'H:i:s') : value;
        if (!this.rendered) {
            return;
        }
        this.setRawValue(this.value);
        this.validate();
    },
    // overwrite
    disable: function() {
        this.callParent(arguments);
        this.callSpinnersFunction('disable', arguments);
    },
    // overwrite
    enable: function() {
        this.callParent(arguments);
        this.callSpinnersFunction('enable', arguments);
    },
    // overwrite
    setReadOnly: function() {
        this.callParent(arguments);
        this.callSpinnersFunction('setReadOnly', arguments);
    },
    // overwrite
    clearInvalid: function() {
        this.callParent(arguments);
        this.callSpinnersFunction('clearInvalid', arguments);
    },
    // overwrite
    isValid: function(preventMark) {
        return this.hoursSpinner.isValid(preventMark) && this.minutesSpinner.isValid(preventMark)
                && this.secondsSpinner.isValid(preventMark);
    },
    // overwrite
    validate: function() {
        return this.hoursSpinner.validate() && this.minutesSpinner.validate()
                && this.secondsSpinner.validate();
    }
});
// grid没有数据时总页数默认为0，该方法修改为1
Ext.override(Ext.toolbar.Paging, {
    getPageData: function() {
        var store = this.store, totalCount = store.getTotalCount(), page = Math.ceil(totalCount
                / store.pageSize);

        if (totalCount == 0) {
            page = 1;
        }

        return {
            total: totalCount,
            currentPage: store.currentPage,
            pageCount: page,
            fromRecord: ((store.currentPage - 1) * store.pageSize) + 1,
            toRecord: Math.min(store.currentPage * store.pageSize, totalCount)
        };
    }
});

// 更改加载时的悬浮框内容
Ext.override(Ext.LoadMask, {
    msg: '正在加载，请稍等...'
});

// 更改加载时的悬浮框内容
Ext.override(Ext.window.MessageBox, {
    titleText: {
        confirm: 'Confirm',
        prompt: 'Prompt',
        wait: '正在加载，请稍等...',
        alert: 'Attention'
    }
});

// 更改加载时的悬浮框内容
Ext.override(Ext.LoadMask, {
    defaultListConfig: {
        emptyText: '',
        loadingText: '正在加载，请稍等...',
        loadingHeight: 70,
        minWidth: 70,
        maxHeight: 300,
        shadow: 'sides'
//    },
//    onHide: function() {
//        this.callParent();
//        this.getMaskEl().hide('display');
//    },
//    listeners : {
//        hide : function(view) {
//            var mainView = view.up('mainView');
//            if (mainView) {
//                var scrollHeight = document.documentElement.scrollHeight,
//                scrollWidth = document.documentElement.scrollWidth,
//                mainView = view.up('mainView'),
//                minHeight = mainView.minHeight,
//                minWidth = mainView.minWidth,
//                height = mainView.height,
//                width = mainView.width;
//                if(minHeight > height || minWidth > width) {
//                    document.body.style.overflow = 'auto';
//                } else if(width >= scrollWidth && height === scrollHeight){
//                    document.body.style.overflow = 'hidden';
//                } else {
//                    document.body.style.overflow = 'auto';
//                }
//                mainView.doLayout();
//            }
//        }
    }
});

// 更改加载时的悬浮框内容
Ext.override(Ext.view.AbstractView, {
    loadingText: '正在加载，请稍等...'
});

// 合作伙伴分析详细信息页面使用，设置substr为true，格式化时间（截取0-16位字符串），
Ext.override(Ext.form.DisplayField, {
    substr: false,

    getValue: function() {
        return this.value;
    },
    setValue: function(v) {
        this.value = v;
        this.setRawValue(this.formatValue(v));
        return this;
    },
    formatValue: function(v) {
        if (this.substr) {
            return Ext.util.Format.substr(v, 0, 16);
        }
        return v;
    }
});
// 去除grid隐藏/显示不同列的功能
Ext.grid.header.Container.override({
    getMenuItems: function() {
        var me = this, menuItems = [];
        if (me.sortable) {
            menuItems = [ {
                itemId: 'ascItem',
                text: me.sortAscText,
                cls: Ext.baseCSSPrefix + 'hmenu-sort-asc',
                handler: me.onSortAscClick,
                scope: me
            }, {
                itemId: 'descItem',
                text: me.sortDescText,
                cls: Ext.baseCSSPrefix + 'hmenu-sort-desc',
                handler: me.onSortDescClick,
                scope: me
            } ];
        }
        return menuItems;
    }
});

/*
 * Overrides for fixing clearOnLoad for TreeStore
 */
Ext.override(Ext.data.TreeStore, {
    load: function(options) {
        options = options || {};
        options.params = options.params || {};

        var me = this, node = options.node || me.tree.getRootNode();

        // If there is not a node it means the user hasnt defined a
        // rootnode yet. In this case lets just
        // create one for them.
        if (!node) {
            node = me.setRootNode({
                expanded: true
            });
        }

        if (me.clearOnLoad) {
            node.removeAll(false);
        }

        Ext.applyIf(options, {
            node: node
        });
        options.params[me.nodeParam] = node
                ? node.getId() : 'root';

        if (node) {
            node.set('loading', true);
        }

        return me.callParent([ options ]);
    }
});