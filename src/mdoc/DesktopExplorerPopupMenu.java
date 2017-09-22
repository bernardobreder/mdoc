package mdoc;

import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class DesktopExplorerPopupMenu extends ExplorerPopupMenu {

	public DesktopExplorerPopupMenu() {
		this.add(new JSeparator(), 0);
		this.add(new JMenuItem("Abrir"), 0);
	}

}
