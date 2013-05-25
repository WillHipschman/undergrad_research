package collection.visualizer.common;

import java.awt.Color;
import java.util.List;

import bus.uigen.shapes.Shape;
import bus.uigen.shapes.SimpleShape;

public abstract class AnimationUtil {

	public static int animationStep;
	public static int animationPauseTime;

	private static synchronized void moveHorizontally(Shape model, int x,
			Color highlighting, Color finalColor) {
		boolean test = x > model.getX();
		int move = x - model.getX();
		model.setColor(highlighting);
		try {
			int count = 0;

			while (count != move) {
				if (test) {
					double newX = model.getX() + animationStep;
					double y = model.getY();
					model.setX((int) newX);
					model.setY((int) y);
					count = count + animationStep;
				} else {
					double newX = model.getX() - animationStep;
					double y = model.getY();
					model.setX((int) newX);
					model.setY((int) y);
					count = count - animationStep;
				}
				if (Math.abs(count - move) < 5) {
					model.setColor(finalColor);
				}
				Thread.sleep(animationPauseTime);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static synchronized void moveVertically(Shape model, int y,
			Color highlighting, Color finalColor) {
		boolean test = y > model.getY();
		int move = y - model.getY();
		model.setColor(highlighting);
		try {
			int count = 0;
			while (count != move) {
				Thread.sleep(animationPauseTime);
				if (test) {
					double x = model.getX();
					double newY = model.getY() + animationStep;
					model.setX((int) x);
					model.setY((int) newY);
					count = count + animationStep;
				} else {
					double x = model.getX();
					double newY = model.getY() - animationStep;
					model.setX((int) x);
					model.setY((int) newY);
					count = count - animationStep;
				}
			}
			model.setColor(finalColor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static synchronized void moveAllVertically(List<Shape> shapes,
			int y, Color highlighting, Color finalColor) {
		boolean positive = y > 0;
		try {
			int current_y_position = 0;
			while (current_y_position != Math.abs(y)) {
				for (Shape shape : shapes) {
					if (positive) {
						moveVertically(shape, y, highlighting, finalColor);
					} else {
						moveVertically(shape, -y, highlighting, finalColor);
					}
				}
				current_y_position += animationStep;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static synchronized void moveAllHorizontally(List<Shape> shapes,
			int x, Color highlighting, Color finalColor) {
		boolean positive = x > 0;
		try {
			int current_x_position = 0;
			while (current_x_position != x) {
				for (Shape shape : shapes) {
					if (positive) {
						moveHorizontally(shape, x, highlighting, finalColor);
					} else {
						moveHorizontally(shape, -x, highlighting, finalColor);
					}
				}
				current_x_position = current_x_position + animationStep;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// This method moves an entire list of shapes to
	// (shapes.get(i).getX()+x,shapes.get(i).getY()+y)
	public static synchronized void move(List<Shape> shapes, int x, int y,
			boolean animate, Color highlighting, Color finalColor) {
		if (animate) {
			moveAllHorizontally(shapes, x, highlighting, finalColor);
			moveAllVertically(shapes, y, highlighting, finalColor);
		} else {
			for (SimpleShape s : shapes) {
				s.setX(s.getX() + x);
				s.setY(s.getY() + y);
			}
		}
	}

	// This method moves one shape to the position (x,y)
	public static synchronized void move(Shape shape, int x, int y,
			boolean animate, Color highlighting, Color finalColor) {
		if (animate) {
			moveHorizontally(shape, x, highlighting, finalColor);
			moveVertically(shape, y, highlighting, finalColor);
		} else {
			shape.setX(x);
			shape.setY(y);
		}
	}
}
