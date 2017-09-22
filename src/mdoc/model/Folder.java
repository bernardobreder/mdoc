package mdoc.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Folder extends Resource {

	private List<Resource> children = new ArrayList<Resource>();

	private List<Resource> childrenLock;

	public Folder(String name) {
		super(name);
	}

	public Folder add(Resource doc) {
		this.children.add(doc);
		doc.setParent(this);
		return this;
	}

	public List<Resource> list() {
		if (childrenLock == null) {
			childrenLock = Collections.unmodifiableList(children);
		}
		return childrenLock;
	}

}
