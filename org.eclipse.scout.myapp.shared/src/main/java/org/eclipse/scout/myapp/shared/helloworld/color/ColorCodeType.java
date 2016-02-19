package org.eclipse.scout.myapp.shared.helloworld.color;

import java.awt.Color;
import java.util.List;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCode;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCodeType;

public class ColorCodeType extends AbstractCodeType<String, Color> {

	private static final long serialVersionUID = 1L;

	private static final String ID = "oneuniquecodetypeid";
	
	@Override
	public String getId() {
		return ID;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<? extends AbstractColorCode> getCodes() {
		return (List<? extends AbstractColorCode>) super.getCodes();
	}
	
	@Override
	public AbstractColorCode getCode(Color id) {
		return (AbstractColorCode) super.getCode(id);
	}
	
	@Order(1000)
	public static class RedCode extends AbstractColorCode {
		private static final long serialVersionUID = 1L;
		public static final Color ID = Color.RED;

		@Override
		protected String getConfiguredText() {
			return "Red";
		}

		@Override
		public Color getId() {
			return ID;
		}

		@Override
		public Color getColorVariaton(int offset) {
			return new Color(ID.getRed(), ID.getGreen() + offset, ID.getBlue() + offset);
		}
	}

	@Order(2000)
	public static class GreenCode extends AbstractColorCode {
		private static final long serialVersionUID = 1L;
		public static final Color ID = Color.GREEN;

		@Override
		protected String getConfiguredText() {
			return "Green";
		}

		@Override
		public Color getId() {
			return ID;
		}
		
		@Override
		public Color getColorVariaton(int offset)  {
			return new Color(ID.getRed() + offset, ID.getGreen(), ID.getBlue() + offset);
		}
	}

	@Order(3000)
	public static class BlueCode extends AbstractColorCode {
		private static final long serialVersionUID = 1L;
		public static final Color ID = Color.BLUE;

		@Override
		protected String getConfiguredText() {
			return "Blue";
		}

		@Override
		public Color getId() {
			return ID;
		}
		
		@Override
		public Color getColorVariaton(int offset)  {
			return new Color(ID.getRed() + offset, ID.getGreen() + offset, ID.getBlue());
		}
	}
	
	public interface IColorCode {
		Color getColorVariaton(int offset);
		Color getId();
	}

	public static abstract class AbstractColorCode extends AbstractCode<Color> implements IColorCode {

		private static final long serialVersionUID = 1L;
		
	}
}
