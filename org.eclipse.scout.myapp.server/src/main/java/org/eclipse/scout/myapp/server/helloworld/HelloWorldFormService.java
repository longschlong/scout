package org.eclipse.scout.myapp.server.helloworld;

import org.eclipse.scout.myapp.server.ServerSession;
import org.eclipse.scout.myapp.shared.helloworld.HelloWorldFormData;
import org.eclipse.scout.myapp.shared.helloworld.IHelloWorldFormService;

/**
 * <h3>{@link HelloWorldFormService}</h3>
 *
 * @author mlu
 */
public class HelloWorldFormService implements IHelloWorldFormService {

  @Override
  public HelloWorldFormData load(HelloWorldFormData input) {
    StringBuilder msg = new StringBuilder();
    msg.append("Hello ").append(ServerSession.get().getUserId()).append("!");
    input.getMessage().setValue(msg.toString());
    return input;
  }
}
