
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