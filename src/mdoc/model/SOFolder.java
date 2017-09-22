package mdoc.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SOFolder extends Folder {

	private File file;

	public SOFolder(File file) {
		super(file.getName());
		this.file = file;
	}

	public SOFolder add(Resource doc) {
		throw new IllegalStateException();
	}

	public List<Resource> list() {
		List<Resource> list = new ArrayList<Resource>();
		File[] files = file.listFiles();
		if (files != null) {
			for (File f : files) {
				if (!f.isHidden()) {
					if (f.isFile()) {
						list.add(new SODocument(f));
					} else {
						list.add(new SOFolder(f));
					}
				}
			}
		}
		return list;
	}

}
