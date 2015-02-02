import java.awt.*;
import javax.swing.*;

public class mcanvas extends JComponent {
	private static final long serialVersionUID = 1;
	int max_size = 760;
	int block_size = 60;
	int left_x = 110;
	int right_x = 650;
	int up_y = 110;
	int down_y = 650;
	int color_long = 59;
	int color_small = 20;
	int down_string_x_start = 594;
	int left_string_x_start = 20;
	int up_string_x_start = 114;
	int right_string_x_start = 690;
	int up_down_d = 20;
	int right_left_d = 15;
	int right_left_ic_d = 20;
	int left_string_y = 609;
	int right_string_y = 129;
	int string_player_d = 20;
	
	int p_gap = 5;
	
	public Game_Map game_data = new Game_Map();
	public game mygame = new game();
	
	public mcanvas(Game_Map gm, game Game) {
		game_data = gm;
		mygame = Game;
	}

	public void paintComponent(Graphics g) {
		int i;
		String sp1, sp2, sp3, sp4;
		
		sp1 = mygame.p_name[0] + ": "+ mygame.p_money[0] +" (" + mygame.p_status[0] + ")";
		sp2 = mygame.p_name[1] + ": "+ mygame.p_money[1] +" (" + mygame.p_status[1] + ")";
		sp3 = mygame.p_name[2] + ": "+ mygame.p_money[2] +" (" + mygame.p_status[2] + ")";
		sp4 = mygame.p_name[3] + ": "+ mygame.p_money[3] +" (" + mygame.p_status[3] + ")";
		
		//ini sp_x, sp_y
		mygame.sp_x[0] = left_x + string_player_d;
		mygame.sp_y[0] = up_y + string_player_d;
		mygame.sp_x[1] = left_x + string_player_d;
		mygame.sp_y[1] = up_y + 2*string_player_d;
		mygame.sp_x[2] = left_x + string_player_d;
		mygame.sp_y[2] = up_y + 3*string_player_d;
		mygame.sp_x[3] = left_x + string_player_d;
		mygame.sp_y[3] = up_y + 4*string_player_d;
		
		
		super.paintComponent(g);

		g.drawLine(left_x, 0, left_x, max_size);
		g.drawLine(right_x, 0, right_x, max_size);
		g.drawLine(0, up_y, max_size, up_y);
		g.drawLine(0, down_y, max_size, down_y);
		
		//Up
		for(i=left_x+block_size;i<right_x;i+=block_size){
			g.drawLine(i, 0, i, up_y);
		}
		
		//Down
		for(i=left_x+block_size;i<right_x;i+=block_size){
			g.drawLine(i, down_y, i, max_size);
		}
		
		//Left
		for(i=up_y+block_size;i<down_y;i+=block_size){
			g.drawLine(0, i, left_x, i);
		}
		
		//Right
		for(i=up_y+block_size;i<down_y;i+=block_size){
			g.drawLine(right_x, i, max_size, i);
		}
		
		// Game Map id=0
		g.drawImage(mygame.iarrow.getImage(), right_x+(max_size-right_x-mygame.iarrow.getIconWidth())/2, down_y+(max_size-down_y-mygame.iarrow.getIconHeight())/2, mygame.iarrow.getIconWidth(), mygame.iarrow.getIconHeight(), null);
		
		for(i=1;i<game_data.Size;i++)
		{
			if(0 == game_data.type[i]){
				// Down
				if(i>=1 && i<=9){
					g.setColor(Color.black);
					g.drawLine(right_x-block_size*(i-1), down_y+color_small+1, right_x-block_size*i, down_y+color_small+1);
					g.drawString(game_data.name[i], down_string_x_start-block_size*(i-1), down_y+color_small+up_down_d);
					g.drawString("$"+game_data.value[i], down_string_x_start-block_size*(i-1), down_y+color_small+2*up_down_d);
					g.setColor(game_data.color[i]);
					g.fillRect(right_x-block_size*i+1, down_y+1, color_long, color_small);
					if(4 == game_data.level[i]){
						g.drawImage(mygame.ihotel.getImage(), right_x-block_size*i+1, down_y+1, mygame.ihotel.getIconWidth(), mygame.ihotel.getIconHeight(), null);
					}
					else if(game_data.level[i] > 0){
						g.drawImage(mygame.ihouse.getImage(), right_x-block_size*i+1, down_y+1, mygame.ihouse.getIconWidth(), mygame.ihouse.getIconHeight(), null);
						
						if(game_data.level[i] >= 2){
							g.drawImage(mygame.ihouse.getImage(), right_x-block_size*i+mygame.ihouse.getIconWidth()+1, down_y+1, mygame.ihouse.getIconWidth(), mygame.ihouse.getIconHeight(), null);
						}
						if(3 == game_data.level[i]){
							g.drawImage(mygame.ihouse.getImage(), right_x-block_size*i+2*mygame.ihouse.getIconWidth()+1, down_y+1, mygame.ihouse.getIconWidth(), mygame.ihouse.getIconHeight(), null);
						}
					}
					if(game_data.owner[i] > 0){
						g.drawImage(mygame.p_ic[game_data.owner[i]-1].getImage(), right_x-block_size*i+(block_size-mygame.p_ic[game_data.owner[i]-1].getIconWidth())/2, down_y+color_small+3*up_down_d, mygame.p_ic[game_data.owner[i]-1].getIconWidth(), mygame.p_ic[game_data.owner[i]-1].getIconHeight(), null);
					}
				}
				
				// Left
				else if(i>=11 && i<=19){
					g.setColor(Color.black);
					g.drawLine(left_x-color_small-1, down_y-block_size*(i-11), left_x-color_small-1, down_y-block_size*(i-10));
					g.drawString(game_data.name[i], left_string_x_start, left_string_y-block_size*(i-11));
					g.drawString("$"+game_data.value[i], left_string_x_start, left_string_y+right_left_d-block_size*(i-11));
					g.setColor(game_data.color[i]);
					g.fillRect(left_x-color_small, down_y-block_size*(i-10)+1, color_small, color_long);
					if(4 == game_data.level[i]){
						g.drawImage(mygame.ihotel_left.getImage(), left_x-color_small, down_y-block_size*(i-10)+1, mygame.ihotel_left.getIconWidth(), mygame.ihotel_left.getIconHeight(), null);
					}
					else if(game_data.level[i] > 0){
						g.drawImage(mygame.ihouse_left.getImage(), left_x-color_small, down_y-block_size*(i-10)+1, mygame.ihouse_left.getIconWidth(), mygame.ihouse_left.getIconHeight(), null);
						
						if(game_data.level[i] >= 2){
							g.drawImage(mygame.ihouse_left.getImage(), left_x-color_small, down_y-block_size*(i-10)+mygame.ihouse_left.getIconHeight()+1, mygame.ihouse_left.getIconWidth(), mygame.ihouse_left.getIconHeight(), null);
						}
						if(3 == game_data.level[i]){
							g.drawImage(mygame.ihouse_left.getImage(), left_x-color_small, down_y-block_size*(i-10)+2*mygame.ihouse_left.getIconHeight()+1, mygame.ihouse_left.getIconWidth(), mygame.ihouse_left.getIconHeight(), null);
						}
					}
					if(game_data.owner[i] > 0){
						g.drawImage(mygame.p_ic[game_data.owner[i]-1].getImage(), (left_x - color_small - mygame.p_ic[game_data.owner[i]-1].getIconWidth())/2, left_string_y+right_left_ic_d-block_size*(i-11), mygame.p_ic[game_data.owner[i]-1].getIconWidth(), mygame.p_ic[game_data.owner[i]-1].getIconHeight(), null);
					}
				}
				
				// Up
				else if(i>=21 && i<=29){
					g.setColor(Color.black);
					g.drawLine(left_x+block_size*(i-21), up_y-color_small-1, left_x+block_size*(i-20), up_y-color_small-1);
					g.drawString(game_data.name[i], up_string_x_start+block_size*(i-21),up_y-color_small-up_down_d);
					g.drawString("$"+game_data.value[i], up_string_x_start+block_size*(i-21),up_y-color_small-2*up_down_d);
					g.setColor(game_data.color[i]);
					g.fillRect(left_x+block_size*(i-21)+1, up_y-color_small, color_long, color_small);
					if(4 == game_data.level[i]){
						g.drawImage(mygame.ihotel_up.getImage(), left_x+block_size*(i-21)+1, up_y-color_small, mygame.ihotel_up.getIconWidth(), mygame.ihotel_up.getIconHeight(), null);
					}
					else if(game_data.level[i] > 0){
						g.drawImage(mygame.ihouse_up.getImage(), left_x+block_size*(i-21)+1, up_y-color_small, mygame.ihouse_up.getIconWidth(), mygame.ihouse_up.getIconHeight(), null);
						
						if(game_data.level[i] >= 2){
							g.drawImage(mygame.ihouse_up.getImage(), left_x+block_size*(i-21)+ mygame.ihouse_up.getIconWidth()+1, up_y-color_small, mygame.ihouse_up.getIconWidth(), mygame.ihouse_up.getIconHeight(), null);
						}
						if(3 == game_data.level[i]){
							g.drawImage(mygame.ihouse_up.getImage(), left_x+block_size*(i-21)+ 2*mygame.ihouse_up.getIconWidth()+1, up_y-color_small, mygame.ihouse_up.getIconWidth(), mygame.ihouse_up.getIconHeight(), null);
						}
					}
					if(game_data.owner[i] > 0){
						g.drawImage(mygame.p_ic[game_data.owner[i]-1].getImage(), left_x+block_size*(i-21)+(block_size-mygame.p_ic[game_data.owner[i]-1].getIconWidth())/2, up_y-color_small-3*up_down_d-mygame.p_ic[game_data.owner[i]-1].getIconHeight(), mygame.p_ic[game_data.owner[i]-1].getIconWidth(), mygame.p_ic[game_data.owner[i]-1].getIconHeight(), null);
					}
				}
				
				// Right
				else if(i>=31 && i<=39){
					g.setColor(Color.black);
					g.drawLine(right_x+color_small+1, up_y+block_size*(i-31), right_x+color_small+1, up_y+block_size*(i-30));
					g.drawString(game_data.name[i], right_string_x_start, right_string_y+block_size*(i-31));
					g.drawString("$"+game_data.value[i], right_string_x_start, right_string_y+right_left_d+block_size*(i-31));
					g.setColor(game_data.color[i]);
					g.fillRect(right_x+1, up_y+block_size*(i-31)+1, color_small, color_long);
					if(4 == game_data.level[i]){
						g.drawImage(mygame.ihotel_right.getImage(), right_x+1, up_y+block_size*(i-31)+1, mygame.ihotel_right.getIconWidth(), mygame.ihotel_right.getIconHeight(), null);
					}
					else if(game_data.level[i] > 0){
						g.drawImage(mygame.ihouse_right.getImage(), right_x+1, up_y+block_size*(i-31)+1, mygame.ihouse_right.getIconWidth(), mygame.ihouse_right.getIconHeight(), null);
						
						if(game_data.level[i] >= 2){
							g.drawImage(mygame.ihouse_right.getImage(), right_x+1, up_y+block_size*(i-31)+mygame.ihouse_right.getIconHeight()+1, mygame.ihouse_right.getIconWidth(), mygame.ihouse_right.getIconHeight(), null);
						}
						if(3 == game_data.level[i]){
							g.drawImage(mygame.ihouse_right.getImage(), right_x+1, up_y+block_size*(i-31)+2*mygame.ihouse_right.getIconHeight()+1, mygame.ihouse_right.getIconWidth(), mygame.ihouse_right.getIconHeight(), null);
						}
					}
					if(game_data.owner[i] > 0){
						g.drawImage(mygame.p_ic[game_data.owner[i]-1].getImage(), right_x + color_small + (block_size- mygame.p_ic[game_data.owner[i]-1].getIconWidth())/2, right_string_y+right_left_ic_d+block_size*(i-31), mygame.p_ic[game_data.owner[i]-1].getIconWidth(), mygame.p_ic[game_data.owner[i]-1].getIconHeight(), null);
					}
				}
				
			}//end if
			else if(1 == game_data.type[i]){
				ImageIcon iblock = null;
				if(25 == game_data.id[i]){// Jail					
					iblock = mygame.ijail;
				}
				else if(26 == game_data.id[i]){//Nothing
					iblock = mygame.iparking;
				}
				else if(27 == game_data.id[i]){//Hospital
					iblock = mygame.ihospital;
				}
				
				if(10 == i){
					g.drawImage(iblock.getImage(), (left_x-iblock.getIconWidth())/2, down_y+(max_size-down_y-iblock.getIconHeight())/2, iblock.getIconWidth(), iblock.getIconHeight(), null);
				}
				else if(20 == i){
					g.drawImage(iblock.getImage(), (left_x-iblock.getIconWidth())/2, (up_y-iblock.getIconHeight())/2, iblock.getIconWidth(), iblock.getIconHeight(), null);
				}
				else if(30 == i){
					g.drawImage(iblock.getImage(), right_x + (max_size-right_x-iblock.getIconWidth())/2, (up_y-iblock.getIconHeight())/2, iblock.getIconWidth(), iblock.getIconHeight(), null);					
				}
			}
			else if(2 == game_data.type[i]){
				if(i>=1 && i<=9){
					g.drawImage(mygame.iquestionmark.getImage(), right_x-block_size*i, down_y+color_small+1, mygame.iquestionmark.getIconWidth(), mygame.iquestionmark.getIconHeight(), null);
				}
				else if(i>=11 && i<=19){
					g.drawImage(mygame.iquestionmark_left.getImage(), left_x-color_small-mygame.iquestionmark_left.getIconWidth()-1, down_y-block_size*(i-10), mygame.iquestionmark_left.getIconWidth(), mygame.iquestionmark_left.getIconHeight(), null);
				}
				else if(i>=21 && i<=29){
					g.drawImage(mygame.iquestionmark_up.getImage(), left_x+block_size*(i-21), up_y-color_small-mygame.iquestionmark_up.getIconHeight()-1, mygame.iquestionmark_up.getIconWidth(), mygame.iquestionmark_up.getIconHeight(), null);
				}
				else if(i>=31 && i<=39){
					g.drawImage(mygame.iquestionmark_right.getImage(), right_x+color_small+1, up_y+block_size*(i-31), mygame.iquestionmark_right.getIconWidth(), mygame.iquestionmark_right.getIconHeight(), null);
				}
			}
			else if(3 == game_data.type[i]){
				String s1 = null, s2 = null;
				if(36 == game_data.id[i]){//go jail
					s1 = mygame.s36_1;
					s2 = mygame.s36_2;
				}
				else if(37 == game_data.id[i]){//go hospital
					s1 = mygame.s37_1;
					s2 = mygame.s37_2;
				}
				else if(38 == game_data.id[i]){//land tax
					s1 = mygame.s38_1;
					s2 = mygame.s38_2;
				}
				else if(39 == game_data.id[i]){//house tax
					s1 = mygame.s39_1;
					s2 = mygame.s39_2;
				}
				
				if(i>=1 && i<=9){
					g.setColor(Color.black);
					g.drawString(s1, down_string_x_start-block_size*(i-1), down_y+color_small+up_down_d);
					g.drawString(s2, down_string_x_start-block_size*(i-1), down_y+color_small+2*up_down_d);
				}
				else if(i>=11 && i<=19){
					g.setColor(Color.black);
					g.drawString(s1, left_string_x_start, left_string_y-block_size*(i-11));
					g.drawString(s2, left_string_x_start, left_string_y+right_left_d-block_size*(i-11));
				}
				else if(i>=21 && i<=29){
					g.setColor(Color.black);
					g.drawString(s2, up_string_x_start+block_size*(i-21),up_y-color_small-up_down_d);
					g.drawString(s1, up_string_x_start+block_size*(i-21),up_y-color_small-2*up_down_d);
				}
				else if(i>=31 && i<=39){
					g.setColor(Color.black);
					g.drawString(s1, right_string_x_start, right_string_y+block_size*(i-31));
					g.drawString(s2, right_string_x_start, right_string_y+right_left_d+block_size*(i-31));
				}
			}
		}//end for
		
		//draw player total money
		g.setColor(Color.black);
		g.drawString(sp1, mygame.sp_x[0], mygame.sp_y[0]);
		g.drawString(sp2, mygame.sp_x[1], mygame.sp_y[1]);
		g.drawString(sp3, mygame.sp_x[2], mygame.sp_y[2]);
		g.drawString(sp3, mygame.sp_x[3], mygame.sp_y[3]);
		
		//draw dice
		g.drawImage(mygame.dice.idice1.getImage(), mygame.dice.idice1_x, mygame.dice.idice1_y, mygame.dice.idice1.getIconWidth(), mygame.dice.idice1.getIconHeight(), this);
		g.drawImage(mygame.dice.idice2.getImage(), mygame.dice.idice2_x, mygame.dice.idice2_y, mygame.dice.idice2.getIconWidth(), mygame.dice.idice2.getIconHeight(), this);
		//System.out.println(mygame.dice.dice1_raw);
	    //System.out.println(mygame.dice.dice2_raw);
		
		//draw pawn at p_x, p_y;
		g.drawImage(mygame.p_pawn[0].getImage(), mygame.p_x_now[0], mygame.p_y_now[0], mygame.p_pawn[0].getIconWidth(), mygame.p_pawn[0].getIconHeight(), null);
		g.drawImage(mygame.p_pawn[1].getImage(), mygame.p_x_now[1], mygame.p_y_now[1], mygame.p_pawn[1].getIconWidth(), mygame.p_pawn[1].getIconHeight(), null);
		g.drawImage(mygame.p_pawn[2].getImage(), mygame.p_x_now[2], mygame.p_y_now[2], mygame.p_pawn[2].getIconWidth(), mygame.p_pawn[2].getIconHeight(), null);
		g.drawImage(mygame.p_pawn[3].getImage(), mygame.p_x_now[3], mygame.p_y_now[3], mygame.p_pawn[3].getIconWidth(), mygame.p_pawn[3].getIconHeight(), null);
	}

	public Dimension getPreferredSize() {
		return new Dimension(max_size, max_size);
	}

	public Dimension getMaximumSize() {
		return getPreferredSize();
	}

	public Dimension getMinimumSize() {
		return getPreferredSize();
	}
}
