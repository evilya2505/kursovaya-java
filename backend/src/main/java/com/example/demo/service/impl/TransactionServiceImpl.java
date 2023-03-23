package com.example.demo.service.impl;
import com.example.demo.models.Code;
import com.example.demo.models.Customer;
import com.example.demo.models.Transaction;
import com.example.demo.models.Type;
import com.example.demo.repositories.CodeRepository;
import com.example.demo.repositories.CustomersRepository;
import com.example.demo.repositories.TransactionRepository;
import com.example.demo.repositories.TypeRepository;
import com.example.demo.service.TransactionService;
import com.opencsv.CSVWriter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    @Autowired
    private CustomersRepository customersRepository;
    @Autowired
    private CodeRepository codeRepository;
    @Autowired
    private TypeRepository typeRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    @Override
    public void readCSVFile(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("Файл пуст");
        } else {
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                List<List<String>> transactionsStringLists = new ArrayList<>();

                for (String line = ((BufferedReader) reader).readLine(); line != null; line = ((BufferedReader) reader).readLine()) {
                    List<String> transaction;

                    transaction = List.of(line.split(","));
                    transactionsStringLists.add(transaction);
                }

                for (int i = 0; i < transactionsStringLists.size(); i++) {
                    Customer customer = customersRepository.findByID(Long.valueOf(transactionsStringLists.get(i).get(0)));
                    String date = transactionsStringLists.get(i).get(1);
                    Code code = codeRepository.findByID(Long.valueOf(transactionsStringLists.get(i).get(2)));
                    Type type = typeRepository.findByID(Long.valueOf(transactionsStringLists.get(i).get(3)));
                    Integer amount = Integer.valueOf(transactionsStringLists.get(i).get(4));
                    Integer term_id = Integer.valueOf(transactionsStringLists.get(i).get(5));


                    Transaction newTransaction = new Transaction(customer, date, code, type, amount, term_id);

                    transactionRepository.save(newTransaction);
                }

            } catch (Exception e){
            }
        }
    }

    @Override
    public void writeToCSVList(String filePath, List<Transaction> result) {
        // Запиь данных в CSV файл
        File file = new File(filePath);
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);

            for (int i = 0; i < result.size(); i++) {
                String[] data1 = {String.valueOf(result.get(i).getId()), String.valueOf(result.get(i).getCustomer().getId()), String.valueOf(result.get(i).getAmount()), String.valueOf(result.get(i).getTerm_id()), String.valueOf(result.get(i).getCode().getId()), String.valueOf(result.get(i).getType().getId())};
                writer.writeNext(data1);
            }

            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeToCSV(String filePath, Integer result) {
        File file = new File(filePath);
        String[] temp = new String[1];
        temp[0] = String.valueOf(result);
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);
            writer.writeNext(temp);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String convertToJson(List<Transaction> result) throws JSONException {
        var resultJson = new JSONArray();

        for (int i = 0; i < result.size(); i++) {
            var transaction = new JSONObject();

            transaction.put("id", result.get(i).getId());
            transaction.put("customer_id", result.get(i).getCustomer().getId());
            transaction.put("datetime", result.get(i).getDate());
            transaction.put("amount", result.get(i).getAmount());
            transaction.put("term_id", result.get(i).getTerm_id());
            transaction.put("code_id", result.get(i).getCode().getType());
            transaction.put("type_id", result.get(i).getType().getType());

            resultJson.put(transaction);
        }

        return resultJson.toString();
    }

    @Override
    public Integer findMax(Long id) {
        return transactionRepository.findMax(id);
    }

    @Override
    public Integer findMostFreqModule(Long id) {
        return transactionRepository.findMostFreqModule(id);
    }

    @Override
    public List<Transaction> getTransactionsGrouppedBy() {
        return transactionRepository.getTransactionsGrouppedBy();
    }

    @Override
    public List<Transaction> getTransactionsMaxGrouppedBy() {
        return transactionRepository.getTransactionsMaxGrouppedBy();
    }
}
