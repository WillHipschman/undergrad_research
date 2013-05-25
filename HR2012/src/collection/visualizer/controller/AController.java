package collection.visualizer.controller;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import bus.uigen.OEFrame;

import collection.visualizer.CollectionVisualizer;
import collection.visualizer.common.*;
import collection.visualizer.commands.vector.AAddCommand;
import collection.visualizer.commands.vector.AChangeCommand;
import collection.visualizer.commands.vector.AnInsertCommand;
import collection.visualizer.commands.vector.ARemoveCommand;
import collection.visualizer.commands.vector.ASwapCommand;
import collection.visualizer.datatype.observer.ObservableEventGenerator;
import collection.visualizer.trappers.EventTrapper;

@SuppressWarnings("rawtypes")
public class AController<ElementType> extends Observable implements
		Controller<ElementType>, ObservableEventGenerator {

	private CollectionVisualizer visualizer;

	private int pauseTime = 1;
	private int position = 0;
	private boolean continuous;
	private boolean moveForward;
	private ListenableVector<String> commands = new AListenableVector<String>();
	private ButtonPressTrapper buttonPressTrapper;
	private int commandIndex = -1;
	private OEFrame frame;

	// TODO This class should delegate to a stub of vector methods listener
	// instead of
	// implementing al of it
	/***************** Constructor ***********************/
	public void init(CollectionVisualizer cv){
		visualizer = cv;
		buttonPressTrapper = visualizer.initializeButtonPressTrapper();
	}

	/******** PROPERTIES *****************************/
	public void setMoveForward(boolean newMoveForward) {
		moveForward = newMoveForward;
	}

	public boolean getMoveForward() {
		return moveForward;
	}

	public void setAnimationPauseTime(int newVal) {
		pauseTime = newVal;
		visualizer.changeAnimationPauseTime(pauseTime);
	}

	public void setListenableVector(int newVal) {
		position = newVal;
	}

	public int getAnimationPauseTime() {
		return pauseTime;
	}

	public int getListenableVector() {
		return position;
	}

	public void setSynchronous(boolean b) {
		continuous = b;
	}

	public boolean getSynchronous() {
		return continuous;
	}

	public ListenableVector<String> getCommands() {

		return commands;
	}

	public void setFrame(OEFrame frame) {
		this.frame = frame;
	}

	/****************** Animation Controls ***************************/

	public synchronized void first() {
		buttonPressTrapper.maybeDiscardFirstButtonPress();
	}

	public synchronized void previous() {
		buttonPressTrapper.maybeDiscardPreviousButtonPress();
		this.setChanged();
		this.notifyObservers("previous");
	}

	public synchronized void next() {
		buttonPressTrapper.maybeDiscardNextButtonPress();
		this.setChanged();
		this.notifyObservers("next");
	}

	public synchronized void last() {
		buttonPressTrapper.maybeDiscardLastButtonPress();
	}

	/************************** Observer Methods **********************************/

	public void commandExecuted() {
		if (getMoveForward()) {
			commandIndex++;
		} else {
			commandIndex--;
		}
		if (null != frame && commandIndex >= 0) {
			frame.select(commands, commandIndex);
		}
	}

	public void elementAdded(Object source, Object element, int newSize) {

		if (element instanceof AAddCommand)
			commands.add("Add "
					+ ((AAddCommand) element).getObject().toString());
		else if (element instanceof AnInsertCommand)
			commands.add("Insert "
					+ ((AnInsertCommand) element).getObject().toString()
					+ " at position "
					+ ((AnInsertCommand) element).getPosition());
		else if (element instanceof ARemoveCommand)
			commands.add("Remove from "
					+ ((ARemoveCommand) element).getPosition());
		else if (element instanceof ASwapCommand)
			commands.add("Swap indices "
					+ ((ASwapCommand) element).getIndexOne() + " and "
					+ ((ASwapCommand) element).getIndexTwo());
		else if (element instanceof AChangeCommand)
			commands.add("Change position "
					+ ((AChangeCommand) element).getPosition()
					+ " to have value "
					+ ((AChangeCommand) element).getObject());
	}

	public void elementChanged(Object source, Object element, int pos) {

	}

	public void elementCopied(Object source, int fromIndex, int toIndex,
			int newSize) {

	}

	public void elementCopied(Object source, int fromIndex, int fromNewSize,
			Object to, int toIndex) {

	}

	public void elementInserted(Object source, Object element, int pos,
			int newSize) {

	}

	public void elementMoved(Object source, int fromIndex, int toIndex) {

	}

	public void elementMoved(Object source, int fromIndex, int fromNewSize,
			Object to, int toIndex) {

	}

	public void elementRemoved(Object source, int pos, int newSize) {

		commands.remove(pos);
	}

	public void elementRemoved(Object source, Object element, int newSize) {

	}

	public void elementReplaced(Object source, int fromIndex, int toIndex,
			int newSize) {

	}

	public void elementReplaced(Object source, int fromIndex, int newFromSize,
			Object to, int toIndex) {

	}

	public void elementSwapped(Object newParam, int index1, int index2) {

	}

	public void elementSwapped(Object source, int index1, Object other,
			int index2) {

	}

	public void elementsAdded(Object source,
			Collection<? extends ElementType> element, int newSize) {
		// TODO Auto-generated method stub

	}

	public void elementsCleared(Object source) {
		// TODO Auto-generated method stub

	}

	public void addListener(
			EventTrapper<Observer, ObservableEventGenerator> observer)
			throws Exception {
		if (!(observer instanceof Observer)) {
			throw new Exception("Ill Defined Trapper: The trapper "
					+ observer.toString()
					+ "must be an instance of an observer and an observable");
		}
		super.addObserver((Observer) observer);

	}

	public void removeListener(
			EventTrapper<Observer, ObservableEventGenerator> observer) {
		if (observer instanceof Observer) {
			super.deleteObserver((Observer) observer);
		}

	}

	public CollectionVisualizer getVisualizer() {
		return visualizer;
	}

}
