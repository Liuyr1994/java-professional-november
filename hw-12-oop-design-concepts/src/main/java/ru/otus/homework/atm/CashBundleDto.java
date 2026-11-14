package ru.otus.homework.atm;

import java.util.Map;

public record CashBundleDto(Map<Nominal, Integer> denomination) {
    public CashBundleDto {
        denomination = denomination == null ? Map.of() : Map.copyOf(denomination);
    }
}
