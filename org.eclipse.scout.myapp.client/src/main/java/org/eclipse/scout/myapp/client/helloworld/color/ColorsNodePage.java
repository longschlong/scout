package org.eclipse.scout.myapp.client.helloworld.color;

import java.util.List;

import org.eclipse.scout.myapp.shared.helloworld.color.ColorCodeType.AbstractColorCode;
import org.eclipse.scout.myapp.shared.helloworld.color.IColorPageService;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.shared.TEXTS;

/**
 * <h3>{@link ColorsNodePage}</h3>
 *
 * @author mlu
 */
public class ColorsNodePage extends AbstractPageWithNodes {

	@Override
	protected boolean getConfiguredLeaf() {
		return true;
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("Colors");
	}

	
	@Override
	protected void execCreateChildPages(List<IPage<?>> pageList) {
		super.execCreateChildPages(pageList);
		List<? extends AbstractColorCode> loadBaseColors = BEANS.get(IColorPageService.class).loadBaseColors();
		for (AbstractColorCode c : loadBaseColors) {
			pageList.add(new BaseColorTablePage(c));
		}
	}
}
