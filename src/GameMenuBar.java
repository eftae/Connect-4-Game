import java.awt.Font;
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
	private PlayerWindow psw;
	private static int counter = 0;
	
	public GameMenuBar(final Connect4 mainGame, final PlayerWindow psw) {
		
			this.mainGame = mainGame;
			this.psw = psw;
			JMenu first = new JMenu("Menu");
			add(first);
		
			
	        ImageIcon icon1 = new ImageIcon ("src/pics/Save-50.png");
	        ImageIcon icon2 = new ImageIcon ("src/pics/Home-50.png");
	        ImageIcon icon3 = new ImageIcon ("src/pics/Exit-50.png");
	        ImageIcon icon4 = new ImageIcon ("src/pics/Mute.png");
	        ImageIcon icon5 = new ImageIcon ("src/pics/Speaker.png");
	     
	 	    ImageIcon newIcon1 = ResizeImage.changeImage(icon1, 18, 18);
	 	    ImageIcon newIcon2 = ResizeImage.changeImage(icon2, 20, 20);
	 	    ImageIcon newIcon3 = ResizeImage.changeImage(icon3, 20, 20);
	 	    final ImageIcon newIcon4 = ResizeImage.changeImage(icon4, 20, 20);
	 	    final ImageIcon newIcon5 = ResizeImage.changeImage(icon5, 20, 20);
	 	    
	       

	        JMenuItem menuItem1 = new JMenuItem("Save",newIcon1);
	        JMenuItem menuItem2 = new JMenuItem("Home", newIcon2);
	        JMenuItem menuItem3 = new JMenuItem("Exit", newIcon3);
	        final JMenuItem menuItem4 = new JMenuItem("mute", newIcon4);
	        
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
	        
	        menuItem4.setToolTipText("mute/play the music");
	        menuItem4.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	            	if (counter %2 == 0) {
	            		menuItem4.setIcon(newIcon5);
						menuItem4.setText("music");
						BackgroundMusic.stopMusic();					
						counter++;
	            	}else {
	            		menuItem4.setIcon(newIcon4);
						menuItem4.setText("mute");
						BackgroundMusic
						.music("src/sound/2-05_Playing_with_a_Full_Deck.wav");					
						counter++;	            		
	            	}
	                
	            }
	        });
	        
	        
	        

	        first.add(menuItem1);
	        first.addSeparator();
	        first.add(menuItem2);
	        first.addSeparator();
	        first.add(menuItem4);
	        first.addSeparator();
	        first.add(menuItem3);

	}

}
