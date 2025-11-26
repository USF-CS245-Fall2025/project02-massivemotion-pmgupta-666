

WHAT IT IS
==========
Massive Motion is a small animation project where a star and a bunch of randomly generated bodies float around the screen based on settings from a property file. The fun part is that all objects are stored in one of four custom list implementations (array list, singly linked list, doubly linked list, or dummy-head linked list), and you can switch between them. The animation updates every timer tick, adding new bodies from the screen edges and removing anything that drifts offscreen.

HOW TO RUN
==========
If you try running this in a cloud environment like GitHub Codespaces, you’ll get a HeadlessException because Swing windows can’t open there. But the code compiles and works correctly pls run it locally to actually see the animation:

java MassiveMotion MassiveMotion.txt

WHAT EACH FILE DOES
==========
MassiveMotion.java
----------
Loads the property file, picks the list type, builds the window, and starts the simulation.

SimulationCanvas.java
---
Handles the animation: drawing the star/bodies, updating positions, spawning new ones, and removing offscreen ones.

CelestialBody.java
-----
Represents any object in space with a position, size, velocity, and color.

List.java
----------
A simple interface that all four custom lists follow.

ArrayList.java
----------
Your own array implementation used to store simulation objects.

SinglyLinkedList.java
---------
Basic linked list that connects nodes in one direction.

DoublyLinkedList.java
-----
A linked list with both next and previous pointers for easier removal.

DummyHeadLinkedList.java
------------
A linked list that uses a dummy head node so removals at the start are painless.

ListFactory.java
---------
Picks and returns the right list implementation based on what’s written in the config file.

MassiveMotion.txt
-------
The property file that decides window size, timing, list type, velocities, and star/body settings.