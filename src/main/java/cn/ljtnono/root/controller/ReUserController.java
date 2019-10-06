package cn.ljtnono.root.controller;


import cn.ljtnono.root.entity.ReUser;
import cn.ljtnono.root.pojo.JsonResult;
import cn.ljtnono.root.service.IReUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import cn.ljtnono.root.controller.common.BaseController;

import java.util.Arrays;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ljt
 * @since 2019-10-06
 */
@RestController
@RequestMapping("/root/re-user")
public class ReUserController extends BaseController {

    @Autowired
    private IReUserService iReUserService;

    @PostMapping("/login")
    public JsonResult login(@RequestParam("username") String username) {

        return null;
    }


    @GetMapping("/logout")
    public JsonResult logout() {

        return null;
    }


    public JsonResult updatePassword() {
        return null;
    }
}
