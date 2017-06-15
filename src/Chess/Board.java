package Chess;


public class Board {

    private BoardSquare squares[][] = new BoardSquare[9][9];     // all chess boards are 8 x 8.  Ordering is 8 to 1 rows and a to h columns
    // however we are ignoring row and column 0 for simpler math

	private String statusMessage = "";
	
	
    public Board() {
		this.setupBoard();
    }

	public String getLastStatusMessage() {
		return this.statusMessage;
	}
	
    public boolean setupBoard() {

        // set the board to empty
        for ( int i = 1; i <= 8; i++ ) {
            for ( int j = 1; j <= 8; j++ ) {
                squares[i][j] = new BoardSquare( i, j );
            }
        }

        boolean setupGood;

        setupGood = this.setupSide( 1, 7, 8 );
        if ( setupGood ) {
            setupGood = this.setupSide( 2, 2, 1 );
        }

        return setupGood;
    }


    private boolean setupSide( int player, int pawnRow, int royaltyRow ) {

        // check for valid rows
        if ( pawnRow < 1 || pawnRow > 8 || royaltyRow < 1 || royaltyRow > 8 )
            return false;


        // white always goes first, so....
        String color = "white";
        if ( player == 2 )
            color = "black";

        // set the 8 pawns
        for ( int i = 1; i <= 8; i++ ) {
            squares[pawnRow][i].occupy( new Pawn( color, pawnRow, i ) );
        }


        // set Rooks
        squares[royaltyRow][1].occupy( new Rook( color, royaltyRow, 1 ) );
        squares[royaltyRow][8].occupy( new Rook( color, royaltyRow, 8 ) );

        // set Knights
        squares[royaltyRow][2].occupy( new Knight( color, royaltyRow, 2 ) );
        squares[royaltyRow][7].occupy( new Knight( color, royaltyRow, 7 ) );

        // set Bishops
        squares[royaltyRow][3].occupy( new Bishop( color, royaltyRow, 3 ) );
        squares[royaltyRow][6].occupy( new Bishop( color, royaltyRow, 6 ) );

        // set Queen and King
        squares[royaltyRow][4].occupy( new Queen( color, royaltyRow, 4 ) );
        squares[royaltyRow][5].occupy( new King( color, royaltyRow, 5 ) );

        return true;
    }

    public String dumpBoard() {

        StringBuilder layout = new StringBuilder();

        // iterate through board and output pieces and empty spots
		
		// label grid top row
		layout.append( " " );   // top left corner is empty
		for ( int i = 1; i <= 8; i++ ) {
			layout.append( "\t" ).append( (char)('`' + i) );
		}	
		layout.append("\n");
		
		
        for ( int i = 1; i <= 8; i++ ) {
			layout.append((char)('9' - i) );    // board counts down from 8 to 1
            for ( int j = 1; j <= 8; j++ ) {
                layout.append( "\t" );
                if ( squares[i][j].isEmpty() ) {
					layout.append( "Free" );
				}
				else {
					// if not null then a piece is in it.  Get it tell us what it is
					layout.append( squares[i][j].getPiece().identity() );
				}	
            }
            layout.append( "\n" );
        }
        return layout.toString();
    }
	
	public boolean movePiece( String startSquare, String endSquare ) {

			
		if ( startSquare == null || endSquare == null || startSquare.isEmpty() || endSquare.isEmpty() ) {
			this.statusMessage = "You didn't specify a starting or ending square";
			return false;
		}
			
		int startingRow = startSquare.charAt(0);
		int startingCol = startSquare.charAt(1);
		int endingRow = endSquare.charAt(0);
		int endingCol = endSquare.charAt(1);
		
		// do range checks here
		if ( startingRow < 'a' || startingRow > 'h' || endingRow < 'a' || endingRow > 'h' 
			|| startingCol < 1 || startingCol > 8 || endingCol < 1 || endingCol > 8 ) {
			this.statusMessage = "You specified an invalid starting or ending square.";
			return false;
		}			
		
		// let's make row numeric
		startingRow =- '`';
        if ( squares[startingRow][startingCol].isEmpty() ) {
            this.statusMessage = "There is no piece to move at " + startSquare;
            return false;
        }
        else {
            ChessPiece piece = squares[startingRow][startingCol].getPiece();
            boolean success = piece.movePieceTo( endingRow, endingRow );
            if ( success ) {
                // let's vacate where the piece was
                squares[startingRow][startingCol].free();
                // and put the piece where it can end
                squares[startingRow][startingCol].occupy( piece );

                this.statusMessage = piece.identity() + "successfully moved.";
                return true;
            }
            else {
                this.statusMessage = "You cannot move " + piece.identity() + " to " + endSquare;
                return false;
            }
        }

        // yes we could rearrange for a single exit point but it also makes for very nested code too that is hard to read
	}
	 
}
