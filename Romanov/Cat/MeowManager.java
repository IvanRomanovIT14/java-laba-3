package ru.Romanov.Cat;

import ru.Romanov.Cat.RoboCat;
import ru.Romanov.main.Meowable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MeowManager {
    private List<Meowable> meowables = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void createCats() { //Метод для создания котов
        int cnt = CorrectNumber("Сколько котов создать: ");
        for (int i = 0; i < cnt; i++) {
            String name = input(scanner, "имя кота: ");
            meowables.add(new Cat(name));
        }
    }

    public void createRoboCats() { //Метод для создания робокотов
        int cnt = CorrectNumber("Сколько робокотов создать: ");
        for (int i = 0; i < cnt; i++) {
            String name = input(scanner, "имя робокота: ");
            meowables.add(new RoboCat(name));
        }
    }

    public void MeowOne() { //Метод для одного мяуканья
        System.out.println("Все мяукают один раз:");
        for (Meowable meowable : meowables) {
            meowable.meow();
        }
    }

    public void MeowMuch() { //Метод для нескольких мяу
        int cnt = CorrectNumber("Сколько раз мяукнуть: ");
        System.out.println("Коты мяукают " + cnt + " раз:");
        for (Meowable meowable : meowables) {
            if (meowable instanceof Cat) {
                ((Cat) meowable).meow(cnt);
            }
        }
    }

    private int CorrectNumber(String s) { //Метод для проверки на ввод числа
        while (true) {
            System.out.print(s);
            try {
                //Преобразует строку в целое число
                int number = Integer.parseInt(scanner.nextLine());
                if (number >= 0) {
                    return number;
                }
                System.out.println("Число должно быть неотрицательным!");
            } catch (Exception e) {
                System.out.println("Введите целое число!");
            }
        }
    }

    public static String input(Scanner scanner, String s) { //Метод для проверки на ввод строки
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