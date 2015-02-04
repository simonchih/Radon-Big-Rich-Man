
public class AI {
	public game mygame;
	public Game_Map game_map;
	public Game_Loop mygl;
	AI(game Game, Game_Map gm, Game_Loop gl){
		mygame = Game;
		game_map = gm;
		mygl = gl;
	}
	void buy_land(){
		int turn_id, id;
		turn_id = mygame.turn;
		id = mygame.p_dest_id[turn_id];
		// Won't buy if buy land then player money less than 3500
		if(mygame.p_money[turn_id]-game_map.value[id] < 3500)return;
		else{
			mygame.deal((-1)*game_map.value[mygame.p_dest_id[turn_id]], turn_id);
			game_map.owner[id] = turn_id + 1;
		}
	}
	void build_house(){
		long spent;
		int turn_id, id;
		turn_id = mygame.turn;
		id = mygame.p_dest_id[turn_id];
		
		if(4 == game_map.level[id]){
			return;
		}
		else if(3 == game_map.level[id]){
			spent = (long) (game_map.value[mygame.p_dest_id[turn_id]]*(0.4));
		}
		else {
			spent = (long) (game_map.value[mygame.p_dest_id[turn_id]]*(0.2));
		}
		
		// Won't build if build house or hotel then player money less than 2500
		if(mygame.p_money[turn_id] - spent < 2500)return;
		else{
			mygame.deal((-1)*spent, turn_id);
			game_map.level[id] += 1;
		}
		
	}
}
