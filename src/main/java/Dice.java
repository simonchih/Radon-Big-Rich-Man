import javax.swing.*;
import java.util.*;

public class Dice {
	public ImageIcon idice1_2 = new ImageIcon(Dice.class.getResource("/Image/die-1+2.gif"));
	public ImageIcon idice1_3 = new ImageIcon(Dice.class.getResource("/Image/die-1+3.gif"));
	public ImageIcon idice1_4 = new ImageIcon(Dice.class.getResource("/Image/die-1+4.gif"));
	public ImageIcon idice1_5 = new ImageIcon(Dice.class.getResource("/Image/die-1+5.gif"));
	public ImageIcon idice2_1 = new ImageIcon(Dice.class.getResource("/Image/die-2+1.gif"));
	public ImageIcon idice2_3 = new ImageIcon(Dice.class.getResource("/Image/die-2+3.gif"));
	public ImageIcon idice2_4 = new ImageIcon(Dice.class.getResource("/Image/die-2+4.gif"));
	public ImageIcon idice2_6 = new ImageIcon(Dice.class.getResource("/Image/die-2+6.gif"));
	public ImageIcon idice3_1 = new ImageIcon(Dice.class.getResource("/Image/die-3+1.gif"));
	public ImageIcon idice3_2 = new ImageIcon(Dice.class.getResource("/Image/die-3+2.gif"));
	public ImageIcon idice3_5 = new ImageIcon(Dice.class.getResource("/Image/die-3+5.gif"));
	public ImageIcon idice3_6 = new ImageIcon(Dice.class.getResource("/Image/die-3+6.gif"));
	public ImageIcon idice4_1 = new ImageIcon(Dice.class.getResource("/Image/die-4+1.gif"));
	public ImageIcon idice4_2 = new ImageIcon(Dice.class.getResource("/Image/die-4+2.gif"));
	public ImageIcon idice4_5 = new ImageIcon(Dice.class.getResource("/Image/die-4+5.gif"));
	public ImageIcon idice4_6 = new ImageIcon(Dice.class.getResource("/Image/die-4+6.gif"));
	public ImageIcon idice5_1 = new ImageIcon(Dice.class.getResource("/Image/die-5+1.gif"));
	public ImageIcon idice5_3 = new ImageIcon(Dice.class.getResource("/Image/die-5+3.gif"));
	public ImageIcon idice5_4 = new ImageIcon(Dice.class.getResource("/Image/die-5+4.gif"));
	public ImageIcon idice5_6 = new ImageIcon(Dice.class.getResource("/Image/die-5+6.gif"));
	public ImageIcon idice6_2 = new ImageIcon(Dice.class.getResource("/Image/die-6+2.gif"));
	public ImageIcon idice6_3 = new ImageIcon(Dice.class.getResource("/Image/die-6+3.gif"));
	public ImageIcon idice6_4 = new ImageIcon(Dice.class.getResource("/Image/die-6+4.gif"));
	public ImageIcon idice6_5 = new ImageIcon(Dice.class.getResource("/Image/die-6+5.gif"));
	public ImageIcon ddice[] = {idice1_2, idice1_3, idice1_4, idice1_5, idice2_1, idice2_3, idice2_4, idice2_6, 
			idice3_1, idice3_2, idice3_5, idice3_6, idice4_1, idice4_2, idice4_5, idice4_6,
			idice5_1, idice5_3, idice5_4, idice5_6, idice6_2, idice6_3, idice6_4, idice6_5};
	public ImageIcon idice1;
	public ImageIcon idice2;
	
	public int dice1;
	public int dice1_raw = 0;
	public int dice2;
	public int dice2_raw = 4;
	public int count;
	
	public int idice1_x = 480;
	public int idice1_y = 520;
	public int idice2_x = 540;
	public int idice2_y = 520;
	
	Dice(){
		idice1 = ddice[dice1_raw];
		idice2 = ddice[dice2_raw];
		dice1 = dice1_raw/4 + 1;
		dice2 = dice2_raw/4 + 1;
		count = dice1 + dice2;
	}
	
	public int Generate_Random_Number(){
		Random r = new Random();
		int i = r.nextInt(ddice.length);
			return i;
	}
	public void Roll_Dice(){
		int i;
		i = Generate_Random_Number();
		dice1_raw = i;
		i = Generate_Random_Number();
		dice2_raw = i;
		idice1 = ddice[dice1_raw];
		idice2 = ddice[dice2_raw];
		dice1 = dice1_raw/4 + 1;
		dice2 = dice2_raw/4 + 1;
		count = dice1 + dice2;
		//System.out.println(dice1_raw);
	    //System.out.println(dice2_raw); 
	}
}
