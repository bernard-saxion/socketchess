/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketchess;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author espinosa baeza & bernard
 */
public class SocketChess extends Applet implements ActionListener
{
    private Board board = null;
    private final Button asclient = new Button("Play as white (client)");
    private final Button asserver = new Button("Play as black (server)");
    
    private final Label addressl = new Label("Server address:");
    private final Label portl = new Label("Server port:");
    
    private final TextField addressf = new TextField();
    private final TextField portf = new TextField();
    
    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    @Override
    public void init()
    {
        setBackground(Color.lightGray);
        
        add(addressl);
        addressf.setText("000.000.000.000");
        add(addressf);
        add(portl);
        portf.setText("5500");
        add(portf);
        
        asclient.addActionListener(this);
        add(asclient);
        asserver.addActionListener(this);
        add(asserver);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==asclient)
        {
            board=new Board(Side.white);
            board.init(this);
        }
        if(ae.getSource()==asserver)
        {
            board=new Board(Side.black);
            board.init(this);
        }
    }/*
    @Override
     public void paint(Graphics g)
     {
         if(null!=board)board.paint(g, this);
     }
     */
             
}
