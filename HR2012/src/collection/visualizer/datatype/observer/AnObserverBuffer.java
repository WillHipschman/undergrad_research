package collection.visualizer.datatype.observer;

import java.util.Observable;
import java.util.Observer;

import collection.visualizer.ABuffer;
import collection.visualizer.Buffer;
import collection.visualizer.CollectionVisualizer;
import collection.visualizer.trappers.EventTrapper;

public abstract class AnObserverBuffer extends
		ABuffer<Observer, ObservableEventGenerator> implements Observer,
		Buffer<Observer, ObservableEventGenerator>,
		EventTrapper<Observer, ObservableEventGenerator>,
		ObservableEventGenerator {

	protected ObservableEventGenerator copy;
	
	public AnObserverBuffer(
			CollectionVisualizer<Observer, ObservableEventGenerator> visualizer) {
		super(visualizer);
	}

	public ObservableEventGenerator getCopy(){
		return copy;
	}
	public void setObservable(ObservableEventGenerator observable) {
		try {
			copy = observable.getClass().newInstance();
			this.setBufferData(observable);
			observable.addListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addListener(
			EventTrapper<Observer, ObservableEventGenerator> observer)
			throws Exception {
		copy.addListener(observer);

	}

	public void removeListener(
			EventTrapper<Observer, ObservableEventGenerator> observer) {
		copy.removeListener(observer);

	}

	public abstract void update(Observable arg0, Object arg1);
}
