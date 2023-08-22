package fw.scene.shader.custom;

import java.util.*;

import fw.scene.shader.*;

/**
 * Fragment Shader with a fixed output color
 * 
 * @author Caleb Boender
 *
 */
public class Scene3ExerciseFragment extends FragmentShader {

	@Override
	protected void initInputs(List<String> inputs) {
		inputs.add("vec4 color");
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
		processor.add("FragColor = color");
	}

}
