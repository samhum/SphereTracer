import java.util.ArrayList;

/**
 * Class to do math commonly repeated math on vectors such as the dot product,
 * cross product, and sphere tracing as well as basic operations like adding,
 * subtracting, and multiplying vectors.
 */
public class VectorMath {
	// efficient.
	private final static int NUMBER_OF_STEPS = 5000; // Higher is more accurate but gets much less efficient

	/**
	 * Gets the normal vector to two given vectors. Uses cross product.
	 * 
	 * @param a
	 *            The first vector.
	 * @param b
	 *            The second vector.
	 * @return The cross product normal vector.
	 */
	public static Vector crossProduct(Vector a, Vector b) {
		return new Vector(a.getY() * b.getZ() - a.getZ() * b.getY(), a.getZ() * b.getX() - a.getX() * b.getZ(),
				a.getX() * b.getY() - a.getY() * b.getX());
	}

	

	/**
	 * Calculates the dot product of two vectors.
	 * 
	 * @param a
	 *            The first vector.
	 * @param b
	 *            The second vector.
	 * @return The cross product of the two vectors.
	 */
	public static double dotProduct(Vector a, Vector b) {
		return a.getX() * b.getX() + a.getY() * b.getY() + a.getZ() * b.getZ();
	}

	/**
	 * Multiplies a vector by some given coefficient.
	 * 
	 * @param coefficient
	 *            The coefficient to multiply by.
	 * @param vector
	 *            The vector to be multiplied.
	 * @return The multiplied vector.
	 */
	public static Vector multiply(double coefficient, Vector vector) {
		return new Vector(vector.getX() * coefficient, vector.getY() * coefficient, vector.getZ() * coefficient);
	}

	/**
	 * Subtracts a vector from another vector and returns the result.
	 * 
	 * @param one
	 *            The vector to be subtracted from.
	 * @param two
	 *            The vector that is subtracted.
	 * @return The vector that is the result of the subtraction.
	 */
	public static Vector subtract(Vector one, Vector two) {
		return new Vector(two.getX() - one.getX(), two.getY() - one.getY(), two.getZ() - one.getZ());
	}

	/**
	 * Adds a vector to another vector and returns the result.
	 * 
	 * @param one
	 *            The first vector.
	 * @param two
	 *            The second vector.
	 * @return The vector that is the sum of the two vectors.
	 */
	public static Vector add(Vector one, Vector two) {
		return new Vector(two.getX() + one.getX(), two.getY() + one.getY(), two.getZ() + one.getZ());
	}

	/**
	 * Method that does the sphere tracing.
	 * 
	 * @param point
	 *            The initial position of the ray to sphere trace along.
	 * @param direction
	 *            The direction of the ray to sphere trace along.
	 * @param objects
	 *            The list of objects in the scene.
	 * @return The point on the ray when the sphere trace ends, either by hitting an
	 *         object or going over the maximum number of steps.
	 */
	public static Vertex sphereTrace(Vertex point, Vector direction, ArrayList<Object3D> objects) {

		double distance = Double.MAX_VALUE;
		int counter = 0;
		// loop that keeps the spheres tracing until they hit an object.
		while (distance > 0) {
			// counter to break the while loop after a certain number of steps.
			if (counter > NUMBER_OF_STEPS) {
				counter = 0;
				break;
			}

			// finds the shortest distance to another object distance
			distance = Double.MAX_VALUE;
			for (Object3D object : objects) {
				if (object.distanceFrom(point) < distance) {
					distance = object.distanceFrom(point);
				}
			}

			point.setPosition(point.getX() + direction.getX() * distance, point.getY() + direction.getY() * distance,
					point.getZ() + direction.getZ() * distance);

			counter++;
		}

		return point;

	}

	/**
	 * Calculates the closest object to the given point.
	 * 
	 * @param point
	 *            The point to calculate the closest object from.
	 * @param objects
	 *            The objects in the scene.
	 * @return The object closest to the point.
	 */
	public static Object3D closestObject(Vertex point, ArrayList<Object3D> objects) {
		Object3D closestObject = null;
		double distance = Double.MAX_VALUE;
		for (Object3D object : objects) {
			if (object.distanceFrom(point) < distance) {
				distance = object.distanceFrom(point);
				closestObject = object;
			}
		}
		// If the point has stepped so far that no objects are closer than positive
		// infinity then return the first object to avoid a null pointer exception.
		if (distance == Double.MAX_VALUE) {
			return objects.get(0);
		}
		return closestObject;
	}

}
