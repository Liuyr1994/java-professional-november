package ru.otus.homework.atm;

import java.util.*;

public class Atm {
    private NavigableMap<Nominal, Cassette> cassettesByNominal;

    public Atm(List<Cassette> cassetteList) {
        if (cassetteList == null || cassetteList.isEmpty()) {
            throw new IllegalArgumentException("Передали пустой список с кассетами");
        }
        cassettesByNominal =
                new TreeMap<>(Comparator.comparing(Nominal::getNominal).reversed());
        for (Cassette cassette : cassetteList) {
            if (cassette == null) {
                throw new IllegalArgumentException("Кассета в списке не может быть null");
            }
            if (cassettesByNominal.containsKey(cassette.getNominal())) {
                throw new IllegalArgumentException("Дубликат кассеты для номинала: " + cassette.getNominal());
            }
            cassettesByNominal.put(cassette.getNominal(), cassette);
        }
    }

    public void addMoney(CashBundleDto cashBundleDto) {
        if (cashBundleDto == null
                || cashBundleDto.denomination() == null
                || cashBundleDto.denomination().isEmpty()) {
            throw new IllegalArgumentException("Передали устую пачку купюр");
        }

        for (var entry : cashBundleDto.denomination().entrySet()) {
            Nominal nominal = entry.getKey();
            Integer quantity = entry.getValue();

            if (nominal == null || !cassettesByNominal.containsKey(nominal)) {
                throw new IllegalArgumentException("Банкомат не принимает номинал: " + nominal);
            }

            if (quantity == null || quantity <= 0) {
                throw new IllegalArgumentException("Некорректное количество купюр для номинала: " + nominal);
            }
        }

        for (var entry : cashBundleDto.denomination().entrySet()) {
            cassettesByNominal.get(entry.getKey()).add(entry.getValue());
        }
    }

    public int getBalance() {
        int balance = 0;
        for (var value : cassettesByNominal.values()) {
            balance += value.getTotalAmount();
        }
        return balance;
    }

    public CashBundleDto withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Некоректная сумма");
        }
        int remainingAmount = amount;
        Map<Nominal, Integer> notesToWithdraw = new EnumMap<>(Nominal.class);
        for (var entry : cassettesByNominal.entrySet()) {
            Nominal nominal = entry.getKey();
            Cassette cassette = entry.getValue();

            int neededNotes = remainingAmount / nominal.getNominal();
            int notesToTake = Math.min(neededNotes, cassette.getCount());

            if (notesToTake > 0) {
                notesToWithdraw.put(nominal, notesToTake);
                remainingAmount -= notesToTake * nominal.getNominal();
            }
        }

        if (remainingAmount > 0) {
            throw new IllegalStateException("Невозможно выдать запрошенную сумму: " + amount);
        }

        for (var entry : notesToWithdraw.entrySet()) {
            cassettesByNominal.get(entry.getKey()).debit(entry.getValue());
        }

        return new CashBundleDto(notesToWithdraw);
    }
}
