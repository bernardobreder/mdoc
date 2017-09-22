package mdoc.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class ToolbarButton extends JPanel {

	private static final MatteBorder CREATE_MATTE_BORDER = BorderFactory
			.createMatteBorder(1, 1, 1, 1, Color.GRAY);

	private static final MatteBorder CREATE_MATTE_BORDER_LIGHT = BorderFactory
			.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY);

	private static final Border CREATE_EMPTY_BORDER = BorderFactory
			.createEmptyBorder(1, 1, 1, 1);

	private JLabel label;

	public ToolbarButton(Icon icon, String toolTip,
			final ActionListener listener) {
		this.setLayout(new BorderLayout());
		this.setBorder(CREATE_EMPTY_BORDER);
		this.setOpaque(false);
		this.setToolTipText(toolTip);
		this.add(this.label = new JLabel(icon));
		this.label.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (label.isEnabled()) {
					setBorder(CREATE_MATTE_BORDER_LIGHT);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (label.isEnabled()) {
					setBorder(CREATE_MATTE_BORDER);
					if (listener != null) {
						listener.actionPerformed(new ActionEvent(label, 0,
								"action"));
					}
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setBorder(CREATE_EMPTY_BORDER);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (label.isEnabled()) {
					setBorder(CREATE_MATTE_BORDER);
				}
			}

		});
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.label.setEnabled(enabled);
	}

}
