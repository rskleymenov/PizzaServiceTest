package view;

import domain.Customer;
import domain.Order;
import repository.PizzaRepository;
import service.OrderService;
import service.infrastructure.ApplicationContex;
import service.infrastructure.impl.JavaConfigApplicationContex;

public class PizzaApp {
	public static void main(String args[]) throws Exception {
		Customer customer = new Customer(1, "Roman");
		Order order;

		ApplicationContex ac = new JavaConfigApplicationContex();

		PizzaRepository pizzaRepository = (PizzaRepository) ac.getBean("pizzaRepository");
		System.out.println(pizzaRepository.getPizzaByID(1));

		OrderService orderService = (OrderService) ac.getBean("orderService");
		order = orderService.placeNewOrder(customer, 1, 2, 3);

		System.out.println(order);

	}

}
