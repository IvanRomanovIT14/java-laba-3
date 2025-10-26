package ru.Romanov.Cat;

import ru.Romanov.main.Meowable;

class Cat implements Meowable {
    private String name;

    public Cat(String name) { //Конструктор с именем кота
        this.name = name;
    }

    @Override
    public void meow() { //Метод с выводом 1 мяу!
        System.out.println(name + ": мяу!");
    }

    public void meow(int n) { //Метод с выводом нескольких мяу!
        if (n <= 0) {
            System.out.println(name + ": тишина");
            return;
        }
        StringBuilder res = new StringBuilder(name + ": ");
        for (int i = 0; i < n; i++) {
            res.append("мяу");
            if (i < n - 1) res.append("-");
        }
        res.append("!");
        System.out.println(res.toString());
    }

    public String getName() { //Геттер для имени кота
        return name;
    }

    public void setName(String name) { //Сеттер для имени Кота
        this.name = name;
    }
}
