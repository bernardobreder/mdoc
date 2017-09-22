package mdoc;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.border.Border;

import mdoc.model.Document;
import mdoc.model.Folder;
import mdoc.model.Resource;
import mdoc.resource.FSResource;

public class DefaultListRenderer extends DefaultListCellRenderer {

	private static final Border SELECTED_BORDER = BorderFactory
			.createLineBorder(UI.ITEM_LINECOLOR);
	
	private static final Border SELECTED_UNFOCUS_BORDER = BorderFactory
			.createLineBorder(UI.ITEM_LINE_UNFOCUS_COLOR);

	private static final Border UNSELECTED_BORDER = BorderFactory
			.createLineBorder(Color.WHITE);

	private static final ImageIcon WORD = new ImageIcon(FSResource.getWord());

	private static final ImageIcon FOLDER_OPEN = new ImageIcon(
			FSResource.getFolderOpen());

	private static final ImageIcon FOLDER_CLOSE = new ImageIcon(
			FSResource.getFolderClose());

	private Font defaultFont;

	/**
   * 
   */
	public DefaultListRenderer() {
		this.defaultFont = this.getFont().deriveFont(Font.PLAIN);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected,
				cellHasFocus);
		this.setFont(defaultFont);
		if (isSelected) {
			if(cellHasFocus){
				this.setBorder(SELECTED_BORDER);
			}else{
				this.setBorder(SELECTED_UNFOCUS_BORDER);
			}
		} else {
			this.setBorder(UNSELECTED_BORDER);
		}
		if (value instanceof Resource) {
			Resource resource = (Resource) value;
			if (resource instanceof Folder) {
				this.setIcon(FOLDER_CLOSE);
			} else {
				if (resource instanceof Document) {
					this.setIcon(WORD);
				} else {
					this.setIcon(WORD);
				}
			}
			this.setText(resource.getName());
		}
		this.setForeground(Color.BLACK);
		this.setPreferredSize(new Dimension(10,20));
		return this;
	}

}
