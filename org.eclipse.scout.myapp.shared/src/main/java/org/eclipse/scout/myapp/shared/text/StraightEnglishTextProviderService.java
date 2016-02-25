package org.eclipse.scout.myapp.shared.text;

import java.util.Locale;
import java.util.Map;

import org.eclipse.scout.rt.platform.Order;

@Order(2000)
public class StraightEnglishTextProviderService extends DefaultTextProviderService {
	  
	  @Override
	  public String getText(Locale locale, String key, String... messageArguments) {
	    return instance.getText(Locale.ENGLISH, key, messageArguments);
	  }

	  @Override
	  public Map<String, String> getTextMap(Locale locale) {
	    return instance.getTextMap(Locale.ENGLISH);
	  }
	  
}
