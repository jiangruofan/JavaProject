package test;

import dungeon.DungeonIWithMonster;
import org.junit.Test;
import player.PlayerWithArrow;
import random.PredictableRandom;
import random.RandomInterface;
import random.RealRandom;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertArrayEquals;

/**
 * represent a test for public class DungeonIWithMonster.
 */
public class DungeonIWithMonsterTest {
  private PlayerWithArrow player;

  /**
   * test the dungeon and player.
   */
  @Test
  public void test1() {
    int[] order = {
        1, 2, 3, 4, 4, 5, 6, 7, 7, 8, 9, 10, 10, 10, 10,
        1, 1, 1, 1, 1, 1, 1, 1, 1,
        1, 5, 1, 1, 1, 1,
        0, 0, 1, 2,
        1, 3,
        0, 1, 2, 3, 4, 5, 6, 7,
        0, 1
    };
    RandomInterface random = new PredictableRandom(order);
    int row = 4;
    int column = 4;
    int monsterNum = 3;
    DungeonIWithMonster dungeonIWithMonster = new DungeonIWithMonster(row, column,
            0, false, 50, random, monsterNum);
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
    map[5][7] = " 2 ";
    map[3][7] = " 2 ";
    map[7][1] = " 2 ";
    assertArrayEquals(map, dungeonIWithMonster.showMap());

    int count = 0;
    for (int i = 1; i < 17; i++) {
      if (dungeonIWithMonster.getDescription(i).contains("1 arrow")) {
        count += 1;
      }
    }
    assertEquals(8, count);

    assertTrue(dungeonIWithMonster.judgeWhetherDeath(8));
    assertFalse(dungeonIWithMonster.judgeWhetherDeath(7));

    assertTrue(dungeonIWithMonster.judgeWhetherEnd(13));

    assertEquals(2, dungeonIWithMonster.detectMonster(4));
    assertEquals(1, dungeonIWithMonster.detectMonster(3));
    assertEquals(0, dungeonIWithMonster.detectMonster(1));

    ArrayList<String> arrayList = new ArrayList<>();
    arrayList.add("east");
    arrayList.add("DIAMONDS");
    arrayList.add("RUBIES");
    arrayList.add("1 arrow");
    assertEquals(arrayList, dungeonIWithMonster.getDescription(1));

    // shoot to south
    assertTrue(dungeonIWithMonster.shot(4, 3, 1));
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
    map[5][7] = " 2 ";
    map[3][7] = " 1 ";
    map[7][1] = " 2 ";
    assertArrayEquals(map, dungeonIWithMonster.showMap());

    //shot to north
    assertTrue(dungeonIWithMonster.shot(16, 1, 1));
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
    map[5][7] = " 1 ";
    map[3][7] = " 1 ";
    map[7][1] = " 2 ";
    assertArrayEquals(map, dungeonIWithMonster.showMap());

    //shot to east
    assertTrue(dungeonIWithMonster.shot(13, 2, 1));
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
    map[5][7] = " 0 ";
    map[3][7] = " 1 ";
    map[7][1] = " 2 ";
    assertArrayEquals(map, dungeonIWithMonster.showMap());

    //shot to west
    assertTrue(dungeonIWithMonster.shot(16, 4, 1));
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
    map[5][7] = " 0 ";
    map[3][7] = " 1 ";
    map[7][1] = " 1 ";
    assertArrayEquals(map, dungeonIWithMonster.showMap());

    //not shoot successfully
    assertFalse(dungeonIWithMonster.shot(1, 4, 1));
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
    map[5][7] = " 0 ";
    map[3][7] = " 1 ";
    map[7][1] = " 1 ";
    assertArrayEquals(map, dungeonIWithMonster.showMap());

    //if the monster life is 1, there is 50% chance that player will be eaten
    assertFalse(dungeonIWithMonster.judgeWhetherDeath(8));
    assertTrue(dungeonIWithMonster.judgeWhetherDeath(13));

    player = new PlayerWithArrow(dungeonIWithMonster.getStartPoint());
    assertEquals(3, player.getArrowNum());
    player.pickUpArrow();
    assertEquals(4, player.getArrowNum());
    dungeonIWithMonster.updateArrow(1);
    assertFalse(dungeonIWithMonster.getArrow(1));
    player.updateArrow();
    assertEquals(3, player.getArrowNum());
  }

  /**
   * test player shoot without arrow.
   */
  @Test(expected = IllegalStateException.class)
  public void test2() {
    player = new PlayerWithArrow(new int[]{2, 4, 4});
    player.updateArrow();
    player.updateArrow();
    player.updateArrow();
    player.updateArrow();
  }

  /**
   * test monster num < 1.
   */
  @Test(expected = IllegalArgumentException.class)
  public void test3() {
    RandomInterface random = new RealRandom();
    int row = 4;
    int column = 4;
    int monsterNum = 0;
    DungeonIWithMonster dungeonIWithMonster = new DungeonIWithMonster(row, column,
            0, false, 50, random, monsterNum);
  }

  /**
   * test there is always a monster in the end and no monster in the start.
   */
  @Test
  public void test4() {
    for (int k = 0; k < 100; k++) {
      RandomInterface random = new RealRandom();
      int row = 4;
      int column = 4;
      int monsterNum = 3;
      DungeonIWithMonster dungeonIWithMonster = new DungeonIWithMonster(row, column,
              0, false, 50, random, monsterNum);
      int loc = dungeonIWithMonster.getStartEnd()[0];
      int res1 = (loc - 1) / column;
      int res2 = loc - res1 * column - 1;
      res1 = res1 * 2 + 1;
      res2 = res2 * 2 + 1;
      assertEquals(" 0 ", dungeonIWithMonster.showMap()[res1][res2]);

      loc = dungeonIWithMonster.getStartEnd()[1];
      res1 = (loc - 1) / column;
      res2 = loc - res1 * column - 1;
      res1 = res1 * 2 + 1;
      res2 = res2 * 2 + 1;
      assertEquals(" 2 ", dungeonIWithMonster.showMap()[res1][res2]);
    }
  }
}