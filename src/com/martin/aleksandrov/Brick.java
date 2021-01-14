package com.martin.aleksandrov;

import java.util.Arrays;

public class Brick {

    private int number;
    private int[] brick = new int[2];

    //Constructor
    public Brick(int number) {
        this.number = number;
        this.brick[0] = number;
        this.brick[1] = number;
    }

    //Getters
    public int getNumber() {
        return number;
    }

    public int[] getBrick() {
        return brick;
    }

    @Override
    public String toString() {
        return number + " " + number;
        //return Arrays.toString(brick);
    }
}
