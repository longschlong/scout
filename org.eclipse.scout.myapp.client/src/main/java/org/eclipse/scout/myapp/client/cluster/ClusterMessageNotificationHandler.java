package org.eclipse.scout.myapp.client.cluster;

import java.util.concurrent.Callable;

import org.eclipse.scout.myapp.client.ClientSession;
import org.eclipse.scout.myapp.shared.cluster.ClusterMessage;
import org.eclipse.scout.rt.client.IClientSession;
import org.eclipse.scout.rt.client.context.ClientRunContext;
import org.eclipse.scout.rt.client.context.ClientRunContexts;
import org.eclipse.scout.rt.client.job.ModelJobs;
import org.eclipse.scout.rt.client.ui.desktop.IDesktop;
import org.eclipse.scout.rt.platform.job.JobInput;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.notification.INotificationHandler;

/**
 * <h3>{@link ClusterMessageNotificationHandler}</h3>
 *
 * @author mlu
 */
public class ClusterMessageNotificationHandler implements INotificationHandler<ClusterMessage> {

	private IClientSession session;
	
	public void putSessionContext(IClientSession session) {
		this.session = session;
	}
	
	@Override
	public void handleNotification(final ClusterMessage notification) {
		System.out.println(notification.getStatus().getMessage());
		ClientRunContext copyCurrent = ClientRunContexts.copyCurrent();
		if (copyCurrent.getSession() == null) {
			copyCurrent.withSession(session, true);
		}
		JobInput newInput = ModelJobs.newInput(copyCurrent);
		//System.out.println(notification.getStatus().getMessage());
		ModelJobs.schedule(new Callable<ClusterMessage>() {

			@Override
			public ClusterMessage call() throws Exception {
				updateForm(notification);
				return notification;
			}

		}, newInput);
	}

	private void updateForm(ClusterMessage notification) {
		if (notification == null) {
			return;
		}
		ConversationForm f = findOrCreateForm();
		if (f != null) {
			String currentValue = StringUtility.nvl(f.getConversationField().getValue(), "");
			StringBuffer b = new StringBuffer(currentValue);
			b.append(notification.getStatus().getMessage());
			b.append("\r\n");
			f.getConversationField().setValue(b.toString());
		}
	}

	private ConversationForm findOrCreateForm() {
		IDesktop desktop = ClientSession.get().getDesktop();
		if (desktop != null && desktop.isOpened()) {
			ConversationForm form = desktop.findForm(ConversationForm.class);
			if (form != null) {
				return form;
			}
		}
		return null;
	}
}
