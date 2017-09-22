package mdoc;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTree;

public class MyScrollTree extends JScrollPane {

	public MyScrollTree(JTree tree) {
		super(tree);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				onMouseReleased(e);
			}
		});
	}

	protected void onMouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			JTree tree = this.getComponent();
			int rowHeight = tree.getRowHeight();
			int y = e.getY();
			int row = y / rowHeight;
			tree.setSelectionRow(row);
		}
	}

	public JTree getComponent() {
		return (JTree) this.getViewport().getView();
	}

}
