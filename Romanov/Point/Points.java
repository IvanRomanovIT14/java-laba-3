package ru.Romanov.Point;

public class Points extends Point {
    private double z;

    public Points() { //Конструктор сохдаёт точку в начале координат {0,0,0}
        super();
        this.z = 0.0;
    }

    public Points(double x, double y, double z) { //Конcтруктор с заданными параметрами X, Y и Z
        super(x, y);
        this.z = z;
    }

    @Override
    public String toString() { //Метод для текстового представления точки в формате {X;Y;Z}
        return "{" + getX() + ";" + getY() + ";" + z + "}";
    }

    public double getZ() { //Геттер для координаты Z
        return z;
    }

    public void setZ(double z) { //Сеттер для координаты Z
        this.z = z;
    }
}