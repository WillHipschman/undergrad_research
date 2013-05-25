package collection.visualizer;

import java.util.HashMap;

import collection.visualizer.controller.ButtonPressTrapper;
import collection.visualizer.threads.CommandHistory;

import collection.visualizer.trappers.*;

public interface DelegatingCollectionVisualizer<Observer, ObservableDataType extends EventGenerator<Observer, ObservableDataType>>
		extends TrapperChainSupporter<Observer, ObservableDataType> {

	void visualize(ObservableDataType listenableVector) throws Exception;

	HashMap<Integer, ObservableDataType> getOriginalData();

	void setCanProceed(boolean b);

	boolean getCanProceed();

	void changeAnimationPauseTime(int n);

	void notifyVisualizer();

	void waitForNextBufferThreadStep();

	CommandHistory getUndoer();
	
	ButtonPressTrapper initializeButtonPressTrapper();

}
