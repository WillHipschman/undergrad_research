package collection.visualizer.datatype.bean;


import java.beans.PropertyChangeListener;
import collection.visualizer.ACollectionVisualizer;
import collection.visualizer.CollectionVisualizer;
import collection.visualizer.controller.AGenericButtonPressTrapper;
import collection.visualizer.controller.ButtonPressTrapper;
import collection.visualizer.layout.LayoutManager;

public class ABeanVisualizer extends
		ACollectionVisualizer<PropertyChangeListener, BeanEventGenerator>
		implements
		CollectionVisualizer<PropertyChangeListener, BeanEventGenerator> {

	private static final long serialVersionUID = 5651052060059133256L;
	private int pauseTimeInMilliseconds = 20;
	private BeanEventGenerator bean;

	public ABeanVisualizer(LayoutManager<BeanEventGenerator> layoutManager, BeanEventGenerator bean) {
		super();
		this.bean = bean;
		((ABeanBuffer) buffer).setBean(this.bean);
		this.layoutManager = layoutManager;
		
	}

	@Override
	protected BeanEventGenerator initializeBuffer() {
		return new ABeanBuffer(this);
	}

	@Override
	public ButtonPressTrapper initializeButtonPressTrapper() {
		return new AGenericButtonPressTrapper(controller, this,
				pauseTimeInMilliseconds);
	}

}
