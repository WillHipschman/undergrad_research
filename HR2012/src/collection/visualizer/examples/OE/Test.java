package collection.visualizer.examples.OE;

import collection.visualizer.common.*;

import collection.visualizer.controller.AController;
import collection.visualizer.examples.barChart.AnIntegerBarChartEventTrapper;
import collection.visualizer.examples.barChart.AnIntegerBarChartLayoutManager;
import collection.visualizer.examples.barChart.AnIntegerBarChartVisualizer;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class Test {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		ListenableVector<Integer> vector = new AListenableVector<Integer>();

		vector.add(5);
		vector.add(4);
		vector.add(3);
		vector.add(2);
		vector.add(1);
		
		try {
			AnIntegerBarChartVisualizer visualizer = new AnIntegerBarChartVisualizer();
			visualizer.visualize(vector);
			collection.visualizer.common.Algorithms.bubbleSort(vector);

			visualizer.addTrapper(new AnIntegerBarChartEventTrapper(visualizer,
					(AnIntegerBarChartLayoutManager) visualizer
							.getLayoutManager()));

			Object[] menuItems = {
					new collection.visualizer.examples.OE.Sorting(vector),
					new Algorithms(vector) };
			
			CreateCustomView viewCreator1 = new CreateCustomView();
			viewCreator1.createView(menuItems, visualizer,
					null, true, false);
			
			
			AController<Integer> controller = (AController) visualizer.getController();
			
			ObjectEditor.setPropertyComponentWidth(AListenableVector.class,
					"element", 300);
			OEFrame controllerFrame = ObjectEditor.edit(controller);
			controller.setFrame(controllerFrame);
			controllerFrame.setDemoFont();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
