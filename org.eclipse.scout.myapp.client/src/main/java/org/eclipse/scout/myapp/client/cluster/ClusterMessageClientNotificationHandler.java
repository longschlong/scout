package org.eclipse.scout.myapp.client.cluster;

import java.util.concurrent.Callable;

import org.eclipse.scout.myapp.client.ClientSession;
import org.eclipse.scout.myapp.shared.cluster.ClusterMessage;
import org.eclipse.scout.rt.client.context.ClientRunContexts;
import org.eclipse.scout.rt.client.job.ModelJobs;
import org.eclipse.scout.rt.client.ui.desktop.IDesktop;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.notification.INotificationHandler;

/**
 * <h3>{@link ClusterMessageClientNotificationHandler}</h3>
 *
 * @author mlu
 */
public class ClusterMessageClientNotificationHandler implements INotificationHandler<ClusterMessage> {

	@Override
	public void handleNotification(final ClusterMessage notification) {
		//System.out.println("handleNotification(...)");
		ModelJobs.schedule(new Callable<ClusterMessage>() {

			@Override
			public ClusterMessage call() throws Exception {
				updateForm(notification);
				return notification;
			}

		}, ModelJobs.newInput(ClientRunContexts.copyCurrent()));
	}

	private void updateForm(ClusterMessage notification) {
		if (notification == null) {
			return;
		}
		ConversationForm f = findOrCreateForm();
		if (f != null) {
			if (notification.isClearMessage()) {
				f.getConversationField().setValue(null);
			}
			else {
				String currentValue = StringUtility.nvl(f.getConversationField().getValue(), "");
				StringBuffer b = new StringBuffer(currentValue);
				b.append(notification.getStatus().getMessage());
				b.append("\r\n");
				f.getConversationField().setValue(b.toString());
			}
		}
	}

	private ConversationForm findOrCreateForm() {
		IDesktop desktop = ClientSession.get().getDesktop();
		if (desktop != null && desktop.isOpened()) {
			ConversationForm form = desktop.findForm(ConversationForm.class);
			if (form != null) {
				return form;
			}
			else {
				// Show form
				ConversationForm f = new ConversationForm();
				f.start();
				return f;
			}
		}
		return null;
	}
}
