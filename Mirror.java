import java.awt.Color;
import java.util.ArrayList;

/**
 * Class to define mirror material that gets color from reflection of ray
 *
 */
public class Mirror implements Material {
	private final static double APPOXIMATION = 0.05; // lower is more accurate but lower is slightly less
														// efficient.

	/**
	 * Returns color of point hit by reflection
	 * 
	 * @param toColor
	 *            - is the point in the scene to be colored
	 * @param position
	 *            - the position of the camera
	 * @param lightSource
	 *            - the position of the lightsource
	 * @param objects
	 *            - list of objects in the scene
	 * @param normal
	 *            - normal vector to surface
	 * @return - Returns color of point hit by reflection
	 */
	@Override
	public Color getColor(Vertex toColor, Vertex position, Vertex lightSource, ArrayList<Object3D> objects,
			Vector normal) {
		Vector w = normal;
		w.makeUnitVector();

		Vector l = new Vector(position.getX() - toColor.getX(), position.getY() - toColor.getY(),
				position.getZ() - toColor.getZ());
		l.makeUnitVector();

		Vector r = new Vector((2 * w.getX()) - l.getX(), (2 * w.getY()) - l.getY(), (2 * w.getZ()) - l.getZ());

		Object3D closestObject = new Sphere(1);
		r.makeUnitVector();
		toColor.setPosition(toColor.getX() + r.getX() * APPOXIMATION, toColor.getY() + r.getY() * APPOXIMATION,
				toColor.getZ() + r.getZ() * APPOXIMATION);
		// Sphere traces reflection
		toColor = VectorMath.sphereTrace(toColor, r, objects);
		// Gets closest object
		closestObject = VectorMath.closestObject(toColor, objects);
		// If intersection with object find its color allows for reflections of
		// reflections
		if (closestObject.distanceFrom(toColor) == 0) {
			return closestObject.getColor(toColor, position, lightSource, objects);
		}
		return Color.BLACK;
	}

}
