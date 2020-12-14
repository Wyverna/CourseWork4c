package com.example.CourseWork.Services;

import com.example.CourseWork.Models.ExtraProduct;
import com.example.CourseWork.Models.Organization;
import com.example.CourseWork.Models.Product;
import com.example.CourseWork.Repositories.ExtraProductRepository;
import com.example.CourseWork.Repositories.OrganizationRepository;
import com.example.CourseWork.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtraProductService {
    @Autowired
    private ExtraProductRepository extraProductRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    public Iterable<ExtraProduct> getAll()
    {
        return extraProductRepository.findAll();
    }

    public Iterable<ExtraProduct> getByExtraProductName(String name)
    {
        return extraProductRepository.findAllByNameExtraProduct(name);
    }

    public Iterable<ExtraProduct> getByOrganizationName(String name)
    {
        return extraProductRepository.findAllByOrganizationName(organizationRepository.findByOrganizationName(name));
    }

    public Iterable<ExtraProduct> getByOrganizationNameAndNameProduct(String organizationName,String nameProduct)
    {
        Organization organization = organizationRepository.findByOrganizationName(organizationName);
        return extraProductRepository.findAllByOrganizationNameAndNameProduct(
                organization,
                productRepository.findByNameProductAndOrganizationName(nameProduct,organization));
    }

    public void AddItem(ExtraProduct extraProduct)
    {
        extraProductRepository.save(extraProduct);
    }

    public void DeleteItem(String nameExtraProduct,String nameProduct, String organizationName)
    {
       extraProductRepository.delete( extraProductRepository.findByNameExtraProductAndNameProductAndOrganizationName(nameExtraProduct,
               productRepository.findByNameProductAndOrganizationName(nameProduct,organizationRepository.findByOrganizationName(organizationName)),
               organizationRepository.findByOrganizationName(organizationName)));
    }

    public void UpdateItem(ExtraProduct product)
    {
        extraProductRepository.save(product);
    }

    public void updateCostProduct(String nameExtraProduct,
                                  String nameProduct, String organizationName, Integer cost)
    {
        Organization organization =organizationRepository.findByOrganizationName(organizationName);
        ExtraProduct product= extraProductRepository.findByNameExtraProductAndNameProductAndOrganizationName
                (nameExtraProduct,productRepository.findByNameProductAndOrganizationName(nameProduct,organization),
                        organization);
        product.setUnitCost(cost);
        UpdateItem(product);
    }

    public ExtraProduct getCostProduct(String nameExtraProduct,String nameProduct, String organizationName)
    {
        Organization organization = organizationRepository.findByOrganizationName(organizationName);
        return extraProductRepository.findByNameExtraProductAndNameProductAndOrganizationName(nameExtraProduct,
                productRepository.findByNameProductAndOrganizationName(nameProduct,organization),
                organization);
    }
}
