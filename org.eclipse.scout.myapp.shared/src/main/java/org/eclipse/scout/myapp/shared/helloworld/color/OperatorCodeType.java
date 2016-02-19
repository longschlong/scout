package org.eclipse.scout.myapp.shared.helloworld.color;

import java.util.List;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.util.CompareUtility;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCode;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCodeType;

public class OperatorCodeType extends AbstractCodeType<String, String> {

	private static final long serialVersionUID = 1L;

	private static final String ID = "anotheruniquecodetypeid";
	
	@Override
	public String getId() {
		return ID;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<? extends AbstractOperatorCode> getCodes() {
		return (List<? extends AbstractOperatorCode>) super.getCodes();
	}
	
	@Override
	public AbstractOperatorCode getCode(String id) {
		return (AbstractOperatorCode) super.getCode(id);
	}
	
	@Order(1000)
	public static class EqualsCode extends AbstractOperatorCode {
		private static final long serialVersionUID = 1L;
		public static final String ID = "equals";

		@Override
		protected String getConfiguredText() {
			return "=";
		}

		@Override
		public String getId() {
			return ID;
		}
		
		@Override
		public boolean applies(int i1, int i2) {
			return CompareUtility.compareTo(i1, i2) == 0;
		}
	}

	@Order(2000)
	public static class GreaterThenCode extends AbstractOperatorCode {
		private static final long serialVersionUID = 1L;
		public static final String ID = "greather then";

		@Override
		protected String getConfiguredText() {
			return ">";
		}

		@Override
		public String getId() {
			return ID;
		}
		
		@Override
		public boolean applies(int i1, int i2) {
			return CompareUtility.compareTo(i1, i2) > 0;
		}
	}

	@Order(3000)
	public static class LessThenCode extends AbstractOperatorCode {
		private static final long serialVersionUID = 1L;
		public static final String ID = "less then";

		@Override
		protected String getConfiguredText() {
			return "<";
		}

		@Override
		public String getId() {
			return ID;
		}

		@Override
		public boolean applies(int i1, int i2) {
			return CompareUtility.compareTo(i1, i2) < 0;
		}
		
	}
	
	public interface IOperatorCode {
		
		boolean applies(int i1, int i2);
		
	}

	public static abstract class AbstractOperatorCode extends AbstractCode<String> implements IOperatorCode {

		private static final long serialVersionUID = 1L;
		
	}
}
