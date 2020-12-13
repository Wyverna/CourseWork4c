package com.example.CourseWork.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.CourseWork.Models.*;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ProductRepository extends CrudRepository<Product, Integer> {
    Iterable<Product> findAllByCategoryProduct(CategoryProduct name);
    Iterable<Product> findAllByOrganizationName(Organization name);
    Iterable<Product> findAllByNameProduct(String name);
    Product findByNameProduct(String name);
    void deleteByNameProductAndOrganizationName(String nameProduct, Organization organizationName);
    Product findByNameProductAndOrganizationName(String nameProduct, Organization organizationName);
    Product findDistinctByNameProduct(String name);
}