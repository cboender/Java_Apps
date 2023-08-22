package fw.scene.shader.custom;

import java.util.*;

import fw.scene.shader.*;

public class UniformVertexShader extends VertexShader {

	@Override
	protected void initInputs(List<String> inputs) {
		inputs.add("layout (location = 0) in vec3 aPos;");
	}

	@Override
	protected void initOutputs(List<String> outputs) {
	}

	@Override
	protected void defineProcessor(List<String> processor) {
		processor.add("gl_Position = vec4(aPos, 1.0);");
	}

	@Override
	protected void initUniforms(List<String> uniforms) {
	}

}
