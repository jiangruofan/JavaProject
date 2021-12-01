package test;

import org.junit.Before;
import org.junit.Test;
import player.Player;
import treasure.Treasure;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * This is a test class for player.
 */
public class PlayerTest {
  Player player;

  /**
   * Set up object for test.
   */
  @Before
  public void setUp() {
    player = new Player(new int[]{10, 4, 4});
  }

  /**
   * test player.
   */
  @Test
  public void playerTest() {
    assertArrayEquals(new int[]{2,1}, player.getLocInPoint());
    ArrayList<String> arrayList = new ArrayList<>();
    arrayList.add("SOUTH");
    arrayList.add("NORTH");
    assertTrue(player.move("south", arrayList));
    assertArrayEquals(new int[]{3,1}, player.getLocInPoint());
    assertFalse(player.move("west", arrayList));

    ArrayList<Treasure> arrayList1 = new ArrayList<>();
    arrayList1.add(Treasure.SAPPHIRES);
    arrayList1.add(Treasure.RUBIES);
    assertEquals(3, player.pickUpTreasure("rubies", arrayList1));
    assertEquals(2, player.pickUpTreasure("no", arrayList1));
    assertEquals(1, player.pickUpTreasure("diamonds", arrayList1));

    HashMap<Treasure, Integer> hashMap = new HashMap<>();
    hashMap.put(Treasure.RUBIES, 1);
    assertEquals(hashMap, player.getDescription());

  }

}