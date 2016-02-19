package org.eclipse.scout.myapp.client;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.scout.rt.client.services.common.bookmark.BookmarkServiceEvent;
import org.eclipse.scout.rt.client.services.common.bookmark.BookmarkServiceListener;
import org.eclipse.scout.rt.client.services.common.bookmark.IBookmarkService;
import org.eclipse.scout.rt.client.ui.action.menu.IMenu;
import org.eclipse.scout.rt.client.ui.action.menu.MenuSeparator;
import org.eclipse.scout.rt.client.ui.desktop.IDesktop;
import org.eclipse.scout.rt.client.ui.desktop.bookmark.menu.AbstractBookmarkMenu;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.annotations.ConfigOperation;
import org.eclipse.scout.rt.shared.services.common.bookmark.Bookmark;
import org.eclipse.scout.rt.shared.services.common.bookmark.BookmarkFolder;

public class MyBookmarkMenu extends AbstractBookmarkMenu {

	public MyBookmarkMenu(IDesktop desktop) {
		super(desktop);
	}

	@Override
	@ConfigOperation
	@Order(10)
	protected void execInitAction() {
		BEANS.get(IBookmarkService.class).addBookmarkServiceListener(new BookmarkServiceListener() {
			@Override
			public void bookmarksChanged(BookmarkServiceEvent e) {
				handleBookmarksChanged();
			}
		});
		handleBookmarksChanged();
	}

	private void handleBookmarksChanged() {
		IBookmarkService service = BEANS.get(IBookmarkService.class);
		List<IMenu> oldList = getChildActions();
		List<IMenu> newList = new ArrayList<IMenu>();
		for (IMenu m : oldList) {
			if (m.getClass() == AddUserBookmarkMenu.class) {
				newList.add(m);
			} else if (m.getClass() == AddGlobalBookmarkMenu.class) {
				newList.add(m);
			} else if (m.getClass() == ManageBookmarksMenu.class) {
				newList.add(m);
			} else if (m.getClass() == StartBookmarkMenu.class) {
				newList.add(m);
			} else if (m.getClass() == Separator1Menu.class) {
				newList.add(m);
			} else {
				// ignore the rest
				break;
			}
		}
		// add global bookmarks
		newList.add(new MenuSeparator());
		addBookmarkTreeInternal(service.getBookmarkData().getGlobalBookmarks(), newList);
		// add user bookmarks
		newList.add(new MenuSeparator());
		addBookmarkTreeInternal(service.getBookmarkData().getUserBookmarks(), newList);
		setChildActions(newList);
	}

	private void addBookmarkTreeInternal(BookmarkFolder folder, List<IMenu> actionList) {
		for (BookmarkFolder f : folder.getFolders()) {
			IMenu group = new MenuSeparator();
			group.setSeparator(false);
			group.setText(f.getTitle());
			group.setIconId(f.getIconId());
			List<IMenu> childActionList = new ArrayList<IMenu>();
			addBookmarkTreeInternal(f, childActionList);
			group.setChildActions(childActionList);
			actionList.add(group);
		}
		for (Bookmark b : folder.getBookmarks()) {
			actionList.add(new MyActivateBookmarkMenu(b));
		}
	}
}
