package test;

import org.junit.Before;
import org.junit.Test;

import battle.Battle;
import player.Player;
import random.PredictableRandom;
import random.RandomInterface;
import weapon.Weapon;

import static org.junit.Assert.assertEquals;

/**
 * This is a test class for Weapon.
 */
public class WeaponTest {
  RandomInterface random;
  Weapon twoHandedSwords;
  Weapon flails;
  Battle battle;
  Player player1;
  Player player2;

  /**
   * Setting up objects for tests.
   */
  @Before
  public void setUp() {
    twoHandedSwords = Weapon.TWOHANDEDSWORDS;
    flails = Weapon.FLAILS;
  }

  /**
   * Test the damage TWOHANDEDSWORDS can cause should be half if the strength of the player < 14.
   */
  @Test
  public void testTWOHANDEDSWORDS() {
    int[] order = {
        6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
        6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
        1, 1, 1, 1, 1,
        2,
        10, 1,
        10,
    };
    random = new PredictableRandom(order);
    battle = new Battle(random);
    player1 = new Player(random);
    player2 = new Player(random);
    player1.setWeapon(battle.createWeapon());
    assertEquals(10, battle.fight(player1, player2), 0.01);

    int[] order2 = {
        4, 4, 4, 4, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
        6, 6, 6, 6, 4, 4, 4, 4, 6, 6, 6, 6, 6, 6, 6, 6,
        1, 1, 1, 1, 1,
        2,
        10, 1,
        10,
    };
    random = new PredictableRandom(order2);
    battle = new Battle(random);
    player1 = new Player(random);
    player2 = new Player(random);
    player1.setWeapon(battle.createWeapon());
    assertEquals(5, battle.fight(player1, player2), 0.01);
  }

  /**
   * Test the damage FLAILS can cause should be half if the dexterity of the player < 14.
   */
  @Test
  public void testFlail() {
    int[] order = {
        6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
        6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
        1, 1, 1, 1, 1,
        4,
        10, 1,
        10,
    };
    random = new PredictableRandom(order);
    battle = new Battle(random);
    player1 = new Player(random);
    player2 = new Player(random);
    player1.setWeapon(battle.createWeapon());
    assertEquals(10, battle.fight(player1, player2), 0.01);

    int[] order2 = {
        6, 6, 6, 6, 6, 6, 6, 6, 3, 3, 3, 3, 6, 6, 6, 6,
        6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
        1, 1, 1, 1, 1,
        4,
        10, 1,
        10,
    };
    random = new PredictableRandom(order2);
    battle = new Battle(random);
    player1 = new Player(random);
    player2 = new Player(random);
    player1.setWeapon(battle.createWeapon());
    assertEquals(5, battle.fight(player1, player2), 0.01);
  }

}