package test;

import org.junit.Before;
import org.junit.Test;

import random.RandomInterface;
import random.RealRandom;

import static org.junit.Assert.assertTrue;

/**
 * This is a test class for real randomness.
 */
public class RandomInterfaceTest {
  RandomInterface random;

  /**
   * Setting up objects for tests.
   */
  @Before
  public void setUp() {
    random = new RealRandom();
  }

  /**
   * Test whether there is actual randomness.
   */
  @Test
  public void testGetRandom() {
    int i = random.getRandom(1, 5);
    int i1 = random.getRandom(1, 5);
    assertTrue(i != i1);
  }

  /**
   * Test whether created values are all in the range.
   */
  @Test
  public void testGetRandom1() {
    int random;
    for (int i = 0; i < 1000; i++) {
      random = this.random.getRandom(1, 10);
      assertTrue(random >= 1);
      assertTrue(random <= 10);
    }
  }
}