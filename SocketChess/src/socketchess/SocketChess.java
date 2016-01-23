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
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;

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
    
    private final Label notice = new Label("");
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
        
        add(notice);
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        try(final Socket socket=new Socket(addressf.getText(),Short.parseShort(portf.getText())))
        {
            if(ae.getSource()==asclient)
            {
                board=new Board(Side.white, new Opponent(Side.black, socket));
                board.init(this);
            }
            if(ae.getSource()==asserver)
            {
                board=new Board(Side.black, new Opponent(Side.white, socket));
                board.init(this);
            }
        }
        catch(final NumberFormatException nfe)
        {
            notice.setText("Port must be a number (0-65535), choose the same number that the server chose");
        }
        catch(final UnknownHostException uhe)
        {
            notice.setText("Don't know how to reach \""+addressf.getText()+"\"");
        }
        catch(final IOException ioe)
        {
            notice.setText("\""+addressf.getText()+"\" broke off the connection");
        }
    }
}
