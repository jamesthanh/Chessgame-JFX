
/*
Team Tei x Ren
Le Minh Truyen  - s3627577
Hoang Quang Huy - s3623383
Nguyen Tan Thanh s3634815
OOP - Project - the Chess Game
2017C
 */

/*
Resource:
- All of app icons and piece images can be found on google. They are standard chess images,
- SheetStyle can be found at - http://www.renderx.com/chess.html
- How to build a chess board - https://stackoverflow.com/questions/24082063/chessboard-with-automatic-resizing
- Attempting to try network services - Based on "JavaFX Software: Chat (Server-Client)" - https://www.youtube.com/watch?v=VVUuo9VO2II
- Knight Movements - https://codereview.stackexchange.com/questions/105748/knight-moves-in-chess-game
Chess rule - https://www.chess.com/learn-how-to-play-chess
 */


/*
This class loads the image for the pieces and defines a color or a piece.
true is white and false is black.
 */


import javafx.scene.image.Image;

public abstract class GeneralPieces {
    protected boolean moveYet;
    protected Image image;
    protected boolean color;

    // validMoves

    public GeneralPieces(boolean inColor) {
        this.color = inColor;

        //for pawn double move
        moveYet = false;

        String location = "resources/pieces/";
        String filename = this.getPieceColor() + "_" + this.getPieceName() + ".png";
        this.image = new Image(location + filename);


    }


    public void setMoveYet(boolean isTrue) {
        this.moveYet = isTrue;
    }

    // image of chess piece
    public Image loadImage() {
        return this.image;
    }


    public String toString() {
        return (this.getPieceName() + " " + this.getPieceColor());
    }

    // create a sub class for movement list.
    protected abstract ListOfMovements[] getMovements();

    // create a sub class for single move.
    protected abstract boolean moveOnlyOne();

    // create a sub class for get name
    protected abstract String getPieceName();


    // Get piece color
    public String getPieceColor() {
        if (this.color == true)
            return "white";
        else
            return "black";
    }

}
