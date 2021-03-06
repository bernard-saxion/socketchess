/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketchess;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import static java.awt.Font.PLAIN;

/**
 *
 * @author saxion
 */
public class Position extends Button
{
    private int row;
    private int column;
    private Piece piece=Piece.none;

    public Position(final int column,final int row)
    {
        this((char)(column+('A'-1)),row);
    }

    public Position(final char column, final int row)
    {
        if(!((column>='A'&&column<='H')||(column>='a'&&column<='h')))
            throw new IllegalArgumentException("column must be between 'A' and 'G', was '"+column+'\'');
        if(!(row>=1&&row<=8))
            throw new IllegalArgumentException("row must be between 1 and 8, was '"+row+'\'');
        this.row=row;
        this.column=column-('A'-1); // FIXME: watch out for lowercase
        
        if((row%2==0) ^ (column%2==0))
                setBackground(Color.yellow);
        else
                setBackground(Color.red);
        
        Font auxFont=this.getFont();
        if(null!=auxFont)
            this.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),70));
        else
            this.setFont(new Font(null,PLAIN,65));
    }
    
    public Position(final String pos)
    {
        this(pos.charAt(0),(byte)(pos.charAt(1)-'0'));
    }
    
    public Position(final String pos, final Piece piece)
    {
        this(pos);
        this.piece=piece;
    }
    
    public char column()
    {
        return (char)(column+('A'-1));
    }
    
    public int row()
    {
        return row;
    }
    
    @Override
    public String toString()
    {
        return ""+column()+row();
    }
    
    public Piece get_piece()
    {
    return(piece);
    }
    
    public void set_piece(Piece piece)
    {
        this.piece=piece;
        this.setLabel(""+piece.toString());
    }
}
