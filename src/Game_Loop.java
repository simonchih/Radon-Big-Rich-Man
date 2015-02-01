
public class Game_Loop implements Runnable{
	public game mygame;
	public  mcanvas mycanvas;
	public Game_Map game_map;
	public Buy_Land buy_land;
	public Thread t_game;
	public boolean susp;
	
	Game_Loop(game Game, mcanvas canvas, Game_Map gm){
		mygame = Game;
		mycanvas = canvas;
		game_map = gm;
		t_game = new Thread(this);
		buy_land = new Buy_Land(Game, canvas, gm, this);
	}
	public void run(){
		int one_step;
		
		while(player_number(mygame) > 1)
		{	
			if(0 == mygame.turn){
				if(9 == mygame.p_type[0])mygame.turn++;
				else if(false == mygame.move_start && 0 == mygame.p_type[0]){
					mygame.btnNewButton.setEnabled(true);
				}
				else if(1 == mygame.p_type[0]){
					mygame.dice.Roll_Dice();
					mygame.move_start = true;
					mygame.p_dest_id[0] = (mygame.p_id[0] + mygame.dice.count) % game_map.Size;
				}
			}
			else if(1 == mygame.turn){
				if(9 == mygame.p_type[1])mygame.turn++;
				else if(false == mygame.move_start && 0 == mygame.p_type[1]){
					mygame.btnNewButton.setEnabled(true);
				}
				else if(1 == mygame.p_type[1]){
					mygame.dice.Roll_Dice();
					mygame.move_start = true;
					mygame.p_dest_id[1] = (mygame.p_id[1] + mygame.dice.count) % game_map.Size;
				}
			}
			else if(2 == mygame.turn){
				if(9 == mygame.p_type[2])mygame.turn++;
				else if(false == mygame.move_start && 0 == mygame.p_type[2]){
					mygame.btnNewButton.setEnabled(true);
				}
				else if(1 == mygame.p_type[2]){
					mygame.dice.Roll_Dice();
					mygame.move_start = true;
					mygame.p_dest_id[2] = (mygame.p_id[2] + mygame.dice.count) % game_map.Size;
				}
			}
			else if(3 == mygame.turn){
				if(9 == mygame.p_type[3])mygame.turn = 0;
				else if(false == mygame.move_start && 0 == mygame.p_type[3]){
					mygame.btnNewButton.setEnabled(true);
				}
				else if(1 == mygame.p_type[3]){
					mygame.dice.Roll_Dice();
					mygame.move_start = true;
					mygame.p_dest_id[3] = (mygame.p_id[3] + mygame.dice.count) % game_map.Size;
				}
			}
			
			//if(true == mygame.move_start){
				mycanvas.paintImmediately(0, 0, mycanvas.max_size, mycanvas.max_size);
			//}
			
			while(mygame.p_id[0] != mygame.p_dest_id[0] || mygame.p_id[1] != mygame.p_dest_id[1] || mygame.p_id[2] != mygame.p_dest_id[2] || mygame.p_id[3] != mygame.p_dest_id[3]){
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if(mygame.p_id[0] != mygame.p_dest_id[0]){
					if(mygame.p_x_now[0] == game_map.p1_x[(mygame.p_id[0]+1)%game_map.Size] && mygame.p_y_now[0] == game_map.p1_y[(mygame.p_id[0]+1)%game_map.Size]) {
						mygame.p_id[0] = (mygame.p_id[0]+1)%game_map.Size;
					}
					if(mygame.p_x_now[0] != game_map.p1_x[(mygame.p_id[0]+1)%game_map.Size]){
						one_step = game_map.p1_x[(mygame.p_id[0]+1)%game_map.Size] > mygame.p_x_now[0] ? 1 : -1;
						mygame.p_x_now[0] = mygame.p_x_now[0] + one_step;
					}
					if(mygame.p_y_now[0] != game_map.p1_y[(mygame.p_id[0]+1)%game_map.Size]){
						one_step = game_map.p1_y[(mygame.p_id[0]+1)%game_map.Size] > mygame.p_y_now[0] ? 1 : -1;
						mygame.p_y_now[0] = mygame.p_y_now[0] + one_step;
					}
				}
				
				if(mygame.p_id[1] != mygame.p_dest_id[1]){
					if(mygame.p_x_now[1] == game_map.p2_x[(mygame.p_id[1]+1)%game_map.Size] && mygame.p_y_now[1] == game_map.p2_y[(mygame.p_id[1]+1)%game_map.Size]) {
						mygame.p_id[1] = (mygame.p_id[1]+1)%game_map.Size;
					}
					if(mygame.p_x_now[1] != game_map.p2_x[(mygame.p_id[1]+1)%game_map.Size]){
						one_step = game_map.p2_x[(mygame.p_id[1]+1)%game_map.Size] > mygame.p_x_now[1] ? 1 : -1;
						mygame.p_x_now[1] = mygame.p_x_now[1] + one_step;
					}
					if(mygame.p_y_now[1] != game_map.p2_y[(mygame.p_id[1]+1)%game_map.Size]){
						one_step = game_map.p2_y[(mygame.p_id[1]+1)%game_map.Size] > mygame.p_y_now[1] ? 1 : -1;
						mygame.p_y_now[1] = mygame.p_y_now[1] + one_step;
					}
				}
				
				if(mygame.p_id[2] != mygame.p_dest_id[2]){
					if(mygame.p_x_now[2] == game_map.p3_x[(mygame.p_id[2]+1)%game_map.Size] && mygame.p_y_now[2] == game_map.p3_y[(mygame.p_id[2]+1)%game_map.Size]) {
						mygame.p_id[2] = (mygame.p_id[2]+1)%game_map.Size;
					}
					if(mygame.p_x_now[2] != game_map.p3_x[(mygame.p_id[2]+1)%game_map.Size]){
						one_step = game_map.p3_x[(mygame.p_id[2]+1)%game_map.Size] > mygame.p_x_now[2] ? 1 : -1;
						mygame.p_x_now[2] = mygame.p_x_now[2] + one_step;
					}
					if(mygame.p_y_now[2] != game_map.p3_y[(mygame.p_id[2]+1)%game_map.Size]){
						one_step = game_map.p3_y[(mygame.p_id[2]+1)%game_map.Size] > mygame.p_y_now[2] ? 1 : -1;
						mygame.p_y_now[2] = mygame.p_y_now[2] + one_step;
					}
				}
				
				if(mygame.p_id[3] != mygame.p_dest_id[3]){
					if(mygame.p_x_now[3] == game_map.p4_x[(mygame.p_id[3]+1)%game_map.Size] && mygame.p_y_now[3] == game_map.p4_y[(mygame.p_id[3]+1)%game_map.Size]) {
						mygame.p_id[3] = (mygame.p_id[3]+1)%game_map.Size;
					}
					if(mygame.p_x_now[3] != game_map.p4_x[(mygame.p_id[3]+1)%game_map.Size]){
						one_step = game_map.p4_x[(mygame.p_id[3]+1)%game_map.Size] > mygame.p_x_now[3] ? 1 : -1;
						mygame.p_x_now[3] = mygame.p_x_now[3] + one_step;
					}
					if(mygame.p_y_now[3] != game_map.p4_y[(mygame.p_id[3]+1)%game_map.Size]){
						one_step = game_map.p4_y[(mygame.p_id[3]+1)%game_map.Size] > mygame.p_y_now[3] ? 1 : -1;
						mygame.p_y_now[3] = mygame.p_y_now[3] + one_step;
					}
				}
				if(0 == mygame.turn && true == mygame.move_start && mygame.p_id[0] == mygame.p_dest_id[0]){
					mygame.move_start = false;
					if(0 == game_map.type[mygame.p_dest_id[mygame.turn]] && 0 == mygame.p_type[mygame.turn]){
						System.out.println("Test Buy Land");
						susp = true;
						buy_land.show(mygame.turn);
						while(true == susp){
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					mygame.turn++;
				}
				if(1 == mygame.turn && true == mygame.move_start && mygame.p_id[1] == mygame.p_dest_id[1]){
					mygame.move_start = false;
					if(0 == game_map.type[mygame.p_dest_id[mygame.turn]] && 0 == mygame.p_type[mygame.turn]){
						susp = true;
						buy_land.show(mygame.turn);
						while(true == susp){
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					mygame.turn++;
				}
				if(2 == mygame.turn && true == mygame.move_start && mygame.p_id[2] == mygame.p_dest_id[2]){
					mygame.move_start = false;
					if(0 == game_map.type[mygame.p_dest_id[mygame.turn]] && 0 == mygame.p_type[mygame.turn]){
						susp = true;
						buy_land.show(mygame.turn);
						while(true == susp){
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					mygame.turn++;
				}
				if(3 == mygame.turn && true == mygame.move_start && mygame.p_id[3] == mygame.p_dest_id[3]){
					mygame.move_start = false;
					if(0 == game_map.type[mygame.p_dest_id[mygame.turn]] && 0 == mygame.p_type[mygame.turn]){
						susp = true;
						buy_land.show(mygame.turn);
						while(true == susp){
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					mygame.turn = 0;
				}
				//System.out.println(mygame.turn);
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
}
