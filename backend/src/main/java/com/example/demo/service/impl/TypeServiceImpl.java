package com.example.demo.service.impl;

import com.example.demo.models.Code;
import com.example.demo.models.Customer;
import com.example.demo.models.Transaction;
import com.example.demo.models.Type;
import com.example.demo.repositories.TypeRepository;
import com.example.demo.service.TypeService;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    private final TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }
    @Override
    public Type findByID(Long id) {
        return typeRepository.findByID(id);
    }

    @Override
    public void readCSVFile(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("Файл пуст");
        } else {
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                List<List<String>> typeLists = new ArrayList<>();

                for (String line = ((BufferedReader) reader).readLine(); line != null; line = ((BufferedReader) reader).readLine()) {
                    List<String> transaction;

                    transaction = List.of(line.split(","));
                    typeLists.add(transaction);
                }

                for (int i = 0; i < typeLists.size(); i++) {
                    String type = typeLists.get(i).get(0);
                    String description = typeLists.get(i).get(1);

                    Type newType = new Type(type, description);

                    typeRepository.save(newType);
                }

            } catch (Exception e){
            }
        }
    }
}
