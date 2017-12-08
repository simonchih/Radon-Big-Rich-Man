
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game_Setting2 {

	private final int playerNum = 2;
	private final int playerIdx = playerNum - 1;
	public JFrame games1 = new JFrame("Player" + playerNum + " Setting");
	public Game_Setting3 gs3 = new Game_Setting3();

	public Game_Setting2() {
		games1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	/**
	 * @param f
	 * @param Game
	 * @wbp.parser.entryPoint
	 */
	public void show(final JFrame f, final game Game) {
		games1.getContentPane().removeAll();
		games1.setSize(450, 320);
		games1.getContentPane().setLayout(null);

		final JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(122, 40, 46, 15);
		games1.getContentPane().add(lblNewLabel);

		final JEditorPane dtrpnPlayer = new JEditorPane();
		dtrpnPlayer.setText("Player" + playerNum);
		dtrpnPlayer.setBounds(205, 34, 106, 21);
		games1.getContentPane().add(dtrpnPlayer);

		final JLabel lblHumanai = new JLabel("Human/AI");
		lblHumanai.setBounds(122, 79, 68, 15);
		games1.getContentPane().add(lblHumanai);

		final JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Human", "AI"}));
		comboBox.setSelectedIndex(1);
		comboBox.setBounds(205, 76, 106, 21);
		games1.getContentPane().add(comboBox);

		final JLabel lblIcon = new JLabel("Icon");
		lblIcon.setBounds(122, 124, 46, 15);
		games1.getContentPane().add(lblIcon);

		final JComboBox<ImageIcon> comboBox_1 = new JComboBox<>();
		comboBox_1.setBounds(205, 118, 106, 21);
		ImageIcon array[] = new ImageIcon[] {Game.image1, Game.image2, Game.image3, Game.image4, Game.image5, Game.image6, Game.image7, Game.image8};
		final List<ImageIcon> list = new ArrayList<>(Arrays.asList(array));
		list.remove(Game.p_icon[0]);
		array = list.toArray(new ImageIcon[0]);
		comboBox_1.setModel(new DefaultComboBoxModel<>(array));
		comboBox_1.setSelectedIndex(0);
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
			@Override
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

		final JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ImageIcon array[] = new ImageIcon[] {Game.image1, Game.image2, Game.image3, Game.image4, Game.image5, Game.image6, Game.image7, Game.image8};
				ImageIcon parray[] = new ImageIcon[] {Game.imagep1, Game.imagep2, Game.imagep3, Game.imagep4, Game.imagep5, Game.imagep6, Game.imagep7, Game.imagep8};

				final List<ImageIcon> list = new ArrayList<>(Arrays.asList(array));
				list.remove(Game.p_icon[0]);
				array = list.toArray(new ImageIcon[0]);
				final List<ImageIcon> listp = new ArrayList<>(Arrays.asList(parray));
				listp.remove(Game.p_icon[0]);
				parray = listp.toArray(new ImageIcon[0]);
				try{
					Game.p_name[playerIdx] = dtrpnPlayer.getText();
				} catch(final Exception e1){
					lblErrorValue.setVisible(true);
					return;
				}
				lblErrorValue.setVisible(false);

				try {
					Game.p_money[playerIdx] = Long.parseLong(editorPane.getText());
				} catch(final NumberFormatException e2) {
					lblNewLabel_2.setVisible(true);
					return;
				}
				lblNewLabel_2.setVisible(false);

				Game.p_type[playerIdx] = comboBox.getSelectedIndex();
				Game.p_icon[playerIdx] = comboBox_1.getSelectedIndex();
				Game.p_ic[playerIdx] = array[Game.p_icon[playerIdx]];
				Game.p_pawn[playerIdx] = parray[Game.p_icon[playerIdx]];

				games1.setVisible(false);
				gs3.show(games1, Game);
			}
		});
		btnNext.setBounds(250, 214, 94, 23);
		games1.getContentPane().add(btnNext);
		games1.setVisible(true);
	}
}
