package org.eclipse.scout.myapp.client.bookmark;

import org.eclipse.scout.rt.client.ui.desktop.bookmark.menu.ActivateBookmarkMenu;
import org.eclipse.scout.rt.shared.services.common.bookmark.Bookmark;

public class MyActivateBookmarkMenu extends ActivateBookmarkMenu {

	public MyActivateBookmarkMenu(Bookmark b) {
		super(b);
		setText(b.getTitle());
	}

}
