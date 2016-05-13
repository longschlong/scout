package org.eclipse.scout.myapp.client.helloworld.color;

import org.eclipse.scout.myapp.client.helloworld.HelloWorldForm;
import org.eclipse.scout.myapp.client.helloworld.HelloWorldForm.MainBox.TopBox;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.AbstractIcons;

/**
 * <h3>{@link HelloWorldForm}</h3>
 *
 * @author mlu
 */
public class ColorForm extends AbstractForm {

	public ColorForm() {
		super();
		setHandler(new ViewHandler());
	}

	@Override
	protected void execStored() {
		super.execStored();
		getDesktop().dataChanged(BaseColorTablePage.PAGE_RELOAD);
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

	@Order(1000.0)
	public class MainBox extends AbstractGroupBox {

		@Order(1000.0)
		public class TopBox extends AbstractGroupBox {

			@Override
			protected String getConfiguredLabel() {
				return "Hello";
			}
			
			@Override
			protected int getConfiguredHeightInPixel() {
				return 100;
			}
			
			@Override
			protected boolean execIsSaveNeeded() {
				return true;
			}
		}

		public class OkButton extends AbstractOkButton {
		}
	}
	
	public class ViewHandler extends AbstractFormHandler {

		@Override
		protected void execLoad() {
			touch();
		}
	}

}
