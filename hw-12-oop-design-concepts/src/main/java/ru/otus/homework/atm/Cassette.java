package ru.otus.homework.atm;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(includeFieldNames = true)
@EqualsAndHashCode
public class Cassette {
    private final Nominal nominal;
    private int count;

    public Cassette(Nominal nominal, int count) {
        if (nominal != null && count >= 0) {
            this.count = count;
            this.nominal = nominal;
            return;
        }
        throw new IllegalArgumentException("Неверно переданные данные");
    }

    public void add(int quantity) {
        if (quantity > 0) {
            count += quantity;
            return;
        }
        throw new IllegalArgumentException("Передали некорректные данные");
    }

    public void debit(int quantity) {
        if (quantity > 0 && count - quantity >= 0) {
            count -= quantity;
            return;
        }
        throw new IllegalArgumentException("Передали некорректные данные");
    }

    public int getTotalAmount() {
        return count * nominal.getNominal();
    }
}
