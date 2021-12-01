package dungeon;

import random.RandomInterface;
import treasure.Treasure;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Map;

/**
 * This class represents the dungeon. It creates the map for player
 * to move and create treasure in the cave for player to pick up.
 */
public class Dungeon implements DungeonInterface {
  private final int row;
  private final int column;
  private final List<Location> mapInList;
  private final int interconnectivity;
  private final boolean wrap;
  private final double percent;
  private final RandomInterface random;
  private final int[] startEnd;

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
   */
  public Dungeon(int row, int column, int interconnectivity, boolean wrap,
                 double percent, RandomInterface random) {
    if (row < 1 || column < 1 || interconnectivity < 0 || percent < 0 || percent > 100) {
      throw new IllegalArgumentException("invalid input");
    }
    if (row == 1 || column == 1) {
      if (wrap && interconnectivity != 0) {
        throw new IllegalArgumentException("When there is only one row or column " +
                "and the dungeon is wrapped, the interconnectivity" +
                " can be only 0 otherwise there will be no cave");
      }
    }
    this.row = row;
    this.column = column;
    this.interconnectivity = interconnectivity;
    this.wrap = wrap;
    this.percent = percent;
    this.random = random;
    this.mapInList = dungeonHelper();
    this.startEnd = startEnd();
    setTreasure();
  }

  /**
   * Input a list of value to create a List.
   *
   * @param intArrays a list of value
   * @return a List of value
   */
  private List<Integer> arrayListHelper(int... intArrays) {
    List<Integer> arrayList = new ArrayList<>();
    for (int intArray : intArrays) {
      arrayList.add(intArray);
    }
    return arrayList;
  }

  /**
   * create treasure for caves.
   */
  private void setTreasure() {
    List<Location> arrayList = new ArrayList<>();
    for (Location location : mapInList) {
      if (location.caveOrTunnel()) {
        arrayList.add(location);
      }
    }
    int i = (int) Math.ceil(arrayList.size() * percent / 100);
    int random;
    while (i != 0) {
      random = this.random.getRandom(0, arrayList.size() - 1);
      arrayList.get(random).setTreasures();
      if (arrayList.get(random).getTreasures().size() == 1) {
        i--;
      }
    }
  }

  /**
   * set the start and end point for the player.
   *
   * @return an array of two numbers which are start point and end point
   */
  private int[] startEnd() {
    int[] res = new int[2];
    int i1;
    List<Integer> arrayList = new ArrayList<>();
    List<Integer> arrayList1 = new ArrayList<>();
    for (Location location : mapInList) {
      if (location.caveOrTunnel()) {
        arrayList.add(location.getNum());
      }
    }
    int size = arrayList.size();
    for (int i = 0; i < size; i++) {
      i1 = random.getRandom(1, arrayList.size());
      arrayList1.add(arrayList.get(i1 - 1));
      arrayList.remove(i1 - 1);
    }
    for (int i = 0; i < arrayList1.size(); i++) {
      for (int j = i; j < arrayList1.size(); j++) {
        res[0] = arrayList1.get(i);
        res[1] = arrayList1.get(j);
        if (bfs(res[0], res[1]) >= 5) {
          return res;
        }
      }
    }
    throw new IllegalArgumentException("the map is too small to create the start and end point");
  }

  /**
   * get the length of the shortest path between two locations.
   *
   * @param num    one location
   * @param target the other location
   * @return the shortest length between there two locations
   */
  private int bfs(int num, int target) {
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(num);
    int depth = -1;
    List<Integer> visited = new ArrayList<>();
    while (!queue.isEmpty()) {
      int size = queue.size();
      depth++;
      int cur;
      for (int i = 0; i < size; i++) {
        cur = queue.poll();
        visited.add(cur);
        if (cur == target) {
          return depth;
        }
        for (Integer integer : mapInList.get(cur - 1).getNeighbors()) {
          if (visited.contains(integer)) {
            continue;
          }
          queue.offer(integer);
        }
      }
    }
    return depth;
  }

  /**
   * This is a helper method to create the map.
   *
   * @return a List of locations
   */
  private List<Location> dungeonHelper() {
    List<Location> nodes = new ArrayList<>();
    int total = row * column;
    Map<Integer, List<Integer>> hashMap = new HashMap<>();

    for (int i1 = 1; i1 <= total; i1++) {
      nodes.add(new Location(i1));
    }

    for (int j = 1; j <= total - column; j = j + column) {
      for (int k = j; k < j + column - 1; k++) {
        hashMap.put(k, arrayListHelper(k + 1, k + column));
      }
      hashMap.put(j + column - 1, arrayListHelper(j + 2 * column - 1));
    }

    for (int i = total - column + 1; i < total; i++) {
      hashMap.put(i, arrayListHelper(i + 1));
    }

    if (wrap) {
      if (row != 1) {
        for (int n = 1; n <= column; n++) {
          hashMap.get(n).add(n + (row - 1) * column);
        }
      }
      if (column != 1) {
        for (int n = 1; n <= total - column + 1; n = n + column) {
          hashMap.get(n).add(n + column - 1);
        }
      }
    }

    List<List<Integer>> arrayLists = new ArrayList<>();
    for (int k = 1; k <= total; k++) {
      arrayLists.add(arrayListHelper(k));
    }

    Map<Integer, List<Integer>> hashMap1 = new HashMap<>();

    int size;
    int key;
    int value;
    List<Integer> arrayList;
    while (hashMap.size() != 0) {
      size = hashMap.size();
      arrayList = new ArrayList<>(hashMap.keySet());
      key = arrayList.get(random.getRandom(1, size) - 1);
      value = hashMap.get(key).get(0);
      if (hashMap.get(key).size() == 1) {
        hashMap.remove(key);
      } else {
        hashMap.get(key).remove(0);
      }
      for (List<Integer> arrayList_ : arrayLists) {
        if (arrayList_.contains(key) && arrayList_.contains(value)) {
          if (hashMap1.containsKey(key)) {
            hashMap1.get(key).add(value);
          } else {
            hashMap1.put(key, arrayListHelper(value));
          }
          break;
        } else if (arrayList_.contains(key)) {
          for (List<Integer> arrayList__ : arrayLists) {
            if (arrayList__.contains(value)) {
              arrayList_.addAll(arrayList__);
              arrayLists.remove(arrayList__);
              break;
            }
          }
          nodes.get(key - 1).setNeighbors(value);
          nodes.get(value - 1).setNeighbors(key);
          break;
        }
      }
    }

    for (int i_ = 0; i_ < interconnectivity; i_++) {
      size = hashMap1.size();
      if (size == 0) {
        break;
      }
      arrayList = new ArrayList<>(hashMap1.keySet());
      key = arrayList.get(random.getRandom(1, size) - 1);
      value = hashMap1.get(key).get(0);
      if (hashMap1.get(key).size() == 1) {
        hashMap1.remove(key);
      } else {
        hashMap1.get(key).remove(0);
      }
      nodes.get(key - 1).setNeighbors(value);
      nodes.get(value - 1).setNeighbors(key);
    }

    return nodes;
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
    List<Integer> arrayList;
    for (int i = 1; i < 2 * row; i = i + 2) {
      for (int j = 1; j < 2 * column; j = j + 2) {
        map[i][j] = " 0 ";
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

  @Override
  public int[] getStartPoint() {
    return new int[]{startEnd[0], row, column};
  }

  @Override
  public Boolean judgeWhetherEnd(int i) {
    return i == startEnd[1];
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
    return res;
  }

  @Override
  public void updateTreasure(int num, String treasure) {
    if (treasure == null) {
      throw new IllegalArgumentException("invalid input");
    }
    for (String str : treasure.toUpperCase().split(",")) {
      if (str.equals("DIAMONDS")) {
        mapInList.get(num - 1).updateTreasure(Treasure.DIAMONDS);
      } else if (str.equals("RUBIES")) {
        mapInList.get(num - 1).updateTreasure(Treasure.RUBIES);
      } else {
        mapInList.get(num - 1).updateTreasure(Treasure.SAPPHIRES);
      }
    }
  }

  @Override
  public int[] getStartEnd() {
    return new int[]{startEnd[0], startEnd[1]};
  }

  @Override
  public List<String> getValidMove(int num) {
    List<String> res = new ArrayList<>();
    for (Integer i : mapInList.get(num - 1).getNeighbors()) {
      if (i == num + column || i == num - (row - 1) * column) {
        res.add("SOUTH");
      } else if (i == num - column || i == num + (row - 1) * column) {
        res.add("NORTH");
      } else if (i == num + 1 || i == num - column + 1) {
        res.add("EAST");
      } else if (i == num - 1 || i == num + column - 1) {
        res.add("WEST");
      } else {
        throw new IllegalArgumentException("wrong");
      }
    }
    return res;
  }

  @Override
  public List<Treasure> getValidTreasure(int num) {
    return mapInList.get(num - 1).getTreasures();
  }

}
