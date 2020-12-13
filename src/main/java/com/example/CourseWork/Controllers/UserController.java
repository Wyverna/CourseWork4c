package com.example.CourseWork.Controllers;

import com.example.CourseWork.Models.User;
import com.example.CourseWork.Services.UserService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(path="/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void login(@RequestParam("login") String login, @RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response)
    {
        User user = userService.getItemByLoginUserAndPassword(login,password);
        if(user!=null)
        {
            request.getSession().setAttribute("role",user.getRoleuser().getRolename());
            request.getSession().setAttribute("username",user.getLoginuser());

        }
        else
        {
            request.getSession().setAttribute("message","Wrong login or password");
        }
        try {
            response.sendRedirect("/");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public String register(@RequestParam("emailuser") String emailuser,@RequestParam("passworduser") String passworduser,
                         @RequestParam("loginuser") String loginuser,
                         HttpServletRequest request, HttpServletResponse response)
    {
        User user = new User();
        user.setLoginuser(loginuser);
        user.setPassworduser(passworduser);
        user.setEmailuser(emailuser);
        try {
        userService.AddItem(user);
        return "Successfuly adding user";
        }
        catch (Exception e)
        {
            return "User is existing";
        }
    }


}
