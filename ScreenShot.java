

import javax.imageio.ImageIO;
import java.io.File;
import java.util.Calendar;
import java.awt.*;
import java.net.URL;
import javax.swing.*;
import java.awt.image.*;

class ScreenShot {
	
	
	
	private Dimension screenSize;
	private String imageFormat;
	private Robot robot;
	private Rectangle screenRectangle;
	private Boolean color;
	private Boolean resize;
	private int timeInterval;
	private String directory;
	private double height;
	private double width;
	private double per;
	

	public ScreenShot (String args[])
	{
			
			//get the scree size 
			screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			dimensionSetHeight();
			dimensionSetWidth();
		
			//sets the percentage value to crop from each side of the screen
			per=.1;
		
			//sets the resize flag
			resize=false;
		
			//create a rect angle of screensize
			screenRectangle = new Rectangle(getPoint(),getDimension());
			
			
			try {
			//this class is used to hadnle user operations
			robot = new Robot();
			}
			
			catch (Exception ex)
			{
				System.out.println("Failed to creat robot");
				
			}
			
			//process out the time inteval in minute from the command line argument
			//then multiply it to convert in milisecond
			
			//uncomment the line after succesful execution
			//timeInterval=Integer.parseInt(args[0])*60000;
			//in minute
			timeInterval = 10000;
			
			//process out the putput directory from the command line argument
			//uncomment the line after succesful execution
			//directory = args[1];
			directory = "E:\\";
			
			////process out the image coloring opton from the command line argument
			//uncomment the line after succesful execution
			/*
			if(Integer.parseInt(args[2])==0)
			{
				//nope , user does not want a color image
				color=false;
			}
			
			//yup!! user wants a color image
			else color =true;
			*/
			color =true ;
			
			
			//process out the image type from the command line argument
			//uncomment the line after succesful execution
			//imageFormat=args[3];
			imageFormat = "png";
			
			coreProcess ();			
			
			


	}//end of constructor
	
	
	private Point getPoint() {
		
		int y=(int) (height*per);
		int x=(int) (width * per);
		
		System.out.println(new Point(x,y));
		
		return new Point(x,y);
		
	}//private Point getPoint()
	
	private Dimension getDimension () {
		
		int y=( int ) height -2*(int) (height*per);
		int x=(int ) width -2*(int) (width * per);
		
		System.out.println(new Dimension (x,y));
		return new Dimension (x,y);
		
	}//private getDimension () 
	
	
	
	private void dimensionSetHeight() {
	
		height= screenSize.getHeight();
		
		
	}//public void getHeight()
	
	
	private void dimensionSetWidth() {
	
		width= screenSize.getWidth();
		
		
	}//public void getWidth()	
	
	private BufferedImage takeScreenShot (){
		

				BufferedImage image = robot.createScreenCapture(screenRectangle);
				
				return image;
		
		
	}//private BufferedImage takeScreenShot
	
	
	//change the color setting according to user need
	private BufferedImage colorSetting(BufferedImage image) {
		
		
		if(color)
		{
			//yes user wants a color image
			//return the image as it is
			return image;
		}
		
		//nope user wants a b/w image
		//so make it b/w and return 
		else 
		{
			
			return makeItBW(image);
		}
	}//private BufferedImage colorSetting() 
	
	
	//make the image black and white
	private BufferedImage makeItBW(BufferedImage image) {
		
		
		double image_width = image.getWidth();
		double image_height = image.getHeight();

		//bimg will store the black and white image
		BufferedImage bwimg = null;
		

		//drawing a new image      
		bwimg = new BufferedImage( (int)image_width ,  (int)image_height,
										BufferedImage.TYPE_BYTE_GRAY);
		Graphics2D bwimgGraphics2D = bwimg.createGraphics();
		bwimgGraphics2D.drawImage(image, 0 , 0 , image.getWidth(null) , 
											image.getHeight(null), null);
		
		return bwimg;

	}//private BufferedImage makeItBW() 	
	
	
	//resize the image
	private BufferedImage resizeImage(BufferedImage image) {
		
		BufferedImage resizedImage = new BufferedImage(300, 200, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(image, 0, 0, 300, 200, null);
					
		return resizedImage;
	}//private resize() 
	
	private void createDirectory (BufferedImage image) {
		
				CalendarProcess cal = new CalendarProcess();
				new File(directory + "\\" +cal.getDate()).mkdir();
				writeInFile(image);
		
	}//private void createDirectory ()
	
	
	//write the image object in the file
	private void writeInFile(BufferedImage image) {
		
		CalendarProcess cal ;
		
		
		try {
			
				cal = new CalendarProcess();
				String  time = cal.getTime();
				String date = cal.getDate();
			
				ImageIO.write(image, imageFormat , new File(directory + date+ "\\"+time + "."+imageFormat));
		}//end of try 
		
		catch (Exception ex) {
			try{
				createDirectory(image);
			}
			catch (Exception exep)
			{
				System.out.println();
			}
			
		}//catch (Exception ex)
		
		
	}//private void writeInFile() 
	
	private void coreProcess () {
		
		while (true)
		{
			BufferedImage screenShot;
			screenShot = takeScreenShot ();
			
			//modified image
			BufferedImage modifiedImage = colorSetting(screenShot);
			try {
				if(resize)
				{
					BufferedImage image = resizeImage(modifiedImage);
					writeInFile (image);
				}
				else writeInFile (modifiedImage);
			}
			
			catch (Exception ex)
			{}
			
			
			try {
			Thread.sleep(timeInterval);
			}
			
			catch (Exception ex)
			{
				System.out.println("Failed to sleep");
				
			}
			
		}//end of while
		
	}//private void coreProcess 
	
	
}//end of class
 
