package ru.shutov.cft.arguments;

public enum Order {
    ASCEND("a"), DESCEND("d");

    private final String name;

    Order(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
