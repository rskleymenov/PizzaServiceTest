package service.infrastructure.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

import service.infrastructure.ApplicationContex;
import service.infrastructure.annotations.BenchMark;
import service.infrastructure.annotations.PostConstruct;

public class BeanBuilder {
	private final Class<?> clazz;
	private ApplicationContex applicationContex;
	private Object bean;
	private Object beanProxy;
	private boolean isProxy;

	public BeanBuilder(Class<?> clazz, ApplicationContex applicationContex) {
		super();
		this.clazz = clazz;
		this.applicationContex = applicationContex;
	}

	public void createBean() throws Exception {
		Constructor<?> constructor = clazz.getConstructors()[0];
		Class<?>[] paramTypes = constructor.getParameterTypes();
		if (paramTypes.length == 0) {
			bean = clazz.newInstance();
		} else {
			bean = createNewInstanceWithParams(constructor, paramTypes);
		}
	}

	private Object createNewInstanceWithParams(Constructor<?> constructor, Class<?>[] paramTypes) throws Exception {
		Object bean;
		Object[] paramBeans = getParams(paramTypes);
		bean = constructor.newInstance(paramBeans);
		return bean;
	}

	private Object[] getParams(Class<?>[] paramTypes) throws Exception {
		Object[] paramBeans = new Object[paramTypes.length];
		for (int i = 0; i < paramTypes.length; i++) {
			paramBeans[i] = getBeanByType(paramTypes[i]);
		}
		return paramBeans;
	}

	private Object getBeanByType(Class<?> paramType) throws Exception {
		String paramName = getBeanNameByType(paramType);
		return applicationContex.getBean(paramName);
	}

	private String getBeanNameByType(Class<?> paramType) {
		String paramTypeName = paramType.getSimpleName();
		String paramName = changeFirstLetterToLowerCase(paramTypeName);
		return paramName;
	}

	private String changeFirstLetterToLowerCase(String paramTypeName) {
		String paramName = Character.toLowerCase(paramTypeName.charAt(0)) + paramTypeName.substring(1);
		return paramName;
	}

	public Object build() {
		Object object;
		if (isProxy) {
			object = beanProxy;
		} else {
			object = bean;
		}
		return object;
	}

	public void callInitMethod() throws Exception {
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			if (method.getName().equalsIgnoreCase("init")) {
				method.invoke(bean);
			}
		}

	}

	public void callPostConstruct() throws Exception {
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			if (method.getAnnotation(PostConstruct.class) != null) {
				method.invoke(bean);
			}
		}

	}

	public void createBeanProxy() {
		Set<String> setOfMethods = new HashSet<String>();
		getMethodsThatHaveBenchMarkAnnotations(setOfMethods);
		createProxy(setOfMethods);
	}

	private void getMethodsThatHaveBenchMarkAnnotations(Set<String> setOfMethods) {
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			if (method.getAnnotation(BenchMark.class) != null
					&& method.getAnnotation(BenchMark.class).active() == true) {
				updateProxyFlag();
				setOfMethods.add(method.getName());
			}
		}
	}

	private void createProxy(Set<String> setOfMethods) {
		if (isProxy) {
			beanProxy = Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(),
					new TimeHandler(bean, setOfMethods));
		}
	}

	private void updateProxyFlag() {
		isProxy = true;
	}

}
