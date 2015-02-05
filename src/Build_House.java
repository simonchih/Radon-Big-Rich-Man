import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Build_House {
	public game mygame;
	public  mcanvas mycanvas;
	public Game_Map game_map;
	public Game_Loop mygl;
	public JFrame bh = new JFrame("Building");
	Build_House(game Game, Game_Map gm, Game_Loop gl){
		bh.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mygame = Game;
		//mycanvas = canvas;
		game_map = gm;
		mygl = gl;
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public void show(){
		String h;
		String button_name;
		long spent;
		int turn_id = mygame.turn;
		
		if(4 == game_map.level[mygame.p_dest_id[turn_id]]){
			mygl.susp = false;
			return;
		}
		else if(3 == game_map.level[mygame.p_dest_id[turn_id]]){
			h = "hotel";
			button_name = "Build Hotel";
			spent = (long) (game_map.value[mygame.p_dest_id[turn_id]]*(0.4));
		}
		else {
			h = "house";
			button_name = "Build House";
			spent = (long) (game_map.value[mygame.p_dest_id[turn_id]]*(0.2));
		}
		
		bh.getContentPane().removeAll();
		bh.setSize(450, 300);
		bh.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hi, "+mygame.p_name[turn_id]+":");
		lblNewLabel.setBounds(10, 10, 414, 15);
		bh.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bh.dispose();
				mygl.susp = false;
			}
		});
		btnNewButton.setBounds(55, 214, 124, 23);
		bh.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton(button_name);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mygame.deal((-1)*spent, turn_id);
				game_map.level[mygame.p_dest_id[turn_id]] += 1;
				bh.dispose();
				mygl.susp = false;
			}
		});
		btnNewButton_1.setBounds(283, 214, 124, 23);
		bh.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Do you want to spent $"+ spent+" to build a "+h+"?");
		lblNewLabel_1.setBounds(20, 26, 404, 15);
		bh.getContentPane().add(lblNewLabel_1);
		
		if(spent > mygame.p_money[turn_id]){
			JLabel lblNewLabel_2 = new JLabel("You have NOT enough money!");
			lblNewLabel_2.setBounds(20, 189, 404, 15);
			lblNewLabel_2.setForeground(Color.RED);
			bh.getContentPane().add(lblNewLabel_2);
			btnNewButton_1.setEnabled(false);
		}
		bh.setVisible(true);
	}
}
