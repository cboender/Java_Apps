package fw.scene.shader.custom;

import java.util.*;

import fw.scene.shader.*;

/**
 * Fragment Shader with a fixed output color
 * 
 * @author Caleb Boender
 *
 */
public class ColorFragmentShader extends FragmentShader {

	@Override
	protected void initInputs(List<String> inputs) {
		inputs.add("vec4 myColor");
	}

	@Override
	protected void initOutputs(List<String> outputs) {
		outputs.add("vec4 FragColor;");
	}

	@Override
	protected void initUniforms(List<String> uniforms) {
	}

	@Override
	protected void defineProcessor(List<String> processor) {
		processor.add("FragColor = myColor");
	}

}
