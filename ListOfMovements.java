
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
this class gives the x and y movements and directions of moves that are for the pieces.
 */

// using enum to enable the series of predefined movements and directions

public enum ListOfMovements {
    UP(0, 1),
    UP_TO_THE_RIGHT(1, 1),
    RIGHT(1, 0),
    DOWN_TO_THE_RIGHT(1, -1),
    DOWN(0, -1),
    DOWN_LEFT(-1, -1),
    LEFT(-1, 0),
    UP_LEFT(-1, 1),

    KNIGHT_LEFT_UP(-2, 1),
    KNIGHT_UP_LEFT(-1, 2),
    KNIGHT_UP_RIGHT(1, 2),
    KNIGHT_RIGHT_UP(2, 1),

    KNIGHT_RIGHT_DOWN(2, -1),
    KNIGHT_DOWN_RIGHT(1, -2),
    KNIGHT_DOWN_LEFT(-1, -2),
    KNIGHT_LEFT_DOWN(-2, -1),

    DOUBLE_UP(0, 2),
    DOUBLE_DOWN(0, -2);

    // final xy
    private int x;
    private int y;

    // getter methods.
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    // create the class constructor.
    private ListOfMovements(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
