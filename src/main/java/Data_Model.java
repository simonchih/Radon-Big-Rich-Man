import java.util.Vector;

import javax.swing.table.*;

class Data_Model extends AbstractTableModel{
			  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Vector<String> columnNames;
	Vector<Object> rowData;
			
	Data_Model(Vector<Object> data1, Vector<String> Column_Name){
		columnNames = Column_Name;
		rowData = data1;
	};
	public String getColumnName(int column) {
		return columnNames.elementAt(column);
	}
	public int getColumnCount() { return columnNames.size(); }
	public int getRowCount() { return rowData.size();}
	public Object getValueAt(int row, int col) { return ((Vector<?>) rowData.get(row)).get(col); }
	public Class<? extends Object> getColumnClass(int column) {
		return (getValueAt(0, column).getClass());
	}
}