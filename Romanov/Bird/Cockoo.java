package ru.Romanov.Bird;

public class Cockoo extends Bird {
    public Cockoo() { //Конструктор с фиксированным именем Кукушка
        super("Кукушка");
    }

    @Override
    public void sing() { //Метод для пения Кукушки
        int cnt = getRandom().nextInt(10) + 1; //Случайной число 1 - 10
        StringBuilder song = new StringBuilder(getName() + ": ");
        for (int i = 0; i < cnt; i++) {
            song.append("ку-ку");
            if (i < cnt - 1) {
                song.append(" ");
            }
        }
        System.out.println(song.toString());
    }
}
