package mdoc.swing;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ComponentInputMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ActionMapUIResource;

public class MyDialog extends JDialog {

	public MyDialog() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.installLinkKey();
	}

	private void installLinkKey() {
		ActionMap actionMap = new ActionMapUIResource();
		actionMap.put("frame_close", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		JComponent p = this.getRootPane();
		InputMap keyMap = new ComponentInputMap(p);
		keyMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "frame_close");
		SwingUtilities.replaceUIActionMap(p, actionMap);
		SwingUtilities.replaceUIInputMap(p, JComponent.WHEN_IN_FOCUSED_WINDOW,
				keyMap);
	}

}
