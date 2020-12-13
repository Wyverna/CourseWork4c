package com.example.CourseWork.Services;

import com.example.CourseWork.Repositories.*;
import com.example.CourseWork.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private CategoryProductRepository categoryProductRepository;

    public Iterable<Product> getAll()
    {
        return productRepository.findAll();
    }

    public Iterable<Product> getItemsByCategory(String name)
    {
        return productRepository.findAllByCategoryProduct(categoryProductRepository.findByCategoryProductsName(name));
    }

    public Iterable<Product> getItemsByOrganization(String name)
    {
        return productRepository.findAllByOrganizationName(organizationRepository.findByOrganizationName(name));
    }

    public Iterable<Product> getItemsByProduct(String name)
    {
        return productRepository.findAllByNameProduct(name);
    }

    public void AddItem(Product product)
    {
        productRepository.save(product);
    }

    public void deleteItem(String nameProduct,String organizationName)
    {
        productRepository.delete(productRepository.findByNameProductAndOrganizationName(nameProduct,
                organizationRepository.findByOrganizationName(organizationName)));
    }

    public Product getCostProduct(String nameProduct, String organizationName)
    {
        return productRepository.findByNameProductAndOrganizationName(nameProduct,
                organizationRepository.findByOrganizationName(organizationName));
    }

    public void UpdateItem(Product product)
    {
        productRepository.save(product);
    }

    public void updateCostProduct(String nameProduct, String organizationName, Integer cost)
    {
       Product product= productRepository.findByNameProductAndOrganizationName(nameProduct,
               organizationRepository.findByOrganizationName(organizationName));
       product.setCostProduct(cost);
       UpdateItem(product);
    }

    public Product getItemByNameProduct(String name)
    {
        return productRepository.findByNameProduct(name);
    }
}
