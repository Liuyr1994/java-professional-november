package homework;

import java.util.LinkedList;
import java.util.List;

// при выполнении ДЗ эту аннотацию надо удалить
public class CustomerReverseOrder {
    private List<Customer> customers = new LinkedList<>();
    // todo: 2. надо реализовать методы этого класса
    // надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"

    public void add(Customer customer) {
        customers.add(customer);
    }

    public Customer take() {
        Customer customer = customers.removeLast();
        return customer; // это "заглушка, чтобы скомилировать"
    }
}
