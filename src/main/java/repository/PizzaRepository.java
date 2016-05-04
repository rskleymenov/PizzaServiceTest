package repository;

import domain.Pizza;

public interface PizzaRepository {
	
	Pizza getPizzaByID(int id);
	
	Pizza create(Pizza pizza);
}
