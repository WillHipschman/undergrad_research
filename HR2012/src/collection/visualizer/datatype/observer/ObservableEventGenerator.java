package collection.visualizer.datatype.observer;

import java.util.Observer;

import collection.visualizer.trappers.EventGenerator;

public interface ObservableEventGenerator extends
		EventGenerator<Observer, ObservableEventGenerator> {

}
