package org.eclipse.scout.myapp.shared.helloworld;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

import org.eclipse.scout.myapp.shared.helloworld.HelloWorldFormData;

/**
 * <h3>{@link IHelloWorldFormService}</h3>
 *
 * @author mlu
 */
@TunnelToServer
public interface IHelloWorldFormService extends IService {
      HelloWorldFormData load(HelloWorldFormData input);
}
