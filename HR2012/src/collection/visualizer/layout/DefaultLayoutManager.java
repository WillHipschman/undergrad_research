package collection.visualizer.layout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import collection.visualizer.common.*;
import collection.visualizer.examples.OE.CreateCustomView;

import collection.visualizer.CollectionVisualizer;
import collection.visualizer.trappers.*;
import bus.uigen.shapes.Shape;

public class DefaultLayoutManager<Observer, ObservableDataType extends EventGenerator<Observer, ObservableDataType>>
		implements LayoutManager<ObservableDataType> {
	
	public DefaultLayoutManager(
			CollectionVisualizer<Observer, ObservableDataType> aCollectionVisualizer) {
	}

	public ListenableVector<Shape> display(ObservableDataType v) {
		return null;
	}

	public collection.visualizer.common.ListenableVector<Shape> constructPseudoCode() {
		// TODO Auto-generated method stub
		return null;
	}

	public collection.visualizer.common.ListenableVector<Shape> getPseudoCode() {
		// TODO Auto-generated method stub
		return null;
	}

	public void incrementPseudoCodeMarker() {
		// TODO Auto-generated method stub
		
	}

	public void decrementPseudoCodeMarker() {
		// TODO Auto-generated method stub
		
	}

	public int getPseudoCodeMarker() {
		// TODO Auto-generated method stub
		return 0;
	}

	public JPanel displayInPanel(ObservableDataType data) {
		// TODO Auto-generated method stub
		return null;
	}

	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	public JPanel displayInPanel() {
		// TODO Auto-generated method stub
		return null;
	}

}
