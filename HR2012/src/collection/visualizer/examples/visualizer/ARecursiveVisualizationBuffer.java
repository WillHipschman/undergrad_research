package collection.visualizer.examples.visualizer;

import java.util.Observable;
import java.util.Observer;

import bus.uigen.introspect.IntrospectUtility;
import bus.uigen.reflect.ClassProxy;
import bus.uigen.reflect.MethodProxy;
import bus.uigen.reflect.RemoteSelector;
import bus.uigen.undo.Command;
import bus.uigen.undo.CommandCreator;
import bus.uigen.undo.Inverses;

import collection.visualizer.CollectionVisualizer;
import collection.visualizer.controller.*;
import collection.visualizer.datatype.observer.AnObserverBuffer;
import collection.visualizer.datatype.observer.ObservableEventGenerator;

public class ARecursiveVisualizationBuffer extends AnObserverBuffer {

	public ARecursiveVisualizationBuffer(
			CollectionVisualizer<Observer, ObservableEventGenerator> visualizer) {
		super(visualizer);
	}

	@Override
	public void setObservable(ObservableEventGenerator observable) {
		super.setObservable(observable);

		((Control) copy).init(this.getVisualizer());

	}

	@Override
	public void update(Observable observable, Object arg1) {
		
		ClassProxy objClass = RemoteSelector.getClass(copy);
		MethodProxy method = IntrospectUtility.getMethod(objClass, arg1.toString(),
				objClass.voidType(), new ClassProxy[] {});
		
		Inverses.add("next", "previous");
		Command inverse = CommandCreator.createCommandBasic(null, method, copy,
				new Object[] {});

		this.getCommandHistory().addCommand(inverse);
	}

}
