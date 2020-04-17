
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
this class is to create a board table for the chess with the dimension of 8x8.
it also apply the starting positions for the pieces
also in the class it will apply the all the logical that follow the chess 's rules.

 */


import javafx.scene.layout.GridPane;

public class Board extends GridPane {
    private boolean whiteTurn;
    public Square[][] square = new Square[8][8];


    //clicked square
    public Square clickedSquare = null;

    public Board(boolean whitePlayer) {
        //call super
        super();

        whiteTurn = true;

        // 8x8 array size
        for (int x = 0; x < square[0].length; x++) {
            for (int y = 0; y < square[1].length; y++) {

                int rowInt = 7 - y;
                int colInt = 7 - x;

                boolean whiteSquare = ((x + y) % 2 != 0);
                square[x][y] = new Square(whiteSquare, x, y);


                // make two players facing each other.
                if (whitePlayer) {
                    this.add(square[x][y], x, rowInt);
                } else {
                    this.add(square[x][y], colInt, y);
                }

                // make the values to be final so they can be put in the event handler.
                final int xFinal = x;
                final int yFinal = y;
                // Act when a square is pressed.
                square[x][y].setOnAction(e -> activeClickSquare(xFinal, yFinal));
            }
        }

        //put every pieces in starting positions
        this.startPos();
    }


    //define the starting piece positions
    public void startPos() {        //  put all white pieces in to correct order.
        // they will be at the bottom
        this.square[0][0].setPiece(new RookPiece(true));
        this.square[1][0].setPiece(new KnightPiece(true));
        this.square[2][0].setPiece(new BishopPiece(true));
        this.square[3][0].setPiece(new QueenPiece(true));
        this.square[4][0].setPiece(new KingPiece(true));
        this.square[5][0].setPiece(new BishopPiece(true));
        this.square[6][0].setPiece(new KnightPiece(true));
        this.square[7][0].setPiece(new RookPiece(true));

        // then set the white pawn positions
        for (int i = 0; i < this.square[0].length; i++)
            this.square[i][1].setPiece(new PawnPiece(true));

        //  put all black pieces in to correct order.
        // the will be at the top
        this.square[0][7].setPiece(new RookPiece(false));
        this.square[1][7].setPiece(new KnightPiece(false));
        this.square[2][7].setPiece(new BishopPiece(false));
        this.square[3][7].setPiece(new QueenPiece(false));
        this.square[4][7].setPiece(new KingPiece(false));
        this.square[5][7].setPiece(new BishopPiece(false));
        this.square[6][7].setPiece(new KnightPiece(false));
        this.square[7][7].setPiece(new RookPiece(false));

        // then set the white pawn positions
        for (int i = 0; i < this.square[0].length; i++)
            this.square[i][6].setPiece(new PawnPiece(false));
    }

    public void activeClickSquare(int x, int y) {
        Square activatedSquare = square[x][y];
        // if piece is selected && user didn't click on allied piece
        if (clickedSquare != null &&
                clickedSquare.getPiece() != null &&
                (activatedSquare.getPieceColor() != clickedSquare.getPieceColor() || (clickedSquare.getPiece().getPieceName().equals("king") && activatedSquare.getPiece().getPieceName().equals("rook")))) {
            MoveData info;
            info = new MoveData(clickedSquare.getX(), clickedSquare.getY(), x, y);

            boolean singlePlayerMode = (LauncherGUI.testNet == null);

            // update the game board
            if (this.moveComputing(info) && !singlePlayerMode) {
                // send move to other player
                if (this.switchMoves(info)) {
                    // lock the board
                    this.setDisable(true);
                }
            }

            //separate the square
            this.setClickedSquare(null);

        } else {
            //if there'mySquare a piece on the selected square when no active square
            if (square[x][y].getPiece() != null) {
                if (whiteTurn) {
                    if (square[x][y].getPiece().getPieceColor().equals("white")) {
                        this.setClickedSquare(square[x][y]);
                    }
                } else {
                    if (square[x][y].getPiece().getPieceColor().equals("black")) {
                        this.setClickedSquare(square[x][y]);
                    }
                }
            }
        }
    }


    public void setClickedSquare(Square mySquare) {
        // Remove style from old active square
        if (this.clickedSquare != null)
            this.clickedSquare.getStyleClass().removeAll("chess-space-active");

        this.clickedSquare = mySquare;

        // Add new square
        if (this.clickedSquare != null)
            this.clickedSquare.getStyleClass().add("chess-space-active");
    }


    // Process a move after it has been made by a player - for castled
    protected boolean moveComputing(MoveData feedInData) {
        if (validatingMoves(feedInData)) {
            Square preMoves = square[feedInData.getPreX()][feedInData.getPreY()];
            Square curMoves = square[feedInData.getNewX()][feedInData.getNewY()];
            // check king and rook
            if (preMoves.getPiece().getPieceName().equals("king") && curMoves.getPiece().getPieceName().equals("rook")) {
                // check same color
                if (curMoves.getPiece().getPieceColor().equals("white") && preMoves.getPiece().getPieceColor().equals("white")) {
                    if (feedInData.getNewX() == 7) {
                        curMoves = square[6][0];
                        curMoves.setPiece(preMoves.pieceRemove());
                        preMoves = square[7][0];
                        curMoves = square[5][0];
                        curMoves.setPiece(preMoves.pieceRemove());
                    } else {
                        curMoves = square[2][0];
                        curMoves.setPiece(preMoves.pieceRemove());
                        preMoves = square[0][0];
                        curMoves = square[3][0];
                        curMoves.setPiece(preMoves.pieceRemove());
                    }
                }
                // same for black
                // check for same color
                else if (curMoves.getPiece().getPieceColor().equals("black") && preMoves.getPiece().getPieceColor().equals("black")) {
                    if (feedInData.getNewX() == 7) {
                        curMoves = square[6][7];
                        curMoves.setPiece(preMoves.pieceRemove());
                        preMoves = square[7][7];
                        curMoves = square[5][7];
                        curMoves.setPiece(preMoves.pieceRemove());
                    } else {
                        curMoves = square[2][7];
                        curMoves.setPiece(preMoves.pieceRemove());
                        preMoves = square[0][7];
                        curMoves = square[3][7];
                        curMoves.setPiece(preMoves.pieceRemove());
                    }
                }
            } else {
                curMoves.setPiece(preMoves.pieceRemove());

                if (curMoves.getPiece().getPieceName().equals("pawn")) {
                    if (curMoves.getPiece().getPieceColor().equals("white") && feedInData.getNewY() == 7) {
                        curMoves.setPiece(new QueenPiece(true));
                    } else if (curMoves.getPiece().getPieceColor().equals("black") && feedInData.getNewY() == 0) {
                        curMoves.setPiece(new QueenPiece(false));
                    }
                }
            }
            whiteTurn = !whiteTurn;
            return true;
        } else // invalid move
        {
            return false;
        }
    }


    public boolean validatingMoves(MoveData feedInData) {
        // checking the square
        Square preSquare;
        Square curSquare;
        GeneralPieces piece;
        ListOfMovements[] movements;

        // Pieces that move more than move of base.
        int moveCount;
        int xValDifference;
        int yValDifference;


        // Check for no movements
        if (feedInData == null) {
            return false;
        }

        // Check if preSquare in range
        try {
            preSquare = square[feedInData.getPreX()][feedInData.getPreY()];
        } catch (NullPointerException e) {
            return false;
        }

        // Check if curSquare in range
        try {
            curSquare = square[feedInData.getNewX()][feedInData.getNewY()];
        } catch (NullPointerException e) {
            return false;
        }

        // Check if previous Square  has no piece
        if (!preSquare.isTaken()) {
            return false;
        }

        boolean isCastled = false;
        // check if they are king and rook
        if (preSquare.getPiece().getPieceName().equals("king") && curSquare.getPiece().getPieceName().equals("rook")) {
            // check if they have not moved
            if (!preSquare.getPiece().moveYet && !curSquare.getPiece().moveYet) {
                // check if they are same color.
                if (curSquare.getPiece().getPieceColor().equals("white") && preSquare.getPiece().getPieceColor().equals("white")) {
                    if (feedInData.getNewX() == 7) {
                        if (square[5][0].getPiece() == null && square[6][0].getPiece() == null) {
                            isCastled = true;
                        } else {
                            isCastled = false;
                        }
                    } else {
                        if (square[1][0].getPiece() == null && square[2][0].getPiece() == null && square[3][0].getPiece() == null) {
                            isCastled = true;
                        } else {
                            isCastled = false;
                        }
                    }
                }
                // same but for black.
                else if (curSquare.getPiece().getPieceColor().equals("black") && preSquare.getPiece().getPieceColor().equals("black")) {
                    if (feedInData.getNewX() == 7) {
                        if (square[5][7].getPiece() == null && square[6][7].getPiece() == null) {
                            isCastled = true;
                        } else {
                            isCastled = false;
                        }
                    } else {
                        if (square[1][7].getPiece() == null && square[2][7].getPiece() == null && square[3][7].getPiece() == null) {
                            isCastled = true;
                        } else {
                            isCastled = false;
                        }
                    }
                }

            } else {
                isCastled = false;
            }
        }


        // Check piece'mySquare move list
        piece = preSquare.getPiece();
        movements = piece.getMovements();
        boolean matchesPieceMoves = false;


        //labels to break out later
        MoveLoop:
        for (ListOfMovements m : movements) {//use a basic move multiple time if it is a multiple move
            moveCount = 1;
            if (piece.moveOnlyOne() == false) {
                moveCount = 8;
            }

            // check if they collided
            boolean clashedYet = false;

            for (int constant = 1; constant <= moveCount; constant++) {
                // cant move if there is a blocking one
                if (clashedYet) {
                    break;
                }

                //multiply a base move to know if it fits the move made
                xValDifference = m.getX() * constant;
                yValDifference = m.getY() * constant;

                Square holderSquare;

                // check if there is no move left.
                try {
                    holderSquare = square[feedInData.getPreX() + xValDifference][feedInData.getPreY() + yValDifference];
                } catch (Exception e) {
                    break;
                }

                // collision and capturing
                if (holderSquare.isTaken()) {
                    clashedYet = true;
                    boolean piecesSameColor = holderSquare.getPiece().getPieceColor() == preSquare.getPiece().getPieceColor();
                    //stops  if pieces are the same color
                    if (piecesSameColor) {
                        break;
                    }
                }

                // get differences in X or Y
                if (feedInData.getXDifference() == xValDifference && feedInData.getGYDifference() == yValDifference) {
                    matchesPieceMoves = true;

                    if (checkPawn(feedInData) == false) {
                        return false;
                    }

                    piece.setMoveYet(true);
                    //breaks both loops
                    break MoveLoop;
                }
            }
        }

        // change to true.
        if (isCastled) {
            return true;
        } else if (!matchesPieceMoves) {
            return false;
        }


        return true;
    }

    // Send move in both modes
    public boolean switchMoves(MoveData p) {
        try {
            LauncherGUI.testNet.send(p);
        } catch (Exception e) {
            System.err.println("Failed");
            return false;
        }

        return true;
    }

    protected boolean checkPawn(MoveData movements) {

        Square preSquare = square[movements.getPreX()][movements.getPreY()];
        Square curSquare = square[movements.getNewX()][movements.getNewY()];
        GeneralPieces piece = preSquare.getPiece();


        //check if the piece is a pawn
        if (!piece.getPieceName().equals("pawn")) {
            return true;
        }

        // normal move
        if (movements.getXDifference() == 0) {
            // white is positive, black is negative.
            int colorIndex = movements.getGYDifference() / Math.abs(movements.getGYDifference());

            // normal move, don't allow move
            for (int constant = 1; constant <= Math.abs(movements.getGYDifference()); constant++) {
                if (square[movements.getPreX()][movements.getPreY() + (constant * colorIndex)].isTaken()) {
                    return false;
                }
            }
        } else
        //elimination move
        {
            // any opposing piece, don't allow move
            if ((!curSquare.isTaken()) || piece.getPieceColor() == curSquare.getPiece().getPieceColor()) {
                return false;
            }
        }

        return true;
    }
}
