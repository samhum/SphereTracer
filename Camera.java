import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicArrowButton;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that represents the camera. Responsible for drawing scene
 * 
 *
 */
public class Camera {
	private Vertex position;
	private Vector direction;

	/**
	 * Constructor that takes in the direction and location of the camera and
	 * initializes the polygon.
	 * 
	 * @param position
	 *            The position of the camera.
	 * @param direction
	 *            The vector showing the direction the camera is facing.
	 */
	public Camera(Vertex position, Vector direction) {
		this.position = position;
		this.direction = new Vector(direction.getX(), direction.getY(), direction.getZ());
	}

	/**
	 * Draws the given face to the given graphics 2d.
	 * 
	 * @param g2d
	 *            The graphics to draw on.
	 * @param objects
	 *            - list of objects to draw in scene
	 */
	public void draw(Graphics2D g2d, ArrayList<Object3D> objects, Vertex lightSource) {
		double height = 800;
		double width = 1200;

		Object3D closestObject = null;
		/*
		 * SETUP FOR DRAWING RAYS THROUGH PIXEL CENTERS
		 */
		Vector t = new Vector(direction.getX(), direction.getY(), direction.getZ());
		t.makeUnitVector();
		Vector w = new Vector(0, 1, 0);

		Vector b = VectorMath.crossProduct(w, t);
		b.makeUnitVector();

		Vector v = VectorMath.crossProduct(t, b);

		double gx = Math.tan((Math.PI / 2) / 2);
		double gy = gx * height / width;

		Vector qx = VectorMath.multiply((2 * gx) / (width - 1), b);
		Vector qy = VectorMath.multiply(-(2 * gy) / (height - 1), v); // seems like qy should be negative
		Vector p1m = VectorMath.subtract(VectorMath.subtract(t, VectorMath.multiply(gx, b)),
				VectorMath.multiply(gy, v));

		/*
		 * SETUP FOR SPHERE TRACING
		 */

		Vector throughPixel;
		Vertex point = new Vertex(position.getX(), position.getY(), position.getZ());

		BufferedImage bf = new BufferedImage((int) width, (int) height, BufferedImage.TYPE_INT_RGB);

		double counter = 0;
		// iterating through rows
		for (int i = 0; i < height; i++) {
			// iterating through columns
			for (int j = 0; j < width; j++) {
				if(counter % (width*height/400) == 0) {
					System.out.println(counter/(width*height)*100 +"/" + 100);
				}
				counter++;
				// vector in the direction of the middle of pixel i,j from the camera
				throughPixel = VectorMath.add(VectorMath.add(p1m, VectorMath.multiply(j, qx)),
						VectorMath.multiply(i, qy));
				throughPixel.makeUnitVector();
				Vertex cameraPosition = new Vertex(position.getX(), position.getY(), position.getZ());
				point = VectorMath.sphereTrace(cameraPosition, throughPixel, objects);
				closestObject = VectorMath.closestObject(point, objects);
				Color pixelColor = Color.BLACK;
				if (closestObject.distanceFrom(point) == 0) {
					pixelColor = closestObject.getColor(point, position, lightSource, objects);
				}
				bf.setRGB(j, i, pixelColor.getRGB());
			}
		}

		g2d.drawImage(bf, 0, 0, (int) width, (int) height, new BasicArrowButton(0));

		File outputfile = new File("outputImage.png");
		try {
			ImageIO.write(bf, "png", outputfile);
		} catch (IOException e) {
			System.out.println("failed to write image");
			e.printStackTrace();
		}
		System.out.println("done");

	}

}
