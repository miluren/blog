package com.z.blog.controller;

import com.z.blog.model.entity.User;
import com.z.blog.model.form.LoginForm;
import com.z.blog.model.vo.ResultVo;
import com.z.blog.service.IUserService;
import com.z.blog.utils.JwtUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 登录注册
 * @author Mr zhang
 * @date 2020/12/18
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private IUserService iUserService;

    /**
     * 登录
     * @param loginForm 登陆的表单
     * @param response HttpServletResponse
     * @return ResultVo
     */
    @PostMapping("/login")
    public ResultVo login(@RequestBody LoginForm loginForm, HttpServletResponse response) {
        User login = iUserService.login(loginForm.getUsername(), loginForm.getPassword());
        Md5Hash hash = new Md5Hash(loginForm.getPassword(), login.getSalt(), 3);
        if (login.getPassword().equals(hash.toHex())) {
            String token = JwtUtil.createToken(login.getUsername(), login.getId());
            response.setHeader("token", token);
            return ResultVo.ok("登录成功");
        } else {
            return ResultVo.error("用户名或密码错误");
        }

    }

    @RequestMapping("/exit")
    public ResultVo exit(){
        System.out.println("退出");
        return ResultVo.ok("退出成功");
    }
}
