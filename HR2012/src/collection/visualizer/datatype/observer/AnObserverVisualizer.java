package collection.visualizer.datatype.observer;


import java.util.Observer;

import collection.visualizer.ACollectionVisualizer;
import collection.visualizer.CollectionVisualizer;
import collection.visualizer.controller.AGenericButtonPressTrapper;
import collection.visualizer.controller.ButtonPressTrapper;
import collection.visualizer.layout.LayoutManager;

public abstract class AnObserverVisualizer extends
		ACollectionVisualizer<Observer, ObservableEventGenerator> implements
		CollectionVisualizer<Observer, ObservableEventGenerator> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5270046450696451458L;

	ObservableEventGenerator observable;
	
	public AnObserverVisualizer(
			LayoutManager<ObservableEventGenerator> layoutManager,
			ObservableEventGenerator observable) {
		super();
		this.observable = observable;
		((AnObserverBuffer) buffer).setObservable(this.observable);
		this.layoutManager = layoutManager;

	}

	@Override
	protected abstract ObservableEventGenerator initializeBuffer();

	@Override
	public ButtonPressTrapper initializeButtonPressTrapper() {
		return new AGenericButtonPressTrapper(controller, this,
				0);
	}

}
