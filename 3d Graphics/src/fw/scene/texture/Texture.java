package fw.scene.texture;

import java.awt.image.*;
import java.io.*;
import java.nio.*;

import javax.imageio.*;

import org.lwjgl.opengl.*;

public class Texture {
	private int id = 0;

	private int width = 0;
	private int height = 0;

	private String fileName = null;
	private String format = null;

	private boolean hasAlpha = false;

	int position = 0;

	public Texture(String format, String fileName, int position) {
		this.fileName = fileName;
		this.position = position;
		this.format = format;
	}

	private ByteBuffer loadImage() {
		File file = new File(fileName);
		try {
			BufferedImage img = null;
			// if ("png".equalsIgnoreCase(format)) {
			// img = new PNGDecoder(new BufferedInputStream(new
			// FileInputStream(file))).decode();
			// } else {
			img = ImageIO.read(file);
			// }
			width = img.getWidth();
			height = img.getHeight();
			hasAlpha = img.getColorModel().hasAlpha();

			if ("png".equalsIgnoreCase(format)) {
				// PNGDecoder decoder = new PNGDecoder(new FileInputStream(file));
				// ByteBuffer bb = ByteBuffer.allocateDirect(4 * width * height);
				// int perPixel = decoder.hasAlpha() ? 4 : 3;
				// decoder.decode(bb, perPixel * width, hasAlpha ? PNGDecoder.RGBA :
				// PNGDecoder.RGB);
				// return bb;
			}
			return new TextureLoader().convertImageData(img);
		} catch (IOException exp) {
			throw new RuntimeException(exp.getMessage(), exp);
		}
	}

	public void initialize() {
		ByteBuffer buffer = loadImage();
		// Horizontal?
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL13.GL_CLAMP_TO_BORDER);
		// Vertical?
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL13.GL_CLAMP_TO_BORDER);

		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);

		id = GL11.glGenTextures();

		use();
		int format = GL11.GL_RGB;
		if (hasAlpha) {
			format = GL11.GL_RGBA;
		}
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, format, width, height, 0, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, buffer);
		GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);

		buffer.clear();
	}

	public int getId() {
		return id;
	}

	public void use() {
		GL13.glActiveTexture(position);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
	}
}
