package com.example.CourseWork.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.CourseWork.Models.*;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface OrganizationRepository extends CrudRepository<Organization, Integer> {
    Organization findByOrganizationName(String name);
}