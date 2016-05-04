package service;

import java.util.ArrayList;
import java.util.List;

import domain.Customer;
import domain.Order;
import domain.Pizza;
import repository.OrderRepository;
import repository.PizzaRepository;
import service.infrastructure.annotations.BenchMark;

public class SimpleOrderService implements OrderService {

	OrderRepository orderRepository;
	PizzaRepository pizzaRepository;
	Customer customer;

	public SimpleOrderService(OrderRepository orderRepository, PizzaRepository pizzaRepository) {
		this.orderRepository = orderRepository;
		this.pizzaRepository = pizzaRepository;
	}
	
	

	public void setCustomer(Customer customer) {
		this.customer = customer;
		System.out.println(this.customer.toString());
	}



	@BenchMark
	public Order placeNewOrder(Customer customer, Integer... pizzasID) {
		List<Pizza> pizzas = pizzasByArrOfId(pizzasID);
		Order newOrder = createOrder();
		newOrder.setCustomer(customer);
		newOrder.setListOfPizzas(pizzas);
		orderRepository.saveOrder(newOrder);
		return newOrder;
	}
	
	@BenchMark
	protected Order createOrder() {
		return null;
	}

	private List<Pizza> pizzasByArrOfId(Integer... pizzasID) {
		List<Pizza> pizzas = new ArrayList<>();

		for (Integer id : pizzasID) {
			pizzas.add(pizzaRepository.getPizzaByID(id));
		}
		return pizzas;
	}

}
