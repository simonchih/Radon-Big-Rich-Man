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

	private final Game game;
	private final GameMap gameMap;

	public AI(final Game game, final GameMap gameMap, final GameLoop gameLoop) {
		this.game = game;
		this.gameMap = gameMap;
	}

	public void buy_land() {
		final int turnId = game.turn;
		final int id = game.p_dest_id[turnId];

		// Won't buy if buy land then player money less than 3500
		if (game.p_money[turnId]-gameMap.value[id] >= 3500) {
			game.deal(-1 * gameMap.value[game.p_dest_id[turnId]], turnId, "Buy Land: ");
			gameMap.owner[id] = turnId + 1;
		}
	}

	public void build_house() {
		final int turnId = game.turn;
		final int id = game.p_dest_id[turnId];

		long spent;
		switch (gameMap.level[id]) {
			case 4:
				return;
			case 3:
				spent = (long) (gameMap.value[game.p_dest_id[turnId]] * 0.4);
				break;
			default:
				spent = (long) (gameMap.value[game.p_dest_id[turnId]] * 0.2);
				break;
		}

		// Won't build if build house or hotel then player money less than 2500
		if (game.p_money[turnId] - spent >= 2500) {
			game.deal((-1)*spent, turnId, "Building...");
			gameMap.level[id] += 1;
		}
	}
}
