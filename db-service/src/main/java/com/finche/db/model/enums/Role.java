package com.finche.db.model.enums;

public enum Role {
    ADMIN("ADMIN"),
    USER("USER");

    private String description;

    Role(String description) {
        this.description = description;
    }
}
