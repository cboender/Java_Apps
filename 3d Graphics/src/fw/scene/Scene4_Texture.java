package fw.scene;

import org.lwjgl.opengl.*;

import fw.graphics.util.vertex.*;
import fw.graphics.window.*;
import fw.scene.shader.*;
import fw.scene.shader.custom.*;
import fw.scene.texture.*;

/**
 * Port of Test 1 sample using objects
 *
 */
public class Scene4_Texture extends Scene {

	private ShaderProgram shaderProgram = null;

	private int vertexArray = 0;

	Texture texture = null;
	Texture texture2 = null;

	@Override
	protected void init() {
		GL11.glClearColor(0.2f, 0.3f, 0.3f, 1.0f);
		VertexData vData = new VertexData();
		vData.createVertexArray("coord", 2);
		vData.createVertexArray("color", 3);
		vData.createVertexArray("texture", 2);

		vData.addData(.5f, .5f, 1f, 0f, 0f, 1f, 1f);
		vData.addData(.5f, -.5f, 1f, 0f, 0f, 1f, 0f);
		vData.addData(-.5f, -.5f, 0f, 0f, 1f, 0f, 0f);
		vData.addData(-.5f, .5f, 0f, 1f, 1f, 0f, 1f);

		int indices[] = { 0, 1, 3, 1, 2, 3 };

		vertexArray = GL30.glGenVertexArrays();
		int vertexBuffer = GL15.glGenBuffers();
		int elementBuffer = GL15.glGenBuffers();

		GL30.glBindVertexArray(vertexArray);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexBuffer);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vData.getVertexes(), GL15.GL_STATIC_DRAW);

		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, elementBuffer);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indices, GL15.GL_STATIC_DRAW);

		long offset = 0L;
		GL20.glVertexAttribPointer(0, 2, GL11.GL_FLOAT, false, (vData.getStride() * 4), offset * 4L);
		GL20.glEnableVertexAttribArray(0);
		offset += 2;
		GL20.glVertexAttribPointer(1, 3, GL11.GL_FLOAT, false, (vData.getStride() * 4), offset * 4L);
		GL20.glEnableVertexAttribArray(1);
		offset += 3;
		GL20.glVertexAttribPointer(2, 2, GL11.GL_FLOAT, false, (vData.getStride() * 4), offset * 4L);
		GL20.glEnableVertexAttribArray(2);

		// GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);

		texture = new Texture("JPG", "wall.jpg", GL13.GL_TEXTURE0);
		texture.initialize();
		texture2 = new Texture("jpg", "smile.png", GL13.GL_TEXTURE1);
		texture2.initialize();

		// Shaders
		shaderProgram = new ShaderProgram(new TextureVertexShader(), new TextureFragmentShader());
		shaderProgram.initalize();
	}

	@Override
	protected void loop() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		shaderProgram.use();
		texture.use();
		texture2.use();
		shaderProgram.setUniformValue("texture1", 0);
		shaderProgram.setUniformValue("texture2", 1);
		GL30.glBindVertexArray(vertexArray);

		GL11.glDrawElements(GL11.GL_TRIANGLES, 6, GL11.GL_UNSIGNED_INT, 0);

		GL30.glBindVertexArray(0);
	}

	@Override
	protected void cleanup() {
		// TODO Auto-generated method stub
	}

}
