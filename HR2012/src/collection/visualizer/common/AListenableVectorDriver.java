package collection.visualizer.common;

import util.models.AListenableVector;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class AListenableVectorDriver {
	public static void main (String[] args) {
//		ObjectEditor.setDefaultAttribute(AttributeNames.SHOW_SYSTEM_MENUS, false);
		AListenableVector<String> vector = new AListenableVector<String>();
		vector.add("hello");
		vector.add("bye");
		ObjectEditor.setPropertyComponentWidth(AListenableVector.class, "element", 300);
		OEFrame frame =		ObjectEditor.edit(vector);
		frame.setDemoFont();
		try {
			frame.select(vector, 0);
			Thread.sleep (5000);
			frame.select(vector, 1);
			Thread.sleep (5000);
			frame.select(vector, 1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
