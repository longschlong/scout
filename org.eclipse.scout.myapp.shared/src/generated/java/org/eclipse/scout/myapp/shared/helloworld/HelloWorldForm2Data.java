
package org.eclipse.scout.myapp.shared.helloworld;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications
 * recommended.
 */
@Generated(value = "org.eclipse.scout.myapp.client.helloworld.HelloWorldForm", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class HelloWorldForm2Data extends AbstractFormData {

  private static final long serialVersionUID = 1L;

  public HelloWorldForm2Data() {
  }

  public Message getMessage() {
    return getFieldByClass(Message.class);
  }

  public static class Message extends AbstractValueFieldData<String> {

    private static final long serialVersionUID = 1L;

    public Message() {
    }
  }
}
