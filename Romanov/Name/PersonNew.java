package ru.Romanov.Name;

import java.util.Scanner;

public class PersonNew {
    private final String name;
    private final String surname;
    private final String patronymic;
    private int height;
    private final PersonNew father;

    public PersonNew(String name, int height) { //Конструктор с именем и ростом
        this.name = correctName(name, "Личное имя");
        this.surname = null;
        this.patronymic = null;
        this.height = correctHeight(height);
        this.father = null;
    }

    //Конструктор с именем, ростом и отцом
    public PersonNew(String name, int height, PersonNew father) {
        this.name = correctName(name, "Личное имя");
        this.height = correctHeight(height);
        this.father = father;
        this.surname = father.surname;
        this.patronymic = father.name + "ович";
    }

    //Конструктор с именем, фамилией, отчеством и ростом
    public PersonNew(String name, String surname, String patronymic, int height) {
        this.name = correctName(name, "Личное имя");
        this.surname = correctName(surname, "Фамилия");
        this.patronymic = correctName(patronymic, "Отчество");
        this.height = correctHeight(height);
        this.father = null;
    }

    //Конструктор с именем, фамилией, отчеством, ростом и отцом
    public PersonNew(String name, String surname, String patronymic, int height,
                     PersonNew father) {
        this.height = correctHeight(height);
        this.father = father;
        String actualSurname;
        if (surname == null || surname.isEmpty()) {
            actualSurname = father.surname;
        } else {
            actualSurname = surname;
        }
        String actualPatronymic;
        if (patronymic == null || patronymic.isEmpty()) {
            actualPatronymic = father.name + "ович";
        } else {
            actualPatronymic = patronymic;
        }
        this.name = correctName(name, "Личное имя");
        this.surname = correctName(actualSurname, "Фамилия");
        this.patronymic = correctName(actualPatronymic, "Отчество");
    }

    private String correctName(String name, String surname) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(surname + " не может быть пустым");
        }
        return name.trim();
    }

    private int correctHeight(int height) {
        if (height <= 0 || height > 500) {
            throw new IllegalArgumentException("Рост должен быть положительным и не более 500 см");
        }
        return height;
    }

    //Метод для ввода с консоли имени и роста
    public static PersonNew createName(Scanner scanner) {
        System.out.println("Создание человека только с личным именем и ростом:");
        String name = input(scanner, "личное имя");
        int height = inputHeight(scanner);
        return new PersonNew(name, height);
    }

    //Метод для ввода с консоли имени, роста и отца
    public static PersonNew createNameFather(Scanner scanner, PersonNew father) {
        System.out.println("Создание человека с личным именем и отцом:");
        String name = input(scanner, "личное имя");
        int height = inputHeight(scanner);
        return new PersonNew(name, height, father);
    }

    //Метод для ввода с консоли имени, фамилии, отчества, роста и отца
    public static PersonNew createFullNameFather(Scanner scanner, PersonNew father) {
        System.out.println("Создание человека с полным именем и отцом:");
        String name = input(scanner, "личное имя");
        String surname = input(scanner, "фамилию");
        String patronymic = input(scanner, "отчество");
        int height = inputHeight(scanner);
        return new PersonNew(name, surname, patronymic, height, father);
    }

    //Метод для проверки на ввод строки
    public static String input(Scanner scanner, String s) {
        while (true) {
            System.out.print("Введите " + s + ": ");
            String m = scanner.nextLine().trim();
            if (!m.isEmpty() && Correct(m)) {
                return m;
            }

            System.out.println(s + " не может быть пустым и не может состоять не из букв!");
        }
    }

    public static boolean Correct(String nsp) { //Вспомогательная проверка на ввод
        if (nsp == null || nsp.isEmpty()) {
            return true;
        }
        return nsp.matches("^[a-zA-Zа-яА-ЯёЁ\\s-]+$");
    }

    //Метод для проверки на ввод роста
    public static int inputHeight(Scanner scanner) {
        while (true) {
            System.out.print("Введите рост: ");
            String input = scanner.nextLine().trim();
            if (isInteger(input)) {
                int height = Integer.parseInt(input); //Преобразует строку в целое число
                if (height > 0 && height <= 500) {
                    return height;
                } else {
                    System.out.println("Рост должен быть от 1 до 500 см!");
                }
            } else {
                System.out.println("Вводить можно только целое число!");
            }
        }
    }

    //Метод проверяет что строка состоит только и цифр
    private static boolean isInteger(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) { //Является ли цифрой 0-9
                return false;
            }
        }
        return true;
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

    public int getHeight() { //Геттер для роста
        return height;
    }

    public PersonNew getFather() { //Геттер для отца
        return father;
    }

    public void setHeight(int height) { //Сеттер для роста
        this.height = correctHeight(height);
    }

    @Override
    public String toString() { //Метод для отображения имени
        if (surname != null && patronymic != null) {
            return surname + " " + name + " " + patronymic + ", рост " + height;
        } else if (surname != null) {
            return name + " " + surname + ", рост " + height;
        } else {
            return name + ", рост " + height;
        }
    }
}