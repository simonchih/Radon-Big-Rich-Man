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
	 * @param Vector 
	 * @wbp.parser.entryPoint
	 */
	protected void show(Game_Map game_map){
		
		int i, o;
		String Property = "'s Property";
		
		Vector<String> Column_Name = new Vector<String>();
		Column_Name.addElement("Color");
		Column_Name.addElement("Name");
		Column_Name.addElement("Value");
		Column_Name.addElement("House Level");
		Column_Name.addElement("Double");
		Column_Name.addElement("Toll");
		
		Vector<Object> data = new Vector<Object>();
		
		Vector<Object> data1 = new Vector<Object>();
		Vector<Object> data2 = new Vector<Object>();
		Vector<Object> data3 = new Vector<Object>();
		Vector<Object> data4 = new Vector<Object>();
		
		for(i=0; i<game_map.Size; i++){
			o = game_map.owner[i];
			switch(o){
				case 1:
					data.addElement("");
					data.addElement(game_map.name[i]);
					data.addElement(game_map.value[i]);
					data.addElement(game_map.level[i]);
					data.addElement("");
					data.addElement("");
					Vector<Object> copy = new Vector<Object>(data);
					data1.addElement(copy);
					data.removeAll(data);
					break;
				case 2:
					data.addElement("");
					data.addElement(game_map.name[i]);
					data.addElement(game_map.value[i]);
					data.addElement(game_map.level[i]);
					data.addElement("");
					data.addElement("");
					Vector<Object> copy2 = new Vector<Object>(data);
					data2.addElement(copy2);
					data.removeAll(data);
					break;
				case 3:
					data.addElement("");
					data.addElement(game_map.name[i]);
					data.addElement(game_map.value[i]);
					data.addElement(game_map.level[i]);
					data.addElement("");
					data.addElement("");
					Vector<Object> copy3 = new Vector<Object>(data);
					data3.addElement(copy3);
					data.removeAll(data);
					break;
				case 4:
					data.addElement("");
					data.addElement(game_map.name[i]);
					data.addElement(game_map.value[i]);
					data.addElement(game_map.level[i]);
					data.addElement("");
					data.addElement("");
					Vector<Object> copy4 = new Vector<Object>(data);
					data4.addElement(copy4);
					data.removeAll(data);
					break;
				default:
					System.out.println(o);
			}
		}
		
		/*
		//data.addElement("Red");
		data.addElement("Gutting");
		data.addElement(new Integer(2500));
		data.addElement(new Integer(0));
		data.addElement(new Boolean(false));
		data.addElement(new Integer(1000));
		*/
				
		tabbedPane.removeAll();
		
		table = new JTable(data1, Column_Name);
		table.setEnabled(false);
		tabbedPane.addTab(mygame.p_name[0] + Property, null, new JScrollPane(table), null);
		
		table_1 = new JTable(data2, Column_Name);
		table_1.setEnabled(false);
		tabbedPane.addTab(mygame.p_name[1] + Property, null, new JScrollPane(table_1), null);
		
		table_2 = new JTable(data3, Column_Name);
		table_2.setEnabled(false);
		tabbedPane.addTab(mygame.p_name[2] + Property, null, new JScrollPane(table_2), null);
		
		table_3 = new JTable(data4, Column_Name);
		table_3.setEnabled(false);
		tabbedPane.addTab(mygame.p_name[3] + Property, null, new JScrollPane(table_3), null);
		
		ShowTab(tabbedPane);
	}
}