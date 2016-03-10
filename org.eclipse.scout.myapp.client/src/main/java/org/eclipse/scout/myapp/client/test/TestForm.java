package org.eclipse.scout.myapp.client.test;

import org.eclipse.scout.myapp.client.test.TestForm.MainBox.GroupBox;
import org.eclipse.scout.myapp.client.test.TestForm.MainBox.GroupBox.LeftBox;
import org.eclipse.scout.myapp.client.test.TestForm.MainBox.GroupBox.RightBox;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IFormField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateTimeField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.util.TriState;
import org.eclipse.scout.rt.platform.util.collection.OrderedCollection;
import org.eclipse.scout.rt.shared.AbstractIcons;

/**
 * <h3>{@link TestForm}</h3>
 *
 * @author mlu
 */
public class TestForm extends AbstractForm {

	public TestForm() {
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
		return AbstractIcons.Info;
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public LeftBox getLeftBox() {
		return getFieldByClass(LeftBox.class);
	}

	public GroupBox getGroupBox() {
		return getFieldByClass(GroupBox.class);
	}

	public RightBox getRightBox() {
		return getFieldByClass(RightBox.class);
	}

	@Order(1000.0)
	public class MainBox extends AbstractGroupBox {

		@Override
		protected TriState getConfiguredScrollable() {
			return TriState.FALSE;
		}
		
		@Order(1000.0)
		public class GroupBox extends AbstractGroupBox {

			@Override
			protected String getConfiguredLabel() {
				return "Generic test form";
			}

			@Override
			protected TriState getConfiguredScrollable() {
				return TriState.FALSE;
			}
			
			@Order(1000.0)
			public class LeftBox extends AbstractGroupBox {

				@Override
				protected int getConfiguredGridW() {
					return 1;
				}
				
				@Override
				protected int getConfiguredGridColumnCount() {
					return 1;
				}

				@Override
				protected void injectFieldsInternal(OrderedCollection<IFormField> fields) {
					super.injectFieldsInternal(fields);
					for (int i = 0; i < 15; i++) {
						String label = "left_" + i;
						String tooltip = "This is a provided tooltip for left_" + i;
						if (i % 3 == 0) {
							fields.addLast(createTestStringField(label, tooltip));
						} else if (i % 3 == 1) {
							fields.addLast(createTestDateTimeField(label, tooltip));
						} else {
							fields.addLast(createTestSmartField(label, tooltip));
						}
					}
				}
				
				@Override
				protected TriState getConfiguredScrollable() {
					return TriState.TRUE;
				}
			}

			@Order(2000.0)
			public class RightBox extends AbstractGroupBox {

				@Override
				protected int getConfiguredGridColumnCount() {
					return 1;
				}
				
				@Override
				protected int getConfiguredGridW() {
					return 1;
				}
				
				@Override
				protected void injectFieldsInternal(OrderedCollection<IFormField> fields) {
					super.injectFieldsInternal(fields);
					for (int i = 0; i < 15; i++) {
						String label = "right" + i;
						String tooltip = "This is a provided tooltip for right" + i;
						if (i % 3 == 0) {
							fields.addLast(createTestStringField(label, tooltip));
						} else if (i % 3 == 1) {
							fields.addLast(createTestDateTimeField(label, tooltip));
						} else {
							fields.addLast(createTestSmartField(label, tooltip));
						}
					}
				}
				
				@Override
				protected TriState getConfiguredScrollable() {
					return TriState.TRUE;
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

	private static AbstractStringField createTestStringField(final String label, final String tooltip) {
		return new AbstractStringField() {
			
			@Override
			protected String getConfiguredLabel() {
				return label;
			}
			
			@Override
			protected String getConfiguredTooltipText() {
				return tooltip;
			}
		};
	}
	
	private static AbstractSmartField<Long> createTestSmartField(final String label, final String tooltip) {
		return new AbstractSmartField<Long>() {
			
			@Override
			protected String getConfiguredLabel() {
				return label;
			}
			
			@Override
			protected String getConfiguredTooltipText() {
				return tooltip;
			}
		};
	}
	
	private static AbstractDateTimeField createTestDateTimeField(final String label, final String tooltip) {
		return new AbstractDateTimeField() {
			
			@Override
			protected void execInitField() {
				super.execInitField();
//				addPropertyChangeListener(PROP_TOOLTIP_TEXT, new PropertyChangeListener() {
//					
//					@Override
//					public void propertyChange(PropertyChangeEvent evt) {
//						System.out.println(evt);
//					}
//				});
			}
			
			@Override
			protected String getConfiguredLabel() {
				return label;
			}
			
			@Override
			protected String getConfiguredTooltipText() {
				return tooltip;
			}
		};
	}
}
