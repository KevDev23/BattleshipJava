import java.util.Random;

class ship
{
	board id;//has size in it
	//may need function to set that up
	int xcoordinates[];
	int ycoordinates[];
	int damage;//tracks ship damage unless the size var can be changed in id
	int x,y,end,temp;//temps for first x and y coordinate, end will be for the end of the other end of a ship
	Random rand = new Random();
	int rando = rand.nextInt(3);

	
	board[] enumvals = {board.CARRIER,board.BATTLESHIP,board.SUBMARINE,board.DESTROYER};//for randomly selecting a ship
	
	public void initShip(int shipnum)
	{
		id = enumvals[shipnum];
		xcoordinates = new int[id.getSize()];//this line causes nullpointer error??????????????????????
		ycoordinates = new int[id.getSize()];
		damage = 0;
	}
	
	public void print()
	{
		System.out.println(id.getSize());
		System.out.println(id.getid());
	}
	
	public void location()//sets location
	{
		//long millis = System.currentTimeMillis() % 1000;
		x = rand.nextInt(10);//(int)Math.random(millis) * 10 + 1;
		y = rand.nextInt(10);//(int)Math.random(millis) * 10 + 1;
		end = id.getSize();
		//millis = System.currentTimeMillis() % 1000;
		temp = rand.nextInt(1000);//(int)Math.random() * 1000 + 1;
		
		if(temp >= 500)//if true the the ship will be oriented horizontally else vertically
		{
				if(end + x > 10)//then error
				{
					this.location();
					return;
				}
				else{
						for(int i = 0; i < end; i++)//populates the y values
						{
						 ycoordinates[i] = y + i;
						}
						
						for(int i = 0; i < end; i++)//populates x values
							xcoordinates[i] = x;
					}
				
		}
		
		if(temp <= 499)//if rand is false then the ship will be oriented vertically
		{
				if(end + y > 10)//then the boundries on the y coordinate will be broken
				{
					this.location();
					return;
				}
				else{
						for(int i = 0; i < end; i++)//populates the xcoordinates
						{
								xcoordinates[i] = x + i;
						}
						
							
						for(int i = 0; i < end; i++)//populates the ycoordinates
							ycoordinates[i] = y;
					}
		}
		
		
	}
	
	//checks for a colllision with another ship. Returns true when no collision is found. False if otherwise
	public boolean collisionCheck(ship alpha,ship beta)
	{
		for(int i = 0; i < alpha.xcoordinates.length; i++)
			for(int j = 0; j < beta.xcoordinates.length; j++)
			{
				if(alpha.xcoordinates[i] == beta.xcoordinates[j] && alpha.ycoordinates[i] == beta.ycoordinates[j])
				{	
					System.out.println("******************ERROR: SHIP COLLISION DETECTED******************");
					System.out.println("Alpha:");
					alpha.printCoord();
					System.out.println("Beta:");
					beta.printCoord();
					alpha.location();//then a collision is detected
					beta.location();
					return true;
				}
			}
			
		return false;
	}
	
	public void printCoord()
	{
		for(int i = 0; i < xcoordinates.length; i++)
			System.out.println(xcoordinates[i] + "," + ycoordinates[i]);
	}
	
}