**Grid-Developer-Test**

The program aims to find the 5 closest events to a location, passed as a pair of coordinates, by the user.

**Assumptions made:**

1) When no tickets are available for an event, the event is still considered if it is one of the 5 closest, even though it
may not have any "cheapest ticket".

2) Ticket prices are within the range of $10 to $50.

3) A event may have a maximum of 10 tickets.

4) There may be a minimum of only 3 events.

**How might you change your program if you needed to support multiple events at the same location?**

--> Solution involving minimal change in code

As per the current version, in the populateEvents function of the "GridDeveloper" class, the Set (HashSet) allows for 
only 1 event per location by checking if the coordinates of a randomly generated event have not already been allocated to 
another event (absent from the Set). Removing this check allows for supporting multiple events at the same location. 

--> Solution involving a significant change in code but improves complexity

Apart from the above, if a location was to support multiple events I would use a Map (HashMap) instead, where a Coordinate 
would be "mapped" to a list of Events. The proximity to the key set of the Map (the coordinates) would then be used to filter
out events closest to the user's input and the first 5 would be considered.

**How would you change your program if you were working with a much larger world size?**

When working on a global scale, an issue to consider is the amount of the data being dealt with and where it is being stored.
Most systems I have learnt of or come across store it externally, in some disks or databases.

Restricting a user to querying for events only around their geographical location (i.e. town or city or country) would be 
inaccurate given that they could be querying for events at a different time and on a different day, where they
may even be in a different location. Therefore, a user's query should be able to produce results considering events all over the world. 

In order to allow for such capabilities, each Event object can be stored in the external memory (disk/database) and logical 
decision structures like Binary Trees can be used. Based on the coordinate, input by the user, the choice of which subtree to 
visit can be decided. This representation gives the memory a "cluster" like appearance, as all the coordinates in a particular 
geographical location can be clustered together. This improves the number of accesses to the external memory and thus, reduces
the searching/querying time in the external memory.

To improve on the above, caching algorithms may also be deployed to store information regarding events at locations most frequently
queried. This may be achieved via the Adapter and Caching Proxy software engineering patterns.