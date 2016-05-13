package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import repository.PizzaRepository;

@org.springframework.stereotype.Controller
public class GetPizzaController implements Controller{
	
	@Autowired
	private PizzaRepository pizzaRepository;

	@Override
	public void handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		try (PrintWriter out = resp.getWriter()) {
			out.println(pizzaRepository.getPizzaByID(1));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}



}
