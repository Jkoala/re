"use strict";
// 计算初始页面元素的offsetTop TODO 这里还是只能用于index页面的side部分，其他部分存在bug
let $scroll_fixed = $("[scroll-fixed]");
let scroll_fixed_offsetTop = [];
let scroll_fixed_top = [];
$scroll_fixed.each(function (index, obj) {
    scroll_fixed_offsetTop.push($(obj).offset().top);
    if (index === 0 || index === "0") {
        scroll_fixed_top.push(0);
    } else {
        scroll_fixed_top.push($scroll_fixed.eq(index - 1).outerHeight() + 20);
    }
});

// 简单的节流函数
function throttle(func, wait, mustRun) {
    var timeout, startTime = new Date();
    return function () {
        var context = this, args = arguments, curTime = new Date();
        clearTimeout(timeout);
        // 如果达到了规定的触发时间间隔，触发 handler
        if (curTime - startTime >= mustRun) {
            func.apply(context, args);
            startTime = curTime;
            // 没达到触发间隔，重新设定定时器
        } else {
            timeout = setTimeout(func, wait);
        }
    };
}

// 实际想绑定在 scroll 事件上的 handler
function realFunc() {
    let scrollTop = $("html, body").scrollTop();
    let navHeight = $("#header .side-nav-header").eq(0).outerHeight();
    if (scrollTop > 0) {
        $(".roll-top").fadeIn(800);
    } else {
        $(".roll-top").fadeOut(800);
    }
    if (scrollTop > navHeight) {
        $(".side-nav-header").addClass('fixed');
    } else {
        $(".side-nav-header").removeClass("fixed")
    }
    $scroll_fixed.each(function (index, obj) {
        if (scrollTop >= scroll_fixed_offsetTop[index]) {
            $(obj).addClass("pf");
            $(obj).css("top", scroll_fixed_top[index]);
            $(obj).css("width", "inherit");
        } else {
            $(obj).css("top", "inherit");
            $(obj).removeClass("pf");
        }
    });
}

// 采用了节流函数
$(window).scroll(throttle(realFunc, 20, 1000));

/**鼠标点击回到顶部事件*/
$(".roll-top").on("click", function (e) {
    $("html, body").clearQueue().animate({
        scrollTop: 0
    }, "ease");
});


