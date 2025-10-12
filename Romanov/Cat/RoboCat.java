package ru.Romanov.Cat;

import ru.Romanov.main.Meowable;

class RoboCat implements Meowable {
    private String name;

    public RoboCat(String name) { //Конструктор с именем робокота
        this.name = name;
    }

    @Override
    public void meow() { // метод с выводом мяу!
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
}
