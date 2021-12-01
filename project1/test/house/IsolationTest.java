package house;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import monkey.Monkey;
import monkey.Size;
import monkey.Food;
import monkey.Sex;

import static org.junit.Assert.assertEquals;

/**
 * This is a test class for Isolation.
 */
public class IsolationTest {
  Isolation isolation1;
  Isolation isolation2;

  /** Setting up objects for tests. */
  @org.junit.Before
  public void setUp() {
    Monkey monkey = new Monkey("obj2", "saki", Sex.male, Size.small, 15, 10, Food.nuts);
    isolation1 = new Isolation(1, "obj1", "drill", Sex.male, 30, 30, 10, Food.nuts);
    isolation2 = new Isolation(2, monkey);
  }

  /**
   * Tests whether input value is valid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1() {
    isolation1 = new Isolation(1, "", "drill", Sex.male, 30, 30, 10, Food.nuts);
  }

  /**
   * Tests whether input value is valid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    isolation1 = new Isolation(1, "obj1", "", Sex.male, 30, 30, 10, Food.nuts);
  }

  /**
   * Tests whether input value is valid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    isolation1 = new Isolation(1, null);
  }

  /**
   * Tests get room number.
   */
  @Test
  public void testGetNumber() {
    assertEquals(1, isolation1.getNumber());
    assertEquals(2, isolation2.getNumber());
  }

  /**
   * Tests get the name of monkey.
   */
  @Test
  public void testGetName() {
    ArrayList<String> res = new ArrayList<>();
    res.add("obj1");
    assertEquals(res, isolation1.getName());
    res.remove(0);
    res.add("obj2");
    assertEquals(res, isolation2.getName());
  }

  /**
   * Tests get a clone of the monkey.
   */
  @Test
  public void testGetMonkey() {
    Monkey monkey1 = new Monkey("obj1", "drill", Sex.male, Size.large, 30, 10, Food.nuts);
    assertEquals(monkey1, isolation1.getMonkey());
    Monkey monkey2 = new Monkey("obj2", "saki", Sex.male, Size.small, 15, 10, Food.nuts);
    assertEquals(monkey2, isolation2.getMonkey());
  }

  /**
   * Tests get the species of the monkey.
   */
  @Test
  public void testGetSpecies() {
    assertEquals("drill", isolation1.getSpecies());
    assertEquals("saki", isolation2.getSpecies());
  }

  /**
   * Tests get the food and quantity of the monkey.
   */
  @Test
  public void testGetFoodAndQuantity() {
    HashMap<Food, Integer> res = new HashMap<>();
    res.put(Food.nuts, 500);
    assertEquals(res, isolation1.getFoodAndQuantity());
    res.remove(Food.nuts);
    res.put(Food.nuts, 100);
    assertEquals(res, isolation2.getFoodAndQuantity());
  }
}