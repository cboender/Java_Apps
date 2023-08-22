package fw.scene;

import org.lwjgl.opengl.*;

import fw.graphics.window.*;

/**
 * Port of Test 1 sample using objects
 *
 */
public class Scene1 extends Scene {

	@Override
	protected void init() {
		// Set window color
		GL11.glClearColor(1f, 1f, 1f, 0f);
	}

	@Override
	protected void loop() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}

	@Override
	protected void cleanup() {
		// TODO Auto-generated method stub

	}
	
}
