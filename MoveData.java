
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
this method will keep track the xy
and feed the xy for other methods
 */


import java.io.Serializable;

public class MoveData implements Serializable {
    // variables
    final static int LOW_BOUND = 27;
    final static int HIGH_BOUND = 64;

    int preX;
    int preY;
    int newX;
    int newY;


    // create constructor
    public MoveData(int inOldX, int inOldY, int inNewX, int inNewY) {
        this.preX = inOldX;
        this.preY = inOldY;
        this.newX = inNewX;
        this.newY = inNewY;
    }


    // Getter method
    public int getPreX() {
        return this.preX;
    }

    public int getPreY() {
        return this.preY;
    }

    public int getNewX() {
        return this.newX;
    }

    public int getNewY() {
        return this.newY;
    }



    public String toString() {
        return (getCharLabel(preX + 1) + (preY + 1) + " to " + getCharLabel(newX + 1) + (newY + 1));
    }

    // Converts x position to label
    private String getCharLabel(int i) {
        return i > 0 && i < LOW_BOUND ? String.valueOf((char) (i + HIGH_BOUND)) : null;
    }

    public int getXDifference() {
        return this.newX - this.preX;
    }

    public int getGYDifference() {
        return this.newY - this.preY;
    }
}
