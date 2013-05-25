package collection.visualizer;


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import util.models.IndexedElementChecker;
import util.models.VectorChangeSupport;
import util.models.VectorListener;
import util.models.VectorMethodsListener;
import bus.uigen.shapes.Shape;

import collection.visualizer.common.AListenableVector;
import collection.visualizer.common.ListenableVector;
import collection.visualizer.controller.ButtonPressTrapper;
import collection.visualizer.controller.Control;
import collection.visualizer.layout.DefaultLayoutManager;
import collection.visualizer.layout.LayoutManager;
import collection.visualizer.threads.CommandHistory;

import collection.visualizer.trappers.*;


public class ADelegatingCollectionVisualizer<Observer, ObservableDataType extends EventGenerator<Observer, ObservableDataType>>
		implements CollectionVisualizer<Observer, ObservableDataType> {

	protected ACollectionVisualizer<Observer, ObservableDataType> visualizer;
	private ListenableVector<Shape> vector = new AListenableVector<Shape>();

	public ADelegatingCollectionVisualizer(ACollectionVisualizer<Observer, ObservableDataType> visualizer) {
		this.visualizer = visualizer;
	}

	@util.annotations.Visible(false)
	public Control getController() {
		return visualizer.getController();
	}

	@util.annotations.Visible(false)
	public void changeAnimationPauseTime(int newTime) {
		visualizer.changeAnimationPauseTime(newTime);

	}

	@util.annotations.Visible(false)
	public boolean getCanProceed() {
		return visualizer.getCanProceed();
	}

	@util.annotations.Visible(false)
	public CommandHistory getUndoer() {
		return visualizer.getUndoer();
	}

	@util.annotations.Visible(false)
	public void notifyVisualizer() {
		visualizer.notifyVisualizer();

	}

	@util.annotations.Visible(false)
	public HashMap<Integer, ObservableDataType> getOriginalData() {
		return visualizer.getOriginalData();
	}

	@util.annotations.Visible(false)
	public void setCanProceed(boolean b) {
		visualizer.setCanProceed(b);

	}

	@util.annotations.Visible(false)
	public void visualize(ObservableDataType data) throws Exception {
		visualizer.visualize(data);

	}

	@util.annotations.Visible(false)
	public void waitForNextBufferThreadStep() {
		visualizer.waitForNextBufferThreadStep();

	}

	public ButtonPressTrapper initializeButtonPressTrapper() {
		// TODO Auto-generated method stub
		return visualizer.initializeButtonPressTrapper();
	}

	public void addTrapper(EventTrapper<Observer, ObservableDataType> trapper)
			throws Exception {
		visualizer.addTrapper(trapper);
		
	}

	public void removeTrapper(EventTrapper<Observer, ObservableDataType> trapper)
			throws Exception {
		visualizer.removeTrapper(trapper);
		
	}
	
	public LayoutManager getLayoutManager(){
		return new DefaultLayoutManager<Observer, ObservableDataType>(visualizer);
		
	}
	public ListenableVector<Shape> getShapes(){
		return visualizer.getShapes();
	}
	/*****************************************************/
	public ListenableVector<Shape> deepClone() {
		return null;
	}

	public VectorChangeSupport<Shape> getVectorChangeSupport() {
		return vector.getVectorChangeSupport();
	}

	public void setVectorChangeSupport(VectorChangeSupport<Shape> arg0) {
		vector.setVectorChangeSupport(arg0);
		
	}

	public void addVectorListener(VectorListener vectorListener) {
		vector.addVectorListener(vectorListener);
		
	}

	public void removeVectorListener(VectorListener vectorListener) {
		vector.removeVectorListener(vectorListener);
	}

	public void addVectorMethodsListener(VectorMethodsListener vectorListener) {
		vector.addVectorMethodsListener(vectorListener);
	}

	public void removeVectorMethodsListener(VectorMethodsListener vectorListener) {
		vector.removeVectorMethodsListener(vectorListener);
	}

	public void addElement(Shape c) {
		vector.addElement(c);
	}

	public boolean addAll(Collection<? extends Shape> elements) {
		return vector.addAll(elements);
	}

	public void insertElementAt(Shape element, int pos) {
		vector.insertElementAt(element, pos);
	}

	public boolean removeElement(Shape c) {
		return vector.removeElement(c);
	}

	public void removeElementAt(int pos) {
		vector.removeElementAt(pos);
	}

	public void setElementAt(Shape element, int pos) {
		vector.setElementAt(element, pos);
	}

	public int size() {
		return vector.size();
	}

	public void removeAllElements() {
		vector.removeAllElements();
	}

	public Shape elementAt(int i) {
		return vector.elementAt(i);
	}

	public IndexedElementChecker<Shape> getIndexedElementChecker() {
		return vector.getIndexedElementChecker();
	}

	public void setIndexedElementChecker(IndexedElementChecker<Shape> theChecker) {
		vector.setIndexedElementChecker(theChecker);
	}

	public void open(Shape element) {
		vector.open(element);
	}

	public boolean preElementAt(int index) {
		return vector.preElementAt(index);
	}

	public Object getUserObject() {
	return vector.getUserObject();
	}

	public void setUserObject(Object newValue) {
		vector.setUserObject(newValue);
	}

	public util.models.AListenableVector getParent() {
		return vector.getParent();
	}

	public void setIsEditable(int index, boolean status) {
		vector.setIsEditable(index, status);
		
	}

	public boolean isEditable(int index) {
		return vector.isEditable(index);
	}

	public boolean isColumnEditable(int index) {
		return vector.isColumnEditable(index);
	}

	public boolean preRemoveElement(Shape element) {
		return vector.preRemoveElement(element);
	}

	public boolean preRemoveElementAt(int index) {
		return vector.preRemoveElementAt(index);
	}

	public boolean preAddElement(Shape element) {
		return vector.preAddElement(element);
	}

	public boolean preSetElementAt(Shape element, int pos) {
	return vector.preSetElementAt(element, pos);
	}

	public boolean preInsertElementAt(Shape element, int pos) {
	return vector.preInsertElementAt(element, pos);
	}

	public void initSerializedObject() {
		vector.initSerializedObject();
	}

	public void swap(int index1, int index2) {
		vector.swap(index1, index2);
	}

	public void swap(int index1, List<Shape> other, int index2) {
		vector.swap(index1, other, index2);
	}

	public void move(int fromIndex, int toIndex) {
		vector.move(fromIndex, toIndex);
	}

	public void move(int fromIndex, List<Shape> to, int toIndex) {
		vector.move(fromIndex, to, toIndex);
	}

	public void copy(int fromIndex, int toIndex) {
		vector.copy(fromIndex, toIndex);
	}

	public void copy(int fromIndex, List<Shape> to, int toIndex) {
		vector.copy(fromIndex, to, toIndex);
	}

	public void replace(int fromIndex, int toIndex) {
		vector.replace(fromIndex, toIndex);
	}

	public void replace(int fromIndex, List<Shape> to, int toIndex) {
		vector.replace(fromIndex, to, toIndex);
	}

	public boolean add(Shape arg0) {
		return vector.add(arg0);
	}

	public void add(int arg0, Shape arg1) {
		vector.add(arg0,arg1);
	}

	public boolean addAll(int arg0, Collection<? extends Shape> arg1) {
		return vector.addAll(arg0, arg1);
	}

	public void clear() {
		vector.clear();		
	}

	public boolean contains(Object arg0) {
		return vector.contains(arg0);
	}

	public boolean containsAll(Collection<?> arg0) {
		return vector.containsAll(arg0);
	}

	public Shape get(int arg0) {
		return vector.get(arg0);
	}

	public int indexOf(Object arg0) {
		return vector.indexOf(arg0);
	}

	public boolean isEmpty() {
		return vector.isEmpty();
	}

	public Iterator<Shape> iterator() {
		return vector.iterator();
	}

	public int lastIndexOf(Object arg0) {
		return vector.lastIndexOf(arg0);
	}

	public ListIterator<Shape> listIterator() {
		return vector.listIterator();
	}

	public ListIterator<Shape> listIterator(int arg0) {
		return vector.listIterator(arg0);
	}

	public boolean remove(Object arg0) {
		return vector.remove(arg0);
	}

	public Shape remove(int arg0) {
		return vector.remove(arg0);
	}

	public boolean removeAll(Collection<?> arg0) {
		return vector.removeAll(arg0);
	}

	public boolean retainAll(Collection<?> arg0) {
		return vector.retainAll(arg0);
	}

	public Shape set(int arg0, Shape arg1) {
		return vector.set(arg0, arg1);
	}

	public List<Shape> subList(int arg0, int arg1) {
		return vector.subList(arg0, arg1);
	}

	public Object[] toArray() {
		return vector.toArray();
	}

	public <T> T[] toArray(T[] arg0) {
		return vector.toArray(arg0);
	}

	public void addListener(
			EventTrapper<VectorMethodsListener<Shape>, ListenableVector<Shape>> observer)
			throws Exception {
		vector.addListener(observer);
		
	}

	public void removeListener(
			EventTrapper<VectorMethodsListener<Shape>, ListenableVector<Shape>> observer) {
		vector.removeListener(observer);
		
	}
}