# sanctuary.Sanctuary
The [Jungle Friends Primate sanctuary.Sanctuary (Links to an external site.)](https://www.junglefriends.org/monkeys/) provides a permanent home and high-quality sanctuary care for New World primates who have been cast-off from the pet trade, retired from research, or confiscated by authorities. They are seeking to replace all of their paper records with computer records where they can keep track of the individual animals that are in their care. This project is to design and implement such a system that can satisfy their needs.
# **Overview**

The sanctuary.Sanctuary consists of two different types of housing:
-   _Isolation_  is used to keep monkeys when they first arrive at the sanctuary and anytime they are receiving medical attention. Isolation consists of a series of cages each of which can house a single animal.
-   _Enclosures_  are much larger and can host a single troop (i.e., a group) of monkeys. Each troop consists of a single species that is found in the New World (some of which are pictured below): drill, guereza, howler, mangabey, saki, spider, squirrel, and tamarin.

Enclosures are initially configured to house a particular species of monkeys but can be repurposed for a different species if they are empty.

The capacity of an enclosure is dependent upon the size of the enclosure and the size of the monkeys in the enclosure. Small monkeys (<10 cm) require 1 square meter each, medium monkeys (10 to 20 cm) require 5 square meters, and large monkeys (>20 cm) require 10 square meters.

Monkeys that are received by the sanctuary must go into isolation where they are assessed and given medical attention. At this time, each individual monkey is given a name, a species designation, a sex, a size, a weight, an approximate age, and a favorite food. The choices of favorite food include eggs, fruits, insects, leaves, nuts, seeds, and tree sap.

Monkeys that have received medical attention may be moved to an available enclosure if there is room. The Jungle Friends Primate sanctuary.Sanctuary also has exchange agreements with several other primate sanctuaries that can take healthy monkeys and often uses these agreements to find the best suitable home for monkeys it receives but cannot house.
The sanctuary.Sanctuary should start with room for n animals in isolation and m troops of monkeys. While these are defined when the sanctuary.Sanctuary is constructed, the sanctuary.Sanctuary would like the flexibility of expanding should the needs and funds allow.

Report the species that are currently being housed in alphabetical order. The list should include where in the sanctuary each species is (both in enclosures and in isolation).
Look up where a particular species is currently housed. If none of a particular species is currently being housed, it should report this fact.

Produce a sign for a given enclosure that lists each individual monkey that is currently housed there. For each individual monkey, the sign should include their name, sex, and favorite food.

Produce an alphabetical list (by name) of all of the monkeys housed in the sanctuary.Sanctuary. This information should include where each monkey can be found.

Produce a shopping list of the favorite foods of the inhabitants of the sanctuary.Sanctuary. For each food listed, the quantity needed should also be listed. Large monkeys should receive 500 gr of their favorite food while medium and small monkeys require 250 gr and 100 gr respectively.

## **List of features**

There is one interface, one abstract and correspondingly two concrete classes inherited from it that represents isolation and enclosure. The monkey class is a separate class with three enums with it. There is also a central manager class that coordinates all other classes.

## **How to use the program**

First you need to create a sanctuary.Sanctuary class and gives its isolation and enclosure number and the enclosure size, then you can put monkeys into the isolation and transfer them into enclosures and transfer them back if they were transferred to enclosures. You can also get the monkeys' information by calling the correspondingly functions and update the house numbers if it is expanded.

## **Description of Examples.**

<u>Run -- ExampleRun.txt </u>
1. Set the isolation number, enclosures number and enclosures size.
2. Put monkeys to the isolation
3. Transfer monkey from isolation to enclosures.
4. Transfer monkey from enclosures to isolation.
5. Produce a sign for a certain enclosure.
8. Report the species and their living places.
9. Look up a certain species.
10. Produce name list of all monkeys and their living area.
11. Produce the shopping list.
12. Expand the sanctuary.

## **Design/Model Changes**

1. I added a Size enum to store three values which are small, medium and large, but when we input the the size, we should input the int type value and the program will change it to enum type by itself instead of directly inputting the enum type value. This is different from the Sex and Food enum.
2. I removed two fields in the sanctuary.Sanctuary class which are isolationNumber and EnclosureNumber because they are redundant and can be represented by the length of list isolationNum and enclosureNum.
3. I added two help method in sanctuary.Sanctuary class to help deal with functions.
4. I removed the setters in Monkey class because there is no need to change the attribute of monkeys after instantiating monkeys.

## **Assumptions**

I assume that all the enclosure size is same and there is always another sanctuary that can accept monkey if a monkey need to be transferred to enclosures but the enclosures are full. I also assume that if a monkey in enclosures get ill but the isolation is full, the staff in sanctuary can always find a solution to it and make the monkey stay alone.


