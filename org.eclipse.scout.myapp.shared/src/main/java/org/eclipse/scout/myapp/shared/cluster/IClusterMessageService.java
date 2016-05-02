package org.eclipse.scout.myapp.shared.cluster;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IClusterMessageService extends IService {

	void sendMessage(ClusterMessage message);
	
}
