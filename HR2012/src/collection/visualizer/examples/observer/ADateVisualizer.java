package collection.visualizer.examples.observer;

import java.util.Observable;
import java.util.Observer;


import bus.uigen.shapes.Shape;
import collection.visualizer.ACollectionVisualizer;
import collection.visualizer.CollectionVisualizer;
import collection.visualizer.controller.ButtonPressTrapper;

public class ADateVisualizer extends
		ACollectionVisualizer<Observer, ObservableDate> implements
		CollectionVisualizer<Observer, ObservableDate>, Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 553505402733826775L;

	public ADateVisualizer() {
		super();
		animationPauseTime = 10;

		layoutManager = new ADateLayoutManager(100, 100, 25);
		buffer = this.initializeBuffer();
	}

	public int theStepCount() {
		// TODO Auto-generated method stub
		return 0;
	}


	public void update(Observable date, Object arg1) {
		
		((ADateLayoutManager) layoutManager).update((ObservableDate) arg1);
	}

	@Override
	protected ObservableDate initializeBuffer() {
		return new ABufferDate(this);
	}

	public boolean removeElement(Shape c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ButtonPressTrapper initializeButtonPressTrapper() {
		// TODO Auto-generated method stub
		return null;
	}
}
