package fw.graphics.util.vertex;

import java.util.*;

public class VertexArray {
	private int size = 0;

	private List<Vertex> vertexes = new ArrayList<>();

	public VertexArray(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	protected void addVertex(float... values) {
		Vertex vertex = new Vertex(size);
		vertex.addPoints(values);
		vertexes.add(vertex);
	}

	public int getCount() {
		return vertexes.size();
	}

	protected Vertex getVertex(int index) {
		return vertexes.get(index);
	}
}
