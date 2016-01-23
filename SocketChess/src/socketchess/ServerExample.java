package socketchess;
import java.awt.*;  
import java.net.*;  //sockets 
import java.io.*;   //DataIOStreams 
public class ServerExample extends Frame 
{TextArea display=new TextArea("tekst\n"); 

   public ServerExample()  
    {setLayout(null);  
     setSize(400,200); 
     setTitle("Server"); 
     super.setVisible(true); 
     this.addWindowListener(new MyWindow()); 
     display.setBounds(10,25,380,150); 
     add(display); 
    }  

   public void runServer() 
    {ServerSocket server; 
     Socket sock; 
     DataOutputStream output; 
     DataInputStream input; 
     try 
       {System.out.println("Server is running"); 
        server=new ServerSocket(5500,100); //create server 
        while (true) 
          {sock=server.accept(); //wait for connection 
           display.append("From: "+sock.getInetAddress().getHostName()+"\n"); 
           input=new DataInputStream(sock.getInputStream()); 
           output=new DataOutputStream(sock.getOutputStream()); 
           output.writeUTF("Hello there"); 
           display.append("Received: "+input.readUTF()+"\n"); 
           sock.close(); 
          } 
       } 
     catch (IOException ex) 
       {ex.printStackTrace(); 
       }  
     display.append("bye\n"); 
    } 

  class MyWindow extends java.awt.event.WindowAdapter 
     {public void windowClosing(java.awt.event.WindowEvent event) 
         {System.out.println("Server shut down"); 
          setVisible(false);  
          dispose();  
          System.exit(0);  
         } 
     } 

  public static void main(String[] args) 
  {System.out.println("een server programma"); 
   ServerExample s=new ServerExample(); 
   s.runServer(); 
  } 
}
