package collection.visualizer.examples.linear;


import java.awt.Color;

import javax.swing.JPanel;

import collection.visualizer.CollectionVisualizer;
import collection.visualizer.PseudoCode;
import collection.visualizer.common.*;
import collection.visualizer.layout.LayoutManager;

import util.models.VectorMethodsListener;

import bus.uigen.shapes.AStringModel;
import bus.uigen.shapes.Shape;
import bus.uigen.shapes.TextShape;

public class ALinearLayoutManager<ElementType> implements
		LayoutManager<ListenableVector<ElementType>> {

	protected LinearVisualizer<ElementType> visualizer;
	protected ListenableVector<Shape> pseudoCode;
	protected int boxWidth = 0, boxHeight = 0;
	protected int verticalSpacing = 0, horizontalSpacing = 0;
	protected Color highlighting, color;
	protected boolean alignVertical, dynamicWidth, dynamicHeight, solid;
	protected int currentPseudoCodeMarker = 0;
	protected Shape shape;

	private Shape previousPseudoCodeLine, currentPseudoCodeLine;
	
	private JPanel panel = new JPanel();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ALinearLayoutManager(
			CollectionVisualizer<VectorMethodsListener<ElementType>, ListenableVector<ElementType>> cv) {

		pseudoCode = new AListenableVector<Shape>();
		visualizer = (LinearVisualizer) cv;

		horizontalSpacing = 50;
		verticalSpacing = 10;
	}

	public ListenableVector<Shape> display(
			ListenableVector<ElementType> vector) {
		visualizer.setRoot(visualizer.initRoot(vector, null, null));
		// initElements(root, vector);
		visualizer.getRoot().focusPosition(visualizer.getRoot());
		//((AListenableVector) visualizer).add(constructPseudoCode());
		constructPseudoCode();
		return visualizer.shapes();
	}

	public ListenableVector<Shape> constructPseudoCode() {
		int i = 20;
		for (String s : PseudoCode.pseudoCode) {
			int numLeadingSpaces = Util.findLeadingSpaces(s);
			this.pseudoCode.add(new AStringModel(s,  10+ numLeadingSpaces, i,
					100, 20));
			i += 20;
		}
		return this.pseudoCode;
	}

	public ListenableVector<Shape> getPseudoCode() {
		return this.pseudoCode;
	}



	public Shape createShape(String stringValue, int initX, int initY) {

		try {
			Shape s = this.shape.getClass().newInstance();

			try {
				s.setHeight(boxHeight
						* (dynamicHeight ? Integer.parseInt(stringValue) : 1));

				s.setWidth(boxWidth
						* (dynamicWidth ? Integer.parseInt(stringValue) : 1));
			} catch (Exception e) {
				s.setHeight(boxHeight);
				s.setWidth(boxWidth);
			}
			s.setFilled(solid);

			if (s instanceof TextShape) {
				((TextShape) s).setText(stringValue);
			}

			return s;
		} catch (InstantiationException e) {
			return null;
		} catch (IllegalAccessException e) {
			return null;
		}
	}

	public int getBoxWidth() {
		return boxWidth;
	}

	public int getBoxHeight() {
		return boxHeight;
	}

	public int getHorizontalSpacing() {
		return horizontalSpacing;
	}

	public int getVerticalSpacing() {
		return verticalSpacing;
	}

	public Color getHighlighting() {
		return highlighting;
	}

	public Color getColor() {
		return color;
	}

	public boolean getVertical() {
		return alignVertical;
	}

	public boolean getDynamicWidth() {
		return dynamicWidth;
	}

	public boolean getDynamicHeight() {
		return dynamicHeight;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
		boxWidth = shape.getWidth();
		boxHeight = shape.getHeight();
	}

	public void setHighlighting(Color highlighting) {
		this.highlighting = highlighting;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setVertical(boolean alignVertical) {
		this.alignVertical = alignVertical;
	}

	public void setDynamicWidth(boolean dynamicWidth) {
		this.dynamicWidth = dynamicWidth;
	}

	public void setDynamicHeight(boolean dynamicHeight) {
		this.dynamicHeight = dynamicHeight;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public boolean getSolid() {
		return solid;
	}

	public int getPseudoCodeMarker() {
		return currentPseudoCodeMarker;
	}

	private void setPseudoCodeMarker(int newVal) {
		if (newVal > 0 && newVal < pseudoCode.size() + 1) {
			currentPseudoCodeMarker = newVal;
		}
	}

	public synchronized void setPseudoCodePointer(int newVal) {

		previousPseudoCodeLine = currentPseudoCodeLine;
		if (null != previousPseudoCodeLine) {
			previousPseudoCodeLine.setColor(color);
		}

		this.setPseudoCodeMarker(newVal);

		currentPseudoCodeLine = pseudoCode.get(currentPseudoCodeMarker - 1);
		currentPseudoCodeLine.setColor(highlighting);
	}

	public void clearPseudoCodePointers() {
		for(Shape s: pseudoCode){
			s.setColor(color);
		}
		currentPseudoCodeMarker = 0;
		
	}

	public JPanel displayInPanel() {
		return panel;
	}

	public JPanel getPanel() {
		return panel;
	}
	public void setPanel(JPanel newVal) {
		panel = newVal;
	}
}
