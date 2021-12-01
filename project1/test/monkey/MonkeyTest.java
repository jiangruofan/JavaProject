package monkey;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This is a test class for monkey.
 */
public class MonkeyTest {
  Monkey monkey1;
  Monkey monkey2;

  /** Setting up objects for tests. */
  @Before
  public void setUp() {
    monkey1 = new Monkey("obj1", "drill", Sex.male, Size.large, 30, 10, Food.nuts);
    monkey2 = new Monkey("obj2", "saki", Sex.female, Size.small, 15, 8, Food.seeds);
  }

  /**
   * Tests get the name of the monkey.
   */
  @Test
  public void testGetName() {
    assertEquals("obj1", monkey1.getName());
    assertEquals("obj2", monkey2.getName());
  }

  /**
   * Tests get the species of the monkey.
   */
  @Test
  public void testGetSpecies() {
    assertEquals("drill", monkey1.getSpecies());
    assertEquals("saki", monkey2.getSpecies());
  }

  /**
   * Tests get the sex of the monkey.
   */
  @Test
  public void testGetSex() {
    assertEquals(Sex.male, monkey1.getSex());
    assertEquals(Sex.female, monkey2.getSex());
  }

  /**
   * Tests get the size of the monkey.
   */
  @Test
  public void testGetSize() {
    assertEquals(Size.large, monkey1.getSize());
    assertEquals(Size.small, monkey2.getSize());
  }

  /**
   * Tests get the weight of the monkey.
   */
  @Test
  public void testGetWeight() {
    assertEquals(30, monkey1.getWeight(), 0.001);
    assertEquals(15, monkey2.getWeight(), 0.001);
  }

  /**
   * Tests get the age of the monkey.
   */
  @Test
  public void testGetAge() {
    assertEquals(10, monkey1.getAge());
    assertEquals(8, monkey2.getAge());
  }

  /**
   * Tests get the favourite food of the monkey.
   */
  @Test
  public void testGetFavouriteFood() {
    assertEquals(Food.nuts, monkey1.getFavouriteFood());
    assertEquals(Food.seeds, monkey2.getFavouriteFood());
  }

  /**
   * Tests get a copy of the monkey.
   */
  @Test
  public void testClone() {
    Monkey a = new Monkey("obj1", "drill", Sex.male, Size.large, 30, 10, Food.nuts);
    assertEquals(a, this.monkey1.clone());
    Monkey b = new Monkey("obj2", "saki", Sex.female, Size.small, 15, 8, Food.seeds);
    assertEquals(b, this.monkey2.clone());
  }

  /**
   * Tests the equal function.
   */
  @Test
  public void testEquals() {
    Monkey a = new Monkey("obj1", "drill", Sex.male, Size.large, 30, 10, Food.nuts);
    assertEquals(a, this.monkey1);
    Monkey b = new Monkey("obj2", "saki", Sex.female, Size.small, 15, 8, Food.seeds);
    assertEquals(b, this.monkey2);

  }
}