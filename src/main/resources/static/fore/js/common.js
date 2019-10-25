"use strict";

/**监听鼠标滚动事件*/
$(window).scroll(function(e){
    let scrollTop = $(document).scrollTop();
    if (scrollTop > 0) {
        $(".roll-top").fadeIn(800);
    } else {
        $(".roll-top").fadeOut(800);
    }
});

/**鼠标点击回到顶部事件*/
$(".roll-top").on("click", function (e) {
    $("html, body").clearQueue().animate({
        scrollTop: 0
    },"ease");
});


