package player;

import treasure.Treasure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents the player.
 * The player can move in the dungeon and pick up treasure.
 */
public class Player implements PlayerInterface {
  private int loc;
  private final int col;
  private final int row;
  private final Map<Treasure, Integer> treasures;

  /**
   * construct the player in terms of location.
   *
   * @param location the location of the player
   */
  public Player(int[] location) {
    if (location == null) {
      throw new IllegalArgumentException("input could be null.");
    }
    this.loc = location[0];
    this.row = location[1];
    this.col = location[2];
    this.treasures = new HashMap<>();
  }

  @Override
  public boolean move(String str, List<String> list) {
    if (str == null || list == null) {
      throw new IllegalArgumentException("invalid input");
    }
    str = str.toUpperCase();
    if (list.contains(str)) {
      if (str.equals("NORTH")) {
        if (loc <= col) {
          loc = loc + (row - 1) * col;
        } else {
          loc -= col;
        }
      } else if (str.equals("SOUTH")) {
        if (loc > (row - 1) * col) {
          loc = loc - (row - 1) * col;
        } else {
          loc += col;
        }
      } else if (str.equals("WEST")) {
        List<Integer> list1 = new ArrayList<>();
        for (int i = 1; i < row * col; i = i + col) {
          list1.add(i);
        }
        if (list1.contains(loc)) {
          loc += col - 1;
        } else {
          loc -= 1;
        }
      } else {
        List<Integer> list1 = new ArrayList<>();
        for (int i = col; i <= row * col; i = i + col) {
          list1.add(i);
        }
        if (list1.contains(loc)) {
          loc = loc - col + 1;
        } else {
          loc += 1;
        }
      }
      return true;
    } else {
      return false;
    }
  }

  @Override
  public int getLoc() {
    return loc;
  }

  @Override
  public int[] getLocInPoint() {
    int[] point = new int[2];
    point[0] = (loc - 1) / col;
    point[1] = loc - point[0] * col - 1;
    return point;
  }

  @Override
  public int pickUpTreasure(String str, List<Treasure> treasureList) {
    if (str == null || treasureList == null) {
      throw new IllegalArgumentException("invalid input.");
    }
    List<Treasure> treasure = new ArrayList<>();
    List<Treasure> list;
    for (String str1 : str.toUpperCase().split(",")) {
      if (str1.equals("DIAMONDS")) {
        treasure.add(Treasure.DIAMONDS);
      } else if (str1.equals("RUBIES")) {
        treasure.add(Treasure.RUBIES);
      } else if (str1.equals("SAPPHIRES")) {
        treasure.add(Treasure.SAPPHIRES);
      } else if (str1.equals("NO")) {
        return 2;
      } else {
        return 1;
      }
    }
    if (!treasureList.containsAll(treasure)) {
      return 1;
    }
    for (Treasure treasure1 : treasure) {
      list = new ArrayList<>(treasures.keySet());
      if (list.contains(treasure1)) {
        treasures.replace(treasure1, treasures.get(treasure1) + 1);
      } else {
        treasures.put(treasure1, 1);
      }
    }
    return 3;
  }

  @Override
  public Map<Treasure, Integer> getDescription() {
    return treasures;
  }

}
