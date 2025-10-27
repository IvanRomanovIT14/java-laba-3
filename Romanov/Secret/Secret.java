package ru.Romanov.Secret;

import java.util.Random;
import java.util.Scanner;

public class Secret {
    private String secretText;
    private String keeperName;
    private int keeperNumber;
    private Secret nextKeeper;
    private Secret previousKeeper;
    private static int totalKeepers = 0;
    private static Random random = new Random();

    public Secret() { //Конструктор для создания нового секрета
        Scanner scanner = new Scanner(System.in);
        this.keeperName = input(scanner, "имя хранителя секрета");
        this.secretText = inputSecretText(scanner);
        this.keeperNumber = ++totalKeepers;
        this.nextKeeper = null;
        this.previousKeeper = null;
        System.out.println("Создан новый секрет для: " + this.keeperName);
    }

    //Конструктор для передачи секрета другому человеку
    public Secret(Secret previousSecret) {
        Scanner scanner = new Scanner(System.in);
        if (previousSecret == null) {
            throw new IllegalArgumentException("Исходный секрет не может быть null");
        }
        if (previousSecret.nextKeeper != null) {
            throw new IllegalStateException("Секрет уже был передан другому человеку!");
        }
        this.keeperName = input(scanner, "имя нового хранителя секрета");
        this.secretText = modifySecretText(previousSecret.secretText);
        this.keeperNumber = ++totalKeepers;
        this.nextKeeper = null;
        this.previousKeeper = previousSecret;
        previousSecret.nextKeeper = this; //Связываем предыдущего хранителя с текущим
        System.out.println(previousSecret.keeperName + " сказал что " + previousSecret.secretText);
    }

    //Метод для ввода текста секрета с проверкой на пустоту
    private String inputSecretText(Scanner scanner) {
        while (true) {
            System.out.println("Введите текст секрета:");
            String text = scanner.nextLine();
            if (text.trim().isEmpty()) {
                System.out.println("Текст секрета не может быть пустым!");
            } else {
                return text.trim();
            }
        }
    }

    public static String input(Scanner scanner, String s) { //Метод для проверки на ввод
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

    //Метод для модификации текста секрета при передаче
    private String modifySecretText(String originalText) {
        if (originalText == null || originalText.isEmpty()) {
            return originalText;
        }
        int textLength = originalText.length();
        int maxChanges = Math.max(1, textLength / 10);
        int n = random.nextInt(maxChanges + 1); //x от 0 до n
        StringBuilder modifiedText = new StringBuilder(originalText);
        //Добавляем гарантированно хотя бы 1 изменение, если maxChanges > 0
        if (maxChanges > 0 && n == 0) {
            n = 1;
        }
        for (int i = 0; i < n; i++) {
            int position = random.nextInt(modifiedText.length() + 1);
            char randomChar = (char) (random.nextInt(33) + 'а');
            modifiedText.insert(position, randomChar);
        }
        return modifiedText.toString();
    }

    //Метод для получения количества следующих хранителей
    public int getNextKeepers() {
        int cnt = 0;
        Secret current = this.nextKeeper;
        while (current != null) {
            cnt++;
            current = current.nextKeeper;
        }
        return cnt;
    }

    //Метод для получения имени N-го хранителя
    public String getNameIndex(int n) {
        if (n == 0) {
            return this.keeperName;
        }
        if (n > 0) {
            Secret s = this.nextKeeper;
            for (int i = 1; i < n; i++) {
                if (s == null) {
                    throw new IllegalArgumentException("Нет " + n + "-го следующего хранителя");
                }
                s = s.nextKeeper;
            }
            if (s == null) {
                throw new IllegalArgumentException("Нет " + n + "-го следующего хранителя");
            }
            return s.keeperName;
        } else {
            int absN = Math.abs(n);
            Secret s = this.previousKeeper;
            for (int i = 1; i < absN; i++) {
                if (s == null) {
                    throw new IllegalArgumentException("Нет " + absN + "-го предыдущего хранителя");
                }
                s = s.previousKeeper;
            }
            if (s == null) {
                throw new IllegalArgumentException("Нет " + absN + "-го предыдущего хранителя");
            }
            return s.keeperName;
        }
    }

    //Метод для получения разницы в длине текста с N-ым хранителем
    public int getTextLengthDifference(int n) {
        if (n == 0) {
            return 0;
        }
        Secret s;
        if (n > 0) {
            //Сравнение с последующими хранителями - у них текст ДЛИННЕЕ
            s = this.nextKeeper;
            for (int i = 1; i < n; i++) {
                if (s == null) {
                    throw new IllegalArgumentException("Нет " + n + "-го следующего хранителя");
                }
                s = s.nextKeeper;
            }
        } else { //Сравнение с предыдущими хранителями - у них текст короче
            int absN = Math.abs(n);
            s = this.previousKeeper;
            for (int i = 1; i < absN; i++) {
                if (s == null) {
                    throw new IllegalArgumentException("Нет " + absN + "-го предыдущего хранителя");
                }
                s = s.previousKeeper;
            }
        }
        if (s == null) {
            throw new IllegalArgumentException("Хранитель не найден");
        }
        return s.secretText.length() - this.secretText.length();
    }

    //Геттер для порядкового номера хранителя
    public int getKeeperNumber() {
        return keeperNumber;
    }

    //Геттер для имени хранителя
    public String getKeeperName() {
        return keeperName;
    }

    //Геттер для следующего хранителя
    public Secret getNextKeeper() {
        return nextKeeper;
    }

    //Геттер для предыдущего хранителя
    public Secret getPreviousKeeper() {
        return previousKeeper;
    }

    //Геттер для общего количества хранителей
    public static int getTotalKeepers() {
        return totalKeepers;
    }

    @Override
    public String toString() { //Метод для текстового представления секрета
        return keeperName + ": " + secretText;
    }
}
