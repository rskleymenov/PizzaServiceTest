package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@org.springframework.stereotype.Controller
public class HelloController implements Controller {

	@Override
	public void handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		try (PrintWriter out = resp.getWriter()) {
			out.println("Hello from HelloController");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
