package driver;

import java.util.Scanner;

import battle.Battle;
import player.Player;
import random.RandomInterface;
import random.RealRandom;

/**
 * This is a driver class to show how the program works.
 */
public class Driver {
  /**
   * This is the main function.
   */
  public static void main(String[] args) {
    System.out.println("Let's make a fight between two players!");
    Scanner scan = new Scanner(System.in);
    RandomInterface random = new RealRandom();
    Player player1 = new Player(random);
    Player player2 = new Player(random);
    Battle battle = new Battle(random);
    System.out.printf("player1 strength:%d constitution:%d dexterity:%d charisma:%d%n",
            player1.getStrength(), player1.getConstitution(), player1.getDexterity(),
            player1.getCharisma());
    System.out.printf("player2 strength:%d constitution:%d dexterity:%d charisma:%d%n",
            player2.getStrength(), player2.getConstitution(), player2.getDexterity(),
            player2.getCharisma());
    player1.setWeapon(battle.createWeapon());
    player1.setEquipment(battle.createGear());
    player2.setWeapon(battle.createWeapon());
    player2.setEquipment(battle.createGear());
    System.out.printf("Player1 : %s%n", player1.produceDescription());
    System.out.printf("Player2 : %s%n", player2.produceDescription());
    if (!battle.judgeWhetherHitCanOccur(player1, player2)) {
      throw new IllegalStateException("The hit never happens, please rerun the program!");
    }
    int whoFirst = battle.whoFirstAttack(player1, player2);
    if (whoFirst == 2) {
      Player temp;
      temp = player2;
      player2 = player1;
      player1 = temp;
      System.out.println("player2 first attacks.");
    } else {
      System.out.println("player1 first attacks.");
    }

    String input;
    double i;
    do {
      while (true) {
        i = battle.fight(player1, player2);
        if (i != 0) {
          if (whoFirst == 2) {
            System.out.printf("player2 attacks and causes %f damage to player1%n", i);
          } else {
            System.out.printf("player1 attacks and causes %f damage to player2%n", i);
          }
          if (player2.judgeDeath()) {
            if (whoFirst == 2) {
              System.out.println("player1 is dead and player2 wins.");
            } else {
              System.out.println("player2 is dead and player1 wins.");
            }
            break;
          }
        } else {
          if (whoFirst == 2) {
            System.out.println("player2 attacks and failed.");
          } else {
            System.out.println("player1 attacks and failed.");
          }
        }
        i = battle.fight(player2, player1);
        if (i != 0) {
          if (whoFirst == 2) {
            System.out.printf("player1 attacks and causes %f damage to player2%n", i);
          } else {
            System.out.printf("player2 attacks and causes %f damage to player1%n", i);
          }
          if (player1.judgeDeath()) {
            if (whoFirst == 2) {
              System.out.println("player2 is dead and player1 wins.");
            } else {
              System.out.println("player1 is dead and player2 wins.");
            }
            break;
          }
        } else {
          if (whoFirst == 2) {
            System.out.println("player1 attacks and failed.");
          } else {
            System.out.println("player2 attacks and failed.");
          }
        }
      }
      System.out.println("Do you want a rematch? Please input yes or no");
      input = scan.next();
      if (input.equalsIgnoreCase("yes")) {
        player1.setHealth();
        player2.setHealth();
      }
    }
    while (!input.equalsIgnoreCase("no"));
  }
}
