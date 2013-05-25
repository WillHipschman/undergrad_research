package collection.visualizer.layout;

import javax.swing.JPanel;

import collection.visualizer.common.*;

import bus.uigen.shapes.Shape;

public interface LayoutManager<UserDateType> {

	ListenableVector<Shape> display(UserDateType data);
	
	JPanel displayInPanel();
	
	JPanel getPanel();

	ListenableVector<Shape> constructPseudoCode();

	ListenableVector<Shape> getPseudoCode();

	int getPseudoCodeMarker();

}
