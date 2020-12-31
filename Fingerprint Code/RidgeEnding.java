public class RidgeEnding 
// code by Roshni
{
	// stores location of the start of each ridge ending 
	public int xPixel;
	public int yPixel;
	public RidgeEnding(int x, int y)
	{
		xPixel = x;
		yPixel = y;
	}
	public String toString()
	{
		return ("Location (x,y) = ("+xPixel+ ","+yPixel+")");
	}
	
}
