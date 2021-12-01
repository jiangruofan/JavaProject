package manager;

import dungeon.DungeonIWithMonster;
import player.PlayerWithArrow;
import random.RandomInterface;

import java.util.List;
import java.util.Map;

/**
 * This is a central manager to coordinate dungeon and player.
 */
public class Manager implements ManagerInterface {
  private final DungeonIWithMonster dungeon;
  private final PlayerWithArrow player;

  /**
   * construct manger in terms of row, column, interconnectivity, wrap.
   * percent, random and monsterNum.
   *
   * @param row               the row of the map
   * @param column            the column of the map
   * @param interconnectivity the number of path from every cave in the dungeon
   *                          to every other cave in the dungeon
   * @param wrap              true if the location in one side can move the
   *                          opposite side otherwise false
   * @param percent           what percent of caves have treasure
   * @param random            used to create random number
   * @param monsterNum        how many monster there are in the dungeon
   */
  public Manager(int row, int column, int interconnectivity, boolean wrap, double percent,
                 RandomInterface random, int monsterNum) {

    dungeon = new DungeonIWithMonster(row, column, interconnectivity,
            wrap, percent, random, monsterNum);
    player = new PlayerWithArrow(dungeon.getStartPoint());

    /*
    String[][] str = dungeon.showMap();
    for (int i = 0; i < str.length; i++) {
      StringBuilder str1 = new StringBuilder();
      for (int j = 0; j < str[0].length; j++) {
        str1.append(str[i][j]);
      }
      System.out.println(str1);
    }

    for (int i = 1; i <= row * column; i++) {
      System.out.println(dungeon.getDescription(i));
    }

    System.out.println("start and end point:");
    System.out.println(Arrays.toString(dungeon.getStartEnd()));

     */
  }

  @Override
  public int judgeEnd() {
    if (dungeon.judgeWhetherEnd(player.getLoc())) {
      return 2;
    } else if (dungeon.judgeWhetherDeath(player.getLoc())) {
      return 3;
    } else {
      return 1;
    }
  }

  @Override
  public int[] getPlayerLoc() {
    return player.getLocInPoint();
  }

  @Override
  public Map<String, Integer> getPlayerDescription() {
    return player.getDescription1();
  }

  @Override
  public List<String> getLocDescription() {
    return dungeon.getDescription(player.getLoc());
  }

  @Override
  public boolean whetherHaveTreasure() {
    return dungeon.getValidTreasure(player.getLoc()).size() != 0;
  }

  @Override
  public int pickUpTreasure(String str) {
    if (str == null || str.length() <= 0) {
      throw new IllegalArgumentException("invalid input");
    }
    return player.pickUpTreasure(str, dungeon.getValidTreasure(player.getLoc()));
  }

  @Override
  public void updateTreasure(String str) {
    if (str == null || str.length() <= 0) {
      throw new IllegalArgumentException("invalid input");
    }
    dungeon.updateTreasure(player.getLoc(), str);
  }

  @Override
  public boolean move(String str) {
    if (str == null || str.length() <= 0) {
      throw new IllegalArgumentException("invalid input");
    }
    return player.move(str, dungeon.getValidMove(player.getLoc()));
  }

  @Override
  public boolean pickUpArrow(String str) {
    if (str == null || str.length() <= 0) {
      throw new IllegalArgumentException("invalid input");
    }
    if (str.equalsIgnoreCase("yes")) {
      player.pickUpArrow();
      dungeon.updateArrow(player.getLoc());
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean judgeWhetherHaveArrow() {
    return dungeon.getArrow(player.getLoc());
  }

  @Override
  public boolean judgeWhetherHaveArrow1() {
    return player.getArrowNum() != 0;
  }

  @Override
  public int shot(String str) {
    if (str == null || str.length() <= 0) {
      throw new IllegalArgumentException("invalid input");
    }
    if (str.equalsIgnoreCase("no")) {
      return 4;
    }
    String[] res = str.split(",");
    int direction;
    int distance;
    if (res[0].equalsIgnoreCase("north")) {
      direction = 1;
    } else if (res[0].equalsIgnoreCase("east")) {
      direction = 2;
    } else if (res[0].equalsIgnoreCase("south")) {
      direction = 3;
    } else if (res[0].equalsIgnoreCase("west")) {
      direction = 4;
    } else {
      return 3;
    }
    try {
      distance = Integer.parseInt(res[1]);
    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
      return 3;
    }

    player.updateArrow();

    if (dungeon.shot(player.getLoc(), direction, distance)) {
      /*
      String[][] str2 = dungeon.showMap();
      for (int i = 0; i < str2.length; i++) {
        StringBuilder str1 = new StringBuilder();
        for (int j = 0; j < str2[0].length; j++) {
          str1.append(str2[i][j]);
        }
        System.out.println(str1);
      }

       */
      return 1;
    } else {
      /*
      String[][] str2 = dungeon.showMap();
      for (int i = 0; i < str2.length; i++) {
        StringBuilder str1 = new StringBuilder();
        for (int j = 0; j < str2[0].length; j++) {
          str1.append(str2[i][j]);
        }
        System.out.println(str1);
      }

       */
      return 2;
    }
  }

  @Override
  public int detectMonster() {
    return dungeon.detectMonster(player.getLoc());
  }

}
