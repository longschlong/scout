package org.eclipse.scout.myapp.client.helloworld;

import org.eclipse.scout.myapp.client.helloworld.HelloWorldForm2.MainBox.TopBox;
import org.eclipse.scout.myapp.client.helloworld.HelloWorldForm2.MainBox.TopBox.Form1;
import org.eclipse.scout.myapp.client.helloworld.HelloWorldForm2.MainBox.TopBox.Form2;
import org.eclipse.scout.myapp.shared.helloworld.HelloWorldForm2Data;
import org.eclipse.scout.myapp.shared.helloworld.HelloWorldFormData;
import org.eclipse.scout.myapp.shared.helloworld.IHelloWorldFormService;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.wrappedform.AbstractWrappedFormField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.shared.AbstractIcons;

/**
 * <h3>{@link HelloWorldForm2}</h3>
 *
 * @author mlu
 */
@FormData(value = HelloWorldForm2Data.class, sdkCommand = FormData.SdkCommand.CREATE)
public class HelloWorldForm2 extends AbstractForm {
 
  public HelloWorldForm2() {
    super();
    setHandler(new ViewHandler());
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected int getConfiguredModalityHint() {
    return MODALITY_HINT_MODELESS;
  }

  @Override
  protected String getConfiguredIconId() {
    return AbstractIcons.World;
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public TopBox getTopBox() {
    return getFieldByClass(TopBox.class);
  }

  public Form1 getForm1() {
    return getFieldByClass(Form1.class);
  }
  
  public Form2 getForm2() {
	  return getFieldByClass(Form2.class);
  }

	@Order(1000.0)
	public class MainBox extends AbstractGroupBox {

		@Order(1000.0)
		public class TopBox extends AbstractGroupBox {

			@Override
			protected int getConfiguredGridColumnCount() {
				return 1;
			}

			@Order(1000.0)
			public class Form1 extends AbstractWrappedFormField<HelloWorldForm> {

				@Override
				protected String getConfiguredLabel() {
					return "Form1";
				}

				@Override
				protected void execInitField() {
					super.execInitField();
					setInnerForm(new HelloWorldForm());
				}

			}

			@Order(2000.0)
			public class Form2 extends AbstractWrappedFormField<HelloWorldForm> {

				@Override
				protected double getConfiguredGridWeightY() {
					return 1.0;
				}

				@Override
				protected void execInitField() {
					super.execInitField();
					setInnerForm(new HelloWorldForm());
				}
			}
		}
	}

  public class ViewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() {
      IHelloWorldFormService service = BEANS.get(IHelloWorldFormService.class);
      HelloWorldFormData formData = new HelloWorldFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
    }
  }
  
  public class ReadOnlyHandler extends AbstractFormHandler {
	  
	  @Override
	  protected void execLoad() {
	  }
	  
  }
  
  /**
   * 
   */
  public void startNew() throws ProcessingException {
    startInternal(getHandler());
  }
}
