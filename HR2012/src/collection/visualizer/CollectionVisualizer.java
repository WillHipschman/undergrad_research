package collection.visualizer;

import bus.uigen.shapes.Shape;
import collection.visualizer.common.ListenableVector;
import collection.visualizer.controller.Control;
import collection.visualizer.layout.LayoutManager;
import collection.visualizer.trappers.*;

public interface CollectionVisualizer<Observer, ObservableDataType extends EventGenerator<Observer, ObservableDataType>>
		extends DelegatingCollectionVisualizer<Observer, ObservableDataType>/*, ListenableVector<Shape>*/ {

	LayoutManager<ObservableDataType> getLayoutManager();
	Control getController();
	ListenableVector<Shape> getShapes();
	


}
