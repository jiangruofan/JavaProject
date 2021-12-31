package controller;

import manager.Manager;

import java.io.IOException;

import java.util.Arrays;
import java.util.Scanner;

/**
 * This is a console controller for the dungeon play.
 */
public class DungeonControllerImpl implements DungeonController {
  private final Appendable out;
  private final Scanner scan;

  /**
   * Constructor for the controller.
   *
   * @param in  the source to read from
   * @param out the target to print to
   */
  public DungeonControllerImpl(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
  }

  @Override
  public void playGame(Manager manager) {
    try {
      if (manager == null) {
        throw new IllegalArgumentException("input should not be null");
      }
      String input;
      int i = 1;
      boolean b = false;

      while (manager.judgeEnd() == 1) {
        out.append("current location is ")
                .append(Arrays.toString(manager.getPlayerLoc())).append("\n");
        out.append("the information of location is: ");
        out.append(manager.getLocDescription().toString()).append("\n");

        int det = manager.detectMonster();
        if (det == 1) {
          out.append("there is a less pungent smell").append("\n");
        } else if (det == 2) {
          out.append("there is a great pungent smell").append("\n");
        }

        if (manager.whetherHaveTreasure()) {
          out.append("input treasures you want to pick up, if more than one" +
                  " treasure, split treasures by" +
                  "',' if no treasure you want to pick up, input no").append("\n");
          while (i == 1) {
            input = scan.next();
            i = manager.pickUpTreasure(input);
            if (i == 1) {
              out.append("invalid input, please input again").append("\n");
            } else if (i == 2) {
              out.append("you choose to pick up no treasure").append("\n");
            } else {
              manager.updateTreasure(input);
              out.append("you successfully pick up the treasure").append("\n");
            }
          }
          i = 1;
        }

        if (manager.judgeWhetherHaveArrow()) {
          out.append("do you want to pick up the arrow? if yes," +
                  " Please input yes, any other answer will be considered no").append("\n");
          input = scan.next();
          if (manager.pickUpArrow(input)) {
            out.append("you successfully pick up an arrow").append("\n");
          } else {
            out.append("you choose not to pick up the arrow").append("\n");
          }
        }

        out.append("player's information: ");
        out.append(manager.getPlayerDescription().toString()).append("\n");

        int i1;

        while (manager.judgeWhetherHaveArrow1()) {
          out.append("Do you want to shoot? if yes, input the direction and distance" +
                  " split by ',' otherwise input no").append("\n");
          input = scan.next();
          i1 = manager.shot(input);
          while (i1 == 3) {
            out.append("invalid input, please input again").append("\n");
            input = scan.next();
            i1 = manager.shot(input);
          }
          if (i1 == 4) {
            break;
          } else if (i1 == 1) {
            out.append("you successfully hit a monster").append("\n");
          } else {
            out.append("you don't hit the monster").append("\n");
          }
        }

        if (!manager.judgeWhetherHaveArrow1()) {
          out.append("you don't have any arrow now").append("\n");
        }

        out.append("input the direction you want to move").append("\n");
        while (!b) {
          input = scan.next();
          b = manager.move(input);
          if (!b) {
            out.append("invalid input, please input again").append("\n");
          }
          /*
          if (b) {
            out.append("you successfully moved to ").append("\n");
            out.append(Arrays.toString(manager.getPlayerLoc())).append("\n");
          } else {
            out.append("invalid input, please input again").append("\n");
          }

           */
        }
        b = false;
      }
      if (manager.judgeEnd() == 2) {
        out.append("you reached the end, game over").append("\n");
      } else {
        out.append("unfortunately you are eaten by the monster").append("\n");
      }
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }
}
