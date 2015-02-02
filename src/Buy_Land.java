import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Buy_Land{
	public game mygame;
	public  mcanvas mycanvas;
	public Game_Map game_map;
	public Game_Loop mygl;
	public JFrame bl = new JFrame("Buy Lnad");
	Buy_Land(game Game, mcanvas canvas, Game_Map gm, Game_Loop gl){
		bl.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mygame = Game;
		mycanvas = canvas;
		game_map = gm;
		mygl = gl;
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public void show(int turn_id){
		bl.getContentPane().removeAll();
		bl.setSize(450, 300);
		bl.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hi, "+mygame.p_name[turn_id]+":");
		lblNewLabel.setBounds(10, 10, 414, 15);
		bl.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bl.dispose();
				mygl.susp = false;
			}
		});
		btnNewButton.setBounds(55, 214, 87, 23);
		bl.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Buy Land");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//mygame.p_money[turn_id] -= game_map.value[mygame.p_dest_id[turn_id]];
				mygame.deal((-1)*game_map.value[mygame.p_dest_id[turn_id]], turn_id);
				
				game_map.owner[mygame.p_dest_id[turn_id]] = turn_id + 1;
				bl.dispose();
				mygl.susp = false;
			}
		});
		btnNewButton_1.setBounds(299, 214, 87, 23);
		bl.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Do you want to buy "+ game_map.name[mygame.p_dest_id[turn_id]]+" with $"+game_map.value[mygame.p_dest_id[turn_id]]+"?");
		lblNewLabel_1.setBounds(20, 26, 404, 15);
		bl.getContentPane().add(lblNewLabel_1);
		
		if(game_map.value[mygame.p_dest_id[turn_id]] > mygame.p_money[turn_id]){
			JLabel lblNewLabel_2 = new JLabel("You have NOT enough money!");
			lblNewLabel_2.setBounds(20, 189, 404, 15);
			lblNewLabel_2.setForeground(Color.RED);
			bl.getContentPane().add(lblNewLabel_2);
			btnNewButton_1.setEnabled(false);
		}
		bl.setVisible(true);
	}
}
