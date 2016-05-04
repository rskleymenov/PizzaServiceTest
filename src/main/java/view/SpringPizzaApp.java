package view;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import domain.Customer;
import domain.Order;
import domain.Pizza;
import repository.PizzaRepository;
import service.OrderService;

public class SpringPizzaApp {
	public static void main(String[] args) {
		Customer customer = new Customer(1, "Roman");
		Order order;

		ConfigurableApplicationContext repContext = new ClassPathXmlApplicationContext("repositoryContext.xml");
		ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "appContext.xml" }, repContext);
		repContext.getEnvironment().setActiveProfiles("prod");
		repContext.refresh();
		
		//applicationContext.refresh();
		PizzaRepository pizzaRepository = (PizzaRepository) applicationContext.getBean("pizzaRepository");
		System.out.println(pizzaRepository.getPizzaByID(1));

		OrderService orderService = (OrderService) applicationContext.getBean("orderService");
		System.out.println(orderService.getClass());
		
		order = orderService.placeNewOrder(customer, 1, 2);

		System.out.println(order);
		
		order = orderService.placeNewOrder(customer, 1);
		System.out.println(order);

		Pizza pizza = applicationContext.getBean(Pizza.class);
		System.out.println(pizza);

		ApplicationContext ac = applicationContext.getParent();
		System.out.println("Parent: " + ac);

		Customer customerBean = applicationContext.getBean(Customer.class);
		System.out.println(customerBean);

		repContext.close();
		applicationContext.close();
	}
}
