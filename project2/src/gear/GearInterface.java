package gear;

/**
 * This interface represents the gear.
 * A gear can affect players' abilities and each gear has a name.
 */
public interface GearInterface {
  /**
   * Get hou much the gear can affect the 4 abilities.
   *
   * @return an int array of values that gear can impact
   */
  int[] getEffect();

  /**
   * Get the name of the gear.
   *
   * @return the name of the gear
   */
  String getName();
}
