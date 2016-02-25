package org.eclipse.scout.myapp.server.time;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.eclipse.scout.myapp.shared.time.ITimeService;
import org.eclipse.scout.myapp.shared.time.TimeJobInfoMessage;
import org.eclipse.scout.rt.platform.ApplicationScoped;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.filter.IFilter;
import org.eclipse.scout.rt.platform.job.IFuture;
import org.eclipse.scout.rt.platform.job.Jobs;
import org.eclipse.scout.rt.platform.util.concurrent.IRunnable;
import org.eclipse.scout.rt.server.clientnotification.ClientNotificationRegistry;
import org.quartz.SimpleScheduleBuilder;

@ApplicationScoped
public class TimeService implements ITimeService {

	private static final UpdateJob m_updateJob = new UpdateJob();
	
	public void start() {
		Jobs.schedule(
				m_updateJob
				,Jobs.newInput().withName("Timer").withExecutionTrigger(Jobs.newExecutionTrigger()
						.withStartIn(0, TimeUnit.SECONDS).withSchedule(SimpleScheduleBuilder.repeatSecondlyForever()))
				);
		
		BEANS.get(ClientNotificationRegistry.class).putForAllSessions(new TimeJobInfoMessage("Time client notification (job) started"));
	}

	@Override
	public boolean stop() {
		boolean cancel = Jobs.getJobManager().cancel(new IFilter<IFuture<?>>() {
			@Override
			public boolean accept(IFuture<?> element) {
				return "Timer".equals(element.getJobInput().getName());
			}
		}, true);
		
		BEANS.get(ClientNotificationRegistry.class).putForAllSessions(new TimeJobInfoMessage("Time client notification (job) stopped"));
		return cancel;
	}

	private static class UpdateJob implements IRunnable {
		@Override
		public void run() throws Exception {
			BEANS.get(ClientNotificationRegistry.class).putForAllSessions(new Date());
		}
	}

}
