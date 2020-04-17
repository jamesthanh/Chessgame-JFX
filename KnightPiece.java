
/*
Team Tei x Ren
Le Minh Truyen  - s3627577
Hoang Quang Huy - s3623383
Nguyen Tan Thanh s3634815
OOP - Project - the Chess Game
2017C
 */

/*
This class represent the movements of knight
Since the knight has unique movement.

 */

public class KnightPiece extends GeneralPieces {
    public KnightPiece(boolean color) {
        //this calls the constructor of GeneralPieces
        super(color);
    }

    protected ListOfMovements[] getMovements() {
        ListOfMovements[] moves =
                {
                        ListOfMovements.KNIGHT_LEFT_UP,
                        ListOfMovements.KNIGHT_UP_LEFT,
                        ListOfMovements.KNIGHT_UP_RIGHT,
                        ListOfMovements.KNIGHT_RIGHT_UP,
                        ListOfMovements.KNIGHT_RIGHT_DOWN,
                        ListOfMovements.KNIGHT_DOWN_RIGHT,
                        ListOfMovements.KNIGHT_DOWN_LEFT,
                        ListOfMovements.KNIGHT_LEFT_DOWN
                };
        return moves;
    }

    // if it moves one then its true.
    protected boolean moveOnlyOne() {
        return true;
    }

    protected String getPieceName() {
        return "knight";
    }
}
