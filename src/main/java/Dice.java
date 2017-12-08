/*
 * Copyright (C) 2017 Simon <ficstudio@yahoo.com.tw>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.Random;
import javax.swing.ImageIcon;

public class Dice {

	public static final ImageIcon idice1_2 = new ImageIcon(Dice.class.getResource("/Image/die-1+2.gif"));
	public static final ImageIcon idice1_3 = new ImageIcon(Dice.class.getResource("/Image/die-1+3.gif"));
	public static final ImageIcon idice1_4 = new ImageIcon(Dice.class.getResource("/Image/die-1+4.gif"));
	public static final ImageIcon idice1_5 = new ImageIcon(Dice.class.getResource("/Image/die-1+5.gif"));
	public static final ImageIcon idice2_1 = new ImageIcon(Dice.class.getResource("/Image/die-2+1.gif"));
	public static final ImageIcon idice2_3 = new ImageIcon(Dice.class.getResource("/Image/die-2+3.gif"));
	public static final ImageIcon idice2_4 = new ImageIcon(Dice.class.getResource("/Image/die-2+4.gif"));
	public static final ImageIcon idice2_6 = new ImageIcon(Dice.class.getResource("/Image/die-2+6.gif"));
	public static final ImageIcon idice3_1 = new ImageIcon(Dice.class.getResource("/Image/die-3+1.gif"));
	public static final ImageIcon idice3_2 = new ImageIcon(Dice.class.getResource("/Image/die-3+2.gif"));
	public static final ImageIcon idice3_5 = new ImageIcon(Dice.class.getResource("/Image/die-3+5.gif"));
	public static final ImageIcon idice3_6 = new ImageIcon(Dice.class.getResource("/Image/die-3+6.gif"));
	public static final ImageIcon idice4_1 = new ImageIcon(Dice.class.getResource("/Image/die-4+1.gif"));
	public static final ImageIcon idice4_2 = new ImageIcon(Dice.class.getResource("/Image/die-4+2.gif"));
	public static final ImageIcon idice4_5 = new ImageIcon(Dice.class.getResource("/Image/die-4+5.gif"));
	public static final ImageIcon idice4_6 = new ImageIcon(Dice.class.getResource("/Image/die-4+6.gif"));
	public static final ImageIcon idice5_1 = new ImageIcon(Dice.class.getResource("/Image/die-5+1.gif"));
	public static final ImageIcon idice5_3 = new ImageIcon(Dice.class.getResource("/Image/die-5+3.gif"));
	public static final ImageIcon idice5_4 = new ImageIcon(Dice.class.getResource("/Image/die-5+4.gif"));
	public static final ImageIcon idice5_6 = new ImageIcon(Dice.class.getResource("/Image/die-5+6.gif"));
	public static final ImageIcon idice6_2 = new ImageIcon(Dice.class.getResource("/Image/die-6+2.gif"));
	public static final ImageIcon idice6_3 = new ImageIcon(Dice.class.getResource("/Image/die-6+3.gif"));
	public static final ImageIcon idice6_4 = new ImageIcon(Dice.class.getResource("/Image/die-6+4.gif"));
	public static final ImageIcon idice6_5 = new ImageIcon(Dice.class.getResource("/Image/die-6+5.gif"));
	public static final ImageIcon ddice[] = {idice1_2, idice1_3, idice1_4, idice1_5, idice2_1, idice2_3, idice2_4, idice2_6,
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

	private final Random r;

	Dice() {
		this.r = new Random();
		this.idice1 = ddice[dice1_raw];
		this.idice2 = ddice[dice2_raw];
		this.dice1 = dice1_raw/4 + 1;
		this.dice2 = dice2_raw/4 + 1;
		this.count = dice1 + dice2;
	}

	public int Generate_Random_Number() {
		return r.nextInt(ddice.length);
	}

	public void Roll_Dice() {
		dice1_raw = Generate_Random_Number();
		dice2_raw = Generate_Random_Number();
		idice1 = ddice[dice1_raw];
		idice2 = ddice[dice2_raw];
		dice1 = dice1_raw/4 + 1;
		dice2 = dice2_raw/4 + 1;
		count = dice1 + dice2;
	}
}
