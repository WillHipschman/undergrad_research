package collection.visualizer.examples.OE;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import collection.visualizer.CollectionVisualizer;

import bus.uigen.ObjectEditor;
import bus.uigen.uiFrame;
import bus.uigen.attributes.AttributeNames;

public class CreateCustomView {
	private JButton next = new JButton(">");
	private JButton previous = new JButton("<");
	private JButton last = new JButton(">>");
	private JButton first = new JButton("<<");

	private JPanel dataPanel = new JPanel();
	private JPanel codePanel = new JPanel();
	private JPanel buttonPanel = new JPanel();

	private JSplitPane dataAndCodePanel = new JSplitPane();

	private JFrame frame = new JFrame();

	private void layoutAndPopulateButtonPanel() {
		buttonPanel.add(first);
		buttonPanel.add(previous);
		buttonPanel.add(next);
		buttonPanel.add(last);
	}

	JPanel topPanel = new JPanel();

	@SuppressWarnings("deprecation")
	private void layoutAndPopulateFrame(boolean showCode) {

		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);
		if (showCode) {
			frame.getContentPane().add(dataAndCodePanel);
		} else {
			frame.getContentPane().add(dataPanel);
		}
		frame.setSize(600, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setCursor(Cursor.DEFAULT_CURSOR);

	}

	private void layoutAndPopulateCodeAndDataPanel() {
		dataPanel.setPreferredSize(new Dimension(300, 200));
		dataAndCodePanel.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		dataAndCodePanel.setLeftComponent(dataPanel);
		dataAndCodePanel.setRightComponent(codePanel);

	}

	// static Class[] nullArgs = {};

	@SuppressWarnings("rawtypes")
	public uiFrame createNestedView(Object[] menuItems,
			CollectionVisualizer visualizer, Object code, boolean showButtons,
			boolean showCode) {
		ObjectEditor.setDefaultAttribute(
				AttributeNames.PREDEFINED_MENUS_CHOICE, new String[] {});
		ObjectEditor.setDefaultAttribute(AttributeNames.SEPARATE_THREAD, true);
		ObjectEditor.setDefaultAttribute(AttributeNames.SHOW_SYSTEM_MENUS,
				false);

		if (showButtons) {
			layoutAndPopulateButtonPanel();
		}
		if (showCode) {
			layoutAndPopulateCodeAndDataPanel();
		}

		uiFrame editor = ObjectEditor.createOEFrame(frame);

		Object[] menuObjects = menuItems;
		ObjectEditor.addMenuObjects(editor, menuObjects);

		layoutAndPopulateFrame(showCode);

		// ObjectEditor.editInDrawingContainer(editor, visualizer, dataPanel,
		// false);

		ObjectEditor.editInDrawingContainer(code, codePanel, false);

		ObjectEditor.bind(editor, visualizer.getController(), "first", first);
		ObjectEditor.bind(editor, visualizer.getController(), "previous",
				previous);
		ObjectEditor.bind(editor, visualizer.getController(), "next", next);
		ObjectEditor.bind(editor, visualizer.getController(), "last", last);

		return editor;

	}

	public void setDataPanel(JPanel panel) {
		dataPanel = panel;
	}
	public JPanel getDataPanel() {
		return dataPanel;
	}
	public JSplitPane getDataAndCodePanel() {
		return dataAndCodePanel;
	}
	

	@SuppressWarnings("rawtypes")
	public uiFrame createView(Object[] menuItems,
			CollectionVisualizer visualizer, Object code, boolean showButtons,
			boolean showCode) {
		ObjectEditor.setDefaultAttribute(
				AttributeNames.PREDEFINED_MENUS_CHOICE, new String[] {});
		ObjectEditor.setDefaultAttribute(AttributeNames.SEPARATE_THREAD, true);
		ObjectEditor.setDefaultAttribute(AttributeNames.SHOW_SYSTEM_MENUS,
				false);

		if (showButtons) {
			layoutAndPopulateButtonPanel();
		}
		if (showCode) {
			layoutAndPopulateCodeAndDataPanel();
		}

		uiFrame editor = ObjectEditor.createOEFrame(frame);

		Object[] menuObjects = menuItems;
		ObjectEditor.addMenuObjects(editor, menuObjects);

		layoutAndPopulateFrame(showCode);

		ObjectEditor.editInDrawingContainer(editor, visualizer, dataPanel,
				false);

		ObjectEditor.editInDrawingContainer(code, codePanel, false);

		ObjectEditor.bind(editor, visualizer.getController(), "first", first);
		ObjectEditor.bind(editor, visualizer.getController(), "previous",
				previous);
		ObjectEditor.bind(editor, visualizer.getController(), "next", next);
		ObjectEditor.bind(editor, visualizer.getController(), "last", last);

		return editor;

	}

}
