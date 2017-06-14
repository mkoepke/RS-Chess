package Chess;


public class Board {

    private Object squares[][] = new Object[9][9];     // all chess boards are 8 x 8.  Ordering is 8 to 1 rows and a to h columns
    // however we are ignoring row and column 0 for simplier math a

    public Board() {
    }


    public boolean setupBoard() {

        // set the board to empty
        for ( int i = 1; i <= 8; i++ ) {
            for ( int j = 1; j <= 8; j++ ) {
                squares[i][j] = null;
            }
        }

        boolean setupGood;

        setupGood = this.setupSide( 1, 7, 8 );
        if ( setupGood ) {
            setupGood = this.setupSide(2, 2, 1);
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
            squares[pawnRow][i] = new Pawn( color, pawnRow, i );
        }


        // set Rooks
        squares[royaltyRow][1] = new Rook( color, royaltyRow, 1 );
        squares[royaltyRow][8] = new Rook( color, royaltyRow, 8 );

        // set Knights
        squares[royaltyRow][2] = new Knight( color, royaltyRow, 2 );
        squares[royaltyRow][7] = new Knight( color, royaltyRow, 7 );

        // set Bishops
        squares[royaltyRow][3] = new Bishop( color, royaltyRow, 3 );
        squares[royaltyRow][6] = new Bishop( color, royaltyRow, 6 );

        // set Queen and King
        squares[royaltyRow][4] = new Queen( color, royaltyRow, 4 );
        squares[royaltyRow][5] = new King( color, royaltyRow, 5 );

        return true;
    }

    String dumpBoard() {

        String layout = String();


        // set the board to empty
        for ( int i = 1; i <= 8; i++ ) {
            for ( int j = 1; j <= 8; j++ ) {
                squares[i][j] = null;
            }
        }
        return "";
    }
}
