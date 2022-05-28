package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Random;

public class Window implements Runnable {
    JFrame frame; // окно
    Box[][] boxes; //матрица панелей
    final Random random = new Random(); // генератор случайных чисел
    boolean isRand; // флаг рандомизации

    // Конструктор, устанавливающий режим игры
    public Window(boolean isRand) {
        this.isRand = isRand;
    }

    @Override
    public void run() {
        initFrame();
        initBoxes();
        initTimer();
    }

    // Инициализация главного окна
    void initFrame() {
        frame = new JFrame();
        frame.getContentPane().setLayout(null);
        frame.setSize(Consts.WIDTH * Consts.SIZE, Consts.HEIGHT * Consts.SIZE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo((null));
        frame.setVisible(true);
        frame.setTitle("Life Game");
        String path = "icon.png";
        URL imgURL = Window.class.getResource(path);
        ImageIcon icon = new ImageIcon(imgURL);
        frame.setIconImage(icon.getImage());
        frame.setResizable(false);
    }

    // Заполняем окно панелями
    void initBoxes() {
        // Создаём нужное количество панелей
        boxes = new Box[Consts.WIDTH][Consts.HEIGHT];
        for (int x = 0; x < Consts.WIDTH; x++)
            for (int y = 0; y < Consts.HEIGHT; y++) {
                Cell cell = new Cell();
                boxes[x][y] = new Box(x, y);
                frame.add(boxes[x][y]);
            }

        // Проходимся по каждой панели на окне
        for (int x = 0; x < Consts.WIDTH; x++)
            for (int y = 0; y < Consts.HEIGHT; y++) {
                for (int sx = -1; sx <= 1; sx++)
                    for (int sy = -1; sy <= 1; sy++) {
                        if (!(sx == 0 && sy == 0)) // В каждую клетку сохраняем список её соседей, для их отслеживания (кроме самой клетки)
                            boxes[x][y].cell.addNear(boxes
                                    [(x + sx + Consts.WIDTH) % Consts.WIDTH]
                                    [(y + sy + Consts.HEIGHT) % Consts.HEIGHT].cell);
                    }

                if (isRand) { // если режим игры рандомизированный, то случайные панели помечаем живыми организмами
                    if (random.nextInt() % Consts.DENSITY == 0) {
                        boxes[x][y].cell.status = Status.LIVE;
                        boxes[x][y].setColor();
                    }
                }
            }
    }
    // Инициализируем таймер. Задаём задержку обновления окна и функцию при обновлении
    private void initTimer() {
        TimerListener t1 = new TimerListener();
        Timer timer = new Timer(Consts.SLEEPMS, t1);
        timer.start();
    }

    // Создаём класс для отслеживания тиков таймера
    private class TimerListener implements ActionListener {
        boolean flop = false;
        @Override
        // В два действия производим смену поколения
        public void actionPerformed(ActionEvent e) {
            flop = !flop;
            for (int x = 0; x < Consts.WIDTH; x++)
                for (int y = 0; y < Consts.HEIGHT; y++) {
                    if (flop)
                        boxes[x][y].step1();
                    else
                        boxes[x][y].step2();
                }
        }
    }
}
