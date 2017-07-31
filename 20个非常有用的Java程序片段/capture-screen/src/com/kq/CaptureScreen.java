package com.kq;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CaptureScreen {

	public static void main(String[] args) throws IOException, AWTException {

		String fileName="src/com/kq/1.png";
		
		captureScreen(fileName);
	}

	public static void captureScreen(String fileName) throws IOException, AWTException{
		
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle sRectangle=new Rectangle(screenSize);
		Robot robot=new Robot();
		BufferedImage image=robot.createScreenCapture(sRectangle);
		ImageIO.write(image, "png", new File(fileName));
	}
}
