package com.finche.db.model.enums;

public enum Status {
    APPROVED("APPROVED"),
    REJECTED("REJECTED"),
    IN_PROGRESS("IN_PROGRESS"),
    CANCELLED("CANCELLED"),
    OPEN("OPEN");


    private String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
