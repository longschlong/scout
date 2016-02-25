package org.eclipse.scout.myapp.client.work;

import java.util.List;

import org.eclipse.scout.myapp.client.helloworld.HelloWorldPage;
import org.eclipse.scout.myapp.shared.Icons;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.TEXTS;

/**
 * <h3>{@link WorkOutline}</h3>
 *
 * @author mlu
 */
@Order(1000)
public class WorkOutline extends AbstractOutline {

  @Override
  protected void execCreateChildPages(final List<IPage<?>> pageList) {
	final HelloWorldPage helloWorldPage = new HelloWorldPage();
	pageList.add(helloWorldPage);
    super.execCreateChildPages(pageList);
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Work");
  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.Pencil;
  }
}
