package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    private String date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "code_id", nullable = false)
    private Code code;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;
    private int amount;
    private int term_id;

    public Transaction(Customer customer, String date, Code code, Type type, int amount, int term_id) {
        this.customer = customer;
        this.date = date;
        this.code = code;
        this.type = type;
        this.amount = amount;
        this.term_id = term_id;
    }
}