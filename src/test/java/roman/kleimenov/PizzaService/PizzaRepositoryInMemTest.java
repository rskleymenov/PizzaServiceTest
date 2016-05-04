package roman.kleimenov.PizzaService;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Pizza;
import domain.enums.PizzaType;
import repository.PizzaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/repositoryInMemDBContext.xml" })
public class PizzaRepositoryInMemTest extends AbstractTransactionalJUnit4SpringContextTests{
	@Autowired
	private PizzaRepository pizzaRepository;

	@Test
	public void testGetPizzaByID() {
		Pizza pizza = pizzaRepository.getPizzaByID(1);
		System.out.println(pizza);
	}

	@Test
	public void testCreate() {
		Pizza pizza = new Pizza("MeatPizza", 99.99d, PizzaType.Meat);
		pizza = pizzaRepository.create(pizza);
		assertNotNull(pizza.getId());
	}
}
