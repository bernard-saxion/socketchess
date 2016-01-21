/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketchess;

import java.awt.*;
import java.applet.*;
import java.awt.event.*;


/**
 *
 * @author saxion
 */
public class Board implements ActionListener
{
	Button field[][]=new Button[8][8];
        Image white_pawn;
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
		for(int i=7;i>=0;i--)
		{
			for(int j=7;j>=0;j--)
			{
				field[i][j]=new Button();
				field[i][j].setBounds(i*butsize,j*butsize,butsize,butsize);
				if((i%2==0) ^ (j%2==0))
					field[i][j].setBackground(Color.white);
				else
					field[i][j].setBackground(Color.black);
				applet.add(field[i][j]);
				System.out.println("button at "+(i*butsize) + ", "+ (j*butsize));
			}
		}
	}

        @Override
        public void actionPerformed(ActionEvent event){
        }
}

// put  png inside the board and actionlistener for butons