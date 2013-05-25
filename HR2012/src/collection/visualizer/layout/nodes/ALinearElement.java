package collection.visualizer.layout.nodes;

import bus.uigen.shapes.ALineModel;
import bus.uigen.shapes.Shape;

public class ALinearElement extends AnAbstractLinearElement implements  LinearElement {

	public ALinearElement(Shape s, Object o, ALineModel l,
			ALineModel l2) {
		super(s, o, l, l2);
	}

	@Override
	public void focusPosition() {
		focusPosition(this);
		
	}

}