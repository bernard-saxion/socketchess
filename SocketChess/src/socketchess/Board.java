/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketchess;

import java.applet.*;
import java.awt.event.*;
import java.io.IOException;


/**
 *
 * @author saxion
 */
public class Board implements ActionListener
{
        Position selection=null,drop=null;
	Position field[][]=new Position[8][8];
        
        private boolean myturn;
        
	final static int butsize=80;
        final Side playingas;
        final Opponent opponent;

        public Board(final Side playas, final Opponent opponent)
        {
            this.playingas=playas;
            this.opponent=opponent;
        }

	public void init(final Applet applet)
	{
                applet.removeAll();
		applet.setLayout(null);
                
		for(int i=7;i>=0;i--)
		{
			for(int j=7;j>=0;j--)
			{
                            if(Side.black==playingas)
                            {
                                field[i][j]=new Position(-(i+1)+9,j+1);
                            }
                            else
                            {
				field[i][j]=new Position(i+1,-(j+1)+9);
                            }
                                field[i][j].get_piece().getImage(applet);
				field[i][j].setBounds(i*butsize,j*butsize,butsize,butsize);
                                field[i][j].addActionListener(this);
                                if(field[i][j].get_piece()==Piece.none){
                                    field[i][j].setLabel("");
                                }
                                else
                                {
                                String s=""+field[i][j].get_piece().toString();
                                field[i][j].setLabel(s);
                                }
				applet.add(field[i][j]);
				System.out.println("button at "+(i*butsize) + ", "+ (j*butsize));
                                
			}
		}
                if(Side.white==playingas){
                    for(int i=0;i<=7;i+=7){
                    field[i][7].set_piece(new Piece(Side.white, Piece.Type.rook));}
                    for(int j=1;j<=6;j+=5){
                    field[j][7].set_piece(new Piece(Side.white, Piece.Type.knight));}
                    for(int w=2;w<=5;w+=3){
                    field[w][7].set_piece(new Piece(Side.white, Piece.Type.bishop));}
                    for(int p=0;p<=7;p+=1){
                    field[p][6].set_piece(new Piece(Side.white, Piece.Type.pawn));}
                    field[3][7].set_piece(new Piece(Side.white, Piece.Type.queen));
                    field[4][7].set_piece(new Piece(Side.white, Piece.Type.king));
                    
                    for(int i=0;i<=7;i+=7){
                    field[i][0].set_piece(new Piece(Side.black, Piece.Type.rook));}
                    for(int j=1;j<=6;j+=5){
                    field[j][0].set_piece(new Piece(Side.black, Piece.Type.knight));}
                    for(int w=2;w<=5;w+=3){
                    field[w][0].set_piece(new Piece(Side.black, Piece.Type.bishop));}
                    for(int p=0;p<=7;p+=1){
                    field[p][1].set_piece(new Piece(Side.black, Piece.Type.pawn));}
                    field[3][0].set_piece(new Piece(Side.black, Piece.Type.queen));
                    field[4][0].set_piece(new Piece(Side.black, Piece.Type.king));
                    }
                else 
                {
                for(int i=0;i<=7;i+=7){
                    field[i][0].set_piece(new Piece(Side.white, Piece.Type.rook));}
                    for(int j=1;j<=6;j+=5){
                    field[j][0].set_piece(new Piece(Side.white, Piece.Type.knight));}
                    for(int w=2;w<=5;w+=3){
                    field[w][0].set_piece(new Piece(Side.white, Piece.Type.bishop));}
                    for(int p=0;p<=7;p+=1){
                    field[p][1].set_piece(new Piece(Side.white, Piece.Type.pawn));}
                    field[3][0].set_piece(new Piece(Side.white, Piece.Type.queen));
                    field[4][0].set_piece(new Piece(Side.white, Piece.Type.king));
                    
                    for(int i=0;i<=7;i+=7){
                    field[i][7].set_piece(new Piece(Side.black, Piece.Type.rook));}
                    for(int j=1;j<=6;j+=5){
                    field[j][7].set_piece(new Piece(Side.black, Piece.Type.knight));}
                    for(int w=2;w<=5;w+=3){
                    field[w][7].set_piece(new Piece(Side.black, Piece.Type.bishop));}
                    for(int y=0;y<=7;y+=1){
                    field[y][6].set_piece(new Piece(Side.black, Piece.Type.pawn));}
                    field[4][7].set_piece(new Piece(Side.black, Piece.Type.queen));
                    field[3][7].set_piece(new Piece(Side.black, Piece.Type.king));
                }
                	}
        

        @Override
        public void actionPerformed(ActionEvent event){
            System.out.println("pressed "+event.getSource());
            Piece pressedpiece=((Position)event.getSource()).get_piece();
            
           //if u are whites
            if(Side.white==playingas){
                //If u are playig whit whites, prechoose one of you white pieces
                if( drop==null &&
                        pressedpiece!=Piece.none&&
                        (pressedpiece.side==Side.white) && 
                        (pressedpiece.type==Piece.Type.pawn||
                        pressedpiece.type==Piece.Type.rook||
                        pressedpiece.type==Piece.Type.knight||
                        pressedpiece.type==Piece.Type.bishop||
                        pressedpiece.type==Piece.Type.queen||
                        pressedpiece.type==Piece.Type.king)){
                    
                            selection=(Position)event.getSource();
                            System.out.println("selection= "+selection);
                }
                //if u have a prechoose piece, choos the site than u want to drop it
                if(selection!=null&&
                        drop==null&&
                        (pressedpiece==Piece.none||
                        (pressedpiece.side==Side.black) && 
                        (pressedpiece.type==Piece.Type.pawn||
                        pressedpiece.type==Piece.Type.rook||
                        pressedpiece.type==Piece.Type.knight||
                        pressedpiece.type==Piece.Type.bishop||
                        pressedpiece.type==Piece.Type.queen||
                        pressedpiece.type==Piece.Type.king))){
                    
                            drop=(Position)event.getSource();
                            System.out.println("drop= "+drop);
                    try
                    {
                    domove(new Move(selection.get_piece(), selection, drop,drop.get_piece()));
                    drop=null;
                    selection=null;
                    }
                    catch(IllegalMoveException ime)
                    { 
                        System.out.println("illegal move: "+ime.getMessage());
                    drop=null;
                    selection=null;
                }
                }
            }
            //if u are blaks
            else{
                if(drop==null &&
                        pressedpiece!=Piece.none&&
                        (pressedpiece.side==Side.black) && 
                        (pressedpiece.type==Piece.Type.pawn||
                        pressedpiece.type==Piece.Type.rook||
                        pressedpiece.type==Piece.Type.knight||
                        pressedpiece.type==Piece.Type.bishop||
                        pressedpiece.type==Piece.Type.queen||
                        pressedpiece.type==Piece.Type.king)){
                    
                            selection=(Position)event.getSource();
                            System.out.println("selection= "+selection);
                }
                if(selection!=null&&
                        drop==null&&
                        (pressedpiece==Piece.none||
                        (pressedpiece.side==Side.white) && 
                        (pressedpiece.type==Piece.Type.pawn||
                        pressedpiece.type==Piece.Type.rook||
                        pressedpiece.type==Piece.Type.knight||
                        pressedpiece.type==Piece.Type.bishop||
                        pressedpiece.type==Piece.Type.queen||
                        pressedpiece.type==Piece.Type.king))){
                    
                            drop=(Position)event.getSource();
                            System.out.println("drop= "+drop);
                    try
                    {
                    domove(new Move(selection.get_piece(), selection, drop, drop.get_piece()));
                    drop=null;
                    selection=null;
                    }
                    catch(IllegalMoveException ime)
                    { 
                        System.out.println("illegal move: "+ime.getMessage());
                    drop=null;
                    selection=null;
                    }
                }
        }
        
        }
        
        public void domove(final Move move) throws IllegalMoveException
        {
            if(null==move)throw new IllegalArgumentException("move was null");
            Position from=position(move.from);
            Position to=position(move.to);
            if(from.get_piece()!=move.piece)throw new IllegalMoveException("No "+move.piece+" on "+from);
            if(to.get_piece()!=move.capture)throw new IllegalMoveException(to.toString()+" has "+to.get_piece()+" not "+move.capture);
               
            
            System.out.println("moving piece");
            to.set_piece(move.piece);
            from.set_piece(Piece.none);
            }
        
        private Position position(final Position columnrow)
        {
            if(null==columnrow)throw new IllegalArgumentException("columnrow was null");
            return position(columnrow.column(),columnrow.row());
        }
        
        private Position position(final char column,final int row)
        {
            final int i,j;
            if(Side.black==playingas)
            {
             i=row-1;
             j=-(column-'A')+7;
            }
            else
            {
                i=(-row+9)-1;
                j=(column-'A');
            }
            return field[j][i];
        }
}
