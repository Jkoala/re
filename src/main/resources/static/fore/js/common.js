/**
 * 各种工具类
 */
"use strict";

var dataFormatter = {
    DEFAULT_DATETIME_FORMAT: "yyyy-MM-dd HH:mm:ss",
    DEFAULT_TIME_FORMAT: "HH:mm:ss",
    DEFAULT_DATE_FORMAT: "yyyy-MM-dd"
};

function DateUtil() {}

DateUtil.prototype = {

    format: function(formatter) {
        let d = new Date();
        let year = d.getFullYear();
        let month = d.getMonth() + 1;
        let date = d.getDate();
        let hours = d.getHours();
        let minutes = d.getMinutes();
        let seconds = d.getSeconds();
        if (formatter === dataFormatter.DEFAULT_DATETIME_FORMAT) {
            return year + "-" + month + "-" + date + " " + hours + ":" + minutes + ":" + seconds;
        } else if (formatter === dataFormatter.DEFAULT_DATE_FORMAT) {
            return year + "-" + month + "-" + date;
        } else if(true) {
            return "";
        } else {
            return "";
        }
    }
};
