package collection.visualizer.examples;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import collection.visualizer.datatype.bean.BeanEventGenerator;
import collection.visualizer.trappers.EventTrapper;

public class DateTester implements PropertyChangeListener,
		EventTrapper<PropertyChangeListener, BeanEventGenerator> {

	public void propertyChange(PropertyChangeEvent event) {
		System.out.println("Name      = " + event.getPropertyName());

		System.out.println("Old Value = " + event.getOldValue());

		System.out.println("New Value = " + event.getNewValue());

	}

	public void addListener(
			EventTrapper<PropertyChangeListener, BeanEventGenerator> observer)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void removeListener(
			EventTrapper<PropertyChangeListener, BeanEventGenerator> observer) {
		// TODO Auto-generated method stub
		
	}

}
