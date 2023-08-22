package fw.scene.shader;

import org.lwjgl.opengl.*;

public class ShaderProgram {
	private int program = 0;

	private Shader[] shaders = null;

	public ShaderProgram(Shader... shaders) {
		this.shaders = shaders;
	}

	public void initalize() {
		program = GL20.glCreateProgram();
		for (Shader shader : shaders) {
			shader.compile();
			GL20.glAttachShader(program, shader.getId());
		}

		GL20.glLinkProgram(program);

		// Cleanup
		for (Shader shader : shaders) {
			shader.delete();
		}
	}

	public void setUniformValue(String name, int value) {
		GL20.glUniform1i(getUniformPosition(name), value);
	}

	public void setUniformMatrix(String name, float[] value) {
		GL20.glUniformMatrix4fv(getUniformPosition(name), false, value);
	}

	public void clearUniformMatrix(String name) {
	}

	public int getUniformPosition(String variableName) {
		return GL20.glGetUniformLocation(program, variableName);
	}

	public void use() {
		GL20.glUseProgram(program);
	}
}
