/**
 * 
 */
package org.eclipse.scout.myapp.client.wizard;

import org.eclipse.scout.myapp.client.helloworld.HelloWorldForm;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.wizard.AbstractWizard;
import org.eclipse.scout.rt.client.ui.wizard.AbstractWizardStep;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.shared.TEXTS;

/**
 * @author mlu
 */
public class HelloWorldWizard extends AbstractWizard {

	private String m_testString;

	/**
	 * 
	 */
	public HelloWorldWizard() {
		super();
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("HelloWorldWizard");
	}

	@Override
	protected void execNextStep() {
		super.execNextStep();
	}

	@Override
	protected void execPreviousStep() {
		super.execPreviousStep();
	}

	/**
	 * @return the HelloWorldStep
	 */
	public HelloWorldStep getHelloWorldStep() {
		return getStep(HelloWorldWizard.HelloWorldStep.class);
	}

	/**
	 * @return the TestString
	 */
	@FormData
	public String getTestString() {
		return m_testString;
	}

	/**
	 * @param testString
	 *            the TestString to set
	 */
	@FormData
	public void setTestString(String testString) {
		m_testString = testString;
	}

	@Order(1000.0)
	public class HelloWorldStep extends AbstractWizardStep<HelloWorldForm> {

		@Override
		protected String getConfiguredTitle() {
			return TEXTS.get("HelloWorldStep");
		}

		@Override
		protected void execActivate(int stepKind) throws ProcessingException {
			HelloWorldForm form = getForm();
			if (form == null) {
				form = new HelloWorldForm();
				// start the form by executing the form handler
				form.startWizardStep(this, HelloWorldForm.ViewHandler.class);
				// Register the form on the step
				setForm(form);
			}
			// Set the form on the wizard
			// This will automatically display it as inner form of the wizard
			// container form
			getWizard().setWizardForm(form);
		}

		@Override
		protected void execDeactivate(int stepKind) throws ProcessingException {
			// Save the form if the user clicks next
			if (stepKind == STEP_NEXT) {
				HelloWorldForm form = getForm();
				if (form != null) {
					form.doSave();
					m_testString = form.getInputField().getValue();
				}
			}
		}
	}

	@Order(1000.0)
	public class HelloWorldStep2 extends AbstractWizardStep<HelloWorldForm> {

		@Override
		protected String getConfiguredTitle() {
			return TEXTS.get("HelloWorldStep2");
		}

		@Override
		protected void execActivate(int stepKind) throws ProcessingException {
			HelloWorldForm form = getForm();
			if (form == null) {
				form = new HelloWorldForm();
				// start the form by executing the form handler
				form.startWizardStep(this, HelloWorldForm.ReadOnlyHandler.class);
				// Register the form on the step
				setForm(form);
			}
			form.getMessageField().setValue(m_testString);
			form.getInputField().setVisibleGranted(false);
			// Set the form on the wizard
			// This will automatically display it as inner form of the wizard
			// container form
			getWizard().setWizardForm(form);
		}

		@Override
		protected void execDeactivate(int stepKind) throws ProcessingException {
			// Save the form if the user clicks next
			if (stepKind == STEP_NEXT) {
				HelloWorldForm form = getForm();
				if (form != null) {
					form.doSave();
				}
			}
		}
	}
}