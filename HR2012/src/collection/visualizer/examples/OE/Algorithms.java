package collection.visualizer.examples.OE;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import collection.visualizer.common.*;

public class Algorithms {
	String[] algorithms = { "Randomize" };

	private HashMap<String, String> stringToMethodNameMap = new HashMap<String, String>();
	private Object data;

	public Algorithms(Object data) {
		this.data = data;
		stringToMethodNameMap.put(algorithms[0], "randomize");
	}

	public String[] getDynamicCommands() {
		return algorithms;
	}

	public void invokeDynamicCommand(String commandName) {
		String methodName = stringToMethodNameMap.get(commandName);
		try {
			Method m = collection.visualizer.common.Algorithms.class.getMethod(methodName, ListenableVector.class);
			m.invoke(null, data);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
