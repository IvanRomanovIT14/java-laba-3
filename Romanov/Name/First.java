package ru.Romanov.Name;

import java.util.Scanner;

public class First {
    private final String name;
    private String surname;
    private String patronymic;

    public First(String name) { //Конструктор только с именем
        this.name = validateNotEmpty(name, "Личное имя");
        this.surname = null;
        this.patronymic = null;
    }

    public First(String name, String surname) { //Конструктор с именем и фамилией
        this.name = validateNotEmpty(name, "Личное имя");
        this.surname = validateNotEmpty(surname, "Фамилия");
        this.patronymic = null;
    }

    //Конструктор с именем, фамилией и отчеством
    public First(String name, String surname, String patronymic) {
        this.name = validateNotEmpty(name, "Личное имя");
        this.surname = validateNotEmpty(surname, "Фамилия");
        this.patronymic = validateNotEmpty(patronymic, "Отчество");
    }

    //Метод для проверки на null и пустоту
    private String validateNotEmpty(String value, String s) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(s + " не может быть null или пустым");
        }
        return value.trim();
    }

    public String getName() { //Геттер для имени
        return name;
    }

    public String getSurname() { //Геттер для фамилии
        return surname;
    }

    public String getPatronymic() { //Геттер для отчества
        return patronymic;
    }

    public void setSurname(String surname) { //Сеттер для фамилии
        this.surname = surname;
    }

    public void setPatronymic(String patronymic) { //Сеттер для отчества
        this.patronymic = patronymic;
    }

    @Override
    public String toString() { //Метод для отображения имени
        if (surname != null && patronymic != null) {
            return name + " " + patronymic + " " + surname;
        } else if (surname != null) {
            return name + " " + surname;
        } else {
            return name;
        }
    }

    public static String input(Scanner scanner, String s) { //Метод для проверки на ввод
        while (true) {
            System.out.println("Введите " + s + ":");
            String m = scanner.nextLine();
            if (m.isEmpty()) {
                return "";
            }
            if (Correct(m)) {
                return m;
            } else {
                System.out.println("Можно вводить только буквы, - и пробелы !");
            }
        }
    }

    public static boolean Correct(String nsp) { //Вспомогательная проверка на ввод
        if (nsp == null || nsp.isEmpty()) {
            return true;
        }
        return nsp.matches("^[a-zA-Zа-яА-ЯёЁ\\s-]+$");
    }
}
