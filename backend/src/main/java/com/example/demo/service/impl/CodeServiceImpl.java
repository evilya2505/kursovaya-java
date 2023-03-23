package com.example.demo.service.impl;

import com.example.demo.models.Code;
import com.example.demo.repositories.CodeRepository;
import com.example.demo.service.CodeService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CodeServiceImpl implements CodeService {
    private final CodeRepository codeRepository;

    public CodeServiceImpl(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }
    @Override
    public Code findByID(Long id) {
        return codeRepository.findByID(id);
    }



    @Override
    public void readCSVFile(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("Файл пуст");
        } else {
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                List<List<String>> codeLists = new ArrayList<>();

                for (String line = ((BufferedReader) reader).readLine(); line != null; line = ((BufferedReader) reader).readLine()) {
                    List<String> code;

                    code = List.of(line.split(","));
                    codeLists.add(code);
                }

                for (int i = 0; i < codeLists.size(); i++) {
                    String type = codeLists.get(i).get(0);
                    String description = codeLists.get(i).get(1);

                    Code newCode = new Code(type, description);

                    this.save(newCode);
                }

            } catch (Exception e){
            }
        }
    }

    @Override
    public Code save(Code code) {
        return codeRepository.save(code);
    }
}
