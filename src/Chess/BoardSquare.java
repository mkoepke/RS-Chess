package Chess;

public class BoardSquare {
    private int x;
    private int y;

    private ChessPiece piece;

    public BoardSquare(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        piece = null;
    }

    public ChessPiece getPiece() {
        return piece;
    }

    public boolean isEmpty() {
        return ( piece == null );
    }

    public void occupy( ChessPiece piece ) {

        // if square is empty add our piece
        if ( this.isEmpty() ) {
            this.piece = piece;
        }
        else {
            // not empty handle conditions
        }
    }

    public void free() {
        this.piece = null;
    }

}
