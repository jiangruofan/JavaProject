package player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import gear.Belt;
import gear.FootWear;
import gear.Gear;
import gear.HeadGear;
import gear.Potion;
import random.RandomInterface;
import weapon.Weapon;

/**
 * This class represents player which includes their 4 basic ability and the gear they have.
 */
public class Player {
  private final RandomInterface random;
  private int strength;
  private int constitution;
  private int dexterity;
  private int charisma;
  private final List<String> potions;
  private final List<String> belts;
  private String headGear;
  private String footWear;
  private Weapon weapon;
  private int health;

  /**
   * Construct Player in terms of random.
   *
   * @param random used to produce random number
   * @throws IllegalArgumentException if input is null
   */
  public Player(RandomInterface random) {
    if (random == null) {
      throw new IllegalArgumentException("random could not be null.");
    }
    this.random = random;
    this.strength = constructorHelper();
    this.constitution = constructorHelper();
    this.dexterity = constructorHelper();
    this.charisma = constructorHelper();
    potions = new ArrayList<>();
    belts = new ArrayList<>();
    health = constitution + charisma + dexterity + strength;
  }

  /**
   * Set the 4 basic ability by rolling four 6-sided dice, re-rolling any 1s, and then adding.
   * together the highest 3 values resulting in a value between 6 and 18.
   *
   * @return the value of ability
   */
  private int constructorHelper() {
    int[] res = new int[4];
    int i;
    int j = 0;
    do {
      i = random.getRandom(1, 6);
      if (i != 1) {
        res[j++] = i;
      }
    }
    while (j != 4);
    Arrays.sort(res);
    int result = 0;
    for (int k = 1; k < 4; k++) {
      result += res[k];
    }
    return result;
  }

  /**
   * Update the four ability.
   *
   * @param gear the gear of the player
   */
  private void updateAttribute(Gear gear) {
    strength += gear.getEffect()[0];
    constitution += gear.getEffect()[1];
    dexterity += gear.getEffect()[2];
    charisma += gear.getEffect()[3];
  }

  /**
   * Set the equipment of the player.
   *
   * @param gear a List of gear
   * @throws IllegalArgumentException if input is null
   */
  public void setEquipment(List<Gear> gear) {
    if (gear == null) {
      throw new IllegalArgumentException("input could not be null");
    }
    int size = 0;
    List<Integer> save = new ArrayList<>();
    int i;
    while (save.size() != 20) {
      i = random.getRandom(0, gear.size() - 1);
      if (!save.contains(i)) {
        save.add(i);
        if (gear.get(i) instanceof HeadGear && headGear == null) {
          headGear = gear.get(i).getName();
        } else if (gear.get(i) instanceof FootWear && footWear == null) {
          footWear = gear.get(i).getName();
        } else if (gear.get(i) instanceof Potion) {
          potions.add(gear.get(i).getName());
        } else if (gear.get(i) instanceof Belt && size <= 10) {
          belts.add(gear.get(i).getName());
          size += ((Belt) gear.get(i)).getSize();
        }
        updateAttribute(gear.get(i));
      }
    }
    health = constitution + charisma + dexterity + strength;
  }

  /**
   * Set the weapon of the player.
   *
   * @param weapon a List of weapon
   * @throws IllegalArgumentException if input is null
   */
  public void setWeapon(List<Weapon> weapon) {
    if (weapon == null) {
      throw new IllegalArgumentException("input could not be null.");
    }
    int i = random.getRandom(0, weapon.size() - 1);
    this.weapon = weapon.get(i);
    if (this.weapon == Weapon.KATANAS) {
      int j = random.getRandom(0, weapon.size() - 1);
      while (j == i) {
        j = random.getRandom(0, weapon.size() - 1);
      }
      if (weapon.get(j) == Weapon.KATANAS) {
        this.weapon = Weapon.KATANASPAIR;
      }
    }
  }

  /**
   * Produce a description for the player.
   *
   * @return a List of their basic ability, gear and weapon
   */
  public List<String> produceDescription() {
    List<String> res = new ArrayList<>();
    res.add(String.valueOf(strength));
    res.add(String.valueOf(constitution));
    res.add(String.valueOf(dexterity));
    res.add(String.valueOf(charisma));
    if (headGear != null) {
      res.add(headGear);
    }
    if (potions != null) {
      Collections.sort(potions);
      res.addAll(potions);
    }
    if (belts != null) {
      Collections.sort(belts);
      res.addAll(belts);
    }
    if (footWear != null) {
      res.add(footWear);
    }
    if (weapon != null) {
      res.add(weapon.name());
    }
    return res;
  }

  /**
   * Get the health of the player.
   *
   * @return the health of the player
   */
  public int getHealth() {
    return health;
  }

  /**
   * Set the health of the player after being attacked.
   *
   * @param damage the damage the player gets
   */
  public void setHealth(double damage) {
    health -= damage;
  }

  /**
   * Set the health to its initial value.
   */
  public void setHealth() {
    health = constitution + charisma + dexterity + strength;
  }

  /**
   * Get the strength of the player.
   *
   * @return the strength of the player
   */
  public int getStrength() {
    return strength;
  }

  /**
   * Get the dexterity of the player.
   *
   * @return the dexterity of the player
   */
  public int getDexterity() {
    return dexterity;
  }

  /**
   * Get the constitution of the player.
   *
   * @return the constitution of the player
   */
  public int getConstitution() {
    return constitution;
  }

  /**
   * Get the charisma of the player.
   *
   * @return the charisma of the player
   */
  public int getCharisma() {
    return charisma;
  }

  /**
   * Get the weapon of the player.
   *
   * @return the weapon of the player
   */
  public Weapon getWeapon() {
    return weapon;
  }

  /**
   * Judge whether the player is dead.
   *
   * @return true if the player is dead otherwise false
   */
  public boolean judgeDeath() {
    return health <= 0;
  }
}
