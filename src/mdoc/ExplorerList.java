package mdoc;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mdoc.model.Document;
import mdoc.model.Folder;
import mdoc.model.Resource;

public class ExplorerList extends MyList {

	private boolean hasFile;

	private boolean hasFolder;

	private Runnable viewAction;

	private Runnable openAction;

	private MyMouseEvent popupAction;

	private Folder root;

	/**
	 * @param hasFile
	 * @param hasFolder
	 * @param root
	 * @param viewAction
	 * @param openAction
	 * @param popupAction
	 */
	public ExplorerList(boolean hasFile, boolean hasFolder, Folder root,
			Runnable viewAction, Runnable openAction, MyMouseEvent popupAction) {
		super();
		this.root = root;
		this.hasFile = hasFile;
		this.hasFolder = hasFolder;
		this.viewAction = viewAction;
		this.openAction = openAction;
		this.popupAction = popupAction;
		this.refreshFolder();
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onMouseClicked(e);
			}
		});
		this.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						onListSelectionChanged(e);
					}
				});
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				onKeyReleased(e);
			}
		});
	}

	public Folder getRoot() {
		return root;
	}

	public void setRoot(Folder root) {
		this.root = root;
	}

	/**
	 * @param root
	 */
	public void refreshFolder() {
		DefaultListModel model = this.getModel();
		model.clear();
		List<Resource> list = root.list();
		for (int n = 0; n < list.size(); n++) {
			Resource resource = list.get(n);
			if (hasFile && resource instanceof Document) {
				model.addElement(list.get(n));
			} else if (hasFolder && resource instanceof Folder) {
				model.addElement(list.get(n));
			}
		}
		if(model.getSize()>0){
			this.setSelectedIndex(0);
		}
	}

	protected void onKeyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			int index = this.getSelectedIndex();
			if (index >= 0) {
				if (this.openAction != null) {
					this.openAction.run();
				}
			}
		}
	}

	protected void onListSelectionChanged(ListSelectionEvent e) {
		if (this.viewAction != null) {
			this.viewAction.run();
		}
	}

	// @Override
	// protected void onMouseReleased(MouseEvent e) {
	// super.onMouseReleased(e);
	// if (e.getButton() == MouseEvent.BUTTON3) {
	// this.setSelectionRow(e.getY() / this.getRowHeight());
	// if (this.popupAction != null) {
	// this.popupAction.mouseEvent(e);
	// }
	// }
	// }

	protected void onMouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			int index = this.getSelectedIndex();
			if (index >= 0) {
				if (this.openAction != null) {
					this.openAction.run();
				}
			}
		}
	}

	public static interface MyMouseEvent {

		public void mouseEvent(MouseEvent e);

	}

}
