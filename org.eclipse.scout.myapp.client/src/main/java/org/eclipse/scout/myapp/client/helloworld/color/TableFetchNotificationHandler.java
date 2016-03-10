package org.eclipse.scout.myapp.client.helloworld.color;

import java.util.concurrent.Callable;

import org.eclipse.scout.myapp.client.ClientSession;
import org.eclipse.scout.myapp.client.time.TimeJobNotificationHandler;
import org.eclipse.scout.myapp.shared.helloworld.color.TableFetchInfoMessage;
import org.eclipse.scout.rt.client.context.ClientRunContexts;
import org.eclipse.scout.rt.client.job.ModelJobs;
import org.eclipse.scout.rt.client.ui.basic.table.ITable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.shared.notification.INotificationHandler;

/**
 * <h3>{@link TimeJobNotificationHandler}</h3>
 *
 * @author mlu
 */
public class TableFetchNotificationHandler implements INotificationHandler<TableFetchInfoMessage> {

  @Override
  public void handleNotification(final TableFetchInfoMessage notification) {
	  ModelJobs.schedule(new Callable<TableFetchInfoMessage>() {

			@Override
			public TableFetchInfoMessage call() throws Exception {
				IPage<?> activePage = ClientSession.get().getDesktop().getOutline().getActivePage();
				if (activePage != null && activePage instanceof AbstractPageWithTable<?>) {
					ITable table = activePage.getTable();
					if (table != null) {						
						table.setTableStatus(notification.getStatus());
					}
				}
				return notification;
			}
			
		}, ModelJobs.newInput(ClientRunContexts.copyCurrent()));
  }

}
