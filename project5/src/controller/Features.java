package controller;

import view.IView;

/**
 * This interface represents a set of features that the program offers. Each
 * feature is exposed as a function in this interface. This function is used
 * suitably as a callback by the view, to pass control to the controller. How
 * the view uses them as callbacks is completely up to how the view is designed
 * (e.g. it could use them as a callback for a button, or a callback for a
 * dialog box, or a set of text inputs, etc.)
 *
 * <p>Each function is designed to take in the necessary data to complete that
 * functionality.
 */
public interface Features {
  /**
   * set view for the controller.
   */
  void setView(IView v);

  /**
   * set the model and begin the game.
   */
  void begin();

  /**
   * exit the program.
   */
  void exitProgram();

  /**
   * set the row of the dungeon.
   *
   * @param row row of the dungeon
   */
  void setRow(int row);

  /**
   * set the column of the dungeon.
   *
   * @param col column of the dungeon
   */
  void setCol(int col);

  /**
   * set the interconnectivity of the dungeon.
   *
   * @param interconnectivity interconnectivity of the dungeon
   */
  void setInterconnectivity(int interconnectivity);

  /**
   * set the wrap of the dungeon.
   *
   * @param wrap whether the dungeon is wrapped
   */
  void setWrap(boolean wrap);

  /**
   * set the percent of the dungeon.
   *
   * @param percent the percent of the dungeon
   */
  void setPercent(int percent);

  /**
   * set the monster number of the dungeon.
   *
   * @param num number of monsters in the dungeon
   */
  void setMonsterNum(int num);

  /**
   * move the player.
   *
   * @param str direction of the movement.
   */
  void move(String str);

  /**
   * pick up treasure or arrows.
   *
   * @param i 1 if diamonds, 2 if SAPPHIRES, 3 if rubies, 4 if arrows
   */
  void pickUp(int i);

  /**
   * player shoots the arrow.
   *
   * @param direction direction of the arrow
   * @param distance  distance of arrow to the player's current location
   */
  void shoot(String direction, int distance);

  /**
   * judge whether the player has arrows.
   *
   * @return true if player has arrows otherwise false
   */
  boolean judgeWhetherHaveArrow();
}
