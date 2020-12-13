package com.example.CourseWork.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.CourseWork.Models.*;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ExtraProductRepository extends CrudRepository<ExtraProduct, Integer> {
    Iterable<ExtraProduct> findAllByNameExtraProduct(String name);
    Iterable<ExtraProduct> findAllByOrganizationName(Organization name);
    void deleteByNameExtraProductAndNameProductAndOrganizationName(String nameExtraProduct,Product nameProduct,
                                                                         Organization organizationName);
    ExtraProduct findByNameExtraProductAndNameProductAndOrganizationName(String nameExtraProduct,Product nameProduct,
                                                                           Organization organizationName);
}