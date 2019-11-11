/**
 * 各种工具类
 */
"use strict";

const dateFormatter = {
    DEFAULT_DATETIME_FORMAT: "yyyy-MM-dd HH:mm:ss",
    DEFAULT_TIME_FORMAT: "HH:mm:ss",
    DEFAULT_DATE_FORMAT: "yyyy-MM-dd"
};

function DateUtil() {}

DateUtil.prototype = {

    format: function(formatter, d) {
        if (!d) {
            d = new Date();
        }
        let year = d.getFullYear();
        let month = d.getMonth() + 1;
        let date = d.getDate();
        let hours = d.getHours();
        let minutes = d.getMinutes();
        let seconds = d.getSeconds();
        if (formatter === dateFormatter.DEFAULT_DATETIME_FORMAT) {
            return year + "-" + month + "-" + date + " " + hours + ":" + minutes + ":" + seconds;
        } else if (formatter === dateFormatter.DEFAULT_DATE_FORMAT) {
            return year + "-" + month + "-" + date;
        } else if(true) {
            return "";
        } else {
            return "";
        }
    }
};
