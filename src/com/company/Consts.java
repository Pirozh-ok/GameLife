package com.company;

import java.awt.*;

public class Consts {
    public static final int SIZE = 10;
    public static final int WIDTH = 90;
    public static final int HEIGHT = 60;
    public static final int SLEEPMS = 200;
    public static final int DENSITY = 8;

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
