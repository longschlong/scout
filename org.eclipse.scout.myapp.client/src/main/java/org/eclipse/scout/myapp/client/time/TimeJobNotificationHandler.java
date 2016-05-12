package org.eclipse.scout.myapp.client.time;

import java.util.concurrent.Callable;

import org.eclipse.scout.myapp.client.ClientSession;
import org.eclipse.scout.myapp.shared.time.TimeJobInfoMessage;
import org.eclipse.scout.rt.client.context.ClientRunContexts;
import org.eclipse.scout.rt.client.job.ModelJobs;
import org.eclipse.scout.rt.client.session.ClientSessionProvider;
import org.eclipse.scout.rt.client.ui.basic.table.ITable;
import org.eclipse.scout.rt.client.ui.desktop.notification.IDesktopNotification;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.status.IStatus;
import org.eclipse.scout.rt.shared.notification.INotificationHandler;

/**
 * <h3>{@link TimeJobNotificationHandler}</h3>
 *
 * @author mlu
 */
public class TimeJobNotificationHandler implements INotificationHandler<TimeJobInfoMessage> {

  @Override
  public void handleNotification(final TimeJobInfoMessage notification) {
	  ModelJobs.schedule(new Callable<TimeJobInfoMessage>() {

			@Override
			public TimeJobInfoMessage call() throws Exception {
				IPage<?> activePage = ClientSession.get().getDesktop().getOutline().getActivePage();
				if (activePage != null && activePage instanceof AbstractPageWithTable<?>) {
					ITable table = activePage.getTable();
					if (table != null) {						
						table.setTableStatus(notification.getStatus());
					}
				}
				
				// Global notification
				ClientSessionProvider.currentSession().getDesktop().addNotification(new IDesktopNotification() {
					
					@Override
					public boolean isCloseable() {
						return false;
					}
					
					@Override
					public IStatus getStatus() {
						return notification.getStatus();
					}
					
					@Override
					public long getDuration() {
						return 4000;
					}
				});
				
				return notification;
			}
			
		}, ModelJobs.newInput(ClientRunContexts.copyCurrent()));
  }

}
