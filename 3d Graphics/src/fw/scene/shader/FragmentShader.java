package fw.scene.shader;

import org.lwjgl.opengl.*;

public abstract class FragmentShader extends Shader {

	protected FragmentShader() {
		super(GL20.GL_FRAGMENT_SHADER);
	}

	@Override
	protected String getVersion() {
		return "330 core";
	}
}
