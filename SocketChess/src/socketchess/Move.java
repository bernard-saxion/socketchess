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
                new Position(s.substring(1, 3),Piece.fromUnicode(s.charAt(0))),
                new Position(
                    s.substring(
                        ('x'==s.substring(3,4).charAt(0))?5:4,
                        ('x'==s.substring(3,4).charAt(0))?7:6
                )),
                ('x'==s.substring(3,4).charAt(0))?
                        Piece.fromUnicode(s.substring(4,5).charAt(0)):
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
        
        if(piece.type==Piece.Type.king)
        {
            if(Math.abs(from.column() - to.column())
                    >=2) throw new IllegalMoveException("king can't move that far");
            if(Math.abs(from.row() - to.row())
                    >=2) throw new IllegalMoveException("king can't move that far");
        }
        
        if(piece.type==Piece.Type.pawn)
        {
            if(piece.hasMoved()!=false)
                if(from.column() - to.column()!=0)
                    throw new IllegalMoveException("pawn can't move to the side");
            
            if(piece.hasMoved()==false){
                if(Math.abs(from.row() - to.row())>=3)
                    throw new IllegalMoveException("pawn can't move that far");
            } 
            else{
                System.out.println("this pawn has moved in the past.");
            if(from.row() - to.row()!=-1)
              throw new IllegalMoveException("hasmoved pawn can't move 2 spaces");
            }
        }
            
        
        this.capture=capture;
        if(this.piece.side!=Side.none)
            if(this.piece.side==this.capture.side) throw new IllegalMoveException("Cannot capture own pieces");
    }
    
    public String toString()
    {
        return ""+piece+from+(Piece.none==capture?"-":"x"+capture)+to;
    }
}
