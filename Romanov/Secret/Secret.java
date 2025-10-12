package ru.Romanov.Secret;

import java.util.Random;
import java.util.Scanner;

public class Secret {
    private String secretText;
    private String keeperName;
    private int keeperNumber;
    private Secret keeperNext;
    private static int keepers = 0;
    private static Random random = new Random();

    //Конструктор для создания нового секрета
    public Secret() {
        Scanner scanner = new Scanner(System.in);
        // Ввод имени хранителя с проверкой на цифры и пустоту
        String name = "";
        while (name.trim().isEmpty() || containsDigits(name)) {
            System.out.print("Введите имя хранителя секрета: ");
            name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                System.out.println("Имя не может быть пустым!");
            } else if (containsDigits(name)) {
                System.out.println("Имя не может содержать цифры!");
            }
        }
        this.keeperName = name.trim();

        //Ввод текста секрета с проверкой на пустоту
        String text = "";
        while (text.trim().isEmpty()) {
            System.out.print("Введите текст секрета: ");
            text = scanner.nextLine();
            if (text.trim().isEmpty()) {
                System.out.println("Текст секрета не может быть пустым!");
            }
        }
        this.secretText = text.trim();
        this.keeperNumber = ++keepers;
        this.keeperNext = null;

        System.out.println("Создан новый секрет для: " + this.keeperName);
    }

    // Конструктор для передачи секрета
    public Secret(Secret originalSecret) {
        Scanner scanner = new Scanner(System.in);
        if (originalSecret == null) {
            throw new IllegalArgumentException("Исходный секрет не может быть null");
        }
        if (originalSecret.keeperNext != null) {
            throw new IllegalStateException("Секрет уже был передан другому человеку");
        }
        //Ввод имени нового хранителя с проверкой на цифры и пустоту
        String newName = "";
        while (newName.trim().isEmpty() || containsDigits(newName)) {
            System.out.print("Введите имя нового хранителя секрета: ");
            newName = scanner.nextLine();
            if (newName.trim().isEmpty()) {
                System.out.println("Ошибка: имя не может быть пустым!");
            } else if (containsDigits(newName)) {
                System.out.println("Ошибка: имя не может содержать цифры!");
            }
        }
        this.keeperName = newName.trim();
        this.secretText = modifySecretText(originalSecret.secretText);
        this.keeperNumber = ++keepers;
        this.keeperNext = null;
        originalSecret.keeperNext = this;
        System.out.println(originalSecret.keeperName + " сказал что " + originalSecret.secretText);
    }

    //Метод проверки на наличие цифр в строке
    private boolean containsDigits(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    public String getSecretText() { //Геттер для текста секрета
        return secretText;
    }

    public String getkeeperName() { //Геттер для имени хранителя секрета
        return keeperName;
    }

    public int getKeeperNumber() { //Геттер для номера хранителя секрета
        return keeperNumber;
    }

    public Secret getNextKeeper() { //Геттер для ссылки на следующего хранителя секрета
        return keeperNext;
    }

    public static int getKeepers() { //Геттер для общего количества хранителей секрета
        return keepers;
    }

    public void setSecretText() { //Сеттер для ввода текста секрета
        Scanner scanner = new Scanner(System.in);
        String newText = "";
        while (newText.trim().isEmpty()) {
            System.out.print("Введите новый текст секрета: ");
            newText = scanner.nextLine();
            if (newText.trim().isEmpty()) {
                System.out.println("Ошибка: текст секрета не может быть пустым!");
            }
        }
        this.secretText = newText.trim();
        System.out.println("Текст секрета успешно изменен!");
    }

    public void setkeeperName() { //Сеттер для ввода имени хранителя секрета
        Scanner scanner = new Scanner(System.in);
        String newName = "";
        while (newName.trim().isEmpty() || containsDigits(newName)) {
            System.out.print("Введите новое имя хранителя: ");
            newName = scanner.nextLine();
            if (newName.trim().isEmpty()) {
                System.out.println("Ошибка: имя не может быть пустым!");
            } else if (containsDigits(newName)) {
                System.out.println("Ошибка: имя не может содержать цифры!");
            }
        }
        this.keeperName = newName.trim();
        System.out.println("Имя хранителя успешно изменено!");
    }

    //Метод для модификации текста
    public String modifySecretText(String originalText) {
        if (originalText == null || originalText.isEmpty()) return originalText;
        int textLength = originalText.length();
        int maxChanges = Math.max(1, textLength / 10);
        int numChanges = random.nextInt(maxChanges + 1);
        StringBuilder modifiedText = new StringBuilder(originalText);
        for (int i = 0; i < numChanges; i++) {
            int position = random.nextInt(modifiedText.length() + 1);
            char randomChar = (char) (random.nextInt(26) + 'a');
            modifiedText.insert(position, randomChar);
        }
        return modifiedText.toString();
    }

    @Override
    public String toString() {
        return keeperName + ": это секрет!";
    }

    public int getNumberOfNextKeepers() {
        int count = 0;
        Secret current = this.keeperNext;
        while (current != null) {
            count++;
            current = current.keeperNext;
        }
        return count;
    }

    public String getKeeperNameByIndex(int n) {
        if (n == 0) return this.keeperName;
        if (n > 0) {
            Secret target = this;
            for (int i = 0; i < n; i++) {
                if (target.keeperNext == null) throw new IllegalArgumentException("Нет следующего хранителя");
                target = target.keeperNext;
            }
            return target.keeperName;
        }
        throw new IllegalArgumentException("N должно быть положительным");
    }

    public int getTextLengthDifference(int n) {
        if (n <= 0) throw new IllegalArgumentException("N должно быть положительным");
        Secret target = this;
        for (int i = 0; i < n; i++) {
            if (target.keeperNext == null) throw new IllegalArgumentException("Нет следующего хранителя");
            target = target.keeperNext;
        }
        return target.secretText.length() - this.secretText.length();
    }
}