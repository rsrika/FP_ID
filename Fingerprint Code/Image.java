import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.util.Arrays;
//
//import javax.imageio.ImageIO;
//
//public class Image {
//	private String fileName;
//    private BufferedImage image;
//    private BufferedImage[][] finalImage;
//	
//	public Image(String fileName) {
//		this.fileName = fileName;
//	}
//	
//	public void loadImage() {
//		try {
//            image = ImageIO.read(new File(System.getProperty("user.home"), fileName));
//        } catch (IOException e) {
//        	e.printStackTrace();
//        }
//
//        finalImage = new BufferedImage[image.getWidth()][image.getHeight()];
//        for (int i = 0; i < image.getWidth(); i++) {
//            for (int j = 0; j < image.getHeight(); j++) {
//                finalImage[i][j] = image.getSubimage(i, j, (1 + i), (1 + j));
//            }
//        }
//	}
//	
//	public String getFinalImage() {
//        return Arrays.toString(finalImage);
//    }
//	
//	public void convertToVector() {
//		
//	}
//}
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;

public class Image
{
	public int[][] bW;
	public Image(String file)
	{
		try
		{
			Color[][] colors = loadPixelsFromImage(file);
			int[][] bWh = makeBW(colors);
			bW = bWh;
		}
		catch(Exception e)
		{
			e.toString();
		}
	}

	//code from 
    public Color[][] loadPixelsFromImage(String file) throws IOException
    {
  
        BufferedImage image = ImageIO.read(new File("./src/input/"+file));
        Color[][] colors = new Color[image.getHeight()][image.getWidth()];

        for (int x = 0; x < image.getWidth(); x++) 
        {
            for (int y = 0; y < image.getHeight(); y++) 
            {
                colors[y][x] = new Color(image.getRGB(x, y));
            }
        }

        return colors;
    }
    public int[][] makeBW(Color[][] pixels)
    {
    	int[][] bW = new int[pixels.length][pixels[0].length];
    	
    	for(int i = 0; i<bW.length;i++)//row
    	{
    		for( int j = 0; j< bW[0].length; j++)//col
    		{
    			if(getAvgColor(pixels[i][j]))
    			{
    				bW[i][j] = 1;
    			}
    			else
    			{
    				bW[i][j] = 0;
    			}
    		}
    	}
    	return bW;
    }
    public boolean getAvgColor(Color color)
    {
    	 int num = 0;
    	 if(color.getRed()>128)
    	 {
    		 num++;
    	 }
    	 if(color.getRed()>128)
    	 {
    		 num++;		 
    	 }
    	 if(color.getRed()>128)
    	 {
    		   num++;  		 
    	 }
    	 return (num>=3);
    }
    public static void main(String[] args)
    {
    	Image img = new Image(args[0]);
    	
    	System.out.println(img.bW.length);//width -> cols
    	System.out.println(img.bW[0].length);//height -> rows
    	
    	for(int i = 0; i<img.bW.length;i++)//row
    	{
    		for( int j = 0; j< img.bW[0].length; j++)//col
    		{
    				System.out.print(img.bW[i][j]);
    		}
    		System.out.println();
    	}
    }
    

}