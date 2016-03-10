package org.eclipse.scout.myapp.client.time;

import org.eclipse.scout.myapp.client.helloworld.HelloWorldForm;
import org.eclipse.scout.myapp.client.helloworld.HelloWorldForm.MainBox.TopBox;
import org.eclipse.scout.myapp.client.time.TimeForm.MainBox.TopBox.TimeField;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.AbstractIcons;

/**
 * <h3>{@link HelloWorldForm}</h3>
 *
 * @author mlu
 */
public class TimeForm extends AbstractForm {

	public TimeForm() {
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
		return DISPLAY_HINT_DIALOG;
	}

	@Override
	protected String getConfiguredDisplayViewId() {
		return VIEW_ID_CENTER;
	}

	@Override
	protected String getConfiguredIconId() {
		return AbstractIcons.Clock;
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public TopBox getTopBox() {
		return getFieldByClass(TopBox.class);
	}

	public TimeField getTimeField() {
		return getFieldByClass(TimeField.class);
	}

	@Order(1000.0)
	public class MainBox extends AbstractGroupBox {

		@Order(1000.0)
		public class TopBox extends AbstractGroupBox {

			@Override
			protected String getConfiguredLabel() {
				return "Message from notification";
			}

			@Order(1000.0)
			public class TimeField extends AbstractStringField {

				@Override
				protected String getConfiguredLabel() {
					return "Date: ";
				}

				@Override
				protected boolean getConfiguredEnabled() {
					return false;
				}
				
				
				
			}

		}

		public class OkButton extends AbstractOkButton {
		}
	}
	
	public class ViewHandler extends AbstractFormHandler {

		@Override
		protected void execLoad() {
		}
	}

}
