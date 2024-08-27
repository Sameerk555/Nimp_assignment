package com.nimap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nimap.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

