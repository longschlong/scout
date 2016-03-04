package org.eclipse.scout.myapp.client.bookmark;

import org.eclipse.scout.rt.client.ui.desktop.bookmark.internal.ManageBookmarksForm;

public class PublishUserToGlobalBookmarkCommand extends AbstractPublishBookmarkCommand {

	public PublishUserToGlobalBookmarkCommand(ManageBookmarksForm form) {
		super(form);
	}

	@Override
	protected String getConfiguredMessageBoxHeader() {
		return "Publish user bookmark to global bookmark";
	}

	@Override
	protected int getConfiguredBookmarkKind() {
		return PUBLISH_TYPE_USER_TO_GLOBAL;
	}
}
