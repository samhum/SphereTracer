import java.awt.Color;
import java.util.ArrayList;

/**
 * Interface to define 3D objects to put into the scene
 *
 */
public interface Object3D {
	/**
	 * Returns the distance from the object to the vertex
	 * 
	 * @param vertex
	 *            - the vertex to calculate distance from
	 * @return - the distance between this object and the vertex
	 */
	public double distanceFrom(Vertex vertex);

	/**
	 * Translates the object by the amount of the params
	 * 
	 * @param xAmt
	 *            - the x amount to translate this object by
	 * @param yAmt
	 *            - the y amount to translate this object by
	 * @param zAmt
	 *            - the z amount to translate this object by
	 */
	public void translateXYZ(double xAmt, double yAmt, double zAmt);

	/**
	 * Rotates this object around the vector and the angle amount
	 * 
	 * @param a
	 *            - the vector to rotate around
	 * @param angle
	 *            - the angle amount to rotate by
	 */
	public void rotate(Vector a, double angle);

	/**
	 * Returns color of the point toColor on the object
	 * 
	 * @param toColor
	 *            - the point on the object to return the color of
	 * @param position
	 *            - the position of the camera
	 * @param lightSource
	 *            - the position of the lightsource
	 * @param objects
	 *            - a list of all the objects in the scene
	 * @return - the color of toColor on the object
	 */
	public Color getColor(Vertex toColor, Vertex position, Vertex lightSource, ArrayList<Object3D> objects);

	/**
	 * Sets the material of the object
	 * 
	 * @param material
	 *            - the material to set
	 */
	public void setMaterial(Material material);
}
