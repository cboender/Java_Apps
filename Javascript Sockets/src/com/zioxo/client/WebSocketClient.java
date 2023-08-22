package com.zioxo.client;

import java.io.*;
import java.net.*;
import java.nio.*;
import java.security.*;
import java.util.*;

import javax.xml.bind.*;

import com.zioxo.message.*;

public class WebSocketClient extends Client {

	private static final String WEBSOCKET_RESPONSE_CODE = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";

	public WebSocketClient(Socket socket, ClientListener listener) throws IOException {
		super(socket, listener);
	}

	/**
	 * Initalize client with initalization response
	 * 
	 * @param message
	 * @throws IOException
	 */
	public void initalize(String key) throws IOException {
		StringBuilder response = new StringBuilder();
		response.append("HTTP/1.1 101 Switching Protocols\r\n");
		response.append("Connection: Upgrade\r\n");
		response.append("Upgrade: websocket\r\n");
		key = key + WEBSOCKET_RESPONSE_CODE;
		byte[] sha;
		try {
			sha = MessageDigest.getInstance("SHA-1").digest(key.getBytes("UTF-8"));
			response.append("Sec-WebSocket-Accept: ");
			response.append(DatatypeConverter.printBase64Binary(sha));

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.append("\r\n\r\n");

		System.out.println("Web Socket Connected");
		super.send(response.toString());
	}

	@Override
	public void send(String message) throws IOException {
		// Wrap the message in a frame
		WebSocketFrame frame = new WebSocketFrame();
		frame.setData(message);
		frame.setFinalFrame(-1);
		frame.setType(OperationType.TEXT);
		byte[] bytes = message.getBytes("UTF-8");
		frame.setDataLength(bytes.length);

		super.send(frameMessage(frame, bytes));
	}

	private byte[] frameMessage(WebSocketFrame frame, byte[] bytes) {
		byte first = 0x0;
		first = applyBitValue(first, 0, 1, frame.getFinalFrame());
		first = applyBitValue(first, 4, 4, frame.getType().getCode());

		byte second = 0x0;
		second = applyBitValue(second, 0, 1, frame.getMasked());
		int byteCount = 0;
		if (frame.getDataLength() < 126) {
			second = applyBitValue(second, 1, 7, (int) frame.getDataLength());
		} else if (frame.getDataLength() <= Short.MAX_VALUE) {
			second = applyBitValue(second, 1, 7, 126);
			byteCount = 2;
		} else {
			second = applyBitValue(second, 1, 7, 127);
			byteCount = 8;
		}

		ByteBuffer bb = ByteBuffer.allocate(byteCount + 2 + bytes.length);

		bb.put(first);
		bb.put(second);
		if (byteCount == 2) {
			bb.putShort((short) frame.getDataLength());
		} else if (byteCount == 8) {
			bb.putLong(frame.getDataLength());
		}
		bb.put(bytes);
		return bb.array();
	}

	@Override
	public void messageRecieved(byte[] message) {
		WebSocketFrame frame = new WebSocketFrame();

		int byteIndex = parseHeader(frame, message);
		// Parse the payload data
		if (frame.getType() == OperationType.CLOSE) {
			try {
				socket.close();
			} catch (IOException exp) {
				// Do Nothing
			}
			return;
		}
		// TODO catch close messages
		super.messageRecieved(parsePayload(frame, Arrays.copyOfRange(message, byteIndex, message.length)));
	}

	/**
	 * Controls final message and operation
	 */
	private int parseHeader(WebSocketFrame frame, byte[] b) {
		int byteIndex = 0;

		frame.setFinalFrame(getBitValue(b[byteIndex], 0, 1));
		int operationCode = getBitValue(b[byteIndex], 4, 4);
		frame.setType(OperationType.parse(operationCode));

		byteIndex++;
		frame.setMasked(getBitValue(b[byteIndex], 0, 1));

		long payloadLength = getBitValue(b[byteIndex], 1, 7);
		byteIndex++;
		int biteLength = 0;
		if (payloadLength == 126) {
			biteLength = 2;
		} else if (payloadLength == 127) {
			biteLength = 8;
		}

		if (biteLength > 0) {
			ByteBuffer buffer = ByteBuffer.wrap(b, byteIndex, biteLength);
			if (biteLength == 2) {
				payloadLength = buffer.getShort();
			} else if (biteLength == 8) {
				payloadLength = buffer.getLong();
			}
			byteIndex += byteIndex;
		}

		frame.setDataLength(payloadLength);

		if (frame.isMasked()) {
			// The masking key is only present if the frame was Masked which it should be
			frame.setMaskingKey(Arrays.copyOfRange(b, byteIndex, byteIndex + 4));
			byteIndex += 4;
		}

		return byteIndex;
	}

	private byte[] parsePayload(WebSocketFrame frame, byte[] payload) {
		byte[] mask = frame.getMaskingKey();

		for (int x = 0; x < payload.length; x++) {
			payload[x] ^= mask[x % 4];
		}
		return payload;
	}

	private byte applyBitValue(byte orig, int pos, int len, int value) {
		value <<= (8 - (pos + len));
		orig |= value;
		return orig;
	}

	private byte getBitValue(byte b, int offset, int length) {
		int shift = 8 - (length + offset);
		b >>= shift;
		for (int x = 7; x > (7 - offset); x--) {
			b &= ~(1 << x);
		}

		return b;

	}
}
