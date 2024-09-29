/*
 * Title: Main_hw6_1.java
 * Abstract: This program collects the maximum number of coins on an n x m board.
 * Date: 2/20/2024
 */

import java.util.Scanner;

class Main 
{

  // 2d array to store the optimal path
  static String[][] path;

  // 2d array to store the number of coins to that point
  static int[][] maxCoins;

  public static void main(String[] args) {

    Scanner scan = new Scanner(System.in);

    // get number of rows and columns from user
    int rows = scan.nextInt();
    int columns = scan.nextInt();

    // create 2d array for the board and declare path & maxCoins arrays
    int[][] board = new int[rows][columns];
    path = new String[rows][columns];
    maxCoins = new int[rows][columns];

    // get board array values from the user
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        board[i][j] = scan.nextInt();
      }
    }

    // call the countMaxCoins function
    int maxCoinsValue = countMaxCoins(board, rows, columns);

    // final output
    System.out.println("Max coins:" + maxCoinsValue); // max coins
    System.out.print("Path:");
    printOptimalPath(rows - 1, columns - 1, rows, columns); // call to print optimal path
    System.out.println();

    scan.close();
  }

  // function to count the max coins
  public static int countMaxCoins(int[][] board, int rows, int columns) {
    maxCoins[0][0] = board[0][0];

    // first row
    for (int j = 1; j < columns; j++) {
      maxCoins[0][j] = maxCoins[0][j - 1] + board[0][j];
      path[0][j] = "left";
    }

    // first column
    for (int i = 1; i < rows; i++) {
      maxCoins[i][0] = maxCoins[i - 1][0] + board[i][0];
      path[i][0] = "above";
    }

    // remainder of the maxCoins table
    for (int i = 1; i < rows; i++) {
      for (int j = 1; j < columns; j++) {
        if (maxCoins[i - 1][j] > maxCoins[i][j - 1]) {
          maxCoins[i][j] = maxCoins[i - 1][j] + board[i][j];
          path[i][j] = "above";
        } else {
          maxCoins[i][j] = maxCoins[i][j - 1] + board[i][j];
          path[i][j] = "left";
        }
      }
    }
    return maxCoins[rows - 1][columns - 1];
  }

  // function to print the optimal path
  public static void printOptimalPath(int i, int j, int rows, int columns) {

    // universal starting point
    if (i == 0 && j == 0) {
      System.out.print("(1,1)");
      return;
    }

    // first row
    if (i == 0 && j != 0) {
      printOptimalPath(i, j - 1, rows, columns);
    }

    // first column
    else if (i != 0 && j == 0) {
      printOptimalPath(i - 1, j, rows, columns);
    }

    else {
      // optimal path comes from left
      if (path[i][j].equals("left")) {
        printOptimalPath(i, j - 1, rows, columns);
      }
      // optimal path comes from above
      else {
        printOptimalPath(i - 1, j, rows, columns);
      }
    }
    System.out.print("->(" + (i + 1) + "," + (j + 1) + ")");
  }

}