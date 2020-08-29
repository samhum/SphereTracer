import java.awt.Color;
import java.util.ArrayList;

/**
 * 
 * Class to define diffuse materials, pixels have color based on how much light
 * reflect determined by angle to light source and normal
 *
 */
public class Diffuse implements Material {
	private Color baseColor;
	private final static double APPOXIMATION = 0.05; // lower is more accurate but lower is slightly less
	// efficient.

	public Diffuse(Color baseColor) {
		this.baseColor = baseColor;
	}

	public Diffuse() {
		baseColor = Color.BLUE;
	}

	/**
	 * Returns color of the point toColor based on angle light hits point
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
	 * @return - returns color of the point toColor based on angle light hits point
	 */
	@Override
	public Color getColor(Vertex toColor, Vertex position, Vertex lightSource, ArrayList<Object3D> objects,
			Vector normal) {

		Vector faceToLight = new Vector(lightSource.getX() - toColor.getX(), lightSource.getY() - toColor.getY(),
				lightSource.getZ() - toColor.getZ());

		Vector lightToFace = new Vector(toColor.getX() - lightSource.getX(), toColor.getY() - lightSource.getY(),
				toColor.getZ() - lightSource.getZ());

		if (VectorMath.dotProduct(new Vector(lightSource, toColor), normal) > 0) {
			return Color.BLACK;
		}
		// Sphere Tracing towards light
		faceToLight.makeUnitVector();
		toColor.setPosition(toColor.getX() + faceToLight.getX() * APPOXIMATION,
				toColor.getY() + faceToLight.getY() * APPOXIMATION, toColor.getZ() + faceToLight.getZ() * APPOXIMATION);

		toColor = VectorMath.sphereTrace(toColor, faceToLight, objects);
		Object3D closestObject = VectorMath.closestObject(toColor, objects);
		if (closestObject.distanceFrom(toColor) == 0) {
			return Color.BLACK;
		}
		double angle = Math.acos(Math.abs(VectorMath.dotProduct(normal, lightToFace))
				/ (normal.getMagnitude() * lightToFace.getMagnitude()));
		// 1 means the light is directly on the normal vector, 0 is when the face is 90
		// degrees from the light.
		double lightAmount = (Math.PI / 2 - angle) / (Math.PI / 2);

		return new Color((int) (baseColor.getRed() * lightAmount), (int) (baseColor.getGreen() * lightAmount),
				(int) (baseColor.getBlue() * lightAmount));

	}

	/**
	 * Sets the base color to be the param
	 * 
	 * @param baseColor
	 *            - the base color to set
	 */
	public void setBaseColor(Color baseColor) {
		this.baseColor = baseColor;
	}

}
