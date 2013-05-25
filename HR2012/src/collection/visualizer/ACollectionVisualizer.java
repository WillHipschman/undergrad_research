package collection.visualizer;

import java.util.HashMap;

import collection.visualizer.common.*;
import collection.visualizer.controller.AController;
import collection.visualizer.controller.ButtonPressTrapper;
import collection.visualizer.controller.Control;
import collection.visualizer.controller.Controller;
import collection.visualizer.layout.DefaultLayoutManager;
import collection.visualizer.layout.LayoutManager;
import collection.visualizer.threads.CommandHistory;

import collection.visualizer.trappers.*;

import bus.uigen.shapes.Shape;

@SuppressWarnings("unchecked")
public abstract class ACollectionVisualizer<Observer, ObservableDataType extends EventGenerator<Observer, ObservableDataType>>
		extends AListenableVector<Shape> implements
		CollectionVisualizer<Observer, ObservableDataType> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1701393485380127014L;

	protected int internalIndex, collectionIndex;
	protected int animationPauseTime, animationStep;
	protected boolean canProceed;
	protected ObservableDataType input;

	protected HashMap<Integer, ObservableDataType> bufferData = new HashMap<Integer, ObservableDataType>();
	protected HashMap<Integer, ObservableDataType> originalData = new HashMap<Integer, ObservableDataType>();

	protected Control controller = new AController<ObservableDataType>();
	protected CommandHistory ui;

	private TrapperChainSupporter<Observer, ObservableDataType> trapperChain;
	protected LayoutManager<ObservableDataType> layoutManager = new DefaultLayoutManager<Observer, ObservableDataType>(
			this);
	protected ObservableDataType buffer;
	
	
	//private Observable proxyObservable = new Observable();

	/********************* Initialization ********************************/
	protected ACollectionVisualizer() {

		// should initialize controller and layoutManager here
		animationPauseTime = 0;
		animationStep = 1;
		internalIndex = 0;
		collectionIndex = 0;
		AnimationUtil.animationPauseTime = animationPauseTime;
		AnimationUtil.animationStep = animationStep;
		buffer = initializeBuffer();
	}

	/**
	 * 
	 * @return: This method must be called by the subclass to initialize the
	 *          buffer. The buffer must be an instance of Buffer, an instance of
	 *          ObservableDataType, and an instance of Observer. The required
	 *          methods for the Buffer interface are in the class ABuffer, which
	 *          should be delegated.
	 * 
	 *          The buffer must implement add and remove listener methods as
	 *          well as an update method. If these are not implemented correctly
	 *          then events will not ultimately reach ACollectionVisualizer.
	 */
	protected abstract ObservableDataType initializeBuffer();

	/**
	 * 
	 * @return This method must be called by the subclass to initialize the
	 *         button trapper. The trapper must be an instance of
	 *         ButtonPressTrapper.
	 */
	public abstract ButtonPressTrapper initializeButtonPressTrapper();

	@SuppressWarnings("rawtypes")
	protected void setBufferData(ObservableDataType data) {
		((Buffer) buffer).setBufferData(data);
	}

	/******************
	 * Visualization
	 * 
	 * @throws Exception
	 **************************/

	@SuppressWarnings("rawtypes")
	public synchronized void visualize(ObservableDataType data)
			throws Exception {

		controller.init(this);
		if (!(buffer instanceof EventTrapper)) {
			throw new Exception(
					"Buffer Initialization Error: Buffer must be an instance of EventTrapper");
		}
		this.setBufferData(data);

		originalData.put(collectionIndex, data);

		if (null == trapperChain) {
			trapperChain = new ATrapperChainSupporter<Observer, ObservableDataType>(
					(EventGenerator<Observer, ObservableDataType>) buffer);
		}

		((Buffer) buffer).getCommandHistory().getCommands()
				.addVectorMethodsListener((Controller) getController());
		ui = ((Buffer) buffer).getCommandHistory();

		visualizeBuffer(buffer);
		((Buffer) buffer).putBufferThread(this, controller);
	}

	private synchronized void visualizeBuffer(ObservableDataType vector) {
		bufferData.put(collectionIndex, vector);

		this.addAll(layoutManager.display(vector));
		collectionIndex++;
	}

	/********************* Thread Management ********************************/
	public void changeAnimationPauseTime(int newTime) {
		animationPauseTime = newTime;
	}

	public synchronized void waitForNextBufferThreadStep() {
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void notifyVisualizer() {
		this.notify();
	}

	public synchronized void setCanProceed(boolean b) {
		canProceed = b;
	}

	@util.annotations.Visible(false)
	public synchronized boolean getCanProceed() {
		return canProceed;
	}

	@util.annotations.Visible(false)
	public int getAnimationPauseTime() {
		return animationPauseTime;
	}

	@util.annotations.Visible(false)
	public void setAnimationPauseTime(int newVal) {
		animationPauseTime = newVal;
	}

	/************************** Access Methods ***************************************/
	@util.annotations.Visible(false)
	public HashMap<Integer, ObservableDataType> getOriginalData() {
		return originalData;
	}

	@util.annotations.Visible(false)
	public ObservableDataType getBuffer() {
		return buffer;
	}

	@util.annotations.Visible(false)
	public Control getController() {
		return controller;
	}
	
	@util.annotations.Visible(false)
	public ListenableVector<Shape> getShapes() {
		return this;
	}

	@util.annotations.Visible(false)
	public CommandHistory getUndoer() {

		return ui;
	}

	public void addTrapper(EventTrapper<Observer, ObservableDataType> trapper)
			throws Exception {
		if (null != trapperChain) {
			trapperChain.addTrapper(trapper);
		} else {
			throw new Exception(
					"Data must be visualized before trappers can be added");
		}
	}

	public void removeTrapper(EventTrapper<Observer, ObservableDataType> trapper)
			throws Exception {
		if (null != trapperChain) {
			trapperChain.removeTrapper(trapper);
		}

	}

	public LayoutManager<ObservableDataType> getLayoutManager() {
		return layoutManager;
	}
}
