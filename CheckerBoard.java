import java.awt.Color;
import java.util.ArrayList;

/**
 * Class to define checkerboard currently implemented for XZ plane but can be
 * generalized more has colors corresponding to pixel location
 * 
 *
 */
public class CheckerBoard implements Material {
	private final static double APPOXIMATION = 0.05; // lower is more accurate but lower is slightly less

	/**
	 * Returns color of pixel on checkerboard hit by light
	 * 
	 * @param toColor
	 *            - is the point in the scene to be colored
	 * @param position
	 *            - the position of the camera
	 * @param lightSource
	 *            - the position of the light source
	 * @param objects
	 *            - list of objects in the scene
	 * @param normal
	 *            - normal vector to surface
	 * @return color of the checker position with shading dark grey if x&z even or
	 *         x&z odd and light grey if not black if in shadow
	 */
	@Override
	public Color getColor(Vertex toColor, Vertex position, Vertex lightSource, ArrayList<Object3D> objects,
			Vector normal) {
		Color faceColor;
		if ((int) (toColor.getX() + 1000) % 2 == 0 && (int) (toColor.getZ() + 1000) % 2 == 0) {
			faceColor = Color.DARK_GRAY;
		} else if ((int) (toColor.getX() + 1000) % 2 != 0 && (int) (toColor.getZ() + 1000) % 2 != 0) {
			faceColor = Color.DARK_GRAY;
		} else {
			faceColor = Color.LIGHT_GRAY;
		}

		Vector faceToLight = new Vector(lightSource.getX() - toColor.getX(), lightSource.getY() - toColor.getY(),
				lightSource.getZ() - toColor.getZ());

		Vector lightToFace = new Vector(toColor.getX() - lightSource.getX(), toColor.getY() - lightSource.getY(),
				toColor.getZ() - lightSource.getZ());

		// Sphere Tracing towards light

		faceToLight.makeUnitVector();

		faceToLight.makeUnitVector();
		toColor.setPosition(toColor.getX() + faceToLight.getX() * APPOXIMATION,
				toColor.getY() + faceToLight.getY() * APPOXIMATION, toColor.getZ() + faceToLight.getZ() * APPOXIMATION);

		toColor = VectorMath.sphereTrace(toColor, faceToLight, objects);
		Object3D closestObject = VectorMath.closestObject(toColor, objects);
		if (closestObject.distanceFrom(toColor) == 0) {
			return Color.BLACK;
		}

		// return faceColor;
		// get the angle between the vector from the face to the light and the normal
		// vector of the face.
		double angle = Math.acos(Math.abs(VectorMath.dotProduct(normal, lightToFace))
				/ (normal.getMagnitude() * lightToFace.getMagnitude()));
		// 1 means the light is directly on the normal vector, 0 is when the face is 90
		// degrees from the light.
		double lightAmount = (Math.PI / 2 - angle) / (Math.PI / 2);

		return new Color((int) (faceColor.getRed() * lightAmount), (int) (faceColor.getGreen() * lightAmount),
				(int) (faceColor.getBlue() * lightAmount));

	}

}
