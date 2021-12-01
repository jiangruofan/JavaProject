package test;

import org.junit.Before;
import org.junit.Test;

import gear.FootWear;

import static org.junit.Assert.assertArrayEquals;

/**
 * This is a test class for Footwear.
 */
public class FootWearTest {
  FootWear footWear;

  /**
   * Setting up objects for tests.
   */
  @Before
  public void setUp() {
    footWear = new FootWear("footwear", 3);
  }

  /**
   * Test input without effect.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    footWear = new FootWear("footwear", 0);
  }

  /**
   * Test only affect dexterity.
   */
  @Test
  public void testAffectDexterity() {
    assertArrayEquals(new int[]{0, 0, 3, 0}, footWear.getEffect());
  }
}