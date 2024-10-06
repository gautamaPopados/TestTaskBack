package com.test.testtaskback.services;

import com.test.testtaskback.entities.Interval;
import com.test.testtaskback.repositories.IntervalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class IntervalService {

    @Autowired
    private IntervalRepository intervalRepository;

    public List<Interval> mergeIntervals(String kind, List<List<?>> newIntervals) {

        List<Interval> existingIntervals = intervalRepository.findByKind(kind);

        List<Interval> mergedIntervals = merge(existingIntervals, convertToEntity(kind ,newIntervals));

        intervalRepository.saveAll(mergedIntervals);
        return mergedIntervals;
    }

    private List<Interval> merge(List<Interval> existingIntervals, List<Interval> newIntervals) {
        List<Interval> allIntervals = new ArrayList<>();
        allIntervals.addAll(existingIntervals);
        allIntervals.addAll(newIntervals);

        if (allIntervals.isEmpty()) {
            return allIntervals;
        }

        allIntervals.sort(Comparator.comparing(Interval::getLeftBorder));

        List<Interval> mergedIntervals = new ArrayList<>();

        Interval currentInterval = allIntervals.get(0);

        for (int i = 1; i < allIntervals.size(); i++) {
            Interval nextInterval = allIntervals.get(i);

            if (nextInterval.getLeftBorder().compareTo(currentInterval.getRightBorder()) <= 0) {
                currentInterval.setRightBorder(max(currentInterval.getRightBorder(), nextInterval.getRightBorder()));
            } else {
                mergedIntervals.add(currentInterval);
                currentInterval = nextInterval;
            }
        }

        mergedIntervals.add(currentInterval);

        return mergedIntervals;
    }

    private String max(String a, String b) {
        return a.compareTo(b) >= 0 ? a : b;
    }

    public Interval getMinInterval(String kind) {
        return intervalRepository.findByKind(kind)
                .stream()
                .min(Comparator.comparing(Interval::getLeftBorder).thenComparing(Interval::getRightBorder))
                .orElseThrow(() -> new RuntimeException("Интервалы не найдены"));
    }

    public List<Interval> convertToEntity(String kind, List<List<?>> intervalList) {
        List<Interval> intervals = new ArrayList<>();

        for (List<?> pair : intervalList) {
            if (pair.size() == 2) {
                Interval interval = new Interval(
                        pair.get(0).toString(),
                        pair.get(1).toString(),
                        kind
                );
                intervals.add(interval);
            }
        }

        return intervals;
    }
}
