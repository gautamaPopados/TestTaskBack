package com.test.testtaskback.repositories;

import com.test.testtaskback.entities.Interval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
    public interface IntervalRepository extends JpaRepository<Interval, Long> {
        List<Interval> findByKind(String kind);
}
