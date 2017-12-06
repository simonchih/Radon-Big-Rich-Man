import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;

public class Game_Setting {
	public JFrame games1 = new JFrame("Player1 Setting");
	public Game_Setting2 gs2 = new Game_Setting2();
	public Game_Setting(){
		games1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public void show(final JFrame f, final game Game){
		games1.setSize(450, 320);
		games1.getContentPane().setLayout(null);
		
		final JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(122, 40, 46, 15);
		games1.getContentPane().add(lblNewLabel);
		
		final JEditorPane dtrpnPlayer = new JEditorPane();
		dtrpnPlayer.setText("Player1");
		dtrpnPlayer.setBounds(205, 34, 106, 21);
		games1.getContentPane().add(dtrpnPlayer);
		
		final JLabel lblHumanai = new JLabel("Human/AI");
		lblHumanai.setBounds(122, 79, 68, 15);
		games1.getContentPane().add(lblHumanai);
		
		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Human", "AI"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(205, 76, 106, 21);
		games1.getContentPane().add(comboBox);
		
		final JLabel lblIcon = new JLabel("Icon");
		lblIcon.setBounds(122, 124, 46, 15);
		games1.getContentPane().add(lblIcon);
		
		final JComboBox<ImageIcon> comboBox_1 = new JComboBox<ImageIcon>();
		comboBox_1.setBounds(205, 118, 106, 21);
		final ImageIcon array[] = new ImageIcon[] {Game.image1, Game.image2, Game.image3, Game.image4, Game.image5, Game.image6, Game.image7, Game.image8};
		comboBox_1.setModel(new DefaultComboBoxModel<ImageIcon>(array));
		comboBox_1.setSelectedIndex(0);
		games1.getContentPane().add(comboBox_1);
		
		final JLabel lblNewLabel_1 = new JLabel("Money");
		lblNewLabel_1.setBounds(122, 165, 73, 15);
		games1.getContentPane().add(lblNewLabel_1);
		
		final JEditorPane editorPane = new JEditorPane();
		editorPane.setText("30000");
		editorPane.setBounds(205, 159, 106, 21);
		games1.getContentPane().add(editorPane);
		
		final JButton btnCancel = new JButton("Previous");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				games1.setVisible(false);
				f.setVisible(true);
			}
		});
		btnCancel.setBounds(103, 214, 87, 23);
		games1.getContentPane().add(btnCancel);
			
		final JLabel lblNewLabel_2 = new JLabel("(error value)");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(321, 165, 103, 15);
		lblNewLabel_2.setVisible(false);
		games1.getContentPane().add(lblNewLabel_2);
		
		final JLabel lblErrorValue = new JLabel("(error value)");
		lblErrorValue.setForeground(Color.RED);
		lblErrorValue.setBounds(321, 40, 103, 15);
		lblErrorValue.setVisible(false);
		games1.getContentPane().add(lblErrorValue);
		
		final JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final ImageIcon array[] = new ImageIcon[] {Game.image1, Game.image2, Game.image3, Game.image4, Game.image5, Game.image6, Game.image7, Game.image8};
				final ImageIcon parray[] = new ImageIcon[] {Game.imagep1, Game.imagep2, Game.imagep3, Game.imagep4, Game.imagep5, Game.imagep6, Game.imagep7, Game.imagep8};
				try{
					Game.p_name[0] = dtrpnPlayer.getText();
				}
				catch(final Exception e1){
					lblErrorValue.setVisible(true);
					return;
				}
				lblErrorValue.setVisible(false);
				try{
					Game.p_money[0] = Long.parseLong(editorPane.getText());
				}
				catch(final Exception e2){
					lblNewLabel_2.setVisible(true);
					return;
				}
				lblNewLabel_2.setVisible(false);
				
				Game.p_type[0] = comboBox.getSelectedIndex();
				//System.out.println(Game.p_type[0]);
				
				Game.p_icon[0] = comboBox_1.getSelectedIndex();
				Game.p_ic[0] = array[Game.p_icon[0]];
				Game.p_pawn[0] = parray[Game.p_icon[0]];
				games1.setVisible(false);
				gs2.show(games1, Game);
			}
		});
		
		btnNext.setBounds(224, 214, 94, 23);
		games1.getContentPane().add(btnNext);
		
		games1.setVisible(true);
	}
}
