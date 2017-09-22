package mdoc;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class LoadingPanel extends JPanel {

	public LoadingPanel() {
		this.setOpaque(false);
		this.setLayout(new GridBagLayout());
		this.add(new JLabel(), new GBC(0, 0).both());
		this.add(this.createLabel(), new GBC(0, 1).horizontal());
		this.add(this.createProgressBar(), new GBC(0, 2));
		this.add(new JLabel(), new GBC(0, 3).both());
	}

	private JLabel createLabel() {
		JLabel c = new JLabel("Carregando...", JLabel.CENTER);
		c.setFont(c.getFont().deriveFont(36f));
		return c;
	}

	private JProgressBar createProgressBar() {
		JProgressBar c = new JProgressBar();
		// c.setIndeterminate(true);
		c.setMinimum(0);
		c.setMaximum(100);
		c.setValue(50);
		c.setPreferredSize(new Dimension(400, 20));
		return c;
	}

}
