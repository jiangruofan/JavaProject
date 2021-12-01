package test;

import org.junit.Before;
import org.junit.Test;

import gear.Belt;
import gear.Size;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * This is a test class for Belt.
 */
public class BeltTest {
  Belt belt;
  Belt belt2;
  Belt belt3;

  /**
   * Setting up objects for tests.
   */
  @Before
  public void setUp() {
    belt = Belt.getBuilder().name("belt").size(Size.SMALL).affectStrength(4)
            .affectDexterity(3).build();
    belt2 = Belt.getBuilder().name("belt").size(Size.MEDIUM).affectStrength(4)
            .affectDexterity(3).build();
    belt3 = Belt.getBuilder().name("belt").size(Size.LARGE).affectStrength(4)
            .affectDexterity(3).build();
  }

  /**
   * Test successfully create a belt by getting its effect on ability.
   */
  @Test
  public void testEffect() {
    assertArrayEquals(new int[]{4, 0, 3, 0}, belt.getEffect());
  }

  /**
   * Test input without size.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    belt = Belt.getBuilder().name("belt").affectStrength(4)
            .affectConstitution(1).build();
  }

  /**
   * Test input without affecting any attribute.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    belt = Belt.getBuilder().name("belt").size(Size.SMALL).build();
  }

  /**
   * Test input affecting three attribute.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    belt = Belt.getBuilder().name("belt").size(Size.LARGE).affectStrength(1).affectConstitution(1)
                    .affectCharisma(1).build();
  }

  /**
   * Test input affecting one attribute.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4() {
    belt = Belt.getBuilder().name("belt").size(Size.LARGE).affectStrength(1)
            .affectDexterity(0).build();
  }

  /**
   * Test input affecting four attribute.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor5() {
    belt = Belt.getBuilder().name("belt").size(Size.LARGE).affectStrength(1).affectConstitution(1)
                    .affectCharisma(1).affectDexterity(1).build();
  }

  /**
   * Test get the size of the belt.
   */
  @Test
  public void testGetSize() {
    assertEquals(1, belt.getSize());
    assertEquals(2, belt2.getSize());
    assertEquals(4, belt3.getSize());
  }
}