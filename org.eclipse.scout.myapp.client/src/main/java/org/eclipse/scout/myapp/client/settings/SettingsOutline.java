package org.eclipse.scout.myapp.client.settings;

import org.eclipse.scout.myapp.shared.Icons;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.TEXTS;

/**
 * <h3>{@link SettingsOutline}</h3>
 *
 * @author mlu
 */
@Order(3000)
public class SettingsOutline extends AbstractOutline {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Settings");
  }
  
  @Override
  protected String getConfiguredIconId() {
    return Icons.Gear;
  }
}
