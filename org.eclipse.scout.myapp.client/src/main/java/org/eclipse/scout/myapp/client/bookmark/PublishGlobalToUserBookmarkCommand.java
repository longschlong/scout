package org.eclipse.scout.myapp.client.bookmark;

import org.eclipse.scout.rt.client.ui.desktop.bookmark.internal.ManageBookmarksForm;

public class PublishGlobalToUserBookmarkCommand extends AbstractPublishBookmarkCommand {

	public PublishGlobalToUserBookmarkCommand(ManageBookmarksForm form) {
		super(form);
	}

	@Override
	protected String getConfiguredMessageBoxHeader() {
		return "Publish global bookmark to user bookmark";
	}

	@Override
	protected int getConfiguredBookmarkKind() {
		return PUBLISH_TYPE_GLOBAL_TO_USER;
	}
}
