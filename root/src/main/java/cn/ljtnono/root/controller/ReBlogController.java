package cn.ljtnono.root.controller;

import cn.ljtnono.root.service.ReBlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *  博客类的controller
 *  @author 凌家童
 *  @date 2019/7/8
 *  @version 1.0
 *
*/
@Controller
public class ReBlogController {

    private Logger logger = LoggerFactory.getLogger(ReBlogController.class);

    @Autowired
    private ReBlogService reBlogService;




}
