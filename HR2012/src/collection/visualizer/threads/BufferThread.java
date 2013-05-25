package collection.visualizer.threads;

import collection.visualizer.CollectionVisualizer;
import collection.visualizer.controller.Control;

@SuppressWarnings("rawtypes")
public class BufferThread implements Runnable {

	private CommandHistory commandHistory;
	private CollectionVisualizer visualizer;
	private Control controller;

	public BufferThread(CommandHistory u, Control c, CollectionVisualizer v) {
		controller = c;
		commandHistory = u;
		visualizer = v;
	}

	public void run() {
		while (true) {
			commandHistory.setSafeToGetCommand(true);
			visualizer.waitForNextBufferThreadStep();
			if (visualizer.getCanProceed()) {
				if (controller.getSynchronous()) {
					runAllCommands();
					controller.setSynchronous(false);
				} else {
					runCommand();
				}
				visualizer.setCanProceed(false);
			}
		}
	}

	private void runAllCommands() {
		while (visualizer.getCanProceed() && commandHistory.getSafeToGetCommand()) {
			runCommand();
		}
	}

	private void runCommand() {
		boolean commandSuccessfullyExecuted;
		if (controller.getMoveForward()) {
			commandSuccessfullyExecuted = commandHistory.execute();
		} else {
			commandSuccessfullyExecuted = commandHistory.undo();
		}
		if (commandSuccessfullyExecuted) {
			controller.commandExecuted();
		}
	}
}
