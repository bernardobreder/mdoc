package mdoc;

import java.awt.Color;

import javax.swing.JTextArea;

public class MyTextArea extends JTextArea {

	public MyTextArea() {
		this.setLineWrap(true);
	}

	@Override
	public void setEnabled(boolean enabled) {
		if (enabled) {
			this.setBackground(Color.WHITE);
		} else {
			this.setBackground(Color.LIGHT_GRAY);
		}
		super.setEnabled(enabled);
	}

}
