package mdoc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;

import mdoc.delete.DeleteDialog;
import mdoc.move.MoveDialog;
import mdoc.rename.RenameDialog;
import mdoc.resource.FSResource;

public class ExplorerPopupMenu extends JPopupMenu {

	public ExplorerPopupMenu() {
		this.add(UI.createMenuItem("Copiar", FSResource.getCopy(), 'c',
				UI.keyStroke("ctrl", 'c'), new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						onCopyAction();
					}
				}));
		this.add(UI.createMenuItem("Duplicar", FSResource.getDuplicate(), 'c',
				UI.keyStroke("ctrl", 'd'), new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						onDuplicateAction();
					}
				}));
		this.add(UI.createMenuItem("Move", FSResource.getMove(), 'm',
				UI.keyStroke("meta", 'm'), new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						onMoveAction();
					}
				}));
		this.add(UI.createMenuItem("Renomear", FSResource.getRename(), 'r',
				UI.keyStroke("meta", 'r'), new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						onRenameAction();
					}
				}));
		this.add(UI.createMenuItem("Deletar", FSResource.getDuplicate(), 'd',
				KeyStroke.getKeyStroke("del"), new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						onDeleteAction();
					}
				}));
	}

	protected void onMoveAction() {
		new MoveDialog().setVisible(true);
	}

	protected void onDeleteAction() {
		new DeleteDialog().setVisible(true);
	}

	protected void onRenameAction() {
		new RenameDialog().setVisible(true);
	}

	protected void onDuplicateAction() {
	}

	protected void onCopyAction() {
	}

}
