package com.example.demo.service;

import com.example.demo.models.Customer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CustomerService {
    void readCSVFile(MultipartFile file) throws Exception;
}
