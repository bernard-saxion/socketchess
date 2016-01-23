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
        if(s.charAt(0)!=piece.toString().charAt(0))throw new IllegalMoveException("There is no "+piece.type+" at "+from);
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
        
        this.capture=capture;
        if(this.piece.side==this.capture.side) throw new IllegalMoveException("Cannot capture own pieces");
    }
    
    public String toString()
    {
        return ""+piece+from+(Piece.none==capture?"-":"x"+capture)+to;
    }
}