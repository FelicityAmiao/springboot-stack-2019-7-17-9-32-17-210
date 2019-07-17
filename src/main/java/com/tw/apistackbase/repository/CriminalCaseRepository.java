package com.tw.apistackbase.repository;

import com.tw.apistackbase.entity.CriminalCase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CriminalCaseRepository extends JpaRepository<CriminalCase, Long> {
    List<CriminalCase> findByOrderByCaseOccurrenceTimeDesc();
    List<CriminalCase> findByCaseName(String caseName);
}
