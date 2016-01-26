package socketchess;

import java.io.IOException;

/**
 *
 * @author saxion
 */
public interface Player
{
    public Move waitForMove() throws IOException;
    
    public void doMove(final Move move) throws IOException, IllegalMoveException;
    
    public void objectLastMove() throws IOException;
}
