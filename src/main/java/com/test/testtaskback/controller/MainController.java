package com.test.testtaskback.controller;

import com.test.testtaskback.entities.Interval;
import com.test.testtaskback.repositories.IntervalRepository;
import com.test.testtaskback.services.IntervalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MainController {
    @Autowired
    private IntervalService intervalService;
    @Autowired
    private IntervalRepository intervalRepository;

    @PostMapping("/intervals/merge")
    public void addIntervals(@RequestParam String kind, @RequestBody List<Interval<?>> intervals) {

        List<Interval> intervalEntities = new ArrayList<>();

        if (kind.equals("digits")) {
            // Преобразуем массив для интервалов с цифрами
            intervalEntities = intervals.stream()
                    .map(arr -> new Interval((Integer) arr.get(0), (Integer) arr.get(1)))
                    .collect(Collectors.toList());
        } else if (kind.equals("letters")) {
            // Преобразуем массив для интервалов с буквами
            intervalEntities = intervals.stream()
                    .map(arr -> new Interval(((String) arr.get(0)).charAt(0), ((String) arr.get(1)).charAt(0)))
                    .collect(Collectors.toList());
        } else {
            return ResponseEntity.badRequest().body(null); // Неизвестный тип
        }

    }


}
