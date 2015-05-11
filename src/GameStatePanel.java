import java.awt.Color;
//import java.awt.Font;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


public class GameStatePanel extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1833192830016530003L;
	ArrayList<JButton> buttons = new ArrayList<JButton>();
	ImageIcon icn1 = ResizeImage.changeImage (new ImageIcon(this.getClass().getResource("blueDot.jpeg")), 125, 110);
	ImageIcon icn2 = ResizeImage.changeImage (new ImageIcon(this.getClass().getResource("yellowDot.jpeg")), 125, 110);
	JButton pressed;
	
	
	public GameStatePanel() {
		// setup the game panel
		setLayout(new GridLayout(6,7));
		setBorder(new TitledBorder("Single Play Game"));
		//setBackground(new Color(0, 128, 128));
		
		pressed = null;
	
		for (int i = 0; i< 42; i++) {
			buttons.add(new JButton ());
			buttons.get(i).setBackground(Color.WHITE);
			add(buttons.get(i));
			buttons.get(i).addActionListener(this);
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(buttons.indexOf(e.getSource()));
        if(pressed == null && buttons.contains(e.getSource())){
        	pressed = (JButton)e.getSource();
        }
	}
	
	public void setButton (int btnId, int colorId){
		JButton b = buttons.get(btnId);
		if(colorId == 0) b.setIcon(icn1);
		else b.setIcon(icn2);
	}
	
	public int registerMove() {
		if(pressed != null){
		    int ret = buttons.indexOf(pressed) % 7;
		    pressed = null;
		    return ret;
		} else return -1;
	}
}
