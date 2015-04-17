import java.util.Vector;

import javax.swing.*;

public class Property {
	public game mygame;
	private JTabbedPane tabbedPane = new JTabbedPane();
	private JFrame frame = new JFrame("Property");
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	Property(game g){
		mygame = g;
	}
	private void ShowTab(JTabbedPane tabp) {
		
		frame.getContentPane().removeAll();

        frame.setSize(650, 300);
        
        frame.getContentPane().add(tabp);
        
        frame.setVisible(true);
    }
	/**
	 * @wbp.parser.entryPoint
	 */
	protected void show(Game_Map game_map){
		
		String Property = "'s Property";
		
		Vector<String> Column_Name = new Vector<String>();
		//Column_Name.addElement("Color");
		Column_Name.addElement("Name");
		Column_Name.addElement("Value");
		Column_Name.addElement("House Level");
		Column_Name.addElement("Double");
		Column_Name.addElement("Toll");
		
		Vector<Object> data = new Vector<Object>();
		
		//data.addElement("Red");
		data.addElement("Gutting");
		data.addElement(new Integer(2500));
		data.addElement(new Integer(0));
		data.addElement(new Boolean(false));
		data.addElement(new Integer(1000));
		
		Vector<Object> data1 = new Vector<Object>();
		
		data1.addElement(data);
				
		tabbedPane.removeAll();
		
		table = new JTable(data1, Column_Name);
		table.setEnabled(false);
		tabbedPane.addTab(mygame.p_name[0] + Property, null, new JScrollPane(table), null);
		
		table_1 = new JTable();
		tabbedPane.addTab(mygame.p_name[1] + Property, null, new JScrollPane(table_1), null);
		
		table_2 = new JTable();
		tabbedPane.addTab(mygame.p_name[2] + Property, null, new JScrollPane(table_2), null);
		
		table_3 = new JTable();
		tabbedPane.addTab(mygame.p_name[3] + Property, null, new JScrollPane(table_3), null);
		
		ShowTab(tabbedPane);
	}
}