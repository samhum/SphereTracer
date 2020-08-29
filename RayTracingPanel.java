import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * JPanel to hold the cube and perform the rotations on it
 * 
 * @author elliothardwick
 *
 */
public class RayTracingPanel extends JPanel {

	private Camera camera;
	private ArrayList<Object3D> objects;
	private Vertex lightSource;

	/**
	 * Constructor that sets up the object3ds to render in the scene.
	 */
	public RayTracingPanel(Camera camera) {
		// The following is another way to guarantee correct size.
		setPreferredSize(new Dimension(500, 500));

		setBackground(Color.lightGray);
		this.camera = camera;

		// This is adding the objects to the scene
		objects = new ArrayList<Object3D>();

		objects.add(new Sphere(1));
		objects.get(0).setMaterial(new Diffuse(Color.BLUE));

		objects.add(new Sphere(2));
		objects.get(1).translateXYZ(-1, 1, -3);
		objects.get(1).setMaterial(new Mirror());

		objects.add(new Sphere(2));
		objects.get(2).translateXYZ(-1, 1, 3);
		objects.get(2).setMaterial(new Mirror());

		objects.add(new Plane(new Vector(0, 1, 0), new Vertex(0, -1, 0)));
		objects.get(3).setMaterial(new CheckerBoard());

		lightSource = new Vertex(10, 50, 0);

	}

	/**
	 * Tells the camera to draw the objects.
	 * 
	 * @param g
	 *            The graphics from the main class.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // without this no background color set.
		Graphics2D g2d = (Graphics2D) g; // cast so we can use JAVA2D.

		camera.draw(g2d, objects, lightSource);

	}
}
