package com.example.CourseWork.Controllers;

import com.example.CourseWork.Models.CategoryProduct;
import com.example.CourseWork.Models.RoleUser;
import com.example.CourseWork.Models.User;
import com.example.CourseWork.Repositories.RoleUserRepository;
import com.example.CourseWork.Services.CategoryProductService;
import com.example.CourseWork.Services.RoleUserService;
import com.example.CourseWork.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path="/api/settings")
public class SettingsController {
    @Autowired
    UserService userService;
    @Autowired
    CategoryProductService categoryProductService;
    @Autowired
    RoleUserService roleUserService;

    @GetMapping("/")
    public String AddTestData()
    {
        RoleUser roleUser =new RoleUser();
        roleUser.setRolename("user");
        RoleUser roleUser2 =new RoleUser();
        roleUser2.setRolename("admin");
        CategoryProduct categoryProduct=new CategoryProduct();
        categoryProduct.setCategoryProductsName("keyboards");
        try{
            roleUserService.AddItem(roleUser);
            roleUserService.AddItem(roleUser2);
            categoryProductService.AddItem(categoryProduct);
        }
        catch(Exception e)
        {
            return "Data is existing";
        }
        User user = new User();
        user.setLoginuser("admin");
        user.setEmailuser("123@gmail.com");
        user.setRoleuser(roleUserService.GetByName("admin"));
        user.setPassworduser("202cb962ac59075b964b07152d234b70");
        try
        {
            userService.AddItem(user);
            return "Success adding test data";
        }
        catch(Exception e)
        {
            return "Data is existing";
        }
    }

    @GetMapping("/session")
    public String Session(@RequestParam("time") Integer time, HttpServletRequest request)
    {
        request.getSession().setMaxInactiveInterval(time);
        return "Success set interval";
    }
}
