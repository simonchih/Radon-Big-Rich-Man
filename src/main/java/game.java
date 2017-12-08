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

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class game {
	public static Game_Setting gs1 = new Game_Setting();
	public ImageIcon image1 = new ImageIcon(game.class.getResource("/Image/gtkmonop-token1.png"));
	public ImageIcon image2 = new ImageIcon(game.class.getResource("/Image/gtkmonop-token8.png"));
	public ImageIcon image3 = new ImageIcon(game.class.getResource("/Image/gtkmonop-token7.png"));
	public ImageIcon image4 = new ImageIcon(game.class.getResource("/Image/gtkmonop-token6.png"));
	public ImageIcon image5 = new ImageIcon(game.class.getResource("/Image/gtkmonop-token5.png"));
	public ImageIcon image6 = new ImageIcon(game.class.getResource("/Image/gtkmonop-token4.png"));
	public ImageIcon image7 = new ImageIcon(game.class.getResource("/Image/gtkmonop-token3.png"));
	public ImageIcon image8 = new ImageIcon(game.class.getResource("/Image/gtkmonop-token2.png"));

	public ImageIcon imagep1 = new ImageIcon(game.class.getResource("/Image/pawn1.gif"));
	public ImageIcon imagep2 = new ImageIcon(game.class.getResource("/Image/pawn2.gif"));
	public ImageIcon imagep3 = new ImageIcon(game.class.getResource("/Image/pawn3.gif"));
	public ImageIcon imagep4 = new ImageIcon(game.class.getResource("/Image/pawn4.gif"));
	public ImageIcon imagep5 = new ImageIcon(game.class.getResource("/Image/pawn5.gif"));
	public ImageIcon imagep6 = new ImageIcon(game.class.getResource("/Image/pawn6.gif"));
	public ImageIcon imagep7 = new ImageIcon(game.class.getResource("/Image/pawn7.gif"));
	public ImageIcon imagep8 = new ImageIcon(game.class.getResource("/Image/pawn8.gif"));

	public ImageIcon iarrow = new ImageIcon(game.class.getResource("/Image/gtkmonop-go-0.png"));
	public ImageIcon ijail = new ImageIcon(game.class.getResource("/Image/jail.jpg"));
	public ImageIcon iparking = new ImageIcon(game.class.getResource("/Image/Parking.jpg"));
	public ImageIcon ihospital = new ImageIcon(game.class.getResource("/Image/Hospital.jpg"));

	public ImageIcon ihouse = new ImageIcon(game.class.getResource("/Image/house.png"));
	public ImageIcon ihouse_left = new ImageIcon(game.class.getResource("/Image/house_left.png"));
	public ImageIcon ihouse_up = new ImageIcon(game.class.getResource("/Image/house_up.png"));
	public ImageIcon ihouse_right = new ImageIcon(game.class.getResource("/Image/house_right.png"));
	public ImageIcon ihotel = new ImageIcon(game.class.getResource("/Image/hotel.png"));
	public ImageIcon ihotel_left = new ImageIcon(game.class.getResource("/Image/hotel_left.png"));
	public ImageIcon ihotel_up = new ImageIcon(game.class.getResource("/Image/hotel_up.png"));
	public ImageIcon ihotel_right = new ImageIcon(game.class.getResource("/Image/hotel_right.png"));

	public ImageIcon iquestionmark = new ImageIcon(game.class.getResource("/Image/questionmark_60x79.png"));
	public ImageIcon iquestionmark_left = new ImageIcon(game.class.getResource("/Image/questionmark_60x79_left.png"));
	public ImageIcon iquestionmark_right = new ImageIcon(game.class.getResource("/Image/questionmark_60x79_right.png"));
	public ImageIcon iquestionmark_up = new ImageIcon(game.class.getResource("/Image/questionmark_60x79_up.png"));

	public String s36_1 = "Go to";
	public String s36_2 = "Jail";
	public String s37_1 = "Go to";
	public String s37_2 = "Hospital";
	public String s38_1 = "Land";
	public String s38_2 = "Tax";
	public String s39_1 = "House";
	public String s39_2 = "Tax";

	public String skaching = "/Sound/kaching.wav";

	public JButton btnNewButton = new JButton("Roll Dice");
	public Dice dice = new Dice();

	public JButton btnPropertyButton = new JButton("Player's Property");
	public Property property = new Property(this);

	public static int max_p_size = 4;
	public int turn = 0; // 0: player1
	public boolean move_start = false;

	// 0: player 1 ...
	// 3: player 4
	public String[] p_name = new String[max_p_size];
	public int[] p_type = new int[max_p_size]; // 0:human, 1:AI, 9:out
	public int[] p_icon = new int[max_p_size];
	public ImageIcon[] p_ic = new ImageIcon[max_p_size];
	public ImageIcon[] p_pawn = new ImageIcon[max_p_size];
	public long[] p_money = new long[max_p_size];
	public int[] p_x_now = new int[max_p_size];
	public int[] p_y_now = new int[max_p_size];
	public int[] p_id = new int[max_p_size];
	public int[] p_dest_id = new int[max_p_size];
	public String[] p_status = new String[max_p_size];
	public int[] sp_x = new int[max_p_size];
	public int[] sp_y = new int[max_p_size];
	public int[] p_in_jail = new int[max_p_size]; // 0:out of jail, 1: in jail
	public int[] p_stop = new int[max_p_size]; // 0:continue, 1: stop 1 turn...

	public long cross_cash = 2000;
	public long hospital_fee = 1000;

	//playSound modified from http://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java
	//I personally made this code that works fine. I think it only works with .wav format.
	public static synchronized void playSound(final String url) {
		new Thread(new Runnable() {
			// The wrapper thread is unnecessary, unless it blocks on the
			// Clip finishing
			@Override
			public void run() {
				try {
					final Clip clip = AudioSystem.getClip();
					final AudioInputStream inputStream = AudioSystem.getAudioInputStream(
							game.class.getResource(url));
					clip.open(inputStream);
					clip.start();
				} catch (final IOException | LineUnavailableException | UnsupportedAudioFileException e) {
					System.err.println(e.getMessage());
				}
			}
		}).start();
		}

	public void deal(final long cash, final int turn_id, final String event) {
		p_money[turn_id] += cash;
		p_status[turn_id] = event + cash;
		if (cash < 0) {
			playSound(skaching);
		}
	}

	public int double_fee(final Game_Map game_map, final int i) {
		int doub, owner1, owner2, owner3;

		owner1 = game_map.owner[game_map.same_color[Game_Map.color_index(game_map.color[i])][0]];
		owner2 = game_map.owner[game_map.same_color[Game_Map.color_index(game_map.color[i])][1]];
		owner3 = game_map.owner[game_map.same_color[Game_Map.color_index(game_map.color[i])][2]];
		if (owner1 == owner2 && owner2 == owner3) {
			doub = 2;
		} else {
			doub = 1;
		}

		return doub;
	}

	public long toll(final Game_Map game_map, final int doub, final int i) {
		long fee, basic_money;

		basic_money = (long)(0.2 * game_map.value[i]);
		fee = (long) (doub * Math.pow(2, game_map.level[i]) * basic_money);

		return fee;
	}

	public static void main(final String[] args) {
		final game Game = new game();
		final JFrame frame = new JFrame("Random Big Rich Man");
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		final JButton btnANewGame = new JButton("A New Game");
		btnANewGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				frame.setVisible(false);
				gs1.show(frame, Game);
			}
		});
		btnANewGame.setBounds(10, 112, 264, 23);
		panel.add(btnANewGame);
	}
}
