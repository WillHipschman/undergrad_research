package collection.visualizer.layout;

import java.util.List;

import bus.uigen.shapes.Shape;

public interface CompositeShape extends Shape{

	public List<Shape> getShapes();
	public void setShapes(List<Shape> _shapes);

}
