package collection.visualizer.examples.visualizer;


import java.awt.BorderLayout;

import javax.swing.JPanel;

import bus.uigen.ObjectEditor;
import bus.uigen.shapes.AStringModel;
import bus.uigen.shapes.Shape;
import collection.visualizer.PseudoCode;
import collection.visualizer.controller.*;
import collection.visualizer.common.AListenableVector;
import collection.visualizer.common.ListenableVector;
import collection.visualizer.common.Util;
import collection.visualizer.datatype.observer.ObservableEventGenerator;
import collection.visualizer.layout.LayoutManager;
import collection.visualizer.layout.gui.APanelGenerator;

public class ARecursiveVisualizationLayoutManager implements
		LayoutManager<ObservableEventGenerator> {

	private JPanel panel = new JPanel();
	private APanelGenerator panelGenerator = new APanelGenerator();
	protected ListenableVector<Shape> pseudoCode =  new AListenableVector<Shape>();
	

	@SuppressWarnings("unchecked")
	public ListenableVector<Shape> display(ObservableEventGenerator control) {
		this.constructPseudoCode();
		
		if (!(control instanceof ARecursiveVisualizationBuffer)) {
			return null;
		} else {
		
			ListenableVector<Shape> oldVal = ((Control) ((ARecursiveVisualizationBuffer) control)
					.getBufferData()).getVisualizer().getShapes();

			
			ObjectEditor.editInDrawingContainer(Util.copyVector(oldVal), panel, false);	
			panel.add(panelGenerator.layoutAndPopulateButtonPanel(),BorderLayout.NORTH);
			return Util.copyVector(oldVal);
			
		}
	}

	public JPanel displayInPanel() {
		return panel;
	}

	public JPanel getPanel() {
		return panel;
	}

	public ListenableVector<Shape> constructPseudoCode() {
		int i = 20;
		for (String s : PseudoCode.pseudoCode) {
			int numLeadingSpaces = Util.findLeadingSpaces(s);
			this.pseudoCode.add(new AStringModel(s,  10+ numLeadingSpaces, i,
					100, 20));
			i += 20;
		}
		return this.pseudoCode;
	}

	public ListenableVector<Shape> getPseudoCode() {
		return this.pseudoCode;
	}

	public int getPseudoCodeMarker() {
		// TODO Auto-generated method stub
		return 0;
	}
}
