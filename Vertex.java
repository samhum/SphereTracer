/**
 * An entity class for a vertex.
 */
public class Vertex {

	private double x;
	private double y;
	private double z;

	/**
	 * Creates a vertex with the default position (0,0,0)
	 */
	public Vertex() {
		x = 0;
		y = 0;
		z = 0;
	}

	/**
	 * Creates a vertex with the given x, y, z coordinates.
	 * 
	 * @param x
	 *            The x coordinate.
	 * @param y
	 *            The y coordinate.
	 * @param z
	 *            The z coordinate.
	 */
	public Vertex(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Sets the x value.
	 * 
	 * @param x
	 *            The new x value.
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Sets the y value.
	 * 
	 * @param y
	 *            The new y value.
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Sets the z value.
	 * 
	 * @param z
	 *            The new z value.
	 */
	public void setZ(double z) {
		this.z = z;
	}

	/**
	 * Sets the vertex to the given x, y, z coordinates.
	 * 
	 * @param x
	 *            The new x coordinate.
	 * @param y
	 *            The new y coordinate.
	 * @param z
	 *            The new z coordinate.
	 */
	public void setPosition(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Gets the x value.
	 * 
	 * @return The x value.
	 */
	public double getX() {
		return x;
	}

	/**
	 * Gets the y value.
	 * 
	 * @return The y value.
	 */
	public double getY() {
		return y;
	}

	/**
	 * Gets the z value.
	 * 
	 * @return The z value.
	 */
	public double getZ() {
		return z;
	}

	/**
	 * Translates the vertex by the given amounts.
	 * 
	 * @param xAmt
	 *            The amount to translate on the X axis.
	 * @param yAmt
	 *            The amount to translate on the Y axis.
	 * @param zAmt
	 *            The amount to translate on the Z axis.
	 */
	public void translateXYZ(double xAmt, double yAmt, double zAmt) {
		x += xAmt;
		y += yAmt;
		z += zAmt;
	}
}
