public enum board
{
  CARRIER('c',5), BATTLESHIP('b',4), SUBMARINE('s',3), DESTROYER('d',2), HIT('x',0), BLANK(' ',0);
  
	private char square;
	private int size;
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	//constructor
	private board(char grid,int s)
	{
		square = grid;
		size = s;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public void print()
	{
		System.out.println(this.square);
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------	
	public int getSize()
	{
		return size;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------	
	public char getid()
	{
		return square;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------	
	public void setsquare(char enumval)
	{
		square = enumval;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------	
	public void hit(board grid[][],ship alpha, ship beta, int xcoord, int ycoord)//ship data and coordinate data
	{
		//check for hits on alpha
		for(int i = 0; i < alpha.xcoordinates.length; i++)
		{
			if(xcoord == alpha.xcoordinates[i] && ycoord == alpha.ycoordinates[i])
			{
				alpha.damage++;//the ship is hit so we increment the damage
				grid[xcoord][ycoord] = board.HIT;//set to HIT to show that a ship is there
				
				if(alpha.damage == alpha.id.getSize())//if the ship is sunk
				{
					for(int j = 0; j < alpha.xcoordinates.length; j++)//reveals what ship alpha is.
					{
						grid[alpha.xcoordinates[j]][alpha.ycoordinates[j]].setsquare(alpha.id.getid());
					}
					return;
				}
			}
		}
		//******************************************************************************
		//check for hits on beta
		for(int i = 0; i < beta.xcoordinates.length; i++)
		{
			if(xcoord == beta.xcoordinates[i] && ycoord == beta.ycoordinates[i])
			{
				beta.damage++;
				grid[xcoord][ycoord] = board.HIT;
				
				if(beta.damage == beta.id.getSize())//if the ship is sunk
				{
					for(int j = 0; j < beta.xcoordinates.length; j++)//reveals what ship beta is.
					{
						grid[beta.xcoordinates[j]][beta.ycoordinates[j]].setsquare(beta.id.getid());
					}
				}
				return;
			}
		}
		
	}
}