package repository.impl;

import java.util.ArrayList;
import java.util.List;

import domain.Pizza;
import repository.PizzaRepository;
import service.infrastructure.annotations.BenchMark;

public class InMemPizzaRepository implements PizzaRepository {

	private static List<Pizza> listOfPizzas = new ArrayList<>();

	public static List<Pizza> getListOfPizzas() {
		return listOfPizzas;
	}

	public static void setListOfPizzas(List<Pizza> listOfPizzas) {
		InMemPizzaRepository.listOfPizzas = listOfPizzas;
	}

	public void init() {
		/*
		 * listOfPizzas.add(new Pizza(1, "Vegan", 88.5, PizzaType.Vegetarian));
		 * listOfPizzas.add(new Pizza(2, "Original", 188.5, PizzaType.Sea));
		 * listOfPizzas.add(new Pizza(3, "AllMeat", 25.5, PizzaType.Meat));
		 */
	}

	// @PostConstruct
	public void cookPizzas() {
		/*
		 * listOfPizzas.add(new Pizza(1, "Vegan", 88.5, PizzaType.Vegetarian));
		 * listOfPizzas.add(new Pizza(2, "Original", 188.5, PizzaType.Sea));
		 * listOfPizzas.add(new Pizza(3, "AllMeat", 25.5, PizzaType.Meat));
		 */
	}
	
	@BenchMark
	public Pizza getPizzaByID(int id) {
		int index = id - 1;
		return listOfPizzas.get(index);
	}

	@Override
	public Pizza create(Pizza pizza) {
		return null;
	}
}
