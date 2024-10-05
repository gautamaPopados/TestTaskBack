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

    private int start;
    private int end;
    private String kind;

    public Interval() {

    }

    public Interval(int start, int end, String kind) {
        this.start = start;
        this.end = end;
        this.kind = "start";
    }
}
