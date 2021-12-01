package house;

import java.util.ArrayList;
import java.util.HashMap;

import monkey.Monkey;
import monkey.Size;
import monkey.Food;
import monkey.Sex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

/**
 * This is a test class for Enclosure.
 */
public class EnclosuresTest {
  Monkey monkey1 = new Monkey("obj1", "saki", Sex.male, Size.large, 15, 10, Food.nuts);
  Monkey monkey2 = new Monkey("obj2", "drill", Sex.male, Size.small, 10, 10, Food.nuts);
  Enclosures enclosure1;
  Enclosures enclosure2;

  /** Setting up objects for tests. */
  @org.junit.Before
  public void setUp() throws Exception {
    enclosure1 = new Enclosures(1, 15, monkey1);
    enclosure2 = new Enclosures(2, 40, monkey2);
  }

  /**
   * Tests whether the input is valid.
   */
  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    enclosure1 = new Enclosures(5,10,null);
  }

  /**
   * Tests get room number.
   */
  @org.junit.Test
  public void testGetNumber() {
    assertEquals(1, enclosure1.getNumber());
    assertEquals(2, enclosure2.getNumber());
  }

  /**
   * Tests whether there is enough space for incoming monkey.
   */
  @org.junit.Test
  public void testJudgeSpace() {
    assertFalse(enclosure1.judgeSpace(Size.large));
    assertTrue(enclosure2.judgeSpace(Size.large));
  }

  /**
   * Tests whether the input is valid.
   */
  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testUpdate() {
    enclosure1.update("");
  }

  /**
   * Tests whether successfully remove the monkey.
   */
  @org.junit.Test
  public void testUpdate1() {
    Monkey monkey = new Monkey("obj1", "saki", Sex.male, Size.large, 15, 10, Food.nuts);
    assertEquals(monkey, this.enclosure1.update("obj1"));
    assertNull(enclosure2.update("jrf"));
  }

  /**
   * Tests whether successfully add the monkey.
   */
  @org.junit.Test
  public void testUpdate2() {
    Monkey monkey = new Monkey("obj1", "saki", Sex.male, Size.small, 15, 10, Food.nuts);
    this.enclosure1.update(monkey);
    assertEquals(4, this.enclosure1.getSizeLeft());
    this.enclosure2.update(monkey);
    assertEquals(38, enclosure2.getSizeLeft());
  }

  /**
   * Tests get the left size of the room.
   */
  @org.junit.Test
  public void testGetSizeLeft() {
    assertEquals(5, enclosure1.getSizeLeft());
    assertEquals(39, enclosure2.getSizeLeft());
  }

  /**
   * Tests produce a sign for the room.
   */
  @org.junit.Test
  public void testProduceSign() {
    ArrayList<String> res = new ArrayList<>();
    res.add("obj1, male, nuts");
    assertEquals(res, this.enclosure1.produceSign());
    res.remove(0);
    res.add("obj2, male, nuts");
    assertEquals(res, enclosure2.produceSign());
  }

  /**
   * Tests get the species of the room.
   */
  @org.junit.Test
  public void testGetSpecies() {
    assertEquals("saki", enclosure1.getSpecies());
    assertEquals("drill", enclosure2.getSpecies());
  }

  /**
   * Tests get the names of monkeys living in the room.
   */
  @org.junit.Test
  public void testGetName() {
    ArrayList<String> res = new ArrayList<>();
    res.add("obj1");
    assertEquals(res, this.enclosure1.getName());
    res.remove(0);
    res.add("obj2");
    assertEquals(res, enclosure2.getName());
  }

  /**
   * Tests get the food and quantity.
   */
  @org.junit.Test
  public void testGetFoodAndQuantity() {
    HashMap<Food, Integer> res = new HashMap<Food, Integer>();
    res.put(Food.nuts, 500);
    assertEquals(res, this.enclosure1.getFoodAndQuantity());
    res.remove(Food.nuts);
    res.put(Food.nuts, 100);
    assertEquals(res, enclosure2.getFoodAndQuantity());
  }
}