package com.nimap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nimap.controller.exception.ResourceNotFoundException;
import com.nimap.entity.Category;
import com.nimap.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("category not found"));
    }

    public Category updateCategory(Long id, Category category) {
        Category ct= categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("id not found"));
        if(category.getName().length()>0) {
        	ct.setName(category.getName());
        }
        return categoryRepository.save(ct);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
