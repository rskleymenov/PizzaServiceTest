package web.infrastructure;

import java.util.HashMap;
import java.util.Map;

import web.Controller;
import web.HelloController;

public class UrlHandlerMapping implements HandlerMapping{

	private static Map<String, Controller> mapping = new HashMap<>();
	
	static {
		mapping.put("/hello", new HelloController());
	}
	
	@Override
	public Controller getController(String url) {
		return mapping.get(url);
	}

}
