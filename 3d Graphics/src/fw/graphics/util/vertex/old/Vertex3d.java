package fw.graphics.util.vertex.old;

public class Vertex3d {
	public float x = 0f;

	public float y = 0f;

	public float z = 0f;

	public Vertex3d(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * 2d Vertex
	 * 
	 * @param x
	 * @param y
	 */
	public Vertex3d(float x, float y) {
		this.x = x;
		this.y = y;
	}
}
