package com.example.demo.repositories;

import com.example.demo.models.Customer;
import com.example.demo.models.Gender;
import com.example.demo.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "select amount from  public.transaction where customer_id = :number and amount > 0 order by amount desc limit 1",
            nativeQuery = true)
    Integer findMax(@Param("number") Long Number);
    @Query(value = "select abs from (SELECT abs(amount), COUNT(*) FROM public.transaction WHERE customer_id = :number GROUP BY abs(amount) order by count desc LIMIT 1) as foo;",
            nativeQuery = true)
    Integer findMostFreqModule(@Param("number") Long Number);
    @Query(value = "SELECT id, amount, date, term_id, code_id, customer_id, type_id FROM public.transaction GROUP BY customer_id, id order by abs(amount), customer_id ",
            nativeQuery = true)
    List<Transaction> getTransactionsGrouppedBy();
    @Query(value = "select * from  public.transaction where amount > 0 order by customer_id, amount desc",
            nativeQuery = true)
    List<Transaction> getTransactionsMaxGrouppedBy();
}
