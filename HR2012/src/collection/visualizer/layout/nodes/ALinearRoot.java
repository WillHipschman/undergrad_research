package collection.visualizer.layout.nodes;

import bus.uigen.shapes.ALineModel;
import bus.uigen.shapes.Shape;

public class ALinearRoot extends AnAbstractLinearElement implements
		LinearElement {

	public ALinearRoot(Shape _s, Object _o, ALineModel _l,
			ALineModel _l2) {

		super(_s, _o, _l, _l2);
	}
	public void focusPosition(){
		focusPosition(this);
	}
	@Override
	public void focusPosition(LinearElement node) {

		super.focusPosition(node);
		if (node.getVector().size() > 0) {

			super.positionVerticalLine(this);
		}
	}
}
