package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;
/**
 * PhoneDictionary
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 28.06.2018
 */
public class PhoneDictionary {
    /**
     * Хранить телефонную книгу.
     */
    private List<Person> persons = new ArrayList<Person>();

    /**
     * Добовляет пользователя в телефонную книгу.
     * @param person ползьзователь для добавления.
     */
    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список подощедщих пользователей.
     */
    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
        for (Person person : this.persons) {
            if (person.getAddress().contains(key) || person.getName().contains(key)
                    || person.getPhone().contains(key) || person.getSurname().contains(key)) {
                result.add(person);
            }
        }
        return result;
    }
}