package socketchess;

import java.applet.Applet;
import java.awt.Image;

/**
 * Represents the different kinds of pieces chess is played with
 * @author saxion
 */
public class Piece {
    /** Identifiers to differentiate between the different types of pieces in chess with their unique behaviour. Also a special <code>Type.none</code>. */
    public enum Type
    {
        none,
        king, queen,
        bishop, knight, tower,
        pawn,
    };
    /** The colour of this piece, or to which side this piece belongs, or which player controls this piece. */
    public final Side side;
    /** The type of piece, what kind of piece it is, how the piece should behave and look. */
    public final Type type;
    /** Single-character symbol alternative to the <code>type</code>.
     * case-sensitive.
     * usually the first letter of the piece's type, except for <code>bishop</code>, who gets <code>i</code>.
     * <code>king</code> and <code>queen</code> have capital-letter symbols.
     * 
     * The <code>none</code> piece has symbol '<code>-</code>'
     */
    public final char symbol;
    
    /** Explicitly not a chess piece. Alternative to <code>null</code>. neither on <code>Side.black</code> nor <code>Side.white</code>, has <code>Type.none</code> and symbol '<code>-</code>' */
    public final static Piece none = new Piece(Side.none,Type.none);

    // <editor-fold defaultstate="collapsed" desc="public final static <black pieces>" >
    /** The black King piece. symbol '<code>K</code>'. */
    public final static Piece black_king = new Piece(Side.black,Type.king);
    /** The black Queen piece. symbol '<code>Q</code>'. */
    public final static Piece black_queen = new Piece(Side.black,Type.queen);
    /** The black bishop piece. symbol '<code>i</code>'. */
    public final static Piece black_bishop = new Piece(Side.black,Type.bishop);
    /** The black knight piece. symbol '<code>k</code>'. */
    public final static Piece black_knight = new Piece(Side.black,Type.knight);
    /** The black tower piece. symbol '<code>t</code>'. */
    public final static Piece black_tower = new Piece(Side.black,Type.tower);
    /** The black pawn piece. symbol '<code>p</code>'. */
    public final static Piece black_pawn = new Piece(Side.black,Type.pawn);
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="public final static <white pieces>" >
    /** The white King piece. symbol '<code>K</code>'. */
    public final static Piece white_king = new Piece(Side.black,Type.king);
    /** The white Queen piece. symbol '<code>Q</code>'. */
    public final static Piece white_queen = new Piece(Side.black,Type.queen);
    /** The white bishop piece. symbol '<code>i</code>'. */
    public final static Piece white_bishop = new Piece(Side.black,Type.bishop);
    /** The white knight piece. symbol '<code>k</code>'. */
    public final static Piece white_knight = new Piece(Side.black,Type.knight);
    /** The white tower piece. symbol '<code>t</code>'. */
    public final static Piece white_tower = new Piece(Side.black,Type.tower);
    /** The white pawn piece. symbol '<code>p</code>'. */
    public final static Piece white_pawn = new Piece(Side.black,Type.pawn);
    // </editor-fold>

    /** Creates any well-formed piece object. <code>symbol</code>s always correspond to the given type. */
    private Piece(final Side side, final Type type)
    {
        if(Side.none==side^Type.none==type) throw new IllegalArgumentException("Cannot mix NONE values with other values");
        this.side=side;
        this.type=type;
        switch(type)
        {
            case none: this.symbol='-'; break;
            case king: this.symbol='K'; break;
            case queen: this.symbol='Q'; break;
            case bishop: this.symbol='i'; break;
            default:
                this.symbol=type.name().charAt(0);break;
        }
    }
    
    /**
     * Provides a nice image of what this piece looks like.
     * Images are up to 60 pixels long in either direction.
     * Image is mostly transparent with a single-colour icon centered.
     * @param a The Applet where to link the image from.
     * @return An Image object, or <code>null</code> for <code>Piece.none</code>.
     */
    public Image getImage(final Applet a)
    {
        if(null==a) throw new IllegalArgumentException("Applet was null");
        // TODO: maybe provide an empty/transparent png instead of null?
        if(none==this)return null;
        else return a.getImage(a.getDocumentBase(), ""+side+"_"+type+".png");
    }
}
