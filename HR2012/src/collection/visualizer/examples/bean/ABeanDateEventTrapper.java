package collection.visualizer.examples.bean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import collection.visualizer.common.Util;
import collection.visualizer.datatype.bean.ABeanEventTrapper;
import collection.visualizer.datatype.bean.BeanEventGenerator;
import collection.visualizer.trappers.EventTrapper;

public class ABeanDateEventTrapper extends ABeanEventTrapper implements
		PropertyChangeListener,
		EventTrapper<PropertyChangeListener, BeanEventGenerator> {

	private ABeanDateLayoutManager layoutManager;

	public ABeanDateEventTrapper(ABeanDateLayoutManager layoutManager,
			BeanEventGenerator bean) {
		super(bean);
		this.layoutManager = layoutManager;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void propertyChange(PropertyChangeEvent event) {

		String methodName = event.getPropertyName();
		Object newVal = event.getNewValue();
		Class param = newVal.getClass();
		Class alternateParam = Util.convertWrapperClassToPrimitiveClass(newVal
				.getClass());
		try {
			Method changeProperty;
			try {
				changeProperty = bean.getClass().getMethod(methodName, param);

			} catch (NoSuchMethodException e) {
				changeProperty = bean.getClass().getMethod(methodName,
						alternateParam);
			}
			changeProperty.invoke(bean, newVal);

			layoutManager.update(event);
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
