package test;

import org.junit.Before;
import org.junit.Test;

import gear.Potion;

import static org.junit.Assert.assertArrayEquals;


/**
 * This is a test class for Potion.
 */
public class PotionTest {
  Potion potion;

  /**
   * Setting up objects for tests.
   */
  @Before
  public void setUp() {
    potion = Potion.getBuilder().name("potion").affectStrength(1).affectCharisma(1)
            .affectDexterity(1).affectConstitution(1).builder();
  }

  /**
   * Test successfully create a potion by getting its effect on ability.
   */
  @Test
  public void testEffect() {
    assertArrayEquals(new int[]{1, 1, 1, 1}, potion.getEffect());
  }

  /**
   * Test input without affecting any attribute.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    potion = Potion.getBuilder().name("potion").builder();
  }

  /**
   * Test input affecting one attribute.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    potion = Potion.getBuilder().name("potion").affectConstitution(1).builder();
  }

  /**
   * Test input affecting two attribute.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    potion = Potion.getBuilder().name("potion").affectConstitution(1)
            .affectDexterity(1).builder();
  }

  /**
   * Test input affecting three attribute.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4() {
    potion = Potion.getBuilder().name("potion").affectConstitution(1)
            .affectDexterity(1).affectCharisma(1).builder();
  }
}