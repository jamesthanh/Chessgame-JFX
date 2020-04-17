public class RookPiece extends GeneralPieces
{
    public RookPiece(boolean color)
    {
        //this calls the constructor of GeneralPieces
        super(color);
    }

    protected ListOfMovements[] getMovements()
    {
        ListOfMovements[] moves =
            {
                ListOfMovements.UP,
                ListOfMovements.RIGHT,
                ListOfMovements.DOWN,
                ListOfMovements.LEFT
            };
        return moves;
    }

    protected boolean moveOnlyOne(){return false;}
    protected String getPieceName(){return "rook";}
}
