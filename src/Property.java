import java.awt.Color;
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
	private void AddElementToTable(Vector<Object> datat, Game_Map game_map, int i){
		long fee, basic_money;
		int doub, owner1, owner2, owner3;
		boolean db;
		Vector<Object> data = new Vector<Object>();
		
		owner1 = game_map.owner[game_map.same_color[game_map.color_index(game_map.color[i])][0]];
		owner2 = game_map.owner[game_map.same_color[game_map.color_index(game_map.color[i])][1]];
		owner3 = game_map.owner[game_map.same_color[game_map.color_index(game_map.color[i])][2]];
		if(owner1 == owner2 && owner2 == owner3)doub = 2;
		else doub = 1;
		
		if(2 == doub)db = true;
		else db = false;
		
		basic_money = (long)((0.2)*game_map.value[i]);
		fee = (long) (doub*Math.pow(2, game_map.level[i])*basic_money);
		
		data.addElement(game_map.color[i]);
		data.addElement(game_map.name[i]);
		data.addElement(game_map.value[i]);
		data.addElement(game_map.level[i]);
		data.addElement(db);
		data.addElement(fee);
		datat.addElement(data);
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
		
		Vector<Object> data1 = new Vector<Object>();
		Vector<Object> data2 = new Vector<Object>();
		Vector<Object> data3 = new Vector<Object>();
		Vector<Object> data4 = new Vector<Object>();
		
		for(i=0; i<game_map.Size; i++){
			o = game_map.owner[i];
			switch(o){
				case 1:
					AddElementToTable(data1, game_map, i);
					break;
				case 2:
					AddElementToTable(data2, game_map, i);
					break;
				case 3:
					AddElementToTable(data3, game_map, i);
					break;
				case 4:
					AddElementToTable(data4, game_map, i);
					break;
				//default:
					//System.out.println(o);
			}
		}
				
		tabbedPane.removeAll();
		
		Data_Model dataModel1 = new Data_Model(data1, Column_Name);
		Data_Model dataModel2 = new Data_Model(data2, Column_Name);
		Data_Model dataModel3 = new Data_Model(data3, Column_Name);
		Data_Model dataModel4 = new Data_Model(data4, Column_Name);
		
		//table = new JTable(data1, Column_Name);
		table = new JTable(dataModel1);
		//Set up renderer and editor for the Favorite Color column.
        table.setDefaultRenderer(Color.class, new ColorRenderer(true));
	    table.setEnabled(false);
		tabbedPane.addTab(mygame.p_name[0] + Property, null, new JScrollPane(table), null);
		
		table_1 = new JTable(dataModel2);
		table_1.setDefaultRenderer(Color.class, new ColorRenderer(true));
		table_1.setEnabled(false);
		tabbedPane.addTab(mygame.p_name[1] + Property, null, new JScrollPane(table_1), null);
		
		table_2 = new JTable(dataModel3);
		table_2.setDefaultRenderer(Color.class, new ColorRenderer(true));
		table_2.setEnabled(false);
		tabbedPane.addTab(mygame.p_name[2] + Property, null, new JScrollPane(table_2), null);
		
		table_3 = new JTable(dataModel4);
		table_3.setDefaultRenderer(Color.class, new ColorRenderer(true));
		table_3.setEnabled(false);
		tabbedPane.addTab(mygame.p_name[3] + Property, null, new JScrollPane(table_3), null);
		
		ShowTab(tabbedPane);
	}
}