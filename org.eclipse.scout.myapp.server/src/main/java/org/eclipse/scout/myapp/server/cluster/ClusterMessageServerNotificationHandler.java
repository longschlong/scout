package org.eclipse.scout.myapp.server.cluster;

import org.eclipse.scout.myapp.shared.cluster.ServerMessage;
import org.eclipse.scout.rt.shared.notification.INotificationHandler;

/**
 * <h3>{@link ClusterMessageServerNotificationHandler}</h3>
 *
 * @author mlu
 */
public class ClusterMessageServerNotificationHandler implements INotificationHandler<ServerMessage> {

	@Override
	public void handleNotification(final ServerMessage notification) {
		System.err.println(notification.getStatus().getMessage());
	}

}
