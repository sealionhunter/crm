Ext.define('CRM.controller.index.IndexController', {
    extend: 'Ext.app.Controller',
    views: [ 'index.WorkIndex', 'index.MyWorkingList', 'index.ImportantTask', 'index.InformTask', 'index.WorkUpdate', 'index.MessageList', 'index.TaskForm', 'index.ContactTrackInfo'],
    stores: [ 'index.WorkStore', 'index.TaskStore', 'index.ContactTrackStore' ],
    models: [],
    init: function() {
        this.control({
            // 在面板importantTask中添加定时刷新事件
//            'importantTask': {
//                afterrender: this.showTask
//            },
            // 在面板informTask中添加定时刷新事件
//            'informTask': {
//                afterrender: this.refreshContactTrackInfo
//            },
            // 关闭通知面板中客户联系窗口
            'contacttrackinfo button[action=closeContactTrackInfo]': {
                click: this.closeContactTrackInfo
            },
            // 关闭详细信息页面
            'taskform button[action=closeTask]': {
                click: this.taskFormClose
            },
            // 监听list的grid的双击事件引起的操作
            'myworkinglist': {
                itemdblclick: this.editWork,
                selectionchange: this.changeBtn,
                scrollershow: utils.scrollershow
            },
            // 监听list的textfield失去焦点时去除前后空格
            'myworkinglist textfield': {
                blur: utils.trimSpaceSearch
            },
            // 监听list的高级检索打开按钮
            'myworkinglist button[action=workOpenOrCloseExpertSearch]': {
                click: this.openOrCloseExpertSearch
            },
            // 监听list的模糊检索和高级检索
            'myworkinglist button[action=queryBtn]': {
                click: this.query
            },
            // 监听list的分配团队任务按钮
            'myworkinglist button[action=addTeamWork]': {
                click: this.addTeamWork
            },
            // 监听list的添加按钮
            'myworkinglist button[action=addWork]': {
                click: this.addWork
            },
            // 监听list的编辑按钮
            'myworkinglist button[action=editWork]': {
                click: this.editWork
            },
            // 监听list的删除按钮
            'myworkinglist button[action=deleteWork]': {
                click: this.deleteWork
            },
            'workIndex datepicker[id=datePicker]': {
                select: this.showDayWork
            },
            // 监听添加编辑页面的textfield失去焦点时去除前后空格
            'workupdate textfield': {
                blur: utils.trimSpace
            },
            // 监听添加或编辑窗口的确定按钮
            'workupdate button[action=updateWork]': {
                click: this.updateWork
            },
            // 监听添加或编辑窗口的清空按钮
            'workupdate button[action=doresetWork]': {
                click: utils.resetRecord
            },
            // 监听添加或编辑窗口的取消按钮
            'workupdate button[action=closeWork]': {
                click: utils.winClose
            },
            'workupdate datetimefield[id=workStratTime]': {
                change: this.checkTime
            },
            // 监听消息弹出窗口单击事件
            'messagelist gridpanel': {
                itemclick: this.showMessageDetail
            }
        });
    },

    viewInit: function(flag) {
        var indexPanel = Ext.getCmp('workIndex');
        if (typeof (indexPanel) === 'undefined') {
            indexPanel = Ext.widget('workIndex');
            Ext.getCmp('centercard').insert(1, indexPanel);
        }
        Ext.getCmp('centercard').getLayout().setActiveItem('workIndex');
        var workSuperQueryForm = Ext.getCmp('workSuperQueryForm');
        var workSearchText = Ext.getCmp('workSearchText');
        var workSearchBtn = Ext.getCmp('workQueryBtn');
        var workOpenOrCloseBtn = Ext.getCmp('workOpenOrCloseExpertSearch');
        var myWorkinglist = Ext.getCmp('myWorking');
        var workGridStore = myWorkinglist.getStore();
        workGridStore.on('beforeload', function(store, options) {
            var new_params = {
                searchFlag: 0,
                date: Ext.util.Format.date(new Date(), 'Y-m-d H:m'),
                userID: USER_ID
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        Ext.Ajax.request({
            url: 'userIsProjectTeamLeader.action',
            params: {
                userID: USER_ID
            },
            success: function(response) {
                if (CRM.checkResponse(response)) {
                    return;
                }
                var responseText = Ext.decode(response.responseText) || '';
                if (responseText.flag == false) {
                    Ext.getCmp('addTeamWorkBtn').setDisabled(true);
                } else {
                    Ext.getCmp('addTeamWorkBtn').setDisabled(false);
                }
            },
            failure: function(response) {
                messageBox.alert('提示', '验证是是否是组长失败，请联系管理员！');
            }
        });
        if (workSuperQueryForm.isHidden()) {
            workSearchText.reset();
        } else {
            Ext.getCmp('workTheme').reset();
            Ext.getCmp('workCustomerName').reset();
            Ext.getCmp('jobStyleSearchCombobox').reset();
            Ext.getCmp('prioritySearchCombobox').reset();
            Ext.getCmp('completionSearchCombobox').reset();
            Ext.getCmp('workDateStartSearch').reset();
            Ext.getCmp('workDateEndSearch').reset();
            workSearchBtn.setDisabled(false);
            workSearchText.setDisabled(false);
            workSuperQueryForm.hide();
            workOpenOrCloseBtn.setText('高级检索');
            workOpenOrCloseBtn.up('grid').update();
        }
        workGridStore.loadPage(1);
        workGridStore.currentPage = 1;
        //this.changeTask('informTask');
        this.showTask('importantTask');
        this.refreshContactTrackInfo('informTask');
    },

    showTask: function(componentId) {
        var me = this;
        me.changeTask(componentId);
        if (typeof(me.showTaskIntervalId) != 'undefined') {
            // clear The last setting
            clearInterval(me.showTaskIntervalId);
        }
//        utils.changeMessageText();
        me.showTaskIntervalId = setInterval(function() {
            if (Ext.getCmp('centercard').getLayout().getActiveItem().id == 'workIndex') {
                me.changeTask(componentId);
//                utils.changeMessageText();
            }
        }, 10 * 60 * 1000);
    },

    changeBtn: function(sm, selections) {
        var flag = false;
        var flag2 = false;
        var addTeamWorkBtn = Ext.getCmp('addTeamWorkBtn');
        Ext.Array.each(selections, function(item) {
            flag = flag || (item.get('teamFlag') > 0);
            if ((addTeamWorkBtn.disabled == true && item.get('teamFlag') == -1)) {
                Ext.getCmp('workEditBtn').setDisabled(true);
                flag2 = true;
            }
        });
        Ext.getCmp('workDeleteBtn').setDisabled(selections.length == 0 || flag);
        if (!flag2) {
            Ext.getCmp('workEditBtn').setDisabled(selections.length !== 1 || flag);
        }
    },
    openOrCloseExpertSearch: function(button) {
        var toolBar = button.up('toolbar');
        var gridPanel = toolBar.up('gridpanel');
        var toolForm = gridPanel.down('form');
        var workGridStore = gridPanel.getStore();
        workGridStore.on('beforeload', function(store, options) {
            var new_params = {
                searchFlag: 0,
                date: Ext.util.Format.date(new Date(), 'Y-m-d H:m'),
                userID: USER_ID
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        workGridStore.loadPage(1);
        workGridStore.currentPage = 1;
        if (toolForm.isHidden()) {
            if (gridPanel.completionSearchStore.getCount() == 0) {
                utils.searchStoreInset(gridPanel.completionSearchStore, gridPanel.completionStore, Ext.getCmp('completionSearchCombobox'));
            }
            if (gridPanel.prioritySearchStore.getCount() == 0) {
                utils.searchStoreInset(gridPanel.prioritySearchStore, gridPanel.priorityStore, Ext.getCmp('prioritySearchCombobox'));
            }
            if (gridPanel.jobStyleSearchStore.getCount() == 0) {
                utils.searchStoreInset(gridPanel.jobStyleSearchStore, gridPanel.jobStyleStore, Ext.getCmp('jobStyleSearchCombobox'));
            }
            if (gridPanel.workTypeSearchStore.getCount() == 0) {
                utils.searchStoreInset(gridPanel.workTypeSearchStore, gridPanel.workTypeStore, Ext.getCmp('workTypeSearchCombobox'));
            }
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
    },
    query: function(button) {
        utils.query(button);
    },

    loadCustomerStore: function(workUpdate, customerID) {
        if (workUpdate.customerStore.getCount() == 0) {
            workUpdate.customerStore.load({
                params: {
                    customerFlag: 2,
                    userID: USER_ID
                },
                callback: function(records, operation, success) {
                    workUpdate.customerStore.insert(0, {
                        customerID: '0',
                        customerName: '-无-'
                    });
                    var customerCombo = workUpdate.down('#customerID');
                    if (customerID == 0) {
                        customerCombo.select('0');
                    }
                }
            });
        }
    },

    addWork: function() {
        var view = Ext.widget('workupdate');
        view.down('#assignee').hide();
        view.down('#userID').setValue(USER_ID);
        this.loadCustomerStore(view, 0);
        Ext.getCmp('workTeamFlag').setValue('0');
    },

    addTeamWork: function() {
        var view = Ext.widget('workupdate');
        view.down('#assignee').setDisabled(false);
        view.down('#userID').setValue(USER_ID);
        Ext.getCmp('workTeamFlag').setValue('-1');
        this.loadCustomerStore(view, 0);
        this.loadUser(view.assigneeStore);
    },

    loadUser: function(store) {
        store.load({
            params: {
                userID: USER_ID
            }
        });
    },
    editWork: function(button) {
        var addTeamWorkBtn = Ext.getCmp('addTeamWorkBtn');
        var workEditBtn = Ext.getCmp('workEditBtn');
        if (!workEditBtn.isHidden()) {
            var grid = button.up('grid');
            var checkRecord = grid.getSelectionModel().getSelection();
            if (checkRecord[0].get('teamFlag') <= 0) {
                var view = Ext.widget('workupdate');
                view.down('#assignee').setDisabled(false);
                if (checkRecord[0].get('teamFlag') == 0) {
                    view.down('#assignee').setDisabled(true);
                    view.down('#assignee').hide();
                }
                view.setTitle('编辑任务');
                this.loadCustomerStore(view, checkRecord[0].get('customerID'));
                view.down('#reset').setText('重置');
                var form = view.down('form');
                this.loadUser(view.assigneeStore);
                form.loadRecord(checkRecord[0]);
                if (addTeamWorkBtn.disabled == true) {
                    setTimeout(function fn() {
                        view.down('#assignee').markInvalid('团队已被删除！');
                    }, 200);
                }
                view.down('#userID').setValue(USER_ID);
                if (addTeamWorkBtn.disabled == true && workEditBtn.disabled == true) {
                    view.setTitle('详细信息');
                    view.down('#theme').setReadOnly(true);
                    view.down('#customerID').setReadOnly(true);
                    view.down('#completion').setReadOnly(true);
                    view.down('#priority').setReadOnly(true);
                    view.down('#workType').setReadOnly(true);
                    view.down('#startTime').setReadOnly(true);
                    view.down('#endTime').setReadOnly(true);
                    view.down('#assignee').setReadOnly(true);
                    view.down('#workDetail').setReadOnly(true);
                    view.down('#descriptions').setReadOnly(true);
                }
            } else {
                var messageDetailWin = Ext.widget('taskform');
                var form = messageDetailWin.down('#taskForm');
                form.loadRecord(checkRecord[0]);
            }
        }
    },

    deleteWork: function(button) {
        var grid = button.up('grid');
        var checkRecord = grid.getSelectionModel().getSelection();
        if (checkRecord[0].get('teamFlag') <= 0) {
            utils.delRecords(grid, 'deleteWork.action', 'workID');
        }
    },

    showDayWork: function(menu, date, eOpts) {
        var date = Ext.util.Format.date(menu.getValue(), 'Y-m-d');
        var workingStore = Ext.getCmp('myWorking').store;
        workingStore.on('beforeload', function(store, options) {
            var new_params = {
                searchFlag: 3,
                jsonString: '{}',
                date: date,
                userID: USER_ID
            };
            Ext.apply(store.proxy.extraParams, new_params);
        });
        workingStore.load();
    },

    updateWork: function(button) {
        var me = this;
        utils.updateRecord(button, 'updateWork.action', "myWorking", 2);
        setTimeout(function() {
            //me.changeTask('informTask');
            me.changeTask('importantTask');
        }, 300);
    },
    showMessageDetail: function(grid, record) {
        var messageDetailWin = Ext.getCmp('taskform');
        if (typeof (messageDetailWin) == 'undefined') {
            messageDetailWin = Ext.widget('taskform');
            var form = messageDetailWin.down('#taskForm');
            form.loadRecord(record);
        }
    },

    taskFormClose: function(button) {
        var win = button.up('window');
        var workID = Ext.getCmp('taskFormWorkID').getValue();
        win.close();
        Ext.Ajax.request({
            url: 'readMessage.action',
            params: {
                jsonString: workID
            },
            success: function(response) {
                if (CRM.checkResponse(response)) {
                    return;
                }
                var messageGrid = Ext.getCmp('messageGrid');
                if (typeof (messageGrid) != 'undefined') {
                    var messageStore = messageGrid.getStore();
                    var currentPage = messageStore.currentPage;
                    var pageSize = messageStore.pageSize;
                    var total = messageStore.totalCount - 1;
                    if (total <= (currentPage - 1) * pageSize) {
                        currentPage = currentPage - 1;
                    }
                    if (total == 0) {
                        currentPage = 1;
                    }
                    messageStore.loadPage(currentPage);
                }
//                utils.changeMessageText();
            },
            failure: function(response) {
                messageBox.alert('提示', '查看失败，请联系管理员！');
            }
        });
    },
    closeContactTrackInfo:function(button) {
        button.up('window').close();
    },
    changeTask: function(componentID) {
        var divName = 'show' + componentID;
        var domshow = Ext.get(divName).dom;
        var taskFlag;
        if (divName == 'showinformTask') {
            // display contact 
            taskFlag = 1;
        } else {
            taskFlag = 0;
        }
        var store = Ext.getCmp(componentID).store;
        store.load({
            params: {
                searchFlag: taskFlag,
                date: Ext.util.Format.date(new Date(), 'Y-m-d H:i:s'),
                userID: USER_ID
            },
            callback: function() {
                var html = "";
                var storelength = 1;
                store.each(function(item) {
                    var workID = item.get('workID');
                    var theme = item.get('theme');
                    html = html + "<div style='padding-left:20px;'><h3>" + "<span style='color:blue;cursor:pointer;'" + "onclick=utils.showOneTask(this,'" + componentID + "'," + workID + ")>" + storelength
                            + ". " + Ext.htmlEncode(theme) + "</span></h3></div>";
                    storelength += 1;
                });
                if (store.getCount() == 0) {
                    html = "<div style='padding-left:20px;'><br>无数据</div>";
                } else if (store.getCount() < 6) {
                    html = "<div><br>" + html + "</div>";
                } else {
                    html = "<marquee behavior=scroll direction=up scrollamount=2 scrolldelay=0 vspace=0 hspace=1 loop=-1 onmouseover=this.stop() onmouseout=this.start()>" + html + "</marquee>";
                }
                Ext.DomHelper.overwrite(domshow, html);
            }
        });
    },
    checkTime: function() {
        var workStratTimeValue = Ext.getCmp('workStratTime').getValue();
        var formatWorkStratTimeValue = Ext.util.Format.date(workStratTimeValue, 'Y-m-d H:m');
        var time = utils.getTime();
        var isMailInformed = Ext.getCmp('isMailInformed');
        if (utils.compareTime(time, formatWorkStratTimeValue)) {
            isMailInformed.setDisabled(false);
        } else {
            isMailInformed.setDisabled(true);
            isMailInformed.setValue(false);
        }
    },
    refreshContactTrackInfo: function(componentID) {
        var me = this;
        // refresh ContactTrack information
        me.getContactTrackInfo(componentID);
        if (typeof(me.contactIntervalId) != 'undefined') {
            // clear The last setting
            clearInterval(me.contactIntervalId);
        }
        // reset refresh ContactTrack setting
        // interval time: 60 s
        me.contactIntervalId = setInterval(function(){
            if (Ext.getCmp('centercard').getLayout().getActiveItem().id == 'workIndex') {
                me.getContactTrackInfo(componentID);
            }
        }, 1 * 60 * 1000);
    },
    getContactTrackInfo: function(componentID) {
        var store = Ext.getCmp(componentID).store;
        store.load({
            params: {
                date: Ext.util.Format.date(new Date(), 'Y-m-d H:i:s'),
                userID: USER_ID
            },
            callback: function() {
                var html = "";
                var storelength = 1;
                store.each(function(item) {
                    // get contactTrack info
                    var contactID = item.get('contactID');
                    var contactTheme = item.get('contactTheme');
                    html = html + "<div style='padding-left:20px;'><h3>" 
                                    + "<span style='color:blue;cursor:pointer;'" 
                                    + "onclick=utils.showContactTrackInfo(this,'" + componentID + "'," + contactID + ")>" + storelength
                                    + ". " + Ext.htmlEncode(contactTheme) + "</span></h3></div>";
                    storelength += 1;
                });
                if (store.getCount() == 0) {
                    html = "<div style='padding-left:20px;'><br>无数据</div>";
                } else if (store.getCount() < 6) {
                    html = "<div><br>" + html + "</div>";
                } else {
                    html = "<marquee behavior=scroll direction=up scrollamount=2 scrolldelay=0 vspace=0 hspace=1 loop=-1 onmouseover=this.stop() onmouseout=this.start()>" + html + "</marquee>";
                }
                var domshow = Ext.get('showinformTask').dom;
                Ext.DomHelper.overwrite(domshow, html);
            }
        });
    }
});