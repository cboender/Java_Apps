package com.zioxo.message;

public class WebSocketFrame {

	private boolean finalFrame = false;

	private OperationType type = null;

	private boolean masked = false;

	private long dataLength = 0;

	private String data = null;

	private byte[] maskingKey = null;

	public WebSocketFrame() {
	}

	public int getFinalFrame() {
		return getInt(finalFrame);
	}

	public boolean isFinalFrame() {
		return finalFrame;
	}

	public void setFinalFrame(int finalFrame) {
		this.finalFrame = getBoolean(finalFrame);
	}

	public OperationType getType() {
		return type;
	}

	public void setType(OperationType type) {
		this.type = type;
	}

	public int getMasked() {
		return getInt(masked);
	}

	public boolean isMasked() {
		return masked;
	}

	public void setMasked(int masked) {
		this.masked = getBoolean(masked);
	}

	public long getDataLength() {
		return dataLength;
	}

	public void setDataLength(long dataLength) {
		this.dataLength = dataLength;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	private int getInt(boolean value) {
		if (value) {
			return 1;
		} else {
			return 0;
		}
	}

	private boolean getBoolean(int value) {
		if (value == 0) {
			return false;
		} else {
			return true;
		}
	}

	public byte[] getMaskingKey() {
		return maskingKey;
	}

	public void setMaskingKey(byte[] maskingKey) {
		this.maskingKey = maskingKey;
	}
}
