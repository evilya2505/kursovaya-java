package com.example.demo.service;

import com.example.demo.models.Customer;
import com.example.demo.models.Gender;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GenderService {
    Gender findByID(Long id);
    void readCSVFile(MultipartFile file) throws Exception;
}
