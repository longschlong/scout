package org.eclipse.scout.myapp.server.cluster;

import org.eclipse.scout.myapp.shared.cluster.ClusterMessage;
import org.eclipse.scout.myapp.shared.cluster.IClusterMessageDistributionService;
import org.eclipse.scout.rt.platform.ApplicationScoped;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.server.services.common.clustersync.ClusterSynchronizationService;
import org.eclipse.scout.rt.server.session.ServerSessionProvider;

@ApplicationScoped
public class ClusterMessageDistributionService implements IClusterMessageDistributionService {

	@Override
	public void sendMessage(ClusterMessage message) {
		if (message == null) {
			message = new ClusterMessage(ServerSessionProvider.currentSession().getUserId() + ": " + "Ping... Hello other nodes...");
		}
		publish(message);
	}

	@Override
	public void clear() {
		publish(new ClusterMessage(true));
	}

	protected void publish(ClusterMessage message) {
		BEANS.get(ClusterSynchronizationService.class).publish(message);
	}
}
