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
                    field[j][7].set_piece(Piece.white_knight);}
                    for(int w=2;w<=5;w+=3){
                    field[w][7].set_piece(Piece.white_bishop);}
                    for(int p=0;p<=7;p+=1){
                    field[p][6].set_piece(Piece.white_pawn);}
                    field[3][7].set_piece(Piece.white_queen);
                    field[4][7].set_piece(Piece.white_king);
                    
                    for(int i=0;i<=7;i+=7){
                    field[i][0].set_piece(Piece.black_rook);}
                    for(int j=1;j<=6;j+=5){
                    field[j][0].set_piece(Piece.black_knight);}
                    for(int w=2;w<=5;w+=3){
                    field[w][0].set_piece(Piece.black_bishop);}
                    for(int p=0;p<=7;p+=1){
                    field[p][1].set_piece(Piece.black_pawn);}
                    field[3][0].set_piece(Piece.black_queen);
                    field[4][0].set_piece(Piece.black_king);
                    }
                else 
                {
                for(int i=0;i<=7;i+=7){
                    field[i][0].set_piece(Piece.white_rook);}
                    for(int j=1;j<=6;j+=5){
                    field[j][0].set_piece(Piece.white_knight);}
                    for(int w=2;w<=5;w+=3){
                    field[w][0].set_piece(Piece.white_bishop);}
                    for(int p=0;p<=7;p+=1){
                    field[p][1].set_piece(Piece.white_pawn);}
                    field[4][0].set_piece(Piece.white_queen);
                    field[3][0].set_piece(Piece.white_king);
                    
                    for(int i=0;i<=7;i+=7){
                    field[i][7].set_piece(Piece.black_rook);}
                    for(int j=1;j<=6;j+=5){
                    field[j][7].set_piece(Piece.black_knight);}
                    for(int w=2;w<=5;w+=3){
                    field[w][7].set_piece(Piece.black_bishop);}
                    for(int p=0;p<=7;p+=1){
                    field[p][6].set_piece(Piece.black_pawn);}
                    field[4][7].set_piece(Piece.black_queen);
                    field[3][7].set_piece(Piece.black_king);
                }
                
                applet.repaint();
	}

        @Override
        public void actionPerformed(ActionEvent event){
            System.out.println("pressed "+event.getSource());
            if(Side.white==playingas){
                //If u are playig whit whites, prechoose one of you white pieces
                if( drop==null &&
                        ((Position)event.getSource()).get_piece()!=Piece.none&&
                        ((Position)event.getSource()).get_piece()!=Piece.black_pawn&&
                        (((Position)event.getSource()).get_piece().side==Side.black && ((Position)event.getSource()).get_piece().type==Piece.Type.rook) &&
                        ((Position)event.getSource()).get_piece()!=Piece.black_knight&&
                        ((Position)event.getSource()).get_piece()!=Piece.black_bishop&&((Position)event.getSource()).get_piece()!=Piece.black_queen&&((Position)event.getSource()).get_piece()!=Piece.black_king){
                    selection=(Position)event.getSource();
                    System.out.println("selection= "+selection);
                }
                //if u have a prechoose piece, choos the site than u want to drop it
                if(selection!=null&&drop==null&&(((Position)event.getSource()).get_piece()==Piece.none||((Position)event.getSource()).get_piece()==Piece.black_pawn||((Position)event.getSource()).get_piece()==Piece.black_rook||((Position)event.getSource()).get_piece()==Piece.black_knight||((Position)event.getSource()).get_piece()==Piece.black_bishop||((Position)event.getSource()).get_piece()==Piece.black_queen||((Position)event.getSource()).get_piece()==Piece.black_king)){
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
            else{
                if(drop==null&&((Position)event.getSource()).get_piece()!=Piece.none&&((Position)event.getSource()).get_piece()!=Piece.white_pawn&&((Position)event.getSource()).get_piece()!=Piece.white_rook&&((Position)event.getSource()).get_piece()!=Piece.white_knight&&((Position)event.getSource()).get_piece()!=Piece.white_bishop&&((Position)event.getSource()).get_piece()!=Piece.white_queen&&((Position)event.getSource()).get_piece()!=Piece.white_king){
                    selection=(Position)event.getSource();
                    System.out.println("selection= "+selection);
                }
                if(selection!=null&&drop==null&&(((Position)event.getSource()).get_piece()==Piece.none||((Position)event.getSource()).get_piece()==Piece.white_pawn||((Position)event.getSource()).get_piece()==Piece.white_rook||((Position)event.getSource()).get_piece()==Piece.white_knight||((Position)event.getSource()).get_piece()==Piece.white_bishop||((Position)event.getSource()).get_piece()==Piece.white_queen||((Position)event.getSource()).get_piece()==Piece.white_king)){
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
