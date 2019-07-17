package com.tw.apistackbase.entity;

import javax.persistence.*;

@Entity
public class CaseInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 255, nullable = false)
    private String subjectiveCondition;
    @Column(length = 255, nullable = false)
    private String objectiveCondition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectiveCondition() {
        return subjectiveCondition;
    }

    public void setSubjectiveCondition(String subjectiveCondition) {
        this.subjectiveCondition = subjectiveCondition;
    }

    public String getObjectiveCondition() {
        return objectiveCondition;
    }

    public void setObjectiveCondition(String objectiveCondition) {
        this.objectiveCondition = objectiveCondition;
    }
}
