package fw.scene.shader;

import java.util.*;

import org.lwjgl.opengl.*;

public abstract class Shader {

	protected int shaderType = 0;

	protected int shader = 0;

	private List<String> inputs = new ArrayList<>();

	private List<String> outputs = new ArrayList<>();

	private List<String> uniforms = new ArrayList<>();

	private List<String> processor = new ArrayList<>();

	protected Shader(int shaderType) {
		this.shaderType = shaderType;
	}

	private String getShaderSource() {
		init();

		StringBuilder builder = new StringBuilder();

		// Write the version line
		builder.append("#version ");
		builder.append(getVersion());
		builder.append("\r\n");

		// Write any inputs
		inputs.forEach(input -> {
			writeInput(builder, input);
		});

		outputs.forEach(output -> {
			builder.append("out ");
			builder.append(output);
			if (!output.endsWith(";")) {
				builder.append(";");
			}
			builder.append("\r\n");
		});

		uniforms.forEach(uniform -> {
			builder.append("uniform ");
			builder.append(uniform);
			if (!uniform.endsWith(";")) {
				builder.append(";");
			}
			builder.append("\r\n");
		});

		builder.append("void main() \r\n");
		builder.append("{\r\n");

		processor.forEach(process -> {
			builder.append(process);
			if (!process.endsWith(";")) {
				builder.append(";");
			}
			builder.append("\r\n");
		});
		builder.append("}");
		return builder.toString();
	}

	protected void compile() {
		shader = GL20.glCreateShader(shaderType);
		GL20.glShaderSource(shader, getShaderSource());
		GL20.glCompileShader(shader);

		int[] success = new int[1];
		GL20.glGetShaderiv(shader, GL20.GL_COMPILE_STATUS, success);

		if (success[0] == 0) {
			System.out.println(GL20.glGetShaderInfoLog(shader));
		}
	}

	public int getId() {
		return shader;
	}

	public void delete() {
		GL20.glDeleteShader(shader);
	}

	protected void writeInput(StringBuilder builder, String input) {
		builder.append("in ");
		builder.append(input);
		if (!input.endsWith(";")) {
			builder.append(";");
		}
		builder.append("\r\n");
	}

	protected abstract String getVersion();

	protected abstract void initInputs(List<String> inputs);

	protected abstract void initOutputs(List<String> outputs);

	protected abstract void initUniforms(List<String> uniforms);

	protected abstract void defineProcessor(List<String> processor);

	protected void init() {
		initInputs(inputs);
		initOutputs(outputs);
		initUniforms(uniforms);
		defineProcessor(processor);
	}
}
