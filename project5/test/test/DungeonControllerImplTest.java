package test;

import controller.DungeonControllerImpl;
import manager.Manager;
import org.junit.Before;
import org.junit.Test;
import random.PredictableRandom;
import random.RandomInterface;

import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

/**
 * represent a test for DungeonControllerImpl.
 */
public class DungeonControllerImplTest {
  private DungeonControllerImpl controller;
  private StringBuffer out;
  private Reader in;
  private Manager manager;

  /**
   * set up the condition for test.
   */
  @Before
  public void setUp() {
    out = new StringBuffer();
    int[] order = {
        1, 2, 3, 4, 4, 5, 6, 7, 7, 8, 9, 10, 10, 10, 10,
        1, 1, 1, 1, 1, 1, 1, 1, 1,
        1, 5, 1, 1, 1, 1,
        0, 0, 0, 1, 2,
        1, 3,
        0, 1, 2, 3, 4, 5, 6, 7,
        0, 1
    };
    RandomInterface random = new PredictableRandom(order);
    int row = 4;
    int column = 4;
    int monsterNum = 3;
    manager = new Manager(row, column,
            0, false, 50, random, monsterNum);
  }

  /**
   * a player is eaten by monster.
   */
  @Test
  public void test1() {
    in = new StringReader("qwe diamonds,rubies,sapphires yes no" +
            " east qwe no east yes no east no " + "south,2 qwe south,3 no south");
    controller = new DungeonControllerImpl(in, out);
    controller.playGame(manager);
    assertEquals("current location is [0, 0]\n" +
            "the information of location is: [east, DIAMONDS, RUBIES, SAPPHIRES, 1 arrow]\n" +
            "input treasures you want to pick up, " +
            "if more than one treasure, split treasures by','" +
            " if no treasure you want to pick up, input no\n" +
            "invalid input, please input again\n" +
            "you successfully pick up the treasure\n" +
            "do you want to pick up the arrow? if yes, Please input yes," +
            " any other answer will be considered no\n" +
            "you successfully pick up an arrow\n" +
            "player's information: {Arrow=4, RUBIES=1, SAPPHIRES=1, DIAMONDS=1}\n" +
            "Do you want to shoot? if yes, input the direction and distance split by ','" +
            " otherwise input no\n" +
            "input the direction you want to move\n" +
            "current location is [0, 1]\n" +
            "the information of location is: [west, east, 1 arrow]\n" +
            "do you want to pick up the arrow? if yes, Please input yes," +
            " any other answer will be considered no\n" +
            "you choose not to pick up the arrow\n" +
            "player's information: {Arrow=4, RUBIES=1, SAPPHIRES=1, DIAMONDS=1}\n" +
            "Do you want to shoot? if yes, input the direction and distance" +
            " split by ',' otherwise input no\n" +
            "input the direction you want to move\n" +
            "current location is [0, 2]\n" +
            "the information of location is: [west, east, 1 arrow]\n" +
            "there is a less pungent smell\n" +
            "do you want to pick up the arrow? if yes, Please input yes," +
            " any other answer will be considered no\n" +
            "you successfully pick up an arrow\n" +
            "player's information: {Arrow=5, RUBIES=1, SAPPHIRES=1, DIAMONDS=1}\n" +
            "Do you want to shoot? if yes, input the direction " +
            "and distance split by ',' otherwise input no\n" +
            "input the direction you want to move\n" +
            "current location is [0, 3]\n" +
            "the information of location is: [west, south, 1 arrow]\n" +
            "there is a great pungent smell\n" +
            "do you want to pick up the arrow? if yes, Please input yes," +
            " any other answer will be considered no\n" +
            "you choose not to pick up the arrow\n" +
            "player's information: {Arrow=5, RUBIES=1, SAPPHIRES=1, DIAMONDS=1}\n" +
            "Do you want to shoot? if yes, input the direction and" +
            " distance split by ',' otherwise input no\n" +
            "you successfully hit a monster\n" +
            "Do you want to shoot? if yes, input the direction and" +
            " distance split by ',' otherwise input no\n" +
            "invalid input, please input again\n" +
            "you successfully hit a monster\n" +
            "Do you want to shoot? if yes, input the direction and" +
            " distance split by ',' otherwise input no\n" +
            "input the direction you want to move\n" +
            "unfortunately you are eaten by the monster\n", out.toString());
  }

  /**
   * a player successfully reach the end.
   */
  @Test
  public void test2() {
    int[] order = {
        1, 2, 3, 4, 4, 5, 6, 7, 7, 8, 9, 10, 10, 10, 10,
        1, 1, 1, 1, 1, 1, 1, 1, 1,
        1, 5, 1, 1, 1, 1,
        0, 0, 1, 2,
        1, 3,
        0, 1, 2, 3, 4, 5, 6, 7,
        0, 0, 0,
    };
    RandomInterface random = new PredictableRandom(order);
    int row = 4;
    int column = 4;
    int monsterNum = 3;
    manager = new Manager(row, column,
            0, false, 50, random, monsterNum);
    in = new StringReader("no yes no east yes no east yes no east yes south,1" +
            " south,2 south,3 no south diamonds yes no west yes east,1 east,1 no east" +
            " no south no south north,1 north,1 west,1 west,3 north south west west west");
    controller = new DungeonControllerImpl(in, out);
    controller.playGame(manager);
    assertEquals("current location is [0, 0]\n" +
            "the information of location is: [east, DIAMONDS, RUBIES, 1 arrow]\n" +
            "input treasures you want to pick up, " +
            "if more than one treasure, split treasures by','" +
            " if no treasure you want to pick up, input no\n" +
            "you choose to pick up no treasure\n" +
            "do you want to pick up the arrow? if yes, " +
            "Please input yes, any other answer will be considered no\n" +
            "you successfully pick up an arrow\n" +
            "player's information: " +
            "{Arrow=4}\n" +
            "Do you want to shoot? if yes, input the direction and" +
            " distance split by ',' otherwise input no\n" +
            "input the direction you want to move\n" +
            "current location is [0, 1]\n" +
            "the information of location is: [west, east, 1 arrow]\n" +
            "do you want to pick up the arrow? if yes, Please input yes," +
            " any other answer will be considered no\n" +
            "you successfully pick up an arrow\n" +
            "player's information: " +
            "{Arrow=5}\n" +
            "Do you want to shoot? if yes, input the direction and" +
            " distance split by ',' otherwise input no\n" +
            "input the direction you want to move\n" +
            "current location is [0, 2]\n" +
            "the information of location is: [west, east, 1 arrow]\n" +
            "there is a less pungent smell\n" +
            "do you want to pick up the arrow? if yes, Please input yes," +
            " any other answer will be considered no\n" +
            "you successfully pick up an arrow\n" +
            "player's information: " +
            "{Arrow=6}\n" +
            "Do you want to shoot? if yes, input the direction and" +
            " distance split by ',' otherwise input no\n" +
            "input the direction you want to move\n" +
            "current location is [0, 3]\n" +
            "the information of location is: [west, south, 1 arrow]\n" +
            "there is a great pungent smell\n" +
            "do you want to pick up the arrow? if yes, Please input yes," +
            " any other answer will be considered no\n" +
            "you successfully pick up an arrow\n" +
            "player's information: " +
            "{Arrow=7}\n" +
            "Do you want to shoot? if yes, input the direction" +
            " and distance split by ',' otherwise input no\n" +
            "you successfully hit a monster\n" +
            "Do you want to shoot? if yes, input the direction" +
            " and distance split by ',' otherwise input no\n" +
            "you successfully hit a monster\n" +
            "Do you want to shoot? if yes, input the direction" +
            " and distance split by ',' otherwise input no\n" +
            "you successfully hit a monster\n" +
            "Do you want to shoot? if yes, input the direction" +
            " and distance split by ',' otherwise input no\n" +
            "input the direction you want to move\n" +
            "current location is [1, 3]\n" +
            "the information of location is: [north, west, south, DIAMONDS, 1 arrow]\n" +
            "there is a great pungent smell\n" +
            "input treasures you want to pick up," +
            " if more than one treasure, split treasures by','" +
            " if no treasure you want to pick up, input no\n" +
            "you successfully pick up the treasure\n" +
            "do you want to pick up the arrow? if yes, Please input yes," +
            " any other answer will be considered no\n" +
            "you successfully pick up an arrow\n" +
            "player's information: " +
            "{Arrow=5, DIAMONDS=1}\n" +
            "Do you want to shoot? if yes, input the direction and" +
            " distance split by ',' otherwise input no\n" +
            "input the direction you want to move\n" +
            "current location is [1, 2]\n" +
            "the information of location is: [west, east, 1 arrow]\n" +
            "there is a great pungent smell\n" +
            "do you want to pick up the arrow? if yes, Please input yes," +
            " any other answer will be considered no\n" +
            "you successfully pick up an arrow\n" +
            "player's information: " +
            "{Arrow=6, DIAMONDS=1}\n" +
            "Do you want to shoot? if yes, input the direction and" +
            " distance split by ',' otherwise input no\n" +
            "you successfully hit a monster\n" +
            "Do you want to shoot? if yes, input the direction and" +
            " distance split by ',' otherwise input no\n" +
            "you don't hit the monster\n" +
            "Do you want to shoot? if yes, input the direction and" +
            " distance split by ',' otherwise input no\n" +
            "input the direction you want to move\n" +
            "current location is [1, 3]\n" +
            "the information of location is: [north, west, south]\n" +
            "there is a great pungent smell\n" +
            "player's information: " +
            "{Arrow=4, DIAMONDS=1}\n" +
            "Do you want to shoot? if yes, input the direction and" +
            " distance split by ',' otherwise input no\n" +
            "input the direction you want to move\n" +
            "current location is [2, 3]\n" +
            "the information of location is: [north, west, south]\n" +
            "there is a great pungent smell\n" +
            "player's information: " +
            "{Arrow=4, DIAMONDS=1}\n" +
            "Do you want to shoot? if yes, input the direction and" +
            " distance split by ',' otherwise input no\n" +
            "input the direction you want to move\n" +
            "current location is [3, 3]\n" +
            "the information of location is: [north, west]\n" +
            "there is a great pungent smell\n" +
            "player's information: " +
            "{Arrow=4, DIAMONDS=1}\n" +
            "Do you want to shoot? if yes, input the direction and" +
            " distance split by ',' otherwise input no\n" +
            "you successfully hit a monster\n" +
            "Do you want to shoot? if yes, input the direction and" +
            " distance split by ',' otherwise input no\n" +
            "you don't hit the monster\n" +
            "Do you want to shoot? if yes, input the direction and" +
            " distance split by ',' otherwise input no\n" +
            "you successfully hit a monster\n" +
            "Do you want to shoot? if yes, input the direction and" +
            " distance split by ',' otherwise input no\n" +
            "you don't hit the monster\n" +
            "you don't have any arrow now\n" +
            "input the direction you want to move\n" +
            "current location is [2, 3]\n" +
            "the information of location is: [north, west, south]\n" +
            "player's information: " +
            "{Arrow=0, DIAMONDS=1}\n" +
            "you don't have any arrow now\n" +
            "input the direction you want to move\n" +
            "current location is [3, 3]\n" +
            "the information of location is: [north, west]\n" +
            "player's information: " +
            "{Arrow=0, DIAMONDS=1}\n" +
            "you don't have any arrow now\n" +
            "input the direction you want to move\n" +
            "current location is [3, 2]\n" +
            "the information of location is: [west, east]\n" +
            "player's information: " +
            "{Arrow=0, DIAMONDS=1}\n" +
            "you don't have any arrow now\n" +
            "input the direction you want to move\n" +
            "current location is [3, 1]\n" +
            "the information of location is: [west, east]\n" +
            "player's information: " +
            "{Arrow=0, DIAMONDS=1}\n" +
            "you don't have any arrow now\n" +
            "input the direction you want to move\n" +
            "you reached the end, game over\n", out.toString());
  }

  /**
   * Testing when something goes wrong with the Appendable.
   * Here we are passing it a mock of an Appendable that always fails.
   */
  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    StringReader input = new StringReader("qwe diamonds,rubies,sapphires yes no" +
            " east qwe no east yes no east no " +
            "south,2 qwe south,3 no south");
    Appendable gameLog = new FailingAppendable();
    DungeonControllerImpl c = new DungeonControllerImpl(input, gameLog);
    c.playGame(manager);
  }
}