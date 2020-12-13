package com.example.CourseWork.Controllers;

import com.example.CourseWork.Models.Product;
import com.example.CourseWork.Services.CategoryProductService;
import com.example.CourseWork.Services.OrganizationService;
import com.example.CourseWork.Services.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryProductService categoryProductService;
    @Autowired
    private OrganizationService organizationService;
    @GetMapping( "/")
    public Iterable<Product> ListProduct()
    {
        return productService.getAll();
    }

    @GetMapping( "/getByProduct")
    public Iterable<Product> ListByProduct(@RequestParam("nameProduct") String nameProduct)
    {
        return productService.getItemsByProduct(nameProduct);
    }

    @GetMapping( "/getByOrganization")
    public Iterable<Product> ListByOrganization(@RequestParam("organizationName") String organizationName)
    {
        return productService.getItemsByOrganization(organizationName);
    }

    @GetMapping( "/getCost")
    public Product getCost(@RequestParam("nameProduct") String nameProduct,
                           @RequestParam("organizationName") String organizationName)
    {
        return productService.getCostProduct(nameProduct,organizationName);
    }

    @PostMapping("/deleteProduct")
    public void DeleteProduct(@RequestParam("nameProduct") String nameProduct,
                              @RequestParam("organizationName") String organizationName)
    {
        productService.deleteItem(nameProduct,organizationName);
    }

    @PostMapping("/updateCost")
    public void UpdateCost(@RequestParam("nameProduct") String nameProduct,
                              @RequestParam("organizationName") String organizationName,
                           @RequestParam("costProduct") Integer costProduct)
    {
        productService.updateCostProduct(nameProduct,organizationName,costProduct);
    }

    @PostMapping("/postProduct")
    public void AddItem(@RequestParam("nameProduct") String nameProduct,
                           @RequestParam("organizationName") String organizationName,
                           @RequestParam("costProduct") Integer costProduct,
                        @RequestParam("categoryProduct") String categoryProduct)
    {
        Product product=new Product();
        product.setCostProduct(costProduct);
        product.setCategoryProduct(categoryProductService.getByCategoryProduct(categoryProduct));
        product.setNameProduct(nameProduct);
        product.setOrganizationName(organizationService.getItem(organizationName));
        productService.AddItem(product);
    }
}
