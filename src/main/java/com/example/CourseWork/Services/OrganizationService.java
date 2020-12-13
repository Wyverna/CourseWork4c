package com.example.CourseWork.Services;

import com.example.CourseWork.Repositories.*;
import com.example.CourseWork.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    public void AddItem(Organization organization)
    {
        organizationRepository.save(organization);
    }

    public void DeleteItem(Integer id)
    {
        organizationRepository.deleteById(id);
    }

    public Iterable<Organization> getAll()
    {
        return organizationRepository.findAll();
    }

    public Organization getItem(String name)
    {
        return organizationRepository.findByOrganizationName(name);
    }
}
