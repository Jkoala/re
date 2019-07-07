package cn.ljtnono.root.controller;

import cn.ljtnono.root.entity.ReBlog;
import cn.ljtnono.root.mapper.ReBlogDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    private Logger logger = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("/hello")
    public ReBlog hello() {
        logger.debug("hello");
        return reBlogDao.selectByPrimaryKey(1);
    }
}
