$(function () {
    // 每页展示课程数
    var pageSize = 5;
    $.addCookie("pageBegin", 1);
    
    // 首页后的一级节点
    var li_name = UrlParm.parm("name");
    console.log(li_name);
    $(".arrow_next").text(li_name);

    // 设置二级节点
    $.ajax({
        url: "http://localhost:8080/ShowKind",
        method: "post",
        data: {
            "name": li_name
        },
        dataType: "json",
        success: function (list) {
            $.each(list, function (key, value) {
                var $li = createFuLi(value.kind_name);
                $(".nav_flow").append($li);

                // 设置三级标题
                ajax_three(value.kind_name);
                
                // 获取课程信息
                ajax_ShowCourse(value.kind_name, "http://localhost:8080/ShowCourse", 1, 1);
            })
        },
        error: function (xml) {
            console.log(xml);
        }
    })

    // 转跳首页
    $(".head_home").click(function () {
        window.location.href="allClass.html";
    })

    // 设置二级节点
    $("ul").delegate(".flow_li", "click", function () {
        var $nav_li = $(this).text();
        $(".arrow_next").text($nav_li);

        // 设置节点颜色
        // 移除所有类名
        $(this).parent().children("li").removeClass("nav_flow_li");
        // 添加类名
        $(this).addClass("nav_flow_li");

        // 设置动态三级标签
        // 移除所有三级节点
        $(".nav_li_all").siblings("li").remove();
        // 设置三级节点
        ajax_three($nav_li);

        // 删除所有课程信息
        $(".classAll").children().remove();
        // 获取课程信息
        ajax_ShowCourse($nav_li, "http://localhost:8080/ShowCourse", 1, 1);

    })
    // 设置三级节点 点击动画
    $("ul").delegate(".specific_li", "click", function () {
        // 移除所有类名
        $(this).parent().children("li").removeClass("nav_li");
        // 添加类名
        $(this).addClass("nav_li");
        // 获取名字
        var name = $(this).text();

        // 保存课程名
        $.addCookie("courseName", name);
        // 保存网址
        $.addCookie("str_url", "http://localhost:8080/ShowSignleCourse");
        
        // 删除所有课程信息
        $(".classAll").children().remove();
        // 获取课程信息
        ajax_ShowCourse(name, "http://localhost:8080/ShowSignleCourse",
            $.getCookie("pageBegin"), pageSize);

    })

    // 分页
    // 点击上一页
    $(".page_up").click(function () {
        // 取页码
        var num = $.getCookie("pageBegin");
        if(num>1){
            --num;
        }
        // 存页码
        $.addCookie("pageBegin", num);

        // 删除所有课程信息
        $(".classAll").children().remove();
        // 获取课程信息
        ajax_ShowCourse($.getCookie("courseName"), $.getCookie("str_url"),
            $.getCookie("pageBegin"), pageSize);
    })
    // 点击下一页
    $(".page_down").click(function () {
        // 取页码
        var num = $.getCookie("pageBegin");
        ++num;
        // 存页码
        $.addCookie("pageBegin", num);

        // 删除所有课程信息
        $(".classAll").children().remove();
        // 获取课程信息
        ajax_ShowCourse($.getCookie("courseName"), $.getCookie("str_url"),
            $.getCookie("pageBegin"), pageSize);
    })

    // 设置三级节点
    function ajax_three(name) {
        $.ajax({
            url: "http://localhost:8080/ShowKind",
            method: "post",
            data: {
                "name": name
            },
            dataType: "json",
            success: function (list) {
                $.each(list, function (key, value) {
                    var $li = createLi(value.kind_name);
                    $(".nav_specific").append($li);
                })
            },
            error: function (xml) {
                console.log(xml);
            }
        })
    }

    // 创建二级节点
    function createFuLi(obj) {
        var $li = $('<li class="flow_li"><a href="#" target="_self">'+obj+'</a></li>');
        return $li;
    }

    // 创建三级节点 -- 导航栏属性
    function createLi(obj) {
        var $li = $('<li class="nav_xie">/</li>\n' +
            '        <li class="specific_li"><a href="#" target="_self">'+obj+'</a></li>');
        return $li;
    }

    // 显示课程
    function ajax_ShowCourse(name, str_url, pageBegin, pageSize) {
        $.ajax({
            url: str_url,
            method: "post",
            data: {
                "two_name": name,
                "pageBegin": pageBegin,
                "pageSize": pageSize
            },
            dataType: "json",
            success: function (list) {
                $.each(list, function (key, value) {
                    var $course = createCourse(value);
                    $(".classAll").append($course);
                })
            },
            error: function (xml) {
                console.log(xml);
            }
        })
    }

    // 创建节点 -- 课程块
    function createCourse(obj) {
        var $course = $('<a href="http://10.96.1.183:8080/index.html?booksId='+obj.course_id+'" target="_self">\n' +
            '            <div class="class">\n' +
            '                <div class="classImg"><img src="images/classImg1.png" width="223"></div>\n' +
            '                <div class="classTit">'+obj.course_name+'</div>\n' +
            '                <div class="classTea">'+obj.company+'</div>\n' +
            '                <div class="classEval">'+obj.page_view+'人学习</div>\n' +
            '                <div class="classPrice">￥'+obj.price+'</div>\n' +
            '                <div id="'+obj.course_id+'"></div>\n' +
            '            </div>\n' +
            '        </a>');
        return $course;
    }
})