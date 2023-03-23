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
@NamedQuery(name = "Code.findByID",
        query = "SELECT c FROM Code c where c.id = :number")
//@NamedQuery(name = "Student.findOnlyTest1",
//        query = "SELECT s FROM Student s where s.firstName = 'test1'")
public class Code {
    @Id
    @SequenceGenerator(name = "codesIdSeq", sequenceName = "codes_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "codesIdSeq")
    private Long id;
    private String type;
    private String description;
    @OneToMany(mappedBy="code", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    public Code(String type, String description) {
        this.type = type;
        this.description = description;
    }
}
