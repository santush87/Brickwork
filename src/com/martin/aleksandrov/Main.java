package com.martin.aleksandrov;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // write your code here

        BrickBuilder builder = new BrickBuilder();

/*********      FIRST EXAMPLE        ***************/

        List<int[]> first = new ArrayList<>();
        first.add(new int[]{2, 4});
        first.add(new int[]{1, 1, 2, 2});
        first.add(new int[]{3, 3, 4, 4});
        builder.wallChecker(first);
        System.out.println("===================");

/*********      SECOND EXAMPLE        ***************/

        List<int[]> second = new ArrayList<>();
        second.add(new int[]{2, 8});
        second.add(new int[]{1, 1, 2, 2, 6, 5, 5, 8});
        second.add(new int[]{3, 3, 4, 4, 6, 7, 7, 8});
        builder.wallChecker(second);
        System.out.println("===================");

/*********      THIRD EXAMPLE        ***************/

        List<int[]> third = new ArrayList<>();
        third.add(new int[]{4, 8});
        third.add(new int[]{1, 2, 2, 12, 5, 7, 7, 16});
        third.add(new int[]{1, 10, 10, 12, 5, 15, 15, 16});
        third.add(new int[]{9, 9, 3, 4, 4, 8, 8, 14});
        third.add(new int[]{11, 11, 3, 13, 13, 6, 6, 14});
        builder.wallChecker(third);
        System.out.println("===================");
    }
}