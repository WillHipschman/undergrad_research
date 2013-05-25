package collection.visualizer.commands.vector;

import bus.uigen.uiFrame;
import bus.uigen.reflect.MethodProxy;
import bus.uigen.undo.Command;
import bus.uigen.undo.CommandListener;
import collection.visualizer.datatype.vector.ALinearBuffer;

public class AAddCommand<ElementType> implements Command{
	
	private ALinearBuffer<ElementType> buffer;
	private ElementType obj;
	
	public AAddCommand(ALinearBuffer<ElementType> theBuffer, ElementType theObj) 
	{
		buffer = theBuffer;
		obj = theObj;
	}
	public Object execute() {
		buffer.addElement(obj);
		return null;
	}
	public void undo() {
		buffer.remove(obj);
	}
	public Object getObject(){
		
		return obj;
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
