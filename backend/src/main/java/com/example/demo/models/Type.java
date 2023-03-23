package com.example.demo.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name = "Type.findByID",
        query = "SELECT t FROM Type t where t.id = :number")
public class Type {
    @Id
    @SequenceGenerator(name = "typesIdSeq", sequenceName = "types_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "typesIdSeq")
    private Long id;
    private String type;
    private String description;
    @OneToMany(mappedBy="type", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    public Type(String type, String description) {
        this.type = type;
        this.description = description;
    }
}