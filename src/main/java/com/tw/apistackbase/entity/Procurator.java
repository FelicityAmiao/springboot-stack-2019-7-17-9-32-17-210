package com.tw.apistackbase.entity;

import javax.persistence.*;

@Entity
public class Procurator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 255, nullable = false)
    private String procuratorName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProcuratorName() {
        return procuratorName;
    }

    public void setProcuratorName(String procuratorName) {
        this.procuratorName = procuratorName;
    }
}
