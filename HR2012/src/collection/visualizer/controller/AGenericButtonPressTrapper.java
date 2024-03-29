package collection.visualizer.controller;

import collection.visualizer.ACollectionVisualizer;
import collection.visualizer.CollectionVisualizer;

@SuppressWarnings("rawtypes")
public class AGenericButtonPressTrapper extends AButtonPressTrapper implements
		ButtonPressTrapper {

	public AGenericButtonPressTrapper(Control controller,
			CollectionVisualizer visualizer, int pauseTimeInMillisecond) {
		super(controller, visualizer);
		// this.pauseTimeInMillisecond = pauseTimeInMillisecond;
	}

	@Override
	public void maybeDiscardNextButtonPress() {

		super.maybeDiscardNextButtonPress();

	}

	@Override
	public void maybeDiscardPreviousButtonPress() {

		super.maybeDiscardPreviousButtonPress();
	}

	public void maybeDiscardLastButtonPress() {
		initializeComponents();
		controller.setSynchronous(true);
		super.maybeDiscardNextButtonPress();

	}

	public void maybeDiscardFirstButtonPress() {
		initializeComponents();
		controller.setSynchronous(true);
		super.maybeDiscardPreviousButtonPress();

	}

	private void initializeComponents() {
		if (null == controller) {
			controller = ((ACollectionVisualizer) visualizer).getController();
		}
	}

}
