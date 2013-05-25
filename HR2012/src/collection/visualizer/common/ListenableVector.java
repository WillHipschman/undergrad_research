package collection.visualizer.common;

import collection.visualizer.trappers.*;

public interface ListenableVector<ElementType>
		extends
		util.models.ListenableVector<ElementType>,
		EventGenerator<util.models.VectorMethodsListener<ElementType>, ListenableVector<ElementType>> {

}
