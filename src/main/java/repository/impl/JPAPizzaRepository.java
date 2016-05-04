package repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import domain.Pizza;
import repository.PizzaRepository;

@Repository("pizzaRepository")
@Transactional
public class JPAPizzaRepository implements PizzaRepository{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional(readOnly = true)
	public Pizza getPizzaByID(int id) {
		return em.find(Pizza.class, id);
	}

	@Override
	public Pizza create(Pizza pizza) {
		em.persist(pizza);
		return pizza;
	}

}
