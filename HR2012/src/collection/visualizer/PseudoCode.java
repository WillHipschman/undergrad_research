package collection.visualizer;

import collection.visualizer.common.ListenableVector;
import collection.visualizer.common.AListenableVector;

public class PseudoCode {
	public static ListenableVector<String> pseudoCode = new AListenableVector<String>() {
		{
			add("public synchronized void previous() {");
			add("	buttonPressTrapper.maybeDiscardPreviousButtonPress();");
			add("	this.setChanged();");
			add("	this.notifyObservers(\"previous\");");
			add("}");
			add("");
			add("public synchronized void next() {");
			add("	buttonPressTrapper.maybeDiscardNextButtonPress();");
			add("	this.setChanged();");
			add("	this.notifyObservers(\"next\");");
			add("}");
		}
	};
}