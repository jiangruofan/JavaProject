package player;

import treasure.Treasure;

import java.util.HashMap;
import java.util.Map;

/**
 * represent a player with arrows.
 * the player can pick up arrow and shoot monsters by arrows.
 */
public class PlayerWithArrow extends Player {
  private int arrow;

  /**
   * construct the player in terms of location.
   *
   * @param location the location of the player
   */
  public PlayerWithArrow(int[] location) {
    super(location);
    arrow = 3;
  }

  /**
   * get the number of arrow.
   *
   * @return the number of arrow
   */
  public int getArrowNum() {
    return arrow;
  }

  /**
   * pick up an arrow.
   */
  public void pickUpArrow() {
    arrow += 1;
  }

  /**
   * if the player try to hit a monster, the arrow number should decrease 1.
   */
  public void updateArrow() {
    if (arrow == 0) {
      throw new IllegalStateException("there is no arrow already");
    }
    arrow -= 1;
  }

  /**
   * get the description of the player including arrow.
   *
   * @return a hashmap of treasure and arrow and their number
   */
  public Map<String, Integer> getDescription1() {
    Map<String, Integer> res = new HashMap<>();
    for (Treasure treasure : getDescription().keySet()) {
      res.put(treasure.name(), getDescription().get(treasure));
    }
    res.put("Arrow", arrow);
    return res;
  }

}
