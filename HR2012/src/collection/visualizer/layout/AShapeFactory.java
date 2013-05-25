package collection.visualizer.layout;

import util.models.AListenableVector;
import util.models.ListenableVector;
import bus.uigen.shapes.*;

public class AShapeFactory implements ShapeFactory
{
	Shape theShape;
	public AShapeFactory(Shape shape)
	{
		theShape=shape;
	}
	public Shape create()
	{
		try 
		{
			if(theShape instanceof APointModel)
				return new APointModel(0,0);
			if(theShape instanceof CompositeShape){
				ListenableVector<Shape> v = new AListenableVector<Shape>();
				for(Shape shape : ((CompositeShape)theShape).getShapes()){
					Shape s1 = shape.getClass().newInstance();
					s1.setHeight(shape.getHeight());
					s1.setWidth(shape.getWidth());
					s1.setX(shape.getX());
					s1.setY(shape.getY());
					v.add(s1);
				}
				CompositeShape s = new ACompositeShape(v);
				return s;
			}
			return theShape.getClass().newInstance();
		} 
		catch (InstantiationException e) 
		{
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
}
