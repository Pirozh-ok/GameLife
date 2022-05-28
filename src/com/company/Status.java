package com.company;

public enum Status {
    NONE, //0 нет жизни
    BORN, //1 появилась жизнь
    LIVE, //2 продолжает жить
    DIED; //3 погиб

    // 1 шаг. В зависимости от количества окружающих организмов
    // клетка получает статус рождённой или погибшей
    public Status step1(int around) {
        switch (this) {
            case NONE:
                return (around == 3) ? BORN : NONE;
            case LIVE:
                return (around <= 1 || around >= 4) ? DIED : LIVE;
            default:
                return this;
        }
    }

    // 2 шаг. Если клетка родилась, присваиваем статус живой и перекаршиваем
    // Если клетка погибающая, то зануляем её и перекрашиваем в пустоту
    public Status step2() {
        switch (this) {
            case BORN:
                return LIVE;
            case DIED:
                return NONE;
            default:
                return this;
        }
    }

    // Проверка статуса клетки
    public boolean isLive() {
        return this == LIVE || this == DIED;
    }
}
