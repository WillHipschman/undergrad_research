package collection.visualizer.datatype.bean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import collection.visualizer.ABuffer;
import collection.visualizer.Buffer;
import collection.visualizer.CollectionVisualizer;
import collection.visualizer.commands.bean.APropertyChangeCommand;

import collection.visualizer.trappers.*;

public class ABeanBuffer extends
		ABuffer<PropertyChangeListener, BeanEventGenerator> implements
		PropertyChangeListener,
		Buffer<PropertyChangeListener, BeanEventGenerator>,
		EventTrapper<PropertyChangeListener, BeanEventGenerator>,
		BeanEventGenerator {

	public ABeanBuffer(
			CollectionVisualizer<PropertyChangeListener, BeanEventGenerator> visualizer) {
		super(visualizer);
	}

	private BeanEventGenerator copy;
	public void setBean(BeanEventGenerator observedBean) {

		try {
			observedBean.addListener(this);
			this.copy = observedBean.getClass().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void addListener(
			EventTrapper<PropertyChangeListener, BeanEventGenerator> observer)
			throws Exception {
		copy.addListener(observer);
	}
	public void removeListener(
			EventTrapper<PropertyChangeListener, BeanEventGenerator> observer) {
		copy.removeListener(observer);
	}
	public void propertyChange(PropertyChangeEvent event) {
		this.getCommandHistory().addCommand(
				new APropertyChangeCommand(copy, event));
	}

	public Object getBean() {
		return copy;
	}
}
