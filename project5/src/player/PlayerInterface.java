package player;

import treasure.Treasure;

import java.util.List;
import java.util.Map;

/**
 * This class represents the player.
 */
public interface PlayerInterface {
  /**
   * move the player from one place to the other.
   *
   * @param str  the direction you want to move
   * @param list valid directions you can move
   * @return true if move successfully other false
   */
  boolean move(String str, List<String> list);

  /**
   * get the location of the player.
   * This method is used to interact with the dungeon class.
   *
   * @return the location of the player.
   */
  int getLoc();

  /**
   * get the location of the player in terms of 2D point.
   *
   * @return the location of the player
   */
  int[] getLocInPoint();

  /**
   * pick up the treasure in the location.
   *
   * @param str          the treasure you want to pick up
   * @param treasureList a list of valid treasure you can pick up
   * @return 3 if successfully pick up the treasure,
   *         2 if you don't want to pick up treasure otherwise 1
   */
  int pickUpTreasure(String str, List<Treasure> treasureList);

  /**
   * get a description of the player.
   *
   * @return the treasure the player has collected
   */
  Map<Treasure, Integer> getDescription();



}
