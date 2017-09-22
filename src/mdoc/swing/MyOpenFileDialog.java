package mdoc.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import mdoc.ExplorerList;
import mdoc.ExplorerPopupMenu;
import mdoc.ExplorerTree;
import mdoc.GBC;
import mdoc.UI;
import mdoc.model.Document;
import mdoc.model.Folder;
import mdoc.model.SOFolder;
import mdoc.resource.FSResource;

public class MyOpenFileDialog extends MyDialog {

	private ExplorerTree tree;
	private ExplorerList list;
	private Runnable action;
	private JButton open;
	private Folder root;

	public MyOpenFileDialog(Folder root) {
		this.root = root;
		this.add(this.createContentPane(), BorderLayout.CENTER);
		this.add(this.createButtonsPane(), BorderLayout.SOUTH);
		this.setSize(640, 480);
		this.setLocationRelativeTo(null);
		this.refreshValidation();
	}

	/**
	 * @param action
	 */
	public void setAction(Runnable action) {
		this.action = action;
	}

	private Component createContentPane() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder());
		{
			JScrollPane scroll = new JScrollPane(this.tree = new ExplorerTree(
					root, true, null, new Runnable() {
						@Override
						public void run() {
							if (action != null) {
								action.run();
							}
						}
					}, new ExplorerTree.MyMouseEvent() {
						@Override
						public void mouseEvent(MouseEvent e) {
							ExplorerPopupMenu popupMenu = new ExplorerPopupMenu();
							popupMenu.show(tree, e.getX(), e.getY());
						}
					}));
			this.tree.getSelectionModel().addTreeSelectionListener(
					new TreeSelectionListener() {
						@Override
						public void valueChanged(TreeSelectionEvent e) {
							onChangedPath();
							refreshValidation();
						}
					});
			scroll.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1,
					UI.LEFT_LINECOLOR));
			panel.add(scroll, new GBC(0, 0).both(0.25d, 1d).insets(0));
		}
		{
			JScrollPane scroll = new JScrollPane(this.list = new ExplorerList(
					true, false, root, null, new Runnable() {
						@Override
						public void run() {
							if (action != null) {
								action.run();
							}
						}
					}, new ExplorerList.MyMouseEvent() {
						@Override
						public void mouseEvent(MouseEvent e) {
							ExplorerPopupMenu popupMenu = new ExplorerPopupMenu();
							popupMenu.show(list, e.getX(), e.getY());
						}
					}));
			this.list.getSelectionModel().addListSelectionListener(
					new ListSelectionListener() {
						@Override
						public void valueChanged(ListSelectionEvent arg0) {
							refreshValidation();
						}
					});
			scroll.setBorder(BorderFactory.createEmptyBorder());
			panel.add(scroll, new GBC(1, 0).both(0.75d, 1d).insets(0));
		}
		return panel;
	}

	protected void onChangedPath() {
		Folder folder = (Folder) this.tree.getDocument();
		this.list.setRoot(folder);
		this.list.refreshFolder();
	}

	protected void refreshValidation() {
		this.open.setEnabled(this.getDocument() != null);
	}

	private Component createButtonsPane() {
		MyButtonBar bar = new MyButtonBar();
		bar.add(this.open = UI.createButton("Abrir", FSResource.getYes(), 'a',
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (action != null) {
							action.run();
						}
					}
				}), this);
		this.open.setFocusable(false);
		return bar;
	}

	public Document getDocument() {
		if (this.list.isSelectionEmpty()) {
			return null;
		}
		Folder path = (Folder) this.tree.getDocument();
		if (path == null) {
			return null;
		}
		Document doc = (Document) this.list.getSelectedValue();
		return doc;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				SOFolder root = new SOFolder(new File("."));
				new MyOpenFileDialog(root).setVisible(true);
			}
		});
	}

}
