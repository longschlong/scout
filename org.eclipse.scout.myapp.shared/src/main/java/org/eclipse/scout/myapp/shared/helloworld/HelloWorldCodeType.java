package org.eclipse.scout.myapp.shared.helloworld;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCode;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCodeType;

public class HelloWorldCodeType extends AbstractCodeType<Long, Long> {

	private static final long serialVersionUID = 1L;

	public static final Long ID = 1L;

	@Override
	public Long getId() {
		return ID;
	}

	@Order(10.0)
	public static class UnknownCode extends AbstractCode<Long> {

		private static final long serialVersionUID = 1L;

		public static final Long ID = 2L;

		@Override
		protected String getConfiguredText() {
			return TEXTS.get("Unknown");
		}

		@Override
		public Long getId() {
			return ID;
		}
	}

}
