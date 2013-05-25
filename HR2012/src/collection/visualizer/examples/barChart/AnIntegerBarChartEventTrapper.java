package collection.visualizer.examples.barChart;

import java.awt.Color;
import java.util.Collection;

import bus.uigen.shapes.Shape;

import collection.visualizer.common.*;

import collection.visualizer.trappers.*;

import util.models.VectorMethodsListener;

public class AnIntegerBarChartEventTrapper extends AListenableVector<Integer>
		implements
		EventTrapper<VectorMethodsListener<Integer>, ListenableVector<Integer>>,
		VectorMethodsListener<Integer> {
	
	protected AnIntegerBarChartVisualizer visualizer;
	protected AnIntegerBarChartLayoutManager layoutManager;

	public AnIntegerBarChartEventTrapper(
			AnIntegerBarChartVisualizer visualizer,
			AnIntegerBarChartLayoutManager layoutManager) {
		this.visualizer = visualizer;
		this.layoutManager = layoutManager;
	}
	
	
	private static final long serialVersionUID = 3971272244005861176L;


	public void elementAdded(Object source, Integer element, int newSize) {

		visualizer.add(layoutManager.nextShape(element));
	}

	public void elementChanged(Object source, Integer element, int pos) {

		Shape shape = visualizer.get(pos);

		AnimationUtil.move(shape, shape.getX(), shape.getY() - 100, true,
				Color.BLACK, Color.BLACK);

		shape.setHeight(element * layoutManager.getScaleFactor());

		AnimationUtil.move(shape, shape.getX(), layoutManager.getBaseLine()
				- shape.getHeight(), true, Color.BLACK, Color.BLACK);

	}

	public void elementCopied(Object source, int fromIndex, int toIndex,
			int newSize) {
		// TODO Auto-generated method stub

	}

	public void elementCopied(Object source, int fromIndex, int fromNewSize,
			Object to, int toIndex) {
		// TODO Auto-generated method stub

	}

	public void elementInserted(Object source, Integer element, int pos,
			int newSize) {
		// TODO Auto-generated method stub

	}

	public void elementMoved(Object source, int fromIndex, int toIndex) {
		// TODO Auto-generated method stub

	}

	public void elementMoved(Object source, int fromIndex, int fromNewSize,
			Object to, int toIndex) {
		// TODO Auto-generated method stub

	}

	public void elementRemoved(Object source, int pos, int newSize) {
		// TODO Auto-generated method stub

	}

	public void elementRemoved(Object source, Integer element, int newSize) {
		// TODO Auto-generated method stub

	}

	public void elementReplaced(Object source, int fromIndex, int toIndex,
			int newSize) {
		// TODO Auto-generated method stub

	}

	public void elementReplaced(Object source, int fromIndex, int newFromSize,
			Object to, int toIndex) {
		// TODO Auto-generated method stub

	}

	public synchronized void elementSwapped(Object newParam, int index1,
			int index2) {

		if (index1 <= index2) {
			Shape s1 = visualizer.get(index1);
			Shape s2 = visualizer.get(index2);

			int x1 = s1.getX();
			int x2 = s2.getX();

			AnimationUtil.move(s1, x2, s1.getY(), true, Color.BLACK,
					Color.BLACK);
			AnimationUtil.move(s2, x1, s2.getY(), true, Color.BLACK,
					Color.BLACK);
			this.swap(index1, index2);
			visualizer.swap(index1, index2);
		} else {
			elementSwapped(newParam, index2, index1);
		}

	}

	public void elementSwapped(Object source, int index1, Object other,
			int index2) {
		// TODO Auto-generated method stub

	}

	public void elementsAdded(Object source,
			Collection<? extends Integer> element, int newSize) {
		// TODO Auto-generated method stub

	}

	public void elementsCleared(Object source) {
		// TODO Auto-generated method stub

	}

}
