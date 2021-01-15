package com.martin.aleksandrov;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Instance of BrickBuilder.class to can access the methods in it
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

/*********      EXAMPLE WITH MANUAL INPUT       ***************/

        builder.wallChecker(input());
    }

/*********************************************************************************************/

    /******     Method to manually input numbers    *******/
    static List<int[]> input() {
        // Adding scanner, because we need to type the numbers
        Scanner scanner = new Scanner(System.in);

        // The new list in which we collect the result
        List<int[]> rowList = new ArrayList<>();

        System.out.println("Enter number if rows: ");
        int rowNumbers = scanner.nextInt();
        scanner.nextLine();

        if (rowNumbers > 100) {
            System.out.println("Please enter a number between 1 and 100:");
            rowNumbers = scanner.nextInt();
            scanner.nextLine();
        }

        // Loop in each row
        for (int i = 0; i < rowNumbers; i++) {
            System.out.println("Enter numbers for the " + (i + 1) + " row:");
            String stringNumbers = scanner.nextLine();
            // Splitting the string we wrote by "space", and each peace we put in new array of strings
            String[] nums = stringNumbers.split(" ");

            //check if the length is more than 100
            if (nums.length >= 100) {
                System.out.println("Allowed count of numbers are between 1 and 100");
                System.out.println(-1);
                break;
            }
            // Creating a temporary array of int with size from string array above
            int[] temp = new int[nums.length];

            // Loop in the string array and add the result in our temp array. But before
            // adding we change it from "string" to "int"
            for (int column = 0; column < nums.length; column++) {
                temp[column] = Integer.parseInt(nums[column]);
            }
            // Add the row in the list
            rowList.add(temp);
        }

        System.out.println("INPUT NUMBERS:");
        for (int row = 0; row < rowList.size(); row++) {
            for (int column = 0; column < rowList.get(row).length; column++) {
                System.out.print(rowList.get(row)[column] + "\t");
            }
            System.out.println();
        }
        System.out.println("=======");
        return rowList;
    }
}