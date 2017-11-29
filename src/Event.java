import java.util.PriorityQueue;

public class Event {

  // A static field that keep tracks of the ID of the next Event
  private static int uniqueID = 1;
  // A static field that is used to pad zeroes at the beginning of the event ID
  // when printing it.
  private static int MAX_ID_DIGITS = 3;

  // A queue that prioritises tickets based on their price, the cheapest one
  // being at the head of the queue
  private final PriorityQueue<Double> ticketPrices = new PriorityQueue<>();
  // The coordinate storing the location of the event
  private final Coordinate c;
  // Event ID of an event
  private final int eventID;

  /**
   * Constructs an Event object at a given coordinate, and
   * allots the Event an ID using uniqueID
   *
   * @param c Coordinate at which the event is to be created
   */
  public Event(Coordinate c) {
    this.c = c;
    this.eventID = uniqueID++;
  }

  /**
   * Retrieves the coordinate of the Event's location
   *
   * @return The coordinate of the Event
   */
  public Coordinate getCoordinate() {
    return c;
  }

  /**
   * Adds a ticket for an Event
   *
   * @param newTicketPrice The price of the ticket for the Event
   */
  public void addTicket(double newTicketPrice) {
    ticketPrices.add(newTicketPrice);
  }

  /**
   * Retrieves the price of the cheapest ticket for the Event
   *
   * @return The price of the cheapest ticket for the Event
   */
  public double cheapestTicketPrice() {
    return ticketPrices.peek();
  }

  /**
   * Defines the general string representation of an Event object
   *
   * @return String representation of the Event
   */
  @Override
  public String toString() {
    String zeroPadding = "";
    for (int i = 0; i < MAX_ID_DIGITS - ((int) Math.log10(eventID)) - 1; i++) {
      zeroPadding += "0";
    }
    String priceRelated;
    if (ticketPrices.size() == 0) {
      priceRelated = "TICKETS UNAVAILABLE";
    } else {
      priceRelated = "$" + this.cheapestTicketPrice();
    }
    return "Event " + zeroPadding + eventID + " - " + priceRelated;
  }
}
