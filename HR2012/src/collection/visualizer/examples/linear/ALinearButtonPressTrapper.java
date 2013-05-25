package collection.visualizer.examples.linear;

import java.util.Comparator;

import collection.visualizer.common.*;
import collection.visualizer.common.comparators.AComparator;
import collection.visualizer.ACollectionVisualizer;
import collection.visualizer.controller.AButtonPressTrapper;
import collection.visualizer.controller.ButtonPressTrapper;
import collection.visualizer.controller.Control;
import collection.visualizer.threads.CommandHistory;

@SuppressWarnings("rawtypes")
public class ALinearButtonPressTrapper extends AButtonPressTrapper implements
		ButtonPressTrapper {

	
	private ALinearLayoutManager layoutManager;
	private CommandHistory historyList;
	private int lineNumber = 0, forCounter = 0, executeCounter = -1;
	private ListenableVector buffer;
	private Comparator comparator;

	public ALinearButtonPressTrapper(Control controller,
			ALinearVisualizer visualizer) {
		super(controller, visualizer);
		layoutManager = (ALinearLayoutManager) visualizer.getLayoutManager();
		historyList = visualizer.getUndoer();
		buffer = (ListenableVector) visualizer.getBuffer();
		comparator = new AComparator();
	}

	public synchronized void maybeDiscardFirstButtonPress() {
	}

	public synchronized void maybeDiscardPreviousButtonPress() {
	}

	@SuppressWarnings("unchecked")
	public synchronized void maybeDiscardNextButtonPress() {

		initializeComponents();
		if (executeCounter == historyList.getSize() - 1) {
			layoutManager.clearPseudoCodePointers();
			return;
		}
		lineNumber = layoutManager.getPseudoCodeMarker();
		switch (lineNumber) {
		case 2:
			// WHILE
			if (executeCounter == historyList.getSize()) {
				layoutManager.clearPseudoCodePointers();
			} else {

				layoutManager.setPseudoCodePointer(layoutManager
						.getPseudoCodeMarker() + 1);
			}
			break;
		case 3:
			// FOR
			layoutManager.setPseudoCodePointer(layoutManager
					.getPseudoCodeMarker() + 1);
			break;
		case 4:
			// IF
			if (forCounter == buffer.size() - 1) {
				layoutManager.setPseudoCodePointer(2);
				forCounter = 0;
			} else {
				if (comparator.compare(buffer.get(forCounter),
						buffer.get(forCounter + 1)) > 0) {
					layoutManager.setPseudoCodePointer(layoutManager
							.getPseudoCodeMarker() + 1);
					forCounter++;
				} else {
					forCounter++;
					layoutManager.setPseudoCodePointer(4);
				}
			}
			break;
		case 5:
			// SWAP
			layoutManager.setPseudoCodePointer(layoutManager
					.getPseudoCodeMarker() + 1);

			super.maybeDiscardNextButtonPress();
			executeCounter++;
			break;
		case 7:
			// LAST LINE
			layoutManager.setPseudoCodePointer(4);
			break;
		default:
			layoutManager.setPseudoCodePointer(layoutManager
					.getPseudoCodeMarker() + 1);

		}
	}

	public synchronized void maybeDiscardLastButtonPress() {
		initializeComponents();
		while (executeCounter < historyList.getSize() - 1) {
			this.maybeDiscardNextButtonPress();
			try {
				this.wait(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		layoutManager.clearPseudoCodePointers();
		
	}

	private void initializeComponents() {
		if (null == layoutManager) {
			layoutManager = (ALinearLayoutManager) visualizer
					.getLayoutManager();
		}
		if (null == buffer) {
			buffer = (ListenableVector) ((ACollectionVisualizer) visualizer)
					.getBuffer();
		}
		if (null == historyList) {
			historyList = visualizer.getUndoer();
		}
	}
}
