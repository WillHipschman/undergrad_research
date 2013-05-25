package collection.visualizer.common;


import collection.visualizer.trappers.*;
import util.models.VectorMethodsListener;

public class AListenableVector<ElementType> extends
		util.models.AListenableVector<ElementType>
		implements
		ListenableVector<ElementType>,
		EventGenerator<util.models.VectorMethodsListener<ElementType>, ListenableVector<ElementType>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2780834614415774024L;

	@SuppressWarnings("unchecked")
	public void addListener(
			EventTrapper<VectorMethodsListener<ElementType>, ListenableVector<ElementType>> observer)
			throws Exception {
		if (!(observer instanceof VectorMethodsListener))
			throw new Exception("Ill Defined Trapper: The trapper "
					+ observer.toString()
					+ "must be an instance of an observer and an observable");
		this.addVectorMethodsListener((VectorMethodsListener<ElementType>) observer);

	}

	@SuppressWarnings("unchecked")
	public void removeListener(
			EventTrapper<VectorMethodsListener<ElementType>, ListenableVector<ElementType>> observer) {
		if (observer instanceof VectorMethodsListener) {
			this.removeVectorMethodsListener((VectorMethodsListener<ElementType>) observer);
			
		}

	}

}
