package collection.visualizer.examples.visualizer;

import java.awt.BorderLayout;
import java.util.Observable;

import bus.uigen.ObjectEditor;
import bus.uigen.shapes.Shape;
import collection.visualizer.common.AListenableVector;
import collection.visualizer.common.ListenableVector;
import collection.visualizer.common.Util;
import collection.visualizer.controller.Control;
import collection.visualizer.datatype.observer.*;
import collection.visualizer.layout.LayoutManager;
import collection.visualizer.layout.gui.APanelGenerator;

public class ARecursiveVisualizationTrapper extends AnObserverEventTrapper {

	private ARecursiveVisualizationVisualizer visualizer;
	private ListenableVector<Shape> previousVal;
	private LayoutManager layoutManager;
	private APanelGenerator panelGenerator = new APanelGenerator();

	public ARecursiveVisualizationTrapper(ARecursiveVisualizationVisualizer cv,
			ObservableEventGenerator observable,
			ARecursiveVisualizationLayoutManager layoutManager) {
		super(observable);
		this.layoutManager = layoutManager;
		visualizer = cv;
		previousVal = visualizer.getShapes();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable arg0, Object arg1) {

		if (arg1.equals("next")) {

			previousVal = Util.copyVector(visualizer.getShapes());
			visualizer.clear();

			ListenableVector<Shape> oldVal = ((Control) visualizer
					.getOriginalData().get(0)).getVisualizer().getShapes();
			visualizer.addAll(Util.copyVector(oldVal));

			layoutManager.getPanel().removeAll();
			ObjectEditor.editInDrawingContainer(Util.copyVector(oldVal),
					layoutManager.getPanel(), false);
			layoutManager.getPanel().add(
					panelGenerator.layoutAndPopulateButtonPanel(),
					BorderLayout.NORTH);
		}
		if (arg1.equals("previous")) {

			ListenableVector<Shape> temp = Util.copyVector(previousVal);
			previousVal = Util.copyVector(visualizer.getShapes());
			visualizer.clear();

			visualizer.addAll(temp);

			layoutManager.getPanel().removeAll();
			ObjectEditor.editInDrawingContainer(temp, layoutManager.getPanel(),
					false);
			layoutManager.getPanel().add(
					panelGenerator.layoutAndPopulateButtonPanel(),
					BorderLayout.NORTH);
		}

	}
}
