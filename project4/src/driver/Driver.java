package driver;

import controller.DungeonControllerImpl;
import manager.Manager;
import random.RandomInterface;
import random.RealRandom;

import java.io.InputStreamReader;
import java.util.Objects;

/**
 * This is a driver class for manager.
 */
public class Driver {
  /**
   * This is a main method.
   *
   * @param args parameters used to initialize the manager
   */
  public static void main(String[] args) {
    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;

    RandomInterface random = new RealRandom();
    int row = Integer.parseInt(args[0]);
    int column = Integer.parseInt(args[1]);
    int interconnectivity = Integer.parseInt(args[2]);
    boolean wrap = Objects.equals(args[3], "true");
    int percent = Integer.parseInt(args[4]);
    int monsterNum = Integer.parseInt(args[5]);
    Manager manager = new Manager(row, column, interconnectivity,
            wrap, percent, random, monsterNum);
    new DungeonControllerImpl(input, output).playGame(manager);
  }
}
