package socketchess;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author saxion
 */
public class Opponent
{    
    final Side side;
    
    final DataInputStream i;
    final DataOutputStream o;
    
    public Opponent(final Side side, final Socket socket) throws IOException
    {
        this.side=side;
        this.i=new DataInputStream(socket.getInputStream());
        this.o=new DataOutputStream(socket.getOutputStream());
    }

    public Opponent(Side side, ServerSocket server) throws IOException
    {
        this.side=side;
        final Socket sock=server.accept();
        this.i=new DataInputStream(sock.getInputStream());
        this.o=new DataOutputStream(sock.getOutputStream());
    }
        
    public boolean sendMove(final Move move) throws IllegalMoveException, IOException
    {
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
    
    public Move waitForMove() throws IOException
    {
        Move move=null;
        do try
        {
            move = new Move(i.readUTF(),side);
            o.writeUTF("ok");
        }
        catch(final IllegalMoveException ime)
        {
            o.writeUTF("no");
            move=null;
            continue;
        }
        while(null==move);
        
        return move;
    }
}
