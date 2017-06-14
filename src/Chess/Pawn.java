package Chess;


public class Pawn extends ChessPiece {
    public Pawn( String color, int row, int column ) {
        super( color, row, column );

        this.notation = 'P';
    }
}
