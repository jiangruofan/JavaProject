package view;

import controller.Features;

import java.util.List;
import java.util.Map;

/**
 * The interface for our view class.
 */
public interface IView {

  /**
   * Get the set of feature callbacks that the view can use.
   *
   * @param f the set of feature callbacks as a Features object
   */
  void setFeatures(Features f);

  /**
   * set the row and column of the dungeon that will be shown.
   *
   * @param row the row of the dungeon
   * @param col the column of the dungeon
   */
  void createDungeon(int row, int col);

  /**
   * show a message that the dungeon could not be created.
   */
  void createDungeonFail();

  /**
   * update the dungeon that is being shown.
   *
   * @param loc       the location of the new room
   * @param direction the neighbors of the new room
   */
  void updateDungeon(int loc, String direction);

  /**
   * Reset the focus on the appropriate part of the view that has the keyboard
   * listener attached to it, so that keyboard events will still flow through.
   */
  void resetFocus();

  /**
   * show a message that the game ends.
   *
   * @param i 2 if player reached the end and 3 if player is eaten
   */
  void gameEnd(int i);

  /**
   * show what is in the room.
   *
   * @param list list of items in the room
   */
  void showObject(List<Integer> list);

  /**
   * set the label that is showing the location of the player and the smell of monsters.
   *
   * @param i location of the player
   * @param j the smell of the monster
   */
  void setJLabel(int[] i, int j);

  /**
   * show the player's information including what treasure he has collected.
   *
   * @param input the treasure the player has collected
   */
  void playerInformation(Map<String, Integer> input);

  /**
   * show the message whether the player hits a monster.
   *
   * @param i 0 if player has no arrow, 1 if players don't hit monster otherwise 2
   */
  void shoot(int i);
}
