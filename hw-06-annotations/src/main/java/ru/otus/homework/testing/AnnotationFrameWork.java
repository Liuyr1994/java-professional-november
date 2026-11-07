package ru.otus.homework.testing;

import ru.otus.homework.testing.annotaions.After;
import ru.otus.homework.testing.annotaions.Before;
import ru.otus.homework.testing.annotaions.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AnnotationFrameWork {

    public void start(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            List<Method> methodsBefore = new ArrayList<>();
            List<Method> methodsAfter = new ArrayList<>();
            List<Method> methodsTest = new ArrayList<>();
            int goodTest = 0;
            int badTest = 0;

            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Before.class)) {
                    methodsBefore.add(method);
                    continue;
                }
                if (method.isAnnotationPresent(After.class)) {
                    methodsAfter.add(method);
                    continue;
                }

                if (method.isAnnotationPresent(Test.class)) {
                    methodsTest.add(method); // Исправили: теперь тесты добавляются в свой список
                }
            }

            for (var testMethod : methodsTest) {
                Object test = clazz.getDeclaredConstructor().newInstance();

                for (Method method : methodsBefore) {
                    method.invoke(test);
                }

                try {
                    // ИСПРАВЛЕНИЕ 2: Убрали внутренний цикл! Запускаем ровно ОДИН текущий тест из внешнего цикла
                    testMethod.invoke(test);
                    goodTest++;
                } catch (InvocationTargetException e) {
                    badTest++;
                }

                for (Method method : methodsAfter) {
                    method.invoke(test);
                }
            }

            // Добавим вывод результатов, чтобы вы увидели работу в консоли
            System.out.println("Всего тестов: " + (goodTest + badTest));
            System.out.println("Успешно: " + goodTest);
            System.out.println("Упало: " + badTest);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
