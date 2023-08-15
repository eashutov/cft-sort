package ru.shutov.cft.arguments;

public enum Type {
    INTEGER("i"), STRING("s");

    private final String name;

    Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
