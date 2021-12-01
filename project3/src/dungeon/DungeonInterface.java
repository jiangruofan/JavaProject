package dungeon;

import treasure.Treasure;

import java.util.List;

/**
 * This interface represents dungeon.
 */
public interface DungeonInterface {
  /**
   * get the 2D view of the dungeon.
   *
   * @return an array of string representing the dungeon
   */
  String[][] showMap();

  /**
   * get the start point for player.
   *
   * @return an array of three value one representing start point
   */
  int[] getStartPoint();

  /**
   * judge whether the player arrives the end point.
   *
   * @param i the location of the player
   * @return true if the player arrives the end point otherwise false
   */
  Boolean judgeWhetherEnd(int i);

  /**
   * get the description of one location including the valid directions'
   * player can move to and available treasure.
   *
   * @param num the number of the location
   * @return a list of string representing the valid direction and treasure
   */
  List<String> getDescription(int num);

  /**
   * update the number of treasure of one location if player picks up treasure.
   *
   * @param num      the number of location
   * @param treasure the treasure needed to be removed
   */
  void updateTreasure(int num, String treasure);

  /**
   * get the start and end point.
   *
   * @return an array of two value, the first is the start point while the second is the end point
   */
  int[] getStartEnd();

  /**
   * get the valid direction of one location player can move to.
   *
   * @param num the number of location
   * @return a list of string representing direction
   */
  List<String> getValidMove(int num);

  /**
   * get the valid treasure of one location player can pick up.
   *
   * @param num the number of location
   * @return a list of treasure
   */
  List<Treasure> getValidTreasure(int num);
}
