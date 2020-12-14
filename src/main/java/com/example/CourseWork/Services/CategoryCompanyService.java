package com.example.CourseWork.Services;

import com.example.CourseWork.Repositories.*;
import com.example.CourseWork.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryCompanyService {
    @Autowired
    CategoryCompanyRepository categoryCompanyRepository;
    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    CategoryProductRepository categoryProductRepository;

    public void DeleteItem(String organizationName, String categoryProductName)
    {
        categoryCompanyRepository.delete(
                getItemByOrganizationNameAndCategoryProduct(organizationName,categoryProductName));
    }

    public void AddItem(CategoryCompany categoryCompany)
    {
        categoryCompanyRepository.save(categoryCompany);
    }

    public Iterable<CategoryCompany> getItemsByOrganizationName(String name)
    {
        return categoryCompanyRepository.findAllByOrganizationName(organizationRepository.findByOrganizationName(name));
    }

    public Iterable<CategoryCompany> getItemsByCategoryName(String name)
    {
        return categoryCompanyRepository.findAllByCategoryProduct(categoryProductRepository.findByCategoryProductsName(name));
    }

    public Iterable<CategoryCompany> getAll()
    {
        return categoryCompanyRepository.findAll();
    }

    public CategoryCompany getItemByOrganizationNameAndCategoryProduct(String organizationName, String categoryName)
    {
        return categoryCompanyRepository.findByOrganizationNameAndCategoryProduct(organizationRepository.findByOrganizationName(organizationName),
                categoryProductRepository.findByCategoryProductsName(categoryName));
    }
}
