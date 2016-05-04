package service.infrastructure.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Set;

public class TimeHandler implements InvocationHandler {
	private Object object;
	private Set<String> methods;

	public TimeHandler(Object object, Set<String> methods) {
		this.object = object;
		this.methods = methods;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result;
		long startTime = System.nanoTime();
		result = method.invoke(object, args);
		long endTime = System.nanoTime();
		displayTimeOfMethodExecuting(method, startTime, endTime);
		return result;
	}

	private void displayTimeOfMethodExecuting(Method method, long startTime, long endTime) {
		if (methods.contains(method.getName())) {
			System.out
					.println(method.getName() + " has been working during " + (endTime - startTime) + " nanoseconds!");
		}
	}

}
