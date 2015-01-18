import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class game {
	public static Game_Setting gs1 = new Game_Setting();
	public ImageIcon image1 = new ImageIcon(Game_Setting.class.getResource("/Image/gtkmonop-token1.png"));
	public ImageIcon image2 = new ImageIcon(Game_Setting.class.getResource("/Image/gtkmonop-token8.png"));
	public ImageIcon image3 = new ImageIcon(Game_Setting.class.getResource("/Image/gtkmonop-token7.png"));
	public ImageIcon image4 = new ImageIcon(Game_Setting.class.getResource("/Image/gtkmonop-token6.png"));
	public ImageIcon image5 = new ImageIcon(Game_Setting.class.getResource("/Image/gtkmonop-token5.png"));
	public ImageIcon image6 = new ImageIcon(Game_Setting.class.getResource("/Image/gtkmonop-token4.png"));
	public ImageIcon image7 = new ImageIcon(Game_Setting.class.getResource("/Image/gtkmonop-token3.png"));
	public ImageIcon image8 = new ImageIcon(Game_Setting.class.getResource("/Image/gtkmonop-token2.png"));
	
	public ImageIcon imagep1 = new ImageIcon(Game_Setting.class.getResource("/Image/pawn1.gif"));
	public ImageIcon imagep2 = new ImageIcon(Game_Setting.class.getResource("/Image/pawn2.gif"));
	public ImageIcon imagep3 = new ImageIcon(Game_Setting.class.getResource("/Image/pawn3.gif"));
	public ImageIcon imagep4 = new ImageIcon(Game_Setting.class.getResource("/Image/pawn4.gif"));
	public ImageIcon imagep5 = new ImageIcon(Game_Setting.class.getResource("/Image/pawn5.gif"));
	public ImageIcon imagep6 = new ImageIcon(Game_Setting.class.getResource("/Image/pawn6.gif"));
	public ImageIcon imagep7 = new ImageIcon(Game_Setting.class.getResource("/Image/pawn7.gif"));
	public ImageIcon imagep8 = new ImageIcon(Game_Setting.class.getResource("/Image/pawn8.gif"));
	
	public int turn = 0;//0: player1
	
	public String p1_name;
	public int p1_type;//0:human, 1:AI, 9:out
	public int p1_icon;
	public ImageIcon p1_ic;
	public ImageIcon p1_pawn;
	public long p1_money;
	public int p1_x_now;
	public int p1_y_now;
	public int p1_id = 0;
	public int p1_dest_id = 0;
	
	public String p2_name;
	public int p2_type;
	public int p2_icon;
	public ImageIcon p2_ic;
	public ImageIcon p2_pawn;
	public long p2_money;
	public int p2_x_now;
	public int p2_y_now;
	public int p2_id = 0;
	public int p2_dest_id = 0;
	
	public String p3_name;
	public int p3_type;
	public int p3_icon;
	public ImageIcon p3_ic;
	public ImageIcon p3_pawn;
	public long p3_money;
	public int p3_x_now;
	public int p3_y_now;
	public int p3_id = 0;
	public int p3_dest_id = 0;
	
	public String p4_name;
	public int p4_type;
	public int p4_icon;
	public ImageIcon p4_ic;
	public ImageIcon p4_pawn;
	public long p4_money;
	public int p4_x_now;
	public int p4_y_now;
	public int p4_id = 0;
	public int p4_dest_id = 0;
	
	public static void main(String[] args) {
		game Game = new game();
		JFrame frame = new JFrame("Random Big Rich Man");
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnANewGame = new JButton("A New Game");
		btnANewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				gs1.show(frame, Game);
			}
		});
		btnANewGame.setBounds(79, 112, 121, 23);
		panel.add(btnANewGame);
	}
}