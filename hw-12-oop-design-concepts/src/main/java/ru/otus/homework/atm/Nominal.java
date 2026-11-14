package ru.otus.homework.atm;

public enum Nominal {
    ONE_HUNDRED(100),
    FIVE_HUNDRED(500),
    THOUSAND(1000);

    private final int nominal;

    Nominal(int nominal) {
        this.nominal = nominal;
    }

    public int getNominal() {
        return nominal;
    }
}
