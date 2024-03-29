package collection.visualizer.common;

import bus.uigen.shapes.Shape;

public class Util {
	@SuppressWarnings("rawtypes")
	public static Class convertWrapperClassToPrimitiveClass(Class c) {

		if (c.equals(Integer.class)) {
			return int.class;
		}
		if (c.equals(Byte.class)) {
			return byte.class;
		}
		if (c.equals(Short.class)) {
			return short.class;
		}
		if (c.equals(Long.class)) {
			return long.class;
		}
		if (c.equals(Float.class)) {
			return float.class;
		}
		if (c.equals(Double.class)) {
			return double.class;
		}
		if (c.equals(Boolean.class)) {
			return boolean.class;
		}
		if (c.equals(Character.class)) {
			return char.class;
		}
		return c;
	}
	public static ListenableVector<Shape> copyVector(ListenableVector<Shape> original) {

		ListenableVector<Shape> retVal = new AListenableVector<Shape>();
		for (Shape s : original) {
			try {
				Shape newShape = s.getClass().newInstance();

				newShape.setX(s.getX());
				newShape.setY(s.getY());
				newShape.setWidth(s.getWidth());
				newShape.setHeight(s.getHeight());
				newShape.setFilled(s.isFilled());
				
				retVal.add(newShape);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retVal;
	}

	public static int findLeadingSpaces(String s) {
		if(s.isEmpty()){
			return 0;
		}
		int numSpaces = 0;
		int index = 0;
		int currentChar = s.charAt(index);
		while (currentChar == ' ' || currentChar == '\t') {
			if (currentChar == ' ') {
				numSpaces += 4;
			}
			if (currentChar == '\t') {
				numSpaces += 16;
			}

			index++;
			currentChar = s.charAt(index);
		}
		return numSpaces;
	}
}
