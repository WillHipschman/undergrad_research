package collection.visualizer.controller;

public interface ButtonPressTrapper {
	void maybeDiscardNextButtonPress();

	void maybeDiscardLastButtonPress();

	void maybeDiscardPreviousButtonPress();

	void maybeDiscardFirstButtonPress();
}
