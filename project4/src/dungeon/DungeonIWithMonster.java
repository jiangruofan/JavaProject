package dungeon;

import random.RandomInterface;
import treasure.Treasure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * represent a dungeon having monster and arrow in it.
 */
public class DungeonIWithMonster extends Dungeon {
  private final int monsterNum;

  /**
   * Construct the dungeon in terms of row, column, interconnectivity, wrap, percent and random.
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
  public DungeonIWithMonster(int row, int column, int interconnectivity, boolean wrap
          , double percent, RandomInterface random, int monsterNum) {
    super(row, column, interconnectivity, wrap, percent, random);
    if (monsterNum < 1) {
      throw new IllegalArgumentException("invalid input");
    }
    this.monsterNum = monsterNum;
    setMonster();
    setArrow();
  }

  /**
   * set the monster for the dungeon.
   */
  private void setMonster() {
    mapInList.get(startEnd[1] - 1).setMonsterLife();
    List<Location> arrayList = new ArrayList<>();
    for (Location location : mapInList) {
      if (location.caveOrTunnel() && location.getNum() != startEnd[1]
              && location.getNum() != startEnd[0]) {
        arrayList.add(location);
      }
    }
    int num = monsterNum - 1;
    int random;

    if (num > arrayList.size()) {
      throw new IllegalArgumentException("the monster number is too large");
    }

    while (num != 0) {
      random = this.random.getRandom(0, arrayList.size() - 1);
      if (arrayList.get(random).getMonsterLife() != 2) {
        arrayList.get(random).setMonsterLife();
        num -= 1;
      }
    }
  }

  /**
   * set arrows for the dungeon.
   */
  private void setArrow() {
    int i = (int) Math.ceil(mapInList.size() * percent / 100);
    int random;
    while (i != 0) {
      random = this.random.getRandom(0, mapInList.size() - 1);
      if (!mapInList.get(random).getArrow()) {
        mapInList.get(random).increaseArrow();
        i--;
      }
    }
  }

  /**
   * if the player picks up an arrow, remove the arrow in the location.
   *
   * @param num the number of the location
   */
  public void updateArrow(int num) {
    mapInList.get(num - 1).decreaseArrow();
  }

  /**
   * judge whether there is an arrow in the location.
   *
   * @param num the number of the location
   * @return true if there is an arrow otherwise false
   */
  public boolean getArrow(int num) {
    return mapInList.get(num - 1).getArrow();
  }

  /**
   * judge whether the arrow can shoot a monster.
   *
   * @param num       the number of the location
   * @param direction the direction of the arrow
   * @param distance  the distance of an arrow that can fly
   * @return true if the arrow shoot a monster otherwise false
   */
  public boolean shot(int num, int direction, int distance) {
    int pre;
    while (distance != 0) {
      if (direction == 1) {
        if (mapInList.get(num - 1).getNeighbors().contains(num - column)
                || mapInList.get(num - 1).getNeighbors().contains(num + (row - 1) * column)) {
          pre = num;
          if (mapInList.get(num - 1).getNeighbors().contains(num - column)) {
            num = num - column;
          } else {
            num = num + (row - 1) * column;
          }
          while (!mapInList.get(num - 1).caveOrTunnel()) {
            for (Integer integer : mapInList.get(num - 1).getNeighbors()) {
              if (integer != pre) {
                pre = num;
                num = integer;
                break;
              }
            }
          }
          distance -= 1;
        } else {
          break;
        }
      } else if (direction == 2) {
        if (mapInList.get(num - 1).getNeighbors().contains(num + 1)
                || mapInList.get(num - 1).getNeighbors().contains(num - column + 1)) {
          pre = num;
          if (mapInList.get(num - 1).getNeighbors().contains(num + 1)) {
            num = num + 1;
          } else {
            num = num - column + 1;
          }
          while (!mapInList.get(num - 1).caveOrTunnel()) {
            for (Integer integer : mapInList.get(num - 1).getNeighbors()) {
              if (integer != pre) {
                pre = num;
                num = integer;
                break;
              }
            }
          }
          distance -= 1;
        } else {
          break;
        }
      } else if (direction == 3) {
        if (mapInList.get(num - 1).getNeighbors().contains(num + column)
                || mapInList.get(num - 1).getNeighbors().contains(num - (row - 1) * column)) {
          pre = num;
          if (mapInList.get(num - 1).getNeighbors().contains(num + column)) {
            num = num + column;
          } else {
            num = num - (row - 1) * column;
          }
          while (!mapInList.get(num - 1).caveOrTunnel()) {
            for (Integer integer : mapInList.get(num - 1).getNeighbors()) {
              if (integer != pre) {
                pre = num;
                num = integer;
                break;
              }
            }
          }
          distance -= 1;
        } else {
          break;
        }
      } else {
        if (mapInList.get(num - 1).getNeighbors().contains(num - 1)
                || mapInList.get(num - 1).getNeighbors().contains(num + column - 1)) {
          pre = num;
          if (mapInList.get(num - 1).getNeighbors().contains(num - 1)) {
            num = num - 1;
          } else {
            num = num + column - 1;
          }
          while (!mapInList.get(num - 1).caveOrTunnel()) {
            for (Integer integer : mapInList.get(num - 1).getNeighbors()) {
              if (integer != pre) {
                pre = num;
                num = integer;
                break;
              }
            }
          }
          distance -= 1;
        } else {
          break;
        }
      }
    }
    if (distance == 0) {
      if (mapInList.get(num - 1).getMonsterLife() == 0) {
        return false;
      } else {
        mapInList.get(num - 1).updateMonsterLife();
        return true;
      }
    } else {
      return false;
    }
  }

  /**
   * detect whether there are monsters around.
   *
   * @param num the number of the location
   * @return 0 if there is no monster around, 1 if there is a less pungent smell otherwise 2
   */
  public int detectMonster(int num) {
    Queue<Integer> queue = new LinkedList<>();
    for (Integer integer : mapInList.get(num - 1).getNeighbors()) {
      queue.offer(integer);
    }

    int cur;
    int size = queue.size();
    for (int i = 0; i < size; i++) {
      cur = queue.poll();
      if (mapInList.get(cur - 1).getMonsterLife() != 0) {
        return 2;
      }
      for (Integer integer : mapInList.get(cur - 1).getNeighbors()) {
        queue.offer(integer);
      }
    }

    int num1 = 0;
    size = queue.size();
    for (int i = 0; i < size; i++) {
      cur = queue.poll();
      if (mapInList.get(cur - 1).getMonsterLife() != 0) {
        num1 += 1;
      }
    }

    if (num1 == 1) {
      return 1;
    } else if (num1 == 0) {
      return 0;
    } else {
      return 2;
    }
  }

  /**
   * judge whether a player is eaten by the monster.
   *
   * @param num the number of the location
   * @return true if the player is dead otherwise false
   */
  public boolean judgeWhetherDeath(int num) {
    int life = mapInList.get(num - 1).getMonsterLife();
    if (life == 0) {
      return false;
    } else if (life == 1) {
      int ran = random.getRandom(0, 1);
      return ran == 1;
    } else {
      return true;
    }
  }

  @Override
  public List<String> getDescription(int num) {
    List<String> res = new ArrayList<>();
    for (Integer i : mapInList.get(num - 1).getNeighbors()) {
      if (i == num + column || i == num - (row - 1) * column) {
        res.add("south");
      } else if (i == num - column || i == num + (row - 1) * column) {
        res.add("north");
      } else if (i == num + 1 || i == num - column + 1) {
        res.add("east");
      } else if (i == num - 1 || i == num + column - 1) {
        res.add("west");
      } else {
        throw new IllegalArgumentException("wrong");
      }
    }
    for (Treasure treasure : mapInList.get(num - 1).getTreasures()) {
      res.add(treasure.name());
    }
    if (mapInList.get(num - 1).getArrow()) {
      res.add("1 arrow");
    }
    return res;
  }

  @Override
  public String[][] showMap() {
    String[][] map = new String[2 * row + 1][2 * column + 1];
    for (int i = 0; i < 2 * row + 1; i++) {
      for (int j = 0; j < 2 * column + 1; j++) {
        map[i][j] = "   ";
      }
    }
    int count = 0;
    int count1 = 0;
    List<Integer> arrayList;
    for (int i = 1; i < 2 * row; i = i + 2) {
      for (int j = 1; j < 2 * column; j = j + 2) {
        map[i][j] = String.format(" %d ", mapInList.get(count1++).getMonsterLife());
        arrayList = mapInList.get(count++).getNeighbors();
        for (Integer integer : arrayList) {
          if (integer == count + column) {
            map[i + 1][j] = " | ";
          } else if (integer == count + 1) {
            map[i][j + 1] = " — ";
          }
        }
      }
    }
    return map;
  }

}
