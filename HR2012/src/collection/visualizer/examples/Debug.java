package collection.visualizer.examples;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
import collection.visualizer.common.*;
import collection.visualizer.controller.AController;
import collection.visualizer.examples.OE.Algorithms;
import collection.visualizer.examples.OE.CreateCustomView;
import collection.visualizer.examples.barChart.AnIntegerBarChartEventTrapper;
import collection.visualizer.examples.barChart.AnIntegerBarChartLayoutManager;
import collection.visualizer.examples.barChart.AnIntegerBarChartVisualizer;

public class Debug {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ListenableVector<Integer> vector = new AListenableVector<Integer>();

		vector.add(5);
		vector.add(4);
		vector.add(3);
		vector.add(2);
		vector.add(1);

		try {
			ObjectEditor.setDefaultAttribute(AttributeNames.SEPARATE_THREAD,
					true);
			ObjectEditor.setDefaultAttribute(AttributeNames.SHOW_SYSTEM_MENUS,
					false);

			AnIntegerBarChartVisualizer visualizer = new AnIntegerBarChartVisualizer();
			visualizer.visualize(vector);
			visualizer.addTrapper(new AnIntegerBarChartEventTrapper(visualizer,
					(AnIntegerBarChartLayoutManager) visualizer
							.getLayoutManager()));

			Object[] menuItems = {
					new collection.visualizer.examples.OE.Sorting(vector),
					new Algorithms(vector) };
			
			CreateCustomView viewCreator1 = new CreateCustomView();
			viewCreator1.createView(menuItems, visualizer, null, true, false);

			OEFrame controllerFrame = ObjectEditor.edit(visualizer
					.getController());
			((AController<Integer>) visualizer.getController())
					.setFrame(controllerFrame);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
