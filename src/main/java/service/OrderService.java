package service;

import domain.Customer;
import domain.Order;
import service.infrastructure.annotations.BenchMark;

public interface OrderService {
	@BenchMark
	Order placeNewOrder(Customer customer, Integer... pizzasID);
}