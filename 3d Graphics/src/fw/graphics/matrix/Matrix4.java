package fw.graphics.matrix;

import java.util.*;

public class Matrix4 {

	private float[] matrix = null;

	protected Matrix4() {
		matrix = new float[16];
	}

	protected Matrix4(Matrix4 matrix) {
		this.matrix = Arrays.copyOf(matrix.getMatrix(), 16);
	}

	public void setValue(int x, int y, double value) {
		setValue(x, y, (float) value);
	}

	public void setValue(int x, int y, float value) {
		// Width of matrix times the row + the column
		int index = (4 * y) + x;

		matrix[index] = value;
	}

	public float getValue(int x, int y) {
		int index = (4 * y) + x;
		return matrix[index];
	}

	public float[] getMatrix() {
		return matrix;
	}

	public void printMatrix() {
		System.out.println(matrix.length);
		int index = 0;
		System.out.print("[");
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (y > 0) {
					System.out.print(",");
				}
				System.out.print(matrix[index++]);

			}
			System.out.println();
		}
		System.out.print("]");
	}

	protected Matrix4 multiply(Matrix4 matrix) {
		Matrix4 mat = new Matrix4();

		for (int i = 0; i < this.matrix.length; i++) {
			int x = i % 4;
			int y = i / 4;
			mat.setValue(x, y, calculate(x, y, matrix));
		}

		return mat;
	}

	private float calculate(int x, int y, Matrix4 other) {
		float total = 0f;
		for (int i = 0; i < 4; i++) {
			float me = getValue(i, y);
			float them = other.getValue(x, i);
			total += me * them;
		}

		return total;
	}

	public static Matrix4 createScale(float x, float y, float z) {
		Matrix4 mat = new Matrix4();
		mat.setValue(0, 0, x);
		mat.setValue(1, 1, y);
		mat.setValue(2, 2, z);
		mat.setValue(3, 3, 1);
		return mat;
	}

	public static Matrix4 createTranslate(float x, float y, float z) {
		Matrix4 mat = new Matrix4();
		mat.setValue(0, 0, 1);
		mat.setValue(1, 1, 1);
		mat.setValue(2, 2, 1);
		mat.setValue(3, 3, 1);

		mat.setValue(3, 0, x);
		mat.setValue(3, 1, y);
		mat.setValue(3, 2, z);
		return mat;
	}

	public static Matrix4 createRotationDeg(double angle, float x, float y, float z) {
		return createRotation(Math.toRadians(angle), x, y, z);
	}

	public static Matrix4 createRotation(double angle, float x, float y, float z) {
		Matrix4 mat = new Matrix4();
		double cos = Math.cos(angle);
		double invCos = 1 - cos;
		double sin = Math.sin(angle);
		mat.setValue(0, 0, cos + (Math.pow(x, 2) * invCos));
		mat.setValue(0, 1, (x * y * invCos) - (z * sin));
		mat.setValue(0, 2, (x * z * invCos) + (y * sin));

		mat.setValue(1, 0, (y * x * invCos) + (z * sin));
		mat.setValue(1, 1, cos + (Math.pow(y, 2) * invCos));
		mat.setValue(1, 2, (y * z * invCos) - (x * sin));

		mat.setValue(2, 0, (z * x * invCos) + (z * sin));
		mat.setValue(2, 1, (z * y * invCos) + (x * sin));
		mat.setValue(2, 2, cos + (Math.pow(z, 2) * invCos));

		mat.setValue(3, 3, 1);

		return mat;
	}

	public static Matrix4 merge(Matrix4... matrixes) {
		if (matrixes.length == 0) {
			return new Matrix4();
		}

		Matrix4 mat = new Matrix4(matrixes[0]);

		for (int i = 1; i < matrixes.length; i++) {
			mat = mat.multiply(matrixes[i]);
		}

		return mat;
	}

	public static Matrix4 createEmpty() {
		return new Matrix4();
	}

}
