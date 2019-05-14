package com.lslevins.wordsearcher;

import java.util.Random;

/**
 * Created by Luke Slevinsky on 2019-05-12.
 */
public enum Direction {
    NONE(0, 0),
    RIGHT(1, 0),
    LEFT(-1, 0),
    UP(0, -1),
    DOWN(0, 1),
    DOWN_RIGHT(1, 1),
    UP_LEFT(-1, -1),
    DOWN_LEFT(-1, 1),
    UP_RIGHT(1, -1);

    public final int x;
    public final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Direction nextDirection() {
        int idx = (ordinal() + 1) % values().length;
        if (values()[idx] == NONE)
            idx++;
        return values()[idx];
    }
}
