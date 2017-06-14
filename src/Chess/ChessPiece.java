package Chess;


public class ChessPiece {
    protected boolean hasMoved = false;
    protected String color;
    protected int row;
    protected int column;

    public ChessPiece( String color, int row, int column ) {
        this.color = color;
    }


    int movePiece( String startSquare, String endSquare ) {
        return 1;
    }

}
