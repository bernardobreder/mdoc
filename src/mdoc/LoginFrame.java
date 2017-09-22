package mdoc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import mdoc.resource.FSResource;
import mdoc.swing.MyButtonBar;

public class LoginFrame extends JFrame {

	public LoginFrame() {
		this.setTitle("Autenticação");
		this.add(this.createPane(), BorderLayout.CENTER);
		this.add(this.createButtons(), BorderLayout.SOUTH);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				onQuitAction();
			}
		});
	}

	private Component createButtons() {
		return new MyButtonBar().add(UI.createButton("Entrar",
				FSResource.getYes(), 'E', new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						onLoginAction();
					}

				}), this);
	}

	private Component createPane() {
		JPanel p = new JPanel(new BorderLayout());
		p.setBackground(UI.CENTER_BACKCOLOR);
		p.add(this.createCenter(), BorderLayout.CENTER);
		p.add(this.createSouth(), BorderLayout.SOUTH);
		return p;
	}

	private Component createCenter() {
		JLabel c = new JLabel(new ImageIcon(FSResource.getLogo()));
		// c.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		return c;
	}

	private Component createSouth() {
		JPanel p = new JPanel(new GridBagLayout());
		// p.setOpaque(false);
		p.setBackground(UI.LEFT_BACKCOLOR);
		p.add(new JLabel("Usuário:", JLabel.CENTER), new GBC(0, 0).horizontal());
		p.add(new JTextField(), new GBC(0, 1).horizontal());
		p.add(new JLabel("Senha:", JLabel.CENTER), new GBC(1, 0).horizontal());
		p.add(new JPasswordField(), new GBC(1, 1).horizontal());
		return UI.createPanel(p, UI.LEFT_BACKCOLOR, null, null);
	}

	protected void onLoginAction() {
		DesktopFrame frame = new DesktopFrame();
		frame.setVisible(true);
		this.dispose();
	}

	private void onQuitAction() {
		System.exit(0);
	}

	public static void main(String[] args) {
		MyLookAndFell.install();
		new LoginFrame().setVisible(true);
	}

}
