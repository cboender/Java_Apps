import java.util.*;

import com.micro.handler.*;
import com.micro.handler.request.*;
import com.micro.rest.*;

public class Main {

	private RequestHandler handler = null;

	public Main() {
		handler = new RequestHandler();
		handler.bindEndPoint(Authenticate.class);
	};

	public void run() {
		sendFakeRequest();
	}

	private void sendFakeRequest() {
		Map<String, String> params = new HashMap<>(2);

		params.put("user", "TestUser");
		params.put("password", "secret");
		Map<String, String> headers = new HashMap<>(2);

		DefaultRequest request = new DefaultRequest("POST", "/auth/a", params, headers);
		try {
			Response response = handler.handleRequest(request);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Main().run();

	}
}
