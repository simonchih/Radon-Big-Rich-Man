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

import java.util.Random;
import javax.swing.JFrame;

public class Game_Loop implements Runnable {

	private static final double speedModifier = 1.0;

	public JFrame jf;
	public Game mygame;
	public mcanvas mycanvas;
	public Game_Map game_map;
	public BuyLand buy_land;
	public Build_House build_house;
	public AI ai;
	public Thread t_game;
	public boolean susp;

	Game_Loop(final Game game, final mcanvas canvas, final Game_Map gm, final JFrame jframe) {
		mygame = game;
		mycanvas = canvas;
		game_map = gm;
		jf = jframe;
		t_game = new Thread(this);
		buy_land = new BuyLand(game, gm, this);
		build_house = new Build_House(game, gm, this);
		ai = new AI(game, gm, this);
	}

	@Override
	public void run() {
		int one_step, n, id;
		long cash;
		boolean[] no_cross_cash = {true, true, true, true};

		//ini roll dice button to false (for player1 to AI);
		mygame.btnNewButton.setEnabled(false);

		while (player_number(mygame) > 1)
		{
			for (int i = 0; i < Game.max_p_size; i++) {
				if (i == mygame.turn) {
					if (9 == mygame.p_type[i]) {
						mygame.turn = (mygame.turn + 1) % Game.max_p_size;
					} else if (mygame.p_stop[i] > 0) {
						mygame.p_stop[i]--;
						if (1 == mygame.p_in_jail[i]) {
							mygame.p_status[i] = "In jail. Stop " + mygame.p_stop[mygame.turn] + " turn.";
						} else {
							mygame.p_status[i] = "Stop " + mygame.p_stop[mygame.turn] + " turn.";
						}
						mygame.turn = (mygame.turn + 1) % Game.max_p_size;
					} else {
						mygame.p_in_jail[i] = 0;
						mygame.p_status[i] = "0";
						if (!mygame.move_start && 0 == mygame.p_type[i]) {
							mygame.btnNewButton.setEnabled(true);
							break;
						} else if (1 == mygame.p_type[i]) {
							mygame.dice.Roll_Dice();
							mygame.move_start = true;
							mygame.p_dest_id[i] = (mygame.p_id[i] + mygame.dice.count) % game_map.Size;
							break;
						}
					}
				}
			}

			// if p_money < 0
			for (int i = 0; i < Game.max_p_size; i++) {
				if (9 != mygame.p_type[i]) {
					if (mygame.p_money[i] < 0) {
						for (int j = 0; j < game_map.Size; j++) {
							if (i + 1 == game_map.owner[j]) {
								game_map.owner[j] = 0;
								game_map.level[j] = 0;
							}
						}

						mygame.p_status[i] = "Broken";
						mygame.p_type[i] = 9;
					}
				}
			}

			jf.repaint();
			//mycanvas.paintImmediately(0, 0, mycanvas.max_size, mycanvas.max_size);

			while (mygame.p_id[0] != mygame.p_dest_id[0]
					|| mygame.p_id[1] != mygame.p_dest_id[1]
					|| mygame.p_id[2] != mygame.p_dest_id[2]
					|| mygame.p_id[3] != mygame.p_dest_id[3])
			{
				try {
					Thread.sleep((long) Math.max(1, speedModifier * 5));
				} catch (final InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				for (int i = 0; i < Game.max_p_size; i++) {
					if (mygame.p_id[i] != mygame.p_dest_id[i]) {
						if (!no_cross_cash[i] && 0 == mygame.p_id[i]) {
							no_cross_cash[i] = true;
							mygame.deal(mygame.cross_cash, i, "Get: ");
						}
						if (mygame.p_x_now[i] == game_map.p_x[i][(mygame.p_id[i] + 1) % game_map.Size]
								&& mygame.p_y_now[i] == game_map.p_y[i][(mygame.p_id[i] + 1) % game_map.Size])
						{
							mygame.p_id[i] = (mygame.p_id[i] + 1) % game_map.Size;
						}
						if (mygame.p_x_now[i] != game_map.p_x[i][(mygame.p_id[i] + 1) % game_map.Size])
						{
							one_step = (game_map.p_x[i][(mygame.p_id[i] + 1) % game_map.Size] > mygame.p_x_now[i]) ? 1 : -1;
							mygame.p_x_now[i] = mygame.p_x_now[i] + one_step;
						}
						if (mygame.p_y_now[i] != game_map.p_y[i][(mygame.p_id[i] + 1) % game_map.Size])
						{
							one_step = (game_map.p_y[i][(mygame.p_id[i] + 1) % game_map.Size] > mygame.p_y_now[i]) ? 1 : -1;
							mygame.p_y_now[i] = mygame.p_y_now[i] + one_step;
						}
					}
					if (i == mygame.turn
							&& mygame.move_start
							&& mygame.p_id[i] == mygame.p_dest_id[i])
					{
						mygame.move_start = false;
						id = mygame.p_dest_id[mygame.turn];
						no_cross_cash[i] = (0 == mygame.p_dest_id[i]);
						//land: 0 == mygame.p_type[mygame.turn]
						if (0 == game_map.type[id] && 0 == mygame.p_type[mygame.turn]) {
							susp = true;
							if (0 == game_map.owner[id]) {
								buy_land.show();
							} else if (mygame.turn + 1 == game_map.owner[id]) {
								build_house.show();
							} else {
								//pay for land owner
								payout();
								susp = false;
							}
							while (susp) {
								try {
									Thread.sleep((long) (speedModifier * 500));
								} catch (final InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						else if (0 == game_map.type[id]) { // AI for land
							if (0 == game_map.owner[id]) {
								ai.buy_land();
							} else if (mygame.turn + 1 == game_map.owner[id]) {
								ai.build_house();
							} else {
								//pay for land owner
								payout();
							}
						}
						// Chance: 2 == game_map.type[id]
						else if (2 == game_map.type[id]) {
							Random rand = new Random();
							n = rand.nextInt(11);

							if (n < 3) {
								cash = 100 * (rand.nextInt(30) + 1);
								mygame.deal((-1)*cash, mygame.turn, "Lost: ");
							} else if (n < 6) {
								cash = 100 * (rand.nextInt(30) + 1);
								mygame.deal(cash, mygame.turn, "Get: ");
							} else if (6 == n) {
								cash = -1 * (mygame.p_money[mygame.turn] / 100) * 10;
								if (0 == cash) {
									mygame.p_status[mygame.turn] = "Nothing happen.";
								} else {
									mygame.deal(cash, mygame.turn, "Income Tax: ");
								}
							} else if (7 == n) {
								go_parking();
								continue;
							} else if (8 == n) {
								go_start();
								continue;
							} else if (9 == n) {
								forward();
								jf.repaint();
								try {
									Thread.sleep((long) (speedModifier * 2000));
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								continue;
							} else if (10 == n) {
								//stop once
								stop(1);
							}
						}
						//Others: 3 == game_map.type[id]
						else if (3 == game_map.type[id]) {
							// 36~39: others
							// 36: go jail
							// 37: go hospital
							// 38: land tax
							// 39: house tax
							switch (game_map.id[id]) {
								case 36:
									// go jail
									go_jail();
									continue;
								case 37:
									//go hospital
									go_hospital();
									continue;
								case 38:
									land_tax();
									break;
								case 39:
									house_tax();
									break;
								default:
									break;
							}
						}
						if (mygame.property.frame.isVisible()) {
							mygame.property.show(game_map);
						}
						if (1 == mygame.p_type[mygame.turn]) { // p_type == AI
							jf.repaint();
							try {
								Thread.sleep((long) (speedModifier * 2000));
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						mygame.turn = (mygame.turn + 1) % Game.max_p_size;
					}
				}

				jf.repaint();
				//mycanvas.paintImmediately(0, 0, mycanvas.max_size, mycanvas.max_size);
			}
		}
		if (mygame.property.frame.isVisible()) {
			mygame.property.show(game_map);
		}
	}

	public static int player_number(final Game game) {
		int n = 0;
		final int numPlayers = 4;
		for (int pi = 0; pi < numPlayers; pi++) {
			if (game.p_type[pi] != 9) {
				n++;
			}
		}
		return n;
	}

	public void payout() { // pay for land owner
		long fee;
		int id, doub, owner;
		id = mygame.p_dest_id[mygame.turn];

		doub = mygame.double_fee(game_map, id);
		fee = mygame.toll(game_map, doub, id);

		owner = game_map.owner[id] - 1;
		if (1 != mygame.p_in_jail[owner]) {
			mygame.deal(-1 * fee, mygame.turn, "Toll: ");
			mygame.deal(fee, owner, "Get ");
		}
	}
	public void land_tax() {
		int turn_id = mygame.turn;
		int land_number = 0;
		long tax = 400, fee = 0;
		for (int i = 0; i < game_map.Size; i++) {
			if (turn_id + 1 == game_map.owner[i] && 0 == game_map.type[i]) {
				land_number++;
			}
		}
		fee = tax * land_number;
		mygame.deal(-1 * fee, turn_id, "Land Tax: ");
	}

	public void house_tax() {
		int turn_id = mygame.turn;
		int house_number = 0;
		long tax = 200, fee = 0;
		for (int i = 0; i < game_map.Size; i++) {
			if (turn_id + 1 == game_map.owner[i] && 0 == game_map.type[i]) {
				house_number += game_map.level[i];
			}
		}
		fee = tax * house_number;
		mygame.deal(-1 * fee, turn_id, "House Tax: ");
	}

	public void go_jail() {
		int turn_id = mygame.turn;

		mygame.p_in_jail[turn_id] = 1;
		mygame.move_start = true;
		mygame.p_id[turn_id] = (game_map.jail_id - 1) % game_map.Size;
		mygame.p_dest_id[turn_id] = game_map.jail_id;
		stop(3);
	}

	public void go_hospital() {
		int turn_id = mygame.turn;
		//mygame.p_stop[turn_id] = 1;
		//mygame.p_status[turn_id] = "Stop "+mygame.p_stop[turn_id]+" turn.";
		mygame.move_start = true;
		mygame.p_dest_id[turn_id] = game_map.hospital_id;
		mygame.p_id[turn_id] = (game_map.hospital_id - 1) % game_map.Size;
		mygame.deal(-1 * mygame.hospital_fee, turn_id, "Hospital Fee: ");
	}
	public void go_parking() {
		int turn_id = mygame.turn;
		mygame.p_status[turn_id] = "Go to parking lot.";
		mygame.move_start = true;
		mygame.p_id[turn_id] = (game_map.parking_id - 1) % game_map.Size;
		mygame.p_dest_id[turn_id] = game_map.parking_id;
	}
	public void go_start() {
		int turn_id = mygame.turn;
		mygame.p_status[turn_id] = "Go to start point.";
		mygame.move_start = true;
		mygame.p_id[turn_id] = game_map.Size - 1;
		mygame.p_dest_id[turn_id] = 0;
	}
	public void forward() {
		int move_step, turn_id = mygame.turn;
		final Random rand = new Random();
		// forward step 1~12
		move_step = rand.nextInt(12)+ 1;
		mygame.p_status[turn_id] = "Forward " + move_step + " step(s).";
		mygame.move_start = true;
		mygame.p_dest_id[turn_id] = (mygame.p_id[turn_id] + move_step) % game_map.Size;
	}
	public void stop(int stop_turn) {
		int turn_id = mygame.turn;
		mygame.p_stop[turn_id] = stop_turn;
		//Bug issue (Can't reproduce):
		//go to Jail big block area should NOT Stop 3 turns , go to Jail only if on "go to Jail" block (won't fix)

		if (1 == mygame.p_in_jail[turn_id]) {
			mygame.p_status[turn_id] = "In jail. Stop " + stop_turn + " turn.";
		} else {
			mygame.p_status[turn_id] = "Stop " + stop_turn + " turn.";
		}
	}
}
