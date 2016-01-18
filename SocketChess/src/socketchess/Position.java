/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketchess;

/**
 *
 * @author saxion
 */
public class Position {
    private byte row;
    private char column;
    public Position(final byte column,final byte row)
    {
        this((char)(column+'A'),row);
    }
    
    public Position(final char column, final byte row)
    {
        if(!((column>='A'&&column<='G')||(column>='a'&&column<='g')))
            throw new IllegalArgumentException("column must be between 'A' and 'G', was '"+column+'\'');
        if(!(row>=1&&row<=8))
            throw new IllegalArgumentException("row must be between 1 and 8, was '"+row+'\'');
        this.row=row;
        this.column=column;
    }
    
    public Position(final String pos)
    {
        this(pos.charAt(0),(byte)(pos.charAt(1)-'0'));
    }
}
