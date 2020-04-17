/*
this class represent the movement of bishop

 */

public class BishopPiece extends GeneralPieces
{
    public BishopPiece(boolean color)
    {
        //this calls the constructor of GeneralPieces
        super(color);
    }

    protected ListOfMovements[] getMovements()
    {
        ListOfMovements[] moves =
            {
                ListOfMovements.UP_TO_THE_RIGHT,
                ListOfMovements.DOWN_TO_THE_RIGHT,
                ListOfMovements.DOWN_LEFT,
                ListOfMovements.UP_LEFT
            };
        return moves;
    }

    protected boolean moveOnlyOne(){return false;}
    protected String getPieceName(){return "bishop";}
}
