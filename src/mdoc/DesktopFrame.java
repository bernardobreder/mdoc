package mdoc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.charset.Charset;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import mdoc.action.OpenDocumentTask;
import mdoc.delete.DeleteDialog;
import mdoc.editor.doc.DocumentFrame;
import mdoc.help.HelpFrame;
import mdoc.model.Document;
import mdoc.model.Resource;
import mdoc.model.SOFolder;
import mdoc.move.MoveDialog;
import mdoc.rename.RenameDialog;
import mdoc.resource.FSResource;
import mdoc.resource.MyTrayIcon;
import mdoc.swing.MyFrame;
import mdoc.swing.MyOpenFileDialog;
import mdoc.swing.MyToolbar;
import mdoc.swing.ToolbarButton;
import rht.RHT;
import rht.RHTPanel;

public class DesktopFrame extends MyFrame {

	private ExplorerTree tree;
	private RHTPanel rhtPreview;
	private ToolbarButton printToolButton;
	private ToolbarButton exportToolButton;

	public DesktopFrame() {
		this.setTitle("Online Document Explorer Application");
		this.setIconImage(FSResource.getTrayicon());
		this.add(this.createToolbar(), BorderLayout.NORTH);
		this.add(this.createPane(), BorderLayout.CENTER);
		this.add(this.createSouth(), BorderLayout.SOUTH);
		this.setJMenuBar(this.createMenuBar());
		this.initSize();
		MyTrayIcon.install();
	}

	@Override
	public void close() {
		super.close();
		if (this.tryClose()) {
			MyTrayIcon.uninstall();
		}
	}

	private void initSize() {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(size.width - 100, size.height - 100);
		this.setLocationRelativeTo(null);
	}

	private JMenuBar createMenuBar() {
		JMenuBar bar = new JMenuBar();
		bar.add(this.createFileMenu());
		bar.add(this.createEditMenu());
		bar.add(this.createHelpMenu());
		return bar;
	}

	private JMenu createFileMenu() {
		JMenu menu = UI.createMenu("Arquivo", FSResource.getFileMenu(), 'a');
		menu.add(UI.createMenuItem("Renomear", FSResource.getRename(), 'u',
				UI.keyStroke("ctrl", 'r'), new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						onRenameAction();
					}
				}));
		menu.add(UI.createMenuItem("Mover", FSResource.getRename(), 'm',
				UI.keyStroke("ctrl", 'm'), new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						onMoveAction();
					}
				}));
		menu.add(UI.createMenuItem("Deletar", FSResource.getDel(), 'u',
				KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0),
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						onDeleteAction();
					}
				}));
		menu.addSeparator();
		menu.add(UI.createMenuItem("Trocar Usuário", FSResource.getSignout(),
				'u', UI.keyStroke("ctrl shift", 'w'), new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						onSignoutAction();
					}
				}));
		menu.add(UI.createQuitMenu(this));
		return menu;
	}

	protected void onMoveAction() {
		new MoveDialog().setVisible(true);
	}

	private JMenu createEditMenu() {
		JMenu menu = UI.createMenu("Editar", FSResource.getEdit(), 'a');
		menu.add(UI.createMenuItem("Copiar", FSResource.getCopy(), 'u',
				UI.keyStroke("ctrl", 'c'), new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						onCopyAction();
					}
				}));
		menu.add(UI.createMenuItem("Colar", FSResource.getPaste(), 'u',
				UI.keyStroke("ctrl", 'v'), new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						onPasteAction();
					}
				}));
		return menu;
	}

	private JMenu createHelpMenu() {
		JMenu menu = UI.createMenu("Ajuda", FSResource.getHelp(), 'j');
		menu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new HelpFrame().setVisible(true);
			}
		});
		return menu;
	}

	protected void onPasteAction() {
		// TODO Auto-generated method stub

	}

	protected void onCopyAction() {
		// TODO Auto-generated method stub

	}

	protected void onDeleteAction() {
		new DeleteDialog().setVisible(true);
	}

	protected void onRenameAction() {
		new RenameDialog().setVisible(true);
	}

	protected void onSignoutAction() {
		if (this.tryClose()) {
			this.close();
			new LoginFrame().setVisible(true);
		}
	}

	private Component createToolbar() {
		MyToolbar c = new MyToolbar();
		c.add(FSResource.getRename(), "Renomear o Arquivo ou Diretório", null);
		c.add(FSResource.getMove(), "Mover o Arquivo ou Diretório", null);
		c.add(FSResource.getDel(), "Deletar o Arquivo ou Diretório", null);
		c.addSeparator();
		this.printToolButton = c.add(FSResource.getPrint(),
				"Imprimir o Arquivo", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							rhtPreview.print();
						} catch (PrinterException e1) {
							e1.printStackTrace();
						}
					}
				});
		this.exportToolButton = c.add(FSResource.getExport(),
				"Exportar o Arquivo para RTF", new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						final MyOpenFileDialog dialog = new MyOpenFileDialog(
								new SOFolder(new File(".")));
						dialog.setAction(new Runnable() {
							@Override
							public void run() {
								dialog.dispose();
							}
						});
						dialog.setVisible(true);
					}
				});
		c.addSeparator();
		c.add(FSResource.getHelp(), "Ajuda", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new HelpFrame().setVisible(true);
			}
		});
		return c;
	}

	private Component createSouth() {
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0,
				UI.TOOLBAR_LINECOLOR));
		p.setBackground(UI.TOOLBAR_BACKCOLOR);
		p.setOpaque(true);
		p.add(new JLabel("|"));
		return p;
	}

	private Component createPane() {
		JPanel p = new JPanel(new BorderLayout());
		p.add(this.createCenter(), BorderLayout.CENTER);
		p.add(this.createTree(), BorderLayout.WEST);
		return p;
	}

	private Component createTree() {
		JPanel spacePanel = new JPanel(new BorderLayout());
		spacePanel.setBackground(UI.LEFT_BACKCOLOR);
		spacePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		tree = new ExplorerTree(new SOFolder(new File(".")), false,
				new TreeSelectionListener() {
					@Override
					public void valueChanged(TreeSelectionEvent e) {
						onViewDocument();
					}
				}, new Runnable() {
					@Override
					public void run() {
						onOpenDocument();
					}

				}, new ExplorerTree.MyMouseEvent() {
					@Override
					public void mouseEvent(MouseEvent e) {
						onPopupDocument(e);
					}
				});
		tree.setBackground(UI.LEFT_BACKCOLOR);
		MyScrollTree scroll = new MyScrollTree(tree);
		spacePanel.add(scroll);
		return UI.createPanel(spacePanel, UI.LEFT_BACKCOLOR,
				BorderFactory.createMatteBorder(0, 0, 0, 1, UI.LEFT_LINECOLOR),
				new Dimension(300, 300));
	}

	private Component createCenter() {
		JPanel p = new JPanel(new BorderLayout());
		p.setBackground(Color.WHITE);
		p.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		p.add(this.rhtPreview = new RHTPanel(
				new RHT(
						new ByteArrayInputStream(
								"# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n# Intro\n\nEra um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que um teste que irei fazer isso é para que\nVez um\nTexto\n# Indice interno\n\nação\n"
										.getBytes(Charset.forName("utf-8"))))));
		return p;
	}

	protected void onOpenDocument() {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.tree
				.getSelectionPath().getLastPathComponent();
		Resource resource = (Resource) node.getUserObject();
		if (resource.isFile()) {
			Document doc = (Document) this.tree.getDocument();
			String index = DocumentFrame.class.getSimpleName() + "_"
					+ doc.toString();
			DocumentFrame frame = MyFrame.get(index);
			if (frame == null) {
				new OpenDocumentTask(this, index,doc).start();
			} else {
				frame.setVisible(true);
			}
		} else {
		}
	}

	protected void onPopupDocument(MouseEvent e) {
		JPopupMenu menu = new DesktopExplorerPopupMenu();
		menu.show(tree, e.getX(), e.getY());
	}

	protected void onViewDocument() {
		Resource document = this.tree == null ? null : this.tree.getDocument();
		boolean isFile = document == null ? false : document.isFile();
		this.printToolButton.setEnabled(isFile);
		this.exportToolButton.setEnabled(isFile);
	}

	public static void main(String[] args) {
		MyLookAndFell.install();
		new DesktopFrame().setVisible(true);
	}

}
