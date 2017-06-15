package Chess;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Holder;


@WebService()
public class Chess {

  // our game board
  private Board board;

  // let's track whose tuen it is
  private int currentPlayer = 1;

  public Chess() {

    this.board = new Board();
  }

  @WebMethod
  public String reset() {

    this.currentPlayer = 1;

    // set the board back up
    this.board.setupBoard();

    String result = "Resetting the game board.   Player 1 (White) is up.";
    System.out.println( result );
    return result;
  }

  @WebMethod
  public String quit() {
    String result = "Game has now ended";
    System.out.println( result )  ;
    return result;
  }


  @WebMethod
  public String getChessBoard() {

    return this.board.dumpBoard();
  }

  @WebMethod
  public String makeMove( int player, String startSquare, String endSquare ) {

	String result;
	// check for valid player
	if ( player != 1 && player != 2 ) {
		result = "You specified an invalid player.";
		System.out.println( result )  ;
		return result;
	}
	else if ( player != this.currentPlayer ) {
		result = "It's not your turn.  You just moved.";
		System.out.println( result )  ;
		return result;
	}
	
	this.board.movePiece( startSquare, endSquare );
	
	// next player's turn
	if ( ++this.currentPlayer > 2 ) {
		this.currentPlayer = 1;
	}

	result = "Piece successfully moved.";
	System.out.println( result )  ;
	return result;
  }

  public static void main(String[] argv) {
    Object implementor = new Chess ();
    String address = "http://localhost:9000/RSChess";
    Endpoint.publish(address, implementor);
  }
}
