package Chess;


public class ChessPiece {
	protected char notation;
    protected boolean hasMoved = false;
    protected String color;
    protected int row;
    protected int column;

    public ChessPiece( String color, int row, int column ) {

        this.color = color;
    }

    public String identity() {

        // we will be designating piece a lowercase color then piece notation.   i.e.  WQ is a white Queen
        String me = this.color.equals( "white" ) ? "W" : "B";

        me += this.notation;

        return me;
    }

    public boolean movePieceTo( int row, int col ) {

        return true;
    }

}
