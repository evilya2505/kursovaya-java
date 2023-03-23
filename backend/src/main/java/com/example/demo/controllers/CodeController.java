package com.example.demo.controllers;
import com.example.demo.service.CodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping
public class CodeController {
    private final CodeService codeService;

    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @PostMapping(value = "/code/upload-csv")
    public void uploadCSVFile(@RequestParam("file") MultipartFile file) {
        try {
            codeService.readCSVFile(file);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}