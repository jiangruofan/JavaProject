package manager;

import java.util.List;
import java.util.Map;

/**
 * represent a manager for dungeon and player.
 */
public interface ManagerInterface {
  /**
   * judge whether the game is end.
   *
   * @return 2 if the player gets the end point, 3 if the player is eaten otherwise 1
   */
  int judgeEnd();

  /**
   * get the player's location.
   *
   * @return the player's location
   */
  int[] getPlayerLoc();

  /**
   * get the player's description.
   *
   * @return player's description including the treasure and arrows he has collected
   */
  Map<String, Integer> getPlayerDescription();

  /**
   * get a description of the room.
   *
   * @return a list of string representing the valid move direction, treasure and arrow in the room
   */
  List<String> getLocDescription();

  /**
   * get the player's location.
   *
   * @return the player's location
   */
  int getPlayerLoc1();

  /**
   * judge whether there is treasure in the room.
   *
   * @return true if there is otherwise false
   */
  boolean whetherHaveTreasure();

  /**
   * pick up the treasure in the room.
   *
   * @param str the name of treasure
   * @return 3 if successfully pick up the treasure,
   *         2 if you don't want to pick up treasure otherwise 1
   */
  int pickUpTreasure(String str);

  /**
   * update the treasure in the room.
   *
   * @param str the name of the treasure
   */
  void updateTreasure(String str);

  /**
   * move the player.
   *
   * @param str the direction of move
   * @return true if move successfully other false
   */
  boolean move(String str);

  /**
   * pick up an arrow for player.
   *
   * @param str "yes" if you want to pick up
   * @return true if player successfully picks up an arrow otherwise false
   */
  boolean pickUpArrow(String str);

  /**
   * judge whether a room contains an arrow.
   *
   * @return true if there is an arrow otherwise false
   */
  boolean judgeWhetherHaveArrow();

  /**
   * judge whether a player has arrows.
   *
   * @return yes if the player has arrows otherwise false
   */
  boolean judgeWhetherHaveArrow1();

  /**
   * shoot the monster.
   *
   * @param str direction and instance
   * @return 3 if invalid input, 1 if successfully hit the monster otherwise 2, 4 if quit shoot
   */
  int shot(String str);

  /**
   * detect the smell of the monsters around.
   *
   * @return 0 if there is no monster around, 1 if there is a less pungent smell otherwise 2
   */
  int detectMonster();
}
