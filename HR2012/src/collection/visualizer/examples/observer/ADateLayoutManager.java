package collection.visualizer.examples.observer;

import javax.swing.JPanel;

import collection.visualizer.common.*;
import collection.visualizer.layout.LayoutManager;


import bus.uigen.shapes.ALineModel;
import bus.uigen.shapes.ATextModel;
import bus.uigen.shapes.AnOvalModel;
import bus.uigen.shapes.Shape;

public class ADateLayoutManager implements LayoutManager<ObservableDate> {

	private int minuteDegrees = 0, hourDegrees = 0;

	private Shape clock, secondHand, minuteHand, hourHand;
	private ATextModel digital;
	protected int x, y, radius;

	public ADateLayoutManager(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;

		clock = new AnOvalModel(x, y, 2 * radius, 2 * radius);
		secondHand = new ALineModel(x + radius, y + radius, 25, -25);
		minuteHand = new ALineModel(x + radius, y + radius,
				this.widthFromRadians(minuteDegrees),
				this.heightFromRadians(minuteDegrees));
		hourHand = new ALineModel(x + radius, y + radius,
				this.widthFromRadians(hourDegrees),
				this.heightFromRadians(hourDegrees));

		digital = new ATextModel("", x - radius * 2, y - radius, radius * 6,
				radius);
	}

	public ListenableVector<Shape> display(ObservableDate date) {

		ListenableVector<Shape> vector = new AListenableVector<Shape>();

		this.update(date);

		vector.add(clock);
		vector.add(secondHand);
		vector.add(minuteHand);
		vector.add(hourHand);
		vector.add(digital);

		return vector;
	}
	public void update(ObservableDate date) {

		int seconds = date.getSeconds();
		int minutes = date.getMinutes();
		int hours = date.getHours();
		String timeString = date.toLocaleString();

		double secondRadians = this.convertSecondOrMinuteToRadians(seconds);
		double minuteRadians = this.convertSecondOrMinuteToRadians(minutes);
		double hourRadians = this.convertHourToRadians(hours);

		secondHand.setWidth(this.widthFromRadians(secondRadians));
		secondHand.setHeight(this.heightFromRadians(secondRadians));
		minuteHand.setWidth(this.widthFromRadians(minuteRadians));
		minuteHand.setHeight(this.heightFromRadians(minuteRadians));
		hourHand.setWidth(3 * this.widthFromRadians(hourRadians) / 4);
		hourHand.setHeight(3 * this.heightFromRadians(hourRadians) / 4);

		digital.setText(timeString);
	}
	

	/**
	 * 
	 * @param time
	 *            : and integer measure of either seconds or minutes
	 * @return: the degrees, measured from the vertical y axis, that correspond
	 *          to that number of minutes or seconds on a round clock.
	 */
	public double convertSecondOrMinuteToRadians(int time) {
		return (6 * time) * (Math.PI / 180);
	}

	public double convertHourToRadians(int hour) {
		return (30 * hour) * (Math.PI / 180);
	}

	public int widthFromRadians(double radians) {
		return (int) (Math.sin(radians) * radius);
	}

	public int heightFromRadians(double radians) {
		return (int) (-Math.cos(radians) * radius);
	}

	public ListenableVector<Shape> constructPseudoCode() {
		// TODO Auto-generated method stub
		return null;
	}

	public ListenableVector<Shape> getPseudoCode() {
		// TODO Auto-generated method stub
		return null;
	}

	public void incrementPseudoCodeMarker() {
		// TODO Auto-generated method stub

	}

	public void decrementPseudoCodeMarker() {
		// TODO Auto-generated method stub

	}

	public int getPseudoCodeMarker() {
		// TODO Auto-generated method stub
		return 0;
	}

	public JPanel displayInPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return null;
	}
}
