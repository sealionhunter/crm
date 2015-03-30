Ext.define('CRM.controller.systemManagement.organizationManagement.organizationStructure.OrganizationStructure', {
    extend: 'Ext.app.Controller',
    views: [ 'systemManagement.organizationManagement.organizationStructure.OrganizationalChart' ],
    stores: [],
    init: function() {
        this.control({
            'organizationalChart': function() {
            }
        });
    },
    loadChartData: function() {
        // 定义一个计数的变量
        var count;
        // 定义一个计数的变量
        var preCount;
        // 用来存放一个部门的名称以及它在图中对应的坐标等。
        var sortDepart = null;
        // 用来存放sortDepart的数组
        var sortArray;
        // 用来存放sortArray 的map
        var sortMap = new Ext.util.HashMap();
        // 用来按照深度来存放部门信息（部门ID 部门名称 父部门ID 深度等等）的map
        var map = new Ext.util.HashMap();
        // 存放所有部门的数组
        var allDepartmentArray = new Array();
        // 将所有的矩形框的信息放入到该数组中
        var rectArray = new Array();
        // 将某一个矩形框的信息赋值给rectDepart
        var rectDepart = null;
        // 将所有部门的信息（坐标、名称）存入到该数组中
        var textArray = new Array();
        // 将某一个部门的信息存入到该变量中
        var textDepart = null;
        // 在循环map和sortMap的时候的中间数组
        var array;
        // 循环map和sortMap的时候的中间数组
        var nextArray;
        // 存放连线坐标的变量
        var path = new String();
        // x坐标的起始位置
        var xStart = 0;
        // y坐标的起始位置
        var yStart = 0;
        // 每个矩形框的最小宽度
        var minWidth = 85;
        // 每个矩形框的最小高度
        var minHeight = 30;
        // 文本的样式
        var FONT = '13px Arial bold';
        // 矩形的type
        var TYPE_RECT = 'rect';
        // 文本的type
        var TYPE_TEXT = 'text';
        // 两两相邻的部门在x坐标间的间隔
        var xSpace = 10;
        // 两个相邻深度的部门在y坐标间的间隔
        var ySpace;
        // 找到需要的面板。
        var chart = Ext.getCmp('organizationalChart');
        // 得到面板的width
        var width = chart.getWidth();
        // 得到面板的height
        var height = chart.getHeight();
        Ext.Ajax.request({
            url: 'organizationStructure.action',
            params: {
                width: width,
                height: height
            },
            success: function(response) {
                if (CRM.checkResponse(response)) {
                    return;
                }
                var department = Ext.decode(response.responseText).department;
                var depth = Ext.decode(response.responseText).depth;
                // 将后台传过来的数据按照深度放入allDepartmentArray中
                for ( var i = 1; i <= depth; i++) {
                    array = new Array();
                    for ( var j = 0; j < department.length; j++) {
                        if (i == department[j].depth) {
                            array.push(department[j]);
                        }
                    }
                    allDepartmentArray.push(array);
                }
                // 先计算好最后一层的坐标信息，将其存放到sortMap中，key为最后一层所在的深度
                for ( var i = 0; i < allDepartmentArray.length; i++) {
                    map.add(i + 1, allDepartmentArray[i]);
                }
                array = map.get(depth);
                xSpace = (width - minWidth * array.length) / (array.length + 1);
                xStart = xSpace;
                ySpace = (height - minHeight * depth) / (depth + 1);
                yStart = height - ySpace - minHeight;
                sortArray = new Array();
                if (array.length * minWidth >= width) {
                    chart.setAutoScroll(true);
                }
                for ( var i = 0; i < array.length; i++) {
                    if (array[i].departmentID == -2) {
                        sortDepartment = {
                            x: (xStart + i * (minWidth + xSpace)),
                            y: yStart,
                            text: array[i].departmentName,
                            isExist: 0,
                            child: 0
                        };
                    } else {
                        sortDepartment = {
                            x: (xStart + i * (minWidth + xSpace)),
                            y: yStart,
                            text: array[i].departmentName,
                            isExist: 1,
                            child: 0
                        };
                    }
                    sortArray.push(sortDepartment);
                }
                sortMap.add(depth, sortArray);
                var sortArr;
                // 根据倒数第一层，进行循环，求取倒数第二城至第一层的坐标，存入sortMap中，key为每一层所在的深度。
                for ( var i = depth - 1; i >= 1; i--) {
                    yStart = height - (ySpace + minHeight) * (depth - i);
                    array = map.get(i);
                    nextArray = map.get(i + 1);
                    sortArray = sortMap.get(i + 1);
                    sortArr = new Array();
                    count = 0;
                    preCount = 0;
                    for ( var j = 0; j < array.length; j++) {
                        // 判断当前的节点是否存在，不存在的条件：departmentID==-2
                        if (array[j].departmentID == -2) {
                            count++;
                        } else {
                            for ( var k = 0; k < nextArray.length; k++) {
                                // 判断当前节点的departmentID和下一层的节点的fatherDepartmentID是否相同，如果相同count++
                                if (array[j].departmentID == nextArray[k].fatherDepartmentID) {
                                    count++;
                                }
                            }
                        }
                        // 如果当前节点不存在，则在sortDepart中存入下面的信息。
                        if (array[j].departmentID == -2) {
                            sortDepart = {
                                x: sortArray[count - 1].x,
                                y: yStart,
                                text: array[j].departmentName,
                                isExist: 0,
                                child: 0
                            };
                        } else {
                            // 如果上一个节点的统计和这个节点的统计相同，说明当前节点没有子节点（child：0）
                            if (preCount == count) {
                                sortDepart = {
                                    x: sortArray[count].x,
                                    y: yStart,
                                    text: array[j].departmentName,
                                    isExist: 1,
                                    child: 0
                                };
                                count++;
                            } else {
                                // 下一层在两侧的节点的中点就是当前节点的
                                // x坐标，y坐标为ystart-minHeight-ySpace。
                                sortDepart = {
                                    x: (sortArray[preCount].x + sortArray[count - 1].x) / 2,
                                    y: yStart,
                                    text: array[j].departmentName,
                                    isExist: 1,
                                    child: count - preCount
                                };
                                for ( var m = preCount; m < count; m++) {
                                    path = path + " M" + (sortArray[m].x + minWidth / 2) + " " + yStart;
                                    path = path + " L" + (sortArray[m].x + minWidth / 2) + " " + (yStart - ySpace / 2);
                                    path = path + " L" + (sortDepart.x + minWidth / 2) + " " + (yStart - ySpace / 2);
                                    path = path + " L" + (sortDepart.x + minWidth / 2) + " " + (yStart - ySpace);
                                }
                            }
                        }
                        sortArr.push(sortDepart);
                        preCount = count;
                    }
                    sortMap.add(i, sortArr);
                }
                // 将所有的部门封到相对应的array中 ，在drawComponent的items中使用。
                var textX = 0;
                if (Ext.isIE) {
                    spaces = ' ';
                } else {
                    spaces = '\0\0\0\0\0\0';
                }
                for ( var i = 1; i <= depth; i++) {
                    array = sortMap.get(i);
                    yStart = height - (ySpace + minHeight) * (depth - i + 1);
                    for ( var j = 0; j < array.length; j++) {
                        rectDepart = '';
                        textDepart = '';
                        fillSpace = array[j].text;
                        textX = array[j].x + 2 + (6 - array[j].text.length) * 13 / 2;
                        if (array[j].text.length > 6) {
                            var str = fillSpace.substring(0, 5);
                            fillSpace = str + "...";
                            textX = array[j].x + 2;
                        }
                        // isExist==1,存在，画节点；isExist==0，不存在，放弃节点
                        if (array[j].isExist == 1) {
                            // 画矩形
                            rectDepart = {
                                type: TYPE_RECT,
                                fill: '#00BBFF',
                                width: minWidth,
                                height: minHeight,
                                x: array[j].x,
                                y: yStart,
                                "stroke-width": "2",
                                stroke: '#33CCFF'
                            };
                            // 写文本
                            textDepart = {
                                id: i + "" + j,
                                type: TYPE_TEXT,
                                realText: Ext.htmlEncode(array[j].text),
                                text: fillSpace,
                                fill: '#ffffff',
                                font: FONT,
                                x: textX,
                                y: yStart + 15,
                                // 鼠标悬停的监听事件
                                listeners: {
                                    mouseover: function(e) {
                                        Ext.tip.QuickTipManager.init();
                                        Ext.apply(Ext.tip.QuickTipManager.getQuickTip(), {
                                            autuScroll: true,
                                            maxWidth: e.realText.length * 19,
                                            minWidth: e.realText.length * 18,
                                            showDelay: 50
                                        // Show 50ms after entering target
                                        });
                                        Ext.tip.QuickTipManager.register({
                                            target: e.id,
                                            title: e.realText,
                                            width: e.realText.length * 18,
                                            dismissDelay: 10000
                                        // Hide after 10 seconds hover
                                        });
                                    }
                                }
                            };
                        }
                        rectArray.push(rectDepart);
                        textArray.push(textDepart);
                    }
                }
                // 创建了一个drawComponent
                var component = Ext.create('Ext.draw.Component', {
                    viewBox: false,
                    items: [ {
                        type: 'path',
                        path: path, // if the value is "M100 40 L150 40", it's
                                    // ok.
                        "stroke-width": "1.5",
                        stroke: '#663366'
                    }, rectArray, textArray ]
                });
                chart.items.add(component);
                chart.doLayout();
            }
        });
    },
    viewInit: function(treeId) {
        var organizationalChart = Ext.getCmp('organizationalChart');
        if (typeof (organizationalChart) == 'undefined') {
            organizationalChart = Ext.widget('organizationalChart');
        } else {
            organizationalChart.destroy();
            organizationalChart = Ext.widget('organizationalChart');
        }
        Ext.getCmp('centercard').insert(1, organizationalChart);
        Ext.getCmp('centercard').getLayout().setActiveItem(organizationalChart);
        this.loadChartData();
        return organizationalChart;
    }
});
