package fw.graphics.util.vertex;

public class Vertex {

	private int size = 0;

	private float[] points;

	public Vertex(int size) {
		this.size = size;
		points = new float[size];
	}

	public int getSize() {
		return size;
	}

	public void addPoints(float... points) {
		if (points.length != size) {
			throw new RuntimeException("Vertex must be of the allocated size of " + size);
		}

		for (int i = 0; i < size; i++) {
			this.points[i] = points[i];
		}
	}

	public float[] getPoints() {
		return points;
	}
}
