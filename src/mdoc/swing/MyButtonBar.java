package mdoc.swing;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.RootPaneContainer;

public class MyButtonBar extends JPanel {

	public MyButtonBar() {
		super(new FlowLayout(FlowLayout.RIGHT));
		this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));
		this.setBackground(Color.LIGHT_GRAY);
	}

	public MyButtonBar add(JButton button, RootPaneContainer w) {
		super.add(button);
		w.getRootPane().setDefaultButton(button);
		return this;
	}

}
