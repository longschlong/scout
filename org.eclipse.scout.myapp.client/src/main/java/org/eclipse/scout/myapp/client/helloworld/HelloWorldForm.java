package org.eclipse.scout.myapp.client.helloworld;

import java.util.Collection;
import java.util.List;

import org.eclipse.scout.myapp.client.helloworld.HelloWorldForm.MainBox.TopBox;
import org.eclipse.scout.myapp.client.helloworld.HelloWorldForm.MainBox.TopBox.InputField;
import org.eclipse.scout.myapp.client.helloworld.HelloWorldForm.MainBox.TopBox.MessageField;
import org.eclipse.scout.myapp.shared.helloworld.HelloWorldFormData;
import org.eclipse.scout.myapp.shared.helloworld.HelloWorldLookupCall;
import org.eclipse.scout.myapp.shared.helloworld.IHelloWorldFormService;
import org.eclipse.scout.myapp.shared.helloworld.MyStringCodeType;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBooleanColumn;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.listbox.AbstractListBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
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

//				@Override
//				protected boolean getConfiguredMandatory() {
//					return true;
//				}
				
//				@Override
//				protected boolean execIsEmpty() {
//					return false;
//				} 
				
				@Override
				protected boolean getConfiguredEnabled() {
					return true;
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
				
				public class MySingleSelectionListBoxTable extends DefaultListBoxTable {
					
					@Override
					protected Class<? extends AbstractBooleanColumn> getConfiguredCheckableColumn() {
						return org.eclipse.scout.rt.client.ui.form.fields.listbox.AbstractListBox.DefaultListBoxTable.ActiveColumn.class;
					}

					@Override
					protected void execRowsChecked(Collection<? extends ITableRow> rows) {
						if (rows == null || rows.size() == 0) {
							return;
						}
						// 1. Check, if the 'Unknown' row was found in selection
						/*
						 * As long as we do not use (un)checkRows(...), the collection should have only one element in it.
						 * Therefore, check if we could easily use first element of collection
						 */
						ITableRow unknownRow = null;
						ITableRow r = CollectionUtility.firstElement(rows);
						if (MyStringCodeType.UnknownCode.ID.equals(getKeyColumn().getValue(r))) {
							unknownRow = r;
						}

						// 2. Check, if found, if selected
						boolean unknownSelected = unknownRow != null && unknownRow.isChecked();

						// 3. Uncheck all other rows
						if (unknownSelected) {
							List<ITableRow> allRows = getCheckedRows();
							allRows.remove(unknownRow);
							for (ITableRow tableRow : allRows) {
								getCheckableColumn().setValue(tableRow, false);
								tableRow.setChecked(false);
							}
						} else {
							// If 'Unknown' is selected but any other row gets
							// checked -> uncheck 'Unknown' row
							if (CollectionUtility.firstElement(rows).isChecked()) {
								List<ITableRow> allRows = getCheckedRows();
								for (ITableRow tableRow : allRows) {
									if (MyStringCodeType.UnknownCode.ID.equals(getKeyColumn().getValue(tableRow))) {
										getCheckableColumn().setValue(tableRow, false);
										tableRow.setChecked(false);
										break;
									}
								}
							}
						}
					}
				}
			}
		}
		
		public class OkButton extends AbstractOkButton {
			
			@Override
			protected void execClickAction() {
				HelloWorldFormData fd = new HelloWorldFormData();
				exportFormData(fd);
				System.out.println(fd.getStringListBox().getValue());
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
