package ru.Romanov.Bird;

import java.util.Random;

public class Bird {
    private String name;
    private static Random random = new Random();

    public Bird(String name) { //Конструктор для создания имени птицы
        this.name = name;
    }

    public String getName() { //Геттер для имени птицы
        return name;
    }

    public void setName(String name) { //Сеттер для имени птицы
        this.name = name;
    }

    public static Random getRandom() { //Геттер для генератора случайных чисел
        return random;
    }

    public void sing() { //Метод для пения птиц
        System.out.println(name + "поёт");
    }

    @Override
    public String toString() { //Метод для текстового представления птицы с именем
        return "Птица" + name;
    }
}


