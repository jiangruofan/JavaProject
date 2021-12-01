package gear;

import java.util.Objects;

/**
 * This class represent the belt.
 */
public class Belt extends Gear {
  private final int size;

  /**
   * Construct the belt in terms of affectStrength, affectConstitution, affectDexterity,
   * affectCharisma, size, name.
   *
   * @param affectStrength     how much strength it can affect
   * @param affectConstitution how much constitution it can affect
   * @param affectDexterity    how much dexterity it can affect
   * @param affectCharisma     how much charisma it can affect
   * @param size               the size of the belt
   * @param name               the name of the belt
   * @throws IllegalArgumentException if input values are invalid
   */
  private Belt(int affectStrength, int affectConstitution, int affectDexterity, int affectCharisma,
               Size size, String name) {
    super(name, affectStrength, affectConstitution, affectDexterity, affectCharisma);
    if (size == null) {
      throw new IllegalArgumentException("Size could not be null.");
    }
    int i = 0;
    if (affectStrength == 0) {
      i++;
    }
    if (affectConstitution == 0) {
      i++;
    }
    if (affectDexterity == 0) {
      i++;
    }
    if (affectCharisma == 0) {
      i++;
    }
    if (i != 2) {
      throw new IllegalArgumentException("Belt should affect 2 abilities.");
    }
    if (size == Size.SMALL) {
      this.size = 1;
    } else if (size == Size.MEDIUM) {
      this.size = 2;
    } else {
      this.size = 4;
    }
  }

  /**
   * Get the size of the belt.
   *
   * @return the size of the belt
   */
  public int getSize() {
    return size;
  }

  /**
   * Get the belt builder.
   *
   * @return the beltBuilder object
   */
  public static BeltBuilder getBuilder() {
    return new BeltBuilder();
  }

  /**
   * This is a class used to create belt.
   */
  public static class BeltBuilder {
    private int affectStrength;
    private int affectConstitution;
    private int affectDexterity;
    private int affectCharisma;
    private Size size;
    private String name;

    /**
     * Construct the BeltBuilder in terms of affectStrength, affectConstitution, affectDexterity,
     * affectCharisma, size and name.
     */
    public BeltBuilder() {
      this.affectStrength = 0;
      this.affectConstitution = 0;
      this.affectDexterity = 0;
      this.affectCharisma = 0;
      this.size = null;
      name = null;
    }

    /**
     * Set the affectStrength value.
     *
     * @param affectStrength how much strength it can affect
     * @return the value of the strength
     */
    public BeltBuilder affectStrength(int affectStrength) {
      this.affectStrength = affectStrength;
      return this;
    }

    /**
     * Set the affectConstitution value.
     *
     * @param affectConstitution how much constitution it can affect
     * @return the value of the constitution
     */
    public BeltBuilder affectConstitution(int affectConstitution) {
      this.affectConstitution = affectConstitution;
      return this;
    }

    /**
     * Set the affectDexterity value.
     *
     * @param affectDexterity how much dexterity it can affect
     * @return the value of the dexterity
     */
    public BeltBuilder affectDexterity(int affectDexterity) {
      this.affectDexterity = affectDexterity;
      return this;
    }

    /**
     * Set the affectCharisma value.
     *
     * @param affectCharisma how much Charisma it can affect
     * @return the value of the charisma
     */
    public BeltBuilder affectCharisma(int affectCharisma) {
      this.affectCharisma = affectCharisma;
      return this;
    }

    /**
     * Set the size of the belt.
     *
     * @param size the size of the belt
     * @return the size of the belt
     */
    public BeltBuilder size(Size size) {
      this.size = size;
      return this;
    }

    /**
     * Set the name of the belt.
     *
     * @param name the name of the belt
     * @return the name of the belt
     */
    public BeltBuilder name(String name) {
      this.name = name;
      return this;
    }

    /**
     * Get the Belt object.
     *
     * @return the belt object
     */
    public Belt build() {
      return new Belt(affectStrength, affectConstitution, affectDexterity, affectCharisma,
              size, name);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Belt)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Belt belt = (Belt) o;
    return size == belt.size;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), size);
  }
}
