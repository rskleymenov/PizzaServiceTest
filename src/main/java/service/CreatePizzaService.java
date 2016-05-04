package service;

import domain.Pizza;
import domain.enums.PizzaType;

public class CreatePizzaService {
	public Pizza createPizza(int id, String name, Double price, PizzaType type) {
		return new Pizza(id, name, price, type);
	}
}
