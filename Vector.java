
/**
 * Entity class for a 3D vector.
 */
public class Vector {

	private double x;
	private double y;
	private double z;

	/**
	 * Constructs a default vector with all 0 components.
	 */
	public Vector() {
		x = 0;
		y = 0;
		z = 0;
	}

	/**
	 * Constructs a vector from vertex a to vertex b.
	 * 
	 * @param a
	 *            The start vertex.
	 * @param b
	 *            The end vertex.
	 */
	public Vector(Vertex a, Vertex b) {
		this.x = b.getX() - a.getX();
		this.y = b.getY() - a.getY();
		this.z = b.getZ() - a.getZ();
	}

	/**
	 * Creates a vector with the the component values given.
	 * 
	 * @param x
	 *            The x component.
	 * @param y
	 *            The y component.
	 * @param z
	 *            The z component.
	 */
	public Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Sets the x component of the vector.
	 * 
	 * @param x
	 *            The x component of the vector
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Sets the y component of the vector.
	 * 
	 * @param y
	 *            The y component of the vector
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Sets the z component of the vector.
	 * 
	 * @param z
	 *            The z component of the vector
	 */
	public void setZ(double z) {
		this.z = z;
	}

	/**
	 * Gets the x component of the vector.
	 * 
	 * @return The x component.
	 */
	public double getX() {
		return x;
	}

	/**
	 * Gets the y component of the vector.
	 * 
	 * @return The y component.
	 */
	public double getY() {
		return y;
	}

	/**
	 * Gets the z component of the vector.
	 * 
	 * @return The z component.
	 */
	public double getZ() {
		return z;
	}

	/**
	 * Sets the vector with the the component values given.
	 * 
	 * @param x
	 *            The x component.
	 * @param y
	 *            The y component.
	 * @param z
	 *            The z component.
	 */
	public void setXYZ(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Make the vector a unit vector so it still points the same direction but has a
	 * magnitude of 1.
	 */
	public void makeUnitVector() {
		double magnitude = getMagnitude();
		if (magnitude != 0) {
			setX(x / magnitude);
			setY(y / magnitude);
			setZ(z / magnitude);
		}
	}

	/**
	 * Calculates the magnitude of the vector.
	 * 
	 * @return The magnitude of the vector.
	 */
	public double getMagnitude() {
		return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2) + Math.pow(getZ(), 2));
	}

	/**
	 * ToString to help with debugging.
	 * 
	 * @return A string representing the vector
	 */
	public String toString() {
		return ("(" + x + ", " + y + ", " + z + ")");
	}
}
