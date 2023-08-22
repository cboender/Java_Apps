package fw.scene.shader.custom;

import java.util.*;

import fw.scene.shader.*;

public class Scene6Vertex extends VertexShader {

	@Override
	protected void initInputs(List<String> inputs) {
		inputs.add("layout (location = 0) in vec2 aPos;");
		inputs.add("layout (location = 1) in vec3 aColor;");
		inputs.add("layout (location = 2) in vec2 atexCoord;");
	}

	@Override
	protected void initOutputs(List<String> outputs) {
		outputs.add("vec4 color");
		outputs.add("vec2 texCoord");
	}

	@Override
	protected void initUniforms(List<String> uniforms) {
		uniforms.add("mat4 transform");
	}

	@Override
	protected void defineProcessor(List<String> processor) {
		processor.add("gl_Position = transform * vec4(aPos, 0.0, 1.0);");
		processor.add("color = vec4(aColor, 1.0);");
		processor.add("texCoord = atexCoord");
	}

}
