package com.example.demo.controllers;
import com.example.demo.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping
public class CustomersController {
    private final CustomerService customerService;

    public CustomersController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/customer/upload-csv")
    public void uploadCSVFile(@RequestParam("file") MultipartFile file) {
        try {
            customerService.readCSVFile(file);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}