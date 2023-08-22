package fw.scene;

import org.lwjgl.opengl.*;

import fw.graphics.util.vertex.old.*;
import fw.graphics.window.*;
import fw.scene.shader.*;

/**
 * Port of Test 1 sample using objects
 *
 */
public class Scene2Rectangle extends Scene {

	private ShaderProgram shaderProgram = null;

	private int vertexArray = 0;

	private int elementBuffer = 0;

	@Override
	protected void init() {
		VertexArray3d vArray = new VertexArray3d();
		vArray.addVertex(.5f, .5f);
		vArray.addVertex(.5f, -.5f);
		vArray.addVertex(-.5f, -.5f);
		vArray.addVertex(-.5f, .5f);

		int indices[] = { 0, 1, 3, 1, 2, 3 };

		vertexArray = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vertexArray);

		int vertexBuffer = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexBuffer);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vArray.getVertexes(), GL15.GL_STATIC_DRAW);

		elementBuffer = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, elementBuffer);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indices, GL15.GL_STATIC_DRAW);

		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 12, 0L);
		GL20.glEnableVertexAttribArray(0);

		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);

		// Shaders
		shaderProgram = new ShaderProgram(new BasicVertexShader(), new BasicFragmentShader(1, 0, 0));
		shaderProgram.initalize();

		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
	}

	@Override
	protected void loop() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		shaderProgram.use();
		GL30.glBindVertexArray(vertexArray);

		GL11.glDrawElements(GL11.GL_TRIANGLES, 6, GL11.GL_UNSIGNED_INT, 0);
		GL30.glBindVertexArray(0);
	}

	@Override
	protected void cleanup() {
		// TODO Auto-generated method stub

	}

}
