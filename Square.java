
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
This class represents a square that uses for a piece to stand on.
A square can be taken for empty.
By applying this, we can use in the game is logic.
 */

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class Square extends Button {
    // xy coordinates.
    private int x;
    private int y;
    private GeneralPieces piece; // piece currently on space

    // use the sheet to make it black or white.
    // constructor
    public Square(boolean light, int x, int y) {
        super();
        this.x = x;
        this.y = y;
        this.piece = null;
        this.getStyleClass().add("chess-space");
        // get dark or light from the sheet.
        if (light)
            this.getStyleClass().add("chess-space-light");
        else
            this.getStyleClass().add("chess-space-dark");
    }

    // get set methods
    public void setX(int inX) {
        this.x = inX;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }


    // get the current piece.
    public GeneralPieces getPiece() {
        return this.piece;
    }

    // Sets the piece and and load image.
    public void setPiece(GeneralPieces piece) {
        this.piece = piece;

        if (this.piece != null)
            this.setGraphic(new ImageView(piece.loadImage()));
        else
            this.setGraphic(new ImageView());
    }

    public String getPieceColor() {
        if (getPiece() != null)
            return getPiece().getPieceColor();
        else
            return ""; // empty
    }


    // true if square is taken
    public boolean isTaken() {
        return (this.piece != null);
    }

    // removes pieces
    public GeneralPieces pieceRemove() {
        GeneralPieces tmpPiece = this.piece;
        setPiece(null);
        return tmpPiece;
    }


}
