import javax.swing.*;
import java.awt.Color;
//import java.awt.event.*;
import java.util.*;


public class Main_Map extends JFrame{
	private static final long serialVersionUID = 1;
	
	public Game_Map game_data = new Game_Map();
	public Game_Map ini_map = new Game_Map();
	public Game_Loop game_loop;
	public Main_Map(){
		this.setTitle("Random Big Rich Man");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public Game_Map ini_game_map(Game_Map gdata){
		this.setResizable(false);
		Game_Map ini_map = new Game_Map();
		Color land_color[] = new Color[]{Color.red, Color.cyan, Color.blue, Color.green, Color.orange, Color.pink, Color.yellow, Color.gray};
		int i, j, index;
		// ini gdata
		// 0~23: land
		// 24~27: big block
		// 24: over this will give player $2000
		// 25: Jail
		// 26: Go Jail
		// 27: Hospital
		// 28~35: chance
		// 36~39: others
		for(i=0;i<gdata.Size;i++){
			gdata.id[i] = i;
			if(i>=0 && i<=23){
				gdata.type[i] = 0;
			}
			else if(i <= 27){
				gdata.type[i] = 1;
			}
			else if(i <= 35){
				gdata.type[i] = 2;
			}
			else if( i <= 39){
				gdata.type[i] = 3;
			}
		}
		
		// 0~23
		gdata.value[0]  = 2000;
		gdata.name[0]   = "Jingan";
		gdata.value[1]  = 2100;
		gdata.name[1]   = "Yongan";
		gdata.value[2]  = 2200;
		gdata.name[2]   = "Dingxi";
		gdata.value[3]  = 2300;
		gdata.name[3]   = "Xindian";
		gdata.value[4]  = 2400;
		gdata.name[4]   = "Qizhang";
		gdata.value[5]  = 2500;
		gdata.name[5]   = "Luzhou";
		gdata.value[6]  = 2600;
		gdata.name[6]   = "Jingmei";
		gdata.value[7]  = 2700;
		gdata.name[7]   = "Wanlong";
		gdata.value[8]  = 2800;
		gdata.name[8]   = "Huilong";
		gdata.value[9]  = 2900;
		gdata.name[9]   = "Guting";
		gdata.value[10] = 3000;
		gdata.name[10]  = "Muzha";
		gdata.value[11] = 3000;
		gdata.name[11]  = "Wanfang";
		gdata.value[12] = 3200;
		gdata.name[12]  = "Xinhai";
		gdata.value[13] = 3200;
		gdata.name[13]  = "Tamsui";
		gdata.value[14] = 3400;
		gdata.name[14]  = "Daan";
		gdata.value[15] = 3400;
		gdata.name[15]  = "Xinyi";
		gdata.value[16] = 3600;
		gdata.name[16]  = "Dongmen";
		gdata.value[17] = 3800;
		gdata.name[17]  = "Nangang";
		gdata.value[18] = 4000;
		gdata.name[18]  = "Shandao";
		gdata.value[19] = 4200;
		gdata.name[19]  = "Beimen";
		gdata.value[20] = 4400;
		gdata.name[20]  = "Ximen";
		gdata.value[21] = 4600;
		gdata.name[21]  = "Beitou";
		gdata.value[22] = 4800;
		gdata.name[22]  = "Nanjing";
		gdata.value[23] = 5000;
		gdata.name[23]  = "Xinhu";
		
		// ini map
		for(i=0;i<ini_map.Size;i++){
			ini_map.type[i] = 9;
		}
		
		ini_map.id[0] = 24;
		ini_map.type[0] = 1;
		
		Integer array[] = {25, 26, 27};
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(array));
		Random_Pick_List pick = new Random_Pick_List();
		
		Integer temp, temp_data; 
		temp = pick.getRandomList(list);
		ini_map.id[10] = temp;
		ini_map.type[10] = 1;
		list.remove(temp);
		array = list.toArray(new Integer[0]);
		
		temp = pick.getRandomList(list);
		ini_map.id[20] = temp;
		ini_map.type[20] = 1;
		list.remove(temp);
		array = list.toArray(new Integer[0]);
		
		temp = pick.getRandomList(list);
		ini_map.id[30] = temp;
		ini_map.type[30] = 1;
		
		// 28~35: chance
		// 36~39: others
		Integer array1[] = {28, 29, 30, 31, 32, 33, 34, 35};
		Integer array2[] = {36, 37, 38, 39};
		// 0~23 land
		Integer array3[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
		
		
		List<Integer> list1 = new ArrayList<Integer>(Arrays.asList(array1));
		List<Integer> list2 = new ArrayList<Integer>(Arrays.asList(array2));
		List<Integer> list3 = new ArrayList<Integer>(Arrays.asList(array3));
		
		for(i=0;i<4;i++){
			if(0 == i){
				array = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};	
			}
			else if(1 == i){
				array = new Integer[]{11, 12, 13, 14, 15, 16, 17, 18, 19};
			}
			else if(2 == i){
				array = new Integer[]{21, 22, 23, 24, 25, 26, 27, 28, 29};
			}
			else if(3 == i){
				array = new Integer[]{31, 32, 33, 34, 35, 36, 37, 38, 39};
			}
			list = new ArrayList<Integer>(Arrays.asList(array));
		
			temp = pick.getRandomList(list);
			temp_data = pick.getRandomList(list1);
			ini_map.id[temp] = temp_data;
			ini_map.type[temp] = 2;
			list.remove(temp);
			array = list.toArray(new Integer[0]);
			list1.remove(temp_data);
			array1 = list1.toArray(new Integer[0]);
		
			temp = pick.getRandomList(list);
			temp_data = pick.getRandomList(list1);
			ini_map.id[temp] = temp_data;
			ini_map.type[temp] = 2;
			list.remove(temp);
			array = list.toArray(new Integer[0]);
			list1.remove(temp_data);
			array1 = list1.toArray(new Integer[0]);
		
			temp = pick.getRandomList(list);
			temp_data = pick.getRandomList(list2);
			ini_map.id[temp] = temp_data;
			ini_map.type[temp] = 3;
			list.remove(temp);
			array = list.toArray(new Integer[0]);
			list2.remove(temp_data);
			array2 = list2.toArray(new Integer[0]);
		
			for(j=0;j<6;j++){
				temp = pick.getRandomList(list);
				temp_data = pick.getRandomList(list3);
				ini_map.id[temp] = temp_data;
				ini_map.type[temp] = 0;
				ini_map.owner[temp] = 0;
				list.remove(temp);
				array = list.toArray(new Integer[0]);
				list3.remove(temp_data);
				array3 = list3.toArray(new Integer[0]);
			}
		}
		
		//ini color
		j = 0;
		for(i=0;i<40;i++)
		{
			if(0 == ini_map.type[i]){
				index = ini_map.id[i];
				ini_map.name[i] = game_data.name[index];
				ini_map.value[i] = game_data.value[index];
				ini_map.owner[i] = 0;
				ini_map.color[i] = land_color[j/3];
				++j;
			}
		}

		return ini_map;
	}
	public void generate_map(game Game){
		int i;
		
		this.getContentPane().setLayout(null);
		this.setSize(767, 790);
		this.setResizable(false);
		
		ini_map = ini_game_map(game_data);
		
		Game.p1_x_now = ini_map.p1_x[0];
		Game.p1_y_now = ini_map.p1_y[0];
		Game.p2_x_now = ini_map.p2_x[0];
		Game.p2_y_now = ini_map.p2_y[0];
		Game.p3_x_now = ini_map.p3_x[0];
		Game.p3_y_now = ini_map.p3_y[0];
		Game.p4_x_now = ini_map.p4_x[0];
		Game.p4_y_now = ini_map.p4_y[0];
		
		mcanvas canvas = new mcanvas(ini_map, Game);
		canvas.setBounds(0, 0, canvas.max_size, canvas.max_size);
		
		//ini_map p_x[0], p_y[0]
		ini_map.p1_x[0] = canvas.right_x + canvas.color_small + canvas.p_gap;
		ini_map.p1_y[0] = canvas.down_y + canvas.color_small + canvas.p_gap;
				
		ini_map.p2_x[0] = canvas.right_x + canvas.color_small + canvas.p_gap + Game.p1_pawn.getIconWidth();
		ini_map.p2_y[0] = canvas.down_y + canvas.color_small + canvas.p_gap + Game.p1_pawn.getIconHeight();
				
		ini_map.p3_x[0] = canvas.right_x + canvas.color_small + canvas.p_gap + 2*Game.p1_pawn.getIconWidth();
		ini_map.p3_y[0] = canvas.down_y + canvas.color_small + canvas.p_gap + 2*Game.p1_pawn.getIconHeight();
				
		ini_map.p4_x[0] = canvas.right_x + canvas.color_small + canvas.p_gap + 3*Game.p1_pawn.getIconWidth();
		ini_map.p4_y[0] = canvas.down_y + canvas.color_small + canvas.p_gap + 3*Game.p1_pawn.getIconHeight();
		
		//ini_map p_x[1], p_y[1]
		ini_map.p1_x[1] = canvas.right_x - canvas.block_size/2 - Game.p1_pawn.getIconWidth()/2;
		ini_map.p1_y[1] = canvas.down_y + canvas.color_small + canvas.p_gap;
		ini_map.p2_x[1] = canvas.right_x - canvas.block_size/2 - Game.p2_pawn.getIconWidth()/2;
		ini_map.p2_y[1] = canvas.down_y + canvas.color_small + canvas.p_gap + Game.p1_pawn.getIconHeight();
		ini_map.p3_x[1] = canvas.right_x - canvas.block_size/2 - Game.p3_pawn.getIconWidth()/2;
		ini_map.p3_y[1] = canvas.down_y + canvas.color_small + canvas.p_gap + 2*Game.p1_pawn.getIconHeight();
		ini_map.p4_x[1] = canvas.right_x - canvas.block_size/2 - Game.p4_pawn.getIconWidth()/2;
		ini_map.p4_y[1] = canvas.down_y + canvas.color_small + canvas.p_gap + + 3*Game.p1_pawn.getIconHeight();
		
		//ini_map p_x[2~9], p_y[2~9]
		for(i=2; i<10; i++){
			ini_map.p1_x[i] = ini_map.p1_x[i-1] - canvas.block_size;
			ini_map.p1_y[i] = ini_map.p1_y[i-1];
			ini_map.p2_x[i] = ini_map.p2_x[i-1] - canvas.block_size;
			ini_map.p2_y[i] = ini_map.p2_y[i-1];
			ini_map.p3_x[i] = ini_map.p3_x[i-1] - canvas.block_size;
			ini_map.p3_y[i] = ini_map.p3_y[i-1];
			ini_map.p4_x[i] = ini_map.p4_x[i-1] - canvas.block_size;
			ini_map.p4_y[i] = ini_map.p4_y[i-1];
		}
		
		//ini_map p_x[10], p_y[10]
		ini_map.p1_x[10] = canvas.left_x - canvas.color_small - canvas.p_gap - Game.p1_pawn.getIconWidth();
		ini_map.p1_y[10] = ini_map.p1_y[9];
		ini_map.p2_x[10] = canvas.left_x - canvas.color_small - canvas.p_gap - 2*Game.p1_pawn.getIconWidth();
		ini_map.p2_y[10] = ini_map.p2_y[9];
		ini_map.p3_x[10] = canvas.left_x - canvas.color_small - canvas.p_gap - 3*Game.p1_pawn.getIconWidth();
		ini_map.p3_y[10] = ini_map.p3_y[9];
		ini_map.p4_x[10] = canvas.left_x - canvas.color_small - canvas.p_gap - 4*Game.p1_pawn.getIconWidth();
		ini_map.p4_y[10] = ini_map.p4_y[9];
		
		//ini_map p_x[11], p_y[11]
		ini_map.p1_x[11] = ini_map.p1_x[10];
		ini_map.p1_y[11] = canvas.down_y - canvas.block_size/2 - Game.p1_pawn.getIconHeight()/2;
		ini_map.p2_x[11] = ini_map.p2_x[10];
		ini_map.p2_y[11] = canvas.down_y - canvas.block_size/2 - Game.p2_pawn.getIconHeight()/2;
		ini_map.p3_x[11] = ini_map.p3_x[10];
		ini_map.p3_y[11] = canvas.down_y - canvas.block_size/2 - Game.p3_pawn.getIconHeight()/2;
		ini_map.p4_x[11] = ini_map.p4_x[10];
		ini_map.p4_y[11] = canvas.down_y - canvas.block_size/2 - Game.p4_pawn.getIconHeight()/2;
		
		//ini_map p_x[12~19], p_y[12~19]
		for(i=12; i<20; i++){
			ini_map.p1_x[i] = ini_map.p1_x[i-1];
			ini_map.p1_y[i] = ini_map.p1_y[i-1] - canvas.block_size;
			ini_map.p2_x[i] = ini_map.p2_x[i-1];
			ini_map.p2_y[i] = ini_map.p2_y[i-1] - canvas.block_size;
			ini_map.p3_x[i] = ini_map.p3_x[i-1];
			ini_map.p3_y[i] = ini_map.p3_y[i-1] - canvas.block_size;
			ini_map.p4_x[i] = ini_map.p4_x[i-1];
			ini_map.p4_y[i] = ini_map.p4_y[i-1] - canvas.block_size;
		}
		
		//set player p_x, p_y
		Game.p1_x_now = ini_map.p1_x[0];
		Game.p1_y_now = ini_map.p1_y[0];
		Game.p2_x_now = ini_map.p2_x[0];
		Game.p2_y_now = ini_map.p2_y[0];
		Game.p3_x_now = ini_map.p3_x[0];
		Game.p3_y_now = ini_map.p3_y[0];
		Game.p4_x_now = ini_map.p4_x[0];
		Game.p4_y_now = ini_map.p4_y[0];
		
		this.getContentPane().add(canvas);
		this.getContentPane().setBackground(new Color(233,234,205));
		this.setVisible(true);
		game_loop = new Game_Loop(Game, canvas, ini_map);
		game_loop.start();
	}
}
