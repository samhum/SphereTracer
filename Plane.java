import java.awt.Color;
import java.util.ArrayList;

/**
 * Object3D that is a plane parallel to the XY, YZ, or XZ axis.
 */
public class Plane implements Object3D {
	private Vector normal;
	private Vertex onPlane;
	private Material material;

	/**
	 * Constructor that creates a plane with the given normal and point on the
	 * plane. The normal must in the direction of the X, Y, or Z axis.
	 * 
	 * @param normal
	 *            The normal vector to the plane.
	 * @param onPlane
	 *            A point on the plane.
	 */
	public Plane(Vector normal, Vertex onPlane) {
		this.normal = normal;
		this.onPlane = onPlane;
	}

	/**
	 * Calculates the distance between the plane and the given vertex.
	 * 
	 * @param vertex
	 *            The vertex to calculate the distance from.
	 * @return The distance.
	 */
	@Override
	public double distanceFrom(Vertex vertex) {
		if (normal.getX() * Math.abs(vertex.getX() - onPlane.getX())
				+ normal.getY() * Math.abs(vertex.getY() - onPlane.getY())
				+ normal.getZ() * Math.abs(vertex.getZ() - onPlane.getZ()) < 0.01) {
			return 0;
		}
		return normal.getX() * Math.abs(vertex.getX() - onPlane.getX())
				+ normal.getY() * Math.abs(vertex.getY() - onPlane.getY())
				+ normal.getZ() * Math.abs(vertex.getZ() - onPlane.getZ());
	}

	/**
	 * Translates the plane by some amount in the X, Y, and Z.
	 * 
	 * @param xAmt
	 *            The amount to translate along the X axis.
	 * @param yAmt
	 *            The amount to translate along the Y axis.
	 * @param zAmt
	 *            The amount to translate along the Z axis.
	 */
	@Override
	public void translateXYZ(double xAmt, double yAmt, double zAmt) {
		onPlane.setPosition(onPlane.getX() + xAmt, onPlane.getY() + yAmt, onPlane.getZ() + zAmt);

	}

	/**
	 * Gets the color at a point on the plane based on the material assigned to the
	 * plane.
	 * 
	 * @param toColor
	 *            The position on the plane to get the color of.
	 * @param position
	 *            The position of the camera.
	 * @param lightSource
	 *            The position of the light source.
	 * @param objects
	 *            The ArrayList of object3ds in the scene.
	 * @return The color for the vertex toColor.
	 */
	public Color getColor(Vertex toColor, Vertex position, Vertex lightSource, ArrayList<Object3D> objects) {
		return material.getColor(toColor, position, lightSource, objects, normal);
	}

	/**
	 * Gets the normal vector.
	 * 
	 * @return The normal vector.
	 */
	public Vector getNormal() {
		return normal;
	}

	/**
	 * Gets a point on the plane.
	 * 
	 * @return The point on the plane.
	 */
	public Vertex getVertex() {
		return onPlane;
	}

	@Override
	public void rotate(Vector a, double angle) {

	}

	/**
	 * Sets the material of the plane.
	 * 
	 * @param material
	 *            The material the plane is set to.
	 */
	public void setMaterial(Material material) {
		this.material = material;
	}

}
