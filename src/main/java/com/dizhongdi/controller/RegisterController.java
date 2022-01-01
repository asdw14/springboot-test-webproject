package com.dizhongdi.controller;

import com.dizhongdi.mapper.UserMapper;
import com.dizhongdi.pojo.User;
import com.dizhongdi.service.RegisterCodeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName:RegisterController
 * Package:com.dizhongdi.controller
 * Description:
 *
 * @Date: 2021/8/1 18:44
 * @Author:dizhongdi
 */
@Controller
public class RegisterController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RegisterCodeServiceImpl codeService;

    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

    @RequestMapping("/user/register")
    public String register(@RequestParam("username") String username, @RequestParam("password") String password,@RequestParam("emailAddress") String emailAddress,@RequestParam("code")  String code, Model model){
        User user = new User(username, password, emailAddress);

        int i = codeService.comparisonCode(emailAddress, code);
        System.out.println(i);
        if (i==1){
            //验证码正确
            userMapper.addUser(user);
        }else if (i==0){
            model.addAttribute("msg","验证码过期");
            model.addAttribute("user",user);
            return "register2";
        }else {
            model.addAttribute("msg","验证码错误");
            model.addAttribute("user",user);
            return "register2";
        }

        model.addAttribute("msg","注册成功");
        return "redirect:/main.html";
    }

    @RequestMapping("/sendCode")
    @ResponseBody
    public String sendCode(String emailAddress){
        String s = codeService.sendEmailCode(emailAddress);
        System.out.println(s);
        return "success";
    }

    @RequestMapping("/comparisonCode")
    @ResponseBody
    public String sendCode(String emailAddress,String code){
        int i = codeService.comparisonCode(emailAddress, code);
        if (i==1){
            return "验证码正确";
        }else if (i==0){
            return "验证码过期,请重新输入";
        }else {
            return "验证码错误,请重新输入";
        }
    }
}
