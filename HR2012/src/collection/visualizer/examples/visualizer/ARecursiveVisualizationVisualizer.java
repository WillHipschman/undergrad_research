package collection.visualizer.examples.visualizer;

import collection.visualizer.datatype.observer.AnObserverVisualizer;
import collection.visualizer.datatype.observer.ObservableEventGenerator;
import collection.visualizer.layout.LayoutManager;

public class ARecursiveVisualizationVisualizer extends AnObserverVisualizer{

	private static final long serialVersionUID = 3304598343198436078L;

	public ARecursiveVisualizationVisualizer(
			LayoutManager<ObservableEventGenerator> layoutManager,
			ObservableEventGenerator observable) throws Exception {
		super(layoutManager, observable);
		this.visualize(observable);
	}

	@Override
	protected ObservableEventGenerator initializeBuffer() {
		
		ARecursiveVisualizationBuffer buffer = new ARecursiveVisualizationBuffer(this);
		buffer.setBufferData(originalData.get(0));
		return buffer;
	}

}
