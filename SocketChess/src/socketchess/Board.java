/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketchess;

import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import javax.swing.ImageIcon;


/**
 *
 * @author saxion
 */
public class Board implements ActionListener
{
	Position field[][]=new Position[8][8];
        /*Image white_pawn;
        Image white_knight;
        Image white_bishop;
        Image white_tower;
        Image white_queen;
        Image white_king;
        Image black_pawn;
        Image black_knight;
        Image black_bishop;
        Image black_tower;
        Image black_queen;
        Image black_king;
*/
	final static int butsize=80;
        final Side playingas;

        public Board(final Side playas)
        {
            this.playingas=playas;
        }
        
	public void init(final Applet applet)
	{
                applet.removeAll();
		applet.setLayout(null);
                /*
                white_pawn=applet.getImage(applet.getDocumentBase(),"white_pawn.png");
                white_knight=applet.getImage(applet.getDocumentBase(),"white_knight.png");
                white_bishop=applet.getImage(applet.getDocumentBase(),"white_bishop.png");
                white_tower=applet.getImage(applet.getDocumentBase(),"white_tower.png");
                white_queen=applet.getImage(applet.getDocumentBase(),"white_queen.png");
                white_king=applet.getImage(applet.getDocumentBase(),"white_king.png");
                black_pawn=applet.getImage(applet.getDocumentBase(),"black_pawn.png");
                black_knight=applet.getImage(applet.getDocumentBase(),"black_knight.png");
                black_bishop=applet.getImage(applet.getDocumentBase(),"black_bishop.png");
                black_tower=applet.getImage(applet.getDocumentBase(),"black_tower.png");
                black_queen=applet.getImage(applet.getDocumentBase(),"black_queen.png");
                black_king=applet.getImage(applet.getDocumentBase(),"black_king.png");
*/
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
                if(Side.black==playingas){
                    for(int i=0;i<=7;i+=7){
                    field[i][7].set_piece(Piece.white_rook);}
                    for(int j=1;j<=6;j+=5){
                    field[j][7].set_piece(Piece.white_knight);}
                    for(int w=2;w<=5;w+=3){
                    field[w][7].set_piece(Piece.white_bishop);}
                    for(int p=0;p<=7;p+=1){
                    field[p][6].set_piece(Piece.white_pawn);}
                    field[3][7].set_piece(Piece.white_queen);
                    field[4][7].set_piece(Piece.white_king);
                    }
                
                applet.repaint();
	}

        @Override
        public void actionPerformed(ActionEvent event){
            System.out.println("pressed "+event.getSource());
        }
        
        public void domove(final Move move) throws IllegalMoveException
        {
            if(null==move)throw new IllegalArgumentException("move was null");
            Position from=position(move.from);
            Position to=position(move.to);
            if(from.get_piece()!=move.piece)throw new IllegalMoveException("No "+move.piece+" on "+from);
            if(to.get_piece()!=move.capture)throw new IllegalMoveException(to.toString()+" has "+to.get_piece()+" not "+move.capture);
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
                i=(-row+9)-1;
                j=column-1;
            }
            else
            {
                i=row-1;
                j=(-column+9)-1;
            }
            return field[i][j];
        }
        
        /*
        public void paint(Graphics g,Applet applet){
        g.drawImage(Piece.white_tower.getImage(applet),40,40,80,80,applet);
        System.out.println("piece "+Piece.white_tower);
        }
*/
}

// put  png inside the board and actionlistener for butons