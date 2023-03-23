package com.example.demo.repositories;

import com.example.demo.models.Customer;
import com.example.demo.models.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public interface CustomersRepository extends JpaRepository<Customer, Long>{
    Customer findByID(@Param("number") Long Number);
}
