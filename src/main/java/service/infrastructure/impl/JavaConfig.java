package service.infrastructure.impl;

import java.util.HashMap;
import java.util.Map;

import repository.impl.InMemOrderRepository;
import repository.impl.InMemPizzaRepository;
import service.SimpleOrderService;
import service.infrastructure.Config;

public class JavaConfig implements Config {
	private Map<String, Class<?>> beans = new HashMap<>();

	{
		beans.put("orderRepository", InMemOrderRepository.class);
		beans.put("pizzaRepository", InMemPizzaRepository.class);
		beans.put("orderService", SimpleOrderService.class);
	}

	@Override
	public Class<?> getImpl(String bean) {
		return beans.get(bean);
	}

}
