package fw.graphics.window;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

public class Window {

	private long window = 0L;

	private GLCapabilities capabilities = null;

	private int width = 500;

	private int height = 500;

	private String title = "Sample Program";

	public Window() {
		GLFW.glfwDefaultWindowHints(); // Optional maybe have overrides?
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE); // the window will stay hidden after creation
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE); // the window will be resizable

		window = GLFW.glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL);
		if (window == MemoryUtil.NULL) {
			throw new RuntimeException("Failed to create the GLFW window");
		}

		GLFW.glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE) {
				GLFW.glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
			}
		});

		GLFW.glfwSetFramebufferSizeCallback(window, (window, width, height) -> {
			GL11.glViewport(0, 0, width, height);
		});

		// Center the window
		GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		GLFW.glfwSetWindowPos(window, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);

		GLFW.glfwMakeContextCurrent(window);
		capabilities = GL.createCapabilities();

		// VSync
		GLFW.glfwSwapInterval(1);

		// Init clear color

		GLFW.glfwShowWindow(window);
	}

	public long getId() {
		return window;
	}

	public void close() {
		Callbacks.glfwFreeCallbacks(window);
		GLFW.glfwDestroyWindow(window);
	}

	public GLCapabilities getCapabilities() {
		return capabilities;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
