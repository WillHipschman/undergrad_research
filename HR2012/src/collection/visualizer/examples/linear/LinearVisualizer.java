package collection.visualizer.examples.linear;

import collection.visualizer.CollectionVisualizer;
import bus.uigen.shapes.ALineModel;
import bus.uigen.shapes.Shape;
import util.models.VectorMethodsListener;
import collection.visualizer.common.*;
import collection.visualizer.layout.nodes.ALinearRoot;
import collection.visualizer.layout.nodes.LinearElement;

public interface LinearVisualizer<ElementType>
		extends
		CollectionVisualizer<VectorMethodsListener<ElementType>, ListenableVector<ElementType>> {

	ALinearRoot initRoot(Object element, LinearElement parent,
			LinearElement previousChild);

	LinearElement getRoot();

	void setRoot(LinearElement newVal);

	ListenableVector<Shape> shapes();

	LinearElement initElement(Object element, LinearElement parent,
			LinearElement parentChild);

	void removeLine(ALineModel l);

}
