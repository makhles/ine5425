package view;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LabeledTextInput extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JTextField entry;

	public LabeledTextInput(String labelText, String entryText) {
		super();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		init(labelText, entryText);
	}

	private void init(String labelText, String entryText) {
		label = new JLabel(labelText);
		entry = new JTextField(10);
		entry.setText(entryText);
		add(label);
		add(entry);
	}

	public void setLabelText(String text) {
		label.setText(text);
	}

	public void setEntryText(String text) {
		entry.setText(text);
	}

	public String getEntryText() {
		return entry.getText();
	}
}
