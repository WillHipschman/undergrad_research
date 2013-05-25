package collection.visualizer.examples;

import java.util.Date;

import collection.visualizer.controller.AController;
import collection.visualizer.datatype.bean.ABeanVisualizer;
import collection.visualizer.examples.OE.CreateCustomView;
import collection.visualizer.examples.bean.ABeanDate;
import collection.visualizer.examples.bean.ABeanDateEventTrapper;
import collection.visualizer.examples.bean.ABeanDateLayoutManager;

public class Bean {
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {

		try {

			ABeanDate date = new ABeanDate();
			ABeanVisualizer visualizer = new ABeanVisualizer(
					new ABeanDateLayoutManager(100, 100, 50), date);

			ABeanDateEventTrapper trapper = new ABeanDateEventTrapper(
					(ABeanDateLayoutManager) visualizer.getLayoutManager(),
					new ABeanDate());

			visualizer.visualize(date);
			visualizer.addTrapper(trapper);

			Object[] menuItems = {};
			CreateCustomView viewCreator1 = new CreateCustomView();

			viewCreator1.createView(menuItems, visualizer, visualizer
					.getLayoutManager().getPseudoCode(), true, false);

			Clock timer = new Clock();
			while (true) {
				Date d = new Date();
				date.setDate(d);
				((AController)visualizer.getController()).next();
				timer.waitOneSecond();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void waitOneSecond() {
		try {
			this.wait(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
