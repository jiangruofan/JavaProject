package gear;

/**
 * This class represents Footwear.
 */
public class FootWear extends Gear {
  /**
   * Construct FootWear in terms of name and affectDexterity.
   *
   * @param name            the name of the footwear
   * @param affectDexterity how much dexterity it can affect
   * @throws IllegalArgumentException if input values are zero
   */
  public FootWear(String name, int affectDexterity) {
    super(name, 0, 0, affectDexterity, 0);
    if (affectDexterity == 0) {
      throw new IllegalArgumentException("It should affect attribute positively or negatively.");
    }
  }
}
