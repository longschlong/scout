package org.eclipse.scout.myapp.server.helloworld.color;

import java.awt.Color;
import java.util.List;

import org.eclipse.scout.myapp.shared.helloworld.color.BaseColorTablePageData;
import org.eclipse.scout.myapp.shared.helloworld.color.BaseColorTablePageData.BaseColorTableRowData;
import org.eclipse.scout.myapp.shared.helloworld.color.ColorCodeType;
import org.eclipse.scout.myapp.shared.helloworld.color.ColorCodeType.AbstractColorCode;
import org.eclipse.scout.myapp.shared.helloworld.color.ColorCodeType.IColorCode;
import org.eclipse.scout.myapp.shared.helloworld.color.ColorSearchFormData;
import org.eclipse.scout.myapp.shared.helloworld.color.IColorPageService;
import org.eclipse.scout.myapp.shared.helloworld.color.OperatorCodeType;
import org.eclipse.scout.myapp.shared.helloworld.color.TableFetchInfoMessage;
import org.eclipse.scout.myapp.shared.helloworld.color.OperatorCodeType.AbstractOperatorCode;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.server.clientnotification.ClientNotificationRegistry;

/**
 * <h3>{@link ColorPageService}</h3>
 *
 * @author mlu
 */
public class ColorPageService implements IColorPageService {
	
	@Override
	public BaseColorTablePageData loadSubColors(ColorSearchFormData formData) {
		return loadSubColorsInternal(formData);
	}

	@Override
	public List<? extends AbstractColorCode> loadBaseColors() {
		List<? extends AbstractColorCode> codes = BEANS.get(ColorCodeType.class).getCodes();
		return codes;
	}

	protected BaseColorTablePageData loadSubColorsInternal(ColorSearchFormData formData) {
		BaseColorTablePageData pageData = new BaseColorTablePageData();
		if (formData == null) {
			return pageData;
		}
		
		ColorSearchFormData.Color color = formData.getColor();
		if (color == null) {
			return pageData;
		}

		mixColorRow(pageData, formData);
		
		if (pageData.getRows().length > 200) {
			// XXX
			//BEANS.get(ClientNotificationRegistry.class).putForUser("mlu", new TableFetchInfoMessage("More than " + 200 + " rows loaded."));
		}
		
		return pageData;
	}
	
	protected void mixColorRow(BaseColorTablePageData pageData, ColorSearchFormData formData) {
		for (int i = 0; i <= 255; i++) {
			Color colorValue = formData.getColor().getValue();
			AbstractColorCode code = BEANS.get(ColorCodeType.class).getCode(colorValue);
			if (code instanceof IColorCode) {
				IColorCode iCode = (IColorCode) code;
				
				Color colorVariaton = iCode.getColorVariaton(i);
				if (match(colorVariaton, formData)) {
					BaseColorTableRowData addRow = pageData.addRow();
					addRow.setId(colorVariaton);
					addRow.setColor(colorVariaton);
					addRow.setRedValue(colorVariaton.getRed());
					addRow.setGreenValue(colorVariaton.getGreen());
					addRow.setBlueValue(colorVariaton.getBlue());
				}
			}
		}
		
	}
	
	protected boolean match(Color colorVariaton, ColorSearchFormData formData) {
		boolean match = true;
		
		String redOperator = formData.getRedOperator().getValue();
		String greenOperator = formData.getGreenOperator().getValue();
		String blueOperator = formData.getBlueOperator().getValue();

		if (!StringUtility.hasText(redOperator) && !StringUtility.hasText(greenOperator) && !StringUtility.hasText(blueOperator)) {
			return true;
		}
		
		if (StringUtility.hasText(redOperator)) {
			AbstractOperatorCode code = BEANS.get(OperatorCodeType.class).getCode(redOperator);
			match = match && code.applies(colorVariaton.getRed(), formData.getRedValue().getValue().intValue());
		}
		
		if (StringUtility.hasText(greenOperator)) {
			AbstractOperatorCode code = BEANS.get(OperatorCodeType.class).getCode(greenOperator);
			match = match && code.applies(colorVariaton.getGreen(), formData.getGreenValue().getValue().intValue());
		}
		
		if (StringUtility.hasText(blueOperator)) {
			AbstractOperatorCode code = BEANS.get(OperatorCodeType.class).getCode(blueOperator);
			match = match && code.applies(colorVariaton.getBlue(), formData.getBlueValue().getValue().intValue());
		}
		
		return match;
	}
	
}
