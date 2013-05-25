package collection.visualizer.examples;

import java.util.Date;

import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;

import collection.visualizer.examples.OE.CreateCustomView;
import collection.visualizer.examples.observer.ADateEventTrapper;
import collection.visualizer.examples.observer.ADateVisualizer;
import collection.visualizer.examples.observer.ObservableDate;

public class Clock {

	public static void main(String[] args) {

		try {
			

			ObjectEditor.setDefaultAttribute(AttributeNames.SEPARATE_THREAD, true);
			ObjectEditor.setDefaultAttribute(AttributeNames.SHOW_SYSTEM_MENUS,
					false);
			
			ADateVisualizer visualizer = new ADateVisualizer();
			ObservableDate date = new ObservableDate();
			ADateEventTrapper trapper = new ADateEventTrapper(visualizer);

			visualizer.addTrapper(trapper);
			visualizer.visualize(date);

			Object[] menuItems = {};
			
			
			CreateCustomView viewCreator1 = new CreateCustomView();
			viewCreator1.createView(menuItems, visualizer,
					visualizer.getLayoutManager().getPseudoCode(), false, false);
			//ObjectEditor.edit(visualizer);

			Clock timer = new Clock();

			while (true) {
				date.setDate(new Date());
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
