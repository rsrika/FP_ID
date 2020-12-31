
public class Fingerprint 
{
	public int[][] fingerPrint;
	public int black = 0;
	public int white = 1;
	public GenericLinkedList<RidgeEnding> ridgeEndings;
	public GenericLinkedList<Bifurs> bifurcations;
	public boolean isFP = true;
	int[] xCoor;
	int[] yCoor;
	// 0 is black and 1 is white 
	public Fingerprint(String file)
	{
		Image im = new Image(file);
		int[][] blackWhite = im.bW;
		ridgeEndings = new GenericLinkedList();
		bifurcations = new GenericLinkedList();
		fingerPrint = blackWhite;
		
	}
	
	

	public void findBifurs()
	{
		// excluding ones at the edges of the image
		for(int i = 1; i< fingerPrint.length; i++)
		{
			for(int j = 1; j< fingerPrint[0].length;j++)
			{
				if(isMinutiae(i,j, black,fingerPrint[i][j]))
				{
					bifurcations.add(new Bifurs(j,i));
				}
			}
		}
	}
	

	public void findREs()
	{
		// excluding ones at the edges of the image
		for(int i = 1; i< fingerPrint.length; i++)//cols = y
		{
			for(int j = 1; j< fingerPrint[0].length;j++)//rows = x
			{
				if(isMinutiae(i,j, white,fingerPrint[i][j]))
				{
					ridgeEndings.add(new RidgeEnding(j,i));
				}
			}
		}
	}


	// color should equal black to check for bifurs and should equal white to check for REs
	// if a black pixel is surrounded by at least 6 white pixels, it is a RE
	// if a white pixel is surrounded by at least 5 black pixels, it is a Bifur
	public boolean isMinutiae(int x, int y, int color, int colorCurrentPixel)
	{
		
		int numOfColor = 0; 
		if(colorCurrentPixel != color)
		{
			if((x>0 && x<fingerPrint.length-1) && (y>0 && y<fingerPrint[0].length-1))
			{
				if(fingerPrint[x-1][y-1] == color)
				{
					numOfColor++;
				}
				if(fingerPrint[x][y-1] == color)
				{
					numOfColor++;
				}
				if(fingerPrint[x+1][y-1] == color)
				{
					numOfColor++;
				}
				if(fingerPrint[x-1][y] == color)
				{
					numOfColor++;
				}
				if(fingerPrint[x+1][y] == color)
				{
					numOfColor++;
				}
				if(fingerPrint[x-1][y+1] == color)
				{
					numOfColor++;
				}
				if(fingerPrint[x][y+1] == color)
				{
					numOfColor++;
				}
				if(fingerPrint[x+1][y+1] == color)
				{
					numOfColor++;
				}
			}
			if(color == black && numOfColor>=5)
			{
				return true;
			}
			if(color == white && numOfColor >=6)
			{
				return true;
			}
			
		}
		return false;
	}
	
	
	public void createPolygon(GenericLinkedList<Bifurs> bifurs, GenericLinkedList<RidgeEnding> res)
	{
		int[] xsOfPoly = new int[bifurs.size()+res.size()];
		int[] ysOfPoly = new int[bifurs.size()+res.size()];
		
		for(int i = 0; i< bifurs.size();i++)
		{
			xsOfPoly[i] = bifurs.get(i).xPixel;
			ysOfPoly[i] = bifurs.get(i).yPixel;
		}
		for(int i = 0; i< res.size();i++)
		{
			xsOfPoly[i+bifurs.size()] = res.get(i).xPixel;
			ysOfPoly[i+bifurs.size()] = res.get(i).yPixel;
		}
		xCoor = xsOfPoly;
		yCoor = ysOfPoly;
	}
	public static void main(String[] args)
	{
		Fingerprint fp = new Fingerprint(args[0]);
		fp.findBifurs();
		fp.findREs();
		fp.createPolygon(fp.bifurcations,fp.ridgeEndings);
		for(int i = 0; i<fp.xCoor.length;i++)
		{
			System.out.print("("+fp.xCoor[i]+",");
			System.out.print(fp.yCoor[i]+")");
			System.out.println();
		}
		
	}
	

}
