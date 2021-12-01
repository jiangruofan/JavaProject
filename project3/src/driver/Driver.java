package driver;

import dungeon.Dungeon;
import player.Player;
import random.RandomInterface;
import random.RealRandom;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * This is a driver class to show how the program works.
 */
public class Driver {
  /**
   * This is the main function.
   */
  public static void main(String[] args) {
    RandomInterface random = new RealRandom();
    int row = Integer.parseInt(args[0]);
    int column = Integer.parseInt(args[1]);
    int interconnectivity = Integer.parseInt(args[2]);
    boolean wrap = Objects.equals(args[3], "true");
    int percent = Integer.parseInt(args[4]);
    Dungeon res = new Dungeon(row, column, interconnectivity, wrap, percent, random);
    String[][] str = res.showMap();
    for (int i = 0; i < str.length; i++) {
      StringBuilder str1 = new StringBuilder();
      for (int j = 0; j < str[0].length; j++) {
        str1.append(str[i][j]);
      }
      System.out.println(str1);
    }

    Player player1 = new Player(res.getStartPoint());
    Scanner scan = new Scanner(System.in);
    String input;
    int i = 1;
    boolean b = false;

    while (!res.judgeWhetherEnd(player1.getLoc())) {
      System.out.println("current location is" + Arrays.toString(player1.getLocInPoint()));
      System.out.println("the information of location is:");
      System.out.println(res.getDescription(player1.getLoc()));
      if (res.getValidTreasure(player1.getLoc()).size() != 0) {
        System.out.println("input treasures you want to pick up, if more than one" +
                " treasure, split treasures by" +
                "',' if no treasure you want to pick up, input no");
        while (i == 1) {
          input = scan.next();
          i = player1.pickUpTreasure(input, res.getValidTreasure(player1.getLoc()));
          if (i == 1) {
            System.out.println("invalid input, please input again");
          } else if (i == 2) {
            System.out.println("you choose to pick up no treasure");
          } else {
            res.updateTreasure(player1.getLoc(), input);
            System.out.println("you successfully pick up the treasure");
          }
        }
        i = 1;
      }
      System.out.println("treasure you currently have:");
      System.out.println(player1.getDescription());

      System.out.println("input the direction you want to move");
      while (!b) {
        input = scan.next();
        b = player1.move(input, res.getValidMove(player1.getLoc()));
        if (b) {
          System.out.println("you successfully moved to "
                  + Arrays.toString(player1.getLocInPoint()));
        } else {
          System.out.println("invalid output, please input again");
        }
      }
      b = false;
    }
    System.out.println("you reached the end, game over");
  }
}