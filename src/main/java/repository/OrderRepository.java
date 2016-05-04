package repository;

import domain.Order;

public interface OrderRepository {
	
	Long saveOrder(Order newOrder);
}
