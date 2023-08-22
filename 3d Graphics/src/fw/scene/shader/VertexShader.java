package fw.scene.shader;

import org.lwjgl.opengl.*;

public abstract class VertexShader extends Shader {

	// Vertex shader automatically gets the output variable vec4 gl_position

	protected VertexShader() {
		super(GL20.GL_VERTEX_SHADER);
	}

	@Override
	protected String getVersion() {
		return "330 core";
	}

	protected void writeInput(StringBuilder builder, String input) {
		builder.append(input);
		if (!input.endsWith(";")) {
			builder.append(";");
		}
		builder.append("\r\n");
	}

}
