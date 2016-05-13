package org.eclipse.scout.myapp.client.helloworld.color;

import org.eclipse.scout.myapp.shared.helloworld.color.ColorCodeType.AbstractColorCode;

/**
 * <h3>{@link InlineBaseColorTablePage}</h3>
 *
 * @author mlu
 */
public class InlineBaseColorTablePage extends BaseColorTablePage {

	public InlineBaseColorTablePage(AbstractColorCode colorCode) {
		super(colorCode);
		execLoadData(getSearchFilter());
	}
}
