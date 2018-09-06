$(function () {
    click();

    // 查询所有课程并展示
    showCourse();

    // 删除课程信息
    $("body").delegate(".delBtn", "click", function () {
        // 获取课程名
        var courseId = $(this).parent().children("ul").children("li").eq(0).text();
        $.ajax({
            url: "http://localhost:8080/DelCourse",
            type: "post",
            data: {
                "courseId": courseId
            },
            dataType: "text",
            success: function (msg) {
                if(msg == 1){
                    //移除类名
                    $(this).parent().remove();
                    location.reload();
                    showCourse();
                    alert("success");
                }else{
                   alert("error");
                }
            }
        })
    })
    // 展示所有课程
    function showCourse() {
        var arr = ["办公效率", "职场发展", "职业岗位", "人工智能", "IT&互联网", "语言留学", "职场之外"];
        for(var i=0; i<arr.length; i++){
            // 课程显示
            $.ajax({
                url: "http://localhost:8080/ShowAllCourse",
                method: "post",
                data: {
                    "two_name": arr[i]
                },
                dataType: "json",
                success: function (list) {
                    $.each(list, function (key, value) {
                        var $course = createCourse(value);
                        $("#details3").append($course);
                    })
                },
                error: function (xml) {
                    console.log(xml);
                }
            })
        }
    }

    // 展示课程信息
    function createCourse(obj) {
        var $course = $('<div class="courseInfo course">\n' +
            '              <ul>\n' +
            '                  <li class="info1 info2">'+obj.course_id+'</li>\n' +
            '                  <li class="info2">'+obj.course_name+'</li>\n' +
            '                  <li class="info2">￥'+obj.price+'</li>\n' +
            '                  <li class="info2">'+obj.company+'</li>\n' +
            '                  <li class="info2">'+obj.kind_id+'</li>\n' +
            '                  <li class="info2">'+obj.putway_time+'</li>\n' +
            '                  <li class="info2">'+obj.grade+'</li>\n' +
            '                  <li class="info3">'+obj.page_view+'人</li>\n' +
            '              </ul>\n' +
            '              <div class="delBtn"></div>\n' +
            '          </div>');
        return $course;
    }

    // 展示要查询的课程信息
    $("input[name='searchBtn']").click(function () {
        var courseId = $(".inputTxt").val();
        // 课时显示
        $.ajax({
            url: "http://localhost:8080/ShowPeriod",
            method: "post",
            data: {
                "courseId": courseId
            },
            dataType: "json",
            success: function (list) {
                $.each(list, function (key, value) {
                    var $period = createPeriod(value);
                    $("#details5").append($period);
                })
            },
            error: function (xml) {
                console.log(xml);
            }
        })
    })

    // 删除课程信息
    $("body").delegate(".deBtnP", "click", function () {
        var id = $(this).parent().children("ul").children("li").eq(0).text();
        $.ajax({
            url: "http://localhost:8080/DelPeriod",
            type: "post",
            data: {
                "id": id
            },
            dataType: "text",
            success: function (msg) {
                console.log(msg);
                if(msg == 1){
                    //移除类名
                    $(this).parent().remove();
                    location.reload();
                    alert("success");
                }else{
                    alert("error");
                }
            }
        })
    })

    // 展示课时信息
    function createPeriod(obj) {
        var $period = $('<div class="courseInfo course">\n' +
            '              <ul>\n' +
            '                  <li class="info1 info2">'+obj.id+'</li>\n' +
            '                  <li class="info2">'+obj.period_name+'</li>\n' +
            '                  <li class="info2">'+obj.video_address+'</li>\n' +
            '                  <li class="info3">'+obj.course_id+'</li>\n' +
            '              </ul>\n' +
            '              <div class="deBtnP"></div>\n' +
            '          </div>');
        return $period;
    }

    // 生成订单列表
    $.ajax({
        url: "http://localhost:8080/ShowUserOrder",
        type: "post",
        dataType: "json",
        success: function (list) {
            $.each(list, function (key, value) {
                var $order = createOrder(value);
                $("#details8").append($order);
            })
        },
    })

    // 展示订单信息
    function createOrder(obj) {
        console.log(obj.order_status);
        var status;
        if(obj.order_status == 1){
            status = "交易成功";
        }else{
            status = "待支付";
        }
        var $order = $('<div class="courseInfo course">\n' +
            '              <ul>\n' +
            '                  <li class="info1 info2">'+obj.order_id+'</li>\n' +
            '                  <li class="info2" title="'+obj.order_time+'">'+obj.order_time+'</li>\n' +
            '                  <li class="info2">'+obj.price+'</li>\n' +
            '                  <li class="info3">'+status+'</li>\n' +
            '              </ul>\n' +
            '          </div>')
        return $order;
    }

})

function click() {
    $(".no").hide();
    $(".bodydetails").hide();
    $(".active:eq(0)").click(function () {
        $(".no1").toggle(300);
        $(".no2").hide(300);
        $(".no3").hide(300);
        $(".no4").hide(300);
        $("#kind2 a").text("用户管理");
        $("#kind3 a").text("————");
    })

    $(".active:eq(1)").click(function () {
        $(".no2").toggle(300);
        $(".no1").hide(300);
        $(".no3").hide(300);
        $(".no4").hide(300);
        $("#kind2 a").text("课程管理");
        $("#kind3 a").text("————");
    })

    $(".active:eq(2)").click(function () {
        $(".no3").toggle(300);
        $(".no1").hide(300);
        $(".no2").hide(300);
        $(".no4").hide(300);
        $("#kind2 a").text("统计分析");
        $("#kind3 a").text("————");
    })

    $(".active:eq(3)").click(function () {
        $(".no4").toggle(300);
        $(".no1").hide(300);
        $(".no2").hide(300);
        $(".no3").hide(300);
        $("#kind2 a").text("订单管理");
        $("#kind3 a").text("————");
    })

    $(".no1:eq(0)").click(function () {
        $("#kind2 a").text("用户管理");
        $("#kind3 a").text("删除用户");
        $("#details1").siblings().hide();
        $("#details1").show();
    })

    $(".no2:eq(0)").click(function () {
        $("#kind2 a").text("课程管理");
        $("#kind3 a").text("添加课程");
        $("#details2").siblings().hide();
        $("#details2").show();

    })
    $(".no2:eq(1)").click(function () {
        $("#kind2 a").text("课程管理");
        $("#kind3 a").text("删除课程");
        $("#details3").siblings().hide();
        $("#details3").show();

    })
    $(".no2:eq(2)").click(function () {
        $("#kind2 a").text("课程管理");
        $("#kind3 a").text("添加课时");
        $("#details4").siblings().hide();
        $("#details4").show();

    })
    $(".no2:eq(3)").click(function () {
        $("#kind2 a").text("课程管理");
        $("#kind3 a").text("删除课时");
        $("#details5").siblings().hide();
        $("#details5").show();

    })

    $(".no3:eq(0)").click(function () {
        $("#kind2 a").text("统计分析");
        $("#kind3 a").text("用户喜好");
        $("#details6").siblings().hide();
        $("#details6").show();

    })
    $(".no3:eq(1)").click(function () {
        $("#kind2 a").text("统计分析");
        $("#kind3 a").text("订单分析");
        $("#details7").siblings().hide();
        $("#details7").show();

    })

    $(".no4:eq(0)").click(function () {
        $("#kind2 a").text("订单管理");
        $("#kind3 a").text("查看订单");
        $("#details8").siblings().hide();
        $("#details8").show();
    })

}