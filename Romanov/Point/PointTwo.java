package ru.Romanov.Point;

import java.util.Scanner;

public class PointTwo {
    private double x;
    private double y;

    public PointTwo() { //Конструктор сохдаёт точку в начале координат {0,0}
        this.x = 0.0;
        this.y = 0.0;
    }

    public PointTwo(double x, double y) { //Конcтруктор с заданными параметрами X, Y
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() { //Метод для текстового представления точки в формате {X;Y}
        return "{" + x + ";" + y + "}";
    }

    public double getX() { //Геттер для координаты X
        return x;
    }

    public double getY() { //Геттер для координаты Y
        return y;
    }

    public void setX(double x) { //Cеттер для координаты X
        this.x = x;
    }

    public void setY(double y) { //Cеттер для координаты Y
        this.y = y;
    }

    //Метод для корректного ввода координаты
    public static double correctDouble(Scanner scanner, String s) {
        while (true) {
            System.out.print(s);
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            } else {
                System.out.println("Можно вводить только число!");
                scanner.next(); //Очистка неверного ввода
            }
        }
    }

    //Метод определяет чтобы 2 точки были равны, чтобы X и Y у них совпадали
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PointTwo other = (PointTwo) obj;
        return x == other.x && y == other.y;
    }
}