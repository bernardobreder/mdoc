package mdoc.model;

public abstract class Resource {

	private Folder parent;

	private String name;

	public Resource(String name) {
		super();
		this.name = name;
	}

	public Folder getParent() {
		return parent;
	}

	public void setParent(Folder parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isFile() {
		return this instanceof Document;
	}

	public boolean isFolder() {
		return this instanceof Folder;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}

}
