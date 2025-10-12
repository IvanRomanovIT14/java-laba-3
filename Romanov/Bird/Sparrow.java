package ru.Romanov.Bird;

public class Sparrow extends Bird {
    public Sparrow() { //Конструктор с фиксированным именем Воробей
        super("Воробей");
    }

    @Override
    public void sing() { //Метод для пения воробья
        System.out.println(getName() + ": чирик");
    }
}
