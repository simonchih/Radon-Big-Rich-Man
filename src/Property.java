import javax.swing.*;

public class Property {
	public game mygame;
	private JTabbedPane tabbedPane = new JTabbedPane();
	//private JScrollPane scrollPane = new JScrollPane();
	private JFrame frame = new JFrame("Property");
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	Property(game g){
		mygame = g;
	}
	private void createAndShowGUI() {
		
		frame.getContentPane().removeAll();
		//Create and set up the window.
        frame.setSize(650, 300);
        
        //Add content to the window.
        frame.getContentPane().add(tabbedPane);
        
        //Display the window.
        frame.setVisible(true);
    }
	/**
	 * @wbp.parser.entryPoint
	 */
	protected void show(Game_Map game_map){
		
		String Property = "'s Property";
		String[] Column_Name = {
				"Name", "Value", "House Level", "Double", "Toll" 
				};
		
		Object[][] data1 = new Object[game_map.Size][5];
		
		//temp
		data1[0][0] = "Guting";
		data1[0][1] = new Integer(2500);
		data1[0][2] = new Integer(0);
		data1[0][3] = new Boolean(false);
		data1[0][4] = new Integer(1000);
		//end temp
		
		tabbedPane.removeAll();
		//scrollPane.removeAll();
		
		table = new JTable(data1, Column_Name);
		table.setEnabled(false);
		//scrollPane = new JScrollPane(table);
		tabbedPane.addTab(mygame.p_name[0] + Property, null, new JScrollPane(table), null);
		
		table_1 = new JTable();
		tabbedPane.addTab(mygame.p_name[1] + Property, null, new JScrollPane(table_1), null);
		
		table_2 = new JTable();
		tabbedPane.addTab(mygame.p_name[2] + Property, null, new JScrollPane(table_2), null);
		
		table_3 = new JTable();
		tabbedPane.addTab(mygame.p_name[3] + Property, null, new JScrollPane(table_3), null);
		
		//tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		//tabbedPane.setVisible(true);
		
		//scrollPane = new JScrollPane(tabbedPane);
		
		//scrollPane.setVisible(true);
		
		createAndShowGUI();
	}
}