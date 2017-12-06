import javax.swing.*;

import java.util.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Game_Setting4 {
	public JFrame games1 = new JFrame("Player4 Setting");
	public Main_Map mmap;
	public Game_Setting4(){
		games1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public void show(final JFrame f, final game Game){
		mmap = new Main_Map(Game);
		
		games1.getContentPane().removeAll();
		games1.setSize(450, 320);
		games1.getContentPane().setLayout(null);
		
		final JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(122, 40, 46, 15);
		games1.getContentPane().add(lblNewLabel);
		
		final JEditorPane dtrpnPlayer = new JEditorPane();
		dtrpnPlayer.setText("Player4");
		dtrpnPlayer.setBounds(205, 34, 106, 21);
		games1.getContentPane().add(dtrpnPlayer);
		
		final JLabel lblHumanai = new JLabel("Human/AI");
		lblHumanai.setBounds(122, 79, 68, 15);
		games1.getContentPane().add(lblHumanai);
		
		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Human", "AI"}));
		comboBox.setSelectedIndex(1);
		comboBox.setBounds(205, 76, 106, 21);
		games1.getContentPane().add(comboBox);
		
		final JLabel lblIcon = new JLabel("Icon");
		lblIcon.setBounds(122, 124, 46, 15);
		games1.getContentPane().add(lblIcon);
		
		final JComboBox<ImageIcon> comboBox_1 = new JComboBox<ImageIcon>();
		comboBox_1.setBounds(205, 118, 106, 21);
		ImageIcon array[] = new ImageIcon[] {Game.image1, Game.image2, Game.image3, Game.image4, Game.image5, Game.image6, Game.image7, Game.image8};
		final List<ImageIcon> list = new ArrayList<ImageIcon>(Arrays.asList(array));
		list.remove(Game.p_icon[0]);
		array = list.toArray(new ImageIcon[0]);
		list.remove(Game.p_icon[1]);
		array = list.toArray(new ImageIcon[0]);
		list.remove(Game.p_icon[2]);
		array = list.toArray(new ImageIcon[0]);
		comboBox_1.setModel(new DefaultComboBoxModel<ImageIcon>(array));
		comboBox_1.setSelectedIndex(1);
		games1.getContentPane().add(comboBox_1);
		
		final JLabel lblNewLabel_1 = new JLabel("Money");
		lblNewLabel_1.setBounds(122, 165, 68, 15);
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
		btnCancel.setBounds(85, 214, 155, 23);
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
		
		final JButton btnNext = new JButton("Finish");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon array[] = new ImageIcon[] {Game.image1, Game.image2, Game.image3, Game.image4, Game.image5, Game.image6, Game.image7, Game.image8};
				ImageIcon parray[] = new ImageIcon[] {Game.imagep1, Game.imagep2, Game.imagep3, Game.imagep4, Game.imagep5, Game.imagep6, Game.imagep7, Game.imagep8};
				
				final List<ImageIcon> list = new ArrayList<ImageIcon>(Arrays.asList(array));
				list.remove(Game.p_icon[0]);
				array = list.toArray(new ImageIcon[0]);
				list.remove(Game.p_icon[1]);
				array = list.toArray(new ImageIcon[0]);
				list.remove(Game.p_icon[2]);
				array = list.toArray(new ImageIcon[0]);
				List<ImageIcon> listp = new ArrayList<ImageIcon>(Arrays.asList(parray));
				listp.remove(Game.p_icon[0]);
				parray = listp.toArray(new ImageIcon[0]);
				listp.remove(Game.p_icon[1]);
				parray = listp.toArray(new ImageIcon[0]);
				listp.remove(Game.p_icon[2]);
				parray = listp.toArray(new ImageIcon[0]);
				
				try{
					Game.p_name[3] = dtrpnPlayer.getText();
				}
				catch(final Exception e1){
					lblErrorValue.setVisible(true);
					return;
				}
				lblErrorValue.setVisible(false);
				
				try{
					Game.p_money[3] = Long.parseLong(editorPane.getText());
				}
				catch(final Exception e2){
					lblNewLabel_2.setVisible(true);
					return;
				}
				lblNewLabel_2.setVisible(false);
				
				Game.p_type[3] = comboBox.getSelectedIndex();
				Game.p_icon[3] = comboBox_1.getSelectedIndex();
				Game.p_ic[3] = array[Game.p_icon[3]];
				Game.p_pawn[3] = parray[Game.p_icon[3]];
				
				games1.setVisible(false);
				mmap.generate_map(Game);
			}
		});
		btnNext.setBounds(250, 214, 94, 23);
		games1.getContentPane().add(btnNext);
		games1.setVisible(true);
	}
}