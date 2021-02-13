package ru.geekbrains.homework;

import java.util.Random;
import java.util.Scanner;


public class TicTac {

    // Поле
    private static char [][] map;
    private static final int SIZE = 3;
    private static final int DOT_TO_WIN = 3;

    // Ячейки поля
    private static final char DOT_EMPTY = '•';
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';

    private static Scanner sc = new Scanner(System.in);
    private static Random rand = new Random();

    public static void main(String[] args) {
        System.out.println("Fourth lesson");

        initMap();
        printMap();

        while (true) {
            humanTurn();
            printMap();

            if (checkWin(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }

            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }

            aiTurn();
            printMap();

            if (checkWin(DOT_O)) {
                System.out.println("Победил коспьютер");
                break;
            }
        }

        System.out.println("Игра законченна");
    }

    /*void customizeGame() {
        do {
            System.out.println("\nВведите число клеток на игровом поле (от 3 до 10): ");
            size = sc.nextInt();
        } while (size < 3 || size > 10);

        do {
            System.out.println("Колличество совпадений для победы (от 3 до " + size + "): ");
            block = sc.nextInt();
        } while (block < 3 || block > size);
    }*/

    private static void initMap() {
        map = new char[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    /**
     * Вывод поля в консоль
     */
    private static  final void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }

        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");

            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }

            System.out.println();
        }
    }

    private static void humanTurn() {
        int x, y; // координаты

        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));

        map[y][x] = DOT_X;
    }

    private static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        return map[y][x] == DOT_EMPTY;
    }

    private static void aiTurn() {
        int x, y; // координаты

        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));

        System.out.println("Компьютер сходил в точку " + (x + 1) + " " + (y + 1));

        map[y][x] = DOT_O;

    }

    private static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }

        return true;
    }

    private static boolean checkWin(char dot) {
        /*if (map[0][0] == symb && map[0][1] == symb && map[0][2] == symb) return true;
        if (map[1][0] == symb && map[1][1] == symb && map[1][2] == symb) return true;
        if (map[2][0] == symb && map[2][1] == symb && map[2][2] == symb) return true;

        if (map[0][0] == symb && map[1][0] == symb && map[2][0] == symb) return true;
        if (map[0][1] == symb && map[1][1] == symb && map[2][1] == symb) return true;
        if (map[0][2] == symb && map[1][2] == symb && map[2][2] == symb) return true;

        if (map[0][0] == symb && map[1][1] == symb && map[2][2] == symb) return true;
        if (map[2][0] == symb && map[1][1] == symb && map[0][2] == symb) return true;

        return false;*/
        for (int i = 0; i < 3; i++)
            if ((map[i][0] == dot && map[i][1] == dot && map[i][2] == dot) ||
                (map[0][i] == dot && map[1][i] == dot && map[2][i] == dot)) return true;

            if ((map[0][0] == dot && map[1][1] == dot && map[2][2] == dot) ||
                (map[2][0] == dot && map[1][1] == dot && map[0][2] == dot)) return true;

            return false;
    }

}

