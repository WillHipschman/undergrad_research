package collection.visualizer.examples.observer;

import java.util.Observable;
import java.util.Observer;

import collection.visualizer.ABuffer;
import collection.visualizer.Buffer;
import collection.visualizer.CollectionVisualizer;
import collection.visualizer.controller.Control;
import collection.visualizer.threads.CommandHistory;
import collection.visualizer.trappers.*;


public class ABufferDate extends ObservableDate implements Observer,
		Buffer<Observer, ObservableDate>, EventTrapper<Observer, ObservableDate>{

	private CollectionVisualizer<Observer, ObservableDate> visualizer;
	protected Buffer<Observer, ObservableDate> buffer;

	private ObservableDate observable;

	public ABufferDate(CollectionVisualizer<Observer, ObservableDate> visualizer){
		this.visualizer = visualizer;
		buffer  = new ABuffer<Observer, ObservableDate>(this.visualizer);
	}
	public ABufferDate(ObservableDate date,
			CollectionVisualizer<Observer, ObservableDate> visualizer) {
		this(visualizer);
		observable = date;
		observable.addObserver(this);
		this.setDate(date.getDate());
	}

	public void update(Observable observable, Object changedObject) {
		if (changedObject == this.observable) {
			//buffer.getUndoer().addCommand(c);
			this.setDate(this.observable.getDate());
			this.setChanged();
			this.notifyObservers();
		}
	}

	public CommandHistory getCommandHistory() {
		return buffer.getCommandHistory();
	}

	public CollectionVisualizer<Observer, ObservableDate> getVisualizer() {
		return visualizer;
	}

	public void putBufferThread(
			CollectionVisualizer<Observer, ObservableDate> v, Control control) {
		buffer.putBufferThread(v, control);

	}

	@SuppressWarnings("deprecation")
	public void setBufferData(ObservableDate data) {
		buffer.setBufferData(data);
		observable = data;
		observable.addObserver(this);
		this.setDate(date.getDate());
		
	}
	public ObservableDate getBufferData() {
		return buffer.getBufferData();
	}
}
