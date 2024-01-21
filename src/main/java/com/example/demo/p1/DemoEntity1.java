package com.example.demo.p1;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoEntity1 {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @OneToOne(cascade = CascadeType.REMOVE)
    private DemoEntity1 child;

}
