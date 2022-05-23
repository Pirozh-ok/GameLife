package com.company;

import java.util.ArrayList;

public class Cell {
    Status status;
    ArrayList<Cell> near;

    public Cell() {
        status = Status.NONE;
        near = new ArrayList<>();
    }

    void addNear(Cell cell) {
        near.add(cell);
    }

    void step1() {
        int around = countNearCells();
        status = status.step1(around);
    }

    void step2() {
        status = status.step2();
    }

    int countNearCells() {
        int count = 0;
        for (Cell cell : near) {
            if (cell.status.isLive())
                count++;
        }
        return count;
    }

    void turn() {
        for (Cell cell : near) {
            cell.status = cell.status.isLive() ? Status.NONE : Status.LIVE;
        }
    }
}
