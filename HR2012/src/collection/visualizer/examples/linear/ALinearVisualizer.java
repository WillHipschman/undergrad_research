package collection.visualizer.examples.linear;

import java.util.Collection;

import collection.visualizer.ACollectionVisualizer;
import collection.visualizer.common.*;
import collection.visualizer.controller.ButtonPressTrapper;
import collection.visualizer.datatype.vector.ALinearBuffer;
import collection.visualizer.layout.nodes.ALinearElement;
import collection.visualizer.layout.nodes.ALinearRoot;
import collection.visualizer.layout.nodes.AVisualizerElementSkeleton;
import collection.visualizer.layout.nodes.LinearElement;


import util.models.VectorMethodsListener;
import bus.uigen.shapes.ALabelModel;
import bus.uigen.shapes.ALineModel;
import bus.uigen.shapes.LabelShape;
import bus.uigen.shapes.Shape;
import bus.uigen.shapes.SimpleShape;
import bus.uigen.shapes.TextShape;

@SuppressWarnings("rawtypes")
public class ALinearVisualizer<ElementType>
		extends
		ACollectionVisualizer<VectorMethodsListener<ElementType>, ListenableVector<ElementType>>
		implements LinearVisualizer<ElementType> {

	private static final long serialVersionUID = 2125646954227767702L;
	private ListenableVector<ALineModel> LINE_VEC = new AListenableVector<ALineModel>();
	private ListenableVector<Shape> shapes = new AListenableVector<Shape>();
	private LinearElement root;
	private boolean showLines = true;
	private boolean showRoot = true;
	private ALabelModel label;

	private int initX = 20, initY = 20;

	/**************** Initialization *******************************/
	public ALinearVisualizer(Shape shape) {
		super();
		this.addAll(LINE_VEC);
		animationPauseTime = 10;

		layoutManager = new ALinearLayoutManager<ElementType>(this);
		((ALinearLayoutManager<ElementType>) layoutManager).setShape(shape);
	}
	
	public void temp(){
		System.out.println("SUCCESSFUL METHOD BINDING");
	}

	/********************** Graphics Methods *******************************************/

	private void initElements(LinearElement head,
			ListenableVector<ElementType> vector) {
		for (int i = 0; i < vector.size(); i++) {

			Object element = vector.get(i);
			LinearElement node;
			if (i > 0) { // If this is not the first node in the list
				LinearElement previousChild = head.getVector().get(i - 1);
				node = initElement(element, head, previousChild);
			} else {
				node = initElement(element, head, null);
			}
			((AVisualizerElementSkeleton) node).setAnimate(true);
			node.setColor(this.getLayoutManager().getColor());
			((AVisualizerElementSkeleton) node).setHighlighting(this.getLayoutManager().getHighlighting());
			head.getVector().add(node);
		}
	}

	public ALinearRoot initRoot(Object element, LinearElement parent,
			LinearElement previousChild) {

		String stringValue = element.toString();
		Shape shape = ((ALinearLayoutManager<ElementType>) layoutManager)
				.createShape(stringValue, initX, initY);
		ALineModel horizontalLine = new ALineModel(0, 0, 0, 0);
		ALineModel verticalLine = new ALineModel(0, 0, 0, 0);

		ALinearRoot visualizerElement = new ALinearRoot(shape, stringValue,
				verticalLine, horizontalLine);

		postInitElement(element, parent, previousChild, visualizerElement);
		visualizerElement.setAnimate(true);
		visualizerElement.setColor(this.getLayoutManager().getColor());
		visualizerElement.setHighlighting(this.getLayoutManager().getHighlighting());
		return visualizerElement;
	}

	@SuppressWarnings("unchecked")
	private void postInitElement(Object element, LinearElement parent,
			LinearElement previousChild, LinearElement visualizerElement) {

		if (!((ALinearLayoutManager<ElementType>) layoutManager).getVertical()) {// horizontal
			visualizerElement.setVertical(false);
			visualizerElement
					.setElementShapeXOffset(((ALinearLayoutManager<ElementType>) layoutManager)
							.getBoxHeight());
			visualizerElement
					.setElementShapeYOffset(((ALinearLayoutManager<ElementType>) layoutManager)
							.getBoxHeight() / 2);
			visualizerElement
					.setHorizontalLineYOffset(((ALinearLayoutManager<ElementType>) layoutManager)
							.getBoxHeight() / 2);
			visualizerElement
					.setVerticalLineXOffset(((ALinearLayoutManager<ElementType>) layoutManager)
							.getBoxWidth() / 2);
		} else {
			// vertical
			visualizerElement.setVertical(true);
			visualizerElement
					.setElementShapeXOffset(((ALinearLayoutManager<ElementType>) layoutManager)
							.getBoxWidth());
			visualizerElement
					.setElementShapeYOffset(((ALinearLayoutManager<ElementType>) layoutManager)
							.getBoxHeight() / 2);
			visualizerElement
					.setHorizontalLineYOffset(((ALinearLayoutManager<ElementType>) layoutManager)
							.getBoxHeight() / 2);
			visualizerElement
					.setVerticalLineXOffset(((ALinearLayoutManager<ElementType>) layoutManager)
							.getBoxWidth() / 2);
		}

		visualizerElement.setParent(parent);
		visualizerElement.setPreviousChild(previousChild);

		if (element instanceof ListenableVector) {
			initElements(visualizerElement, (ListenableVector) element);
		}

		if (visualizerElement instanceof ALinearRoot && showRoot) {

			shapes.add(visualizerElement.getShape());
			this.addLine(visualizerElement.getHorizontalLine());
			this.addLine(visualizerElement.getVerticalLine());
		} else if (!(visualizerElement instanceof ALinearRoot)) {

			shapes.add(visualizerElement.getShape());
			this.addLine(visualizerElement.getHorizontalLine());
			this.addLine(visualizerElement.getVerticalLine());
		}
	}

	public LinearElement initElement(Object element, LinearElement parent,
			LinearElement previousChild) {

		String stringValue = element.toString();
		Shape shape = ((ALinearLayoutManager<ElementType>) layoutManager)
				.createShape(stringValue, initX, initY);
		ALineModel horizontalLine = new ALineModel(0, 0, 0, 0);
		ALineModel verticalLine = new ALineModel(0, 0, 0, 0);

		LinearElement visualizerElement = new ALinearElement(shape,
				stringValue, verticalLine, horizontalLine);

		postInitElement(element, parent, previousChild, visualizerElement);

		return visualizerElement;
	}

	public void magnify(double mag) {
		// TODO Auto-generated method stub

	}

	public int theStepCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void elementAdded(Object source, Object element, int newSize) {

		elementInserted(source, element, newSize - 1, newSize);
	}

	public void elementChanged(Object source, Object element, int pos) {

		LinearElement node = root.getVector().get(pos);
		AnimationUtil.move(
				node,
				node.getX()
						+ ((ALinearLayoutManager<ElementType>) layoutManager)
								.getBoxWidth() / 3, node.getY(), true,
				((ALinearLayoutManager<ElementType>) layoutManager)
						.getHighlighting(),
				((ALinearLayoutManager<ElementType>) layoutManager).getColor());
		node.setObject(element);

		SimpleShape shape = node.getShape();

		try {
			double shapeStretchFactor = Double.parseDouble(element.toString());
			shape.setWidth((int) (((ALinearLayoutManager<ElementType>) layoutManager)
					.getBoxWidth() * (((ALinearLayoutManager<ElementType>) layoutManager)
					.getDynamicWidth() ? shapeStretchFactor : 1)));
			shape.setHeight((int) (((ALinearLayoutManager<ElementType>) layoutManager)
					.getBoxHeight() * (((ALinearLayoutManager<ElementType>) layoutManager)
					.getDynamicHeight() ? shapeStretchFactor : 1)));
		} catch (Exception e) {
			shape.setWidth(((ALinearLayoutManager<ElementType>) layoutManager)
					.getBoxWidth());
			shape.setHeight(((ALinearLayoutManager<ElementType>) layoutManager)
					.getBoxHeight());

		}

		if (shape instanceof TextShape)
			((TextShape) shape).setText(element.toString());
		if (shape instanceof LabelShape)
			((LabelShape) shape).setText(element.toString());

		AnimationUtil.move(
				node,
				node.getX()
						- ((ALinearLayoutManager<ElementType>) layoutManager)
								.getBoxWidth() / 3, node.getY(), true,
				((ALinearLayoutManager<ElementType>) layoutManager)
						.getHighlighting(),
				((ALinearLayoutManager<ElementType>) layoutManager).getColor());
	}

	public void elementCopied(Object source, int fromIndex, int toIndex,
			int newSize) {
		// TODO Auto-generated method stub

	}

	public void elementCopied(Object source, int fromIndex, int fromNewSize,
			Object to, int toIndex) {
		// TODO Auto-generated method stub

	}

	public void elementInserted(Object source, Object element, int pos,
			int newSize) {

		LinearElement parent = root;
		LinearElement previousChild = pos - 1 >= 0 ? root.getVector().get(
				pos - 1) : null;

		LinearElement newElement = initElement(element, parent, previousChild);

		if (pos + 1 < newSize) // if there is a child after us
			// use pos not pos + 1 because the element has not been
			// added to the vector yet, so pos + 1 in the user's
			// vector is pos in the roots vector.
			root.getVector().get(pos).setPreviousChild(newElement);

		parent.getVector().insertElementAt(newElement, pos);
		root.focusPosition();
	}

	public void elementMoved(Object source, int fromIndex, int toIndex) {
		// TODO Auto-generated method stub

	}

	public void elementMoved(Object source, int fromIndex, int fromNewSize,
			Object to, int toIndex) {
		// TODO Auto-generated method stub

	}

	public void elementRemoved(Object source, int pos, int newSize) {

		LinearElement toBeRemoved = root.getVector().get(pos);
		shapes.remove(toBeRemoved.getShape());
		this.removeLine(toBeRemoved.getVerticalLine());
		this.removeLine(toBeRemoved.getHorizontalLine());

		LinearElement parent = root;
		LinearElement previousChild = toBeRemoved.getPreviousChild();

		if (pos + 1 <= newSize)// If it is not the last element
			root.getVector().get(pos + 1).setPreviousChild(previousChild);

		parent.getVector().remove(toBeRemoved);

		root.focusPosition();
	}

	public void elementRemoved(Object source, Object element, int newSize) {
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

	public void elementsAdded(Object source, Collection element, int newSize) {
		// TODO Auto-generated method stub

	}

	public void elementsCleared(Object source) {
		// TODO Auto-generated method stub

	}

	public void collectionRemoved(int collectionNum) {
		// TODO Auto-generated method stub

	}

	public void elementSwapped(Object source, int index1, Object other,
			int index2) {
		// TODO Auto-generated method stub

	}

	/*
	 * This method takes two elements and exchanges their parents and previous
	 * elements. It then makes sure spacing is correct by calling
	 * focusPositionAndLines
	 */
	public void elementSwapped(Object newParam, int index1, int index2) {
		if (index1 < index2)
			swapElements(newParam, index1, index2);
		else
			swapElements(newParam, index2, index1);

	}

	/*
	 * This method takes two elements and exchanges their parents and previous
	 * elements. It then makes sure spacing is correct by calling
	 * focusPositionAndLines
	 * 
	 * This method assumes index1 < index2
	 */
	private void swapElements(Object newParam, int index1, int index2) {

		// the two elements to be swapped
		LinearElement firstElement = root.getVector().get(index1);
		LinearElement secondElement = root.getVector().get(index2);

		// the two elements after each of the elements to be swapped
		LinearElement secondElementNext;
		LinearElement firstElementNext;
		/*
		 * There is a corner case where secondElement.previousChild is
		 * firstElement
		 */

		// the first element's previous child before it is reassigned
		LinearElement tempPrevChild = firstElement.getPreviousChild();

		if (index2 != index1 + 1) {// the elements are not adjacent

			// set previous elements for the two nodes
			firstElement.setPreviousChild(secondElement.getPreviousChild());
			secondElement.setPreviousChild(tempPrevChild);

			// set previous elements for the nodes referencing the swapped nodes
			if (index1 + 1 < root.getVector().size()) {
				firstElementNext = root.getVector().get(index1 + 1);
				firstElementNext.setPreviousChild(secondElement);
			}
			if (index2 + 1 < root.getVector().size()) {
				secondElementNext = root.getVector().get(index2 + 1);
				secondElementNext.setPreviousChild(firstElement);
			}

		} else { // the elements are adjacent
			firstElement.setPreviousChild(secondElement);
			secondElement.setPreviousChild(tempPrevChild);

			// set previous elements for the nodes referencing the second node
			if (index2 + 1 < root.getVector().size()) {
				secondElementNext = root.getVector().get(index2 + 1);
				secondElementNext.setPreviousChild(firstElement);
			}
		}

		// swap the elements in root
		root.getVector().swap(index1, index2);

		root.focusPosition();
	}

	private void addLine(ALineModel l) {
		if (showLines) {
			LINE_VEC.add(l);
		}
	}

	public void removeLine(ALineModel l) {
		if (showLines) {
			LINE_VEC.remove(l);
		}
	}

	public void showLines(boolean b) {
		showLines = b;
	}

	public void showRoot(boolean b) {
		showRoot = b;
	}

	public void setLabel(ALabelModel l) {
		label = l;
	}

	@util.annotations.Visible(false)
	public ALabelModel getLabel() {
		return label;
	}

	public ListenableVector<Shape> shapes() {
		return shapes;
	}

	@util.annotations.Visible(false)
	public LinearElement getRoot() {
		return root;
	}

	public void setRoot(LinearElement newVal) {
		root = newVal;
	}

	@util.annotations.Visible(false)
	public ALinearLayoutManager<ElementType> getLayoutManager() {
		return (ALinearLayoutManager<ElementType>) layoutManager;
	}

	/************************* Visualizer Methods ***********************************/
	protected ListenableVector<ElementType> initializeBuffer() {
		// TODO Auto-generated method stub
		return new ALinearBuffer<ElementType>(this);
	}
	@Override
	public ButtonPressTrapper initializeButtonPressTrapper() {
		return new ALinearButtonPressTrapper(controller, this);
	}
}
