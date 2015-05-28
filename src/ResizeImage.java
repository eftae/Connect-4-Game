/**
 * Class has a public method to resize a ImageIcon to desirable size.
 */

import java.awt.Image;

import javax.swing.ImageIcon;

public class ResizeImage {
	/**
	 * 
	 * @param oldImageIcon
	 *            ImageIcon to resize
	 * @param width
	 *            desired width
	 * @param height
	 *            desired height
	 * @return
	 */
	public static ImageIcon changeImage(ImageIcon oldImageIcon, int width,
			int height) {

		Image oldImage = oldImageIcon.getImage();
		Image newImg = oldImage.getScaledInstance(width, height,
				java.awt.Image.SCALE_SMOOTH);
		ImageIcon imgIcon = new ImageIcon(newImg);

		return imgIcon;
	}
}
