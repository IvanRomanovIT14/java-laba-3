package ru.Romanov.Bird;

import java.util.Scanner;

public class Parrot extends Bird {
    private String text;

    //Конструктор с фиксированным именем Попугай и текстом
    public Parrot() {
        super("Попугай");
        this.text = inputText();
    }

    private String inputText() { //Метод для ввода текста Попугая
        Scanner scanner = new Scanner(System.in);
        String inputText;
        while (true) {
            System.out.print("Введите текст для попугая: ");
            inputText = scanner.nextLine();

            if (inputText.isEmpty()) {
                System.out.println("Текст не можнт быть пустым");
            } else {
                break;
            }
        }
        return inputText;
    }

    public String getText() { //Геттер для текста попугая
        return text;
    }

    public void setText(String text) { //Сеттер для текста попугая
        this.text = text;
    }

    @Override
    public void sing() { //Метод для пения попугая
        int textLength = text.length();
        int n = getRandom().nextInt(textLength) + 1; //Случайная длина от 1 до длины текста
        String songText = text.substring(0, n); //Берет первые n символов текста
        System.out.println(getName() + ": " + songText);
    }

    @Override
    public String toString() { //Текстовое представление Попугая и его пения
        return getName() + " текст: " + text;
    }
}
