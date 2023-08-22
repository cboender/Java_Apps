package fw.scene.shader.custom;

import java.util.*;

import fw.scene.shader.*;

/**
 * Fragment Shader with a fixed output color
 * 
 * @author Caleb Boender
 *
 */
public class UniformFragmentShader extends FragmentShader {

	@Override
	protected void initInputs(List<String> inputs) {
	}

	@Override
	protected void initOutputs(List<String> outputs) {
		outputs.add("vec4 FragColor;");
	}

	@Override
	protected void initUniforms(List<String> uniforms) {
		uniforms.add("vec4 ourColor");
	}

	@Override
	protected void defineProcessor(List<String> processor) {
		processor.add("FragColor = ourColor");
	}

}
