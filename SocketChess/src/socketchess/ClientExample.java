/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketchess;

import java.awt.*;
import java.net.*;
import java.io.*;

/**
 *
 * @author geerlings
 */
public class ClientExample extends Frame {
    TextArea display=new TextArea("text\n");
    
    public ClientExample()
    {
        setLayout(null);
        setSize(400,400);
        setTitle("Client");
        super.setVisible(true);
        this.addWindowListener(new MyWindow());
        display.setBounds(10,200,380,150);
        add(display);
    }
    
    public void runClient()
    {
            Socket client;
            DataOutputStream output;
            DataInputStream input;
            try
            {
                System.out.println("Client is running");
                //client=new Socket(InetAddress.getLocalHost(),5500);
                //client=new Socket(InetAddress.getByName("demeter.cs.utwente.nl"),5500);
                client=new Socket("192.168.0.13",5500);
                display.append("To: "+client.getInetAddress().getHostName()+"\n");
                input=new DataInputStream(client.getInputStream());
                output=new DataOutputStream(client.getOutputStream());
                display.append("Received: "+input.readUTF()+"\n");
                output.writeUTF("Bye and see you again soon");
                client.close();
            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }
            display.append("bye\n");
    }
    
    public static void main(String[] args)
    {
        System.out.println("First run the server, then the client");
        ClientExample c=new ClientExample();
        c.runClient();
    }
}
