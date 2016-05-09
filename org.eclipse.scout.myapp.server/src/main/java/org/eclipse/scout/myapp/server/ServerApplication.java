package org.eclipse.scout.myapp.server;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.IPlatform;
import org.eclipse.scout.rt.platform.IPlatformListener;
import org.eclipse.scout.rt.platform.PlatformEvent;
import org.eclipse.scout.rt.server.services.common.clustersync.IClusterSynchronizationService;

public class ServerApplication implements IPlatformListener {

	@Override
	public void stateChanged(PlatformEvent event) {
		if (event.getState() == IPlatform.State.PlatformStarted) {
			IClusterSynchronizationService clusterSyncService = BEANS.get(IClusterSynchronizationService.class);
			if (clusterSyncService != null) {				
				clusterSyncService.enable();
			}
		} else if (event.getState() == IPlatform.State.PlatformStopping) {
			IClusterSynchronizationService clusterSyncService = BEANS.get(IClusterSynchronizationService.class);
			if (clusterSyncService != null) {				
				clusterSyncService.disable();
			}
		} else if (event.getState() == IPlatform.State.PlatformInvalid) {
		}
	}

}
