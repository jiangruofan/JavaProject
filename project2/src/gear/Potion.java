package gear;

/**
 * This class represents the potion.
 */
public class Potion extends Gear {
  /**
   * Construct the Potion in terms of affectStrength, affectConstitution, affectDexterity,
   * affectCharisma and name.
   *
   * @param affectStrength     how much strength it can affect
   * @param affectConstitution how much constitution it can affect
   * @param affectDexterity    how much dexterity it can affect
   * @param affectCharisma     how much charisma it can affect
   * @param name the name of the potion
   * @throws IllegalArgumentException if the input is invalid
   */
  private Potion(int affectStrength, int affectConstitution, int affectDexterity,
                 int affectCharisma, String name) {
    super(name, affectStrength, affectConstitution, affectDexterity, affectCharisma);
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
    if (i != 0) {
      throw new IllegalArgumentException("Potion should affect all the attribute.");
    }
  }

  /**
   * Get the PotionBuilder object.
   *
   * @return the PotionBuilder object
   */
  public static PotionBuilder getBuilder() {
    return new PotionBuilder();
  }

  /**
   * This class is used to build a potion object.
   */
  public static class PotionBuilder {
    private int affectStrength;
    private int affectConstitution;
    private int affectDexterity;
    private int affectCharisma;
    private String name;

    /**
     * Construct PotionBuilder in terms of affectStrength, affectConstitution, affectDexterity,
     * affectCharisma and name.
     */
    public PotionBuilder() {
      this.affectStrength = 0;
      this.affectConstitution = 0;
      this.affectDexterity = 0;
      this.affectCharisma = 0;
      this.name = null;
    }

    /**
     * Set the affectStrength value.
     *
     * @param affectStrength how much strength it can affect
     * @return the value of the strength
     */
    public PotionBuilder affectStrength(int affectStrength) {
      this.affectStrength = affectStrength;
      return this;
    }

    /**
     * Set the affectConstitution value.
     *
     * @param affectConstitution how much constitution it can affect
     * @return the value of the constitution
     */
    public PotionBuilder affectConstitution(int affectConstitution) {
      this.affectConstitution = affectConstitution;
      return this;
    }

    /**
     * Set the affectDexterity value.
     *
     * @param affectDexterity how much dexterity it can affect
     * @return the value of the dexterity
     */
    public PotionBuilder affectDexterity(int affectDexterity) {
      this.affectDexterity = affectDexterity;
      return this;
    }

    /**
     * Set the affectCharisma value.
     *
     * @param affectCharisma how much Charisma it can affect
     * @return the value of the charisma
     */
    public PotionBuilder affectCharisma(int affectCharisma) {
      this.affectCharisma = affectCharisma;
      return this;
    }

    /**
     * Set the name of the belt.
     *
     * @param name the name of the belt
     * @return the name of the belt
     */
    public PotionBuilder name(String name) {
      this.name = name;
      return this;
    }

    /**
     * Get the Potion object.
     *
     * @return the Potion object
     */
    public Potion builder() {
      return new Potion(affectStrength, affectConstitution, affectDexterity, affectCharisma, name);
    }
  }
}
