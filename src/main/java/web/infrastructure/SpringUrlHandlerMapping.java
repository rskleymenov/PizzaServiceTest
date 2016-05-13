package web.infrastructure;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import web.Controller;
import web.GetPizzaController;
import web.HelloController;

public class SpringUrlHandlerMapping implements HandlerMapping{
	
	private ApplicationContext webContext;
	private static Map<String, Controller> mapping = new HashMap<>();
	
	public SpringUrlHandlerMapping(ApplicationContext applicationContext) {
		this.webContext = applicationContext;
		mapping.put("/hello", webContext.getBean(GetPizzaController.class));
		mapping.put("/helloPage", webContext.getBean(HelloController.class));
	}
	
	
	@Override
	public Controller getController(String url) {
		return mapping.get(url);
	}

}
