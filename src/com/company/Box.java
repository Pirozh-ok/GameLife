package com.company;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Box extends JPanel {

    Cell cell;

    public Box(int x, int y) {
        super();
        this.cell = new Cell();
        setBounds(x * Consts.SIZE, y * Consts.SIZE, Consts.SIZE, Consts.SIZE);
        setBackground(Consts.getColor(Status.NONE));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cell.turn();
            }
        });
    }

    public void setColor() {
        setBackground(Consts.getColor(cell.status));
    }

    void step1() {
        cell.step1();
        setColor();
    }

    void step2() {
        cell.step2();
        setColor();
    }
}
