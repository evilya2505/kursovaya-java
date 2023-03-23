package com.example.demo.repositories;

import com.example.demo.models.Code;
import com.example.demo.models.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CodeRepository extends JpaRepository<Code, Long>{
    Code findByID(@Param("number") Long Number);
}
