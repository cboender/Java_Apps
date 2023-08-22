package fw.scene;

import org.lwjgl.opengl.*;

import fw.graphics.util.vertex.old.*;
import fw.graphics.window.*;
import fw.scene.shader.*;
import fw.scene.shader.custom.*;

/**
 * Port of Test 1 sample using objects
 *
 */
public class Scene3_Excercise extends Scene {

	private ShaderProgram shaderProgram = null;

	private int vertexArray[] = new int[2];

	private int elementBuffer = 0;

	@Override
	protected void init() {
		VertexArray3d vArray = new VertexArray3d();
		vArray.addVertex(-1f, -.5f); // coordinate
		vArray.addVertex(1f, 0f, 0f); // Color
		vArray.addVertex(0f, -.5f); // coordinate
		vArray.addVertex(0f, 0f, 1f); // Color
		vArray.addVertex(-.5f, .5f); // coordinate
		vArray.addVertex(0f, 1f, 0f); // Color

		int indices[] = { 0, 1, 2 };

		VertexArray3d vArray2 = new VertexArray3d();
		vArray2.addVertex(0f, -.5f); // coordinate
		vArray2.addVertex(0f, 0f, 1f); // Color
		vArray2.addVertex(1f, -.5f); // coordinate
		vArray2.addVertex(0f, 1f, 0f); // Color
		vArray2.addVertex(.5f, .5f); // coordinate
		vArray2.addVertex(1f, 0f, 0f); // Color

		/** TRIANGLE 1 */
		// Generate the buffers
		int vertexBuffer[] = new int[2];
		GL15.glGenBuffers(vertexBuffer);
		elementBuffer = GL15.glGenBuffers();
		GL30.glGenVertexArrays(vertexArray);

		// Bind the original vertex array
		GL30.glBindVertexArray(vertexArray[0]);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexBuffer[0]);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vArray.getVertexes(), GL15.GL_STATIC_DRAW);

		// Define what the data in the vertex array looks like
		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 24, 0L);
		GL20.glEnableVertexAttribArray(0);
		GL20.glVertexAttribPointer(1, 3, GL11.GL_FLOAT, false, 24, 12L);
		GL20.glEnableVertexAttribArray(1);

		// Associate the buffer to coordinates
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, elementBuffer);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indices, GL15.GL_STATIC_DRAW);

		// Reset buffers to nothing
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);

		
		/** END TRIANGLE 1 */
		/** TRIANGLE 2 */
		GL30.glBindVertexArray(vertexArray[1]);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexBuffer[1]);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vArray2.getVertexes(), GL15.GL_STATIC_DRAW);

		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 24, 0L);
		GL20.glEnableVertexAttribArray(0);
		GL20.glVertexAttribPointer(1, 3, GL11.GL_FLOAT, false, 24, 12L);
		GL20.glEnableVertexAttribArray(1);

		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, elementBuffer);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indices, GL15.GL_STATIC_DRAW);

		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
		/** END TRIANGLE 2 */
		// Shaders
		shaderProgram = new ShaderProgram(new Scene3ExcerciseVertex(), new Scene3ExerciseFragment());
		shaderProgram.initalize();
	}

	@Override
	protected void loop() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		shaderProgram.use();

		//Triangle 1
		GL30.glBindVertexArray(vertexArray[0]);
		GL11.glDrawElements(GL11.GL_TRIANGLES, 3, GL11.GL_UNSIGNED_INT, 0);

		//Triangle 2
		GL30.glBindVertexArray(vertexArray[1]);
		GL11.glDrawElements(GL11.GL_TRIANGLES, 3, GL11.GL_UNSIGNED_INT, 0);
	}

	@Override
	protected void cleanup() {
		// TODO Auto-generated method stub

	}

}
