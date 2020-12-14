package com.example.CourseWork.Services;

import com.example.CourseWork.Repositories.*;
import com.example.CourseWork.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryProductService {
    @Autowired
    private CategoryProductRepository categoryProductRepository;

    public Iterable<CategoryProduct> getAll()
    {
        return categoryProductRepository.findAll();
    }

    public CategoryProduct getByCategoryProduct(String category)
    {
        return categoryProductRepository.findByCategoryProductsName(category);
    }

    public void AddItem(CategoryProduct categoryProduct)
    {
        categoryProductRepository.save(categoryProduct);
    }
}
