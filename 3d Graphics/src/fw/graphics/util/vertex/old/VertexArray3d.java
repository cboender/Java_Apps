package fw.graphics.util.vertex.old;

import java.util.*;

public class VertexArray3d {

	private List<Vertex3d> vertexes = null;

	public VertexArray3d() {
		vertexes = new ArrayList<>();
	}

	public void addVertex(float x, float y) {
		vertexes.add(new Vertex3d(x, y));
	}

	public void addVertex(float x, float y, float z) {
		vertexes.add(new Vertex3d(x, y, z));
	}

	public void addVertex(Vertex3d vertex) {
		this.vertexes.add(vertex);
	}

	public float[] getVertexes() {
		float[] v = new float[vertexes.size() * 3];

		int i = 0;
		for (Vertex3d vertex : vertexes) {
			v[i++] = vertex.x;
			v[i++] = vertex.y;
			v[i++] = vertex.z;
		}
		return v;
	}

	public int getCount() {
		return vertexes.size();
	}

}
