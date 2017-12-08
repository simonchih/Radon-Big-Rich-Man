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

	private final Game game;
	private final GameMap gameMap;
	private final GameLoop gameLoop;
	private final JFrame bl;

	BuyLand(final Game game, final GameMap gameMap, final GameLoop gameLoop) {
		this.bl = new JFrame("Buy Lnad");
		this.bl.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.game = game;
		this.gameMap = gameMap;
		this.gameLoop = gameLoop;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void show() {
		final int turn_id = game.turn;

		bl.getContentPane().removeAll();
		bl.setSize(450, 300);
		bl.getContentPane().setLayout(null);

		final JLabel lblNewLabel = new JLabel("Hi, "+game.p_name[turn_id]+":");
		lblNewLabel.setBounds(10, 10, 414, 15);
		bl.getContentPane().add(lblNewLabel);

		final JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				bl.dispose();
				gameLoop.susp = false;
			}
		});
		btnNewButton.setBounds(55, 214, 87, 23);
		bl.getContentPane().add(btnNewButton);

		final JButton btnNewButton_1 = new JButton("Buy Land");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				//mygame.p_money[turn_id] -= gameMap.value[mygame.p_dest_id[turn_id]];
				game.deal((-1) * gameMap.value[game.p_dest_id[turn_id]], turn_id, "Buy Land: ");

				gameMap.owner[game.p_dest_id[turn_id]] = turn_id + 1;
				bl.dispose();
				gameLoop.susp = false;
			}
		});
		btnNewButton_1.setBounds(198, 214, 188, 23);
		bl.getContentPane().add(btnNewButton_1);

		final JLabel lblNewLabel_1 = new JLabel("Do you want to buy " + gameMap.name[game.p_dest_id[turn_id]]+" with $"+gameMap.value[game.p_dest_id[turn_id]]+"?");
		lblNewLabel_1.setBounds(20, 26, 404, 15);
		bl.getContentPane().add(lblNewLabel_1);

		if(gameMap.value[game.p_dest_id[turn_id]] > game.p_money[turn_id]) {
			final JLabel lblNewLabel_2 = new JLabel("You have NOT enough money!");
			lblNewLabel_2.setBounds(20, 189, 404, 15);
			lblNewLabel_2.setForeground(Color.RED);
			bl.getContentPane().add(lblNewLabel_2);
			btnNewButton_1.setEnabled(false);
		}
		bl.setVisible(true);
	}
}
