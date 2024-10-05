package com.test.testtaskback.services;

import com.test.testtaskback.entities.Interval;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IntervalService {

    public List<Interval> mergeIntervals(List<Interval> intervals) {
        if (intervals.isEmpty()) return intervals;

        intervals.sort((a, b) -> {
            int startComparison = a.getStart().compareTo(b.getStart());
            if (startComparison == 0) {
                return a.getEnd().compareTo(b.getEnd());
            }
            return startComparison;
        });

        List<Interval<T>> mergedIntervals = new ArrayList<>();
        Interval<T> current = intervals.get(0);

        for (int i = 1; i < intervals.size(); i++) {
            Interval<T> next = intervals.get(i);

            if (current.getEnd().compareTo(next.getStart()) >= 0) {
                current.setEnd(max(current.getEnd(), next.getEnd()));
            } else {
                mergedIntervals.add(current);
                current = next;
            }
        }

        mergedIntervals.add(current);
        return mergedIntervals;
    }

    private <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }
}
