package mdoc.editor.doc;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.charset.Charset;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;

import mdoc.MyLookAndFell;
import mdoc.MyTextArea;
import mdoc.UI;
import mdoc.model.Document;
import mdoc.model.SOFolder;
import mdoc.resource.FSResource;
import mdoc.swing.MyFrame;
import mdoc.swing.MyOpenFileDialog;
import mdoc.swing.MyToolbar;
import rht.RHT;
import rht.RHTPanel;

public class DocumentFrame extends MyFrame {

	private MyTextArea text;

	public DocumentFrame(Document data) {
		this.setTitle("Document - ?");
		this.setIconImage(FSResource.getWord());
		this.add(this.createToolbar(), BorderLayout.NORTH);
		this.add(this.createCenter(), BorderLayout.CENTER);
		this.setJMenuBar(this.createMenuBar());
		this.initSize();
	}

	public void setDocumentEnable(boolean flag) {
		this.text.setEnabled(flag);
		this.setEnabled(flag);
	}

	private void initSize() {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		int min = Math.min(size.width, size.height);
		this.setSize(min - 200, min - 200);
		this.setLocationRelativeTo(null);
	}

	private JMenuBar createMenuBar() {
		JMenuBar bar = new JMenuBar();
		bar.add(this.createFileMenu());
		return bar;
	}

	private JMenu createFileMenu() {
		JMenu menu = UI.createMenu("Arquivo", FSResource.getFileMenu(), 'a');
		menu.add(UI.createMenuItem("Salvar", FSResource.getSave(), 's',
				UI.keyStroke("meta", 's'), new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
					}
				}));
		menu.add(UI.createMenuItem("Imprimir...", FSResource.getPrint(), 'p',
				UI.keyStroke("meta", 'p'), new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						onPrintAction();
					}
				}));
		menu.add(UI.createMenuItem("Exportar...", FSResource.getExport(), 'e',
				UI.keyStroke("meta", 'e'), new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
					}
				}));
		menu.addSeparator();
		menu.add(UI.createQuitMenu(this));
		return menu;
	}

	protected void onPrintAction() {
		try {
			new RHTPanel(new RHT(new ByteArrayInputStream(this.text.getText()
					.getBytes(Charset.forName("utf-8"))))).print();
		} catch (PrinterException e) {
			e.printStackTrace();
		}
	}

	private Component createToolbar() {
		MyToolbar p = new MyToolbar();
		p.add(FSResource.getSave(), "Salvar o Documento", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		p.add(FSResource.getPrint(), "Imprimir o Documento",
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						onPrintAction();
					}
				});
		p.add(FSResource.getExport(), "Exportar o Documento para RTF",
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						onExportAction();
					}
				});
		return p;
	}

	protected void onExportAction() {
		final MyOpenFileDialog dialog = new MyOpenFileDialog(new SOFolder(
				new File(".")));
		dialog.setAction(new Runnable() {
			@Override
			public void run() {
				Document document = dialog.getDocument();
				dialog.dispose();
				// new RHTPanel(text.getText()).exportRtf(output);
			}
		});
		dialog.setVisible(true);
	}

	private Component createCenter() {
		this.text = new MyTextArea();
		this.text.setMargin(new Insets(2, 2, 2, 2));
		JScrollPane scroll = new JScrollPane(text);
		scroll.setBorder(BorderFactory.createEmptyBorder());
		return scroll;
	}

	public static void main(String[] args) {
		MyLookAndFell.install();
		new DocumentFrame(null).setVisible(true);
	}

}
