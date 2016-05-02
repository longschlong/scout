package org.eclipse.scout.myapp.client.cluster;

import org.eclipse.scout.myapp.client.cluster.ConversationForm.MainBox.TopBox.ConversationField;
import org.eclipse.scout.myapp.client.cluster.ConversationForm.MainBox.TopBox.MessageBox;
import org.eclipse.scout.myapp.client.cluster.ConversationForm.MainBox.TopBox.MessageBox.MessageField;
import org.eclipse.scout.myapp.client.cluster.ConversationForm.MainBox.TopBox.MessageBox.SendButton;
import org.eclipse.scout.myapp.client.helloworld.HelloWorldForm;
import org.eclipse.scout.myapp.client.helloworld.HelloWorldForm.MainBox.TopBox;
import org.eclipse.scout.myapp.shared.cluster.ClusterMessage;
import org.eclipse.scout.myapp.shared.cluster.IClusterMessageService;
import org.eclipse.scout.rt.client.session.ClientSessionProvider;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.AbstractIcons;

/**
 * <h3>{@link HelloWorldForm}</h3>
 *
 * @author mlu
 */
public class ConversationForm extends AbstractForm {

	public ConversationForm() {
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

	public MessageBox getMessageBox() {
		return getFieldByClass(MessageBox.class);
	}

	public SendButton getSendButton() {
		return getFieldByClass(SendButton.class);
	}

	public TopBox getTopBox() {
		return getFieldByClass(TopBox.class);
	}

	public ConversationField getConversationField() {
		return getFieldByClass(ConversationField.class);
	}
	
	public MessageField getMessageField() {
		return getFieldByClass(MessageField.class);
	}

	@Order(1000.0)
	public class MainBox extends AbstractGroupBox {

		@Order(1000.0)
		public class TopBox extends AbstractGroupBox {

			@Override
			protected String getConfiguredLabel() {
				return "Conversation";
			}
			
			@Override
			protected boolean getConfiguredLabelVisible() {
				return true;
			}

			@Order(1000.0)
			public class ConversationField extends AbstractStringField {

				@Override
				protected String getConfiguredLabel() {
					return "Conversation: ";
				}

				@Override
				protected boolean getConfiguredEnabled() {
					return false;
				}
				
				@Override
				protected boolean getConfiguredLabelVisible() {
					return false;
				}
				
				@Override
				protected int getConfiguredGridW() {
					return 2;
				}
				
				@Override
				protected double getConfiguredGridWeightY() {
					return 1.0;
				}
				
				@Override
				protected boolean getConfiguredHtmlEnabled() {
					return true;
				}
				
				@Override
				protected int getConfiguredGridH() {
					return 2;
				}
				
				@Override
				protected boolean getConfiguredMultilineText() {
					return true;
				}
				
				@Override
				protected boolean getConfiguredWrapText() {
					return true;
				}
				
				@Override
				protected boolean getConfiguredTrimText() {
					return false;
				}
			}

			@Order(1500.0)
			public class MessageBox extends AbstractGroupBox {
				
				@Override
				protected boolean getConfiguredLabelVisible() {
					return false;
				}
				
				@Override
				protected boolean getConfiguredBorderVisible() {
					return false;
				}
				
				@Order(2000.0)
				public class MessageField extends AbstractStringField {

					@Override
					protected String getConfiguredLabel() {
						return "Message: ";
					}

					@Override
					protected boolean getConfiguredLabelVisible() {
						return false;
					}
					
					@Override
					protected int getConfiguredMaxLength() {
						return 60;
					}
					

					@Override
					protected boolean getConfiguredTrimText() {
						return false;
					}
					
					@Override
					protected void execChangedValue() {
						super.execChangedValue();
//						if (StringUtility.hasText(getValue())) {
//							getSendButton().setEnabled(true);
//						}
//						else {
//							getSendButton().setEnabled(false);
//						}
					}
					
					@Override
					protected boolean getConfiguredMultilineText() {
						return true;
					}
				}

				@Order(3000.0)
				public class SendButton extends AbstractButton {
					@Override
					protected String getConfiguredLabel() {
						return "Send";
					}

					@Override
					protected void execClickAction() {
						BEANS.get(IClusterMessageService.class).sendMessage(new ClusterMessage(ClientSessionProvider.currentSession().getUserId() + ": " + getMessageField().getValue()));
						getMessageField().setValue(null);
					}
					
					@Override
					protected boolean getConfiguredProcessButton() {
						return false;
					}
					
					@Override
					protected boolean getConfiguredGridUseUiWidth() {
						return true;
					}
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