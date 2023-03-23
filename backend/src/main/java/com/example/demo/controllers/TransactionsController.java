package com.example.demo.controllers;
import com.example.demo.models.*;
import com.example.demo.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Paths;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping
public class TransactionsController {
    private final TransactionService transactionService;

    public TransactionsController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(value = "/abs-transaction/{id}")
    public ResponseEntity getMostFreqTran(@PathVariable Long id) {
        Integer result = transactionService.findMostFreqModule(id);
        String filePath = System.getProperty("user.dir") + Paths.get("\\src\\main\\resources\\static\\abs.csv");

        transactionService.writeToCSV(filePath, result);

        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/max-transaction/{id}")
    public ResponseEntity getMaxTran(@PathVariable Long id) {
        Integer result = transactionService.findMax(id);
        String filePath = System.getProperty("user.dir") + Paths.get("\\src\\main\\resources\\static\\max.csv");

        transactionService.writeToCSV(filePath, result);

        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/abs-transactions")
    public ResponseEntity getGrouppedByTrans() {
        String filePath = System.getProperty("user.dir") + Paths.get("\\src\\main\\resources\\static\\groupped-abs.csv");
        List<Transaction> result = transactionService.getTransactionsGrouppedBy();
        String resultString = "";

        try {
            resultString = transactionService.convertToJson(result);
            transactionService.writeToCSVList(filePath, result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok(resultString);
    }

    @GetMapping(value = "/max-transactions")
    public ResponseEntity getGrouppedByMaxTrans() {
        String filePath = System.getProperty("user.dir") + Paths.get("\\src\\main\\resources\\static\\groupped-max.csv");
        List<Transaction> result = transactionService.getTransactionsMaxGrouppedBy();
        String resultString = "";

        try {
            resultString = transactionService.convertToJson(result);
            transactionService.writeToCSVList(filePath, result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok(resultString);
    }

    @PostMapping(value = "/transaction/upload-csv")
    public void uploadCSVFile(@RequestParam("file") MultipartFile file) {
        System.out.println(file);
        try {
            transactionService.readCSVFile(file);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
