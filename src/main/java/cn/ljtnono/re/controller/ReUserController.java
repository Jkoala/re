package cn.ljtnono.re.controller;


import cn.ljtnono.re.pojo.JsonResult;
import cn.ljtnono.re.service.IReUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.ljtnono.re.controller.common.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ljt
 * @since 2019-10-06
 */
@RestController
@RequestMapping("/re/user")
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
}
