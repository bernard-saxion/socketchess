/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketchess;

import java.awt.*;
import java.applet.*;


/**
 *
 * @author saxion
 */
public class Board extends Applet
{
	Button field[][]=new Button[8][8];

	static int butsize=80;

	public void init()
	{
		setLayout(null);
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
				add(field[i][j]);
				System.out.println("button at "+(i*butsize) + ", "+ (j*butsize));
			}
		}
	}

	public static void main(String[] args)
	{
		Frame f=new Frame();
		Board listner=new Board();
		f.setSize(8*butsize,8*butsize);
		f.add(listner);
		listner.init();
		f.addWindowListener(new MyWindow());
		f.setVisible(true);
	}
}
