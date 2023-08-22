package fw.graphics.util.vertex;

import java.util.*;

public class VertexData {

	private int stride = 0;

	private Map<String, VertexArray> vertexArrays = new LinkedHashMap<>();

	public void addData(float... data) {
		int offset = 0;
		for (VertexArray array : vertexArrays.values()) {
			float[] vertexPoints = new float[array.getSize()];
			for (int i = 0; i < array.getSize(); i++) {
				vertexPoints[i] = data[i + offset];
			}
			array.addVertex(vertexPoints);
			offset += array.getSize();
		}
	}

	public VertexArray createVertexArray(String name, int size) {
		stride += size;

		VertexArray array = new VertexArray(size);
		vertexArrays.put(name, array);
		return array;
	}

	public void addVertexPoints(String name, float... points) {
		VertexArray array = vertexArrays.get(name);
		if (array == null) {
			return;
		}
		array.addVertex(points);
	}

	private int getLength() {
		int length = -1;
		for (VertexArray array : vertexArrays.values()) {
			if (length == -1) {
				length = array.getCount();
			}
			if (length != array.getCount()) {
				throw new RuntimeException("The Vertex arrays in the data must be equal");
			}
		}
		return length;
	}

	public float[] getVertexes() {
		int length = getLength();
		if (length == -1) {
			return new float[0];
		}

		float[] v = new float[stride * length];

		int i = 0;
		for (int pointer = 0; pointer < length; pointer++) {
			for (VertexArray array : vertexArrays.values()) {
				Vertex vertex = array.getVertex(pointer);
				for (float f : vertex.getPoints()) {
					v[i++] = f;
				}
			}
		}

		return v;
	}

	public int getStride() {
		return stride;
	}

}
