package mdoc.testlib;

import mdoc.model.Folder;
import mdoc.model.RemoteDocument;

public class FileSystemMock {

	public Folder root;

	private Folder bFolder;

	private Folder cFolder;

	public FileSystemMock() {
		this.root = new Folder("");
		this.root.add(new RemoteDocument("a"));
		this.root.add(this.bFolder = new Folder("b"));
		this.bFolder.add(new RemoteDocument("a1"));
		this.bFolder.add(new RemoteDocument("a2"));
		this.bFolder.add(new RemoteDocument("a3"));
		this.root.add(this.cFolder = new Folder("c"));
		this.cFolder.add(new RemoteDocument("b1"));
		this.cFolder.add(new RemoteDocument("b2"));
		this.root.add(new RemoteDocument("d"));
	}

}
