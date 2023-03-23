package com.example.demo.service.impl;

import com.example.demo.models.Customer;
import com.example.demo.models.Gender;
import com.example.demo.repositories.CustomersRepository;
import com.example.demo.service.CustomerService;
import com.example.demo.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomersRepository customersRepository;
    @Autowired
    private GenderService genderService;
    public CustomerServiceImpl(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }
    @Override
    public void readCSVFile(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("Файл пуст");
        } else {
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                List<List<String>> customersStringLists = new ArrayList<>();
                for (String line = ((BufferedReader) reader).readLine(); line != null; line = ((BufferedReader) reader).readLine()) {
                    List<String> customer = new ArrayList<>();

                    customer = List.of(line.split(","));
                    customersStringLists.add(customer);
                }
                for (int i = 0; i < customersStringLists.size(); i++) {
                    Gender customerGender = genderService.findByID(Long.valueOf(customersStringLists.get(i).get(2)));
                    String firstName = customersStringLists.get(i).get(0);
                    String lastName = customersStringLists.get(i).get(1);

                    Customer newCustomer = new Customer(firstName, lastName, customerGender);

                    customersRepository.save(newCustomer);
                }
            } catch (Exception e){
                System.out.println(e);
            }
        }

    }
}
