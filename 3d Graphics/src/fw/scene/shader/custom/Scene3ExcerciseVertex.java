package fw.scene.shader.custom;

import java.util.*;

import fw.scene.shader.*;

public class Scene3ExcerciseVertex extends VertexShader {

	@Override
	protected void initInputs(List<String> inputs) {
		inputs.add("layout (location = 0) in vec3 aPos;");
		inputs.add("layout (location = 1) in vec3 icolor;");
	}

	@Override
	protected void initOutputs(List<String> outputs) {
		outputs.add("vec4 color");
	}

	@Override
	protected void defineProcessor(List<String> processor) {
		processor.add("gl_Position = vec4(aPos, 1.0);");
		processor.add("color = vec4(icolor, 1.0)");
	}

	@Override
	protected void initUniforms(List<String> uniforms) {
	}

}
