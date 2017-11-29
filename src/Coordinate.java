public class Coordinate {

  // x coordinate of a point of Coordinate object
  private final int x;
  // y coordinate of a point of Coordinate object
  private final int y;

  /**
   * Parameterised constructor that sets a Coordinate object with the given
   * coordinates
   *
   * @param x x-coordinate of the object to be created
   * @param y y-coordinate of the object to be created
   */
  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Computes the Manhattan distance between two points
   *
   * @param p Coordinate from which the Manhattan distance is to be
   *          computed with
   *          respect to the given coordinate
   * @return
   */
  public int getDistance(Coordinate p) {
    return Math.abs(p.x - this.x) + Math.abs(p.y - this.y);
  }

  /**
   * Defines equality over Coordinate objects
   *
   * @param other Coordinate object with whom equality is to be tested
   * @return true if the two coordinates are equal, i.e. they have
   * the same coordinates (x and y), false otherwise
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Coordinate)) {
      return false;
    }
    Coordinate otherCoordinate = (Coordinate) other;
    return this.x == otherCoordinate.x && this.y == otherCoordinate.y;
  }

  /**
   * Defines the hashcode of a Coordinate object for equality
   *
   * @return integer value representing the hashcode of the object
   */
  @Override
  public int hashCode() {
    int result = x;
    result = 31 * result + y;
    return result;
  }

  /**
   * Defines the general string representation of a Coordinate object
   *
   * @return the string representing a Coordinate object
   */
  @Override
  public String toString() {
    return "(" + this.x + ", " + this.y + ")";
  }
}
