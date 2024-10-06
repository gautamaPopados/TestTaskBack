package com.test.testtaskback.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "intervals")
public class Interval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String leftBorder;
    private String rightBorder;
    private String kind;

    public Interval() {

    }

    public Interval(String leftBorder, String rightBorder, String kind) {
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
        this.kind = kind;
    }
}
