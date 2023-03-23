package com.example.demo.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name = "Gender.findByID",
        query = "SELECT g FROM Gender g where g.id = :number")
public class Gender {
    @Id
    @SequenceGenerator(name = "gendersIdSeq", sequenceName = "genders_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gendersIdSeq")
    private Long id;
    private String name;
    @OneToMany (mappedBy="gender", cascade = CascadeType.ALL)
    private List<Customer> customers = new ArrayList<>();

    public Gender(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}