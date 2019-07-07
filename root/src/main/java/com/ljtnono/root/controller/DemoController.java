package com.ljtnono.root.controller;

import com.ljtnono.root.entity.ReBlog;
import com.ljtnono.root.mapper.ReBlogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用于演示
 *  @author 凌家童
 *  @date 2019/7/7
 *  @version 1.0
 *
*/
@RestController
public class DemoController {

    @Autowired
    private ReBlogDao reBlogDao;

    @GetMapping("/hello")
    @ResponseBody
    public ReBlog hello() {
        return reBlogDao.selectByPrimaryKey(1);
    }
}
