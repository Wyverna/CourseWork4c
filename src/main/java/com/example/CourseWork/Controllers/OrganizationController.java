package com.example.CourseWork.Controllers;


import com.example.CourseWork.Models.Organization;
import com.example.CourseWork.Services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/organizations")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @GetMapping( "/")
    public Iterable<Organization> ListOrganization()
    {
        return organizationService.getAll();
    }

    @PostMapping( "/postOrganization")
    public String AddItem(@RequestParam("organizationName") String organizationName,
                        @RequestParam("addressOrganization") String addressOrganization,
                        @RequestParam("telephone") String telephone)
    {
        Organization organization=new Organization();
        organization.setOrganizationName(organizationName);
        organization.setAddressOrganization(addressOrganization);
        organization.setTelephone(telephone);
        try
        {
            organizationService.AddItem(organization);
            return "Successfuly adding organization";
        }
        catch (Exception e)
        {
            return "Organization is existing";
        }
    }
}
