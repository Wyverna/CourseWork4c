package com.example.CourseWork.Controllers;

import com.example.CourseWork.AOP.AuthorizeAnnotation;
import com.example.CourseWork.AOP.AuthorizeAnnotationAdmin;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path="/")
public class PageController {

    @RequestMapping("/")
    public ModelAndView index(Model model, HttpServletRequest request) {
        return InstallVariables("index",request);
    }

    @RequestMapping("/register")
    public ModelAndView register(Model model, HttpServletRequest request) {
        return InstallVariables("register",request);
    }

	@AuthorizeAnnotation
    @RequestMapping("/CategoryCompany")
    public ModelAndView CategoryCompany(Model model, HttpServletRequest request) {
        return InstallVariables("categoryCompany",request);
    }

	@AuthorizeAnnotation
    @RequestMapping("/organization")
    public ModelAndView Organization(Model model, HttpServletRequest request) {
        return InstallVariables("organization",request);
    }

    @RequestMapping("/order")
	@AuthorizeAnnotation
    public ModelAndView Order(Model model, HttpServletRequest request) {
        return InstallVariables("order",request);
    }

    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        request.getSession().invalidate();
        response.sendRedirect("/");
    }

    @RequestMapping("/product")
	@AuthorizeAnnotationAdmin
    public ModelAndView Product(Model model, HttpServletRequest request) {

        return InstallVariables("product",request);
    }

    @RequestMapping("/extraproduct")
	@AuthorizeAnnotationAdmin
    public ModelAndView ExtraProduct(Model model, HttpServletRequest request) {

        return InstallVariables("extraproduct",request);
    }

    @RequestMapping("/report")
	@AuthorizeAnnotationAdmin
    public ModelAndView Report(Model model, HttpServletRequest request) {

        return InstallVariables("report",request);
    }

    @RequestMapping("/xml")
	@AuthorizeAnnotationAdmin
    public ModelAndView Xml(Model model, HttpServletRequest request) {

        return InstallVariables("xml",request);
    }

    private ModelAndView InstallVariables(String page,HttpServletRequest request)
    {
        ModelAndView modelAndView = new ModelAndView(page);
        HttpSession session = request.getSession();
        String role =(String)session.getAttribute("role");
        String username =(String)session.getAttribute("username");
        modelAndView.addObject("role", role);
        modelAndView.addObject("username", username);
        modelAndView.addObject("message", (String)session.getAttribute("message"));
        session.removeAttribute("message");
        return modelAndView;
    }
}
