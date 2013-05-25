package collection.visualizer.datatype.vector;

import java.util.Collection;

import collection.visualizer.ABuffer;
import collection.visualizer.Buffer;
import collection.visualizer.CollectionVisualizer;
import collection.visualizer.common.*;
import collection.visualizer.commands.vector.AAddCommand;
import collection.visualizer.commands.vector.AChangeCommand;
import collection.visualizer.commands.vector.AnInsertCommand;
import collection.visualizer.commands.vector.ARemoveCommand;
import collection.visualizer.commands.vector.ASwapCommand;
import collection.visualizer.controller.Control;
import collection.visualizer.threads.CommandHistory;

import collection.visualizer.trappers.*;

import util.models.VectorMethodsListener;

public class ALinearBuffer<ElementType> extends AListenableVector<ElementType>
		implements
		VectorMethodsListener<ElementType>,
		Buffer<VectorMethodsListener<ElementType>, ListenableVector<ElementType>>,
		EventTrapper<VectorMethodsListener<ElementType>, ListenableVector<ElementType>> {

	protected Buffer<VectorMethodsListener<ElementType>, ListenableVector<ElementType>> buffer;
	
	
	public ALinearBuffer(
			CollectionVisualizer<VectorMethodsListener<ElementType>, ListenableVector<ElementType>> visualizer) {
		buffer = new ABuffer<VectorMethodsListener<ElementType>, ListenableVector<ElementType>>(visualizer);
		
	}

	public ALinearBuffer(
			ListenableVector<ElementType> data,
			CollectionVisualizer<VectorMethodsListener<ElementType>, ListenableVector<ElementType>> visualizer) {
		this(visualizer);
		this.setBufferData(data);

	}

	public void elementAdded(Object source, ElementType element, int newSize) {
		this.getCommandHistory().addCommand(new AAddCommand<ElementType>(this, element));

	}

	public void elementChanged(Object source, ElementType element, int pos) {
		this.getCommandHistory().addCommand(new AChangeCommand<ElementType>(this, element, pos));

	}

	public void elementCopied(Object source, int fromIndex, int toIndex,
			int newSize) {
		// TODO Auto-generated method stub

	}

	public void elementCopied(Object source, int fromIndex, int fromNewSize,
			Object to, int toIndex) {
		// TODO Auto-generated method stub

	}

	public void elementInserted(Object source, ElementType element, int pos,
			int newSize) {
		this.getCommandHistory().addCommand(new AnInsertCommand<ElementType>(this, element, pos));

	}

	public void elementMoved(Object source, int fromIndex, int toIndex) {
		// TODO Auto-generated method stub

	}

	public void elementMoved(Object source, int fromIndex, int fromNewSize,
			Object to, int toIndex) {
		// TODO Auto-generated method stub

	}

	public void elementRemoved(Object source, int pos, int newSize) {
		this.getCommandHistory().addCommand(new ARemoveCommand<ElementType>(this, pos));

	}

	public void elementRemoved(Object source, ElementType element, int newSize) {
		this.getCommandHistory().addCommand(new ARemoveCommand<ElementType>(this, element));

	}

	public void elementReplaced(Object source, int fromIndex, int toIndex,
			int newSize) {
		// TODO Auto-generated method stub

	}

	public void elementReplaced(Object source, int fromIndex, int newFromSize,
			Object to, int toIndex) {
		// TODO Auto-generated method stub

	}

	public void elementSwapped(Object newParam, int index1, int index2) {
		this.getCommandHistory().addCommand(new ASwapCommand(this, index1, index2));

	}

	public void elementSwapped(Object source, int index1, Object other,
			int index2) {
		this.getCommandHistory().addCommand(new ASwapCommand(this, index1, index2));

	}

	@SuppressWarnings("rawtypes")
	public void elementsAdded(Object source, Collection element, int newSize) {
		// TODO Auto-generated method stub

	}

	public void elementsCleared(Object source) {
		// TODO Auto-generated method stub
	}

	public CommandHistory getCommandHistory() {
		return buffer.getCommandHistory();
	}

	public void putBufferThread(
			CollectionVisualizer<VectorMethodsListener<ElementType>, ListenableVector<ElementType>> v,
			Control control) {
		buffer.putBufferThread(v, control);

	}

	public void setBufferData(ListenableVector<ElementType> data) {
		buffer.setBufferData(data);
		this.removeAllElements();
		data.addVectorMethodsListener(this);
		
		for(ElementType e: data){
			this.add(e);
		}
	}
	private static final long serialVersionUID = -5548423608100622613L;


	public ListenableVector<ElementType> getBufferData() {
		return buffer.getBufferData();
	}


}
