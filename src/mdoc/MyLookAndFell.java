package mdoc;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

import mdoc.util.so.SoUtil;

public class MyLookAndFell {

	public static void install() {
		try {
			installNimbus();
		} catch (Exception e) {
			try {
				installMetal();
			} catch (Exception e1) {
			}
		}
	}

	private static void installMetal() throws Exception {
		UIManager.setLookAndFeel(MetalLookAndFeel.class.getName());
	}

	private static void installNimbus() throws Exception {
		UIManager
				.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		// http://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/_nimbusDefaults.html
		UIManager.put("Tree:TreeCell[Focused+Selected].backgroundPainter",
				new Color(225, 225, 255));
		UIManager.put("Tree:TreeCelltruetrue.backgroundPainter", new Color(225,
				225, 255));
		if (SoUtil.isMac()) {
			installMacOsShortKey((InputMap) UIManager
					.get("TextArea.focusInputMap"));
			installMacOsShortKey((InputMap) UIManager
					.get("TextField.focusInputMap"));
		}
	}

	private static void installMacOsShortKey(InputMap im) {
		for (KeyStroke key : im.allKeys()) {
			if ((key.getModifiers() & KeyEvent.CTRL_DOWN_MASK) == KeyEvent.CTRL_DOWN_MASK) {
				im.put(KeyStroke.getKeyStroke(key.getKeyCode(),
						KeyEvent.META_DOWN_MASK), im.get(key));
			}
		}
	}

	public static void installTreeTheme(JTree tree) {
		@SuppressWarnings("restriction")
		Object painter = new com.sun.java.swing.Painter<JComponent>() {
			@Override
			public void paint(Graphics2D g, JComponent c, int w, int h) {
				// g.setPaint(UI.gradient(new Color(200, 200, 255), new
				// Color(170,
				// 170, 255), h));
				g.setColor(UI.ITEM_BACKCOLOR);
				g.fillRect(0, 0, w, h);
				g.setColor(UI.ITEM_LINECOLOR);
				g.drawRect(0, 0, w - 1, h - 1);
			}
		};
		Object painter_unfocus = new com.sun.java.swing.Painter<JComponent>() {
			@Override
			public void paint(Graphics2D g, JComponent c, int w, int h) {
				g.setColor(UI.ITEM_BACKCOLOR);
				g.fillRect(0, 0, w, h);
				g.setColor(UI.ITEM_LINE_UNFOCUS_COLOR);
				g.drawRect(0, 0, w - 1, h - 1);
			}
		};
		UIDefaults map = new UIDefaults();
		map.put("Tree:TreeCell[Focused+Selected].backgroundPainter", painter);
		map.put("Tree:TreeCell[Enabled+Selected].backgroundPainter", painter_unfocus);
		map.put("Tree:TreeCell[Enabled+Focused].backgroundPainter", painter);
		tree.putClientProperty("Nimbus.Overrides", map);
	}

	public static void installListTheme(JList tree) {
		tree.setSelectionBackground(UI.ITEM_BACKCOLOR);
	}
}
