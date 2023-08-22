package fw.scene;

import org.lwjgl.opengl.*;

import fw.graphics.util.vertex.old.*;
import fw.graphics.window.*;
import fw.scene.shader.*;

/**
 * Port of Test 1 sample using objects
 *
 */
public class Scene2_Triangle extends Scene {

	private ShaderProgram shaderProgram = null;

	private int vertexArray = 0;

	@Override
	protected void init() {
		VertexArray3d vArray = new VertexArray3d();
		vArray.addVertex(-.5f, -.5f);
		vArray.addVertex(.5f, -.5f);
		vArray.addVertex(0f, .5f);

		vertexArray = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vertexArray);

		int vertexBuffer = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexBuffer);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vArray.getVertexes(), GL15.GL_STATIC_DRAW);

		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 12, 0L);
		GL20.glEnableVertexAttribArray(0);

		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);

		// Shaders
		shaderProgram = new ShaderProgram(new BasicVertexShader(), new BasicFragmentShader(0, 1, 0));
		shaderProgram.initalize();

	}

	@Override
	protected void loop() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		shaderProgram.use();
		GL30.glBindVertexArray(vertexArray);
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 3);
	}

	@Override
	protected void cleanup() {
		// TODO Auto-generated method stub

	}

}
