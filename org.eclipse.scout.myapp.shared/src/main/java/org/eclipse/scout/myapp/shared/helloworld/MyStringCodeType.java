package org.eclipse.scout.myapp.shared.helloworld;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCode;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCodeType;

public class MyStringCodeType extends AbstractCodeType<String, String> {

	private static final long serialVersionUID = 6808664924551155395L;

	public static final String ID = null;

	@Override
	public String getId() {
		return ID;
	}

	@Order(10.0)
	public static class UnknownCode extends AbstractCode<String> {

		private static final long serialVersionUID = -1307260056726644943L;

		public static final String ID = "Unknown";

		@Override
		protected String getConfiguredText() {
			return TEXTS.get("Unknown");
		}

		@Override
		public String getId() {
			return ID;
		}
	}
}
