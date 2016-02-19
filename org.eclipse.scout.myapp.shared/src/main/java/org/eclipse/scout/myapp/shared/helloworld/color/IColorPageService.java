package org.eclipse.scout.myapp.shared.helloworld.color;

import java.util.List;

import org.eclipse.scout.myapp.shared.helloworld.color.ColorCodeType.AbstractColorCode;
import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

/**
 * <h3>{@link IColorPageService}</h3>
 *
 * @author mlu
 */
@TunnelToServer
public interface IColorPageService extends IService {

	List<? extends AbstractColorCode> loadBaseColors();
	
	BaseColorTablePageData loadSubColors(ColorSearchFormData formData);
	
}
