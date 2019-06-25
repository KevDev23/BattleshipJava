import java.util.Scanner;
import java.util.Random;

/*
Author: Kevin Hinson
Description: A version of battle ship
*/

class battleship{
	
	public static void main(String args[]){
		
		//vars & objects
		int difficulty;//difficulty will also track how many round are left
		int temp;
		int seed;//will be a seed for random number generation
		int xtarget,ytarget;//will be user input for coordinates
		boolean condition = true;
		board[][] grid = new board[10][10];
		Scanner reader = new Scanner(System.in);
		ship alpha = new ship();//this will have data of the first ship
		ship beta = new ship();//this will have data of the second ship
		//performs methods on objects
		ship collision = new ship();//for collision checking
		board game = board.BLANK;//will be used for checking for hits. Initialized here
		
		instructions();//print instructions
		difficulty = difficultySelect();//setting difficulty
		System.out.println();
		
		
		//init board
		for(int i = 0; i < 10; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				grid[i][j] = board.BLANK;
			}
		}
		//picking ships
		Random rand = new Random();
	    int rando = rand.nextInt(3);
		
		alpha.initShip(rando);//first ship has been picked
		temp = rando;
		
		while(rando == temp)//prevents the same ship from being picked twice
		{
		rando = rand.nextInt(3);
		beta.initShip(rando);
		}
		
		//picking ship locations
		alpha.location();
		beta.location();
		
		while(condition)//until we get a none colliding set up...
		{
		 condition = collision.collisionCheck(alpha,beta);
		}
		
		/*Testing
			System.out.println("ALPHA'S COORDINATES");
			alpha.printCoord();
			System.out.println("BETA'S COORDINATES");
			beta.printCoord();
		*/
	
		while(difficulty > 0)//the game is run here
		{
			//printing game board
			System.out.print(" ");//for formatting the board

				for(int i = 1; i <= 10; i++)
					System.out.print(i + " ");
				
			
				for(int i = 0; i < 10; i++)
				{
					System.out.print("\n" + (i+1));
					
					for(int j = 0; j < 10; j++)
					{
						System.out.print(grid[i][j].getid() + " ");
					}
				}
				
			System.out.print("SHOTS REMAINING >" + difficulty);
			System.out.print("\nEnter X coordinate > ");
			xtarget = reader.nextInt();
			System.out.print("\nEnter Y coordinate > ");
			ytarget = reader.nextInt();
			
				
			//if xtarget and ytarget are between 1 and 10	
			if(!(xtarget > 11 || xtarget <= 0) && !(ytarget > 11 || ytarget <= 0))
			{
				xtarget--;//setting up for 0-9 range since arrays start at 0
				ytarget--;
				game.hit(grid,alpha,beta,xtarget,ytarget);//checking for hits
				
			difficulty--;
			}
		}
	}
	
	
//------------------M E T H O D S------------------------------------------------------------------------------------------------
	public static void instructions()
	{
		System.out.println("*********************");
		System.out.println("Welcome to Battleship");
		System.out.println("*********************");
		System.out.println("The game will randomly pick 2 ships from Carrier(c), Battleship(b),Submarine(s) or Destroyer(d) for you to sink.But you have a finite resources to do it.");
		System.out.println("Enter coordinates starting with the left column and then the top column to select a space to shoot.");
		System.out.println("x mark hits. Coordinates range from 1-10");
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------
	public static int difficultySelect()
	{
		Scanner userin = new Scanner(System.in);
		char select;
		int shots = 0;//This is returned
		System.out.println("Please select a difficulty, Easy(E)-30 shots, Normal(N)- 25 shots, and Hard(H)-20 shots");
		
		while(shots == 0)
		{
			select = userin.next().charAt(0);
			
			switch(select)
			{				
				case 'E':
				case 'e':
					System.out.println("Easy mode selected");
					shots = 30;
				break;
				
				case 'N':
				case 'n':
					System.out.println("Normal mode selected");
					shots = 25;
				break;
				
				case 'H':
				case 'h':
					System.out.println("Hard mode selected");
					shots = 20;
				break;
				
				default:
					System.out.println(shots + " Is not a valid imput, please enter E,N, or H");
				break;
			}
		}
		
		return shots;
	}

}