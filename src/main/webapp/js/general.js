$(function () {
    righthide();
    backtotop();
    detailsshow();
    jiqiren();
    reglogin();
    $("#header-search").focus(function () {
        $(this).val("")
    }).blur(function () {
        $(this).val("搜索课程...")
    })

})

function righthide() {
    $("#right-gzh").hide();
    $("#erweima").hide();
    $(".right-float-div:eq(1)").hover(function () {
        $("#right-gzh").fadeIn();
        $(".right-icon:eq(0)").fadeOut();
        $("#erweima").fadeIn();
    })
    $("#right-gzh").mouseout(function () {
        $(this).fadeOut();
        $(".right-icon:eq(0)").fadeIn();
        $("#erweima").fadeOut();
    });

    $(".right-float-div:eq(0)").hover(function () {
        $("#right-jqr").fadeIn();
    })
    $("#right-jqr").mouseout(function () {
        $(this).fadeOut();
    })

    $("#right-hddb").hide();
    $(".right-float-div:eq(2)").hover(function () {
        $("#right-hddb").fadeIn();
        $(".right-icon:eq(1)").fadeOut();
    })
    $("#right-hddb").mouseout(function () {
        $(this).fadeOut();
        $(".right-icon:eq(1)").fadeIn();
    });
}
function backtotop() {
    $("#back_to_top").click(function () {
        $('html,body').animate({scrollTop:0},'middle');
    })
}

function detailsshow() {
    $("#touxiang-details").hide();
    $("#touxiang").hover(function () {
        $("#touxiang-details").show();
    },function () {
        $("#touxiang-details").hide();
    })

    $("#touxiang-details").hover(function () {
        $(this).show();
    },function () {
        $(this).hide();
    })
}

function jiqiren() {
    $("#right-jqr").click(function () {
        layer.open({
            type: 1,
            title: false,
            area: ['300px', '500px'],
            closeBtn: 0,
            shadeClose: true,
            skin: 'yourclass',
            content: '<div style="width: 300px;height: 500px;text-align: center;line-height: 500px">这里是机器人界面</div>',
        })
    })
}

function reglogin() {
    $("#weidenglu").click(function () {
        layer.open({
            type: 1,
            title: false,
            area: ['500px', '500px'],
            shadeClose: true,
            skin: 'yourclass',
            content: '<div style="width: 500px;height: 500px;text-align: center;line-height: 500px">这里是登录界面</div>',
        })
        return false;
    })
}

