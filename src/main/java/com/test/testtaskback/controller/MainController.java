package com.test.testtaskback.controller;

import com.test.testtaskback.entities.Interval;
import com.test.testtaskback.services.IntervalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MainController {
    @Autowired
    private IntervalService intervalService;

    @PostMapping("/merge")
    public List<Interval> mergeIntervals(@RequestParam String kind, @RequestBody List<List<?>> intervals) {
        return intervalService.mergeIntervals(kind, intervals);
    }

    @GetMapping("/min")
    public Interval getMinInterval(@RequestParam String kind) {
        return intervalService.getMinInterval(kind);
    }


}
