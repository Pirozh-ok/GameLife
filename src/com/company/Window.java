package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Random;

public class Window implements Runnable {
    JFrame frame;
    Box[][] boxes;
    final Random random = new Random();
    boolean isRand;

    public Window(boolean isRand) {
        this.isRand = isRand;
    }

    @Override
    public void run() {
        initFrame();
        initBoxes();
        initTimer();
    }

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
    }

    void initBoxes() {
        boxes = new Box[Consts.WIDTH][Consts.HEIGHT];
        for (int x = 0; x < Consts.WIDTH; x++)
            for (int y = 0; y < Consts.HEIGHT; y++) {
                Cell cell = new Cell();
                boxes[x][y] = new Box(x, y);
                frame.add(boxes[x][y]);
            }

        for (int x = 0; x < Consts.WIDTH; x++)
            for (int y = 0; y < Consts.HEIGHT; y++) {
                for (int sx = -1; sx <= 1; sx++)
                    for (int sy = -1; sy <= 1; sy++) {
                        if (!(sx == 0 && sy == 0))
                            boxes[x][y].cell.addNear(boxes
                                    [(x + sx + Consts.WIDTH) % Consts.WIDTH]
                                    [(y + sy + Consts.HEIGHT) % Consts.HEIGHT].cell);
                    }

                if (isRand) {
                    if (random.nextInt() % Consts.DENSITY == 0) {
                        boxes[x][y].cell.status = Status.LIVE;
                        boxes[x][y].setColor();
                    }
                }
            }
    }

    private void initTimer() {
        TimerListener t1 = new TimerListener();
        Timer timer = new Timer(Consts.SLEEPMS, t1);
        timer.start();
    }

    private class TimerListener implements ActionListener {
        boolean flop = false;

        @Override
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
