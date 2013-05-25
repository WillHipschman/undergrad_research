package collection.visualizer;

import collection.visualizer.controller.Control;
import collection.visualizer.threads.AHistoryUndoer;
import collection.visualizer.threads.BufferThread;
import collection.visualizer.threads.CommandHistory;
import collection.visualizer.trappers.*;

public class ABuffer<Observer, ObservableDataType extends EventGenerator<Observer, ObservableDataType>>
		implements Buffer<Observer, ObservableDataType> {

	protected CommandHistory commandHistory = new AHistoryUndoer();;
	protected BufferThread bufferThread;
	protected CollectionVisualizer<Observer, ObservableDataType> visualizer;
	protected ObservableDataType data;

	public ABuffer(CollectionVisualizer<Observer, ObservableDataType> visualizer){
		this.visualizer = visualizer;
	}
	
	public CommandHistory getCommandHistory() {
		return commandHistory;
	}

	public void putBufferThread(
			CollectionVisualizer<Observer, ObservableDataType> v,
			Control control) {
		BufferThread b = new BufferThread(commandHistory, control, v);
		Thread bufferThread = new Thread(b);
		bufferThread.start();
	}

	public CollectionVisualizer<Observer, ObservableDataType> getVisualizer() {
		return visualizer;
	}

	public void setBufferData(ObservableDataType data) {
		this.data = data;

	}
	public ObservableDataType getBufferData() {
		return this.data;

	}
}
