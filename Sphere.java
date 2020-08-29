import java.awt.Color;
import java.util.ArrayList;

/**
 * Class for a sphere object3d.
 */
public class Sphere implements Object3D {
	private double radius;
	private Vertex center;
	private Material material;

	/**
	 * Creates a sphere with the given radius.
	 * 
	 * @param radius
	 *            The radius of the sphere.
	 */
	public Sphere(double radius) {
		this.radius = radius;
		center = new Vertex(0, 0, 0);
	}

	/**
	 * Calculates the distance between the sphere and the given vertex.
	 * 
	 * @param vertex
	 *            The vertex to calculate the distance from.
	 * @return The distance.
	 */
	@Override
	public double distanceFrom(Vertex vertex) {
		return Math.sqrt(Math.pow(vertex.getX() - center.getX(), 2) + Math.pow(vertex.getY() - center.getY(), 2)
				+ Math.pow(vertex.getZ() - center.getZ(), 2)) - radius;
	}

	/**
	 * Translates the sphere by some amount in the X, Y, and Z.
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
		center.setPosition(center.getX() + xAmt, center.getY() + yAmt, center.getZ() + zAmt);
	}

	/**
	 * Gets the color at a point on the s based on the material assigned to the
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
		// calculating the reflected ray r.
		Vector normal = new Vector(toColor.getX() - center.getX(), toColor.getY() - center.getY(),
				toColor.getZ() - center.getZ());
		normal.makeUnitVector();
		
		return material.getColor(toColor, position, lightSource, objects, normal);

	}

	@Override
	public void rotate(Vector a, double angle) {
		// rotating a sphere doesn't do anything
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

}