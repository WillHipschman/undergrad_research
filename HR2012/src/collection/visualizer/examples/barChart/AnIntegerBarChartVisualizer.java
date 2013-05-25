package collection.visualizer.examples.barChart;

import util.models.VectorMethodsListener;

import collection.visualizer.ACollectionVisualizer;
import collection.visualizer.common.*;
import collection.visualizer.controller.AGenericButtonPressTrapper;
import collection.visualizer.controller.ButtonPressTrapper;
import collection.visualizer.datatype.vector.ALinearBuffer;

public class AnIntegerBarChartVisualizer
		extends
		ACollectionVisualizer<VectorMethodsListener<Integer>, ListenableVector<Integer>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1409522039257517809L;

	public AnIntegerBarChartVisualizer() throws Exception {
		super();
		layoutManager = new AnIntegerBarChartLayoutManager(this);
	}

	@Override
	protected ListenableVector<Integer> initializeBuffer() {
		return new ALinearBuffer<Integer>(this);
	}

	@Override
	public ButtonPressTrapper initializeButtonPressTrapper() {
		return new AGenericButtonPressTrapper(this.getController(), this, 40);
	}

	public int theStepCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
