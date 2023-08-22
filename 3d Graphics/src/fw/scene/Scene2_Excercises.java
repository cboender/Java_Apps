package fw.scene;

import org.lwjgl.opengl.*;

import fw.graphics.util.vertex.old.*;
import fw.graphics.window.*;
import fw.scene.shader.*;

/**
 * Port of Test 1 sample using objects
 *
 */
public class Scene2_Excercises extends Scene {

	private ShaderProgram shaderProgram = null;

	private ShaderProgram shaderProgram2 = null;

	private int vertexArray[] = new int[2];

	@Override
	protected void init() {
		VertexArray3d vArray = new VertexArray3d();
		vArray.addVertex(-1f, -.5f);
		vArray.addVertex(0f, -.5f);
		vArray.addVertex(-.5f, .5f);

		VertexArray3d vArray2 = new VertexArray3d();
		vArray2.addVertex(0f, -.5f);
		vArray2.addVertex(1f, -.5f);
		vArray2.addVertex(.5f, .5f);

		GL30.glGenVertexArrays(vertexArray);
		GL30.glBindVertexArray(vertexArray[0]);

		int vertexBuffer[] = new int[2];
		GL15.glGenBuffers(vertexBuffer);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexBuffer[0]);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vArray.getVertexes(), GL15.GL_STATIC_DRAW);

		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 12, 0L);
		GL20.glEnableVertexAttribArray(0);

		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);

		GL30.glBindVertexArray(vertexArray[1]);

		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexBuffer[1]);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vArray2.getVertexes(), GL15.GL_STATIC_DRAW);

		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 12, 0L);
		GL20.glEnableVertexAttribArray(0);

		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);

		// Shaders
		shaderProgram = new ShaderProgram(new BasicVertexShader(), new BasicFragmentShader(0f, 0f, 1f));
		shaderProgram.initalize();

		shaderProgram2 = new ShaderProgram(new BasicVertexShader(), new BasicFragmentShader(1f, 0f, 0f));
		shaderProgram2.initalize();
	}

	@Override
	protected void loop() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		shaderProgram.use();
		GL30.glBindVertexArray(vertexArray[0]);
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 3);

		shaderProgram2.use();
		GL30.glBindVertexArray(vertexArray[1]);
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 3);
	}

	@Override
	protected void cleanup() {
		// TODO Auto-generated method stub

	}

}
