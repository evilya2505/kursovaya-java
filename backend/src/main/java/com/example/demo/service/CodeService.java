package com.example.demo.service;

import com.example.demo.models.Code;
import org.springframework.web.multipart.MultipartFile;

public interface CodeService {
    Code findByID(Long id);
    void readCSVFile(MultipartFile file) throws Exception;

    Code save(Code code);
}
