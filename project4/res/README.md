# Dungeon

The world for our game consists of a _dungeon_, a network of tunnels and caves that are interconnected so that player can explore the entire world by traveling from cave to cave through the tunnels that connect them.


# Overview

In many games, these dungeons are generated at random following some set of constraints resulting in a different network each time the game begins. While the details on algorithms for generating a dungeon for our game are given in Part 2 - Implementation, we consider that an implementation detail. We provide the constraints that should be used for the design process:

-   The dungeon should be able to be represented on a 2-D grid.
-   There should be a path from every cave in the dungeon to every other cave in the dungeon.
-   Each dungeon can be constructed with a  _degree of interconnectivity_. We define an  _interconnectivity = 0_  when there is exactly one path from every cave in the dungeon to every other cave in the dungeon. Increasing the degree of interconnectivity increases the number of paths between caves.
-   Not all dungeons "wrap" from one side to the other (as defined above).
-   One cave is randomly selected as the  _start_  and one cave is randomly selected to be the  _end_. The path between the start and the end locations should be at least of length 5.

### Model Requirements

The model should create and support a player moving through the world. To do this, it should support operations that allow:

-   both wrapping and non-wrapping dungeons to be created with different degrees of interconnectivity
-   provide support for at least three types of treasure: diamonds, rubies, and sapphires
-   treasure to be added to a specified percentage of caves. For example, the client should be able to ask the model to add treasure to 20% of the caves and the model should add a random treasure to at least 20% of the caves in the dungeon. A cave can have more than one treasure.
-   a player to enter the dungeon at the  _start_
-   provide a description of the player that, at a minimum, includes a description of what treasure the player has collected
-   provide a description of the player's location that at the minimum includes a description of treasure in the room and the possible moves (north, east, south, west) that the player can make from their current location
-   a player to move from their current location
-   a player to pick up treasure that is located in their same location

#### Adding Monsters

[Otyughs (Links to an external site.)](https://forgottenrealms.fandom.com/wiki/Otyugh)  are extremely smelly creatures that lead solitary lives in the deep, dark places of the world like our dungeon.

-   There is always at least one Otyugh in the dungeon located at the specially designated  _end_  cave. The actual number is specified on the command line. There is never an Otyugh at the  _start_.
-   Otyugh only occupy caves and are never found in tunnels. Their caves can also contain treasure or other items.
-   They can be detected by their smell. In general, the player can detect two levels of smell:
    -   a less pungent smell can be detected when there is a single Otyugh 2 positions from the player's current location
    -   detecting a more pungent smell either means that there is a single Otyugh 1 position from the player's current location or that there are multiple Otyughs within 2 positions from the player's current location
-   They are adapted to eat whatever organic material that they can find, but love it when fresh meat happens into the cave in which they dwell. This means that a player entering a cave with an Otyugh that has not been slayed will be killed and eaten (see next section on how to slay an Otyugh).

#### Slaying Monsters

To give our player the ability to slay the Otyugh, they will automatically be equipped with a bow that uses crooked arrows

-   Player starts with 3 crooked arrows but can find additional arrows in the dungeon with the same frequency as treasure. Arrows and treasure can be, but are not always, found together. Furthermore, arrows can be found in both caves and tunnels.
-   A player that has arrows, can attempt to slay an Otyugh by specifying a direction and distance in which to shoot their crooked arrow. Distance is defined as the number of caves (but not tunnels) that an arrow travels. Arrows travel freely down tunnels (even crooked ones) but only travel in a straight line through a cave. For example,
    -   a tunnel that has exits to the west and south can have an arrow enter the tunnel from the west and exit the tunnel to the south, or vice-versa (this is the reason the arrow is called a  _crooked arrow_)
    -   a cave that has exits to the east, south, and west will allow an arrow to enter from the east and exit to the west, or vice-versa; but an arrow that enters from the south would be stopped since there is no exit to the north
-   Distances must be exact. For example, if you shoot an arrow a distance of 3 to the east and the Otyugh is at a distance of 2, you miss the Otyugh.
-   It takes 2 hits to kill an Otyugh. Players has a 50% chance of escaping if the Otyugh if they enter a cave of an injured Otyugh that has been hit by a single crooked arrow.

## List of features

There are two main classes, dungeon and player classes and one class, location in my model. Dungeon class is used to create the map for player to move on and treasures for player to pick up. The player class represents a player and the location class represents one location in the map which can be either a cave or a tunnel. There is also a random interface which has two implementations representing true and false random. 
In order to add the monster and arrow to dungeon and allow the player to pick up arrow and shoot monsters, there are two class extending dungeon and player class. I also added a manager class used to coordinate the player and dungeon and there is a controller acting as the control of the MVC design pattern.

## How to run

Find the place where your jar file is stored.
Open the CMD and input java -jar project4.jar + row + column + interconnectivity + whether wrapping + what percent of caves you want to have treasure + monster number
For example :
jave -jar project4.jar 4 4 0 false 50 3


## How to use the program

First you need to create a manager object by setting the row, column, interconnectivity, whether it is wrapped and what percent of caves you want to have treasure and the number of monster. Then, you can move the player, pick up arrows and treasure or shoot the monster based on the information you get from the dungeon. Finally, you will win if you successfully reach the end or lose the game if you are eaten by an monster.
## Description of examples

<u>Run -- ExampleRun.txt </u>
Take the first example run as an example

1. output the player's current location
2. output the location's information including possible directions player can move to and the treasure and arrows in the location
3. give the information of smell of monsters if possible
4. pick up the treasure if you want
5. pick up the arrow if you want
6. output the player's information including how many treasure and arrows player has collected 
7. shoot if you want
8. move the player
9. loop the 1 - 8 step until the player gets the end point or eaten by a monster

## Design/Model Changes

I added a manager class to coordinate the player and dungeon and I added interface for controller and manager class. 
## Assumptions

1. Each cave can have at most 3 treasure which are diamond, rubies and sapphire and each type of treasure is not repeatable.
2. If the player gets the end point, the game will be over.
3. If the interconnectivity is larger than it could be, the map will be fully connected.
4. Each room can only have one arrow at most.

## Limitations

I think one limitation may be if the player want to pick up treasure or arrow, the user needs to input the name of them as a string, it's possible that the user may input the wrong name but my program can detect it and return the invalid input message to the screen and let the user input again. 

## Citations

I completely did the project by myself and didn't cite any resources.


