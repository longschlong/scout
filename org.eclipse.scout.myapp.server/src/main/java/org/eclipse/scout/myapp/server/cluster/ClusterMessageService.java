package org.eclipse.scout.myapp.server.cluster;

import java.util.Collections;

import org.eclipse.scout.myapp.shared.cluster.ClusterMessage;
import org.eclipse.scout.myapp.shared.cluster.IClusterMessageService;
import org.eclipse.scout.rt.platform.ApplicationScoped;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.context.CorrelationId;
import org.eclipse.scout.rt.server.clientnotification.ClientNotificationRegistry;
import org.eclipse.scout.rt.server.services.common.clustersync.ClusterSynchronizationService;
import org.eclipse.scout.rt.shared.clientnotification.ClientNotificationAddress;
import org.eclipse.scout.rt.shared.clientnotification.ClientNotificationMessage;

@ApplicationScoped
public class ClusterMessageService implements IClusterMessageService {

	@Override
	public void sendMessage(ClusterMessage message) {
		if (message == null) {
			message = new ClusterMessage("Ping... Hello other cluster nodes...");
		}
		BEANS.get(ClientNotificationRegistry.class).putForAllNodes(message, true);
		//publish(message);
	}

	protected void publish(ClusterMessage message) {
		// Don't send message to own node!
		String ownNodeId = BEANS.get(ClusterSynchronizationService.class).getNodeId();
		BEANS.get(ClientNotificationRegistry.class).publish(
				Collections.singleton(new ClientNotificationMessage(ClientNotificationAddress.createAllNodesAddress(), message, true, CorrelationId.CURRENT.get())), ownNodeId);
	}
}
