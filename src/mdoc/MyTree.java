package mdoc;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class MyTree extends JTree {

	public MyTree(DefaultMutableTreeNode root) {
		super(root);
		this.setRootVisible(false);
		this.setCellRenderer(new DefaultTreeRenderer());
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				onMouseReleased(e);
			}
		});
		this.setRowHeight(20);
		MyLookAndFell.installTreeTheme(this);
	}

	public void updateUI() {

		super.updateUI();
	}

	protected void onMouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			this.setSelectionRow(e.getY() / this.getRowHeight());
		}
	}

}
