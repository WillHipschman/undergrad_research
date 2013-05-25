package collection.visualizer.layout.nodes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import util.models.AListenableVector;
import util.models.ListenableVector;
import bus.uigen.shapes.ALineModel;
import bus.uigen.shapes.Shape;

public abstract class AVisualizerElementSkeleton<ElementType> implements
		VisualizerElement<ElementType> {

	protected Shape shape;
	protected Object object;
	protected ALineModel verticalLine, horizontalLine;
	protected int horizontalLineYOffset, verticalLineXOffset,
			elementShapeYOffset, elementShapeXOffset;
	protected ElementType parent, previousChild;
	protected ListenableVector<ElementType> vector = new AListenableVector<ElementType>();
	protected boolean animate;
	private Color highlighting = Color.black;
	private Color color = Color.black;
	private boolean vertical = false;

	public AVisualizerElementSkeleton(Shape _s, Object _o) {
		shape = _s;
		object = _o;
	}
	public void setVertical(boolean b){
		vertical = b;
	}
	public boolean isVertical(){
		return vertical;
	}
	public void setAnimate(boolean newVal) {
		animate = newVal;
	}

	public void setParent(ElementType p) {
		parent = p;
	}

	public void setPreviousChild(ElementType p) {
		previousChild = p;
	}

	/************** Getters *****************************/
	public ElementType getPreviousChild() {
		return previousChild;
	}

	public ElementType getParent() {
		return parent;
	}

	public ListenableVector<ElementType> getVector() {

		return vector;
	}

	public int getHorizontalLineYOffset() {
		return horizontalLineYOffset;
	}

	public int getVerticalLineXOffset() {
		return verticalLineXOffset;
	}

	public int getElementShapeYOffset() {
		return elementShapeYOffset;
	}

	public int getElementShapeXOffset() {
		return elementShapeXOffset;
	}

	public void setHorizontalLineYOffset(int newY) {
		horizontalLineYOffset = newY;
	}

	public void setVerticalLineXOffset(int newX) {
		verticalLineXOffset = newX;
	}

	public void setElementShapeYOffset(int newY) {
		elementShapeYOffset = newY;
	}

	public void setElementShapeXOffset(int newX) {
		elementShapeXOffset = newX;
	}

	public Shape getShape() {
		return shape;
	}

	public ALineModel getVerticalLine() {
		return verticalLine;
	}

	public ALineModel getHorizontalLine() {
		return horizontalLine;
	}

	public abstract void focusPosition(ElementType node);

	public abstract void focusPosition();

	public Object getObject() {
		return object;
	}

	public void setObject(Object o) {
		object = o;
	}

	public int getHeight() {
		return shape.getHeight();
	}

	public int getWidth() {
		return shape.getWidth();
	}

	public int getX() {
		return shape.getX();
	}

	public int getY() {
		return shape.getY();
	}

	public void setHeight(int arg0) {
		shape.setHeight(arg0);
	}

	public void setWidth(int arg0) {
		shape.setWidth(arg0);

	}

	public void setX(int arg0) {
		shape.setX(arg0);

	}

	public void setY(int arg0) {
		shape.setY(arg0);

	}

	public void setHighlighting(Color theColor) {
		highlighting = theColor;
	}

	@util.annotations.Visible(false)
	public Color getHighlighting() {
		return highlighting;
	}

	public void setColor(Color theColor) {
		color = theColor;
	}

	@util.annotations.Visible(false)
	public Color getColor() {
		return color;
	}

	public boolean contains(Point2D arg0) {
		return shape.contains(arg0);
	}

	public boolean contains(Rectangle2D arg0) {
		return shape.contains(arg0);
	}

	public boolean contains(double arg0, double arg1) {
		return shape.contains(arg0, arg1);
	}

	public boolean contains(double arg0, double arg1, double arg2, double arg3) {
		return shape.contains(arg0, arg1, arg2, arg3);
	}

	public Rectangle getBounds() {
		return shape.getBounds();
	}

	public Rectangle2D getBounds2D() {
	return shape.getBounds2D();
	}

	public Point getCenter() {
		return shape.getCenter();
	}

	public Point getEastEnd() {
		return shape.getEastEnd();
	}

	public Font getFont() {
		return shape.getFont();
	}

	public Point getNECorner() {
		return shape.getNECorner();
	}

	public Point getNWCorner() {
		return shape.getNWCorner();
	}

	public Point getNorthEnd() {
		return shape.getNorthEnd();
	}

	public Paint getPaint() {
		return shape.getPaint();
	}

	public PathIterator getPathIterator(AffineTransform arg0) {
		return shape.getPathIterator(arg0);
	}

	public PathIterator getPathIterator(AffineTransform arg0, double arg1) {
		return shape.getPathIterator(arg0, arg1);
	}

	public Point getPosition() {
	return shape.getPosition();
	}

	public Point getSECorner() {
		return shape.getSECorner();
	}

	public Point getSWCorner() {
		return shape.getSWCorner();
	}

	public Dimension getSize() {
		return shape.getSize();
	}

	public Point getSouthEnd() {
		return shape.getSouthEnd();
	}

	public Stroke getStroke() {
		return shape.getStroke();
	}

	public Point getWestEnd() {
		return shape.getWestEnd();
	}

	public int getZIndex() {
		return shape.getZIndex();
	}

	public boolean intersects(Rectangle2D arg0) {
		return shape.intersects(arg0);
	}

	public boolean intersects(double arg0, double arg1, double arg2, double arg3) {
		return shape.intersects(arg0, arg1, arg2, arg3);
	}

	public boolean is3D() {
		return shape.is3D();
	}

	public boolean isFilled() {
		return shape.isFilled();
	}

	public boolean isRounded() {
		return shape.isRounded();
	}

	public void moveX(int arg0) {
		shape.moveX(arg0);

	}

	public void moveY(int arg0) {
		shape.moveY(arg0);
	}

	public Object remoteClone() {
		return shape.remoteClone();
	}

	public void set3D(boolean arg0) {
		shape.set3D(arg0);

	}

	public void setBounds(Rectangle arg0) {
		shape.setBounds(arg0);

	}

	public void setBounds(Point arg0, Point arg1) {
		shape.setBounds(arg0, arg1);

	}

	public void setBounds(int arg0, int arg1, int arg2, int arg3) {
		shape.setBounds(arg0, arg1, arg2, arg3);

	}

	public void setCenter(Point arg0) {
		shape.setCenter(arg0);

	}

	public void setCenter(int arg0, int arg1) {
		shape.setCenter(arg0, arg1);

	}

	public void setDashedStroke() {
		shape.setDashedStroke();

	}

	public void setDottedStroke() {
		shape.setDottedStroke();

	}

	public void setEastEnd(Point arg0) {
		shape.setEastEnd(arg0);

	}

	public void setEastEnd(int arg0, int arg1) {
		shape.setEastEnd(arg0, arg1);

	}

	public void setFilled(boolean arg0) {
		shape.setFilled(arg0);

	}

	public void setFont(Font arg0) {
		shape.setFont(arg0);

	}

	public void setNECorner(Point arg0) {
		shape.setNECorner(arg0);

	}

	public void setNECorner(int arg0, int arg1) {
		shape.setNECorner(arg0, arg1);

	}

	public void setNWCorner(Point arg0) {
		shape.setNWCorner(arg0);

	}

	public void setNWCorner(int arg0, int arg1) {
		shape.setNWCorner(arg0, arg1);

	}

	public void setNorthEnd(Point arg0) {
		shape.setNorthEnd(arg0);

	}

	public void setNorthEnd(int arg0, int arg1) {
		shape.setNorthEnd(arg0, arg1);

	}

	public void setPaint(Paint arg0) {
		shape.setPaint(arg0);

	}

	public void setPosition(Point arg0) {
		shape.setPosition(arg0);

	}

	public void setRounded(boolean arg0) {
		shape.setRounded(arg0);

	}

	public void setSECorner(Point arg0) {
		shape.setSECorner(arg0);

	}

	public void setSECorner(int arg0, int arg1) {
		shape.setSECorner(arg0, arg1);

	}

	public void setSWCorner(Point arg0) {
		shape.setSWCorner(arg0);

	}

	public void setSWCorner(int arg0, int arg1) {
		shape.setSWCorner(arg0, arg1);

	}

	public void setSize(Dimension arg0) {
		shape.setSize(arg0);

	}

	public void setSize(int arg0, int arg1) {
		shape.setSize(arg0, arg1);

	}

	public void setSolidStroke() {
		shape.setSolidStroke();

	}

	public void setSouthEnd(Point arg0) {
		shape.setSouthEnd(arg0);

	}

	public void setSouthEnd(int arg0, int arg1) {
		shape.setSouthEnd(arg0, arg1);

	}

	public void setStroke(Stroke arg0) {
		shape.setStroke(arg0);

	}

	public void setWestEnd(Point arg0) {
		shape.setWestEnd(arg0);

	}

	public void setWestEnd(int arg0, int arg1) {
		shape.setWestEnd(arg0, arg1);

	}

	public void setZIndex(int arg0) {
		shape.setZIndex(arg0);

	}
}
