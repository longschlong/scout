package org.eclipse.scout.myapp.client.helloworld.color;

import java.awt.Color;
import java.util.Collections;

import org.eclipse.scout.myapp.client.helloworld.color.ColorSearchForm.MainBox.SimpleBox;
import org.eclipse.scout.myapp.client.helloworld.color.ColorSearchForm.MainBox.SimpleBox.SimpleGroupBox;
import org.eclipse.scout.myapp.client.helloworld.color.ColorSearchForm.MainBox.SimpleBox.SimpleGroupBox.ColorField;
import org.eclipse.scout.myapp.client.helloworld.color.ColorSearchForm.MainBox.SimpleBox.SimpleGroupBox.RedSequenceBox;
import org.eclipse.scout.myapp.client.helloworld.color.ColorSearchForm.MainBox.SimpleBox.SimpleGroupBox.RedSequenceBox.RedOperatorField;
import org.eclipse.scout.myapp.client.helloworld.color.ColorSearchForm.MainBox.SimpleBox.SimpleGroupBox.RedSequenceBox.RedValueField;
import org.eclipse.scout.myapp.shared.helloworld.color.ColorCodeType;
import org.eclipse.scout.myapp.shared.helloworld.color.ColorSearchFormData;
import org.eclipse.scout.myapp.shared.helloworld.color.OperatorCodeType;
import org.eclipse.scout.myapp.shared.helloworld.color.OperatorLookupCall;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractSearchForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractResetButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractSearchButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.longfield.AbstractLongField;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.AbstractIcons;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

/**
 * <h3>{@link ColorSearchForm}</h3>
 *
 * @author mlu
 */
@FormData(value = ColorSearchFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class ColorSearchForm extends AbstractSearchForm {

	public ColorSearchForm() {
		super();
	}

	@Override
	public void start() {
		startInternal(new SearchHandler());
	}

	@Override
	protected void execResetSearchFilter(SearchFilter searchFilter) {
		super.execResetSearchFilter(searchFilter);
		ColorSearchFormData formData = new ColorSearchFormData();
		exportFormData(formData);
		searchFilter.setFormData(formData);
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

	public ColorField getColorField() {
		return getFieldByClass(ColorField.class);
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public SimpleGroupBox getSimpleGroupBox() {
		return getFieldByClass(SimpleGroupBox.class);
	}

	public RedValueField getRValueField() {
		return getFieldByClass(RedValueField.class);
	}

	public RedOperatorField getROperatorField() {
		return getFieldByClass(RedOperatorField.class);
	}

	public RedSequenceBox getRSequenceBox() {
		return getFieldByClass(RedSequenceBox.class);
	}

	public SimpleBox getSimpleBox() {
		return getFieldByClass(SimpleBox.class);
	}

	@Order(1000.0)
	public class MainBox extends AbstractGroupBox {

		@Order(1000.0)
		public class SimpleBox extends AbstractTabBox {

			@Order(0)
			public class SimpleGroupBox extends AbstractGroupBox {

				@Override
				protected String getConfiguredLabel() {
					return "Simple Search";
				}

				@Order(1000)
				public class ColorField extends AbstractSmartField<Color> {
					@Override
					protected String getConfiguredLabel() {
						return "Color";
					}

					@Override
					protected boolean getConfiguredEnabled() {
						return false;
					}

					@Override
					protected Class<? extends ICodeType<?, Color>> getConfiguredCodeType() {
						return ColorCodeType.class;
					}

					@Override	
					protected String execFormatValue(Color value) {
						if (value == null) {
							return super.execFormatValue(value);
						}
						return BEANS.get(ColorCodeType.class).getCode(value).getText();
					}
				}

				@Order(2000)
				public class RedSequenceBox extends AbstractSequenceBox {

					@Override
					protected String getConfiguredLabel() {
						return "Red is...";
					}

					@Override
					protected boolean getConfiguredAutoCheckFromTo() {
						return false;
					}

					@Order(1000)
					public class RedOperatorField extends AbstractSmartField<String> {
						@Override
						protected String getConfiguredLabel() {
							return "Operator";
						}

						@Override
						protected boolean getConfiguredLabelVisible() {
							return false;
						}

						@Override
						protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
							return OperatorLookupCall.class;
						}
						
						@Override
						protected void execChangedValue() {
							if (StringUtility.hasText(getValue())) {
								getFieldByClass(RedValueField.class).setMandatory(true);
							}
							else {
								getFieldByClass(RedValueField.class).setMandatory(false);
							}
						}
						@Override
						protected void execPrepareLookup(ILookupCall<String> call) {
							super.execPrepareLookup(call);
							if (call instanceof OperatorLookupCall) {
								OperatorLookupCall c = (OperatorLookupCall) call;
								c.setDisabledElements(Collections.singletonList(OperatorCodeType.EqualsCode.ID));
							}
						}
					}

					@Order(2000)
					public class RedValueField extends AbstractLongField {
						@Override
						protected String getConfiguredLabel() {
							return "R";
						}

						@Override
						protected Long getConfiguredMinValue() {
							return 0L;
						}

						@Override
						protected Long getConfiguredMaxValue() {
							return 255L;
						}

						@Override
						protected boolean getConfiguredLabelVisible() {
							return false;
						}
					}

				}

				@Order(3000)
				public class GreenSequenceBox extends AbstractSequenceBox {

					@Override
					protected String getConfiguredLabel() {
						return "Green is...";
					}

					@Override
					protected boolean getConfiguredAutoCheckFromTo() {
						return false;
					}

					@Order(1000)
					public class GreenOperatorField extends AbstractSmartField<String> {
						@Override
						protected String getConfiguredLabel() {
							return "Operator";
						}

						@Override
						protected boolean getConfiguredLabelVisible() {
							return false;
						}

						@Override
						protected Class<? extends ICodeType<?, String>> getConfiguredCodeType() {
							return OperatorCodeType.class;
						}
						
						@Override
						protected void execChangedValue() {
							if (StringUtility.hasText(getValue())) {
								getFieldByClass(GreenValueField.class).setMandatory(true);
							}
							else {
								getFieldByClass(GreenValueField.class).setMandatory(false);
							}
						}
					}

					@Order(2000)
					public class GreenValueField extends AbstractLongField {
						@Override
						protected String getConfiguredLabel() {
							return "G";
						}

						@Override
						protected Long getConfiguredMinValue() {
							return 0L;
						}

						@Override
						protected Long getConfiguredMaxValue() {
							return 255L;
						}

						@Override
						protected boolean getConfiguredLabelVisible() {
							return false;
						}
					}

				}

				@Order(4000)
				public class BlueSequenceBox extends AbstractSequenceBox {

					@Override
					protected String getConfiguredLabel() {
						return "Blue is...";
					}

					@Override
					protected boolean getConfiguredAutoCheckFromTo() {
						return false;
					}

					@Order(1000)
					public class BlueOperatorField extends AbstractSmartField<String> {
						@Override
						protected String getConfiguredLabel() {
							return "Operator";
						}

						@Override
						protected boolean getConfiguredLabelVisible() {
							return false;
						}

						@Override
						protected Class<? extends ICodeType<?, String>> getConfiguredCodeType() {
							return OperatorCodeType.class;
						}
						
						@Override
						protected void execChangedValue() {
							if (StringUtility.hasText(getValue())) {
								getFieldByClass(BlueValueField.class).setMandatory(true);
							}
							else {
								getFieldByClass(BlueValueField.class).setMandatory(false);
							}
						}
					}

					@Order(2000)
					public class BlueValueField extends AbstractLongField {
						@Override
						protected String getConfiguredLabel() {
							return "B";
						}

						@Override
						protected Long getConfiguredMinValue() {
							return 0L;
						}

						@Override
						protected Long getConfiguredMaxValue() {
							return 255L;
						}

						@Override
						protected boolean getConfiguredLabelVisible() {
							return false;
						}
					}

				}
			}

		}

		@Order(2000)
		public class ResetButton extends AbstractResetButton {
		}

		@Order(3000)
		public class SearchButton extends AbstractSearchButton {
		}
	}

	public class SearchHandler extends AbstractFormHandler {
	}

}
