package com.company;

public class Main {

    public static void main(String[] args) {
        StartWindow start = new StartWindow(); // создаём окно меню
        javax.swing.SwingUtilities.invokeLater(start); // запускаем окно
    }
}