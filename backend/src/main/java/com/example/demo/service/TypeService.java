package com.example.demo.service;

import com.example.demo.models.Gender;
import com.example.demo.models.Type;
import org.springframework.web.multipart.MultipartFile;

public interface TypeService {
    Type findByID(Long id);
    void readCSVFile(MultipartFile file) throws Exception;
}
