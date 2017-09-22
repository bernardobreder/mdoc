package mdoc;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import mdoc.model.Resource;
import mdoc.resource.FSResource;

public class DefaultTreeRenderer extends DefaultTreeCellRenderer {

	private static final ImageIcon WORD = new ImageIcon(FSResource.getWord());

	private static final ImageIcon FOLDER_OPEN = new ImageIcon(
			FSResource.getFolderOpen());

	private static final ImageIcon FOLDER_CLOSE = new ImageIcon(
			FSResource.getFolderClose());

	public DefaultTreeRenderer() {
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, selected, expanded,
				leaf, row, hasFocus);
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		leaf = !node.getAllowsChildren();
		value = node.getUserObject();
		if (value instanceof Resource) {
			Resource resource = (Resource) value;
			this.setText(resource.getName());
		}
		this.setForeground(Color.BLACK);
		if (leaf) {
			this.setIcon(WORD);
		} else {
			if (expanded) {
				this.setIcon(FOLDER_OPEN);
			} else {
				this.setIcon(FOLDER_CLOSE);
			}
		}
		return this;
	}

	private Object getObjectDate(Object value) {
		if (value instanceof DefaultMutableTreeNode) {
			return ((DefaultMutableTreeNode) value).getUserObject();
		}
		return value;
	}

}
