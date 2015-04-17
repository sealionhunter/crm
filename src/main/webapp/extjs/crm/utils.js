var aWeek = [ '星期天', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ];
// 定期更新时间
Ext.TaskMgr = new Ext.util.TaskRunner();
Ext.TaskMgr.start({
    run: function() {
        var clock = Ext.getCmp('tbtext_clock');
        if (typeof (clock) === 'undefined') {
            return;
        }
        clock.update('<font size="2.5">' + Ext.Date.format(new Date(), 'Y-m-d G:i:sA') + ' '
                + aWeek[new Date().getDay()] + '</font>');
    },
    interval: 1000
});
Ext.getDoc().on('keydown', function(e) {
    if (e.getKey() == 8 && e.getTarget().type == 'text' && !e.getTarget().readOnly) {

    } else if (e.getKey() == 8 && e.getTarget().type == 'textarea' && !e.getTarget().readOnly) {

    } else if (e.getKey() == 8 && e.getTarget().type == 'password' && !e.getTarget().readOnly) {

    } else if (e.getKey() == 8) {
        e.preventDefault();
    }
});
var redStar = ':<font color="red">*</font> ';
var box = Ext.create('Ext.window.MessageBox', {
    buttonText: {
        yes: '确定',
        no: '取消'
    }
});
var boxForCustomer = Ext.create('Ext.window.MessageBox', {
    buttonText: {
        yes: '确定',
        no: '忽略'
    }
});
var messageBox = Ext.create('Ext.window.MessageBox', {
    buttonText: {
        ok: '确定'
    }
});
var CRM = {};
CRM.Utils = function() {
};
CRM.Utils.prototype = {
    trimSpace: function(obj) {
        if (obj.getValue() !== null && Ext.isString(obj.getValue())) {
            obj.setValue(Ext.util.Format.trim(obj.getValue()));
        }
    },
    trimSpaceSearch: function(obj) {
        if (obj.getValue() != null && Ext.isString(obj.getValue())) {
            obj.setValue(Ext.util.Format.trim(obj.getValue()));
        }
        if (obj.alias === 'widget.textfield') {
            var jsonChange = obj.getValue();
            jsonChange = jsonChange.replace(new RegExp('\\[', "gm"), '\\[').replace(
                    new RegExp('{', "gm"), '\\{');
            obj.setValue(jsonChange);
        }
    },
    searchStoreInset: function(addStore, eachStore, component) {
        eachStore.each(function(record) {
            addStore.add(record);
        });
        addStore.insert(0, {
            code: '00',
            value: '- 不限 -'
        });
        component.reset();
    },
    /*
     * each the errorMessage and show in window. errorMessage : String error
     * message id : String component id
     */
    checkErrorMsg: function(errorMessage, id) {
        if (errorMessage) {
            Ext.getCmp(id).markInvalid(errorMessage);
        }
    },
    loadPageOne: function(store) {
        store.on('beforeload', function(store, options) {
            var new_params = {
                searchFlag: 0,
                userID: USER_ID
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.loadPage(1);
        store.currentPage = 1;
    },
    updateRecord: function(button, url, listString, flag) {
        var me = this;
        var win = button.up('window');
        var form = win.down('form');
        function setVal(fieldId, val) {
            if (fieldId != 'validate') {
                form.down('[name=' + fieldId + ']').markInvalid(val);
            }
        }
        if (form.getForm().isValid()) {
            var values = form.getValues();
            var record = form.getRecord() || '';
            var list = Ext.getCmp(listString) || '';
            var store = null;
            if (list != '') {
                store = list.getStore();
            }
            Ext.Ajax.request({
                url: url,
                params: {
                    jsonString: Ext.encode(values),
                    flag: flag
                },
                success: function(response) {
                    if (CRM.checkResponse(response)) {
                        return;
                    }
                    var responseText = Ext.decode(response.responseText) || '';
                    if (responseText.CusotmerNameRepeat) {
                        if (GROUP_ID == 1 || GROUP_ID == 2) {
                            boxForCustomer.confirm('提示', '【' + record.get('customerName')
                                    + '】和下列已存在的客户名称可能重复，请重新填写或者忽略该提示！'
                                    + responseText.CusotmerNameRepeat, function showResult(btn) {
                                if (btn === 'no') {
                                    me.updateRecord(button, 'updateCustomer.action', 'customerlist', false);
                                }
                            });
                        } else {
                            messageBox.alert('提示', '【' + record.get('customerName')
                                    + '】和下列已存在的客户名称可能重复，请重新填写或者咨询管理员添加！'
                                    + responseText.CusotmerNameRepeat);
                        }
                        return;
                    }
                    if (responseText.validate == false) {
                        Ext.iterate(responseText, setVal);
                        messageBox.alert('提示', '输入信息有误，请检查输入信息！');
                    } else {
                        if (record == '') {
                            Ext.crm.msg("添加成功!", "");
                        } else {
                            Ext.crm.msg("编辑成功!", "");
                        }
                        if (list != '') {
                            store.loadPage(store.currentPage);
                        }
                        win.close();
                        if (flag == 2) {
                            var teamFlag = responseText.flag || '';
                            if (teamFlag > 0) {
                                Ext.Ajax.request({
                                    url: 'setIsMailed.action',
                                    params: {
                                        jsonString: teamFlag
                                    }
                                });
                            }
                        }
                    }
                },
                failure: function(response) {
                    if (record == '') {
                        messageBox.alert('提示', '添加失败，请联系管理员！');
                    } else {
                        messageBox.alert('提示', '编辑失败，请联系管理员！');
                    }
                }
            });
        } else {
            messageBox.alert('提示', '输入信息有误，请检查输入信息！');
        }
    },
    updateOrderRecord: function(button, url, listString, orderID) {
        var win = button.up('window');
        var form = win.down('form');
        var gridStore = win.down('#buyProduct').store;
        var num = gridStore.getCount();
        var gridValue = [];
        Ext.each(gridStore.getRange(0, num), function(record) {
            gridValue.push(record.data);
        });
        function setVal(fieldId, val) {
            if (fieldId != 'validate') {
                form.down('[name=' + fieldId + ']').markInvalid(val);
            }
        }
        if (form.getForm().isValid()) {
            var values = form.getValues();
            var record = form.getRecord() || '';
            var list = Ext.getCmp(listString) || '';
            var store = null;
            if (list != '') {
                store = list.getStore();
            }
            Ext.Ajax.request({
                url: url,
                params: {
                    jsonString: Ext.encode(values),
                    valueString: Ext.encode(gridValue),
                    orderID: orderID,
                    userID: USER_ID
                },
                success: function(response) {
                    if (CRM.checkResponse(response)) {
                        return;
                    }
                    var responseText = Ext.decode(response.responseText) || '';
                    if (responseText.validate == false) {
                        Ext.iterate(responseText, setVal);
                        messageBox.alert('提示', '输入信息有误，请检查输入信息！');
                    } else {
                        if (record == '') {
                            Ext.crm.msg("添加成功!", "");
                        } else {
                            Ext.crm.msg("编辑成功!", "");
                        }
                        if (list != '') {
                            store.loadPage(store.currentPage);
                        }
                        win.close();
                    }
                },
                failure: function(response) {
                    if (record == '') {
                        messageBox.alert('提示', '添加失败，请联系管理员！');
                    } else {
                        messageBox.alert('提示', '编辑失败，请联系管理员！');
                    }
                }
            });
        } else {
            messageBox.alert('提示', '输入信息有误，请检查输入信息！');
        }
    },
    delRecords: function(grid, url, idString) {
        var store = grid.getStore();
        var checkRecord = grid.getSelectionModel().getSelection();
        var checkRecordIDs = new Array();
        for ( var i = 0; i < checkRecord.length; i++) {
            checkRecordIDs[i] = checkRecord[i].get(idString);
        }
        if (checkRecord.length != 0) {
            box.confirm('提示', '确定删除所选信息？', function showResult(button) {
                if (button == 'yes') {
                    Ext.Ajax.request({
                        url: url,
                        params: {
                            jsonString: checkRecordIDs
                        },
                        success: function(response) {
                            if (CRM.checkResponse(response)) {
                                return;
                            }
                            var currentPage = store.currentPage;
                            var pageSize = store.pageSize;
                            var total = store.totalCount - checkRecord.length;
                            if (total <= (currentPage - 1) * pageSize) {
                                currentPage = currentPage - 1;
                            }
                            if (total == 0) {
                                currentPage = 1;
                            }
                            Ext.crm.msg("删除成功!", "");
                            store.loadPage(currentPage);
                        },
                        failure: function(response) {
                            messageBox.alert('提示', '删除失败，请联系管理员！');
                        }
                    });
                }
            });
        }

    },
    delRecordsCheck: function(grid, url, idString) {
        var store = grid.getStore();
        var checkRecord = grid.getSelectionModel().getSelection();

        var unSortRecordIDs = new Array();
        var checkRecordIDs = new Array();
        for ( var i = 0; i < checkRecord.length; i++) {
            unSortRecordIDs[grid.getStore().indexOf(checkRecord[i])] = checkRecord[i].get(idString);
        }

        for ( var index in unSortRecordIDs) {
            if (index != undefined) {
                checkRecordIDs.push(unSortRecordIDs[index]);
            }
        }

        if (checkRecord.length != 0) {
            box.confirm('提示', '确定删除所选信息？', function showResult(button) {
                if (button == 'yes') {
                    Ext.Ajax.request({
                        url: url,
                        params: {
                            jsonString: checkRecordIDs
                        },
                        success: function(response) {
                            if (CRM.checkResponse(response)) {
                                return;
                            }
                            var errorMessage = Ext.JSON
                                    .decode(response.responseText).errorMessage
                                    || "";
                            if (errorMessage == "") {
                                var currentPage = store.currentPage;
                                var pageSize = store.pageSize;
                                var total = store.totalCount
                                        - checkRecord.length;
                                if (total <= (currentPage - 1) * pageSize) {
                                    currentPage = currentPage - 1;
                                }
                                if (total == 0) {
                                    currentPage = 1;
                                }
                                Ext.crm.msg("删除成功!", "");
                                store.loadPage(currentPage);
                            } else {
                                var errorMessage = Ext.JSON
                                        .decode(response.responseText).errorMessage;
                                messageBox.alert('错误', Ext.String
                                        .htmlEncode(errorMessage));
                            }
                        },
                        failure: function(response) {
                            messageBox.alert('提示', '删除失败，请联系管理员！');
                        }
                    });
                }
            });
        }
    },

    changeToOrder: function(grid, url, idString) {
        var store = grid.getStore();
        var checkRecord = grid.getSelectionModel().getSelection();
        var checkRecordIDs = new Array();
        for ( var i = 0; i < checkRecord.length; i++) {
            checkRecordIDs[i] = checkRecord[i].get(idString);
        }
        if (checkRecord.length != 0) {
            box.confirm('提示', '确定将所选生成正式订单？', function showResult(button) {
                if (button == 'yes') {
                    Ext.Ajax.request({
                        url: url,
                        params: {
                            jsonString: checkRecordIDs
                        },
                        success: function(response) {
                            if (CRM.checkResponse(response)) {
                                return;
                            }
                            var currentPage = store.currentPage;
                            var pageSize = store.pageSize;
                            var total = store.totalCount - checkRecord.length;
                            if (total <= (currentPage - 1) * pageSize) {
                                currentPage = currentPage - 1;
                            }
                            if (total == 0) {
                                currentPage = 1;
                            }
                            Ext.crm.msg("生成成功!", "");
                            store.loadPage(currentPage);
                        },
                        failure: function(response) {
                            messageBox.alert('提示', '生成失败，请联系管理员！');
                        }
                    });
                }
            });
        }
    },
    comboChangeSelect: function(combo, records, eOpts) {
        if (records[0].get('code') == '00' && records.length > 1) {
            combo.setValue(records[1].get('code'));
        } else {
            Ext.Array.each(records, function(record) {
                if (record.get('code') == '00') {
                    combo.setValue('00');
                    return false;
                }
            });
        }
    },
    resetRecord: function(button) {
        var form = button.up('window').down('form');
        var record = form.getRecord() || '';
        if (record == '') {
            setTimeout(function fn() {
                form.getForm().reset();
            }, 100);
        } else {
            form.loadRecord(record);
        }
    },
    winClose: function(button) {
        button.up('window').close();
    },
    createDoc: function(form, url, name) {
        if (form.getForm().isValid()) {
            // 提交到服务器操作
            form.getForm().doAction('submit', {
                url: url,
                method: 'post',
                params: {
                    "name": name
                },
                success: function(formc, action) {
                    if (CRM.checkResponse(action.response)) {
                        return;
                    }
                    window.location = encodeURI(action.result.filePath);
                    form.up('window').destroy();
                },
                failure: function() {
                    messageBox.alert('提示', '文件导出失败，请联系管理员！');
                }
            });
        }
    },
    query: function(button) {
        if (button.up('grid').down('form').isHidden()
                && button.up('toolbar').down('#searchText').isValid()
                || !button.up('grid').down('form').isHidden()
                && button.up('form').getForm().isValid()) {
            var store = button.up('grid').getStore();
            var searchFlag = 1;
            var jsonString = "";
            if (!button.up('grid').down('form').isHidden()) {
                searchFlag = 2;
                var form = button.up('form');
                var values = form.getValues();
                jsonString = Ext.encode(values);
            } else {
                var searchValue = button.up('toolbar').down('#searchText').getValue().toString();
                jsonString = '{searchText : ' + Ext.encode(searchValue) + '}';
            }
            store.on('beforeload', function(store, options) {
                var new_params = {
                    searchFlag: searchFlag,
                    jsonString: jsonString
                };
                Ext.apply(store.proxy.extraParams, new_params);
            });
            store.loadPage(1, {
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
    superQuery: function(button, paramName, paramValue) {
        if (button.up('grid').down('form').isHidden()
                && button.up('toolbar').down('#searchText').isValid()
                || !button.up('grid').down('form').isHidden()
                && button.up('form').getForm().isValid()) {
            var store = button.up('grid').getStore();
            var searchFlag = 1;
            var jsonString = "";
            if (!button.up('grid').down('form').isHidden()) {
                searchFlag = 2;
                var form = button.up('form');
                var values = form.getValues();
                jsonString = Ext.encode(values);
            } else {
                var searchValue = button.up('toolbar').down('#searchText').getValue();
                jsonString = '{"' + paramName + '": ' + paramValue + ',"searchText" : "'
                        + searchValue + '"}';
            }
            store.on('beforeload', function(store, options) {
                var new_params = {
                    searchFlag: searchFlag,
                    jsonString: jsonString
                };
                Ext.apply(store.proxy.extraParams, new_params);
            });
            store.loadPage(1, {
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
    openOrCloseSuperQueryBtn: function(button) {
        var toolBar = button.up('toolbar');
        var gridPanel = toolBar.up('gridpanel');
        var gridStore = gridPanel.getStore();
        var toolForm = gridPanel.down('form');
        if (toolForm.isHidden()) {
            toolBar.down('button[action="queryBtn"]').setDisabled(true);
            toolBar.down('#searchText').setDisabled(true);
            toolBar.down('#searchText').reset();
            toolForm.show();
            button.setText('关闭检索');
            gridPanel.update();
        } else {
            toolBar.down('button[action="queryBtn"]').setDisabled(false);
            toolBar.down('#searchText').setDisabled(false);
            toolForm.getForm().reset();
            toolForm.hide();
            button.setText('高级检索');
            gridPanel.update();
        }
        this.loadPageOne(gridStore);
    },
    changeAdvancedSearchShowBtnType: function(button, paramName, paramValue) {
        var toolBar = button.up('toolbar');
        var gridPanel = toolBar.up('gridpanel');
        var toolForm = gridPanel.down('form');
        if (toolForm.isHidden()) {
            toolBar.down('button[action="queryBtn"]').setDisabled(true);
            toolBar.down('#searchText').setDisabled(true);
            toolBar.down('#searchText').reset();
            toolForm.show();
            button.setText('关闭检索');
            gridPanel.update();
        } else {
            toolBar.down('button[action="queryBtn"]').setDisabled(false);
            toolBar.down('#searchText').setDisabled(false);
            toolForm.hide();
            button.setText('高级检索');
            gridPanel.update();
        }
        jsonString = '{"' + paramName + '": ' + paramValue + '}';
        var store = gridPanel.getStore();
        store.on('beforeload', function(store, options) {
            var new_params = {
                searchFlag: 0,
                jsonString: jsonString
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        this.loadPageOne(store);
    },
    hideSuperQuery: function(list) {
        var toolForm = list.down('form');
        var toolBar = list.down('toolbar');
        if (toolForm.isHidden()) {
            toolBar.down('#searchText').reset();
        } else {
            toolBar.down('button[action="queryBtn"]').setDisabled(false);
            toolBar.down('#searchText').setDisabled(false);
            toolForm.getForm().reset();
            toolForm.hide();
            toolBar.down('button[action="superQueryBtn"]').setText('高级检索');
            list.update();
        }
        var store = list.getStore();
        this.loadPageOne(store);
    },
    nodeCheckChange: function(node, checked) {
        if (!node.data.leaf) {
            node.expand();
            node.cascadeBy(function(node) {
                node.set('checked', checked);
            });
        }
        this.changeNodeParentType(node);
    },
    changeNodeParentType: function(node) {
        if (!node.isRoot()) {
            var parentNode = node.parentNode;
            var flag = 0;
            parentNode.eachChild(function(n) {
                if (n.data.checked) {
                    flag++;
                }
            });
            if (flag == parentNode.childNodes.length) {
                parentNode.set('checked', true);
            } else {
                parentNode.set('checked', false);
            }
            this.changeNodeParentType(parentNode);
        }
    },
    authorizationControl: function(treeId, list) {
        function setVal(id, index) {
            list.down('#' + id).show();
        }
        Ext.Ajax.request({
            url: 'getOperationAuthorization.action',
            params: {
                id: GROUP_ID,
                rootID: treeId
            },
            success: function(response, opts) {
                if (CRM.checkResponse(response)) {
                    return;
                }
                var responseText = Ext.decode(response.responseText) || '';
                actionIDs = responseText.actionIDs;
                Ext.each(actionIDs, setVal);
            },
            failure: function(response, opts) {
                messageBox.alert('提示', '加载权限出错，请联系管理员！');
            }
        });
    },
//    changeMessageText: function() {
//        var messageStore = Ext.create('CRM.store.index.MessageStore');
//        messageStore.load({
//            params: {
//                searchFlag: 3,
//                userID: USER_ID
//            },
//            callback: function() {
//                var messageTotal = messageStore.getTotalCount();
//                Ext.getCmp('message').setText(
//                        '<font size="2.5">' + '【' + messageTotal + '条未读消息】' + '</font>');
//            }
//        });
//    },
    onresize: function() {
        var messageList = Ext.getCmp('messagelist');
        if (typeof (messageList) != 'undefined') {
            setTimeout(function() {
                var messageBtn = Ext.getCmp('message');
                var xy = messageBtn.getPosition();
                var width = messageBtn.getWidth();
                var height = messageBtn.getHeight();
                x = xy[0] + width - 16;
                y = xy[1] + height;
                messageList.setPosition(x, y);
            }, 300);
        }
    },
    getTime: function() {
        var time;
        objD = new Date();
        var yy = objD.getYear();
        if (yy < 1900)
            yy = yy + 1900;
        var MM = objD.getMonth() + 1;
        if (MM < 10)
            MM = '0' + MM;
        var dd = objD.getDate();
        if (dd < 10)
            dd = '0' + dd;
        var hh = objD.getHours() + 1;
        if (hh < 10)
            hh = '0' + hh;
        time = yy + "-" + MM + "-" + dd + " " + hh + ":" + "00";
        return time;
    },
    compareTime: function(time1, time2) {
        var time11 = new Date(time1.replace(/\-/g, "\/"));
        var time22 = new Date(time2.replace(/\-/g, "\/"));
        if (time11 < time22) {
            return true;
        } else {
            return false;
        }
    },
    scrollershow: function(scroller) {
//        if (scroller && scroller.scrollEl) {
//            scroller.clearManagedListeners();
//            scroller.mon(scroller.scrollEl, 'scroll', scroller.onElScroll, scroller);
//        }
    },
    // Added for EXTJS 4.2 start 20150302
    removeAllChild: function(destroy, suppressEvents) {
        var cn = this.childNodes, n;

        while ((n = cn[0])) {
            this.removeChild(n, destroy, suppressEvents);
        }
        return this;
    },
    showOneTask: function(obj, componentID, workID) {
        obj.style.color = "purple";
        obj.style.cursor = "pointer";
        var record = Ext.getCmp(componentID).store.findRecord('workID', workID);
        var win = Ext.widget('taskform');
        win.setTitle('任务详细信息');
        win.down('form').loadRecord(record);
    },
    showContactTrackInfo:function(obj, componentID, contactID) {
        obj.style.color = "purple";
        obj.style.cursor = "pointer";
        var record = Ext.getCmp(componentID).store.findRecord('contactID', contactID);
        var win = Ext.widget('contacttrackinfo');
        win.setTitle('客户联系详细信息');
        win.down('form').loadRecord(record);
    },

    backToCustomer : function() {
        Ext.getCmp('centercard').getLayout().setActiveItem('customerinfo');
    },
    getDate : function (day) {
        var dd = new Date();
        day = parseInt(day, 10);
        if (!isNaN(day)) {
            dd.setDate(dd.getDate() - day);
        }
        return dd;
    },
    showMoreCusUpdatedStatus: function() {
        var win = Ext.create('CRM.view.index.CusUpdatedStatusListWin');
        var gird = win.getComponent('cusupdatedstatuslist');
        var store = gird.getStore();
        store.on('beforeload', function(store, options) {
            var new_params = {
                userID: USER_ID,
                jsonString: '{}',
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        store.loadPage(1, {
            scope: this,
            callback: function(records, operation, success) {
                if (store.getCount() == 0) {
                    messageBox.alert('提示', '没有客户更新信息！');
                }
            }
        });
        store.currentPage = 1;
        win.show();
    }
    // Added for EXTJS 4.2 end 20150302
};
var utils = new CRM.Utils();
var xPos = 0;
var yPos = 0;
window.document.onmousemove = function(evt) {
    evt = evt || window.event;
    if (evt.pageX) {
        xPos = evt.pageX;
        yPos = evt.pageY;
    } else {
        xPos = evt.clientX + document.documentElement.scrollLeft
                - document.documentElement.clientLeft;
        yPos = evt.clientY + document.documentElement.scrollTop
                - document.documentElement.clientTop;
    }
};
window.document.onclick = function() {
    var messageList = Ext.getCmp('messagelist');
    if (typeof (messageList) != 'undefined') {
        var messageBtn = Ext.getCmp('message');
        var messageBtnXY = messageBtn.getPosition();
        var messageBtnHeight = messageBtn.getHeight();
        var messageBtnWidth = messageBtn.getWidth();
        if (!((xPos >= messageBtnXY[0]) && (xPos <= messageBtnXY[0] + messageBtnWidth)
                && (yPos >= messageBtnXY[1]) && (yPos <= messageBtnXY[1] + messageBtnHeight))) {
            var messageListXY = messageList.getPosition();
            var messageListHeight = messageList.getHeight();
            if ((xPos < messageListXY[0]) || (yPos < messageListXY[1])
                    || (yPos > messageListXY[1] + messageListHeight)) {
                messageList.close();
            }
        }
    }
};

Ext.apply(Ext.form.field.VTypes, {
    dateRange: function(val, field) {
        var beginDate = null, beginDateCmp = null;
        var endDate = null, endDateCmp = null;
        var validStatus = true, date = field.parseDate(val);
        if (date) {
            if (field.dateRange) {
                if (!Ext.isEmpty(field.dateRange.begin)) {
                    beginDateCmp = Ext.getCmp(field.dateRange.begin);
                    beginDate = beginDateCmp.getValue();
                }
                if (!Ext.isEmpty(field.dateRange.end)) {
                    endDateCmp = Ext.getCmp(field.dateRange.end);
                    endDate = endDateCmp.getValue();
                }
            }
            if (!Ext.isEmpty(beginDate) && !Ext.isEmpty(endDate)) {
                validStatus = beginDate <= endDate;
                if (validStatus) {
//                    beginDateCmp.clearInvalid();
//                    endDateCmp.clearInvalid();
                }
            }
        }
        return validStatus;
    },
    dateRangeText: '开始日期不能大于结束日期，请重新选择！',
    numberRange: function(val, field) {
        var minNumber = null, minNumberCmp = null;
        var maxNumber = null, maxNumberCmp = null, validStatus = true;
        if (field.numberRange) {
            if (!Ext.isEmpty(field.numberRange.min)) {
                minNumberCmp = Ext.getCmp(field.numberRange.min);
                minNumber = minNumberCmp.getValue();
            }
            if (!Ext.isEmpty(field.numberRange.max)) {
                maxNumberCmp = Ext.getCmp(field.numberRange.max);
                maxNumber = maxNumberCmp.getValue();
            }
        }
        // if (!Ext.isEmpty(minNumber) && (maxNumber == null)) {
        // validStatus = false;
        // } else
        if (!Ext.isEmpty(minNumber) && !Ext.isEmpty(maxNumber)) {
            validStatus = minNumber <= maxNumber;
            if (validStatus) {
//                minNumberCmp.clearInvalid();
//                maxNumberCmp.clearInvalid();
            }
        }
        return validStatus;
    },
    numberRangeText: '合作方人月数不能大于总人月数，请重新填写！',
    /** phone check */
    phone: function(v) {
        return /(^(0\d{2,3}-)?(\d{7,8})$)|(^1[3-8][0-9]{9}$)/.test(v);
    },
    phoneText: '号码无效,座机格式：(010-)4444444，手机号码格式：13333333333',
    /** search check */
    search: function(v) {
        var check1 = !(/.*\[.*].*/.test(v));
        var check2 = !(/.*{.*}.*/.test(v));
        return check1 && check2;
    },
    searchText: '检索内容有误，"[]"、"{}"不能成对出现！',
    /** fax check */
    fax: function(v) {
        return /^(0\d{2,3}-)(\d{7,8})$/.test(v);
    },
    faxText: '传真格式：010-4444444',
    /** QQ check */
    qq: function(v) {
        return /^[1-9][0-9]{4,20}$/.test(v);
    },
    qqText: 'QQ格式错误！',
    integer: function(val, field) {
        var exp = /^([1-9]\d{0,7}|0)$/;
        return val.match(exp);
    },
    integerText: '您输入的必须为数字！',
    urlText: '格式如："http://www.example.com"',
    emailText: '格式如： "user@example.com"'
});

var st = Ext.data.SortTypes;
Ext.data.Types.DATETIMETYPE = {
    convert: function(v, data) {
        if (v != '' && v != null) {
            return Ext.Date.format(new Date(v.substr(0, 4), v.substr(5, 2) - 1, v.substr(8, 2), v
                    .substr(11, 2), v.substr(14, 2)), 'Y-m-d H:i');
        }
    },
    sortType: st.asDate,
    type: 'DateTimeType'
};

// session 失效时，store load 时 records 为null。
Ext.define('CRM.store.commonStore', {
    extend : 'Ext.data.Store',
    listeners : {
        load : function(store, records, successful, eOpts) {
            if (!records) {
                CRM.reload();
            }
        }
    },
    /**
     * @private
     * Called internally when a Proxy has completed a load request
     */
    onProxyLoad: function(operation) {
        var me = this,
            resultSet = operation.getResultSet(),
            records = operation.getRecords(),
            error = operation.getError(),
            successful = operation.wasSuccessful();

        if (me.isDestroyed) {
            return;
        }
        if (error && eval(error.status) >= 400) {
                Ext.get(Ext.query(".x-mask")).hide();
                Ext.get(Ext.query(".x-mask-msg")).hide();
                Ext.get(Ext.query(".x-css-shadow")).hide();
                messageBox.alert('提示', '系统异常！');
                return;
        }
        if (resultSet) {
            me.totalCount = resultSet.total;
        }

        // Loading should be set to false before loading the records.
        // loadRecords doesn't expose any hooks or events until refresh
        // and datachanged, so by that time loading should be false
        me.loading = false;
        if (successful) {
            me.loadRecords(records, operation);
        }

        if (me.hasListeners.load) {
            me.fireEvent('load', me, records, successful);
        }

        //TODO: deprecate this event, it should always have been 'load' instead. 'load' is now documented, 'read' is not.
        //People are definitely using this so can't deprecate safely until 2.x
        if (me.hasListeners.read) {
            me.fireEvent('read', me, records, successful);
        }

        //this is a callback that would have been passed to the 'read' function and is optional
        Ext.callback(operation.callback, operation.scope || me, [records, operation, successful]);
    }
});

// 判断session失效，失效时返回true，并跳到登录页面
CRM.checkResponse = function(response) {
    if (!response.getResponseHeader) {
        return false;
    }
    var sessionstatus = response.getResponseHeader('sessionstatus');
    if (sessionstatus && sessionstatus === "timeOut") {
        CRM.reload();
        return true;
    } else {
        return false;
    }
};

CRM.reload = function() {
    box.confirm('提示', '您长时间未操作，请重新登录！', function showResult(button) {
        if (button == 'yes') {
            window.document.location.reload();
        }
    });
};
