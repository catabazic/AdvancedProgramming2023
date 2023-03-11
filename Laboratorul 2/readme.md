### Laboratorul 2

-----------------------

## Compulsory (1p)

------------------------

- [x] Create an object-oriented model of the problem. You should have (at least) the following classes: Location, Road.
  The location and road types will be implemented as enums.
- [x] Each class should have appropriate constructors, getters and setters.
- [x] The toString method form the Object class must be properly overridden for all the classes.
- [x] Create and print on the screen various objects of the two classes.

## Homework (2p)

---------------------

- [x] Create a class that describes an instance of the problem.
- [x] Override the Object.equals method for the Location and Road classes. The problem should not allow adding the same location or road twice.
- [x] Instead of using an enum, create dedicated classes either for locations: cities, air ports, gas stations etc. or roads: highway, express, country, etc. Each concrete location class may have additional specific propertes (population, number of terminals, gas price, etc.)
- [x] Implement a method that determines if a problem's instance is valid.
- [x] Implement an algorithm for determining if it is possible to go from one location to another using the given roads.
- [x] Write doc comments in your source code and generate the class documentation using javadoc.

## Bonus (2p)

----------------------

- [x] Create a class to describe the solution.
- [x] Implement an algorithm to find the best route from a location to another, either as the shortest route, or the fastest route.
- [ ] Generate large random instances of the problem and analyze the performance of your algorithm (running times, memory consumption).