package mdoc.delete;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mdoc.GBC;
import mdoc.UI;
import mdoc.resource.FSResource;
import mdoc.swing.MyButtonBar;
import mdoc.swing.MyDialog;

public class DeleteDialog extends MyDialog {

	private JTextField text;
	private JButton button;

	public DeleteDialog() {
		this.setTitle("Deletar");
		this.setIconImage(FSResource.getDel());
		this.add(this.createContent(), BorderLayout.CENTER);
		this.add(this.createButtons(), BorderLayout.SOUTH);
		this.pack();
		this.setLocationRelativeTo(null);
		this.button.requestFocus();
	}

	private Component createContent() {
		JPanel p = new JPanel(new GridBagLayout());
		p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		p.add(new JLabel("Gostaria de deletar o arquivo:"),
				new GBC(0, 0).horizontal());
		p.add(this.text = new JTextField(), new GBC(0, 1).horizontal());
		this.text.setText("a.txt");
		this.text.setEditable(false);
		return p;
	}

	private Component createButtons() {
		MyButtonBar bar = new MyButtonBar();
		bar.add(this.button = UI.createButton("Deletar", FSResource.getYes(),
				'd', new ActionListener() {
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
