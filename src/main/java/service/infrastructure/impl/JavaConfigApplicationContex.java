package service.infrastructure.impl;

import java.util.HashMap;
import java.util.Map;

import service.infrastructure.ApplicationContex;
import service.infrastructure.Config;

public class JavaConfigApplicationContex implements ApplicationContex {
	private final Config config = new JavaConfig();
	private final Map<String, Object> contex = new HashMap<>();

	@Override
	public Object getBean(String beanName) throws Exception {
		if (contex.containsKey(beanName)) {
			return contex.get(beanName);
		}

		Class<?> clazz = config.getImpl(beanName);

		if (clazz == null) {
			throw new RuntimeException("Bean not found");
		}

		BeanBuilder builder = new BeanBuilder(clazz, this);

		builder.createBean();
		builder.createBeanProxy();
		builder.callPostConstruct();
		builder.callInitMethod();
		Object bean = builder.build();

		contex.put(beanName, bean);
		return bean;
	}
}
