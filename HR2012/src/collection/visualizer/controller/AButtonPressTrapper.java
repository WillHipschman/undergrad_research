package collection.visualizer.controller;

import collection.visualizer.ACollectionVisualizer;
import collection.visualizer.CollectionVisualizer;

@SuppressWarnings("rawtypes")
public abstract class AButtonPressTrapper implements ButtonPressTrapper {

	protected Control controller;
	protected CollectionVisualizer visualizer;

	public AButtonPressTrapper(Control controller,
			CollectionVisualizer visualizer) {
		this.controller = controller;
		this.visualizer = visualizer;
	}

	public void maybeDiscardNextButtonPress() {
		if(null == controller){
			controller = ((ACollectionVisualizer) visualizer).getController();
		}
		controller.setMoveForward(true);
		visualizer.setCanProceed(true);
		visualizer.notifyVisualizer();

	}


	public void maybeDiscardPreviousButtonPress() {
		if(null == controller){
			controller = ((ACollectionVisualizer) visualizer).getController();
		}
		controller.setMoveForward(false);
		visualizer.setCanProceed(true);
		visualizer.notifyVisualizer();
		
	}
}
