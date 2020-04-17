
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
pawn is a special piece. At the first of the game. they can move 1 or 2 steps ahead. when the capture an enemy.
they will go either right or left diagonally.
 */

import java.util.ArrayList;

public class PawnPiece extends GeneralPieces {
    public PawnPiece(boolean color) {
        //this calls the constructor of GeneralPieces
        super(color);
    }

    protected ListOfMovements[] getMovements() {

        boolean isWhite = this.color;

        ListOfMovements[] moves = {};

        // white pawn goes up.
        if (isWhite) {
            ArrayList<ListOfMovements> whiteMoves = new ArrayList<ListOfMovements>();

            //standard move
            whiteMoves.add(ListOfMovements.UP);

            //capture move
            whiteMoves.add(ListOfMovements.UP_TO_THE_RIGHT);
            whiteMoves.add(ListOfMovements.UP_LEFT);

            //first move can be 1 or 2 up
            if (!moveYet) {
                whiteMoves.add(ListOfMovements.DOUBLE_UP);
            }

            moves = whiteMoves.toArray(moves);
        }
        // black pawn moves down
        else {
            ArrayList<ListOfMovements> blackMoves = new ArrayList<ListOfMovements>();

            //standard move
            blackMoves.add(ListOfMovements.DOWN);

            //capture move
            blackMoves.add(ListOfMovements.DOWN_TO_THE_RIGHT);
            blackMoves.add(ListOfMovements.DOWN_LEFT);

            //first move can be 1 or 2 down
            if (!moveYet) {
                blackMoves.add(ListOfMovements.DOUBLE_DOWN);
            }

            moves = blackMoves.toArray(moves);
        }

        return moves;
    }

    // return
    protected boolean moveOnlyOne() {
        return true;
    }

    protected String getPieceName() {
        return "pawn";
    }
}
