
public class Game_Loop extends Thread{
	public game mygame;
	public  mcanvas mycanvas;
	public Game_Map game_map;
	Game_Loop(game Game, mcanvas canvas, Game_Map gm){
		mygame = Game;
		mycanvas = canvas;
		game_map = gm;
	}
	public void run(){
		int one_step;
		
		while(player_number(mygame) > 1)
		{	
			if(0 == mygame.turn){
				mygame.turn++;
			}
			else if(1 == mygame.turn){
				mygame.turn++;
			}
			else if(2 == mygame.turn){
				mygame.turn++;
			}
			else if(3 == mygame.turn){
				mygame.turn = 0;
			}
			
			while(mygame.p1_id != mygame.p1_dest_id || mygame.p2_id != mygame.p2_dest_id || mygame.p3_id != mygame.p3_dest_id || mygame.p4_id != mygame.p4_dest_id){
				try {
					Game_Loop.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if(mygame.p1_id != mygame.p1_dest_id){
					if(mygame.p1_x_now == game_map.p1_x[(mygame.p1_id+1)%game_map.Size] && mygame.p1_y_now == game_map.p1_y[(mygame.p1_id+1)%game_map.Size]) {
						mygame.p1_id = (mygame.p1_id+1)%game_map.Size;
					}
					if(mygame.p1_x_now != game_map.p1_x[(mygame.p1_id+1)%game_map.Size]){
						one_step = game_map.p1_x[(mygame.p1_id+1)%game_map.Size] > mygame.p1_x_now ? 1 : -1;
						mygame.p1_x_now = mygame.p1_x_now + one_step;
					}
					if(mygame.p1_y_now != game_map.p1_y[(mygame.p1_id+1)%game_map.Size]){
						one_step = game_map.p1_y[(mygame.p1_id+1)%game_map.Size] > mygame.p1_y_now ? 1 : -1;
						mygame.p1_y_now = mygame.p1_y_now + one_step;
					}
				}
				
				if(mygame.p2_id != mygame.p2_dest_id){
					if(mygame.p2_x_now == game_map.p2_x[(mygame.p2_id+1)%game_map.Size] && mygame.p2_y_now == game_map.p2_y[(mygame.p2_id+1)%game_map.Size]) {
						mygame.p2_id = (mygame.p2_id+1)%game_map.Size;
					}
					if(mygame.p2_x_now != game_map.p2_x[(mygame.p2_id+1)%game_map.Size]){
						one_step = game_map.p2_x[(mygame.p2_id+1)%game_map.Size] > mygame.p2_x_now ? 1 : -1;
						mygame.p2_x_now = mygame.p2_x_now + one_step;
					}
					if(mygame.p2_y_now != game_map.p2_y[(mygame.p2_id+1)%game_map.Size]){
						one_step = game_map.p2_y[(mygame.p2_id+1)%game_map.Size] > mygame.p2_y_now ? 1 : -1;
						mygame.p2_y_now = mygame.p2_y_now + one_step;
					}
				}
				
				if(mygame.p3_id != mygame.p3_dest_id){
					if(mygame.p3_x_now == game_map.p3_x[(mygame.p3_id+1)%game_map.Size] && mygame.p3_y_now == game_map.p3_y[(mygame.p3_id+1)%game_map.Size]) {
						mygame.p3_id = (mygame.p3_id+1)%game_map.Size;
					}
					if(mygame.p3_x_now != game_map.p3_x[(mygame.p3_id+1)%game_map.Size]){
						one_step = game_map.p3_x[(mygame.p3_id+1)%game_map.Size] > mygame.p3_x_now ? 1 : -1;
						mygame.p3_x_now = mygame.p3_x_now + one_step;
					}
					if(mygame.p3_y_now != game_map.p3_y[(mygame.p3_id+1)%game_map.Size]){
						one_step = game_map.p3_y[(mygame.p3_id+1)%game_map.Size] > mygame.p3_y_now ? 1 : -1;
						mygame.p3_y_now = mygame.p3_y_now + one_step;
					}
				}
				
				if(mygame.p4_id != mygame.p4_dest_id){
					if(mygame.p4_x_now == game_map.p4_x[(mygame.p4_id+1)%game_map.Size] && mygame.p4_y_now == game_map.p4_y[(mygame.p4_id+1)%game_map.Size]) {
						mygame.p4_id = (mygame.p4_id+1)%game_map.Size;
					}
					if(mygame.p4_x_now != game_map.p4_x[(mygame.p4_id+1)%game_map.Size]){
						one_step = game_map.p4_x[(mygame.p4_id+1)%game_map.Size] > mygame.p4_x_now ? 1 : -1;
						mygame.p4_x_now = mygame.p4_x_now + one_step;
					}
					if(mygame.p4_y_now != game_map.p4_y[(mygame.p4_id+1)%game_map.Size]){
						one_step = game_map.p4_y[(mygame.p4_id+1)%game_map.Size] > mygame.p4_y_now ? 1 : -1;
						mygame.p4_y_now = mygame.p4_y_now + one_step;
					}
				}
				
				mycanvas.paintImmediately(0, 0, mycanvas.max_size, mycanvas.max_size);
			}
		}
	}
	public int player_number(game Game){
		int n = 0;
		if(Game.p1_type != 9)n++;
		if(Game.p2_type != 9)n++;
		if(Game.p3_type != 9)n++;
		if(Game.p4_type != 9)n++;
		//System.out.println(n);
		return n;
	}
}
