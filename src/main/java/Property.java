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

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class Property {

	private final Game game;
	private final JFrame frame;
	private final JTabbedPane tabbedPane;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;

	Property(final Game game) {
		this.tabbedPane = new JTabbedPane();
		this.frame = new JFrame("Property");
		this.game = game;
	}
	private void AddElementToTable(final List<List<?>> datat, final GameMap gameMap, final int i) {
		long fee;
		int doub;
		boolean db;
		final List<Object> data = new ArrayList<>();

		doub = game.double_fee(gameMap, i);

		db = (2 == doub);

		fee = game.toll(gameMap, doub, i);

		data.add(gameMap.color[i]);
		data.add(gameMap.name[i]);
		data.add(gameMap.value[i]);
		data.add(gameMap.level[i]);
		data.add(db);
		data.add(fee);
		datat.add(data);
	}
	private void cTab(JTabbedPane tabp) {

		frame.getContentPane().removeAll();
		frame.setSize(650, 300);
		frame.getContentPane().add(tabp);
		frame.setVisible(true);
	}

    public boolean isVisible() {
		return frame.isVisible();
	}

	/**
	 * @param gameMap
	 * @wbp.parser.entryPoint
	 */
	protected void show(final GameMap gameMap) {

		int t_index = 0;
		String Property = "'s Property";

		if (frame.isVisible()) {
			t_index = tabbedPane.getSelectedIndex();
		}
		final List<String> Column_Name = new ArrayList<>();
		Column_Name.add("Color");
		Column_Name.add("Name");
		Column_Name.add("Value");
		Column_Name.add("House Level");
		Column_Name.add("Double");
		Column_Name.add("Toll");

		final List<List<?>> data1 = new ArrayList<>();
		final List<List<?>> data2 = new ArrayList<>();
		final List<List<?>> data3 = new ArrayList<>();
		final List<List<?>> data4 = new ArrayList<>();

		for (int i = 0; i < gameMap.size; i++) {
			final int o = gameMap.owner[i];
			switch(o) {
				case 1:
					AddElementToTable(data1, gameMap, i);
					break;
				case 2:
					AddElementToTable(data2, gameMap, i);
					break;
				case 3:
					AddElementToTable(data3, gameMap, i);
					break;
				case 4:
					AddElementToTable(data4, gameMap, i);
					break;
				//default:
					//System.out.println(o);
			}
		}

		frame.setVisible(false);
		tabbedPane.removeAll();

		final TableModel dataModel1 = new ListBasedDataModel(data1, Column_Name);
		final TableModel dataModel2 = new ListBasedDataModel(data2, Column_Name);
		final TableModel dataModel3 = new ListBasedDataModel(data3, Column_Name);
		final TableModel dataModel4 = new ListBasedDataModel(data4, Column_Name);

		table = new JTable(dataModel1);
		table.setDefaultRenderer(Color.class, new ColorRenderer(true));
		table.setEnabled(false);
		tabbedPane.addTab(game.p_name[0] + Property, null, new JScrollPane(table), null);

		table_1 = new JTable(dataModel2);
		table_1.setDefaultRenderer(Color.class, new ColorRenderer(true));
		table_1.setEnabled(false);
		tabbedPane.addTab(game.p_name[1] + Property, null, new JScrollPane(table_1), null);

		table_2 = new JTable(dataModel3);
		table_2.setDefaultRenderer(Color.class, new ColorRenderer(true));
		table_2.setEnabled(false);
		tabbedPane.addTab(game.p_name[2] + Property, null, new JScrollPane(table_2), null);

		table_3 = new JTable(dataModel4);
		table_3.setDefaultRenderer(Color.class, new ColorRenderer(true));
		table_3.setEnabled(false);
		tabbedPane.addTab(game.p_name[3] + Property, null, new JScrollPane(table_3), null);

		tabbedPane.setSelectedIndex(t_index);

		cTab(tabbedPane);
	}
}
