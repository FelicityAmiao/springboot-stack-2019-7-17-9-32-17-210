package com.tw.apistackbase.entity;

import javax.persistence.*;

@Entity
public class CriminalCase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 255, nullable = false)
    private String caseName;

    @Column(nullable = false)
    private Long caseOccurrenceTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public Long getCaseOccurrenceTime() {
        return caseOccurrenceTime;
    }

    public void setCaseOccurrenceTime(Long caseOccurrenceTime) {
        this.caseOccurrenceTime = caseOccurrenceTime;
    }

    public static String createLongStringOver260() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 100; i++) {
            stringBuffer.append("a");
        }
        return stringBuffer.toString();
    }
}
