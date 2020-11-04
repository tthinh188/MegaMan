import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

public class ImageTransparent {
	private static ImageFilter filter = new RGBImageFilter() {
        int transparentColor = Color.white.getRGB() | 0xFF000000;

        public final int filterRGB(int x, int y, int rgb) {
           if ((rgb | 0xFF000000) == transparentColor) {
              return 0x00FFFFFF & rgb;
           } else {
              return rgb;
           }
        }
     };
     
     public static Image makeTransparent(Image i) {
    	 ImageProducer filteredImgProd = new FilteredImageSource(i.getSource(), filter);
    	 Image transparentImg = Toolkit.getDefaultToolkit().createImage(filteredImgProd);
		return transparentImg;
     }
}
