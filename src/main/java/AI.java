
public class AI {

	private final game mygame;
	private final Game_Map game_map;

	public AI(final game Game, final Game_Map gm, final Game_Loop gl) {
		this.mygame = Game;
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
