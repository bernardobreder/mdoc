package mdoc.util.task;

import javax.swing.SwingUtilities;

public abstract class RemoteTask extends Task {

	@Override
	public void start() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					perform();
					executeUI(new Runnable() {
						@Override
						public void run() {
							doneUI();
						}
					});
				} catch (final Exception e) {
					executeUI(new Runnable() {
						@Override
						public void run() {
							handlerUI(e);
						}
					});
				} finally {
					executeUI(new Runnable() {
						@Override
						public void run() {
							finallyUI();
						}
					});
				}
			}
		}, this.getClass().getSimpleName()).start();
	}

	public abstract void perform() throws Exception;

	public void doneUI() {
	}

	public void finallyUI() {
	}

	public void handlerUI(Exception e) {
		e.printStackTrace();
	}

	public void executeUI(Runnable r) {
		SwingUtilities.invokeLater(r);
	}

}
