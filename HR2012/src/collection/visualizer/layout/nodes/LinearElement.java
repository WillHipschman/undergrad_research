package collection.visualizer.layout.nodes;

import bus.uigen.shapes.ALineModel;

public interface LinearElement extends VisualizerElement<LinearElement>{
	
	public int getHorizontalLineYOffset();
	public int getVerticalLineXOffset();
	public int getElementShapeYOffset();
	public int getElementShapeXOffset();
	public void setHorizontalLineYOffset(int newY);
	public void setVerticalLineXOffset(int newX);
	public void setElementShapeYOffset(int newY);
	public void setElementShapeXOffset(int newX);
	
	public ALineModel getHorizontalLine();
	public ALineModel getVerticalLine();
}
