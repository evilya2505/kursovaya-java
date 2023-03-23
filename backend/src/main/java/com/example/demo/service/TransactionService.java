package com.example.demo.service;

import com.example.demo.models.Customer;
import com.example.demo.models.Transaction;
import org.json.JSONException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TransactionService {
    void readCSVFile(MultipartFile file) throws Exception;
    void writeToCSVList(String filePath, List<Transaction> result);
    void writeToCSV(String filePath,Integer result);
    String convertToJson(List<Transaction> result) throws JSONException;
    Integer findMax(Long id);
    Integer findMostFreqModule(Long id);
    List<Transaction> getTransactionsGrouppedBy();
    List<Transaction> getTransactionsMaxGrouppedBy();
}
