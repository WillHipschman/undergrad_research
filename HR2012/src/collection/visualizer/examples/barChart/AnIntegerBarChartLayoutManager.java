package collection.visualizer.examples.barChart;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import collection.visualizer.common.*;
import collection.visualizer.layout.LayoutManager;
import collection.visualizer.layout.gui.APanelGenerator;

import bus.uigen.ObjectEditor;
import bus.uigen.shapes.ARectangleModel;
import bus.uigen.shapes.Shape;

public class AnIntegerBarChartLayoutManager implements
		LayoutManager<ListenableVector<Integer>> {

	private int currX = 220;
	private int baseLineY = 200;
	private int barWidth = 10;
	private int scaleFactor = 10;
	private int xSpacing = 15;
	
	private JPanel panel = new JPanel();
	private APanelGenerator panelGenerator = new APanelGenerator();
	
	private AnIntegerBarChartVisualizer visualizer;

	public AnIntegerBarChartLayoutManager(AnIntegerBarChartVisualizer visualizer){
		this.visualizer = visualizer;
	}
	
	public ListenableVector<Shape> display(ListenableVector<Integer> v) {

		ListenableVector<Shape> shapes = new AListenableVector<Shape>();

		for (Integer i : v) {
			int height = i * scaleFactor;
			int currY = baseLineY - height;
			Shape s = new ARectangleModel(currX, currY, barWidth, height);
			s.setFilled(true);
			shapes.add(s);
			currX += xSpacing;
		}
		
		ObjectEditor.editInDrawingContainer(Util.copyVector(shapes), panel, false);	
		panel.add(panelGenerator.layoutAndPopulateButtonPanel(),BorderLayout.NORTH);
		return shapes;
	}

	public Shape nextShape(int val) {
		int height = val * scaleFactor;
		int currY = baseLineY - height;
		Shape retVal = new ARectangleModel(currX, currY, barWidth, height);
		currX += xSpacing;
		return retVal;
	}

	public ListenableVector<Shape> constructPseudoCode() {
		// TODO Auto-generated method stub
		return null;
	}

	public ListenableVector<Shape> getPseudoCode() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getPseudoCodeMarker() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getScaleFactor() {
		return scaleFactor;
	}

	public int getBaseLine() {
		return baseLineY;
	}

	public JPanel displayInPanel() {
		return panel;
	}

	public JPanel getPanel() {
		return panel;
	}
}
