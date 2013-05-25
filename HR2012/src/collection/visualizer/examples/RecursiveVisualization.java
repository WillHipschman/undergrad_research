package collection.visualizer.examples;

import collection.visualizer.common.AListenableVector;
import collection.visualizer.common.ListenableVector;
import collection.visualizer.datatype.observer.ObservableEventGenerator;
import collection.visualizer.examples.OE.Algorithms;
import collection.visualizer.examples.OE.CreateCustomView;
import collection.visualizer.examples.barChart.AnIntegerBarChartEventTrapper;
import collection.visualizer.examples.barChart.AnIntegerBarChartLayoutManager;
import collection.visualizer.examples.barChart.AnIntegerBarChartVisualizer;
import collection.visualizer.examples.visualizer.ARecursiveVisualizationBuffer;
import collection.visualizer.examples.visualizer.ARecursiveVisualizationLayoutManager;
import collection.visualizer.examples.visualizer.ARecursiveVisualizationTrapper;
import collection.visualizer.examples.visualizer.ARecursiveVisualizationVisualizer;

public class RecursiveVisualization {

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
			AnIntegerBarChartVisualizer v1 = new AnIntegerBarChartVisualizer();
			v1.visualize(vector);
			v1.addTrapper(new AnIntegerBarChartEventTrapper(v1,
					(AnIntegerBarChartLayoutManager) v1.getLayoutManager()));

			Object[] menuItems = {
					new collection.visualizer.examples.OE.Sorting(vector),
					new Algorithms(vector) };

			ObservableEventGenerator controller = v1.getController();

			ARecursiveVisualizationVisualizer beanVisualizer = new ARecursiveVisualizationVisualizer(
					new ARecursiveVisualizationLayoutManager(), controller);

			ARecursiveVisualizationTrapper trapper = new ARecursiveVisualizationTrapper(
					beanVisualizer,
					((ARecursiveVisualizationBuffer) beanVisualizer.getBuffer())
							.getCopy(), (ARecursiveVisualizationLayoutManager) beanVisualizer.getLayoutManager());
			beanVisualizer.addTrapper(trapper);
			
			collection.visualizer.common.Algorithms.shellSort(vector);

			CreateCustomView viewCreator1 = new CreateCustomView();
			CreateCustomView viewCreator2 = new CreateCustomView();
			
			viewCreator1.createView(menuItems, v1, v1
			 .getLayoutManager().getPseudoCode(), true, false);
			
			viewCreator2.setDataPanel(beanVisualizer.getLayoutManager().getPanel());
			viewCreator2.createNestedView(menuItems, beanVisualizer, beanVisualizer
			 .getLayoutManager().getPseudoCode(), true, true);
			
//			JFrame f = new JFrame("The Frame");
//			f.setLocation(100, 100);
//			Container content = v1.getLayoutManager().displayInPanel();
//			f.setContentPane(content);
//			f.setSize(600, 600);
//			f.setVisible(true);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
