package fw.graphics.window;

import org.lwjgl.glfw.*;

public abstract class Scene {

	protected Window window = null;

	public void run() {
		_init();
	}

	private void _init() {
		GLFWErrorCallback.createPrint(System.err).set();

		if (!GLFW.glfwInit()) {
			throw new IllegalStateException("Unable to initialize GLFW");
		}

		window = new Window();
		init();
		_loop();

		cleanup();

		window.close();
		// Terminate GLFW and free the error callback
		GLFW.glfwTerminate();
		GLFW.glfwSetErrorCallback(null).free();

	}

	private void _loop() {
		while (!GLFW.glfwWindowShouldClose(window.getId())) {
			loop();

			// Switch from write to read
			GLFW.glfwSwapBuffers(window.getId());

			GLFW.glfwPollEvents();
		}
	}

	protected abstract void init();

	protected abstract void loop();

	protected abstract void cleanup();

}
