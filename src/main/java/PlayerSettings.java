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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PlayerSettings {

	private static final int NUM_PLAYERS = 4;

	private final int playerNum;
	private final int playerIdx;

	public PlayerSettings(final int playerIdx) {

		this.playerIdx = playerIdx;
		this.playerNum = playerIdx + 1;
	}

	/**
	 * @param f
	 * @param Game
	 * @wbp.parser.entryPoint
	 */
	public void show(final JFrame f, final game Game) {

		final boolean firstPlayer = (playerIdx == 0);
		final boolean humanPlayer = firstPlayer;
		final boolean lastPlayer = (playerIdx == (NUM_PLAYERS - 1));

		final PlayerSettings gs3;
		final Main_Map mmap;
		if (lastPlayer) {
			gs3 = null;
			mmap = new Main_Map(Game);
		} else {
			gs3 = new PlayerSettings(playerIdx + 1);
			mmap = null;
		}
		JFrame games1 = new JFrame("Player" + playerNum + " Setting");
		games1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		games1.getContentPane().removeAll();
		games1.setSize(450, 320);
		games1.getContentPane().setLayout(null);

		final JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(122, 40, 46, 15);
		games1.getContentPane().add(lblNewLabel);

		final JEditorPane dtrpnPlayer = new JEditorPane();
		dtrpnPlayer.setText("Player" + playerNum);
		dtrpnPlayer.setBounds(205, 34, 106, 21);
		games1.getContentPane().add(dtrpnPlayer);

		final JLabel lblHumanai = new JLabel("Human/AI");
		lblHumanai.setBounds(122, 79, 68, 15);
		games1.getContentPane().add(lblHumanai);

		final JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Human", "AI"}));
		comboBox.setSelectedIndex(humanPlayer ? 0 : 1);
		comboBox.setBounds(205, 76, 106, 21);
		games1.getContentPane().add(comboBox);

		final JLabel lblIcon = new JLabel("Icon");
		lblIcon.setBounds(122, 124, 46, 15);
		games1.getContentPane().add(lblIcon);

		final JComboBox<ImageIcon> comboBox_1 = new JComboBox<>();
		comboBox_1.setBounds(205, 118, 106, 21);
		ImageIcon[] array = new ImageIcon[] {Game.image1, Game.image2, Game.image3, Game.image4, Game.image5, Game.image6, Game.image7, Game.image8};
		final List<ImageIcon> list = new ArrayList<>(Arrays.asList(array));
		for (int i = 0; i < playerIdx; i++) {
			list.remove(Game.p_icon[i]);
		}
		array = list.toArray(new ImageIcon[0]);
		comboBox_1.setModel(new DefaultComboBoxModel<>(array));
		comboBox_1.setSelectedIndex(0);
		games1.getContentPane().add(comboBox_1);

		final JLabel lblNewLabel_1 = new JLabel("Money");
		lblNewLabel_1.setBounds(122, 165, 68, 15);
		games1.getContentPane().add(lblNewLabel_1);

		final JEditorPane editorPane = new JEditorPane();
		editorPane.setText("30000");
		editorPane.setBounds(205, 159, 106, 21);
		games1.getContentPane().add(editorPane);

		final JButton btnCancel = new JButton("Previous");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				games1.setVisible(false);
				f.setVisible(true);
			}
		});
		btnCancel.setBounds(85, 214, 155, 23);
		games1.getContentPane().add(btnCancel);

		final JLabel lblNewLabel_2 = new JLabel("(error value)");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(321, 165, 103, 15);
		lblNewLabel_2.setVisible(false);
		games1.getContentPane().add(lblNewLabel_2);

		final JLabel lblErrorValue = new JLabel("(error value)");
		lblErrorValue.setForeground(Color.RED);
		lblErrorValue.setBounds(321, 40, 103, 15);
		lblErrorValue.setVisible(false);
		games1.getContentPane().add(lblErrorValue);

		final JButton btnNext = new JButton("Next");
		if (lastPlayer) {
			btnNext.setText("Finish");
		}
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ImageIcon[] array = new ImageIcon[] {Game.image1, Game.image2, Game.image3, Game.image4, Game.image5, Game.image6, Game.image7, Game.image8};
				ImageIcon[] parray = new ImageIcon[] {Game.imagep1, Game.imagep2, Game.imagep3, Game.imagep4, Game.imagep5, Game.imagep6, Game.imagep7, Game.imagep8};

				final List<ImageIcon> list = new ArrayList<>(Arrays.asList(array));
				for (int i = 0; i < playerIdx; i++) {
					list.remove(Game.p_icon[i]);
				}
				array = list.toArray(new ImageIcon[0]);
				final List<ImageIcon> listp = new ArrayList<>(Arrays.asList(parray));
				for (int i = 0; i < playerIdx; i++) {
					listp.remove(Game.p_icon[i]);
				}
				parray = listp.toArray(new ImageIcon[0]);

				try {
					Game.p_name[playerIdx] = dtrpnPlayer.getText();
				} catch(final Exception e1) {
					lblErrorValue.setVisible(true);
					return;
				}
				lblErrorValue.setVisible(false);

				try {
					Game.p_money[playerIdx] = Long.parseLong(editorPane.getText());
				} catch(final NumberFormatException e2) {
					lblNewLabel_2.setVisible(true);
					return;
				}
				lblNewLabel_2.setVisible(false);

				Game.p_type[playerIdx] = comboBox.getSelectedIndex();
				Game.p_icon[playerIdx] = comboBox_1.getSelectedIndex();
				Game.p_ic[playerIdx] = array[Game.p_icon[playerIdx]];
				Game.p_pawn[playerIdx] = parray[Game.p_icon[playerIdx]];

				games1.setVisible(false);
				if (lastPlayer) {
					mmap.generate_map(Game);
				} else {
					gs3.show(games1, Game);
				}
			}
		});
		btnNext.setBounds(250, 214, 94, 23);
		games1.getContentPane().add(btnNext);
		games1.setVisible(true);
	}
}
