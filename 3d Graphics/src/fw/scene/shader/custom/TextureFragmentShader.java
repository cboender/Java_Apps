package fw.scene.shader.custom;

import java.util.*;

import fw.scene.shader.*;

/**
 * Fragment Shader with a fixed output color
 * 
 * @author Caleb Boender
 *
 */
public class TextureFragmentShader extends FragmentShader {

	@Override
	protected void initInputs(List<String> inputs) {
		inputs.add("vec4 color");
		inputs.add("vec2 texCoord");
	}

	@Override
	protected void initOutputs(List<String> outputs) {
		outputs.add("vec4 FragColor;");
	}

	@Override
	protected void initUniforms(List<String> uniforms) {
		uniforms.add("sampler2D texture1");
		uniforms.add("sampler2D texture2");
	}

	@Override
	protected void defineProcessor(List<String> processor) {
		processor.add("FragColor = mix(mix(texture(texture1, texCoord), color,.5), texture(texture2, texCoord * -1),.2)");
	}

}
