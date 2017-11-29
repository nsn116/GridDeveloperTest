import java.util.Comparator;

public class EventComparator implements Comparator<Event> {

  // The mutual coordinate used to compare Manhattan Distances with.
  private final Coordinate coordinate;

  /**
   * Defines a comparator for Event objects based on their
   * Manhattan distance with another mutual object
   *
   * @param coordinate The mutual object used to compute Manhattan distances
   *                   with respect to and then compare
   */
  public EventComparator(Coordinate coordinate) {
    this.coordinate = coordinate;
  }

  /**
   * Compares two Event objects based on their Manhattan distances
   * with the Coordinate provided as an instance of the class
   *
   * @param e1 One of the two events to be compared
   * @param e2 The other event to be compared
   * @return -1 if the first event is closer to the mutual Coordinate object,
   * 0 is they are both equally close to the mutual Coordinate object and
   * 1 if the second is closer to the mutual Coordinate object
   */
  @Override
  public int compare(Event e1, Event e2) {
    return Integer.compare(e1.getCoordinate().getDistance(coordinate),
        e2.getCoordinate().getDistance(coordinate));
  }
}
