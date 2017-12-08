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

public class AI {

	private final Game mygame;
	private final Game_Map game_map;

	public AI(final Game game, final Game_Map gm, final Game_Loop gl) {
		this.mygame = game;
		this.game_map = gm;
	}

	public void buy_land() {
		final int turn_id = mygame.turn;
		final int id = mygame.p_dest_id[turn_id];

		// Won't buy if buy land then player money less than 3500
		if (mygame.p_money[turn_id]-game_map.value[id] >= 3500) {
			mygame.deal(-1 * game_map.value[mygame.p_dest_id[turn_id]], turn_id, "Buy Land: ");
			game_map.owner[id] = turn_id + 1;
		}
	}

	public void build_house() {
		final int turn_id = mygame.turn;
		final int id = mygame.p_dest_id[turn_id];

		long spent;
		switch (game_map.level[id]) {
			case 4:
				return;
			case 3:
				spent = (long) (game_map.value[mygame.p_dest_id[turn_id]] * 0.4);
				break;
			default:
				spent = (long) (game_map.value[mygame.p_dest_id[turn_id]] * 0.2);
				break;
		}

		// Won't build if build house or hotel then player money less than 2500
		if (mygame.p_money[turn_id] - spent >= 2500) {
			mygame.deal((-1)*spent, turn_id, "Building...");
			game_map.level[id] += 1;
		}
	}
}
