
public class Game_Loop implements Runnable{
	public game mygame;
	public  mcanvas mycanvas;
	public Game_Map game_map;
	public Buy_Land buy_land;
	public Build_House build_house;
	public AI ai;
	public Thread t_game;
	public boolean susp;
	
	Game_Loop(game Game, mcanvas canvas, Game_Map gm){
		mygame = Game;
		mycanvas = canvas;
		game_map = gm;
		t_game = new Thread(this);
		buy_land = new Buy_Land(Game, gm, this);
		build_house = new Build_House(Game, gm, this);
		ai = new AI(Game, gm, this);
	}
	public void run(){
		int one_step, i, j;
		boolean no_cross_cash[] = {true, true, true, true};
		
		while(player_number(mygame) > 1)
		{	
			for(i=0; i<game.max_p_size; i++){
				if(i == mygame.turn){
					if(9 == mygame.p_type[i]){
						mygame.turn++;
						break;
					}
					else if(false == mygame.move_start && 0 == mygame.p_type[i]){
						mygame.btnNewButton.setEnabled(true);
						break;
					}
					else if(1 == mygame.p_type[i]){
						mygame.dice.Roll_Dice();
						mygame.move_start = true;
						mygame.p_dest_id[i] = (mygame.p_id[i] + mygame.dice.count) % game_map.Size;
						break;
					}
				}
			}
			
			// if p_money < 0
			for(i=0;i<game.max_p_size;i++){
				if(9 == mygame.p_type[i]){
					continue;
				}
				else{
					if(mygame.p_money[i] < 0){
						for(j=0;j<game_map.Size;j++){
							if(i + 1 == game_map.owner[j]){
								game_map.owner[j] = 0;
								game_map.level[j] = 0;
							}
						}
						
						mygame.p_status[i] = "Broken";
						mygame.p_type[i] = 9;
					}
				}
			}
			
			mycanvas.paintImmediately(0, 0, mycanvas.max_size, mycanvas.max_size);
			
			
			while(mygame.p_id[0] != mygame.p_dest_id[0] || mygame.p_id[1] != mygame.p_dest_id[1] || mygame.p_id[2] != mygame.p_dest_id[2] || mygame.p_id[3] != mygame.p_dest_id[3]){
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				for(i=0; i<game.max_p_size; i++){
					if(mygame.p_id[i] != mygame.p_dest_id[i]){
						if(false == no_cross_cash[i] && 0 == mygame.p_id[i]){
							no_cross_cash[i] = true;
							mygame.deal(mygame.cross_cash, i);
						}
						if(mygame.p_x_now[i] == game_map.p_x[i][(mygame.p_id[i]+1)%game_map.Size] && mygame.p_y_now[i] == game_map.p_y[i][(mygame.p_id[i]+1)%game_map.Size]) {
							mygame.p_id[i] = (mygame.p_id[i]+1)%game_map.Size;
						}
						if(mygame.p_x_now[i] != game_map.p_x[i][(mygame.p_id[i]+1)%game_map.Size]){
							one_step = game_map.p_x[i][(mygame.p_id[i]+1)%game_map.Size] > mygame.p_x_now[i] ? 1 : -1;
							mygame.p_x_now[i] = mygame.p_x_now[i] + one_step;
						}
						if(mygame.p_y_now[i] != game_map.p_y[i][(mygame.p_id[i]+1)%game_map.Size]){
							one_step = game_map.p_y[i][(mygame.p_id[i]+1)%game_map.Size] > mygame.p_y_now[i] ? 1 : -1;
							mygame.p_y_now[i] = mygame.p_y_now[i] + one_step;
						}
					}
					if(i == mygame.turn && true == mygame.move_start && mygame.p_id[i] == mygame.p_dest_id[i]){
						mygame.move_start = false;
						if(0 == mygame.p_dest_id[i])no_cross_cash[i] = true;
						else no_cross_cash[i] = false;
						if(0 == game_map.type[mygame.p_dest_id[mygame.turn]] && 0 == mygame.p_type[mygame.turn]){
							susp = true;
							if(0 == game_map.owner[mygame.p_dest_id[mygame.turn]])
								buy_land.show();
							else if(mygame.turn + 1 == game_map.owner[mygame.p_dest_id[mygame.turn]]){
								build_house.show();
							}
							else{
								//pay for land owner
								payout();
								susp = false;
							}
							while(true == susp){
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						else if(0 == game_map.type[mygame.p_dest_id[mygame.turn]]){//AI for land
							if(0 == game_map.owner[mygame.p_dest_id[mygame.turn]]){
								ai.buy_land();
							}
							else if(mygame.turn + 1 == game_map.owner[mygame.p_dest_id[mygame.turn]]){
								ai.build_house();
							}
							else{
								//pay for land owner
								payout();
							}
						}
						mygame.turn = (mygame.turn+1)%game.max_p_size;
					}
					//System.out.println(mygame.turn);
				}//for max_p_size
				
				mycanvas.paintImmediately(0, 0, mycanvas.max_size, mycanvas.max_size);
			}///while(mygame.p_id[0] != mygame.p_dest_id[0] ...)
		}// while(player_number > 1)
	}
	public int player_number(game Game){
		int n = 0;
		if(Game.p_type[0] != 9)n++;
		if(Game.p_type[1] != 9)n++;
		if(Game.p_type[2] != 9)n++;
		if(Game.p_type[3] != 9)n++;
		//System.out.println(n);
		return n;
	}
	public void payout(){//pay for land owner
		long fee, basic_money;
		int id, doub, owner1, owner2, owner3, owner;
		id = mygame.p_dest_id[mygame.turn];
		
		owner1 = game_map.owner[game_map.same_color[game_map.color_index(game_map.color[id])][0]];
		owner2 = game_map.owner[game_map.same_color[game_map.color_index(game_map.color[id])][1]];
		owner3 = game_map.owner[game_map.same_color[game_map.color_index(game_map.color[id])][2]];
		if(owner1 == owner2 && owner2 == owner3)doub = 2;
		else doub = 1;
		
		basic_money = (long)((0.2)*game_map.value[id]);
		fee = (long) (doub*Math.pow(2, game_map.level[id])*basic_money);
		owner = game_map.owner[id] - 1;
		if(1 == mygame.p_in_jail[owner])return;
		else{
			mygame.deal((-1)*fee, mygame.turn);
			mygame.deal(fee, owner);
		}
	}
	public void land_tax(){
		int turn_id = mygame.turn;
		int i, land_number=0;
		long tax=400, fee=0;
		for(i=0; i<game_map.Size; i++){
			if(turn_id + 1 == game_map.owner[i]){
				land_number++;
			}
		}
		fee = tax*land_number;
		mygame.deal((-1)*fee, turn_id);
	}
	public void house_tax(){
		int turn_id = mygame.turn;
		int i, house_number=0;
		long tax=200, fee=0;
		for(i=0; i<game_map.Size; i++){
			if(turn_id + 1 == game_map.owner[i]){
				house_number += game_map.level[i];
			}
		}
		fee = tax*house_number;
		mygame.deal((-1)*fee, turn_id);
	}
}
