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

	@Order(20.0)
	public static class BlueCode extends AbstractCode<String> {
		
		private static final long serialVersionUID = 2584415366784937361L;
		
		public static final String ID = "Blue";
		
		@Override
		protected String getConfiguredText() {
			return "Blue";
		}
		
		@Override
		public String getId() {
			return ID;
		}
	}
	
	@Order(30.0)
	public static class BlackCode extends AbstractCode<String> {
		
		private static final long serialVersionUID = 671381503916985066L;
		
		public static final String ID = "Black";
		
		@Override
		protected String getConfiguredText() {
			return "Black";
		}
		
		@Override
		public String getId() {
			return ID;
		}
	}
	
	@Order(30.0)
	public static class GreenCode extends AbstractCode<String> {
		
		private static final long serialVersionUID = -2911995505164111572L;
		
		public static final String ID = "Green";
		
		@Override
		protected String getConfiguredText() {
			return "Green";
		}
		
		@Override
		public String getId() {
			return ID;
		}
	}
}
