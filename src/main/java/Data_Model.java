/*
 * Copyright (C) 2017 Simon <ficstudio@yahoo.com.tw>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.List;
import javax.swing.table.AbstractTableModel;

class Data_Model extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private final List<String> columnNames;
	private final List<List<?>> rowData;

	Data_Model(final List<List<?>> rowData, final List<String> columnNames) {
		this.columnNames = columnNames;
		this.rowData = rowData;
	}

	@Override
	public String getColumnName(final int column) {
		return columnNames.get(column);
	}

	@Override
	public int getColumnCount() {
		return columnNames.size();
	}

	@Override
	public int getRowCount() {
		return rowData.size();
	}

	@Override
	public Object getValueAt(final int row, final int col) {
		return rowData.get(row).get(col);
	}

	@Override
	public Class<? extends Object> getColumnClass(final int column) {
		return getValueAt(0, column).getClass();
	}
}
