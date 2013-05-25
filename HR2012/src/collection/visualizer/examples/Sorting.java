package collection.visualizer.examples;

import collection.visualizer.common.*;
import collection.visualizer.examples.OE.Algorithms;
import collection.visualizer.examples.OE.CreateCustomView;
import collection.visualizer.examples.barChart.AnIntegerBarChartEventTrapper;
import collection.visualizer.examples.barChart.AnIntegerBarChartLayoutManager;
import collection.visualizer.examples.barChart.AnIntegerBarChartVisualizer;

public class Sorting {

	public static void main(String[] args) {
		ListenableVector<Integer> vector = new AListenableVector<Integer>();

		vector.add(10);
		vector.add(9);
		vector.add(8);
		vector.add(7);
		vector.add(6);
		vector.add(5);
		vector.add(4);
		vector.add(3);
		vector.add(2);
		vector.add(1);

		try {
			AnIntegerBarChartVisualizer visualizer = new AnIntegerBarChartVisualizer();
			visualizer.visualize(vector);
			visualizer.addTrapper(new AnIntegerBarChartEventTrapper(visualizer,
					(AnIntegerBarChartLayoutManager) visualizer
							.getLayoutManager()));

			CreateCustomView viewCreator = new CreateCustomView();
			viewCreator.setDataPanel(visualizer.getLayoutManager()
					.displayInPanel());

			Object[] menuItems = {
					new collection.visualizer.examples.OE.Sorting(vector),
					new Algorithms(vector) };

			CreateCustomView viewCreator1 = new CreateCustomView();
			viewCreator1.createView(menuItems, visualizer, visualizer
					.getLayoutManager().getPseudoCode(), true, false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void visualize(ListenableVector<Integer> vector)
			throws Exception {

		AnIntegerBarChartVisualizer visualizer = new AnIntegerBarChartVisualizer();
		visualizer.visualize(vector);

		visualizer
				.addTrapper(new AnIntegerBarChartEventTrapper(visualizer,
						(AnIntegerBarChartLayoutManager) visualizer
								.getLayoutManager()));

		Object[] menuItems = {};

		CreateCustomView viewCreator1 = new CreateCustomView();
		viewCreator1.createView(menuItems, visualizer, visualizer
				.getLayoutManager().getPseudoCode(), true, false);
	}

}
