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
	public Game_Map game_data = new Game_Map();
	
	public mcanvas(Game_Map gm) {
		game_data = gm;
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
		
		for(i=1;i<game_data.Size;i++)
		{
			if(0 == game_data.type[i]){
				// Down
				if(i>=1 && i<=9){
					g.setColor(Color.black);
					g.drawLine(650-60*(i-1), 671, 650-60*i, 671);
					g.drawString(game_data.name[i], 594-60*(i-1), 690);
					g.drawString("$"+game_data.value[i], 594-60*(i-1), 710);
					g.setColor(game_data.color[i]);
					g.fillRect(590-60*(i-1)+1, 651, 59, 20);
				}
				
				// Left
				else if(i>=11 && i<=19){
					g.setColor(Color.black);
					g.drawLine(89, 650-60*(i-11), 89, 650-60*(i-10));
					g.drawString(game_data.name[i], 20, 609-60*(i-11));
					g.drawString("$"+game_data.value[i], 20, 624-60*(i-11));
					g.setColor(game_data.color[i]);
					g.fillRect(90, 590-60*(i-11)+1, 20, 59);					
				}
				
				// Up
				else if(i>=21 && i<=29){
					g.setColor(Color.black);
					g.drawLine(110+60*(i-21), 89, 110+60*(i-20), 89);
					g.drawString(game_data.name[i], 114+60*(i-21),70);
					g.drawString("$"+game_data.value[i], 114+60*(i-21),50);
					g.setColor(game_data.color[i]);
					g.fillRect(110+60*(i-21)+1, 90, 59, 20);
				}
				
				// Right
				else if(i>=31 && i<=39){
					g.setColor(Color.black);
					g.drawLine(671, 110+60*(i-31), 671, 110+60*(i-30));
					g.drawString(game_data.name[i], 690, 129+60*(i-31));
					g.drawString("$"+game_data.value[i], 690, 144+60*(i-31));
					g.setColor(game_data.color[i]);
					g.fillRect(651, 110+60*(i-31)+1, 20, 59);
				}
				
			}
		}
	}

	public Dimension getPreferredSize() {
		return new Dimension(760, 760);
	}

	public Dimension getMaximumSize() {
		return getPreferredSize();
	}

	public Dimension getMinimumSize() {
		return getPreferredSize();
	}
}
