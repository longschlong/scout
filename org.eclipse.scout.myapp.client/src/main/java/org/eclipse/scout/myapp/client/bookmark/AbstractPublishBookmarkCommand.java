package org.eclipse.scout.myapp.client.bookmark;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.scout.rt.client.services.common.bookmark.IBookmarkService;
import org.eclipse.scout.rt.client.ui.desktop.bookmark.internal.ManageBookmarksForm;
import org.eclipse.scout.rt.client.ui.desktop.bookmark.view.IPublishBookmarkCommand;
import org.eclipse.scout.rt.client.ui.messagebox.MessageBox;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.shared.services.common.bookmark.Bookmark;
import org.eclipse.scout.rt.shared.services.common.bookmark.BookmarkData;
import org.eclipse.scout.rt.shared.services.common.bookmark.BookmarkFolder;

public abstract class AbstractPublishBookmarkCommand implements IPublishBookmarkCommand {

	public static final int PUBLISH_TYPE_GLOBAL_TO_USER = 1;
	public static final int PUBLISH_TYPE_USER_TO_GLOBAL = 2;
	private ManageBookmarksForm m_form;
	
	protected AbstractPublishBookmarkCommand(ManageBookmarksForm form) {
		m_form = form;
	}
	
	@Override
	public void publishBookmark(BookmarkFolder publishFolder) {
		if (publishFolder != null) {
			int decision = BEANS.get(MessageBox.class).withHeader(getConfiguredMessageBoxHeader()).withBody(getConfiguredMessageBoxBody() + " " + publishFolder.getBookmarks().get(0).getTitle()).withNoButtonText("No").withYesButtonText("Yes").show();
			if (decision == MessageBox.YES_OPTION) {
				if (getConfiguredBookmarkKind() == PUBLISH_TYPE_GLOBAL_TO_USER) {
					processPublishGlobalToUser(publishFolder);
				}
				else {
					processPublishUserToGlobal(publishFolder);
				}
			}
		}
	}

	protected void processPublishGlobalToUser(BookmarkFolder publishFolder) {
		processPublishing(publishFolder, Bookmark.USER_BOOKMARK);
	}
	
	protected void processPublishUserToGlobal(BookmarkFolder publishFolder) {
		processPublishing(publishFolder, Bookmark.GLOBAL_BOOKMARK);
	}
	
	protected void processPublishing(BookmarkFolder publishFolder, int bookmarkType) {
		List<Bookmark> bookmarks = publishFolder.getBookmarks();
		if (bookmarks != null && bookmarks.size() > 0) {
			List<Bookmark> newBookmarkList = new ArrayList<Bookmark>(bookmarks.size());
			for (Bookmark bookmark : bookmarks) {
				Bookmark newBookmark = (Bookmark) bookmark.clone();
				newBookmark.setKind(bookmarkType);
				String addNewTitle = " [transferred from ";
				if (getConfiguredBookmarkKind() == PUBLISH_TYPE_GLOBAL_TO_USER) {
					addNewTitle += "global to user";
				}
				else {
					addNewTitle += "user to global";
				}
				addNewTitle += "]";
				newBookmark.setTitle(newBookmark.getTitle() + " " + addNewTitle);
				newBookmarkList.add(newBookmark);
			}
			IBookmarkService s = BEANS.get(IBookmarkService.class);
			BookmarkData bookmarkData = s.getBookmarkData();
			BookmarkFolder bookmarkFolder = bookmarkData.getGlobalBookmarks();
			if (getConfiguredBookmarkKind() == PUBLISH_TYPE_GLOBAL_TO_USER) {
				bookmarkFolder = bookmarkData.getUserBookmarks();
			}
			List<Bookmark> allBookmarkList = bookmarkFolder.getBookmarks();
			for (Bookmark bookmark : newBookmarkList) {
				allBookmarkList.add(bookmark);
			}
			s.storeBookmarks();
			
			m_form.getGlobalBookmarkTreeField().populateTree();
			m_form.getUserBookmarkTreeField().populateTree();
		}
	}
	
	protected abstract String getConfiguredMessageBoxHeader();
	
	protected String getConfiguredMessageBoxBody() {
		return "Publish bookmark";
	}
	
	protected abstract int getConfiguredBookmarkKind();
}
