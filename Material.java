import java.awt.Color;
import java.util.ArrayList;

/**
 * Interface for all materials to implement has one method getColor
 *
 */
public interface Material {
	/**
	 * Returns color for the point toColor
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
	 * @return the color to paint toColor
	 */
	public Color getColor(Vertex toColor, Vertex position, Vertex lightSource, ArrayList<Object3D> objects,
			Vector normal);
}
