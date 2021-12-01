package test;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gear.Belt;
import gear.FootWear;
import gear.Gear;
import gear.HeadGear;
import gear.Potion;
import gear.Size;
import player.Player;
import random.PredictableRandom;
import random.RandomInterface;
import weapon.Weapon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;

/**
 * This is a test class for Player.
 */
public class PlayerTest {
  RandomInterface random;
  Player player;

  /**
   * Setting up objects for tests.
   */
  @Before
  public void setUp() {
    int[] order = {
        6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
    };
    random = new PredictableRandom(order);
    player = new Player(random);
  }

  /**
   * Test the random input could not be null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    player = new Player(null);
  }

  /**
   * Test players to enter the arena with only their basic abilities and their bare hands.
   */
  @Test
  public void testBasic() {
    assertEquals(18, player.getStrength());
    assertEquals(18, player.getDexterity());
    assertEquals(18, player.getConstitution());
    assertEquals(18, player.getCharisma());
    assertNull(player.getWeapon());
  }

  /**
   * Test players to enter the arena with weapon.
   */
  @Test
  public void testWeapon() {
    int[] order = {
        6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
        1,
    };
    Weapon[] weapon = {Weapon.KATANAS, Weapon.AXES, Weapon.FLAILS,
        Weapon.BROADSWORDS, Weapon.TWOHANDEDSWORDS};
    List<Weapon> weapons = new ArrayList<>(Arrays.asList(weapon));
    random = new PredictableRandom(order);
    player = new Player(random);
    player.setWeapon(weapons);
    assertEquals(Weapon.AXES, player.getWeapon());
  }

  /**
   * Test players to enter the arena with weapon which is one Katana.
   */
  @Test
  public void testWeapon1() {
    int[] order = {
        6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
        0, 3,
    };
    Weapon[] weapon = {Weapon.KATANAS, Weapon.AXES, Weapon.FLAILS,
        Weapon.BROADSWORDS, Weapon.TWOHANDEDSWORDS};
    List<Weapon> weapons = new ArrayList<>(Arrays.asList(weapon));
    random = new PredictableRandom(order);
    player = new Player(random);
    player.setWeapon(weapons);
    assertEquals(Weapon.KATANAS, player.getWeapon());
  }

  /**
   * Test players to enter the arena with weapon which are two KATANAS.
   */
  @Test
  public void testWeapon2() {
    int[] order = {
        6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
        0, 1,
    };
    Weapon[] weapon = {Weapon.KATANAS, Weapon.KATANAS, Weapon.AXES, Weapon.FLAILS,
        Weapon.BROADSWORDS, Weapon.TWOHANDEDSWORDS};
    List<Weapon> weapons = new ArrayList<>(Arrays.asList(weapon));
    random = new PredictableRandom(order);
    player = new Player(random);
    player.setWeapon(weapons);
    assertEquals(Weapon.KATANASPAIR, player.getWeapon());
  }

  /**
   * Test players to enter the arena with equipment.
   * Test produce description for the player.
   * Test get the health of the player.
   */
  @Test
  public void testEquipmentAndProduceSignAndGetHealth() {
    int[] order = {
        6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
        0, 5, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 25, 26, 27, 28, 29, 30, 31, 32,
        1,
    };
    Belt belt1 = Belt.getBuilder().name("belt1").size(Size.SMALL).affectConstitution(1)
                    .affectStrength(1).build();
    Belt belt2 = Belt.getBuilder().name("belt2").size(Size.SMALL).affectConstitution(1)
                    .affectStrength(1).build();
    Belt belt3 = Belt.getBuilder().name("belt3").size(Size.SMALL).affectConstitution(1)
                    .affectStrength(1).build();
    Belt belt4 = Belt.getBuilder().name("belt4").size(Size.MEDIUM).affectConstitution(1)
                    .affectStrength(1).build();
    Belt belt5 = Belt.getBuilder().name("belt5").size(Size.MEDIUM).affectConstitution(-1)
                    .affectStrength(-1).build();
    Belt belt6 = Belt.getBuilder().name("belt6").size(Size.MEDIUM).affectCharisma(1)
                    .affectDexterity(1).build();
    Belt belt7 = Belt.getBuilder().name("belt7").size(Size.SMALL).affectCharisma(1)
                    .affectDexterity(1).build();
    Belt belt8 = Belt.getBuilder().name("belt8").size(Size.SMALL).affectCharisma(1)
                    .affectDexterity(1).build();
    Belt belt9 = Belt.getBuilder().name("belt9").size(Size.SMALL).affectCharisma(1)
                    .affectDexterity(1).build();
    Belt belt10 = Belt.getBuilder().name("belt10").size(Size.LARGE).affectCharisma(-1)
                    .affectDexterity(-1).build();
    Belt belt11 = Belt.getBuilder().name("belt11").size(Size.LARGE).affectStrength(1)
            .affectDexterity(1).build();
    Belt belt12 = Belt.getBuilder().name("belt12").size(Size.LARGE).affectStrength(1)
            .affectDexterity(1).build();
    Belt belt13 = Belt.getBuilder().name("belt13").size(Size.LARGE).affectStrength(1)
            .affectDexterity(1).build();
    Belt belt14 = Belt.getBuilder().name("belt14").size(Size.MEDIUM).affectStrength(1)
            .affectDexterity(1).build();
    Belt belt15 = Belt.getBuilder().name("belt15").size(Size.MEDIUM).affectStrength(-1)
            .affectDexterity(-1).build();

    List<Potion> potionList = new ArrayList<>();
    for (int k = 0; k < 15; k++) {
      Potion potion;
      if (k < 12) {
        potion = Potion.getBuilder().name(String.format("potion%d", k + 1)).affectStrength(1)
                .affectConstitution(1).affectDexterity(1)
                .affectCharisma(1).builder();
      } else {
        potion = Potion.getBuilder().name(String.format("potion%d", k + 1)).affectStrength(-1)
                .affectConstitution(-1).affectDexterity(-1)
                .affectCharisma(-1).builder();
      }
      potionList.add(potion);
    }

    Gear[] gears = {new HeadGear("headgear1", 1), new HeadGear("headgear2", 1),
        new HeadGear("headgear3", 1), new HeadGear("headgear4", -1),
        new HeadGear("headgear5", -1), new FootWear("footwear1", 1),
        new FootWear("footwear2", 1), new FootWear("footwear3", 1),
        new FootWear("footwear4", -1), new FootWear("footwear5", -1),
        belt1, belt2, belt3, belt4, belt5, belt6, belt7, belt8, belt9, belt10, belt11, belt12,
        belt13, belt14, belt15,
    };
    List<Gear> res = new ArrayList<>(Arrays.asList(gears));
    res.addAll(potionList);
    random = new PredictableRandom(order);
    player = new Player(random);
    player.setEquipment(res);
    assertEquals(29, player.getStrength());
    assertEquals(30, player.getConstitution());
    assertEquals(30, player.getDexterity());
    assertEquals(29, player.getCharisma());

    Weapon[] weapon = {Weapon.KATANAS, Weapon.AXES, Weapon.FLAILS,
        Weapon.BROADSWORDS, Weapon.TWOHANDEDSWORDS};
    List<Weapon> weapons = new ArrayList<>(Arrays.asList(weapon));
    player.setWeapon(weapons);
    String[] describe = {
        "29", "30", "30", "29", "headgear1", "potion1", "potion2", "potion3",
        "potion4", "potion5", "potion6", "potion7", "potion8", "belt1", "belt2", "belt3",
        "belt4", "belt5", "belt6", "belt7", "belt8", "footwear1", "AXES" };
    List<String> des = new ArrayList<>(Arrays.asList(describe));
    assertEquals(des, player.produceDescription());

    assertEquals(118, player.getHealth());
  }

  /**
   * Test the health after getting damage and reset the health to the beginning health.
   */
  @Test
  public void testSetHealth() {
    player.setHealth(5);
    assertEquals(67, player.getHealth());
    player.setHealth();
    assertEquals(72, player.getHealth());
  }

  /**
   * Test get the strength of the player.
   */
  @Test
  public void testGetStrength() {
    assertEquals(18, player.getStrength());
  }

  /**
   * Test get the dexterity of the player.
   */
  @Test
  public void testGetDexterity() {
    assertEquals(18, player.getDexterity());
  }

  /**
   * Test get the constitution of the player.
   */
  @Test
  public void testGetConstitution() {
    assertEquals(18, player.getConstitution());
  }

  /**
   * Test get the charisma of the player.
   */
  @Test
  public void TestGetCharisma() {
    assertEquals(18, player.getCharisma());
  }

  /**
   * Test get the weapon of the player.
   */
  @Test
  public void testGetWeapon() {
    assertNull(player.getWeapon());
  }

  /**
   * Test whether the player is alive.
   */
  @Test
  public void testJudgeDeath() {
    assertFalse(player.judgeDeath());
  }
}