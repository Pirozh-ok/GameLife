package com.company;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Box extends JPanel {

    Cell cell; //организм, который отображает панель

    // Конструктор, который устанавливает координаты панели, её цвет
    public Box(int x, int y) {
        super();
        this.cell = new Cell();
        setBounds(x * Consts.SIZE, y * Consts.SIZE, Consts.SIZE, Consts.SIZE);
        setBackground(Consts.getColor(Status.NONE));
        addMouseListener(new MouseAdapter() { // Обработка события клика мышкой - добавляем пачку организмов (8 штук)
            @Override
            public void mousePressed(MouseEvent e) {
                cell.turn();
            }
        });
    }
    // Установка цвета в зависимости от цвета
    public void setColor() {
        setBackground(Consts.getColor(cell.status));
    }
    // Шаг 1 смены поколения
    void step1() {
        cell.step1();
        setColor();
    }
    // Шаг 2 смены поколения
    void step2() {
        cell.step2();
        setColor();
    }
}
