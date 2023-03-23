package com.example.demo.repositories;

import com.example.demo.models.Gender;
import com.example.demo.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface TypeRepository extends JpaRepository<Type, Long>{
    Type findByID(@Param("number") Long Number);
}
