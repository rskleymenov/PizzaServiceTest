package view;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import domain.Pizza;
import domain.enums.PizzaType;
import repository.PizzaRepository;

public class JPAWithSpringApp {

	public static void main(String[] args) {
		ConfigurableApplicationContext repContext = new ClassPathXmlApplicationContext("repositoryMySQLContext.xml");
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "appContext.xml" }, repContext);
		repContext.getEnvironment().setActiveProfiles("prod");
		repContext.refresh();

		PizzaRepository pizzaRepository = (PizzaRepository) applicationContext.getBean("pizzaRepository");
		Pizza pizza = new Pizza("MeatPizza", 99.99d, PizzaType.Meat);
		pizza = pizzaRepository.create(pizza);
		System.out.println(pizzaRepository.getPizzaByID(pizza.getId()));
		applicationContext.close();
	}

}
