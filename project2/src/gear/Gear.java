package gear;

import java.util.Objects;

/**
 * This class represents gear that can be used by players.
 */
public class Gear implements GearInterface {
  private final String name;
  private final int affectStrength;
  private final int affectConstitution;
  private final int affectDexterity;
  private final int affectCharisma;

  /**
   * Construct Gear in terms of name, affectStrength, affectConstitution, affectDexterity
   * and affectCharisma.
   *
   * @param name               the name of the gear
   * @param affectStrength     how much strength it can affect
   * @param affectConstitution how much constitution it can affect
   * @param affectDexterity    how much dexterity it can affect
   * @param affectCharisma     how much charisma it can affect
   * @throws IllegalArgumentException if the name is empty
   */
  public Gear(String name, int affectStrength, int affectConstitution, int affectDexterity,
              int affectCharisma) {
    if (name == null || name.length() < 1) {
      throw new IllegalArgumentException("Name could not be empty.");
    }
    this.name = name;
    this.affectStrength = affectStrength;
    this.affectConstitution = affectConstitution;
    this.affectDexterity = affectDexterity;
    this.affectCharisma = affectCharisma;
  }

  @Override
  public int[] getEffect() {
    return new int[]{affectStrength, affectConstitution, affectDexterity, affectCharisma};
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Gear gear = (Gear) o;
    return affectStrength == gear.affectStrength && affectConstitution == gear.affectConstitution
            && affectDexterity == gear.affectDexterity && affectCharisma == gear.affectCharisma
            && name.equals(gear.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, affectStrength, affectConstitution, affectDexterity, affectCharisma);
  }
}
