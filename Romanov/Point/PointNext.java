package ru.Romanov.Point;

import java.util.Scanner;

public class PointNext extends PointTwo {
    private String color;

    //Конструктор создаёт тчоку с заданными параметрами X и Y и цветом
    public PointNext(double x, double y, String color) {
        super(x, y);
        this.color = color;
    }

    @Override
    public PointNext clone() { //Метод возвращает точку с такими же координатами и цветом
        return new PointNext(this.getX(), this.getY(), this.color);
    }

    @Override
    //Метод проверяет равенство координат и цвета
    public boolean equals(Object obj) {
        if (!super.equals(obj)) { //Если координаты не совпадают, то точки не равны
            return false;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PointNext other = (PointNext) obj;
        return color.equals(other.color);
    }

    @Override
    //Метод для текстового представления точки с координатами X и Y и цветом
    public String toString() {
        return "{" + getX() + ", " + getY() + "}" + ", цвет: " + color;
    }

    //Метод для корректного ввода строки
    public static String input(Scanner scanner, String s) {
        while (true) {
            System.out.println("Введите " + s + ":");
            String m = scanner.nextLine();
            if (m.isEmpty()) {
                return "";
            }
            if (Correct(m)) {
                return m;
            } else {
                System.out.println("Можно вводить только буквы, - и пробелы!");
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
