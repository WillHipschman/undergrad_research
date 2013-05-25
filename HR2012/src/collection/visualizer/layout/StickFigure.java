package collection.visualizer.layout;

import util.models.AListenableVector;
import util.models.ListenableVector;
import bus.uigen.shapes.ALineModel;
import bus.uigen.shapes.AnOvalModel;
import bus.uigen.shapes.Shape;

public class StickFigure {

	CompositeShape composite;

	public StickFigure() {
		AnOvalModel head = new AnOvalModel(10, 10, 10, 10);

		ALineModel body = new ALineModel(15, 20, 0, 10);

		ALineModel arm1 = new ALineModel(15, 26, 5, -5);
		ALineModel arm2 = new ALineModel(15, 26, -5, -5);
		ALineModel leg1 = new ALineModel(15, 30, 5, 5);
		ALineModel leg2 = new ALineModel(15, 30, -5, 5);

		ListenableVector<Shape> shapes = new AListenableVector<Shape>();

		shapes.add(head);
		shapes.add(body);
		shapes.add(arm1);
		shapes.add(arm2);
		shapes.add(leg1);
		shapes.add(leg2);

		composite = new ACompositeShape(shapes);
	}

	public CompositeShape createStickFigure() {
		return composite;
	}
}
