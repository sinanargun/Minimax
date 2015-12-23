public class Board
{
	public final byte NOBODY = 0;
	public final byte PLAYER = 1;
	public final byte ARTIFICIAL = 2;

	/*
	* 	Width and height of the board..
	*/
	int height,width;


	/*
	* How many stones have to be connected in order to win.
	*/

	int lentghOfConnect; // for our implementation default=4;


	byte board[][];


	/*
	* Keeps track of the number of each column.This is used for
	* efficiency reasons to be able to quickly determine if player
	* can put his into a column.
	*/
	int[] columnCount;


	public Board( int height, int width, int lentghOfConnect )
    {
    	this.height = height;
    	this.width = width;
    	this.lentghOfConnect = lentghOfConnect;

    	this.board = new byte[width][height];
    	this.columnCount = new int[width];
    }

    public boolean isValidMove( int column )
    {
    	return columnCount[column] < height;
    }

    public boolean makeMovePlayer( int column )
    {
    	return makeMove( column , true );
    }

    public boolean makeMoveArtificial( int column )
    {
    	return makeMove( column , false );
    }

    public boolean undoMovePlayer( int column )
    {
    	return undoMove( column , true );
    }

    public boolean undoMoveArtificial( int column )
    {
    	return undoMove( column , false );
    }

    boolean makeMove( int column , boolean player )
    {
    	if ( columnCount[ column ] < height )
    	{
    		byte sign = player ? PLAYER : ARTIFICIAL;
    		columnCount[ column ]++;
    		board[ column ][ columnCount[ count ]] = sign;
    		retun true;
    	}
    	return false;
    }
    boolean undoMove( int column ,  boolean player )
    {
    	byte sign = player ? PLAYER : ARTIFICIAL;
    	if ( board[ column ][ columnCount[ column ] -1 ] == sign )
    	{
    		board[ column ][ columnCount[ column ] -1 ] = NOBODY;
    		columnCount[ column ]--;
    		return true;
    	}
    	return false;
    }

    public int getWidth()
    {
    	return width;
    }
    @Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		for (int x = 0; x < width; x++) {
			result.append((x + 1) + " ");
		}
		result.append(System.lineSeparator());
		for (int y = height - 1; y >= 0; y--) {
			for (int x = 0; x < width; x++) {
				if (board[x][y] == PLAYER) {
					result.append("X ");
				} else if (board[x][y] == AI) {
					result.append("O ");
				} else {
					result.append(". ");
				}
			}
			result.append(System.lineSeparator());
		}
		return result.toString();
	}

	public boolean hasWinner()
	{
		return getWinner() != NOBODY;
	}

	public byte getWinner() 
	 {
		for (int x = 0; x < width; x++) 
		{
			for (int y = 0; y <= height - lentghOfConnect; y++) 
			{
				boolean playerWin = true;
				boolean aiWin = true;
				for (int z = 0; z < lentghOfConnect; z++) {
					if ( board[x][y + z] != PLAYER ) 
					{
						playerWin = false;
					}
					if ( board[x][y + z] != ARTIFICIAL ) 
					{
						aiWin = false;
					}
				}
				
				if ( playerWin ) 
				{
					return PLAYER;
				} 
				else if ( aiWin ) 
				{
					return ARTIFICIAL;
				}
			}
		}

		for ( x = 0 ; x < height ; x++ )
			for( y = 0 ; y <= width - lentghOfConnect; y++ )
			{
				boolean playerWin = true;
				boolean aiWin = true;
				for ( z = 0; z < lentghOfConnect; z++ )
				{
					if ( board[ x ][ y + z ] != PLAYER )
					{
						playerWin = false;
					}
					if ( board[ x ][ y + z ] != ARTIFICIAL )
					{
						aiWin = false;
					} 
				}
			}

		       if ( playerWin ) 
				{
					return PLAYER;
				} 
				else if ( aiWin ) 
				{
					return ARTIFICIAL;
				}
		for ( x = width -1 ; x >= lentghOfConnect - 1; x++ )
			for( y = 0 ; y <= height - lentghOfConnect; y++ )
			{
				boolean playerWin = true;
				boolean aiWin = true;
				for ( z = 0; z < lentghOfConnect; z++ )
				{
					if ( board[ x + z ][ y - z ] != PLAYER )
					{
						playerWin = false;
					}
					if ( board[ x + z ][ y - z ] != ARTIFICIAL )
					{
						aiWin = false;
					} 
				}
			}

		       if ( playerWin ) 
				{
					return PLAYER;
				} 
				else if ( aiWin ) 
				{
					return ARTIFICIAL;
				}
		
		for ( x = 0; x <= width - lentghOfConnect; x++) 
		{
			for ( y = 0; y <= height - lentghOfConnect; y++) 
			{
				boolean playerWin = true;
				boolean aiWin = true;

				for ( z = 0; z < lentghOfConnect; ++) 
				{
					if ( board[x + z][y + z] != PLAYER)
					{
						playerWin = false;
					}
					if ( board[x + o][y + o] != ARTIFICIAL )
					{
						aiWin = false;
					}
				}
				if (playerWin)
				{
					return PLAYER;
				}
				else if (aiWin)
				{
					return AI;
				}
			}
		}

	return NOBODY;
       
    }

	public boolean playerIsWinner() {
		return getWinner() == PLAYER;
	}

	/**
	 * Returns if the board is full and there is
	 * no winner.
	 * 
	 */
	public boolean isTie() {
		return isBoardFull() && getWinner() == NOBODY;
	}

	boolean isBoardFull()
	 {
		boolean emptyColumnFound = false;
		for (int x = 0; x < width; x++) {
			if (columnCounts[x] < height) 
			{
				emptyColumnFound = true;
				break;
			}
		}
		return !emptyColumnFound;
	}

}