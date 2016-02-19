package org.eclipse.scout.myapp.client.helloworld.color;

import java.util.List;

import org.eclipse.scout.myapp.shared.Icons;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.TEXTS;

/**
 * <h3>{@link ColorOutline}</h3>
 *
 * @author mlu
 */
@Order(1000)
public class ColorOutline extends AbstractOutline {

  @Override
  protected void execCreateChildPages(List<IPage<?>> pageList) {
	super.execCreateChildPages(pageList);
	pageList.add(new ColorsNodePage());
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Colors");
  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.Pencil;
  }
}
