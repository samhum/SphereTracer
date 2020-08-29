import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * Class to define objects read from obj files
 *
 */
public class ObjFileObject implements Object3D {
	private ArrayList<Vertex> vertices;
	private double xTranslation;
	private double yTranslation;
	private double zTranslation;
	private Material material;

	/**
	 * Constructor that takes in a file name and sets the translations to 0
	 * 
	 * @param fileName
	 *            - the file name of the obj file
	 */
	public ObjFileObject(String fileName) {
		xTranslation = 0;
		yTranslation = 0;
		zTranslation = 0;

		try {
			parseObjFile(fileName);
		} catch (IOException e) {
			System.out.println("Unable to parse OBJ file: " + fileName);
			e.printStackTrace();
		}

	}

	/**
	 * Constructor that takes in a list of vertices and sets the translations to 0
	 * 
	 * @param vertices
	 *            - a list of vertices that make up the object
	 */
	public ObjFileObject(ArrayList<Vertex> vertices) {
		xTranslation = 0;
		yTranslation = 0;
		zTranslation = 0;

		this.vertices = vertices;
	}

	/**
	 * Returns list of vertices
	 * 
	 * @return - return the list of vertices
	 */
	public ArrayList<Vertex> getVertices() {
		return vertices;
	}

	/**
	 * Rotates vertices around vector a and by angle degrees
	 * 
	 * @param a
	 *            - the vector to rotate around
	 * @param andle
	 *            - the degree amount to rotate by
	 */
	public void rotate(Vector a, double angle) {
		double x;
		double y;
		double z;
		double s;
		double c;

		for (Vertex vertex : vertices) {
			// Translates vertex back to origin
			vertex.translateXYZ(-xTranslation, -yTranslation, -zTranslation);

			// Rotates vertex
			c = Math.cos(Math.toRadians(angle));
			s = Math.sin(Math.toRadians(angle));

			x = vertex.getX() * (c + (1 - c) * Math.pow(a.getX(), 2))
					+ vertex.getY() * ((1 - c) * a.getX() * a.getY() - s * a.getZ())
					+ vertex.getZ() * ((1 - c) * a.getX() * a.getZ() + s * a.getY());

			y = vertex.getX() * ((1 - c) * a.getX() * a.getY() + s * a.getZ())
					+ vertex.getY() * (c + (1 - c) * Math.pow(a.getY(), 2))
					+ vertex.getZ() * ((1 - c) * a.getY() * a.getZ() - s * a.getX());

			z = vertex.getX() * ((1 - c) * a.getX() * a.getZ() - s * a.getY())
					+ vertex.getY() * ((1 - c) * a.getY() * a.getZ() + s * a.getX())
					+ vertex.getZ() * (c + (1 - c) * Math.pow(a.getZ(), 2));

			vertex.setPosition(x, y, z);

			// translates back to the original position.
			vertex.translateXYZ(xTranslation, yTranslation, zTranslation);
		}

	}

	/**
	 * Translates the object by the amount of the params
	 * 
	 * @param xAmt
	 *            - the x amount to translate this object by
	 * @param yAmt
	 *            - the y amount to translate this object by
	 * @param zAmt
	 *            - the z amount to translate this object by
	 */
	public void translateXYZ(double xAmt, double yAmt, double zAmt) {
		for (Vertex vertex : vertices) {
			vertex.translateXYZ(xAmt, yAmt, zAmt);
		}

		xTranslation += xAmt;
		yTranslation += yAmt;
		zTranslation += zAmt;
	}

	/**
	 * Parses the .obj file given to get the list of vertices for this object.
	 * 
	 * @param fileName
	 *            The filename of the file to parse
	 * @throws IOException
	 */
	private void parseObjFile(String fileName) throws IOException {
		vertices = new ArrayList<Vertex>();

		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			String line = br.readLine();
			String formatted;
			String[] coordinates;

			while (line != null) {
				if (line.substring(0, 2).equals("v ")) {

					formatted = line.substring(2);
					coordinates = formatted.split(" ");

					vertices.add(new Vertex(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]),
							Double.parseDouble(coordinates[2])));
				}
				line = br.readLine();
			}
		} finally {
			br.close();
		}
	}

	/**
	 * Returns the distance from the object to the vertex
	 * 
	 * @param vertex
	 *            - the vertex to calculate distance from
	 * @return - the distance between this object and the vertex
	 */
	@Override
	public double distanceFrom(Vertex vertex) {
		double distance = Double.MAX_VALUE;
		Vector vector;
		for (int k = 0; k < vertices.size(); k++) {
			vector = new Vector(vertices.get(k).getX() - vertex.getX(), vertices.get(k).getY() - vertex.getY(),
					vertices.get(k).getZ() - vertex.getZ());
			if (vector.getMagnitude() - 0.1 < distance) {
				// vertices are spheres of radius 0.1
				distance = vector.getMagnitude() - 0.1;
			}
		}
		return distance;
	}

	/**
	 * Returns null since obj files were only used when color was dependent on
	 * distance.
	 * 
	 * @param toColor
	 *            - the point on the object to return the color of
	 * @param position
	 *            - the position of the camera
	 * @param lightSource
	 *            - the position of the lightsource
	 * @param objects
	 *            - a list of all the objects in the scene
	 * @return - Returns null since obj files were only used when color was
	 *         dependent on distance.
	 */
	public Color getColor(Vertex toColor, Vertex position, Vertex lightSource, ArrayList<Object3D> objects) {
		return null;
	}

	/**
	 * Sets the material of the object
	 * 
	 * @param material
	 *            - the material to set
	 */
	public void setMaterial(Material material) {
		this.material = material;
	}
}
