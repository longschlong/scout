package org.eclipse.scout.myapp.shared.helloworld.color;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.shared.services.common.code.ICode;
import org.eclipse.scout.rt.shared.services.lookup.CodeLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;

public class OperatorLookupCall extends CodeLookupCall<String> {
	private static final long serialVersionUID = 1L;

	private List<String> disabledElements = new ArrayList<String>();

	public OperatorLookupCall() {
		super(OperatorCodeType.class);
	}

	public void setDisabledElements(List<String> list) {
		disabledElements = list;
	}

	@Override
	public List<ILookupRow<String>> getDataByKey() {
		return decorateRows(super.getDataByKey());
	}

	@Override
	public List<ILookupRow<String>> getDataByText() {
		return decorateRows(super.getDataByText());
	}

	@Override
	public List<ILookupRow<String>> getDataByAll() {
		return decorateRows(super.getDataByAll());
	}

	@Override
	public List<ILookupRow<String>> getDataByRec() {
		return decorateRows(super.getDataByRec());
	}

	private List<ILookupRow<String>> decorateRows(List<ILookupRow<String>> rows) {
		OperatorCodeType codeType = BEANS.get(OperatorCodeType.class);
		for (ILookupRow<String> r : rows) {
			ICode<String> c = codeType.getCode(r.getKey());
			if (disabledElements.contains(c.getId())) {
				r.withEnabled(false);
			}
		}
		return rows;
	}
}
