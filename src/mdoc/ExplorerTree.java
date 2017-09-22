package mdoc;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import mdoc.model.Document;
import mdoc.model.Folder;
import mdoc.model.Resource;
import mdoc.testlib.FileSystemMock;

public class ExplorerTree extends MyTree {

	private TreeSelectionListener viewAction;

	private Runnable openAction;

	private MyMouseEvent popupAction;

	private boolean onlyFolder;

	private Folder root;

	public ExplorerTree(Folder root, boolean onlyFolder,
			TreeSelectionListener viewAction, Runnable openAction,
			MyMouseEvent popupAction) {
		super(new DefaultMutableTreeNode());
		this.root = root;
		this.onlyFolder = onlyFolder;
		this.viewAction = viewAction;
		this.openAction = openAction;
		this.popupAction = popupAction;
		this.setModel(new DefaultTreeModel(this.createRoot(root)));
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onMouseClicked(e);
			}
		});
		this.getSelectionModel().addTreeSelectionListener(
				new TreeSelectionListener() {
					@Override
					public void valueChanged(TreeSelectionEvent e) {
						onTreeSelectionChanged(e);
					}
				});
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				onKeyReleased(e);
			}
		});
		if (this.root.list().size() > 0) {
			this.setSelectionRow(0);
		}
	}

	private DefaultMutableTreeNode createRoot(Folder rootFolder) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootFolder);
		root.setAllowsChildren(true);
		for (Resource resource : rootFolder.list()) {
			if (resource instanceof Document) {
				if (!this.onlyFolder) {
					DefaultMutableTreeNode child = new DefaultMutableTreeNode(
							resource);
					child.setAllowsChildren(false);
					root.add(child);
				}
			} else {
				root.add(createRoot((Folder) resource));
			}
		}
		return root;
	}

	protected void onKeyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			TreePath path = this.getSelectionPath();
			if (path != null) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) path
						.getLastPathComponent();
				if (node.isLeaf()) {
					if (this.openAction != null) {
						this.openAction.run();
					}
				}
			}
		}
	}

	protected void onTreeSelectionChanged(TreeSelectionEvent e) {
		if (this.viewAction != null) {
			this.viewAction.valueChanged(e);
		}
	}

	protected void onMouseReleased(MouseEvent e) {
		super.onMouseReleased(e);
		if (e.getButton() == MouseEvent.BUTTON3) {
			this.setSelectionRow(e.getY() / this.getRowHeight());
			if (this.popupAction != null) {
				this.popupAction.mouseEvent(e);
			}
		}
	}

	protected void onMouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			TreePath path = this.getSelectionPath();
			if (path != null) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) path
						.getLastPathComponent();
				if (node.isLeaf()) {
					if (this.openAction != null) {
						this.openAction.run();
					}
				}
			}
		}
	}

	public Resource getDocument() {
		if (this.getSelectionCount() == 0) {
			return null;
		}
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) this
				.getSelectionPath().getLastPathComponent();
		return (Resource) node.getUserObject();
	}

	public static interface MyMouseEvent {

		public void mouseEvent(MouseEvent e);

	}

}
