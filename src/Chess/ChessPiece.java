package Chess;


public class ChessPiece {
    protected boolean hasMoved = false;
    protected String color;
    protected int row;
    protected int column;
    protected char notation;

    public ChessPiece( String color, int row, int column ) {

        this.color = color;
    }

    public String identity( ) {

        // we will be designating piece a lowercase color then piece notation.   i.e.  WQ is a white Queen
        String me = this.color.equals( "white" ) ? "W" : "B";

        me += this.notation;

        return me;
    }

    int movePiece( String startSquare, String endSquare ) {

        return 1;
    }

}
