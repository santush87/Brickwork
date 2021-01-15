package com.martin.aleksandrov;

import java.util.ArrayList;
import java.util.List;

public class BrickBuilder {

    public boolean wallChecker(List<int[]> numbers) {

/***********************    Declarations    *****************/
        // This will be the array with the final result
        int[][] finalResult;
        // Collect bricks which are Vertical or Horizontal at the inputNumbers
        ArrayList<Brick> bricks = new ArrayList<>();
        // Row's number for the new wall
        int newRowNumber = findNewRows(numbers);

/**************************         Validations        *********************************/

        /*****  Rows cannot be more than 100  *****/
        if (newRowNumber > 100) {
            return false;
        }

        /** Looking for something line this 2 2  or   3 3  If exist - return false */
        /*                                  2           3                          */
        for (int row = 0; row < numbers.size() - 1; row++) {
            for (int column = 0; column < numbers.get(row).length - 1; column++) {
                if (numbers.get(row)[column] == numbers.get(row)[column + 1] &&
                        (numbers.get(row)[column] == numbers.get(row + 1)[column] ||
                                numbers.get(row)[column + 1] == numbers.get(row + 1)[column + 1])) {
                    System.out.println(-1);
                    return false;
                }
            }
        }
        /** Looking for something line this 2   or    3   If exist - return false */
        /*                                  2 2     3 3                           */
        for (int row = numbers.size() - 1; row > 0; row--) {
            int rowSize;
            // Testing which row is smaller and use it for the column's length
            if (numbers.get(row).length > numbers.get(row - 1).length) {
                rowSize = numbers.get(row - 1).length;
            } else {
                rowSize = numbers.get(row).length;
            }
            for (int column = 0; column < rowSize - 1; column++) {
                if (numbers.get(row)[column] == numbers.get(row)[column + 1] &&
                        (numbers.get(row)[column] == numbers.get(row - 1)[column] ||
                                numbers.get(row)[column + 1] == numbers.get(row - 1)[column + 1])) {
                    System.out.println(-1);
                    return false;
                }
            }
        }

        /** Checking if 3 columns of one row have same number. If YES this means there is no solution! **/
        /**      Example: horizontal (1 1 1)  ****/
        for (int row = 0; row < numbers.size(); row++) {
            for (int column = 0; column < numbers.get(row).length - 2; column++) {
                // Searching for 3 Horizontal matches
                if (numbers.get(row)[column] == numbers.get(row)[column + 1] &&
                        numbers.get(row)[column] == numbers.get(row)[column + 2]) {
                    System.out.println(-1);
                    return false;
                }
            }
        }

        /** Checking if 3 columns of one row have same number. If YES this means there is no solution! **/
        /**      Example: Vertical (1 1 1)  ****/
        for (int row = 0; row < numbers.size() - 2; row++) {
            // Check if the current row is bigger then next one. We always use the smaller one!
            if (numbers.get(row).length <= numbers.get(row + 1).length &&
                    numbers.get(row).length <= numbers.get(row + 2).length) {
                for (int column = 0; column < numbers.get(row).length; column++) {
                    // Searching for 3 Vertical matches
                    if (numbers.get(row)[column] == numbers.get(row + 1)[column] &&
                            numbers.get(row)[column] == numbers.get(row + 2)[column]) {
                        System.out.println(-1);
                        return false;
                    }
                }
            }
            // Check if the current row is smaller then next one.
            if (numbers.get(row).length > numbers.get(row + 1).length) {
                for (int column = 0; column < numbers.get(row + 1).length; column++) {
                    // Searching for 3 Vertical matches
                    if (numbers.get(row)[column] == numbers.get(row + 1)[column] &&
                            numbers.get(row)[column] == numbers.get(row + 2)[column]) {
                        System.out.println(-1);
                        return false;
                    }
                }
            }
        }
/********************** Finding all bricks and add them to an array ******************************************/

        /** Loop to find all Horizontal bricks **/
        for (int row = 0; row < numbers.size(); row++) {
            for (int column = 0; column < numbers.get(row).length - 1; column++) {
                // Searching for Horizontal match and when we find, we are adding it to bricks
                if (numbers.get(row)[column] == numbers.get(row)[column + 1]) {
                    bricks.add(new Brick(numbers.get(row)[column]));
                    column++;
                }
            }
        }
        /** Loop to find all Vertical bricks **/
        for (int row = 0; row < numbers.size() - 1; row++) {
            // Check if the current row is bigger then next one. We always use the smaller one!
            if (numbers.get(row).length <= numbers.get(row + 1).length) {
                for (int column = 0; column < numbers.get(row).length; column++) {
                    // Searching for Vertical match and when we find, we are adding it to bricks
                    if (numbers.get(row)[column] == numbers.get(row + 1)[column]) {
                        bricks.add(new Brick(numbers.get(row)[column]));
                    }
                }
            }
            // Check if the current row is smaller then next one.
            if (numbers.get(row).length > numbers.get(row + 1).length) {
                for (int column = 0; column < numbers.get(row + 1).length; column++) {
                    // Searching for Vertical match and when we find, we are adding it to bricks
                    if (numbers.get(row)[column] == numbers.get(row + 1)[column]) {
                        bricks.add(new Brick(numbers.get(row)[column]));
                    }
                }
            }
        }
/*****************************************************************************************************/

        // Finding if the bricks for for one row are lower than 2 bricks. If YES - return false
        if ((bricks.size() / newRowNumber) < 2) {
            System.out.println(-1);
            return false;
        }

        int bricksInOneRow = bricks.size() / newRowNumber;
        int columnSize = bricksInOneRow * 2;

        /**     Columns cannot be more than 100    **/
        if (columnSize > 100) {
            return false;
        }

        // if in one row different number of bricks than 2, 4, 6, 8... etc, return false
        if (bricksInOneRow % 2 != 0) {
            System.out.println(-1);
            return false;
        }
        // Declaring the rows and columns in the array with final result
        finalResult = new int[newRowNumber][bricksInOneRow * 2];

/******************* The loop for adding the numbers to the array *******************/
        for (int row = 0; row < newRowNumber; row += 2) {
            for (int column = 0; column < columnSize; column = column + 4) {

                // If the last row begin for example: 1 1
                if (numbers.get(row)[0] == numbers.get(row)[1]) {
                    // Vertical
                    finalResult[row][column] = bricks.get(row * column).getNumber();
                    finalResult[row + 1][column] = bricks.get(row * column).getNumber();
                    // Horizontal
                    finalResult[row][column + 1] = bricks.get(row * column + 1).getNumber();
                    finalResult[row][column + 2] = bricks.get(row * column + 1).getNumber();
                    // Horizontal
                    finalResult[row + 1][column + 1] = bricks.get(row * column + 2).getNumber();
                    finalResult[row + 1][column + 2] = bricks.get(row * column + 2).getNumber();
                    // Vertical
                    finalResult[row][column + 3] = bricks.get(row * column + 3).getNumber();
                    finalResult[row + 1][column + 3] = bricks.get(row * column + 3).getNumber();

                    if ((column + 4) > bricksInOneRow * 2) {
                        // Horizontal
                        finalResult[row + 1][column + 4] = bricks.get(row * column + 4).getNumber();
                        finalResult[row + 1][column + 5] = bricks.get(row * column + 4).getNumber();
                        // Horizontal
                        finalResult[row][column + 4] = bricks.get(row * column + 5).getNumber();
                        finalResult[row][column + 5] = bricks.get(column + 5).getNumber();
                        // Horizontal
                        finalResult[row + 1][column + 6] = bricks.get(row * column + 6).getNumber();
                        finalResult[row + 1][column + 7] = bricks.get(row * column + 6).getNumber();
                        // Horizontal
                        finalResult[row][column + 6] = bricks.get(row * column + 7).getNumber();
                        finalResult[row][column + 7] = bricks.get(row * column + 7).getNumber();
                    }
                } else {
                    // Horizontal
                    finalResult[row + 1][column] = bricks.get(column).getNumber();
                    finalResult[row + 1][column + 1] = bricks.get(column).getNumber();
                    // Horizontal
                    finalResult[row][column] = bricks.get(column + 1).getNumber();
                    finalResult[row][column + 1] = bricks.get(column + 1).getNumber();
                    // Horizontal
                    finalResult[row + 1][column + 2] = bricks.get(column + 2).getNumber();
                    finalResult[row + 1][column + 3] = bricks.get(column + 2).getNumber();
                    // Horizontal
                    finalResult[row][column + 2] = bricks.get(column + 3).getNumber();
                    finalResult[row][column + 3] = bricks.get(column + 3).getNumber();
                }
            }
        }

        calculation(bricks, numbers);
        return true;
    }


    /************* Checking how many rows should have the the wall *****************/
    private int findNewRows(List<int[]> numbers) {
        int rowCount;
        if (numbers.size() < 2) {
            return -1;
        }
        if (numbers.size() % 2 == 1) {
            rowCount = numbers.size() - 1;
        } else {
            rowCount = numbers.size();
        }
        return rowCount;
    }

/*************************************************************************************************************/

/*************************************************************************************************************/

    private boolean calculation(ArrayList<Brick> bricks, List<int[]> numbers) {
        /*** Row's number for the new wall ***/
        int rowCount = findNewRows(numbers);

        int bricksInOneRow = bricks.size() / rowCount;
        /*** if there are different number of bricks than 2, 4, 6, 8... etc in one row: return false ***/
        if (bricksInOneRow % 2 != 0) {
            System.out.println(-1);
            return false;
        }
        /*** number of columns in every row ***/
        int columnCount = bricksInOneRow * 2;

        /*** Creating array for final result ***/
        int[][] finalResult = new int[rowCount][columnCount];

        /** Increase this index in the loop to reach next bricks in the array **/
        int bricksIndex = 0;

/************************ The loop begin for adding the bricks and show the last result ************************/
        // The loop for adding the numbers to the array
        for (int row = 0; row < rowCount; row = row + 2) {
            // Increasing column with 8, because in one loop are going trough 8 columns
            for (int column = 0; column < columnCount; column = column + 8) {

/*********************** If the row begin with Horizontal match for example: 1 1 ******************************/
                if (numbers.get(row + 1)[0] == numbers.get(row + 1)[1]) {

                    /***** if the columns = 4 *****/
                    if (columnCount == 4) {
                        // Vertical
                        finalResult[row][column] = bricks.get(column + bricksIndex).getNumber();
                        finalResult[row + 1][column] = bricks.get(column + bricksIndex).getNumber();
                        // Horizontal
                        finalResult[row][column + 1] = bricks.get(column + bricksIndex + 1).getNumber();
                        finalResult[row][column + 2] = bricks.get(column + bricksIndex + 1).getNumber();
                        // Horizontal
                        finalResult[row + 1][column + 1] = bricks.get(column + bricksIndex + 2).getNumber();
                        finalResult[row + 1][column + 2] = bricks.get(column + bricksIndex + 2).getNumber();
                        // Vertical
                        finalResult[row][column + 3] = bricks.get(column + bricksIndex + 3).getNumber();
                        finalResult[row + 1][column + 3] = bricks.get(column + bricksIndex + 3).getNumber();
                    }

                    /********************* if the columns > 4 **********************/
                    if (columnCount > 4) {
                        /****** If row = 0, 4, 8 ... ect. ********/
                        if (row % 4 == 0 || row == 0) {
                            if (column % 8 == 0 || column == 0) {
                                // Vertical
                                finalResult[row][column] = bricks.get(column * row + bricksIndex).getNumber();
                                finalResult[row + 1][column] = bricks.get(column * row + bricksIndex).getNumber();
                                // Horizontal
                                finalResult[row][column + 1] = bricks.get(column * row + bricksIndex + 1).getNumber();
                                finalResult[row][column + 2] = bricks.get(column * row + bricksIndex + 1).getNumber();
                                // Horizontal
                                finalResult[row + 1][column + 1] = bricks.get(column * row + bricksIndex + 2).getNumber();
                                finalResult[row + 1][column + 2] = bricks.get(column * row + bricksIndex + 2).getNumber();
                                // Vertical
                                finalResult[row][column + 3] = bricks.get(column * row + bricksIndex + 3).getNumber();
                                finalResult[row + 1][column + 3] = bricks.get(column * row + bricksIndex + 3).getNumber();

                                // Horizontal
                                finalResult[row + 1][column + 4] = bricks.get(column * row + bricksIndex + 4).getNumber();
                                finalResult[row + 1][column + 5] = bricks.get(column * row + bricksIndex + 4).getNumber();
                                // Horizontal
                                finalResult[row][column + 4] = bricks.get(column * row + bricksIndex + 5).getNumber();
                                finalResult[row][column + 5] = bricks.get(column * row + bricksIndex + 5).getNumber();
                                // Horizontal
                                finalResult[row + 1][column + 6] = bricks.get(column * row + bricksIndex + 6).getNumber();
                                finalResult[row + 1][column + 7] = bricks.get(column * row + bricksIndex + 6).getNumber();
                                // Horizontal
                                finalResult[row][column + 6] = bricks.get(column * row + bricksIndex + 7).getNumber();
                                finalResult[row][column + 7] = bricks.get(column * row + bricksIndex + 7).getNumber();
                            }
                        } else {
                            /****** If row != 0, 4, 8 ... ect. ********/
                            // To go to next part from array<bricks>
                            bricksIndex += 8;

                            // Vertical
                            finalResult[row][column] = bricks.get(column * row + bricksIndex).getNumber();
                            finalResult[row + 1][column] = bricks.get(column * row + bricksIndex).getNumber();
                            // Horizontal
                            finalResult[row][column + 1] = bricks.get(column * row + bricksIndex + 1).getNumber();
                            finalResult[row][column + 2] = bricks.get(column * row + bricksIndex + 1).getNumber();
                            // Horizontal
                            finalResult[row + 1][column + 1] = bricks.get(column * row + bricksIndex + 2).getNumber();
                            finalResult[row + 1][column + 2] = bricks.get(column * row + bricksIndex + 2).getNumber();
                            // Vertical
                            finalResult[row][column + 3] = bricks.get(column * row + bricksIndex + 3).getNumber();
                            finalResult[row + 1][column + 3] = bricks.get(column * row + bricksIndex + 3).getNumber();

                            // Horizontal
                            finalResult[row][column + 4] = bricks.get(row * column + bricksIndex + 4).getNumber();
                            finalResult[row][column + 5] = bricks.get(row * column + bricksIndex + 4).getNumber();
                            // Horizontal
                            finalResult[row + 1][column + 4] = bricks.get(row * column + bricksIndex + 5).getNumber();
                            finalResult[row + 1][column + 5] = bricks.get(row * column + bricksIndex + 5).getNumber();
                            // Horizontal
                            finalResult[row][column + 6] = bricks.get(row * column + bricksIndex + 6).getNumber();
                            finalResult[row][column + 7] = bricks.get(row * column + bricksIndex + 6).getNumber();
                            // Horizontal
                            finalResult[row + 1][column + 6] = bricks.get(row * column + bricksIndex + 7).getNumber();
                            finalResult[row + 1][column + 7] = bricks.get(row * column + bricksIndex + 7).getNumber();
                        }
                    }
                }
/*********************** If the row doesn't begin with Horizontal match for example: 1 1 ******************************/
                if (numbers.get(row+1)[0] != numbers.get(row+1)[1]) {

                    /***** if the columns = 4 *****/
                    if (columnCount == 4) {
                        // Horizontal
                        finalResult[row][column] = bricks.get(column * row + bricksIndex).getNumber();
                        finalResult[row][column + 1] = bricks.get(column * row + bricksIndex).getNumber();
                        // Horizontal
                        finalResult[row][column + 2] = bricks.get(column * row + bricksIndex + 1).getNumber();
                        finalResult[row][column + 3] = bricks.get(column * row + bricksIndex + 1).getNumber();
                        // Horizontal
                        finalResult[row + 1][column] = bricks.get(column * row + bricksIndex + 2).getNumber();
                        finalResult[row + 1][column + 1] = bricks.get(column * row + bricksIndex + 2).getNumber();
                        // Horizontal
                        finalResult[row + 1][column + 2] = bricks.get(column * row + bricksIndex + 3).getNumber();
                        finalResult[row + 1][column + 3] = bricks.get(column * row + bricksIndex + 3).getNumber();
                    }

                    /************** if the columns > 4 **********************/
                    if (columnCount > 4) {
                        if (row % 4 == 0 || row == 0) {
                            if (column % 8 == 0 || column == 0) {

                                // Horizontal
                                finalResult[row][column] = bricks.get(column * row + bricksIndex).getNumber();
                                finalResult[row][column + 1] = bricks.get(column * row + bricksIndex).getNumber();
                                // Horizontal
                                finalResult[row + 1][column] = bricks.get(column * row + bricksIndex + 1).getNumber();
                                finalResult[row + 1][column + 1] = bricks.get(column * row + bricksIndex + 1).getNumber();
                                // Horizontal
                                finalResult[row][column + 2] = bricks.get(column * row + bricksIndex + 2).getNumber();
                                finalResult[row][column + 3] = bricks.get(column * row + bricksIndex + 2).getNumber();
                                // Horizontal
                                finalResult[row + 1][column + 2] = bricks.get(column * row + bricksIndex + 3).getNumber();
                                finalResult[row + 1][column + 3] = bricks.get(column * row + bricksIndex + 3).getNumber();

                                // Vertical
                                finalResult[row][column + 4] = bricks.get(row * column + bricksIndex + 4).getNumber();
                                finalResult[row + 1][column + 4] = bricks.get(row * column + bricksIndex + 4).getNumber();
                                // Horizontal
                                finalResult[row][column + 5] = bricks.get(row * column + bricksIndex + 5).getNumber();
                                finalResult[row][column + 6] = bricks.get(row * column + bricksIndex + 5).getNumber();
                                // Horizontal
                                finalResult[row + 1][column + 5] = bricks.get(row * column + bricksIndex + 6).getNumber();
                                finalResult[row + 1][column + 6] = bricks.get(row * column + bricksIndex + 6).getNumber();
                                // Vertical
                                finalResult[row][column + 7] = bricks.get(row * column + bricksIndex + 7).getNumber();
                                finalResult[row + 1][column + 7] = bricks.get(row * column + bricksIndex + 7).getNumber();
                            }
                        } else {
                            /****** If row != 0, 4, 8 ... ect. ********/
                            // To go to next part from array<bricks>
                            bricksIndex += 8;

                            // Horizontal
                            finalResult[row][column] = bricks.get(column * row + bricksIndex).getNumber();
                            finalResult[row][column + 1] = bricks.get(column * row + bricksIndex).getNumber();
                            // Horizontal
                            finalResult[row + 1][column] = bricks.get(column * row + bricksIndex + 1).getNumber();
                            finalResult[row + 1][column + 1] = bricks.get(column * row + bricksIndex + 1).getNumber();
                            // Horizontal
                            finalResult[row][column + 2] = bricks.get(column * row + bricksIndex + 2).getNumber();
                            finalResult[row][column + 3] = bricks.get(column * row + bricksIndex + 2).getNumber();
                            // Horizontal
                            finalResult[row + 1][column + 2] = bricks.get(column * row + bricksIndex + 3).getNumber();
                            finalResult[row + 1][column + 3] = bricks.get(column * row + bricksIndex + 3).getNumber();

                            // Vertical
                            finalResult[row][column + 4] = bricks.get(row * column + bricksIndex + 4).getNumber();
                            finalResult[row + 1][column + 4] = bricks.get(row * column + bricksIndex + 4).getNumber();
                            // Horizontal
                            finalResult[row][column + 5] = bricks.get(row * column + bricksIndex + 5).getNumber();
                            finalResult[row][column + 6] = bricks.get(row * column + bricksIndex + 5).getNumber();
                            // Horizontal
                            finalResult[row + 1][column + 5] = bricks.get(row * column + bricksIndex + 6).getNumber();
                            finalResult[row + 1][column + 6] = bricks.get(row * column + bricksIndex + 6).getNumber();
                            // Vertical
                            finalResult[row][column + 7] = bricks.get(row * column + bricksIndex + 7).getNumber();
                            finalResult[row + 1][column + 7] = bricks.get(row * column + bricksIndex + 7).getNumber();
                        }
                    }
                }
            }
        }
            for (int row = 0; row < finalResult.length; row++) {
                for (int column = 0; column < finalResult[row].length; column++) {
                    System.out.print(finalResult[row][column] + "\t");
                }
                System.out.println();
            }

        return true;
    }
}