package org.eclipse.scout.myapp.client;

import org.eclipse.scout.rt.client.extension.ui.form.fields.FormFieldChains.FormFieldInitFieldChain;
import org.eclipse.scout.rt.client.extension.ui.form.fields.stringfield.AbstractStringFieldExtension;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;

public class AbstractGenericExtension extends AbstractStringFieldExtension<AbstractStringField> {

	public AbstractGenericExtension(AbstractStringField owner) {
		super(owner);
	}

	@Override
	public void execInitField(FormFieldInitFieldChain chain) {
		getOwner().setValue("Hello");
	}
}
