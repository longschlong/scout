package org.eclipse.scout.myapp.client;

import java.util.List;

import org.eclipse.scout.myapp.client.bookmark.MyBookmarkMenu;
import org.eclipse.scout.myapp.client.cluster.ConversationForm;
import org.eclipse.scout.myapp.client.helloworld.HelloWorldForm;
import org.eclipse.scout.myapp.client.helloworld.color.ColorOutline;
import org.eclipse.scout.myapp.client.search.SearchOutline;
import org.eclipse.scout.myapp.client.settings.SettingsOutline;
import org.eclipse.scout.myapp.client.test.TestForm;
import org.eclipse.scout.myapp.client.time.TimeForm;
import org.eclipse.scout.myapp.client.wizard.HelloWorldWizard;
import org.eclipse.scout.myapp.client.work.WorkOutline;
import org.eclipse.scout.myapp.shared.time.ITimeService;
import org.eclipse.scout.rt.client.session.ClientSessionProvider;
import org.eclipse.scout.rt.client.ui.action.keystroke.AbstractKeyStroke;
import org.eclipse.scout.rt.client.ui.action.keystroke.IKeyStroke;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.desktop.AbstractDesktop;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutlineViewButton;
import org.eclipse.scout.rt.client.ui.desktop.outline.IOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.client.ui.form.ScoutInfoForm;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.shared.TEXTS;

/**
 * <h3>{@link Desktop}</h3>
 *
 * @author mlu
 */
public class Desktop extends AbstractDesktop {
	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("ApplicationTitle");
	}

	@Override
	protected List<Class<? extends IOutline>> getConfiguredOutlines() {
		return CollectionUtility.<Class<? extends IOutline>>arrayList(WorkOutline.class, SearchOutline.class,
				SettingsOutline.class, ColorOutline.class);
	}

	@Override
	protected void execGuiAttached() {
		super.execGuiAttached();
		selectFirstVisibleOutline();
		//getMenu(StartBookmarkMenu.ActivateStartBookmarkMenu.class).doAction();	// XXX View bookmark as startup page
	}

	protected void selectFirstVisibleOutline() {
		for (IOutline outline : getAvailableOutlines()) {
			if (outline.isEnabled() && outline.isVisible()) {
				setOutline(outline);
				break;
			}
		}
	}
	
	@Order(1000)
	public class FileMenu extends AbstractMenu {

		@Override
		protected String getConfiguredText() {
			return TEXTS.get("File");
		}

		@Order(1000.0)
		public class StartWizardMenu extends AbstractMenu {

			@Override
			protected String getConfiguredText() {
				return TEXTS.get("StartWizard");
			}

			@Override
			protected void execAction() {
				new HelloWorldWizard().start();
			}
		}

		@Order(1000.0)
		public class StartTimeFormMenu extends AbstractMenu {
			
			@Override
			protected String getConfiguredText() {
				return "Show time";
			}
			
			@Override
			protected void execAction() {
				TimeForm timeForm = new TimeForm();
				timeForm.start();
				BEANS.get(ITimeService.class).start();
				timeForm.waitFor();
				if (BEANS.get(ITimeService.class).stop()) {
					timeForm.doCancel();
				}
			}
		}

		@Order(1100.0)
		public class StartHelloWorldForm extends AbstractMenu {

			@Override
			protected String getConfiguredText() {
				return "Start HelloWorldForm";
			}

			@Override
			protected void execAction() {
				new HelloWorldForm().startNew();
			}
		}
		
		@Order(1200.0)
		public class StartTestForm extends AbstractMenu {

			@Override
			protected String getConfiguredText() {
				return "Start TestForm";
			}

			@Override
			protected void execAction() {
				new TestForm().start();
			}
		}
		
		@Order(1300)
		public class StartConversationMenu extends AbstractMenu {

			@Override
			protected String getConfiguredText() {
				return "Start conversation";
			}
			
			@Override
			protected void execAction() {
				new ConversationForm().start();
			}
		}

		@Order(2000.0)
		public class ExitMenu extends AbstractMenu {

			@Override
			protected String getConfiguredText() {
				return TEXTS.get("Exit");
			}

			@Override
			protected void execAction() {
				ClientSessionProvider.currentSession(ClientSession.class).stop();
			}
		}

	}

	@Order(2000)
	public class BookmarkMenu extends MyBookmarkMenu {
		public BookmarkMenu() {
			super(Desktop.this);
		}
	}

	@Order(3000)
	public class HelpMenu extends AbstractMenu {

		@Override
		protected String getConfiguredText() {
			return TEXTS.get("Help");
		}

		@Order(1000)
		public class AboutMenu extends AbstractMenu {

			@Override
			protected String getConfiguredText() {
				return TEXTS.get("About");
			}

			@Override
			protected void execAction() {
				ScoutInfoForm form = new ScoutInfoForm();
				form.startModify();
			}
		}
	}
	
	@Order(10.0)
	public class RefreshOutlineKeyStroke extends AbstractKeyStroke {

		@Override
		protected String getConfiguredKeyStroke() {
			return IKeyStroke.F5;
		}

		@Override
		protected void execAction() {
			if (getOutline() != null) {
				IPage<?> page = getOutline().getActivePage();
				if (page != null) {
					page.reloadPage();
				}
			}
		}
	}

	@Order(1000.0)
	public class WorkOutlineViewButton extends AbstractOutlineViewButton {

		public WorkOutlineViewButton() {
			this(WorkOutline.class);
		}

		protected WorkOutlineViewButton(Class<? extends WorkOutline> outlineClass) {
			super(Desktop.this, outlineClass);
		}

		@Override
		protected String getConfiguredKeyStroke() {
			return IKeyStroke.F2;
		}
	}

	@Order(1500.0)
	public class ColorOutlineViewButton extends AbstractOutlineViewButton {

		public ColorOutlineViewButton() {
			this(ColorOutline.class);
		}

		protected ColorOutlineViewButton(Class<? extends ColorOutline> outlineClass) {
			super(Desktop.this, outlineClass);
		}

		@Override
		protected String getConfiguredKeyStroke() {
			return IKeyStroke.F4;
		}
	}

	@Order(2000.0)
	public class SearchOutlineViewButton extends AbstractOutlineViewButton {

		public SearchOutlineViewButton() {
			this(SearchOutline.class);
		}

		protected SearchOutlineViewButton(Class<? extends SearchOutline> outlineClass) {
			super(Desktop.this, outlineClass);
		}

		@Override
		protected DisplayStyle getConfiguredDisplayStyle() {
			return DisplayStyle.TAB;
		}

		@Override
		protected String getConfiguredKeyStroke() {
			return IKeyStroke.F3;
		}
	}

	@Order(3000.0)
	public class SettingsOutlineViewButton extends AbstractOutlineViewButton {

		public SettingsOutlineViewButton() {
			this(SettingsOutline.class);
		}

		protected SettingsOutlineViewButton(Class<? extends SettingsOutline> outlineClass) {
			super(Desktop.this, outlineClass);
		}

		@Override
		protected DisplayStyle getConfiguredDisplayStyle() {
			return DisplayStyle.TAB;
		}

		@Override
		protected String getConfiguredKeyStroke() {
			return IKeyStroke.F10;
		}
	}
}
