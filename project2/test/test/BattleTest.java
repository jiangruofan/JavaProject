package test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import battle.Battle;
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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This is a test class for Battle.
 */
public class BattleTest {
  RandomInterface random;
  Battle battle;
  Player player1;
  Player player2;

  /**
   * Test the random input could not be null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    battle = new Battle(null);
  }

  /**
   * Test create the gear for the player.
   */
  @Test
  public void testCreateGear() {
    int[] order = {
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,

    };
    random = new PredictableRandom(order);
    battle = new Battle(random);
    List<Gear> res = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      if (i < 3) {
        res.add(new HeadGear(String.format("Headgear%d", i + 1), 1));
        res.add(new FootWear(String.format("Footwear%d", i + 1), 1));
      } else {
        res.add(new HeadGear(String.format("Headgear%d", i + 1), -1));
        res.add(new FootWear(String.format("Footwear%d", i + 1), -1));
      }
    }

    for (int j = 0; j < 5; j++) {
      Belt belt;
      if (j < 4) {
        belt = Belt.getBuilder().name(String.format("Belt%d", j + 1)).size(Size.SMALL)
                .affectCharisma(1).affectStrength(1).build();
      } else {
        belt = Belt.getBuilder().name(String.format("Belt%d", j + 1)).size(Size.SMALL)
                .affectCharisma(-1).affectStrength(-1).build();
      }
      res.add(belt);
    }

    for (int j = 5; j < 10; j++) {
      Belt belt;
      if (j < 9) {
        belt = Belt.getBuilder().name(String.format("Belt%d", j + 1)).size(Size.MEDIUM)
                .affectDexterity(1).affectConstitution(1).build();
      } else {
        belt = Belt.getBuilder().name(String.format("Belt%d", j + 1)).size(Size.MEDIUM)
                .affectDexterity(-1).affectConstitution(-1).build();
      }
      res.add(belt);
    }

    for (int j = 10; j < 15; j++) {
      Belt belt;
      if (j < 14) {
        belt = Belt.getBuilder().name(String.format("Belt%d", j + 1)).size(Size.LARGE)
                .affectConstitution(1).affectStrength(1).build();
      } else {
        belt = Belt.getBuilder().name(String.format("Belt%d", j + 1)).size(Size.LARGE)
                .affectConstitution(-1).affectStrength(-1).build();
      }
      res.add(belt);
    }
    for (int k = 0; k < 15; k++) {
      Potion potion;
      if (k < 12) {
        potion = Potion.getBuilder().name(String.format("Potion%d", k + 1)).affectStrength(1)
                .affectConstitution(1).affectDexterity(1)
                .affectCharisma(1).builder();
      } else {
        potion = Potion.getBuilder().name(String.format("Potion%d", k + 1)).affectStrength(-1)
                .affectConstitution(-1).affectDexterity(-1)
                .affectCharisma(-1).builder();
      }
      res.add(potion);
    }
    assertEquals(res, battle.createGear());
  }

  /**
   * Test create weapon for players.
   */
  @Test
  public void testCreateWeapon() {
    int[] order = {
        2, 2, 2, 2, 2,
    };
    random = new PredictableRandom(order);
    battle = new Battle(random);
    List<Weapon> res = new ArrayList<>();
    for (Weapon weapon : Weapon.values()) {
      if (weapon == Weapon.KATANASPAIR) {
        continue;
      }
      int k = 2;
      for (int i = 0; i < k; i++) {
        res.add(weapon);
      }
    }
    assertEquals(res, battle.createWeapon());
  }

  /**
   * Test one player causes really damage to the other with weapon.
   */
  @Test
  public void testFight() {
    int[] order = {
        6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
        6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
        1, 1, 1, 1, 1,
        1,
        10, 1,
        10,
    };
    random = new PredictableRandom(order);
    battle = new Battle(random);
    player1 = new Player(random);
    player2 = new Player(random);
    player1.setWeapon(battle.createWeapon());
    assertEquals(10, battle.fight(player1, player2), 0.01);
    assertEquals(62, player2.getHealth());
  }

  /**
   * Test one player causes really damage to the other without weapon.
   */
  @Test
  public void testFight2() {
    int[] order = {
        6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
        6, 6, 6, 6, 2, 2, 2, 2, 6, 6, 6, 6, 6, 6, 6, 6,
        10, 1,
    };
    random = new PredictableRandom(order);
    battle = new Battle(random);
    player1 = new Player(random);
    player2 = new Player(random);
    assertEquals(12, battle.fight(player1, player2), 0.01);
    assertEquals(48, player2.getHealth());
  }

  /**
   * Test one player' striking power is less than the other's avoidance ability.
   */
  @Test
  public void testFight3() {
    int[] order = {
        6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
        6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
        1, 1, 1, 1, 1,
        1,
        1, 10,
    };
    random = new PredictableRandom(order);
    battle = new Battle(random);
    player1 = new Player(random);
    player2 = new Player(random);
    player1.setWeapon(battle.createWeapon());
    assertEquals(0, battle.fight(player1, player2), 0.01);
    assertEquals(72, player2.getHealth());
  }

  /**
   * Test one player' striking power is larger than the other's avoidance ability.
   * but the actual damage <= 0.
   * the player has weapon.
   */
  @Test
  public void testFight4() {
    int[] order = {
        2, 2, 2, 3, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
        6, 6, 6, 6, 6, 6, 6, 6, 2, 2, 2, 2, 6, 6, 6, 6,
        1, 1, 1, 1, 1,
        1,
        10, 1,
        6,
    };
    random = new PredictableRandom(order);
    battle = new Battle(random);
    player1 = new Player(random);
    player2 = new Player(random);
    player1.setWeapon(battle.createWeapon());
    assertEquals(0, battle.fight(player1, player2), 0.01);
    assertEquals(60, player2.getHealth());
  }

  /**
   * Test one player' striking power is LARGEr than the other's avoidance ability.
   * but the actual damage <= 0.
   * the player has no weapon.
   */
  @Test
  public void testFight5() {
    int[] order = {
        2, 2, 2, 3, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
        6, 6, 6, 6, 6, 6, 6, 6, 2, 2, 2, 2, 6, 6, 6, 6,
        10, 1,
    };
    random = new PredictableRandom(order);
    battle = new Battle(random);
    player1 = new Player(random);
    player2 = new Player(random);
    assertEquals(0, battle.fight(player1, player2), 0.01);
    assertEquals(60, player2.getHealth());
  }

  /**
   * Test who first attack.
   * One player's charisma is larger than the other player.
   */
  @Test
  public void testWhoFirstAttack() {
    int[] order = {
        2, 2, 2, 3, 6, 6, 6, 6, 6, 6, 6, 6, 5, 5, 6, 6,
        6, 6, 6, 6, 6, 6, 6, 6, 2, 2, 2, 2, 6, 6, 6, 6,
    };
    random = new PredictableRandom(order);
    battle = new Battle(random);
    player1 = new Player(random);
    player2 = new Player(random);
    assertEquals(2, battle.whoFirstAttack(player1, player2));
    assertEquals(1, battle.whoFirstAttack(player2, player1));
  }

  /**
   * Test who first attack.
   * One player's charisma equals than the other player.
   */
  @Test
  public void testWhoFirstAttack2() {
    int[] order = {
        2, 2, 2, 3, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
        6, 6, 6, 6, 6, 6, 6, 6, 2, 2, 2, 2, 6, 6, 6, 6,
        1, 2
    };
    random = new PredictableRandom(order);
    battle = new Battle(random);
    player1 = new Player(random);
    player2 = new Player(random);
    assertEquals(1, battle.whoFirstAttack(player1, player2));
    assertEquals(2, battle.whoFirstAttack(player2, player1));
  }

  /**
   * Test whether a hit can occur.
   * A hit can occur in this way.
   */
  @Test
  public void testJudgeWhetherHitCanOccur() {
    int[] order = {
        6, 6, 6, 6, 5, 5, 6, 6, 5, 5, 6, 6, 6, 6, 6, 6,
        6, 6, 6, 6, 5, 5, 6, 6, 5, 5, 6, 6, 6, 6, 6, 6,
    };
    random = new PredictableRandom(order);
    battle = new Battle(random);
    player1 = new Player(random);
    player2 = new Player(random);
    assertTrue(battle.judgeWhetherHitCanOccur(player1, player2));
  }

  /**
   * Test whether a hit can occur.
   * strikingPower <= avoidance for both players no matter what random is.
   */
  @Test
  public void testJudgeWhetherHitCanOccur2() {
    int[] order = {
        2, 2, 2, 2, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
        2, 2, 2, 2, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
    };
    random = new PredictableRandom(order);
    battle = new Battle(random);
    player1 = new Player(random);
    player2 = new Player(random);
    assertFalse(battle.judgeWhetherHitCanOccur(player1, player2));
  }

  /**
   * Test whether a hit can occur.
   * actual damage <= 0 for both players no matter what random number of weapon damage is.
   * player1 has a weapon while player has no weapon.
   */
  @Test
  public void testJudgeWhetherHitCanOccur3() {
    int[] order = {
        2, 2, 2, 2, 6, 6, 6, 6, 2, 2, 2, 2, 6, 6, 6, 6,
        2, 2, 2, 2, 6, 6, 6, 6, 2, 2, 2, 2, 6, 6, 6, 6,
        1, 1, 1, 1, 1,
        1
    };
    random = new PredictableRandom(order);
    battle = new Battle(random);
    player1 = new Player(random);
    player2 = new Player(random);
    player1.setWeapon(battle.createWeapon());
    assertFalse(battle.judgeWhetherHitCanOccur(player1, player2));
  }
}
