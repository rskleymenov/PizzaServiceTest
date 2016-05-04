package roman.kleimenov.PizzaService;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import domain.Customer;
import domain.Order;
import service.OrderService;

@ContextConfiguration(locations = { "classpath:/appContext.xml" }, inheritInitializers = true)
public class SimpleOrderServiceTest extends RepositoryTestConfig {

	@Autowired
	private OrderService orderService;

	@Test
	public void testPlaceNewOrder() {
		Integer[] pizzasId = new Integer[] { 1 };
		Customer customer = new Customer();
		Order result = orderService.placeNewOrder(customer, pizzasId);
		System.out.println(result);
	}
}
