package com.example.demo.repositories;

import com.example.demo.models.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface GenderRepository extends JpaRepository<Gender, Long>{
    Gender findByID(@Param("number") Long Number);
}
