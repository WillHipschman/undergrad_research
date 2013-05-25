package collection.visualizer;

import collection.visualizer.controller.Control;
import collection.visualizer.threads.CommandHistory;
import collection.visualizer.trappers.*;

public interface Buffer<Observer, ObservableDataType extends EventGenerator<Observer, ObservableDataType>> {
	CommandHistory getCommandHistory();

	void putBufferThread(CollectionVisualizer<Observer, ObservableDataType> v,
			Control control);
	void setBufferData(ObservableDataType data);
	public ObservableDataType getBufferData();
}
