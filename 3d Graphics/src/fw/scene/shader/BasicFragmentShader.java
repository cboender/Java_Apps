package fw.scene.shader;

import java.util.*;

/**
 * Fragment Shader with a fixed output color
 * 
 * @author Caleb Boender
 *
 */
public class BasicFragmentShader extends FragmentShader {

	private float r, g, b;

	public BasicFragmentShader(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	@Override
	protected void initInputs(List<String> inputs) {
		// None
	}

	@Override
	protected void initOutputs(List<String> outputs) {
		outputs.add("vec4 FragColor;");
	}

	@Override
	protected void defineProcessor(List<String> processor) {
		processor.add("FragColor = vec4(" + r + "f, " + g + "f, " + b + "f, 1.0f);");
	}

	@Override
	protected void initUniforms(List<String> uniforms) {
		
	}

}
