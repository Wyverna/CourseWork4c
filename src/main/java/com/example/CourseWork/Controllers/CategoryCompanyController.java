package com.example.CourseWork.Controllers;

import com.example.CourseWork.Models.CategoryCompany;
import com.example.CourseWork.Services.CategoryCompanyService;
import com.example.CourseWork.Services.CategoryProductService;
import com.example.CourseWork.Services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/categoryCompanies")
public class CategoryCompanyController {
    @Autowired
    private CategoryCompanyService categoryCompanyService;
    @Autowired
    private CategoryProductService categoryProductService;
    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/")
    public Iterable<CategoryCompany> ListCompany()
    {
        return categoryCompanyService.getAll();
    }

    @GetMapping("/getOrganizations")
    public Iterable<CategoryCompany> ListOrganizations(@RequestParam("categoryProduct") String name)
    {
        return categoryCompanyService.getItemsByCategoryName(name);
    }

    @PostMapping("/postCategory")
    public String postCategory(@RequestParam("organizationName") String organizationName,
                               @RequestParam("categoryProduct") String categoryProduct)
    {
        try
        {
        CategoryCompany categoryCompany = new CategoryCompany();
        categoryCompany.setOrganizationName(organizationService.getItem(organizationName));
        categoryCompany.setCategoryProduct(categoryProductService.getByCategoryProduct(categoryProduct));
        categoryCompanyService.AddItem(categoryCompany);
            return "Succesfuly adding category";
        }
        catch (Exception e)
        {
            return "Bad parameters";
        }
    }
}

