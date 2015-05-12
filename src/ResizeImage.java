import java.awt.Image;

import javax.swing.ImageIcon;

public class ResizeImage {

	public static ImageIcon changeImage(ImageIcon oldImageIcon, int widith, int height) {
		
		Image oldImage = oldImageIcon.getImage();
		Image newImg = oldImage.getScaledInstance(widith, height,java.awt.Image.SCALE_SMOOTH);
		ImageIcon imgIcon = new ImageIcon(newImg);

		return imgIcon;
	}
}
