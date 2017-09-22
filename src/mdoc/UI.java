package mdoc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

import mdoc.resource.FSResource;
import mdoc.swing.MyFrame;
import mdoc.swing.ToolbarButton;
import mdoc.util.so.SoUtil;

public class UI {

	public static Color CENTER_BACKCOLOR = Color.WHITE;

	public static Color LEFT_BACKCOLOR = new Color(245, 245, 255);

	public static Color LEFT_LINECOLOR = new Color(220, 220, 255);

	public static Color ITEM_BACKCOLOR = new Color(225, 225, 255);

	public static Color ITEM_LINECOLOR = new Color(200, 200, 255);
	
	public static Color ITEM_LINE_UNFOCUS_COLOR = new Color(200, 200, 200);

	public static Color TOOLBAR_LINECOLOR = new Color(175, 175, 175);

	public static Color TOOLBAR_BACKCOLOR = new Color(200, 200, 200);

	public static GradientPaint gradient(Color colorUp, Color colorDown, int h) {
		return new GradientPaint(0, 0, colorUp, 0, h, colorDown);
	}

	public static JPanel createPanel(Component c, Color background,
			Border border, Dimension preferenceSize) {
		JPanel panel = new JPanel(new BorderLayout());
		if (c != null) {
			panel.add(c, BorderLayout.CENTER);
		}
		if (background != null) {
			panel.setBackground(background);
		} else {
			panel.setOpaque(false);
		}
		if (border != null) {
			panel.setBorder(border);
		}
		if (preferenceSize != null) {
			panel.setPreferredSize(preferenceSize);
		}
		return panel;
	}

	public static JMenuItem createMenuItem(String text, BufferedImage image,
			char mnemonic, KeyStroke acceletor, ActionListener listener) {
		JMenuItem item = new JMenuItem(text, new ImageIcon(image));
		item.setAccelerator(acceletor);
		item.setMnemonic(mnemonic);
		item.addActionListener(listener);
		return item;
	}

	public static JButton createButton(String text, BufferedImage image,
			char mnemonic, ActionListener listener) {
		JButton c = new JButton(text, new ImageIcon(image));
		c.setMnemonic(mnemonic);
		c.addActionListener(listener);
		return c;
	}

	public static ToolbarButton createToolbarButton(BufferedImage image,
			String toolTip, ActionListener listener) {
		return new ToolbarButton(new ImageIcon(image), toolTip, listener);
	}

	public static JMenu createMenu(String text, BufferedImage image,
			char mnemonic) {
		JMenu menu = new JMenu(text);
		menu.setIcon(new ImageIcon(image));
		menu.setMnemonic(mnemonic);
		return menu;
	}

	public static KeyStroke keyStroke(String modifier, char letter) {
		if (Character.isLowerCase(letter)) {
			letter = Character.toUpperCase(letter);
		}
		if (SoUtil.isMac()) {
			int index = modifier.indexOf("ctrl");
			if (index >= 0) {
				modifier = modifier.substring(0, index) + "meta"
						+ modifier.substring(index + 4);
			}
			String text = modifier + " " + letter;
			return KeyStroke.getKeyStroke(text.trim());
		} else {
			int index = modifier.indexOf("meta");
			if (index >= 0) {
				modifier = modifier.substring(0, index) + "ctrl"
						+ modifier.substring(index + 4);
			}
			String text = modifier + " " + letter;
			return KeyStroke.getKeyStroke(text.trim());
		}
	}

	public static JMenuItem createQuitMenu(final MyFrame frame) {
		return UI.createMenuItem("Sair", FSResource.getQuit(), 'S',
				UI.keyStroke("ctrl", 'w'), new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.close();
					}
				});
	}

}
