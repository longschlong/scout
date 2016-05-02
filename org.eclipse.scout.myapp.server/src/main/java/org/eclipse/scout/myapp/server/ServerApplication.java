package org.eclipse.scout.myapp.server;

import java.util.concurrent.Callable;

import javax.security.auth.Subject;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.IPlatform;
import org.eclipse.scout.rt.platform.IPlatformListener;
import org.eclipse.scout.rt.platform.PlatformEvent;
import org.eclipse.scout.rt.platform.context.CorrelationId;
import org.eclipse.scout.rt.platform.context.RunMonitor;
import org.eclipse.scout.rt.platform.security.SimplePrincipal;
import org.eclipse.scout.rt.platform.util.FinalValue;
import org.eclipse.scout.rt.platform.util.concurrent.IRunnable;
import org.eclipse.scout.rt.server.IServerSession;
import org.eclipse.scout.rt.server.context.ServerRunContext;
import org.eclipse.scout.rt.server.context.ServerRunContexts;
import org.eclipse.scout.rt.server.services.common.clustersync.IClusterSynchronizationService;
import org.eclipse.scout.rt.server.session.ServerSessionProvider;
import org.eclipse.scout.rt.shared.ui.UserAgent;

public class ServerApplication implements IPlatformListener {

	private final FinalValue<Subject> m_subject = new FinalValue<>();
	private final FinalValue<IServerSession> m_session = new FinalValue<>();

	@Override
	public void stateChanged(PlatformEvent event) {
		if (event.getState() == IPlatform.State.PlatformStarted) {
			initClusterSync();
		} else if (event.getState() == IPlatform.State.PlatformStopping) {
		} else if (event.getState() == IPlatform.State.PlatformInvalid) {
		}
	}

	protected void initClusterSync() {
		final Subject subject = new Subject();
		subject.getPrincipals().add(new SimplePrincipal("systen"));
		subject.setReadOnly();
		m_subject.set(subject);

		createSuperUserContext().run(new IRunnable() {
			@Override
			public void run() throws Exception {
				IClusterSynchronizationService clusterSyncService = BEANS.get(IClusterSynchronizationService.class);
				clusterSyncService.enable();
			}
		});
	}

	@SuppressWarnings("deprecation")
	public ServerRunContext createSuperUserContext() {
		String cid = CorrelationId.CURRENT.get();
		final ServerRunContext superUserRunContext = ServerRunContexts.empty()
				.withRunMonitor(BEANS.get(RunMonitor.class)).withUserAgent(UserAgent.createDefault())
				.withSubject(m_subject.get())
				.withCorrelationId(cid != null ? cid : BEANS.get(CorrelationId.class).newCorrelationId());

		return superUserRunContext.withSession(m_session.setIfAbsent(new Callable<IServerSession>() {

			@Override
			public IServerSession call() throws Exception {
				return BEANS.get(ServerSessionProvider.class).provide(superUserRunContext.copy());
			}
		}));
	}

}
