package ru.rivendell.aestheticmenu.enums;

public enum Operator {

    MORE(">"),
    LESS("<"),
    EQUALS("="),
    MORE_OR_EQUALS(">="),
    LESS_OR_EQUALS("<=");

    private String operator;

    Operator(String operator) {
        this.operator = operator;
    }

}
