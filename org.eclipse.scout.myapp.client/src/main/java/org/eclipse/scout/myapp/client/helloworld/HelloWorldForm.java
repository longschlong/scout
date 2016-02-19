package org.eclipse.scout.myapp.client.helloworld;

import org.eclipse.scout.myapp.client.helloworld.HelloWorldForm.MainBox.TopBox;
import org.eclipse.scout.myapp.client.helloworld.HelloWorldForm.MainBox.TopBox.InputField;
import org.eclipse.scout.myapp.client.helloworld.HelloWorldForm.MainBox.TopBox.MessageField;
import org.eclipse.scout.myapp.shared.helloworld.HelloWorldFormData;
import org.eclipse.scout.myapp.shared.helloworld.HelloWorldLookupCall;
import org.eclipse.scout.myapp.shared.helloworld.IHelloWorldFormService;
import org.eclipse.scout.myapp.shared.helloworld.MyStringCodeType;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.listbox.AbstractListBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.shared.AbstractIcons;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

/**
 * <h3>{@link HelloWorldForm}</h3>
 *
 * @author mlu
 */
@FormData(value = HelloWorldFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class HelloWorldForm extends AbstractForm {

	public HelloWorldForm() {
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
	protected int getConfiguredDisplayHint() {
		return DISPLAY_HINT_VIEW;
	}

	@Override
	protected String getConfiguredDisplayViewId() {
		return VIEW_ID_CENTER;
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

	public MessageField getMessageField() {
		return getFieldByClass(MessageField.class);
	}

	public InputField getInputField() {
		return getFieldByClass(InputField.class);
	}

	@Order(1000.0)
	public class MainBox extends AbstractGroupBox {

		@Order(1000.0)
		public class TopBox extends AbstractGroupBox {

			@Override
			protected String getConfiguredLabel() {
				return TEXTS.get("MessageFromServer");
			}

			@Order(1000.0)
			public class MessageField extends AbstractStringField {
				@Override
				protected int getConfiguredGridW() {
					return 2;
				}

				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Message");
				}

				@Override
				protected boolean getConfiguredEnabled() {
					return false;
				}
			}

			@Order(2000.0)
			public class InputField extends AbstractStringField {
				@Override
				protected int getConfiguredGridW() {
					return 2;
				}

				@Override
				protected String getConfiguredLabel() {
					return "New input";
				}
			}

			@Order(3000.0)
			public class LongListBoxField extends AbstractListBox<Long> {

				@Override
				protected int getConfiguredGridW() {
					return 2;
				}

				@Override
				protected String getConfiguredLabel() {
					return "Long listbox";
				}

				@Override
				protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
					return HelloWorldLookupCall.class;
				}
			}

			@Order(4000.0)
			public class StringListBoxField extends AbstractListBox<String> {

				@Override
				protected int getConfiguredGridW() {
					return 2;
				}

				@Override
				protected String getConfiguredLabel() {
					return "String listbox";
				}

				@Override
				protected Class<? extends ICodeType<?, String>> getConfiguredCodeType() {
					return MyStringCodeType.class;
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
		startInternal(new ReadOnlyHandler());
	}
}
