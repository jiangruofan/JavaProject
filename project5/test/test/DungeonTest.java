package test;

import dungeon.Dungeon;
import org.junit.Test;
import random.PredictableRandom;
import random.RandomInterface;
import random.RealRandom;
import treasure.Treasure;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;

/**
 * This is a test for dungeon.
 */
public class DungeonTest {
  RandomInterface random;
  Dungeon dungeon;

  /**
   * Dungeon for 4✖️4 grid with interconnectivity 0 and no wrapping.
   */
  @org.junit.Test
  public void dungeon() {
    int[] order = {
        1, 2, 3, 4, 4, 5, 6, 7, 7, 8, 9, 10, 10, 10, 10,
        1, 1, 1, 1, 1, 1, 1, 1, 1,
        1, 5, 1, 1, 1, 1,
        0, 0, 1, 2,
    };
    random = new PredictableRandom(order);
    int row = 4;
    int column = 4;
    Dungeon dungeon = new Dungeon(row, column, 0, false, 50, random);
    String[][] map = new String[2 * row + 1][2 * column + 1];
    for (int i = 0; i < 2 * row + 1; i++) {
      for (int j = 0; j < 2 * column + 1; j++) {
        map[i][j] = "   ";
      }
    }
    for (int i = 1; i < 8; i = i + 2) {
      map[i][1] = " 0 ";
      map[i][2] = " — ";
      map[i][3] = " 0 ";
      map[i][4] = " — ";
      map[i][5] = " 0 ";
      map[i][6] = " — ";
      map[i][7] = " 0 ";
    }
    for (int i = 2; i < 7; i = i + 2) {
      map[i][7] = " | ";
    }
    assertArrayEquals(map, dungeon.showMap());

    for (int i = 1; i <= 4; i++) {
      assertFalse(dungeon.getDescription(i).contains("north"));
    }

    for (int i = 13; i <= 16; i++) {
      assertFalse(dungeon.getDescription(i).contains("south"));
    }

    for (int i = 1; i < 14; i += 4) {
      assertFalse(dungeon.getDescription(i).contains("west"));
    }

    for (int i = 4; i < 17; i += 4) {
      assertFalse(dungeon.getDescription(i).contains("east"));
    }


    ArrayList<String> arrayList = new ArrayList<>();
    arrayList.add("east");
    arrayList.add("DIAMONDS");
    arrayList.add("RUBIES");
    assertEquals(arrayList, dungeon.getDescription(1));

    arrayList.clear();
    arrayList.add("east");
    arrayList.add("DIAMONDS");
    assertEquals(arrayList, dungeon.getDescription(5));

    int count = 0;
    for (int i = 1; i < 17; i++) {
      if (dungeon.getDescription(i).contains("DIAMONDS") ||
              dungeon.getDescription(i).contains("RUBIES") ||
              dungeon.getDescription(i).contains("SAPPHIRES")) {
        count += 1;
      }
    }
    assertEquals(3, count);

    int[] startEnd = {1, 13};
    assertArrayEquals(startEnd, dungeon.getStartEnd());

    assertTrue(dungeon.judgeWhetherEnd(13));
    assertFalse(dungeon.judgeWhetherEnd(4));

    ArrayList<String> arrayList1 = new ArrayList<>();
    arrayList1.add("EAST");
    assertEquals(arrayList1, dungeon.getValidMove(5));

    ArrayList<Treasure> arrayList2 = new ArrayList<>();
    arrayList2.add(Treasure.DIAMONDS);
    assertEquals(arrayList2, dungeon.getValidTreasure(5));

    dungeon.updateTreasure(5, "diamonds");
    assertEquals(0, dungeon.getValidTreasure(5).size());
  }

  /**
   * Dungeon for 4✖️4 grid with interconnectivity 0 and wrapping.
   */
  @org.junit.Test
  public void dungeon1() {
    int[] order = {
        1, 2, 3, 4, 4, 5, 6, 7, 7, 8, 9, 10, 10, 10, 10,
        1, 2, 3, 4, 4, 5, 5, 5, 6, 6, 6,
        1, 2, 2, 2, 2, 1,
        1, 5, 1, 1, 1, 1,
        0, 1, 2, 3, 4, 5,
    };
    random = new PredictableRandom(order);
    int row = 4;
    int column = 4;
    Dungeon dungeon = new Dungeon(row, column, 0, true, 100, random);

    String[][] map = new String[2 * row + 1][2 * column + 1];
    for (int i = 0; i < 2 * row + 1; i++) {
      for (int j = 0; j < 2 * column + 1; j++) {
        map[i][j] = "   ";
      }
    }
    map[1][1] = " 0 ";
    map[1][2] = " — ";
    map[1][3] = " 0 ";
    map[1][4] = " — ";
    map[1][5] = " 0 ";
    map[1][6] = " — ";
    map[1][7] = " 0 ";
    map[2][1] = " | ";
    map[2][7] = " | ";
    map[3][1] = " 0 ";
    map[3][3] = " 0 ";
    map[3][4] = " — ";
    map[3][5] = " 0 ";
    map[3][6] = " — ";
    map[3][7] = " 0 ";
    map[4][7] = " | ";
    map[5][1] = " 0 ";
    map[5][2] = " — ";
    map[5][3] = " 0 ";
    map[5][4] = " — ";
    map[5][5] = " 0 ";
    map[5][6] = " — ";
    map[5][7] = " 0 ";
    map[7][1] = " 0 ";
    map[7][2] = " — ";
    map[7][3] = " 0 ";
    map[7][4] = " — ";
    map[7][5] = " 0 ";
    map[7][7] = " 0 ";

    assertArrayEquals(map, dungeon.showMap());

    assertTrue(dungeon.getDescription(4).contains("north"));
    assertTrue(dungeon.getDescription(16).contains("south"));
    assertTrue(dungeon.getDescription(16).contains("east"));
    assertTrue(dungeon.getDescription(13).contains("west"));

    ArrayList<String> arrayList = new ArrayList<>();
    arrayList.add("west");
    arrayList.add("south");
    arrayList.add("north");
    arrayList.add("DIAMONDS");
    assertEquals(arrayList, dungeon.getDescription(4));

    int count = 0;
    for (int i = 1; i < 17; i++) {
      if (dungeon.getDescription(i).contains("DIAMONDS") ||
              dungeon.getDescription(i).contains("RUBIES") ||
              dungeon.getDescription(i).contains("SAPPHIRES")) {
        count += 1;
      }
    }
    assertEquals(6, count);

    int[] startEnd = {4, 9};
    assertArrayEquals(startEnd, dungeon.getStartEnd());
  }

  /**
   * Dungeon for 4✖️4 grid with interconnectivity 1 and wrapping.
   */
  @org.junit.Test
  public void dungeon2() {
    int[] order = {
        1, 2, 3, 4, 4, 5, 6, 7, 7, 8, 9, 10, 10, 10, 10,
        1, 2, 3, 4, 4, 5, 5, 5, 6, 6, 6,
        1, 2, 2, 2, 2, 1, 1,
        1, 1, 1, 1, 1, 1, 1, 1,
    };
    random = new PredictableRandom(order);
    int row = 4;
    int column = 4;
    Dungeon dungeon = new Dungeon(row, column, 1, true, 0, random);

    String[][] map = new String[2 * row + 1][2 * column + 1];
    for (int i = 0; i < 2 * row + 1; i++) {
      for (int j = 0; j < 2 * column + 1; j++) {
        map[i][j] = "   ";
      }
    }
    map[1][1] = " 0 ";
    map[1][2] = " — ";
    map[1][3] = " 0 ";
    map[1][4] = " — ";
    map[1][5] = " 0 ";
    map[1][6] = " — ";
    map[1][7] = " 0 ";
    map[2][1] = " | ";
    map[2][7] = " | ";
    map[3][1] = " 0 ";
    map[3][3] = " 0 ";
    map[3][4] = " — ";
    map[3][5] = " 0 ";
    map[3][6] = " — ";
    map[3][7] = " 0 ";
    map[4][7] = " | ";
    map[5][1] = " 0 ";
    map[5][2] = " — ";
    map[5][3] = " 0 ";
    map[5][4] = " — ";
    map[5][5] = " 0 ";
    map[5][6] = " — ";
    map[5][7] = " 0 ";
    map[7][1] = " 0 ";
    map[7][2] = " — ";
    map[7][3] = " 0 ";
    map[7][4] = " — ";
    map[7][5] = " 0 ";
    map[7][7] = " 0 ";

    assertArrayEquals(map, dungeon.showMap());

    assertTrue(dungeon.getDescription(1).contains("north"));
    assertTrue(dungeon.getDescription(13).contains("south"));

    int[] startEnd = {1, 6};
    assertArrayEquals(startEnd, dungeon.getStartEnd());
  }

  /**
   * Dungeon for 4✖️4 grid with interconnectivity 1 and no wrapping.
   */
  @org.junit.Test
  public void dungeon3() {
    int[] order = {
        1, 2, 3, 4, 4, 5, 6, 7, 7, 8, 9, 10, 10, 10, 10,
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        1, 1, 1, 1,
    };
    random = new PredictableRandom(order);
    int row = 4;
    int column = 4;
    Dungeon dungeon = new Dungeon(row, column, 1, false, 0, random);
    String[][] map = new String[2 * row + 1][2 * column + 1];
    for (int i = 0; i < 2 * row + 1; i++) {
      for (int j = 0; j < 2 * column + 1; j++) {
        map[i][j] = "   ";
      }
    }
    for (int i = 1; i < 8; i = i + 2) {
      map[i][1] = " 0 ";
      map[i][2] = " — ";
      map[i][3] = " 0 ";
      map[i][4] = " — ";
      map[i][5] = " 0 ";
      map[i][6] = " — ";
      map[i][7] = " 0 ";
    }
    for (int i = 2; i < 7; i = i + 2) {
      map[i][7] = " | ";
    }
    map[2][1] = " | ";
    assertArrayEquals(map, dungeon.showMap());

    int[] startEnd = {8, 13};
    assertArrayEquals(startEnd, dungeon.getStartEnd());
  }

  /**
   * Dungeon for 4✖️4 grid with full interconnectivity and wrapping.
   * can not create the start end point.
   */
  @org.junit.Test(expected = IllegalArgumentException.class)
  public void dungeon4() {
    int[] order = {
        1, 2, 3, 4, 5, 6, 7, 8, 8, 9, 10, 11, 11, 12, 12,
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
    };
    random = new PredictableRandom(order);
    int row = 4;
    int column = 4;
    dungeon = new Dungeon(row, column, 100, true, 0, random);
  }

  /**
   * column < 1.
   */
  @Test(expected = IllegalArgumentException.class)
  public void dungeon5() {
    random = new RealRandom();
    dungeon = new Dungeon(1, 0, 10, true, 0, random);
  }

  /**
   * row < 1.
   */
  @Test(expected = IllegalArgumentException.class)
  public void dungeon6() {
    random = new RealRandom();
    dungeon = new Dungeon(0, 1, 10, true, 0, random);
  }

  /**
   * interconnectivity < 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void dungeon7() {
    random = new RealRandom();
    dungeon = new Dungeon(10, 1, -1, true, 0, random);
  }

  /**
   * percent < 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void dungeon8() {
    random = new RealRandom();
    dungeon = new Dungeon(10, 1, 10, true, -1, random);
  }

  /**
   * percent > 100.
   */
  @Test(expected = IllegalArgumentException.class)
  public void dungeon9() {
    random = new RealRandom();
    dungeon = new Dungeon(10, 1, 10, true, 200, random);
  }

  /**
   * one column and wrapped dungeon but the interconnectivity is not zero.
   */
  @Test(expected = IllegalArgumentException.class)
  public void dungeon10() {
    random = new RealRandom();
    dungeon = new Dungeon(10, 1, 10, true, 50, random);
  }

  /**
   * one row and wrapped dungeon but the interconnectivity is not zero.
   */
  @Test(expected = IllegalArgumentException.class)
  public void dungeon11() {
    random = new RealRandom();
    dungeon = new Dungeon(1, 10, 10, true, 50, random);
  }

  /**
   * Dungeon for 2 ✖️ 2 grid.
   * can not create start and end point.
   */
  @Test(expected = IllegalArgumentException.class)
  public void dungeon12() {
    random = new RealRandom();
    dungeon = new Dungeon(2, 2, 0, false, 20, random);
  }

  /**
   * Dungeon for 1✖️6 grid with interconnectivity 0 and no wrapping.
   */
  @Test
  public void dungeon13() {
    random = new RealRandom();
    int row = 1;
    int column = 6;
    dungeon = new Dungeon(row, column, 0, false, 0, random);
    String[][] map = new String[2 * row + 1][2 * column + 1];
    for (int i = 0; i < 2 * row + 1; i++) {
      for (int j = 0; j < 2 * column + 1; j++) {
        map[i][j] = "   ";
      }
    }
    map[1][1] = " 0 ";
    map[1][2] = " — ";
    map[1][3] = " 0 ";
    map[1][4] = " — ";
    map[1][5] = " 0 ";
    map[1][6] = " — ";
    map[1][7] = " 0 ";
    map[1][8] = " — ";
    map[1][9] = " 0 ";
    map[1][10] = " — ";
    map[1][11] = " 0 ";
    assertArrayEquals(map, dungeon.showMap());
  }

  /**
   * Dungeon for 1✖️6 grid with interconnectivity 0 and wrapping.
   */
  @Test
  public void dungeon14() {
    int[] order = {
        1, 1, 1, 1, 1, 1,
        1, 1,
    };
    random = new PredictableRandom(order);
    int row = 1;
    int column = 6;
    dungeon = new Dungeon(row, column, 0, true, 0, random);
    String[][] map = new String[2 * row + 1][2 * column + 1];
    for (int i = 0; i < 2 * row + 1; i++) {
      for (int j = 0; j < 2 * column + 1; j++) {
        map[i][j] = "   ";
      }
    }
    map[1][1] = " 0 ";
    map[1][2] = " — ";
    map[1][3] = " 0 ";
    map[1][4] = " — ";
    map[1][5] = " 0 ";
    map[1][6] = " — ";
    map[1][7] = " 0 ";
    map[1][8] = " — ";
    map[1][9] = " 0 ";
    map[1][11] = " 0 ";
    assertArrayEquals(map, dungeon.showMap());
    assertArrayEquals(new int[]{5, 6}, dungeon.getStartEnd());
  }

  /**
   * Dungeon for 1✖️6 grid with interconnectivity 1 and no wrapping.
   */
  @Test
  public void dungeon15() {
    random = new RealRandom();
    int row = 1;
    int column = 6;
    dungeon = new Dungeon(row, column, 1, false, 0, random);
    String[][] map = new String[2 * row + 1][2 * column + 1];
    for (int i = 0; i < 2 * row + 1; i++) {
      for (int j = 0; j < 2 * column + 1; j++) {
        map[i][j] = "   ";
      }
    }
    map[1][1] = " 0 ";
    map[1][2] = " — ";
    map[1][3] = " 0 ";
    map[1][4] = " — ";
    map[1][5] = " 0 ";
    map[1][6] = " — ";
    map[1][7] = " 0 ";
    map[1][8] = " — ";
    map[1][9] = " 0 ";
    map[1][10] = " — ";
    map[1][11] = " 0 ";
    assertArrayEquals(map, dungeon.showMap());
  }

  /**
   * Dungeon for 6✖️1 grid with interconnectivity 0 and no wrapping.
   */
  @Test
  public void dungeon16() {
    random = new RealRandom();
    int row = 6;
    int column = 1;
    dungeon = new Dungeon(row, column, 0, false, 0, random);
    String[][] map = new String[2 * row + 1][2 * column + 1];
    for (int i = 0; i < 2 * row + 1; i++) {
      for (int j = 0; j < 2 * column + 1; j++) {
        map[i][j] = "   ";
      }
    }
    map[1][1] = " 0 ";
    map[2][1] = " | ";
    map[3][1] = " 0 ";
    map[4][1] = " | ";
    map[5][1] = " 0 ";
    map[6][1] = " | ";
    map[7][1] = " 0 ";
    map[8][1] = " | ";
    map[9][1] = " 0 ";
    map[10][1] = " | ";
    map[11][1] = " 0 ";
    assertArrayEquals(map, dungeon.showMap());
  }

  /**
   * Dungeon for 6✖️1 grid with interconnectivity 1 and no wrapping.
   */
  @Test
  public void dungeon17() {
    random = new RealRandom();
    int row = 6;
    int column = 1;
    dungeon = new Dungeon(row, column, 1, false, 0, random);
    String[][] map = new String[2 * row + 1][2 * column + 1];
    for (int i = 0; i < 2 * row + 1; i++) {
      for (int j = 0; j < 2 * column + 1; j++) {
        map[i][j] = "   ";
      }
    }
    map[1][1] = " 0 ";
    map[2][1] = " | ";
    map[3][1] = " 0 ";
    map[4][1] = " | ";
    map[5][1] = " 0 ";
    map[6][1] = " | ";
    map[7][1] = " 0 ";
    map[8][1] = " | ";
    map[9][1] = " 0 ";
    map[10][1] = " | ";
    map[11][1] = " 0 ";
    assertArrayEquals(map, dungeon.showMap());
  }

  /**
   * Dungeon for 6✖️1 grid with interconnectivity 0 and wrapping.
   */
  @Test
  public void dungeon18() {
    int[] order = {
        1, 1, 1, 1, 1, 1,
        1, 1,
    };
    random = new PredictableRandom(order);
    int row = 6;
    int column = 1;
    dungeon = new Dungeon(row, column, 0, true, 0, random);
    String[][] map = new String[2 * row + 1][2 * column + 1];
    for (int i = 0; i < 2 * row + 1; i++) {
      for (int j = 0; j < 2 * column + 1; j++) {
        map[i][j] = "   ";
      }
    }
    map[1][1] = " 0 ";
    map[2][1] = " | ";
    map[3][1] = " 0 ";
    map[4][1] = " | ";
    map[5][1] = " 0 ";
    map[6][1] = " | ";
    map[7][1] = " 0 ";
    map[8][1] = " | ";
    map[9][1] = " 0 ";
    map[11][1] = " 0 ";
    assertArrayEquals(map, dungeon.showMap());
    assertArrayEquals(new int[]{5, 6}, dungeon.getStartEnd());
  }


}