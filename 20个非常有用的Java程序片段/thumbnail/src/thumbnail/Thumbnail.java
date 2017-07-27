package thumbnail;

import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 创建图片的缩略图
 * 
 * @author huang
 *
 */
public class Thumbnail {

	public static void main(String[] args) throws ImageFormatException, InterruptedException, IOException {

		Thumbnail thumbnail = new Thumbnail();
		
		String srcImage="resources/1.jpg";
		String desImage="resources/2.jpg";
		
		thumbnail.createThumbnail(srcImage, 100, 80, 90, desImage);
	}

	/**
	 * 
	 * @param srcImage
	 *            源图片路径名
	 * @param thumbWidth
	 *            缩略图宽度
	 * @param thumbHeight
	 *            缩略图高度
	 * @param quality
	 *            缩略图质量
	 * @param desImage
	 *            生成的缩略图路径
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws ImageFormatException 
	 */
	public void createThumbnail(String srcImage, int thumbWidth, int thumbHeight, int quality, String desImage) throws InterruptedException, ImageFormatException, IOException {

		// load image from srcImage
		Image image=Toolkit.getDefaultToolkit().getImage(srcImage);
		MediaTracker mediaTracker=new MediaTracker(new Container());
		mediaTracker.addImage(image, 0);
		mediaTracker.waitForID(0);
		
		double thumbRatio=(double)thumbWidth/(double)thumbHeight;
		int imageWidth=image.getWidth(null);
		int imageHeight=image.getHeight(null);
		double imageRatio=(double)imageWidth/(double)imageHeight;
		if(thumbRatio<imageRatio){
			thumbHeight=(int)(thumbWidth/imageRatio);
		}else {
			thumbWidth=(int)(thumbHeight*imageRatio);
		}
		
		BufferedImage thumbImage=new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2d=thumbImage.createGraphics();
		graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2d.drawImage(image, 0, 0, thumbWidth,thumbHeight,null);
		
		BufferedOutputStream out=new BufferedOutputStream(new FileOutputStream(desImage));
		JPEGImageEncoder encoder=JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param=encoder.getDefaultJPEGEncodeParam(thumbImage);
		
		quality=Math.max(0, Math.min(100, quality));
		param.setQuality((float)quality/100.0f, false);
		encoder.setJPEGEncodeParam(param);
		encoder.encode(thumbImage);
		out.close();
		
	}

}
