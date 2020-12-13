package com.example.CourseWork.Controllers;

import com.example.CourseWork.Models.ExtraProduct;
import com.example.CourseWork.Models.Product;
import com.example.CourseWork.Services.ExtraProductService;
import com.example.CourseWork.Services.OrganizationService;
import com.example.CourseWork.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/extraProducts")
public class ExtraProductController {
    @Autowired
    private ExtraProductService extraProductService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrganizationService organizationService;
    @GetMapping( "/")
    public Iterable<ExtraProduct> ListProduct()
    {
        return extraProductService.getAll();
    }

    @GetMapping( "/getOrganizationsByExtraProduct")
    public Iterable<ExtraProduct> ListByExtraProduct(@RequestParam("nameExtraProduct") String nameExtraProduct)
    {
        return extraProductService.getByExtraProductName(nameExtraProduct);
    }
    @GetMapping( "/getCost")
    public ExtraProduct getCost(@RequestParam("nameExtraProduct") String nameExtraProduct,
                           @RequestParam("nameProduct") String nameProduct,
                           @RequestParam("organizationName") String organizationName)
    {
        return extraProductService.getCostProduct(nameExtraProduct,nameProduct,organizationName);
    }

    @PostMapping("/postExtraProduct")
    public void AddItem(@RequestParam("nameProduct") String nameProduct,
                        @RequestParam("organizationName") String organizationName,
                        @RequestParam("unitCost") Integer costProduct,
                        @RequestParam("nameExtraProduct") String nameExtraProduct)
    {
        ExtraProduct product=new ExtraProduct();
        product.setUnitCost(costProduct);
        product.setNameExtraProduct(nameExtraProduct);
        product.setNameProduct(productService.getItemByNameProduct(nameProduct));
        product.setOrganizationName(organizationService.getItem(organizationName));
        extraProductService.AddItem(product);
    }

    @PostMapping("/deleteExtraProduct")
    public void DeleteExtraProduct(@RequestParam("nameExtraProduct") String extraNameProduct,
                              @RequestParam("nameProduct") String nameProduct,
                              @RequestParam("organizationName") String organizationName)
    {
        extraProductService.DeleteItem(extraNameProduct,nameProduct,organizationName);
    }

    @PostMapping("/updateCost")
    public void UpdateCost(@RequestParam("nameExtraProduct") String nameExtraProduct,
                           @RequestParam("nameProduct") String nameProduct,
                           @RequestParam("organizationName") String organizationName,
                           @RequestParam("costProduct") Integer costProduct)
    {
        extraProductService.updateCostProduct(nameExtraProduct,nameProduct,organizationName,costProduct);
    }
}
