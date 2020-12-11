$(function () {
    layui.use(['form', 'laydate', 'table'], function () {
        var form = layui.form
            , layer = layui.layer
            , laydate = layui.laydate
            , table = layui.table;

        //点击搜索按钮的点击事件
        $("#searchBtn").click(function () {
            var titleVal = $("input[name='title']").val();
            var price1Val = $("input[name='price1']").val();
            var price2Val = $("input[name='price2']").val();
            //被选中的这一列的值
            var statusVal = $("#status option:selected").val();
            var date1Val = $("input[name='date1']").val();
            var date2Val = $("input[name='date2']").val();

            /*
            *   1.web程序员帮我们吧数据做了校验
            *   2.java后台做校验
            *   3.这里不能发起ajax请求
            *       为什么呢？
            *   我们点击按钮搜索的目的是为了在table表格上面显示数据
            *   LayuiTableResult
            *   他到底是 json的数据格式 还是key=value的数据格式
            *   你的controller 就不知道怎么接受了 从哪里可以分析到他是什么类型的数据
            *   F12
            * */
            table.reload('itemTableAll', {
                url: '/item/multipleQuery',
                where: {
                    title: titleVal,
                    price1: price1Val*100,
                    price2: price2Val*100,
                    status: statusVal,
                    date1: date1Val,
                    date2: date2Val
                }
            });
            $("input[name='title']").val("");
            $("input[name='price1']").val("");
            $("input[name='price2']").val("");
            //被选中的这一列的值
            $("#status").val(0);
            //下拉选项框比较特殊  他需要使用form表单对象调用render刷新整个页面才有用
            form.render();
            $("input[name='date1']").val("");
            $("input[name='date2']").val("");
        })
        //
       var dateObj1 = laydate.render({
            elem: '#date1',
            theme: '#393D49',
            showBottom: false,
            done: function (value, date, endDate) {
                var arr = value.split("-");
                console.log(arr);
                dateObj2.config.min.year = arr[0];
                dateObj2.config.min.month = arr[1]-1;
                dateObj2.config.min.date = arr[2];
            }
        });
       var dateObj2 = laydate.render({
            elem: '#date2',
            theme: '#393D49',
            showBottom: false,
            done: function (value, date, endDate) {
                var arr = value.split("-");
                dateObj1.config.max.year = arr[0];
                dateObj1.config.max.month = arr[1]-1;
                dateObj1.config.max.date = arr[2];
            }
        });

        table.render({
            elem: '#itemTableAll'
            , url: '/item/getItemByPage'
            , height: '750px'
            , toolbar: '#topBtnGroup' //开启头部工具栏，并为其绑定左侧模板
            , defaultToolbar: ['filter', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                , layEvent: 'LAYTABLE_TIPS'
                , icon: 'layui-icon-tips'
            }]
            , title: '商品列表'
            , cols: [
                [
                    {type: 'checkbox', fixed: 'left'}
                    , {field: 'id', title: 'ID', width: 80, fixed: 'left', unresize: true, sort: true}
                    , {field: 'title', title: '商品名称', width: 150, edit: 'text'}
                    , {field: 'sellPoint', title: '商品卖点', width: 150, edit: 'text'}
                    , {field: 'itemPrice', title: '商品价格', width: 120, edit: 'text', sort: true}
                    , {field: 'num', title: '商品数量', width: 100}
                    , {field: 'itemImage', title: '商品图片', width: 160, templet: '#itemImage'}
                    , {field: 'tbItemCatName', title: '分类名称', width: 120}
                    , {field: 'status', title: '商品状态', width: 120, sort: true, templet: '#statusTransform'}
                    , {field: 'created', title: '创建时间', width: 160, templet: "<div>{{layui.util.toDateString(d.created, 'yyyy-MM-dd HH:mm:ss')}}</div>"}
                    , {field: 'updated', title: '更新时间', width: 160, templet: "<div>{{layui.util.toDateString(d.updated, 'yyyy-MM-dd HH:mm:ss')}}</div>"}
                    , {fixed: 'right', title: '操作', toolbar: '#rightBtnGroup', width: 150}
                ]
            ]

            , page: true
            , limit: 20
            , parseData: function (res) {
                if(res.data==null){
                    window.location.href = "http://localhost:8080/error.jsp";
                }else{
                    return {
                        "code": res.code, //解析接口状态
                        "msg": res.msg, //解析提示文本
                        "count": res.count, //解析数据长度
                        "data": res.data //解析数据列表
                    };
                }
            }
        });
        /*
        *   注意这里绑定的不是table表的id值 而是lay-filter
        * */
        table.on('toolbar(itemTableAll)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'delItem':
                    var data = checkStatus.data;
                    var jsonStr = JSON.stringify(data);
                    /*
                    *  在这里我们用第一种做法
                    * */
                    if (jsonStr == "[]") {
                        layer.alert("请勾选您需要删除的数据");
                        break;
                    }
                    //发起ajax请求 来controller完成真正的删除
                    $.ajax({
                        type: "POST",
                        url: "/item/delItemById",
                        dataType: "json",
                        contentType: 'application/json;charset=utf-8',
                        data: jsonStr,
                        success: function (msg) {
                            layer.alert(msg.msg);
                            table.reload('itemTableAll', {
                                url: '/item/getItemByPage'
                            });
                        }
                    });
                    break;
                case 'exports':
                    var data = checkStatus.data;
                    var ids = [];
                    $.each(data,function (i,n) {
                        ids[i] = n.id;
                    });
                    window.location.href = "/item/exportsExcel?ids="+ids;
                    break;
                case 'upload':
                    var data = checkStatus.data;
                    var jsonStr = JSON.stringify(data);
                    if (jsonStr == "[]") {
                        layer.alert("请勾选您需要上架的商品");
                        break;
                    }
                    //定义了一个空数组
                    var ids = [];
                    //json代表你要循环的数据 i代表下标 n代表对象
                    //layui的这个造型他认为是一个json字符串 不是一个真正的json对象
                    var json = eval('(' + jsonStr + ')');
                    $.each(json, function (i, n) {
                        ids[i] = n["id"];
                    });

                    $.ajax({
                        type: "POST",
                        url: "/item/changeItemStatus",
                        dataType: "json",
                        data: "ids=" + ids + "&statusCode=1",
                        success: function (msg) {
                            layer.alert(msg.msg);
                            table.reload('itemTableAll', {
                                url: '/item/getItemByPage'
                            });
                        }
                    });
                    break;
                case 'offload':
                    var data = checkStatus.data;
                    var jsonStr = JSON.stringify(data);
                    if (jsonStr == "[]") {
                        layer.alert("请勾选您需要上架的商品");
                        break;
                    }
                    //定义了一个空数组
                    var ids = [];
                    //json代表你要循环的数据 i代表下标 n代表对象
                    //layui的这个造型他认为是一个json字符串 不是一个真正的json对象
                    var json = eval('(' + jsonStr + ')');
                    $.each(json, function (i, n) {
                        ids[i] = n["id"];
                    });

                    $.ajax({
                        type: "POST",
                        url: "/item/changeItemStatus",
                        dataType: "json",
                        data: "ids=" + ids + "&statusCode=2",
                        success: function (msg) {
                            layer.alert(msg.msg);
                            table.reload('itemTableAll', {
                                url: '/item/getItemByPage'
                            });
                        }
                    });
                    break;
            };
        });
        //监听行工具事件
        table.on('tool(itemTableAll)', function (obj) {
            var dataJson = obj.data;
            if (obj.event === 'edit') {
                console.log(dataJson);
            } else if (obj.event === 'export') {
                window.location.href = "/item/exportExcel?itemId="+dataJson.id
            }
        });
    });
})
