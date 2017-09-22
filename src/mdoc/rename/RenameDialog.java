package mdoc.rename;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mdoc.GBC;
import mdoc.UI;
import mdoc.resource.FSResource;
import mdoc.swing.MyButtonBar;
import mdoc.swing.MyDialog;

public class RenameDialog extends MyDialog {

	private JTextField text;

	public RenameDialog() {
		this.setTitle("Alterar o Nome");
		this.setIconImage(FSResource.getRename());
		this.add(this.createContent(), BorderLayout.CENTER);
		this.add(this.createButtons(), BorderLayout.SOUTH);
		this.pack();
		this.setLocationRelativeTo(null);

	}

	private Component createContent() {
		JPanel p = new JPanel(new GridBagLayout());
		p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		p.add(new JLabel("Qual nome gostaria de renomear:"),
				new GBC(0, 0).horizontal());
		p.add(this.text = new JTextField(), new GBC(0, 1).horizontal());
		this.text.setText("a.txt");
		this.text.selectAll();
		this.text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					onAction();
				}
			}
		});
		return p;
	}

	private Component createButtons() {
		MyButtonBar bar = new MyButtonBar();
		bar.add(UI.createButton("Alterar", FSResource.getYes(), 'a',
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						onAction();
					}
				}), this);
		return bar;
	}

	protected void onAction() {
		dispose();
	}

}
