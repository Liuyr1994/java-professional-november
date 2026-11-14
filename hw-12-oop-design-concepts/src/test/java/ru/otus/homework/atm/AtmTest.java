package ru.otus.homework.atm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AtmTest {
    private Atm atm;

    @BeforeEach
    void setUp() {
        List<Cassette> cassettes =
                List.of(new Cassette(Nominal.ONE_HUNDRED, 10), new Cassette(Nominal.FIVE_HUNDRED, 5));
        atm = new Atm(cassettes);
    }

    @Test
    void addMoneyTrueTest() {
        Map<Nominal, Integer> cash = new HashMap<>();
        cash.put(Nominal.ONE_HUNDRED, 1);
        cash.put(Nominal.FIVE_HUNDRED, 1);
        CashBundleDto cashBundleDto = new CashBundleDto(cash);
        atm.addMoney(cashBundleDto);
        Assertions.assertEquals(atm.getBalance(), 4100);
    }

    @Test
    void getBalanceTrueTest() {
        Assertions.assertEquals(atm.getBalance(), 3500);
    }

    @Test
    void withdrawTrueTest() {
        CashBundleDto cashBundleDto = atm.withdraw(700);
        Map<Nominal, Integer> cash = cashBundleDto.denomination();
        Assertions.assertEquals(cash.size(), 2);
        Assertions.assertEquals(cash.get(Nominal.FIVE_HUNDRED), 1);
        Assertions.assertEquals(cash.get(Nominal.ONE_HUNDRED), 2);
        Assertions.assertEquals(atm.getBalance(), 2800);
    }
}
