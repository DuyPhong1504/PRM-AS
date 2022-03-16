package com.example.as.model;

public enum ERole {
    ADMIN("ADMIN"),
    USER("USER");

    private String text;

    ERole(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static ERole fromString(String text) {
        for (ERole b : ERole.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
