package battle;

import java.util.ArrayList;
import java.util.List;

import gear.Belt;
import gear.FootWear;
import gear.Gear;
import gear.HeadGear;
import gear.Potion;
import gear.Size;
import player.Player;
import random.RandomInterface;
import weapon.Weapon;

/**
 * Battle class represent a center manger to equip players and make them fight.
 */
public class Battle {
  private final RandomInterface random;

  /**
   * Constructs Battle in terms of random.
   *
   * @param random used to produce random number.
   * @throws IllegalArgumentException if input is null
   */
  public Battle(RandomInterface random) {
    if (random == null) {
      throw new IllegalArgumentException("random could not be null.");
    }
    this.random = random;
  }

  /**
   * Create gear based on random number.
   *
   * @return a List of random number
   */
  private List<Gear> createGearHelper() {
    List<Gear> res = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      if (i < 3) {
        res.add(new HeadGear(String.format("Headgear%d", i + 1), random.getRandom(1, 3)));
        res.add(new FootWear(String.format("Footwear%d", i + 1), random.getRandom(1, 3)));
      } else {
        res.add(new HeadGear(String.format("Headgear%d", i + 1), -random.getRandom(1, 3)));
        res.add(new FootWear(String.format("Footwear%d", i + 1), -random.getRandom(1, 3)));
      }
    }

    for (int j = 0; j < 5; j++) {
      Belt belt;
      if (j < 4) {
        belt = Belt.getBuilder().name(String.format("Belt%d", j + 1)).size(Size.SMALL)
                .affectCharisma(random.getRandom(1, 3))
                        .affectStrength(random.getRandom(1, 3)).build();
      } else {
        belt = Belt.getBuilder().name(String.format("Belt%d", j + 1)).size(Size.SMALL)
                .affectCharisma(-random.getRandom(1, 3))
                        .affectStrength(-random.getRandom(1, 3)).build();
      }
      res.add(belt);
    }

    for (int j = 5; j < 10; j++) {
      Belt belt;
      if (j < 9) {
        belt = Belt.getBuilder().name(String.format("Belt%d", j + 1)).size(Size.MEDIUM)
                .affectDexterity(random.getRandom(1, 3))
                        .affectConstitution(random.getRandom(1, 3)).build();
      } else {
        belt = Belt.getBuilder().name(String.format("Belt%d", j + 1)).size(Size.MEDIUM)
                .affectDexterity(-random.getRandom(1, 3))
                        .affectConstitution(-random.getRandom(1, 3)).build();
      }
      res.add(belt);
    }

    for (int j = 10; j < 15; j++) {
      Belt belt;
      if (j < 14) {
        belt = Belt.getBuilder().name(String.format("Belt%d", j + 1)).size(Size.LARGE)
                .affectConstitution(random.getRandom(1, 3))
                        .affectStrength(random.getRandom(1, 3)).build();
      } else {
        belt = Belt.getBuilder().name(String.format("Belt%d", j + 1)).size(Size.LARGE)
                .affectConstitution(-random.getRandom(1, 3))
                        .affectStrength(-random.getRandom(1, 3)).build();
      }
      res.add(belt);
    }

    for (int k = 0; k < 15; k++) {
      Potion potion;
      if (k < 12) {
        potion = Potion.getBuilder().name(String.format("Potion%d", k + 1))
                        .affectStrength(random.getRandom(1, 3))
                .affectConstitution(random.getRandom(1, 3))
                        .affectDexterity(random.getRandom(1, 3))
                .affectCharisma(random.getRandom(1, 3)).builder();
      } else {
        potion = Potion.getBuilder().name(String.format("Potion%d", k + 1))
                        .affectStrength(-random.getRandom(1, 3))
                .affectConstitution(-random.getRandom(1, 3))
                        .affectDexterity(-random.getRandom(1, 3))
                .affectCharisma(-random.getRandom(1, 3)).builder();
      }
      res.add(potion);
    }
    return res;
  }

  /**
   * Create weapons based on random number.
   *
   * @return a List of weapons
   */
  private List<Weapon> createWeaponHelper() {
    List<Weapon> res = new ArrayList<>();
    for (Weapon weapon : Weapon.values()) {
      if (weapon == Weapon.KATANASPAIR) {
        continue;
      }
      int k = random.getRandom(1, 3);
      for (int i = 0; i < k; i++) {
        res.add(weapon);
      }
    }
    return res;
  }

  /**
   * Create gear for players to choose.
   *
   * @return a List of gear
   */
  public List<Gear> createGear() {
    return createGearHelper();
  }

  /**
   * Create weapon for players to choose.
   *
   * @return a List of weapons
   */
  public List<Weapon> createWeapon() {
    return createWeaponHelper();
  }

  /**
   * Calculate how much damage a weapon can create.
   *
   * @param weapon the weapon of the player
   * @param strength the strength of the player
   * @param dexterity the dexterity of the player
   * @return the value of damage
   */
  private double weaponDamage(Weapon weapon, int strength, int dexterity) {
    if (weapon == null) {
      return 0;
    }

    if (weapon == Weapon.KATANAS) {
      return random.getRandom(4, 6);
    } else if (weapon == Weapon.KATANASPAIR) {
      return random.getRandom(8, 12);
    } else if (weapon == Weapon.BROADSWORDS || weapon == Weapon.AXES) {
      return random.getRandom(6, 10);
    } else if (weapon == Weapon.TWOHANDEDSWORDS) {
      if (strength > 14) {
        return random.getRandom(8, 12);
      } else {
        return (double) random.getRandom(8, 12) / 2;
      }
    } else {
      if (dexterity > 14) {
        return random.getRandom(8, 12);
      } else {
        return (double) random.getRandom(8, 12) / 2;
      }
    }

  }

  /**
   * Make one player attack the other.
   *
   * @param player1 the attacker
   * @param player2 the player being attacked
   * @return the value of damage if attack doesn't succeed, the value is 0
   */
  public double fight(Player player1, Player player2) {
    int strikingPower = player1.getStrength() + random.getRandom(1, 10);
    int avoidance = player2.getDexterity() + random.getRandom(1, 6);
    if (strikingPower > avoidance) {
      double actualDamage = player1.getStrength() + weaponDamage(player1.getWeapon(),
              player1.getStrength(), player1.getDexterity()) - player2.getConstitution();
      if (actualDamage > 0) {
        player2.setHealth(actualDamage);
        return actualDamage;
      }
    }
    return 0;
  }

  /**
   * Judge who should attack first based on the charisma.
   *
   * @param player1 the first player
   * @param player2 the second player
   * @return 1 represents the first player while 2 represents the second player
   */
  public int whoFirstAttack(Player player1, Player player2) {
    if (player1.getCharisma() > player2.getCharisma()) {
      return 1;
    } else if (player1.getCharisma() < player2.getCharisma()) {
      return 2;
    } else {
      return random.getRandom(1, 2);
    }
  }

  /**
   * Calculate the maximum damage a weapon can cause.
   *
   * @param weapon the weapon of the player
   * @param strength the strength of the player
   * @param dexterity the dexterity of the player
   * @return the value of damage
   */
  private int judgeWhetherHitCanOccurHelper(Weapon weapon, int strength, int dexterity) {
    if (weapon == null) {
      return 0;
    }

    if (weapon == Weapon.KATANAS) {
      return 6;
    } else if (weapon == Weapon.KATANASPAIR) {
      return 12;
    } else if (weapon == Weapon.BROADSWORDS || weapon == Weapon.AXES) {
      return 10;
    } else if (weapon == Weapon.TWOHANDEDSWORDS) {
      if (strength > 14) {
        return 12;
      } else {
        return 6;
      }
    } else {
      if (dexterity > 14) {
        return 12;
      } else {
        return 6;
      }
    }
  }

  /**
   * Judge whether a hit can occur.
   *
   * @param player1 the first player
   * @param player2 the second player
   * @return true represent a hit can occur, vice versa
   */
  public boolean judgeWhetherHitCanOccur(Player player1, Player player2) {
    if ((player1.getStrength() + judgeWhetherHitCanOccurHelper(player1.getWeapon(),
            player1.getStrength(), player1.getDexterity()) - player2.getConstitution() <= 0)
            && (player2.getStrength() + judgeWhetherHitCanOccurHelper(player2.getWeapon(),
                    player2.getStrength(), player2.getDexterity()) - player1.getConstitution() <= 0)
    ) {
      return false;
    }
    return (player1.getStrength() - player2.getDexterity() > -9)
            || player2.getStrength() - player1.getDexterity() > -9;

  }
}
