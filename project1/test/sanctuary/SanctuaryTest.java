package sanctuary;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import monkey.Food;
import monkey.Sex;

import static org.junit.Assert.assertEquals;

/**
 * This is a test class for sanctuary.Sanctuary.
 */
public class SanctuaryTest {
  Sanctuary sanctuary;

  /** Setting up objects for tests. */
  @Before
  public void setUp() {
    sanctuary = new Sanctuary(10, 10, 40);
  }

  /**
   * Tests whether input is valid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1() {
    sanctuary = new Sanctuary(-1, 10, 10);
  }

  /**
   * Tests whether input is valid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    sanctuary = new Sanctuary(10, -1, 10);
  }

  /**
   * Tests whether input is valid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    sanctuary = new Sanctuary(10, 10, -1);
  }

  /**
   * Tests whether input is valid.
   */
  @Test(expected = IllegalStateException.class)
  public void testSetIsolations1() {
    Sanctuary sanctuary = new Sanctuary(0, 10, 10);
    sanctuary.setIsolations("obj1", "drill", Sex.male, 5, 100, 100, Food.eggs);
  }

  /**
   * Tests whether input is valid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetIsolations2() {
    Sanctuary sanctuary = new Sanctuary(10, 10, 10);
    sanctuary.setIsolations("obj1", "drill", Sex.male, 5, 100, 100, Food.eggs);
    sanctuary.setIsolations("obj1", "drill", Sex.male, 5, 100, 100, Food.eggs);
  }

  /**
   * Tests whether input is valid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetIsolations3() {
    Sanctuary sanctuary = new Sanctuary(10, 10, 10);
    sanctuary.setIsolations("obj1", "drill", Sex.male, 5, 100, 100, Food.eggs);
    sanctuary.transferToEnclosure("obj1");
    sanctuary.setIsolations("obj1", "drill", Sex.male, 5, 100, 100, Food.eggs);
  }

  /**
   * Tests whether input is valid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetIsolations4() {
    Sanctuary sanctuary = new Sanctuary(10, 10, 10);
    sanctuary.setIsolations("", "drill", Sex.male, 5, 100, 100, Food.eggs);
  }

  /**
   * Tests whether input is valid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetIsolations5() {
    Sanctuary sanctuary = new Sanctuary(10, 10, 10);
    sanctuary.setIsolations("obj1", "", Sex.male, 5, 100, 100, Food.eggs);
  }

  /**
   * Tests whether input is valid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testTransferToIsolation_() {
    Sanctuary sanctuary = new Sanctuary(10, 10, 10);
    sanctuary.setIsolations("obj1", "drill", Sex.male, 5, 100, 100, Food.eggs);
    sanctuary.transferToEnclosure("obj1");
    sanctuary.transferToIsolation("123");
  }

  /**
   * Tests whether input is valid.
   */
  @Test(expected = IllegalStateException.class)
  public void testTransferToIsolation_1() {
    Sanctuary sanctuary = new Sanctuary(1, 10, 10);
    sanctuary.setIsolations("obj1", "drill", Sex.male, 5, 100, 100, Food.eggs);
    sanctuary.transferToEnclosure("obj1");
    sanctuary.setIsolations("obj2", "drill", Sex.male, 5, 100, 100, Food.eggs);
    sanctuary.transferToIsolation("obj1");
  }

  /**
   * Tests whether input is valid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testTransferToIsolation_2() {
    Sanctuary sanctuary = new Sanctuary(10, 10, 10);
    sanctuary.setIsolations("obj1", "drill", Sex.male, 5, 100, 100, Food.eggs);
    sanctuary.transferToEnclosure("obj1");
    sanctuary.transferToIsolation("");
  }

  /**
   * Tests transfer the monkey from enclosures to isolation.
   */
  @Test
  public void testTransferToIsolation() {
    Sanctuary sanctuary = new Sanctuary(10, 10, 10);
    sanctuary.setIsolations("obj1", "drill", Sex.male, 5, 100, 100, Food.eggs);
    sanctuary.transferToEnclosure("obj1");
    assertEquals("obj1 is successfully transferred to Isolation 1",
            sanctuary.transferToIsolation("obj1"));
  }

  /**
   * Tests whether input is valid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testTransferToEnclosure_() {
    Sanctuary sanctuary = new Sanctuary(10, 10, 10);
    sanctuary.setIsolations("obj1", "drill", Sex.male, 5, 100, 100, Food.eggs);
    sanctuary.transferToEnclosure("123");
  }

  /**
   * Tests whether input is valid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testTransferToEnclosure_1() {
    Sanctuary sanctuary = new Sanctuary(10, 10, 10);
    sanctuary.setIsolations("obj1", "drill", Sex.male, 5, 100, 100, Food.eggs);
    sanctuary.transferToEnclosure("");
  }

  /**
   * Tests transfer monkeys from isolation to enclosures.
   */
  @Test
  public void testTransferToEnclosure() {
    Sanctuary sanctuary = new Sanctuary(10, 1, 10);
    sanctuary.setIsolations("obj1", "drill", Sex.male, 15, 100, 100, Food.eggs);
    sanctuary.setIsolations("obj2", "saki", Sex.male, 5, 100, 100, Food.eggs);
    sanctuary.setIsolations("obj3", "drill", Sex.male, 50, 100, 100, Food.eggs);
    assertEquals("obj1 is successfully transferred to Enclosures 1",
            sanctuary.transferToEnclosure("obj1"));
    assertEquals("there is no room available for the monkey in this sanctuary and will be "
            + "transferred to other sanctuaries", sanctuary.transferToEnclosure("obj2"));
    assertEquals("there is no room available for the monkey in this sanctuary and will be "
            + "transferred to other sanctuaries", sanctuary.transferToEnclosure("obj3"));

    sanctuary = new Sanctuary(10, 10, 5);
    sanctuary.setIsolations("obj1", "drill", Sex.male, 40, 100, 100, Food.eggs);
    assertEquals("The monkey is too large and there is no room big enough to put it,"
            + " it will be transferred to other sanctuary", sanctuary.transferToEnclosure("obj1"));

    sanctuary = new Sanctuary(10, 10, 20);
    sanctuary.setIsolations("obj1", "drill", Sex.male, 40, 100, 100, Food.eggs);
    sanctuary.setIsolations("obj2", "drill", Sex.male, 40, 100, 100, Food.eggs);
    sanctuary.setIsolations("obj3", "drill", Sex.male, 40, 100, 100, Food.eggs);
    assertEquals("obj1 is successfully transferred to Enclosures 1",
            sanctuary.transferToEnclosure("obj1"));
    assertEquals("obj2 is successfully transferred to Enclosures 1",
            sanctuary.transferToEnclosure("obj2"));
    assertEquals("obj3 is successfully transferred to Enclosures 2",
            sanctuary.transferToEnclosure("obj3"));
  }

  /**
   * Tests report all the species and their living area.
   */
  @Test
  public void testReportSpecies() {
    Sanctuary sanctuary = new Sanctuary(10, 10, 20);
    sanctuary.setIsolations("obj1", "drill", Sex.male, 40, 100, 100, Food.eggs);
    sanctuary.setIsolations("obj2", "saki", Sex.male, 40, 100, 100, Food.eggs);
    sanctuary.transferToEnclosure("obj2");
    ArrayList<String> res = new ArrayList<>();
    res.add("drill is in isolation:1");
    res.add("saki is in enclosures:1");
    assertEquals(res, sanctuary.reportSpecies());
  }

  /**
   * Tests whether input is valid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLookUpSpecies_() {
    Sanctuary sanctuary = new Sanctuary(10, 10, 20);
    sanctuary.setIsolations("obj1", "drill", Sex.male, 40, 100, 100, Food.eggs);
    sanctuary.lookUpSpecies("");
  }

  /**
   * Tests find a certain species.
   */
  @Test
  public void testLookUpSpecies() {
    Sanctuary sanctuary = new Sanctuary(10, 10, 20);
    sanctuary.setIsolations("obj1", "drill", Sex.male, 40, 100, 100, Food.eggs);
    sanctuary.setIsolations("obj2", "drill", Sex.male, 40, 100, 100, Food.eggs);
    sanctuary.transferToEnclosure("obj2");
    ArrayList<String> res = new ArrayList<>();
    res.add("isolation:1");
    res.add("enclosures:1");
    assertEquals(res, sanctuary.lookUpSpecies("drill"));
    res = new ArrayList<>();
    res.add("there is no such species");
    assertEquals(res, sanctuary.lookUpSpecies("abc"));
  }

  /**
   * Tests produce a sign for an enclosure.
   */
  @Test
  public void testProduceSign() {
    Sanctuary sanctuary = new Sanctuary(10, 10, 20);
    sanctuary.setIsolations("obj1", "drill", Sex.male, 40, 100, 100, Food.eggs);
    sanctuary.setIsolations("obj2", "drill", Sex.female, 40, 100, 100, Food.nuts);
    sanctuary.transferToEnclosure("obj1");
    sanctuary.transferToEnclosure("obj2");
    ArrayList<String> res = new ArrayList<>();
    res.add("obj1, male, eggs");
    res.add("obj2, female, nuts");
    assertEquals(res, sanctuary.produceSign(1));
    res = new ArrayList<>();
    res.add("the enclosure is empty");
    assertEquals(res, sanctuary.produceSign(5));
  }

  /**
   * Tests produce name list of monkeys living in the sanctuary.
   */
  @Test
  public void testProduceNameList() {
    Sanctuary sanctuary = new Sanctuary(10, 10, 20);
    sanctuary.setIsolations("obj1", "drill", Sex.male, 40, 100, 100, Food.eggs);
    sanctuary.setIsolations("obj2", "drill", Sex.female, 40, 100, 100, Food.nuts);
    sanctuary.transferToEnclosure("obj2");
    ArrayList<String> res = new ArrayList<>();
    res.add("obj1 in isolation:1");
    res.add("obj2 in enclosure:1");
    assertEquals(res, sanctuary.produceNameList());
  }

  /**
   * Tests produce the food and quantity needed by the monkeys.
   */
  @Test
  public void testProduceShoppingList() {
    Sanctuary sanctuary = new Sanctuary(10, 10, 20);
    sanctuary.setIsolations("obj1", "drill", Sex.male, 15, 100, 100, Food.eggs);
    sanctuary.setIsolations("obj2", "drill", Sex.female, 40, 100, 100, Food.nuts);
    sanctuary.transferToEnclosure("obj2");
    HashMap<Food, Integer> res = new HashMap<>();
    res.put(Food.nuts, 500);
    res.put(Food.eggs, 250);
    assertEquals(res, sanctuary.produceShoppingList());
  }

  /**
   * Tests whether input is valid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExpand_() {
    Sanctuary sanctuary = new Sanctuary(10, 10, 20);
    sanctuary.expand(5, 5);
  }

  /**
   * Tests expand the sanctuary.
   */
  @Test
  public void testExpand() {
    Sanctuary sanctuary = new Sanctuary(10, 10, 20);
    int[] res = sanctuary.expand(20, 20);
    assertEquals(10, res[0]);
    assertEquals(10, res[1]);
    assertEquals(20, res[2]);
    assertEquals(20, res[3]);
  }
}