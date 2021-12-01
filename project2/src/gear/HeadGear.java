package gear;

/**
 * This class represents headgear.
 */
public class HeadGear extends Gear {
  /**
   * Construct HearGear in terms of name and affectConstitution.
   *
   * @param name               the name of the headgear
   * @param affectConstitution how much constitution it can affect
   * @throws IllegalArgumentException if the input is zero
   */
  public HeadGear(String name, int affectConstitution) {
    super(name, 0, affectConstitution, 0, 0);
    if (affectConstitution == 0) {
      throw new IllegalArgumentException("It should affect attribute positively or negatively.");
    }
  }
}
