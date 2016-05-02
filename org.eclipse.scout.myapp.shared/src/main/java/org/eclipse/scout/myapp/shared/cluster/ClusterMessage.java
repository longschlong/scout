package org.eclipse.scout.myapp.shared.cluster;

import java.io.Serializable;

import org.eclipse.scout.rt.platform.status.IStatus;
import org.eclipse.scout.rt.platform.status.Status;

public class ClusterMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private Status status;
	
	public ClusterMessage(String infoText) {
		status = new Status(infoText, IStatus.INFO);
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}


}
