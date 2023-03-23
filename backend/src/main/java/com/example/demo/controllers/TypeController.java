package com.example.demo.controllers;
import com.example.demo.service.TypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping
public class TypeController {
    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @PostMapping(value = "/type/upload-csv")
    public void uploadCSVFile(@RequestParam("file") MultipartFile file) {
        try {
            typeService.readCSVFile(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}