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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BuyLand {

	public Game mygame;
	public mcanvas mycanvas;
	public GameMap game_map;
	public Game_Loop mygl;
	public JFrame bl;

	BuyLand(final Game game, final GameMap gm, final Game_Loop gl) {
		this.bl = new JFrame("Buy Lnad");
		this.bl.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.mygame = game;
		//mycanvas = canvas;
		this.game_map = gm;
		this.mygl = gl;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void show() {
		final int turn_id = mygame.turn;

		bl.getContentPane().removeAll();
		bl.setSize(450, 300);
		bl.getContentPane().setLayout(null);

		final JLabel lblNewLabel = new JLabel("Hi, "+mygame.p_name[turn_id]+":");
		lblNewLabel.setBounds(10, 10, 414, 15);
		bl.getContentPane().add(lblNewLabel);

		final JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				bl.dispose();
				mygl.susp = false;
			}
		});
		btnNewButton.setBounds(55, 214, 87, 23);
		bl.getContentPane().add(btnNewButton);

		final JButton btnNewButton_1 = new JButton("Buy Land");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				//mygame.p_money[turn_id] -= game_map.value[mygame.p_dest_id[turn_id]];
				mygame.deal((-1) * game_map.value[mygame.p_dest_id[turn_id]], turn_id, "Buy Land: ");

				game_map.owner[mygame.p_dest_id[turn_id]] = turn_id + 1;
				bl.dispose();
				mygl.susp = false;
			}
		});
		btnNewButton_1.setBounds(198, 214, 188, 23);
		bl.getContentPane().add(btnNewButton_1);

		final JLabel lblNewLabel_1 = new JLabel("Do you want to buy " + game_map.name[mygame.p_dest_id[turn_id]]+" with $"+game_map.value[mygame.p_dest_id[turn_id]]+"?");
		lblNewLabel_1.setBounds(20, 26, 404, 15);
		bl.getContentPane().add(lblNewLabel_1);

		if(game_map.value[mygame.p_dest_id[turn_id]] > mygame.p_money[turn_id]){
			final JLabel lblNewLabel_2 = new JLabel("You have NOT enough money!");
			lblNewLabel_2.setBounds(20, 189, 404, 15);
			lblNewLabel_2.setForeground(Color.RED);
			bl.getContentPane().add(lblNewLabel_2);
			btnNewButton_1.setEnabled(false);
		}
		bl.setVisible(true);
	}
}
