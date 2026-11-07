package ru.otus.homework.testing.testing;

import ru.otus.homework.testing.annotaions.After;
import ru.otus.homework.testing.annotaions.Before;
import ru.otus.homework.testing.annotaions.Test;

public class CustomerTest {

    @Before
    public void setUp() {
        System.out.println("Начало");
    }

    @Test
    public void reverseOrderTest() {
        int random = (int) (Math.random() * 10);
        if (random > 5) {
            System.out.println("Сам тест");
            return;
        }
        throw new RuntimeException("Тест упал((");
    }

    @After
    public void tearDown() {
        System.out.println("Конец");
    }
}
