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

## List of features

There are two main classes, dungeon and player classes and one class, location in my model. Dungeon class is used to create the map for player to move on and treasures for player to pick up. The player class represents a player and the location class represents one location in the map which can be either a cave or a tunnel. There is also a random interface which has two implementations representing true and false random.

## How to run

Find the place where your jar file is stored.
Open the CMD and input java -jar project3.jar + row + column + interconnectivity + whether wrapping + what percent of caves you want to have treasure
For example :
jave -jar project3.jar 4 4 2 true 50


## How to use the program

First you need to create a random object to set random values for Dungeon class to create the map. Then you create a dungeon object by setting the row, column, interconnectivity, whether it is wrapped and what percent of caves you want to have treasure. After that, you can create a player object to represent the player. 
Then you set the player to his start point and you can choose to pick up the treasure in the location if it exists and move player to 4 possible directions. If the player arrives at the end point, the game will be over.
## Description of examples

<u>Run -- ExampleRun.txt </u>
Take the first example run as an example

1. print the map to the screen
2. output the player's current location
3. output the location's information including possible directions player can move to and the treasure in the location
4. pick up the treasure if you want
5. output the player's information including how many treasure player has collected
6. move the player
7. loop the 2 - 6 step until the player gets the end point and output the message "game is over"

## Design/Model Changes

The model itself doesn't change a lot, I added more private methods in the Dungeon class to help create the map and set the treasure. At first I think I can use the coordinate of two points to judge whether the shortest path between them is at least length of 5, but when I actually did the project, I think it's wrong and I used BFS algorithm to solve this problem. In addition, I set the location class as package private because only the dungeon class needs to use it, so making its package private is a good idea.
## Assumptions

1. Each cave can have at most 3 treasure which are diamond, rubies and sapphire and each type of treasure is not repeatable.
2. If the player gets the end point, the game will be over.
3. If the interconnectivity is larger than it could be, the map will be fully connected.

## Limitations

I think one limitation may be if the player want to pick up treasure, the user needs to input the name of treasure as a string, it's possible that the user may input the wrong name but my program can detect it and return the invalid input message to the screen and let the user input again. 

## Citations

I completely did the project by myself and didn't cite any resources.


