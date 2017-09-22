package mdoc.swing;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ComponentInputMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ActionMapUIResource;

public class MyFrame extends JFrame {

	private List<MyFrame> frames = new ArrayList<MyFrame>();

	private static Map<Object, MyFrame> indexs = new HashMap<Object, MyFrame>();

	public MyFrame() {
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				forceClose();
			}
		});
		if (false) {
			ActionMap actionMap = new ActionMapUIResource();
			actionMap.put("frame_close", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Save action performed.");
				}
			});
			actionMap.put("action_exit", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Exit action performed.");
				}
			});
			JComponent p = (JComponent) this.getRootPane();
			InputMap keyMap = new ComponentInputMap(p);
			keyMap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S,
					java.awt.Event.CTRL_MASK), "action_save");
			keyMap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q,
					java.awt.Event.CTRL_MASK), "action_exit");
			SwingUtilities.replaceUIActionMap(p, actionMap);
			SwingUtilities.replaceUIInputMap(p,
					JComponent.WHEN_IN_FOCUSED_WINDOW, keyMap);
		}
	}

	public static <E extends MyFrame> E get(Object index) {
		@SuppressWarnings("unchecked")
		E e = (E) indexs.get(index);
		return e;
	}

	public static void set(Object index, MyFrame frame) {
		indexs.put(index, frame);
	}

	protected void forceClose() {
		if (this.tryClose()) {
			this.close();
		}
	}

	public void close() {
		if (!this.frames.isEmpty()) {
			for (MyFrame frame : this.frames) {
				if (!frame.tryClose()) {
					return;
				}
			}
			for (MyFrame frame : this.frames) {
				frame.close();
			}
		}
		for (Entry<Object, MyFrame> entry : indexs.entrySet()) {
			if (entry.getValue().equals(this)) {
				indexs.remove(entry.getKey());
				break;
			}
		}
		this.dispose();
	}

	protected void focus() {
		this.requestFocusInWindow();
	}

	protected boolean tryClose() {
		return true;
	}

	public void show(final MyFrame frame) {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				frames.remove(frame);
			}
		});
		this.frames.add(frame);
		frame.setVisible(true);
	}

}
