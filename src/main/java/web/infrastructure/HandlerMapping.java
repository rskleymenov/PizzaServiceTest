package web.infrastructure;

import web.Controller;

public interface HandlerMapping {
	Controller getController(String url);
}
