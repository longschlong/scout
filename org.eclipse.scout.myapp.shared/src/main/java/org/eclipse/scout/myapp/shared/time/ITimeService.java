package org.eclipse.scout.myapp.shared.time;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface ITimeService extends IService {

	void start();
	
	boolean stop();
	
}
