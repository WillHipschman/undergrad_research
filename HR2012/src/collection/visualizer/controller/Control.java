package collection.visualizer.controller;

import collection.visualizer.CollectionVisualizer;
import collection.visualizer.datatype.observer.ObservableEventGenerator;

@SuppressWarnings("rawtypes")
public interface Control extends ObservableEventGenerator{
	
	void first();
	void previous();
	void next();
	void last();
	
	
	CollectionVisualizer getVisualizer();
	boolean getSynchronous();
	void setSynchronous(boolean newVal);
	boolean getMoveForward();
	void setMoveForward(boolean newVal);
	void commandExecuted();
	void init(CollectionVisualizer cv);
}
