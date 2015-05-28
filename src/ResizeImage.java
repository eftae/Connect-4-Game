/**
 * Class has a public method to resize the image to desirable size
 */

import java.awt.Image;

import javax.swing.ImageIcon;

public class ResizeImage {
	/**
	 * 
	 * @param oldImageIcon
	 * @param widith
	 * @param height
	 * @return
	 */
	public static ImageIcon changeImage(ImageIcon oldImageIcon, int widith,
			int height) {

		Image oldImage = oldImageIcon.getImage();
		Image newImg = oldImage.getScaledInstance(widith, height,
				java.awt.Image.SCALE_SMOOTH);
		ImageIcon imgIcon = new ImageIcon(newImg);

		return imgIcon;
	}
}
