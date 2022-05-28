package com.company;

import java.awt.*;

public class Consts {
    public static final int SIZE = 10; // размер панели
    public static final int WIDTH = 90; //ширина окна игры
    public static final int HEIGHT = 60; //высота окна игры
    public static final int SLEEPMS = 200; // пауза между обновлением окна
    public static final int DENSITY = 8; // частота заселения карты при выборе случайного режима

    // Получение цвета панели в зависимости от состояния организма
    public static Color getColor(Status status) {
        switch (status) {
            default:
            case NONE:
                return Color.BLACK;
            case BORN:
                return Color.GRAY;
            case LIVE:
                return Color.ORANGE;
            case DIED:
                return Color.RED;
        }
    }
}
