package collection.visualizer.examples.bean;

import java.beans.PropertyChangeEvent;
import java.util.Date;

import javax.swing.JPanel;

import bus.uigen.shapes.Shape;
import collection.visualizer.common.ListenableVector;
import collection.visualizer.datatype.bean.ABeanBuffer;
import collection.visualizer.datatype.bean.BeanEventGenerator;
import collection.visualizer.examples.observer.ADateLayoutManager;
import collection.visualizer.examples.observer.ObservableDate;
import collection.visualizer.layout.LayoutManager;

public class ABeanDateLayoutManager implements
		LayoutManager<BeanEventGenerator> {

	private ADateLayoutManager layoutManager;

	public ABeanDateLayoutManager(int x, int y, int radius) {
		layoutManager = new ADateLayoutManager(x, y, radius);
	}

	public ListenableVector<Shape> display(BeanEventGenerator bean) {
		if (bean instanceof ABeanBuffer) {
			ObservableDate observableDate = new ObservableDate();
			ABeanDate date = (ABeanDate) ((ABeanBuffer)bean).getBean();
			observableDate.setDate(date.getDate());
			return layoutManager.display(observableDate);
		}
		return null;
	}

	public ListenableVector<Shape> constructPseudoCode() {
		return layoutManager.constructPseudoCode();
	}

	public ListenableVector<Shape> getPseudoCode() {
		return layoutManager.getPseudoCode();
	}

	public int getPseudoCodeMarker() {
		return layoutManager.getPseudoCodeMarker();
	}

	public void update(PropertyChangeEvent event) {
		Object newVal = event.getNewValue();
		if (newVal instanceof Date) {

			ObservableDate observableDate = new ObservableDate();
			observableDate.setDate((Date)newVal);
			layoutManager.update(observableDate);
		}
	}

	public JPanel displayInPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return null;
	}

}
