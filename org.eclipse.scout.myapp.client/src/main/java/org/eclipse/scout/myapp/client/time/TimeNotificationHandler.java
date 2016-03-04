package org.eclipse.scout.myapp.client.time;

import java.util.Date;
import java.util.concurrent.Callable;

import org.eclipse.scout.myapp.client.ClientSession;
import org.eclipse.scout.rt.client.context.ClientRunContexts;
import org.eclipse.scout.rt.client.job.ModelJobs;
import org.eclipse.scout.rt.client.ui.desktop.IDesktop;
import org.eclipse.scout.rt.platform.util.date.DateUtility;
import org.eclipse.scout.rt.shared.notification.INotificationHandler;

/**
 * <h3>{@link TimeNotificationHandler}</h3>
 *
 * @author mlu
 */
public class TimeNotificationHandler implements INotificationHandler<Date> {

  @Override
  public void handleNotification(final Date notification) {
    ModelJobs.schedule(new Callable<Date>() {

		@Override
		public Date call() throws Exception {
			updateForm(notification);
			return notification;
		}
		
	}, ModelJobs.newInput(ClientRunContexts.copyCurrent()));
  }

  private void updateForm(Date notification) {
    TimeForm f = findOrCreateForm();
    if (f != null) {
      f.getTimeField().setValue(DateUtility.format(notification, "YYYY-MM-dd HH:mm:ss"));
    }
  }

  private TimeForm findOrCreateForm() {
    IDesktop desktop = ClientSession.get().getDesktop();
    if (desktop != null && desktop.isOpened()) {
    	TimeForm form = desktop.findForm(TimeForm.class);
      if (form != null) {
        return form;
      }
    }
    return null;
  }

}
