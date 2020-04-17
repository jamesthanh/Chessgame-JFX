
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
this class represent the movement of the most powerful piece. - Queen
Queen can go in all direction
 */


public class QueenPiece extends GeneralPieces {
    public QueenPiece(boolean color) {
        //this calls the constructor of GeneralPieces
        super(color);
    }

    protected ListOfMovements[] getMovements() {
        ListOfMovements[] moves =
                {
                        ListOfMovements.UP,
                        ListOfMovements.UP_TO_THE_RIGHT,
                        ListOfMovements.RIGHT,
                        ListOfMovements.DOWN_TO_THE_RIGHT,
                        ListOfMovements.DOWN,
                        ListOfMovements.DOWN_LEFT,
                        ListOfMovements.LEFT,
                        ListOfMovements.UP_LEFT
                };
        return moves;
    }

    // return method
    protected boolean moveOnlyOne() {
        return false;
    }

    protected String getPieceName() {
        return "queen";
    }
}
