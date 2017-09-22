package mdoc.swing;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mdoc.UI;

public class MyToolbar extends JPanel {

	public MyToolbar() {
		super(new FlowLayout(FlowLayout.LEFT, 3, 3));
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,
				UI.TOOLBAR_LINECOLOR));
		this.setBackground(UI.TOOLBAR_BACKCOLOR);
		this.setOpaque(true);
	}

	public ToolbarButton add(BufferedImage image, String tooltip,
			ActionListener listener) {
		ToolbarButton c = UI.createToolbarButton(image, tooltip, listener);
		this.add(c);
		return c;
	}

	public void addSeparator() {
		JLabel c = new JLabel("|");
		c.setForeground(UI.TOOLBAR_LINECOLOR);
		this.add(c);
	}

}
