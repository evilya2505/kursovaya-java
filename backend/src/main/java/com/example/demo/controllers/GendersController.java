package com.example.demo.controllers;

import com.example.demo.service.GenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping
public class GendersController {
    private final GenderService genderService;

    public GendersController(GenderService genderService) {
        this.genderService = genderService;
    }

    @GetMapping(value="/gender/{id}")
    public ResponseEntity getGenderByID(@PathVariable Long id) {
        return ResponseEntity.ok(this.genderService.findByID(id));
    }

    @PostMapping(value = "/gender/upload-csv")
    public void uploadCSVFile(@RequestParam("file") MultipartFile file) {
        try {
            genderService.readCSVFile(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
