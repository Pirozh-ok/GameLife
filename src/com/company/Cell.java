package com.company;

import java.util.ArrayList;

public class Cell {
    Status status; // Состояние клетки
    ArrayList<Cell> near; // Список соседей клетки

    //Конструктор по умолчанию - состояние NONE по умолчанию и пустой список соседей
    public Cell() {
        status = Status.NONE;
        near = new ArrayList<>();
    }

    // Добавление соседа в список
    void addNear(Cell cell) {
        near.add(cell);
    }

    // Шаг 1 смены поколения (проверка количества соседей)
    void step1() {
        int around = countNearCells();
        status = status.step1(around);
    }

    // Шаг 2 смены поколения
    void step2() {
        status = status.step2();
    }

    // Подсчёт количества соседей клетки
    int countNearCells() {
        int count = 0;
        for (Cell cell : near) {
            if (cell.status.isLive())
                count++;
        }
        return count;
    }

    // Создание вокруг указанной точки живых организмов (вокруг указанной точки заселяются соседи)
    // При нажатии по организму - убивание его.
    void turn() {
        for (Cell cell : near) {
            cell.status = cell.status.isLive() ? Status.NONE : Status.LIVE;
        }
    }
}
