package mdoc.help;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ComponentInputMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ActionMapUIResource;

import rht.RHTPanel;
import mdoc.MyLookAndFell;
import mdoc.resource.FSResource;
import mdoc.swing.MyFrame;

public class HelpFrame extends MyFrame {

	private RHTPanel panel;

	public HelpFrame() {
		this.setTitle("Ajuda");
		this.setIconImage(FSResource.getHelp());
		this.add(this.panel = new RHTPanel(
				"# Introdução\n\nEsse é um teste de Help\n(...)"));
		this.panel.setPreferredSize(new Dimension(640, 480));
		this.pack();
		this.setLocationRelativeTo(null);
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
