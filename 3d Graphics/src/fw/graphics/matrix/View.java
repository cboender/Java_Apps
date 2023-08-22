package fw.graphics.matrix;

public class View {

	private View() {

	}

	public static Matrix4 createProjection(double fov, double ratio, double near, double far) {
		double yScale = 1 / Math.tan(Math.toRadians(fov / 2));
		double xScale = yScale / ratio;
		double frustrum = far - near;

		Matrix4 matrix4 = new Matrix4();
		matrix4.setValue(0, 0, xScale);
		matrix4.setValue(1, 1, yScale);
		matrix4.setValue(2, 2, -((far + near) / frustrum));
		matrix4.setValue(3, 2, -1);
		matrix4.setValue(2, 3, -((2 * near * far) / frustrum));
//		matrix4.setValue(3, 3, -1);
		return matrix4;
	}

}
