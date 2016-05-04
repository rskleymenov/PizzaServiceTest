package repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import domain.Order;
import repository.OrderRepository;
import service.infrastructure.annotations.BenchMark;

@Repository("orderRepository")
public class InMemOrderRepository implements OrderRepository {

	private List<Order> orders = new ArrayList<>();

	@BenchMark
	public Long saveOrder(Order newOrder) {
		orders.add(newOrder);
		return newOrder.getId();
	}
}
