import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
import java.util.Scanner;

public class GridDeveloper {

  // Axes related constants
  private static final int NUM_COORDS_ON_AXIS = 21;
  private static final int AXIS_MAX = 10;
  private static final int AXIS_MIN = -10;


  // Ticket related constants
  private static final int MAX_NUM_TICKETS = 20;
  private static final int MIN_TICKET_PRICE = 10;
  // Scaling factor ticket prices
  private static final int SCALING_FACTOR = 40;

  // Constants related to number of events desired
  private static final int MIN_NUM_EVENTS = 3;
  private static final int NUM_EVENTS_REQUIRED = 5;

  // Seed generator for the Grid Developer
  private static final Random generator = new Random();

  // Set that stores all coordinates, used for ensuring no two events are
  // stored at the same coordinate
  private static final Set<Coordinate> coordinates = new HashSet<>();

  /**
   * Populates a priority queue with random events (using the seed) passed in
   * as a parameter. The number of events to be added to the priority queue
   * is also passed in as a parameter
   *
   * @param numEvents Number of events to fill in the queue
   * @param events    The priority queue to be filled with events.
   */
  private static void populateEvents(int numEvents,
                                     PriorityQueue<Event> events) {

    // Defines all variables that will be used to populate events.
    int numTicketsForEachEvent;
    double priceOfTicket;
    Event event;
    Coordinate c;
    int x;
    int y;

    for (int i = 0; i < numEvents; i++) {

      // Generate random x and y coordinates for the event and create the
      // coordinate object that they describe
      x = generator.nextInt(NUM_COORDS_ON_AXIS) - AXIS_MAX;
      y = generator.nextInt(NUM_COORDS_ON_AXIS) - AXIS_MAX;
      c = new Coordinate(x, y);

      // Checks if an event is already present at a coordinate. If there is an
      // event there already, it skips to the next iteration, but decrements
      // the loop variable before in order to ensure that the number of events
      // generated is the same as the number of events passed in as a
      // parameter to the function
      if (coordinates.contains(c)) {
        i--;
        continue;
      }

      // Adds a coordinate to the set to block the current coordinate for all
      // other events, and generates an event with the coordinate
      coordinates.add(c);
      event = new Event(c);

      // Generates a random number of tickets for an event
      numTicketsForEachEvent = generator.nextInt(MAX_NUM_TICKETS);

      // Generates all ticket related information for the event
      for (int j = 0; j < numTicketsForEachEvent; j++) {
        priceOfTicket = generator.nextDouble() * SCALING_FACTOR +
            MIN_TICKET_PRICE;
        priceOfTicket = ((double) Math.round(priceOfTicket * 100)) / 100;
        event.addTicket(priceOfTicket);
      }
      // Adds event to the priority queue
      events.add(event);
    }
  }

  public static void main(String[] args) {
    // Defines a Scanner object and other variables required to obtain user
    // input
    int userX;
    int userY;
    String userInput;
    String[] coordinates;
    Scanner in = new Scanner(System.in);

    // Generates the user interface and prompts user for coordinates
    // infinitely until input of correct format has been entered
    System.out.println("Please Input Coordinates: \n");
    do {
      System.out.print("> ");
      userInput = in.nextLine();
      System.out.println();
      // splits user input on commas after discarding all spaces
      coordinates = userInput.replace(" ", "").split(",");

      // checks if user input has too many coordinates or too many commas
      if (coordinates.length != 2 || userInput.indexOf(',') != userInput
          .lastIndexOf(',')) {
        System.out.println("Error reading Input Coordinates, please try " +
            "entering comma separated x and y coordinates again!\n");
        continue;
      }
      try {
        // validates x-coordinate entered by user
        userX = Integer.parseInt(coordinates[0]);
        if (userX > AXIS_MAX || userX < AXIS_MIN) {
          System.out.println("Value of x-coordinate entered: " + userX +
              " isn't between " + AXIS_MIN + " and " + AXIS_MAX + "\n");
          continue;
        }
        // validates y-coordinate entered by user
        userY = Integer.parseInt(coordinates[1]);
        if (userY > AXIS_MAX || userY < AXIS_MIN) {
          System.out.println("Value of y-coordinate entered: " + userY +
              " isn't between " + AXIS_MIN + " and " + AXIS_MAX + "\n");
          continue;
        }
        break;
      } catch (Exception e) {
        // Generates error message when user provides input with unrecognised
        // or non-integral coordinates
        System.out.println("Invalid  format! Please try again." + "\n");
      }
    } while (true);

    // Closes the scanner object
    in.close();
    System.out.println();
    Coordinate userCoordinate = new Coordinate(userX, userY);

    // Generates a random number for the number of events in the world
    int numEvents = generator.nextInt(AXIS_MAX) + MIN_NUM_EVENTS;
    // Defines the comparator with the coordinate provided by the user as the
    // comparator compares events based on their Manhattan distance to the
    // user's input coordinate
    EventComparator eventComparator = new EventComparator(userCoordinate);
    // Defines a priority queue based on the comparator, so as to automatically
    // rearrange events added to it, based on Manhattan distances to user's
    // input coordinates with optimal space and time complexity.
    final PriorityQueue<Event> events = new PriorityQueue<>(numEvents,
        eventComparator);
    // Populates the queue with events
    populateEvents(numEvents, events);
    System.out.println("Closest Events to " + userCoordinate + ":\n");
    Event event;
    // Prints the 5 closest events to the input coordinates, if there are that
    // many in the given format
    for (int i = 0; i < NUM_EVENTS_REQUIRED && !events.isEmpty(); i++) {
      event = events.poll();
      System.out.println(event + ", Distance " +
          event.getCoordinate().getDistance(userCoordinate) + "\n");
    }
  }
}
