package collection.visualizer.layout.nodes;

import util.models.ListenableVector;
import bus.uigen.shapes.Shape;

public interface VisualizerElement<ElementType> extends Shape, LinkedTree<ElementType>
{	
	public void focusPosition(ElementType node);
	public void focusPosition();
	
	public Shape getShape();
	
	public ListenableVector<ElementType> getVector();
	
	public Object getObject();
	public void setObject(Object o);
	public void setVertical(boolean b);
}