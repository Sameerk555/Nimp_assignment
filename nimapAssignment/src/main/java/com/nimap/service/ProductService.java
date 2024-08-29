package com.nimap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nimap.controller.exception.ResourceNotFoundException;
import com.nimap.entity.Category;
import com.nimap.entity.Product;
import com.nimap.repositories.CategoryRepository;
import com.nimap.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product saveProduct(Product product) throws Exception{
    	Category ct= categoryRepository.findById(product.getCategory().getId()).orElseThrow(()->new ResourceNotFoundException("this category id does not exist"));
    	product.setCategory(ct);
        return productRepository.save(product);
    	
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("product not found in database"));
    }

    public Product updateProduct(Long id, Product product) {
        Product pd= productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("product not found in database"));
        if(product.getName()!=null) {
        	pd.setName(product.getName());
        }
        System.out.println(product.getPrice());
        if(product.getPrice()!=0.0) {
        	pd.setPrice(product.getPrice());
        }
        if(product.getCategory()!=null) {
        	Category ct=categoryRepository.findById(product.getCategory().getId()).orElseThrow(()->new ResourceNotFoundException("category id not found"));
        	pd.setCategory(ct);
        }
        return productRepository.save(pd);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
