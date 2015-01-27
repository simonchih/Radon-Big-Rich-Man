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
	int left_string_y = 609;
	int right_string_y = 129;
	
	int p_gap = 5;
	
	public Game_Map game_data = new Game_Map();
	public game mygame = new game();
	
	public mcanvas(Game_Map gm, game Game) {
		game_data = gm;
		mygame = Game;
	}

	public void paintComponent(Graphics g) {
		int i;
		
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
		g.drawImage(mygame.iarrow.getImage(), right_x+(max_size-right_x-mygame.iarrow.getIconWidth())/2, down_y+(max_size-right_x-mygame.iarrow.getIconHeight())/2, mygame.iarrow.getIconWidth(), mygame.iarrow.getIconHeight(), null);
		
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
				}
				
				// Left
				else if(i>=11 && i<=19){
					g.setColor(Color.black);
					g.drawLine(left_x-color_small-1, down_y-block_size*(i-11), left_x-color_small-1, down_y-block_size*(i-10));
					g.drawString(game_data.name[i], left_string_x_start, left_string_y-block_size*(i-11));
					g.drawString("$"+game_data.value[i], left_string_x_start, left_string_y+right_left_d-block_size*(i-11));
					g.setColor(game_data.color[i]);
					g.fillRect(left_x-color_small, down_y-block_size*(i-10)+1, color_small, color_long);					
				}
				
				// Up
				else if(i>=21 && i<=29){
					g.setColor(Color.black);
					g.drawLine(left_x+block_size*(i-21), up_y-color_small-1, left_x+block_size*(i-20), up_y-color_small-1);
					g.drawString(game_data.name[i], up_string_x_start+block_size*(i-21),up_y-color_small-up_down_d);
					g.drawString("$"+game_data.value[i], up_string_x_start+block_size*(i-21),up_y-color_small-2*up_down_d);
					g.setColor(game_data.color[i]);
					g.fillRect(left_x+block_size*(i-21)+1, up_y-color_small, color_long, color_small);
				}
				
				// Right
				else if(i>=31 && i<=39){
					g.setColor(Color.black);
					g.drawLine(right_x+color_small+1, up_y+block_size*(i-31), right_x+color_small+1, up_y+block_size*(i-30));
					g.drawString(game_data.name[i], right_string_x_start, right_string_y+block_size*(i-31));
					g.drawString("$"+game_data.value[i], right_string_x_start, right_string_y+right_left_d+block_size*(i-31));
					g.setColor(game_data.color[i]);
					g.fillRect(right_x+1, up_y+block_size*(i-31)+1, color_small, color_long);
				}
				
			}//end if
		}//end for
		
		//draw dice
		g.drawImage(mygame.dice.idice1.getImage(), mygame.dice.idice1_x, mygame.dice.idice1_y, mygame.dice.idice1.getIconWidth(), mygame.dice.idice1.getIconHeight(), this);
		g.drawImage(mygame.dice.idice2.getImage(), mygame.dice.idice2_x, mygame.dice.idice2_y, mygame.dice.idice2.getIconWidth(), mygame.dice.idice2.getIconHeight(), this);
		//System.out.println(mygame.dice.dice1_raw);
	    //System.out.println(mygame.dice.dice2_raw);
		
		//draw pawn at p_x, p_y;
		g.drawImage(mygame.p1_pawn.getImage(), mygame.p1_x_now, mygame.p1_y_now, mygame.p1_pawn.getIconWidth(), mygame.p1_pawn.getIconHeight(), null);
		g.drawImage(mygame.p2_pawn.getImage(), mygame.p2_x_now, mygame.p2_y_now, mygame.p2_pawn.getIconWidth(), mygame.p2_pawn.getIconHeight(), null);
		g.drawImage(mygame.p3_pawn.getImage(), mygame.p3_x_now, mygame.p3_y_now, mygame.p3_pawn.getIconWidth(), mygame.p3_pawn.getIconHeight(), null);
		g.drawImage(mygame.p4_pawn.getImage(), mygame.p4_x_now, mygame.p4_y_now, mygame.p4_pawn.getIconWidth(), mygame.p4_pawn.getIconHeight(), null);
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
