package ru.Romanov.main;

import ru.Romanov.Bird.Bird;
import ru.Romanov.Bird.Cockoo;
import ru.Romanov.Bird.Parrot;
import ru.Romanov.Bird.Sparrow;
import ru.Romanov.Cat.MeowManager;
import ru.Romanov.Name.First;
import ru.Romanov.Name.PersonNew;
import ru.Romanov.Point.Point;
import ru.Romanov.Point.PointNext;
import ru.Romanov.Point.PointTwo;
import ru.Romanov.Point.Points;
import ru.Romanov.Secret.Secret;

import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Math.pow;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (args.length >= 2) {
            String x = args[0];
            String y = args[1];
            double res = power(x, y);
            System.out.printf("Результат возведения " + x + " в степень " + y + ": " + res);
            System.out.println();
        }
        System.out.println("Выберите номер задания 1-8: ");
        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    String name1 = First.input(scanner, "личное имя");
                    First human1 = new First(name1);
                    System.out.println("Имя (только личное имя): " + human1);
                    String name2 = First.input(scanner, "личное имя");
                    String surname2 = First.input(scanner, "фамилию");
                    First human2 = new First(name2, surname2);
                    System.out.println("Имя (личное имя и фамилия): " + human2);
                    String name3 = First.input(scanner, "личное имя");
                    String surname3 = First.input(scanner, "фамилию");
                    String patronymic3 = First.input(scanner, "отчество");
                    First human3 = new First(name3, surname3, patronymic3);
                    System.out.println("Имя (личное имя, фамилия и отчество): " + human3);
                    break;
                case 2:
                    System.out.println("Создание человека с именем Лев и ростом 170:");
                    PersonNew human21 = PersonNew.createName(scanner);
                    System.out.println(human21);
                    System.out.println("Создание человека с именем Пушкин Сергей, ростом 168 и отцом Львом:");
                    PersonNew human22 = PersonNew.createFullNameFather(scanner, human21);
                    System.out.println(human22);
                    System.out.println("Создание человека с именем Александр, ростом 167 и отцом Сергеем:");
                    PersonNew human23 = PersonNew.createNameFather(scanner, human22);
                    System.out.println(human23);
                    break;
                case 3:
                    System.out.println("Создание первого секрета:");
                    Secret secret1 = new Secret();
                    System.out.println("Строковое представление: " + secret1);
                    System.out.println("Порядковый номер хранителя: " + secret1.getKeeperNumber());
                    System.out.println("Хранителей после: " + secret1.getNextKeepers());
                    System.out.println();
                    System.out.println("Передача секрета второму человеку:");
                    Secret secret2 = new Secret(secret1);
                    System.out.println("Строковое представление: " + secret2);
                    System.out.println("Порядковый номер хранителя: " + secret2.getKeeperNumber());
                    System.out.println("Хранителей после: " + secret2.getNextKeepers());
                    System.out.println("Передача секрета третьему человеку:");
                    Secret secret3 = new Secret(secret2);
                    System.out.println("Строковое представление: " + secret3);
                    System.out.println("Порядковый номер хранителя: " + secret3.getKeeperNumber());
                    System.out.println("Хранителей после: " + secret3.getNextKeepers());
                    System.out.println();
                    System.out.println("Информация о цепочке секретов:");
                    System.out.println("Общее количество хранителей: " + Secret.getTotalKeepers());
                    System.out.println();
                    System.out.println("Поиск хранителей по индексу:");
                    try {
                        System.out.println("0-й хранитель от 1-го секрета: " + secret1.getNameIndex(0));
                        System.out.println("1-й следующий после 1-го секрета: " + secret1.getNameIndex(1));
                        System.out.println("2-й следующий после 1-го секрета: " + secret1.getNameIndex(2));
                        System.out.println("1-й предыдущий до 3-го секрета: " + secret3.getNameIndex(-1));
                        System.out.println("2-й предыдущий до 3-го секрета: " + secret3.getNameIndex(-2));
                        System.out.println();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println("Разница в длине текста секрета:");
                    try {
                        System.out.println("Разница с 1-м следующим: " + secret1.getTextLengthDifference(1) + " символов");
                        System.out.println("Разница с 2-м следующим: " + secret1.getTextLengthDifference(2) + " символов");
                        System.out.println("Разница с 1-м предыдущим: " + secret3.getTextLengthDifference(-1) + " символов");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    System.out.println();
                    System.out.println("Попытка повторной передачи секрета:");
                    try {
                        Secret secret4 = new Secret(secret2);
                        System.out.println("Ошибка: секрет был передан повторно!");
                    } catch (IllegalStateException e) {
                        System.out.println("Успешно предотвращено!");
                    }
                    break;
                case 4:
                    System.out.println("3 точки с координатами {X,Y,Z}:");
                    Point point1 = new Points(1.5, 5, 3.6);
                    Point point2 = new Points(2.5, 7.34, 9);
                    Point point3 = new Points(3, 5.2, 11.11);
                    System.out.println(point1);
                    System.out.println(point2);
                    System.out.println(point3);
                    double x41 = Points.correctDouble(scanner, "Введите координату X: ");
                    double y41 = Points.correctDouble(scanner, "Введите координату Y: ");
                    double z41 = Points.correctDouble(scanner, "Введите координату Z: ");
                    Point xyz41 = new Points(x41, y41, z41);
                    System.out.println("Координаты первой точки: " + xyz41);
                    double x42 = Points.correctDouble(scanner, "Введите координату X: ");
                    double y42 = Points.correctDouble(scanner, "Введите координату Y: ");
                    double z42 = Points.correctDouble(scanner, "Введите координату Z: ");
                    Point xyz42 = new Points(x42, y42, z42);
                    System.out.println("Координаты второй точки: " + xyz42);
                    double x43 = Points.correctDouble(scanner, "Введите координату X: ");
                    double y43 = Points.correctDouble(scanner, "Введите координату Y: ");
                    double z43 = Points.correctDouble(scanner, "Введите координату Z: ");
                    Point xyz43 = new Points(x43, y43, z43);
                    System.out.println("Координаты третьей точки: " + xyz43);
                    break;
                case 5:
                    Bird sparrow = new Sparrow();
                    sparrow.sing();
                    Bird cockoo = new Cockoo();
                    cockoo.sing();
                    Bird parrot = new Parrot();
                    System.out.println(parrot);
                    parrot.sing();
                    break;
                case 6:
                    MeowManager manager = new MeowManager();
                    manager.createCats();
                    manager.createRoboCats();
                    manager.MeowOne();
                    manager.MeowMuch();
                    break;
                case 7:
                    double x71 = PointTwo.correctDouble(scanner, "Введите координату X: ");
                    double y71 = PointTwo.correctDouble(scanner, "Введите координату Y: ");
                    PointTwo xy71 = new PointTwo(x71, y71);
                    System.out.println("Координаты первой точки: " + xy71);
                    double x72 = PointTwo.correctDouble(scanner, "Введите координату X: ");
                    double y72 = PointTwo.correctDouble(scanner, "Введите координату Y: ");
                    PointTwo xy72 = new PointTwo(x72, y72);
                    System.out.println("Координаты второй точки: " + xy72);
                    System.out.println("Одинаковы ли точки: " + xy71.equals(xy72));
                    break;
                case 8:
                    double x111 = PointNext.correctDouble(scanner, "Введите координату X: ");
                    double y111 = PointNext.correctDouble(scanner, "Введите координату Y: ");
                    scanner.nextLine();
                    String s = PointNext.input(scanner, "цвет");
                    PointNext original = new PointNext(x111, y111, s);
                    PointNext cloned = original.clone();
                    System.out.println("Оригинальная точка: " + original);
                    System.out.println("Клонированная точка: " + cloned);
                    System.out.println("Координаты оригинала: " + "{" + original.getX() + ", " + original.getY() + "}");
                    System.out.println("Координаты клона: " + "{" + cloned.getX() + ", " + cloned.getY() + "}");
                    System.out.println("Точки равны: " + original.equals(cloned));
                    break;
                default:
                    System.out.println("Вводить можно только число 1-8!");
            }
        } else {
            System.out.println("Вводить можно только число 1-8!");
        }
    }

    public static double power(String x, String y) {
        int x1 = parseInt(x);
        int y1 = parseInt(y);
        return pow(x1, y1);
    }
}
