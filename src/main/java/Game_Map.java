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

import java.awt.Color;

public class Game_Map {

	final int Size = 40;
	final int color_size = 8;
	final int same_color_land_size = 3;
	// type 0, land
	// type 1, big block
	// type 2, chance
	// type 3, others
	// type 9, NOT assign
	int[] type = new int[Size];
	int[] id = new int[Size];
	int [][] p_x = new int[game.max_p_size][Size];
	int [][] p_y = new int[game.max_p_size][Size];

	// owner 0, No one
	// owner 1, player 1...
	int[] owner = new int[Size];
	// level 0, No house
	// level 1, one house
	// level 4, hotel (highest)
	int[] level = new int[Size];
	String[] name = new String[Size];
	long[] value = new long[Size];
	Color[] color = new Color[Size];

	//Color land_color[] = new Color[]{Color.red, Color.cyan, Color.blue, Color.green, Color.orange, Color.pink, Color.yellow, Color.gray};
	int[][] same_color = new int[color_size][same_color_land_size];

	int hospital_id;
	int jail_id;
	int parking_id;
	//int start_id = 0;

	public static int color_index(Color c) {
		if (Color.red == c) return 0;
		else if (Color.cyan == c) return 1;
		else if (Color.blue == c) return 2;
		else if (Color.green == c) return 3;
		else if (Color.orange == c) return 4;
		else if (Color.pink == c) return 5;
		else if (Color.yellow == c) return 6;
		else if (Color.gray == c) return  7;
		else
			// impossible
			return -1;
	}
}
