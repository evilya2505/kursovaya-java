package com.example.demo.service.impl;

import com.example.demo.models.Gender;
import com.example.demo.models.Type;
import com.example.demo.repositories.CustomersRepository;
import com.example.demo.repositories.GenderRepository;
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
public class GenderServiceImpl implements GenderService {
    private final GenderRepository genderRepository;

    public GenderServiceImpl(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }
    @Override
    public Gender findByID(Long id) {
        return genderRepository.findByID(id);
    }

    @Override
    public void readCSVFile(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("Файл пуст");
        } else {
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                List<List<String>> genderLists = new ArrayList<>();

                for (String line = ((BufferedReader) reader).readLine(); line != null; line = ((BufferedReader) reader).readLine()) {
                    List<String> gender;

                    gender = List.of(line.split(","));
                    genderLists.add(gender);
                }

                for (int i = 0; i < genderLists.size(); i++) {
                    String name = genderLists.get(i).get(0);

                    Gender newGender = new Gender(name);

                    genderRepository.save(newGender);
                }

            } catch (Exception e){
            }
        }

    }
}
