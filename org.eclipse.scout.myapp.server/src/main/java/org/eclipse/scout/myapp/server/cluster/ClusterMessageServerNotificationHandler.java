package org.eclipse.scout.myapp.server.cluster;

import org.eclipse.scout.myapp.shared.cluster.ClusterMessage;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.server.clientnotification.ClientNotificationRegistry;
import org.eclipse.scout.rt.shared.notification.INotificationHandler;

public class ClusterMessageServerNotificationHandler implements INotificationHandler<ClusterMessage> {

	@Override
	public void handleNotification(ClusterMessage notification) {
		// Send back client notifications
		BEANS.get(ClientNotificationRegistry.class).putForAllNodes(notification, true);
	}
}
