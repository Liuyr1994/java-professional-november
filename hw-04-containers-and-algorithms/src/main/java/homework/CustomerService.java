package homework;

import java.util.*;

// при выполнении ДЗ эту аннотацию надо удалить
public class CustomerService {
    private NavigableMap<Customer, String> customers =
            new TreeMap<>(Comparator.comparing(Customer::getScores).thenComparing(Customer::getId));
    // todo: 3. надо реализовать методы этого класса
    // важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    public Map.Entry<Customer, String> getSmallest() {
        // Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        Map.Entry<Customer, String> entry = customers.firstEntry();

        // 2. Если мапа пустая, возвращаем null (защита от NullPointerException)
        if (entry == null) {
            return null;
        }
        long id = entry.getKey().getId();
        String name = entry.getKey().getName();
        long scores = entry.getKey().getScores();
        Customer customer = new Customer(id, name, scores);
        String data = entry.getValue();
        Map.Entry<Customer, String> smallest = new AbstractMap.SimpleImmutableEntry<>(customer, data);

        return smallest;
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> entry = customers.higherEntry(customer);
        if (entry == null) {
            return null;
        }

        long id = entry.getKey().getId();
        String name = entry.getKey().getName();
        long scores = entry.getKey().getScores();
        Customer customer1 = new Customer(id, name, scores);
        String data = entry.getValue();

        Map.Entry<Customer, String> next = new AbstractMap.SimpleImmutableEntry<>(customer1, data);
        return next;
    }

    public void add(Customer customer, String data) {
        customers.put(customer, data);
    }
}
