package org.eclipse.scout.myapp.server.cluster;

import org.eclipse.scout.myapp.shared.cluster.ClusterMessage;
import org.eclipse.scout.myapp.shared.cluster.IClusterMessageDistributionService;
import org.eclipse.scout.myapp.shared.cluster.ServerMessage;
import org.eclipse.scout.rt.platform.ApplicationScoped;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.server.clientnotification.ClientNotificationRegistry;
import org.eclipse.scout.rt.server.services.common.clustersync.ClusterSynchronizationService;

@ApplicationScoped
public class ClusterMessageDistributionService implements IClusterMessageDistributionService {

	@Override
	public void sendMessage(ClusterMessage message) {
		if (message == null) {
			return;
		}
		publishToClients(message);
		notifyServers();
	}

	private void notifyServers() {
		BEANS.get(ClusterSynchronizationService.class).publish(new ServerMessage("Chat message from " + BEANS.get(ClusterSynchronizationService.class).getNodeId()));	// Send only to (other) server nodes!
	}

	@Override
	public void clear() {
		publishToClients(new ClusterMessage(true));
	}

	protected void publishToClients(ClusterMessage message) {
		BEANS.get(ClientNotificationRegistry.class).putForAllSessions(message /*, true*//*distributeOverCluster*/);	// Client notifications are sent over cluster sync
	}
}
