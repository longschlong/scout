package org.eclipse.scout.myapp.client.permission;

import java.security.Permission;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.util.Enumeration;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.services.common.security.AbstractAccessControlService;
import org.eclipse.scout.rt.shared.services.common.security.IAccessControlService;
import org.eclipse.scout.rt.shared.servicetunnel.ServiceTunnelUtility;

/**
 * <h3>{@link AccessControlServiceProxy}</h3>
 *
 * @author mlu
 */
@Order(1)
public class AccessControlServiceProxy extends AbstractAccessControlService<String> {
	@Override
	protected Permissions execLoadPermissions(String userId) {
		PermissionCollection permissions = ServiceTunnelUtility.createProxy(IAccessControlService.class)
				.getPermissions();
		Permissions p = new Permissions();
		Enumeration<Permission> elements = permissions.elements();
		while (elements.hasMoreElements()) {
			Permission permission = (Permission) elements.nextElement();
			p.add(permission);
		}
		return p;
	}

	@Override
	protected String getCurrentUserCacheKey() {
		return getUserIdOfCurrentUser();
	}

	@Override
	public boolean checkPermission(Permission p) {
		return ServiceTunnelUtility.createProxy(IAccessControlService.class).checkPermission(p);
	}
}
