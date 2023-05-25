package org.KATA.java.Test;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    static boolean isRoman(String element) {
        ROMAN[] romans = ROMAN.values();
        for (ROMAN rome :
                romans) {
            if (element.equals(String.valueOf(rome)))
                return true;
        }
        return false;
    }

    static int fromRomanToArabConverter(String element) throws IOException {
        ROMAN roman = ROMAN.valueOf(element);
        int value = roman.ordinal() + 1;
        return value;
    }

    static String fromArabToRomanConverter(int value) {
        ROMAN roman = ROMAN.values()[value - 1];
        String romanResult = roman.toString();
        return romanResult;
    }


    static boolean isArabian(String element) {
        int[] arabian = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70,
                71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 100};

        int n = 0;
        try {
            n = Integer.parseInt(element);
        } catch (RuntimeException e) {
            return false;
        }
        for (int i = 0; i < arabian.length; i++) {
            if (n == arabian[i])
                return true;
        }
        return false;
    }

    static int calculateItNow(int x, int y, String currentOperator) throws IOException {

        //проверка на числа от 1 до 10
        if ((x <= 0 || x > 10) || (y <= 0 || y > 10)) {
            throw new IOException("Ошибка! Калькулятор принимает числа от 1 до 10. Попробуйте еще раз.");
        }

        int result = 0;
        switch (currentOperator) {
            case "+":
                return result = x + y;
            case "-":
                return result = x - y;
            case "/":
                return result = x / y;
            case "*":
                return result = x * y;
        }
        return result;
    }


    public static String CALC(String input) throws IOException {
        String[] operators = {"+", "-", "/", "*"};
        int resultOfCulculation;
        String resultToString = "";


        //сюда запишем оператор, который введет пользователь в строке
        String currentOperator = "";
        for (String oper :
                operators) {
            if (input.contains(oper)) {
                currentOperator = oper;
                break;
            }
        }


        //делим введенную строку на подстроки по оператору
        String[] elements = input.split("[\\+-/\\*]");

        //проверка на соответствие математическому выражению(нет операторов или больше 1 оператора)
        if (elements.length != 2)
            throw new IOException("Формат математической операции не удовлетворяет заданию. Калькулятор принимает 2 числа и 1 оператор: + - * /");

        String el1 = elements[0].trim();
        String el2 = elements[1].trim();


        //проверка на наличие римских чисел
        //прописан массив римских чисел от 1 до 10
        if (isRoman(el1) == true && isRoman(el2) == true) {
            int x = fromRomanToArabConverter(el1);
            int y = fromRomanToArabConverter(el2);
            resultOfCulculation = calculateItNow(x, y, currentOperator);
            if (resultOfCulculation < 0)
                throw new IOException("В римской системе исчисления нет 0 и отрицательных чисел");
            else {
                resultToString = fromArabToRomanConverter(resultOfCulculation);
                return resultToString;
            }
        }
        //проверкка на соответствие арабским числам
        if (isArabian(el1) == true && isArabian(el2) == true) {
            int x = Integer.parseInt(el1);
            int y = Integer.parseInt(el2);

            resultOfCulculation = calculateItNow(x, y, currentOperator);
            return resultToString = String.valueOf(resultOfCulculation);
        }

        //если в введенной строке нет ни арабских, ни римских чисел
        if (!isRoman(el1) && !isRoman(el2) && !isArabian(el1) && !isArabian(el2)) {
            throw new RuntimeException("Ошибка! Введены данные неизвестного типа. \nКалькулятор принимает только арабские или римские числа.\nПопробуйте еще раз");
        } else throw new RuntimeException("Ошибка! Используются одновременно разные системы исчисления");
    }


    public static void main(String[] args) throws Exception {
        System.out.println("Введите математическое выражение из 2 чисел от 1 до 10.\nКалькулятор принимает только римские, либо только арабские числа.");
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        System.out.println("Результат: " + CALC(inputString));

    }

}


