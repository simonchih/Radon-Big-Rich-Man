import java.awt.*;
import javax.swing.*;

public class mcanvas extends JComponent {
	private static final long serialVersionUID = 1;
	public Game_Map game_data = new Game_Map();
	
	public mcanvas(Game_Map gm) {
		game_data = gm;
	}

	public void paintComponent(Graphics g) {
		int i;
		
		super.paintComponent(g);

		g.drawLine(110, 0, 110, 760);
		g.drawLine(650, 0, 650, 760);
		g.drawLine(0, 110, 760, 110);
		g.drawLine(0, 650, 760, 650);
		
		//Up
		for(i=170;i<650;i+=60){
			g.drawLine(i, 0, i, 110);
		}
		
		//Down
		for(i=170;i<650;i+=60){
			g.drawLine(i, 650, i, 760);
		}
		
		//Left
		for(i=170;i<650;i+=60){
			g.drawLine(0, i, 110, i);
		}
		
		//Right
		for(i=170;i<650;i+=60){
			g.drawLine(650, i, 760, i);
		}
		
		for(i=1;i<40;i++)
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
