package org.eclipse.scout.myapp.client.helloworld.color;

import java.awt.Color;

import org.eclipse.scout.myapp.shared.helloworld.color.BaseColorTablePageData;
import org.eclipse.scout.myapp.shared.helloworld.color.ColorCodeType;
import org.eclipse.scout.myapp.shared.helloworld.color.ColorCodeType.AbstractColorCode;
import org.eclipse.scout.myapp.shared.helloworld.color.ColorSearchFormData;
import org.eclipse.scout.myapp.shared.helloworld.color.IColorPageService;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.dto.FormData.SdkCommand;
import org.eclipse.scout.rt.client.dto.PageData;
import org.eclipse.scout.rt.client.ui.basic.cell.Cell;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractIntegerColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractSmartColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.ISearchForm;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

/**
 * <h3>{@link BaseColorTablePage}</h3>
 *
 * @author mlu
 */
@PageData(BaseColorTablePageData.class)
public class BaseColorTablePage extends AbstractPageWithTable<BaseColorTablePage.Table> {

	private AbstractColorCode m_color;

	public BaseColorTablePage(AbstractColorCode colorCode) {
		super(true, colorCode.getText());
		m_color = colorCode;
	}

	@Override
	protected Class<? extends ISearchForm> getConfiguredSearchForm() {
		return ColorSearchForm.class;
	}
	
	@Override
	protected String getConfiguredTitle() {
		return m_color.getText();
	}
	
	@Override
	public ColorSearchForm getSearchFormInternal() {
		return (ColorSearchForm) super.getSearchFormInternal();
	}
	
	@Override
	protected void execInitSearchForm() {
		ColorSearchForm searchFormInternal = getSearchFormInternal();
		if (searchFormInternal != null) {
			searchFormInternal.getColorField().setValue(m_color.getId());
		}
	}

	@Override
	protected void execLoadData(SearchFilter filter) {
		importPageData(BEANS.get(IColorPageService.class).loadSubColors((ColorSearchFormData) getSearchFilter().getFormData()));
	}
	
	@Order(10)
	public class Table extends AbstractTable {

		public RedValueColumn getRedValueColumn() {
			return getColumnSet().getColumnByClass(RedValueColumn.class);
		}

		public BlueValueColumn getBlueValueColumn() {
			return getColumnSet().getColumnByClass(BlueValueColumn.class);
		}

		public GreenValueColumn getGreenValueColumn() {
			return getColumnSet().getColumnByClass(GreenValueColumn.class);
		}

		public ColorColumn getColorColumn() {
			return getColumnSet().getColumnByClass(ColorColumn.class);
		}

		public IdColumn getIdColumn() {
			return getColumnSet().getColumnByClass(IdColumn.class);
		}

		@Order(1000)
		public class IdColumn extends AbstractSmartColumn<Color> {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("ID");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
			
			@Override
			protected Class<? extends ICodeType<?, Color>> getConfiguredCodeType() {
				return ColorCodeType.class;
			}
			
			@Override
			protected boolean getConfiguredDisplayable() {
				return false;
			}
		}

		@Order(3000)
		public class RedValueColumn extends AbstractIntegerColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return "Red";
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(4000)
		public class GreenValueColumn extends AbstractIntegerColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return "Green";
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		
		@Order(5000)
		public class BlueValueColumn extends AbstractIntegerColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return "Blue";
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@FormData(sdkCommand = SdkCommand.IGNORE)
		@Order(6000)
		public class ColorColumn extends IdColumn {
			
			@Override
			protected String getConfiguredHeaderText() {
				return "Color";
			}
			
			@Override
			protected boolean getConfiguredDisplayable() {
				return true;
			}
			
			@Override
			protected void execDecorateCell(Cell cell, ITableRow row) {
				super.execDecorateCell(cell, row);
				Color value = getValue(row);
				if (value != null) {
					cell.setBackgroundColor(Integer.toHexString(value.getRGB()).substring(2));
					cell.setText(null);
				}
			}
			
		}
		
	}
}
