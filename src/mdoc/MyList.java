package mdoc;

import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;

public class MyList extends JList {

	public MyList() {
		super(new DefaultListModel());
		this.setCellRenderer(new DefaultListRenderer());
		MyLookAndFell.installListTheme(this);
	}

	public MyList(ListModel dataModel) {
		super(dataModel);
	}

	public MyList(Object[] listData) {
		super(listData);
	}

	public MyList(Vector<?> listData) {
		super(listData);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DefaultListModel getModel() {
		return (DefaultListModel) super.getModel();
	}

}
