package controller;

import manager.Manager;

/**
 * Represents a Controller for player to play in Dungeon.
 * handle user moves by executing them using the model.
 * convey move outcomes to the user in some form.
 */
public interface DungeonController {

  /**
   * Execute a single game of dungeon given a dungeon manager Model. When the game is over,
   * the playGame method ends.
   *
   * @param manager a non-null dungeon Model
   */
  void playGame(Manager manager);

}
