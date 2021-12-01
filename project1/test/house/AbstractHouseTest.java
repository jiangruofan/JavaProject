package house;

import org.junit.Before;
import org.junit.Test;

import monkey.Food;
import monkey.Sex;

import static org.junit.Assert.assertEquals;

/**
 * This is a test class for AbstractHouse.
 */
public class AbstractHouseTest {
  House house;

  /** Setting up objects for tests. */
  @Before
  public void setUp() {
    house = new Isolation(10, "obj1", "drill", Sex.male, 30, 30, 10, Food.nuts);
  }

  /**
   * Tests get room number.
   */
  @Test
  public void testGetNumber() {
    assertEquals(10, house.getNumber());
  }
}