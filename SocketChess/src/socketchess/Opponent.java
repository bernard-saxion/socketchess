package socketchess;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author saxion
 */
public class Opponent implements Player
{    
    final Side side;
    
    final DataInputStream i;
    final DataOutputStream o;
    
    public Opponent(final Side side, final Socket socket) throws IOException
    {
        this.side=side;
        this.i=new DataInputStream(socket.getInputStream());
        this.o=new DataOutputStream(socket.getOutputStream());
        o.writeUTF("Hello " + side.name());
        System.out.println(i.readUTF());
    }

    public Opponent(Side side, ServerSocket server) throws IOException
    {
        this.side=side;
        final Socket socket=server.accept();
        this.i=new DataInputStream(socket.getInputStream());
        this.o=new DataOutputStream(socket.getOutputStream());
        o.writeUTF("Hello " + side.name());
        System.out.println(i.readUTF());
    }
        
    public boolean sendMove(final Move move) throws IllegalMoveException, IOException
    {
        System.out.println("Sending move to opponent /w old method ("+move+")");
        String response="";
        o.writeUTF(move.toString());
        response=i.readUTF();
        if(null!=response)
        {
            if(0==response.compareTo("no")) throw new IllegalMoveException("Opponent rejects this move");
            if(0==response.compareTo("ok")) return true;
        }
        return false;
    }
    
    @Override
    public Move waitForMove() throws IOException
    {
        System.out.println("Waiting for opponent move");
        Move move=null;
        do try
        {
            final String mdesc = i.readUTF();
            System.out.println("Opponent sent "+ mdesc);
            move = new Move(mdesc,side);
            o.writeUTF("ok");
            System.out.println("Opponent made move "+move);
        }
        catch(final EOFException eofe)
        {
            eofe.printStackTrace();
            return null;
        }
        catch(final IllegalMoveException ime)
        {
            ime.printStackTrace();
            objectLastMove();
            move=null;
            continue;
        }
        while(null==move);
        
        return move;
    }

    @Override
    public void objectLastMove() throws IOException
    {
            System.out.println("Opponent made illegal move");
            o.writeUTF("no");
    }

    @Override
    public void doMove(final Move move) throws IllegalMoveException, IOException
    {
        System.out.println("Sending move "+ move + " to opponent.");
            o.writeUTF(move.toString());
            String response="";
            response=i.readUTF();
            System.out.println("opponent says: " + response);
            if(0!=("ok".compareTo(response)))throw new IllegalMoveException("Opponent objects to this move");
    }
}
