package test;

import gear.Gear;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

/**
 * This is a test class for gear.
 */
public class GearTest {
  Gear gear;

  /**
   * Setting up objects for tests.
   */
  @org.junit.Before
  public void setUp() {
    gear = new Gear("gear", 1, 1, 1, 1);
  }

  /**
   * Test the name of input is null.
   */
  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    gear = new Gear(null, 1, 1, 1, 1);
  }

  /**
   * Test get the effect of the gear.
   */
  @org.junit.Test
  public void testGetEffect() {
    int[] res = {1, 1, 1, 1};
    assertArrayEquals(res, gear.getEffect());
  }

  /**
   * Test get the name of the gear.
   */
  @org.junit.Test
  public void testGetName() {
    assertEquals("gear", gear.getName());
  }
}