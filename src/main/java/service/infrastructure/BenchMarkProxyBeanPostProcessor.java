package service.infrastructure;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import service.infrastructure.annotations.BenchMark;
import service.infrastructure.impl.TimeHandler;

public class BenchMarkProxyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object bean, String arg1) throws BeansException {
		Set<String> setOfMethods = new HashSet<String>();
		getMethodsThatHaveBenchMarkAnnotations(bean, setOfMethods);

		List<Class<?>> interfaces = new ArrayList<>();
		interfaces.addAll(Arrays.asList(bean.getClass().getInterfaces()));
		interfaces.addAll(Arrays.asList(bean.getClass().getSuperclass().getInterfaces()));

		if (!setOfMethods.isEmpty()) {
			Object beanProxy = Proxy.newProxyInstance(bean.getClass().getClassLoader(),
					interfaces.toArray(new Class<?>[interfaces.size()]), new TimeHandler(bean, setOfMethods));
			return beanProxy;
		}
		return bean;
	}

	private void getMethodsThatHaveBenchMarkAnnotations(Object bean, Set<String> setOfMethods) {
		Class<? extends Object> clazz = bean.getClass();
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			if (method.getAnnotation(BenchMark.class) != null
					&& method.getAnnotation(BenchMark.class).active() == true) {
				setOfMethods.add(method.getName());
			}
		}
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String arg1) throws BeansException {
		return bean;
	}

}
