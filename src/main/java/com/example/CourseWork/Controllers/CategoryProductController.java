package com.example.CourseWork.Controllers;

import com.example.CourseWork.Models.CategoryProduct;
import com.example.CourseWork.Services.CategoryProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/categoryProducts")
public class CategoryProductController {
    @Autowired
    private CategoryProductService categoryProductService;

    @GetMapping("/")
    public Iterable<CategoryProduct> ListCategory()
    {
        return categoryProductService.getAll();
    }
}
