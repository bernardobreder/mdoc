package mdoc.resource;

import java.awt.AWTException;
import java.awt.CheckboxMenuItem;
import java.awt.Menu;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;

public class MyTrayIcon {

	private static TrayIcon trayIcon;

	public static void install() {
		if (SystemTray.isSupported()) {
			try {
				SystemTray.getSystemTray().add(
						trayIcon = new TrayIcon(FSResource.getTrayicon(),
								"Online Document Explorer Application",
								createPopupMenu()));
			} catch (AWTException e) {
			}
		}
	}

	public static void uninstall() {
		if (SystemTray.isSupported()) {
			SystemTray.getSystemTray().remove(trayIcon);
		}
	}

	private static PopupMenu createPopupMenu() {
		PopupMenu menu = new PopupMenu();
		menu.add(createDocumentMenu());
		menu.addSeparator();
		menu.add("Trocar Usuário");
		menu.add("Sair");
		return menu;
	}

	private static Menu createDocumentMenu() {
		Menu menu = new Menu("Documentos");
		menu.add("a");
		menu.add("b");
		menu.add(new Menu("c"));
		menu.add("d");
		menu.add(new CheckboxMenuItem("aa", true));
		return menu;
	}

}
