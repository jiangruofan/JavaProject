package sanctuary;

import monkey.Food;
import monkey.Sex;

/**
 * sanctuary.Driver program for sanctuary.Sanctuary to show how it works.
 */
public class Driver {
  /**
   * This is the main function.
   */
  public static void main(String[] args) {
    // invalid input
    try {
      Sanctuary sanctuary = new Sanctuary(-5, 10, 10);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }

    // set the isolation num, enclosures num and enclosures size.
    Sanctuary sanctuary = new Sanctuary(5, 3, 15);
    System.out.println("set the isolation number is 5,"
            + " enclosure number is 3 and enclosures size is 15");

    // put monkeys into an isolation set their attributes.
    sanctuary.setIsolations("obj1", "drill", Sex.male, 5, 100, 100, Food.eggs);
    sanctuary.setIsolations("obj2", "saki", Sex.female, 5, 100, 100, Food.insects);
    sanctuary.setIsolations("obj3", "saki", Sex.male, 15, 100, 100, Food.treeSap);
    sanctuary.setIsolations("obj4", "saki", Sex.female, 30, 100, 100, Food.leaves);
    System.out.println("put obj1, obj2, obj3, obj4 to the isolations");

    // name already exists in isolation.
    try {
      sanctuary.setIsolations("obj4", "saki", Sex.male, 20, 100, 100, Food.treeSap);
    } catch (IllegalArgumentException e) {
      System.out.println("put obj4 into isolations, but " + e.getMessage());
    }

    sanctuary.setIsolations("obj5", "queer", Sex.male, 20, 100, 100, Food.treeSap);
    System.out.println("put obj5 into the isolation");

    // isolations are full
    try {
      sanctuary.setIsolations("obj6", "saki", Sex.male, 20, 100, 100, Food.treeSap);
    } catch (IllegalStateException e) {
      System.out.println("put obj6 into isolations, but " + e.getMessage());
    }

    // transfer monkey from isolations to enclosures
    System.out.println(sanctuary.transferToEnclosure("obj1"));
    System.out.println(sanctuary.transferToEnclosure("obj2"));

    // obj3 is also the saki species and since its size is medium,
    // it can be put into enclosure 2 where obj2 lives
    System.out.println(sanctuary.transferToEnclosure("obj3"));

    // obj4 is also the saki species but because there is not enough space for it in enclosure 2,
    // it will be transferred to a new room which is enclosure 3
    System.out.println(sanctuary.transferToEnclosure("obj4"));

    // all 3 enclosure rooms have species living and obj5 is a different species,
    // so it will be transferred to another sanctuary
    System.out.println(sanctuary.transferToEnclosure("obj5"));

    // the monkey does not exist
    try {
      sanctuary.transferToEnclosure("obj10");
    } catch (IllegalArgumentException e) {
      System.out.println("transfer obj10 to enclosures, but " + e.getMessage());
    }

    // name already exists in enclosures
    try {
      sanctuary.setIsolations("obj2", "saki", Sex.male, 20, 100, 100, Food.treeSap);
    } catch (IllegalArgumentException e) {
      System.out.println("put obj2 into isolations, but " + e.getMessage());
    }

    // transfer monkey from enclosures to isolations
    System.out.println(sanctuary.transferToIsolation("obj1"));
    System.out.println(sanctuary.transferToIsolation("obj2"));

    // Produce a sign for a certain enclosure
    System.out.println("the sign for enclosure 2 is: " + sanctuary.produceSign(2));
    System.out.println("the sign for enclosure 3 is: " + sanctuary.produceSign(3));

    // obj1 has been transferred to isolation, thus the enclosure 1 will be empty
    System.out.println("the sign for enclosure 1 is: " + sanctuary.produceSign(1));

    // set isolation to make isolations full
    sanctuary.setIsolations("obj6", "tamarind", Sex.male, 20, 100, 100, Food.fruits);
    sanctuary.setIsolations("obj7", "queer", Sex.male, 20, 100, 100, Food.insects);
    sanctuary.setIsolations("obj8", "queer", Sex.male, 20, 100, 100, Food.insects);
    System.out.println("put obj6, obj7, obj8 into the isolation");

    // isolations are full, and you can not transfer money to isolations now,
    // you need to find another solution
    try {
      sanctuary.transferToIsolation("obj3");
    } catch (IllegalStateException e) {
      System.out.println("transfer obj3 to the isolation, but " + e.getMessage());
    }

    // the monkey does not exist
    try {
      sanctuary.transferToIsolation("obj10");
    } catch (IllegalArgumentException e) {
      System.out.println("transfer obj10 to the isolation, but " + e.getMessage());
    }

    // report the species and their living places
    System.out.println("the species and their living place are: " + sanctuary.reportSpecies());

    // look up a certain species
    System.out.println("drill is in " + sanctuary.lookUpSpecies("drill"));
    System.out.println("saki is in " + sanctuary.lookUpSpecies("saki"));
    System.out.println("queer is in " + sanctuary.lookUpSpecies("queer"));
    System.out.println("tamarind is in " + sanctuary.lookUpSpecies("tamarind"));

    // the spider species does not exist
    System.out.println(sanctuary.lookUpSpecies("spider"));

    // produce name list of all monkeys and their living area
    System.out.println("name list is: " + sanctuary.produceNameList());

    // produce the shopping list
    System.out.println("shopping list is: " + sanctuary.produceShoppingList());

    // expand the sanctuary
    int[] res = sanctuary.expand(10, 10);
    System.out.printf("old isolation size is %d, old enclosures size is %d, "
            + "new isolation size is %d, new enclosures size is %d%n",
            res[0], res[1], res[2], res[3]);

    // if new sizes are smaller than old sizes
    try {
      sanctuary.expand(2, 2);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }
}
