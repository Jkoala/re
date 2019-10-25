"use strict";

// 计算滚动时页面
const $scroll_fixed = $("[scroll-fixed]");
/**监听鼠标滚动事件*/
$(window).scroll(function(e){
    let scrollTop = $(document).scrollTop();
    if (scrollTop > 0) {
        $(".roll-top").fadeIn(800);
    } else {
        $(".roll-top").fadeOut(800);
    }
    // 设置侧边栏鼠标滚动停靠
    $scroll_fixed.each(function (e, obj) {
        console.log($(obj).offset().top);
    });
    // console.log(offsetTops);
    // offsetTops.each(function (e) {
    //     console.log(e);
    //     if (scrollTop > tagsOffsetTop) {
    //         $(".tags:eq(0)").css("position", "fixed").css("top", "20px");
    //         $(".links:eq(0)").css("position", "fixed").css("top", "80px");
    //     } else {
    //         $(".tags:eq(0)").css("position", "");
    //         $(".links:eq(0)").css("position", "");
    //     }
    // });
});

/**鼠标点击回到顶部事件*/
$(".roll-top").on("click", function (e) {
    $("html, body").clearQueue().animate({
        scrollTop: 0
    },"ease");
});


