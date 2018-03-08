project-team-subpar Sprint2 Report
====================================
1. Documentation

User stories and priorities were already set prior to this meeting, from sprint 1.
Two meetings were held:

(1) For the first meeting all group members joined after our 10-12am CSC301 class in the library. The plans and ideas that was discussed were the following:

-------------------------------------------------

1) We discussed on what we wanted to present for sprint 2, and our product owner Brian decided to present a demo of our game. This demo has to have a functional menu screen, 2 controllable players, with different maps to present.
2) We selected the User stories that were necessary to fulfill our goals; User stories 3, 7, 11, 12, 21, 22, 23, 25, 26 and 27.
3) We followed the strategy used for sprint 1 for tackling the user stories. Discuss our strength and weaknesses to appropriately give each user stories a corresponding team of 2 to 3 people.
4) For User stories 3, 26 and 11, all three stories were very connected. They focused on the functionality and design of the map, we decided that Gabriello and John will be the group working on it. Gabriello focused on the map designs and wall objects, while John focused on the handling on the view for the map.
5) For User stories 7 and 12, both stories focused on a menu screen, how it looks, works and any other features. We decided that Brian, and Sergio will be the group working on it. Sergio worked on the screen and display seen by the user, while Brian worked on the navigation and functionality of the buttons.
6)  For User story 21, 25 and 27, all three stories focused on the player and its actions on the game. We decided that Gaganjot, Gabriello and John will be the group working on it. John, having previously worked on the player class worked on some fixes for it in user story 21. Meanwhile, Gabriello and Gaganjot worked on building and implementing the second player for the game.
7)  For User story 23, an important story that is focused on the wall to player relationship through collision, we decided that Brandon,Tanay and Gaganjot will be working on it. This is due to the fact that all have past experience in Java class implementation and have an understanding on how to implement it.
8)  For User story 22, a story that is focused on movement of the player, we decided that Gabriello will work on it. Since he showed an passionate interest in implementing it as an extra feature.
------------------------------------------------
(2) For the second meeting we checked everyone's progress on user stories. We provided help and advice to those who were behind on their work.

When all of these user stories are fulfilled, we will have a functioning, a menu screen that will take you to a 2 player game with object collision by the due date of the presentation.

-----------------------------------------------

2. Participation
* Brian Lee (*leebri17*)

* Gaganjot Bhullar (*bhulla44*)

* John Bernal (*bernalj1*)

* Gabriello Tanhueco (*tanhuec1*)

* Brandon Fernandez (*ferna800*)

* Tanay Onder (*ondertan*)

* Sergio Parra (*parraser*)

-------------------------
3. Sprint Backlog

This project will utilize the fibonacci sequence to eliminate user stories.
  
--------------------------------------------

#### User Story 3

As a casual/competitive player, I want walls so that I have a more varied experience so that bullets can bounce off.

Criteria of Satisfaction:
The walls must be solid objects so that the player's shots do not phase through the wall.

Priority: 3

Point Estimate: 3

Dependencies: User Story 2

Owners: Gabriello Tanhueco

-------------------

#### User Story 11

As a casual player, I want multiple maps, so that I can have a variety in multiple battlefields

Criteria of Satisfaction:
Each map must be different and should introduce something new that the other maps do not have.

Priority: 4

Point Estimate: 3

Dependencies: User  Story 2

Owners: Gabriello Tanhueco

---------------------

#### User Story 26

As a casual/competitive player, I want to create a handler class to handle all of the game objects so to serve as the "view" of the implementation.

Criteria of Satisfactions:
The game objects should all still work even when moved as a separate from the Game class.

Priority: 2

Point Estimate: 5

Dependencies: User Story 18

Owners: John Bernal

---------------------

#### User Story 12

As a casual/competitive player, I want it so that when I start up the game I want to be able to browse the game's simple main menu, with basic options.

Criteria of Satisfaction:
Each customization must properly affect either the players or that of the map.
The set up on the main menu must properly work.
The default settings given must not be necessarily difficult or too simple.

Priority: 2

Point Estimate: 3

Dependencies: User Story 17

Owners: Segio Parra and Brian Lee

---------------------
#### User Story 7

As a casual player, I want to be able to choose between different game modes, so that I can have infinite bullet hell or a more passive open-field.

Criteria of Satisfaction:
There could be more game modes, but the two game modes above must work with no bugs or issues.

Priority: 5

Point Estimate: 1

Dependencies: User Story 12

Owners: Sergio Parra and Brian Lee

---------------------
#### User Story 21

As a casual/competitive player, I want to move my character so that I can dodge incoming projectiles.

Criteria of Satisfaction:
A user must be able to at minimum, move 8 different directions.
A user must be able to move their player avatar smoothly with no input lag or choppiness.

Priority: 1

Point Estimate: 3

Dependencies: User Story 1

Owners: John Bernal

---------------------
#### User Story 27

As a casual/competitive player, I want to be able to play with other player.

Criteria of Satisfactions:
There must produce up to two distinct players

Priority: 1

Point Estimate: 1

Dependencies: User Story 2

Owners: Gaganjot Bhullar

---------------------
#### User Story 25

As a casual/competitive player, I want a second player to play against if I choose local versus utilizing different 6 buttons.

Criteria of Satisfactions:
The character model should be able to move freely and rotate.

Priority: 6

Point Estimate: 2

Dependencies: User Story 1 ,2, 26, 27

Owners: Gabriello Tanhueco

---------------------
#### User Story 23

As a casual/competitive player, I want my character not to phase through walls and get hit by bullets.

Criteria of Satisfaction:
Character model should not phase through any of the walls, or other players.
Bullets should react appropriately.

Priority: 5

Point Estimate: 5

Dependencies: User Story 3

Owners: Brandon Fernandez, Tanay Onder, Nick Bhullar

---------------------
#### User Story 22

As a casual player, I want player trails, so that I can admire the special effects and add intensity to the game.

Criteria of Satisfaction:
See a trail come out of the player and watch it decay over time.

Priority: 8

Point Estimate: 5

Dependencies: User Story 2

Owners: Gabriello Tanhueco

---------------------
