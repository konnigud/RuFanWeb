package is.rufan.tournament.data;

/**
 * Created by Konni on 27.10.2015.
 */
public class TournamentNotInsertedException extends RuntimeException
{
    public TournamentNotInsertedException()
    {
        super();
    }

    public TournamentNotInsertedException(String message)
    {
        super(message);
    }

    public TournamentNotInsertedException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
