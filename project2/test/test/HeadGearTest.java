package test;

import org.junit.Before;
import org.junit.Test;

import gear.HeadGear;

import static org.junit.Assert.assertArrayEquals;

/**
 * This is a test class for HeadGear.
 */
public class HeadGearTest {
  HeadGear headGear;

  /**
   * Setting up objects for tests.
   */
  @Before
  public void setUp() {
    headGear = new HeadGear("headGear", 1);
  }

  /**
   * Test input without effect.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    headGear = new HeadGear("headGear", 0);
  }

  /**
   * Test only affect constitution.
   */
  @Test
  public void testAffectConstitution() {
    assertArrayEquals(new int[]{0, 1, 0, 0}, headGear.getEffect());
  }
}