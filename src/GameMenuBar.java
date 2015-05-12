import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class GameMenuBar extends JMenuBar {
	
	private Connect4 mainGame;
	private SinglePlayerWindow psw;
	
	public GameMenuBar(final Connect4 mainGame, final SinglePlayerWindow psw) {
		
			this.mainGame = mainGame;
			this.psw = psw;
			JMenu first = new JMenu("Menu");
			add(first);
		
			
	        ImageIcon icon1 = new ImageIcon ("src/pics/Save-50.png");
	        ImageIcon icon2 = new ImageIcon ("src/pics/Home-50.png");
	        ImageIcon icon3 = new ImageIcon ("src/pics/Exit-50.png");
	        ImageIcon icon4 = new ImageIcon ("Exit-50.png");  
	     
	 	    ImageIcon newIcon1 = ResizeImage.changeImage(icon1, 18, 18);
	 	    ImageIcon newIcon2 = ResizeImage.changeImage(icon2, 20, 20);
	 	    ImageIcon newIcon3 = ResizeImage.changeImage(icon3, 20, 20);
	 	    
	       

	        JMenuItem menuItem1 = new JMenuItem("Save",newIcon1);
	        JMenuItem menuItem2 = new JMenuItem("Home", newIcon2);
	        JMenuItem menuItem3 = new JMenuItem("Exit", newIcon3);
	        
	        menuItem1.setToolTipText("Exit application");
	        menuItem1.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	                System.exit(0);
	            }
	        });
	        
	        menuItem2.setToolTipText("Return to Home Menu");
	        menuItem2.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	            	mainGame.setVisity(true);
	            	psw.dispose();
	              
	            }
	        });
	        
	        menuItem3.setToolTipText("Exit application");
	        menuItem3.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	                System.exit(0);
	            }
	        });
	        

	        first.add(menuItem1);
	        first.addSeparator();
	        first.add(menuItem2);
	        first.addSeparator();
	        first.add(menuItem3);

	}

}
