package collection.visualizer.commands.vector;

import bus.uigen.uiFrame;
import bus.uigen.reflect.MethodProxy;
import bus.uigen.undo.Command;
import bus.uigen.undo.CommandListener;
import collection.visualizer.datatype.vector.ALinearBuffer;

public class AChangeCommand<ElementType> implements Command{
	
	
	private ALinearBuffer<ElementType> buffer;
	private ElementType newVal, oldVal;
	private int position;
	
	public AChangeCommand(ALinearBuffer<ElementType> buf, ElementType theObj, int pos) {
		buffer = buf;
		position = pos;
		newVal = theObj;
	}
	public Object execute() {
		
		oldVal = buffer.set(position, newVal);
		return null;
	}
	public void undo() {
		oldVal = buffer.set(position, oldVal);
	}
	public Object getObject(){
		
		return newVal;
	}
	public int getPosition(){
		
		return position;
	}
	public void redo() {
		// TODO Auto-generated method stub
		
	}
	public Command clone(Object arg0, Object[] arg1, uiFrame arg2,
			CommandListener arg3) {
		// TODO Auto-generated method stub
		return null;
	}
	public MethodProxy getMethod() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean getNotUndoablePurgesUndoHistory() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isNoOp() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isUndoable() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isVoid() {
		// TODO Auto-generated method stub
		return false;
	}
	public void setNotUndoablePurgesUndoHistory(boolean arg0) {
		// TODO Auto-generated method stub
		
	}
}
