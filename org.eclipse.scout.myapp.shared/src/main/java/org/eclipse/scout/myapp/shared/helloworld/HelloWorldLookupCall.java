package org.eclipse.scout.myapp.shared.helloworld;

import org.eclipse.scout.rt.shared.services.lookup.CodeLookupCall;

public class HelloWorldLookupCall extends CodeLookupCall<Long> {

	private static final long serialVersionUID = 1L;

	public HelloWorldLookupCall() {
		super(HelloWorldCodeType.class);
	}

}
