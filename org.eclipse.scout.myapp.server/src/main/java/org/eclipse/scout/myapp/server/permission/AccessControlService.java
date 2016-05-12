package org.eclipse.scout.myapp.server.permission;

import java.security.AllPermission;
import java.security.Permissions;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.security.RemoteServiceAccessPermission;
import org.eclipse.scout.rt.shared.services.common.security.AbstractAccessControlService;

/**
 * <h3>{@link AccessControlService}</h3>
 *
 * @author mlu
 */
@Order(-1)
public class AccessControlService extends AbstractAccessControlService<String> {
  @Override
  protected Permissions execLoadPermissions(String userId) {
    Permissions permissions = new Permissions();
    permissions.add(new RemoteServiceAccessPermission("*.shared.*", "*"));

    permissions.add(new AllPermission());
    return permissions;
  }

	@Override
	protected String getCurrentUserCacheKey() {
		return getUserIdOfCurrentUser();
	}
}
