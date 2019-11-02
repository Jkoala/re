package cn.ljtnono.re.controller;


import cn.ljtnono.re.entity.ReUser;
import cn.ljtnono.re.pojo.JsonResult;
import cn.ljtnono.re.service.IReUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.ljtnono.re.controller.common.BaseController;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collections;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ljt
 * @since 2019-10-06
 */
@RestController
@RequestMapping("/user")
public class ReUserController extends BaseController {

    @Autowired
    private IReUserService iReUserService;

    @GetMapping("/{id}")
    public JsonResult getUserById(@PathVariable Integer id, HttpSession session) {
        ReUser byId = iReUserService.getById(id);
        session.setAttribute("user", byId);
        return JsonResult.success(Collections.singletonList(byId), 1);
    }

    @PostMapping("/login")
    public JsonResult login(@RequestParam("username") String username) {
        return null;
    }


    @GetMapping("/logout")
    public JsonResult logout() {

        return null;
    }
}
