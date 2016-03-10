package org.eclipse.scout.myapp.shared.helloworld.color;

import java.io.Serializable;

import org.eclipse.scout.rt.platform.status.IStatus;
import org.eclipse.scout.rt.platform.status.Status;

public class TableFetchInfoMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private Status status;
	
	public TableFetchInfoMessage(String infoText) {
		status = new Status(infoText, IStatus.WARNING);
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}


}
