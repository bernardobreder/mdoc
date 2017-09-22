package mdoc.move;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import mdoc.ExplorerPopupMenu;
import mdoc.ExplorerTree;
import mdoc.ExplorerTree.MyMouseEvent;
import mdoc.GBC;
import mdoc.UI;
import mdoc.model.SOFolder;
import mdoc.resource.FSResource;
import mdoc.swing.MyButtonBar;
import mdoc.swing.MyDialog;

public class MoveDialog extends MyDialog {

	private ExplorerTree tree;

	private JButton button;

	private JTextField text;

	public MoveDialog() {
		this.setTitle("Mover");
		this.setIconImage(FSResource.getMove());
		this.add(this.createButtons(), BorderLayout.SOUTH);
		this.add(this.createContent(), BorderLayout.CENTER);
		this.pack();
		this.setLocationRelativeTo(null);
		this.tree.requestFocus();
	}

	private Component createContent() {
		JPanel p = new JPanel(new GridBagLayout());
		p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		p.add(new JLabel("Deseja mover o arquivo:"), new GBC(0, 0).horizontal());
		p.add(this.text = new JTextField("a.txt"), new GBC(0, 1).horizontal());
		this.text.setEditable(false);
		SOFolder root = new SOFolder(new File("."));
		JScrollPane scroll = new JScrollPane(this.tree = new ExplorerTree(root, true,
				null, null, new MyMouseEvent() {
					@Override
					public void mouseEvent(MouseEvent e) {
						new ExplorerPopupMenu().show(tree, e.getX(), e.getY());
					}
				}));
		scroll.setBorder(BorderFactory.createEmptyBorder());
		scroll.setPreferredSize(new Dimension(400, 300));
		p.add(scroll, new GBC(0, 2).both());
		this.tree.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					onAction();
				}
			}
		});
		this.tree.getSelectionModel().addTreeSelectionListener(
				new TreeSelectionListener() {
					@Override
					public void valueChanged(TreeSelectionEvent e) {
						onSelectedAction(e);
					}
				});
		if (this.tree.getRowCount() > 0) {
			this.tree.addSelectionRow(0);
		}
		return p;
	}

	protected void onSelectedAction(TreeSelectionEvent e) {
		this.button.setEnabled(this.tree.getSelectionCount() > 0);
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
		this.button.setEnabled(false);
		return bar;
	}

	protected void onAction() {
		dispose();
	}

}
