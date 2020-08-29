import java.awt.*;
import javax.swing.*;

/**
 * Creates the JFrame to hold the ray tracing panel for the cube renderer
 * assignment.
 * 
 * @author elliothardwick
 * @author samhum
 */
public class RayTracing {
	/**
	 * Makes the JFrame and makes and adds the panels.
	 * 
	 * @param args
	 *            unused parameter.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Sphere Tracer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// sets up the camera's position and direction to point in.
		Camera camera = new Camera(new Vertex(4, 3, 4), new Vector(-0.577, -0.3, -0.577));

		RayTracingPanel rayTracingPanel = new RayTracingPanel(camera);

		frame.setLayout(new BorderLayout());
		frame.add(rayTracingPanel, BorderLayout.CENTER);

		frame.pack();
		frame.setVisible(true);
	}
}
