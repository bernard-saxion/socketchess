package socketchess;

/**
 *
 * @author saxion
 */
public class Move
{
    public final Position from;
    public final Position to;
    public final Piece capture;
    public final Piece piece;
    public final Side side;
    
    public Move(final String desc, final Side side) throws IllegalMoveException
    {
        this(desc);
        if(this.side!=side)throw new IllegalMoveException("The piece at "+from+" is not "+side);
    }
    
    public Move(final String s) throws IllegalMoveException
    {
        this(Piece.fromUnicode(s.charAt(0)),
                new Position(s.substring(1, 2)),
                new Position(
                    s.substring(
                        ('x'==s.substring(3,1).charAt(0)?5:4),
                        2
                )),
                ('x'==s.substring(3,1).charAt(0))?
                        Piece.fromUnicode(s.substring(4,1).charAt(0)):
                        Piece.none
        );
        if(s.charAt(0)!=piece.toString().charAt(0))throw new IllegalMoveException("There is no "+piece.type+" at "+from+",but"+piece.toString());
    }
    
    public Move(final Piece piece, final Position from, final Position to) throws IllegalMoveException
    {this(piece,from,to,Piece.none);}
    
    public Move(final Piece piece, final Position from, final Position to, final Piece capture) throws IllegalMoveException
    {
        if(null==from||to==null) throw new IllegalArgumentException("Positions may not be null");
        if(Piece.none==from.get_piece()) throw new IllegalMoveException("cannot move a Piece.none");
        this.piece=piece;
        this.side=piece.side;
        this.from=from;
        this.to=to;
        
        //movement for the king
        if(piece.type==Piece.Type.king)
        {
            if(Math.abs(from.column() -
             to.column())
                    >1) throw new IllegalMoveException("king can't move that far to the sides");
            if(Math.abs(from.row() -
             to.row())
                    >1) throw new IllegalMoveException("king can't move that far up or down");
        }
        //movement for the rook
        if(piece.type==Piece.Type.rook){
        if(Math.abs(from.column() -
        to.row())
                    >1) throw new IllegalMoveException("rook can't move that far to the sides");
        }
        
        this.capture=capture;
        if(this.piece.side==this.capture.side) throw new IllegalMoveException("Cannot capture own pieces");
    }
    
    public String toString()
    {
        return ""+piece+from+(Piece.none==capture?"-":"x"+capture)+to;
    }
}
