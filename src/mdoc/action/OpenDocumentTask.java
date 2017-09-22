package mdoc.action;

import mdoc.DesktopFrame;
import mdoc.editor.doc.DocumentFrame;
import mdoc.model.Document;
import mdoc.swing.MyFrame;
import mdoc.util.task.RemoteTask;

public class OpenDocumentTask extends RemoteTask {

	private DesktopFrame desktopFrame;

	private String index;

	private DocumentFrame frame;

	private Document data;

	public OpenDocumentTask(DesktopFrame desktopFrame, String index,
			Document data) {
		this.desktopFrame = desktopFrame;
		this.index = index;
		this.data = data;
	}

	@Override
	public void perform() throws Exception {
		this.executeUI(new Runnable() {
			@Override
			public void run() {
				frame = new DocumentFrame(data);
				frame.setDocumentEnable(false);
				desktopFrame.show(frame);
				MyFrame.set(index, frame);
			}
		});
		Thread.sleep(5000);
		this.executeUI(new Runnable() {
			@Override
			public void run() {
				frame.setDocumentEnable(true);
			}
		});
	}

}
